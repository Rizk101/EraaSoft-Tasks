package com.item.service.impl;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.sql.DataSource;

import com.item.model.User;
import com.item.service.UserService;

public class UserServiceImpl implements UserService {

	private static final String INSERT_USER = "INSERT INTO APP_USER (NAME, EMAIL, PASSWORD_HASH, PASSWORD_SALT) VALUES (?, ?, ?, ?)";
	private static final String SELECT_USER_BY_EMAIL = "SELECT ID, NAME, EMAIL, PASSWORD_HASH, PASSWORD_SALT FROM APP_USER WHERE EMAIL = ?";
	private static final String UPDATE_PASSWORD = "UPDATE APP_USER SET PASSWORD_HASH = ?, PASSWORD_SALT = ? WHERE EMAIL = ?";
	private static final String DELETE_USER = "DELETE FROM APP_USER WHERE ID = ?";
	private static final int HASH_ITERATIONS = 65536;
	private static final int HASH_LENGTH = 256;
	private static final int SALT_LENGTH = 16;
	private static final java.util.regex.Pattern EMAIL_PATTERN = java.util.regex.Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

	private final DataSource dataSource;

	public UserServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean register(User user) {
		validateRegistration(user);
		byte[] salt = new byte[SALT_LENGTH];
		new SecureRandom().nextBytes(salt);

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
			statement.setString(1, user.getName().trim());
			statement.setString(2, normalizeEmail(user.getEmail()));
			statement.setString(3, hashPassword(user.getPassword(), salt));
			statement.setString(4, Base64.getEncoder().encodeToString(salt));
			return statement.executeUpdate() == 1;
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to register the user.", exception);
		}
	}

	@Override
	public User authenticate(String email, String password) {
		if (isBlank(email) || isBlank(password)) {
			return null;
		}

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
			statement.setString(1, normalizeEmail(email));
			try (ResultSet resultSet = statement.executeQuery()) {
				if (!resultSet.next() || !passwordMatches(password, resultSet.getString("PASSWORD_HASH"), resultSet.getString("PASSWORD_SALT"))) {
					return null;
				}
				return new User(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("EMAIL"));
			}
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to authenticate the user.", exception);
		}
	}

	@Override
	public boolean resetPassword(String email, String newPassword) {
		validateEmail(email);
		validatePassword(newPassword);
		byte[] salt = new byte[SALT_LENGTH];
		new SecureRandom().nextBytes(salt);
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {
			statement.setString(1, hashPassword(newPassword, salt));
			statement.setString(2, Base64.getEncoder().encodeToString(salt));
			statement.setString(3, normalizeEmail(email));
			return statement.executeUpdate() == 1;
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to reset the password.", exception);
		}
	}

	@Override
	public boolean deleteUserById(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("A valid user ID is required.");
		}
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
			statement.setLong(1, id);
			return statement.executeUpdate() == 1;
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to delete the account.", exception);
		}
	}

	private void validateRegistration(User user) {
		if (user == null || isBlank(user.getName()) || isBlank(user.getEmail()) || isBlank(user.getPassword())) {
			throw new IllegalArgumentException("Name, email, and password are required.");
		}
		if (user.getName().trim().length() > 100 || user.getEmail().trim().length() > 255) {
			throw new IllegalArgumentException("Name or email is too long.");
		}
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());
	}

	private void validatePassword(String password) {
		if (isBlank(password) || password.length() < 8) {
			throw new IllegalArgumentException("Password must contain at least 8 characters.");
		}
	}

	private void validateEmail(String email) {
		if (isBlank(email) || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
			throw new IllegalArgumentException("A valid email address is required.");
		}
	}

	private boolean passwordMatches(String password, String storedHash, String storedSalt) {
		byte[] expectedHash = Base64.getDecoder().decode(storedHash);
		byte[] actualHash = Base64.getDecoder().decode(hashPassword(password, Base64.getDecoder().decode(storedSalt)));
		return java.security.MessageDigest.isEqual(expectedHash, actualHash);
	}

	private String hashPassword(String password, byte[] salt) {
		PBEKeySpec specification = new PBEKeySpec(password.toCharArray(), salt, HASH_ITERATIONS, HASH_LENGTH);
		try {
			byte[] hash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(specification).getEncoded();
			return Base64.getEncoder().encodeToString(hash);
		} catch (GeneralSecurityException exception) {
			throw new IllegalStateException("Unable to secure the password.", exception);
		} finally {
			specification.clearPassword();
		}
	}

	private String normalizeEmail(String email) {
		return email.trim().toLowerCase(java.util.Locale.ROOT);
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
