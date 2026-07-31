package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;

public class ItemDetailsServiceImpl implements ItemDetailsService {

	private static final String INSERT_DETAILS = "INSERT INTO ITEM_DETAILS (ITEM_ID, DESCRIPTION, CATEGORY) VALUES (?, ?, ?)";
	private static final String UPDATE_DETAILS = "UPDATE ITEM_DETAILS SET DESCRIPTION = ?, CATEGORY = ? WHERE ITEM_ID = ?";
	private static final String SELECT_DETAILS_BY_ITEM_ID = "SELECT ITEM_ID, DESCRIPTION, CATEGORY FROM ITEM_DETAILS WHERE ITEM_ID = ?";
	private static final String SELECT_ITEM_IDS = "SELECT ITEM_ID FROM ITEM_DETAILS";
	private static final String DELETE_DETAILS = "DELETE FROM ITEM_DETAILS WHERE ITEM_ID = ?";

	private final DataSource dataSource;

	public ItemDetailsServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addItemDetails(ItemDetails itemDetails) {
		validate(itemDetails);
		return executeUpdate(INSERT_DETAILS, statement -> {
			statement.setLong(1, itemDetails.getItemId());
			statement.setString(2, itemDetails.getDescription().trim());
			statement.setString(3, itemDetails.getCategory().trim());
		});
	}

	@Override
	public boolean updateItemDetails(ItemDetails itemDetails) {
		validate(itemDetails);
		return executeUpdate(UPDATE_DETAILS, statement -> {
			statement.setString(1, itemDetails.getDescription().trim());
			statement.setString(2, itemDetails.getCategory().trim());
			statement.setLong(3, itemDetails.getItemId());
		});
	}

	@Override
	public ItemDetails getItemDetailsByItemId(Long itemId) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_DETAILS_BY_ITEM_ID)) {
			statement.setLong(1, itemId);
			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next() ? mapItemDetails(resultSet) : null;
			}
		} catch (SQLException exception) {
			throw databaseException("retrieve item details", exception);
		}
	}

	@Override
	public Set<Long> getItemIdsWithDetails() {
		Set<Long> itemIds = new HashSet<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ITEM_IDS);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				itemIds.add(resultSet.getLong("ITEM_ID"));
			}
			return itemIds;
		} catch (SQLException exception) {
			throw databaseException("retrieve item detail references", exception);
		}
	}

	@Override
	public boolean removeItemDetailsByItemId(Long itemId) {
		return executeUpdate(DELETE_DETAILS, statement -> statement.setLong(1, itemId));
	}

	private boolean executeUpdate(String sql, StatementParameterSetter parameterSetter) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			parameterSetter.setParameters(statement);
			return statement.executeUpdate() > 0;
		} catch (SQLException exception) {
			throw databaseException("modify item details", exception);
		}
	}

	private ItemDetails mapItemDetails(ResultSet resultSet) throws SQLException {
		return new ItemDetails(resultSet.getLong("ITEM_ID"), resultSet.getString("DESCRIPTION"), resultSet.getString("CATEGORY"));
	}

	private void validate(ItemDetails itemDetails) {
		if (itemDetails == null || itemDetails.getItemId() == null || itemDetails.getItemId() <= 0 || isBlank(itemDetails.getDescription()) || isBlank(itemDetails.getCategory())) {
			throw new IllegalArgumentException("Description and category are required.");
		}
		if (itemDetails.getDescription().trim().length() > 1000 || itemDetails.getCategory().trim().length() > 100) {
			throw new IllegalArgumentException("Item details are too long.");
		}
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}

	private IllegalStateException databaseException(String operation, SQLException exception) {
		return new IllegalStateException("Unable to " + operation + ".", exception);
	}

	@FunctionalInterface
	private interface StatementParameterSetter {
		void setParameters(PreparedStatement statement) throws SQLException;
	}
}
