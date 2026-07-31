package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.ItemService;

public class ItemServiceImpl implements ItemService {

	private static final String INSERT_ITEM = "INSERT INTO ITEM (NAME, PRICE, TOTAL_NUMBER) VALUES (?, ?, ?)";
	private static final String UPDATE_ITEM = "UPDATE ITEM SET NAME = ?, PRICE = ?, TOTAL_NUMBER = ? WHERE ID = ?";
	private static final String SELECT_ITEM_BY_ID = "SELECT ID, NAME, PRICE, TOTAL_NUMBER FROM ITEM WHERE ID = ?";
	private static final String SELECT_ALL_ITEMS = "SELECT ID, NAME, PRICE, TOTAL_NUMBER FROM ITEM";
	private static final String DELETE_ITEM = "DELETE FROM ITEM WHERE ID = ?";
	private static final String DELETE_ITEM_DETAILS = "DELETE FROM ITEM_DETAILS WHERE ITEM_ID = ?";

	private final DataSource dataSource;

	public ItemServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addItem(Item item) {
		validateItem(item, false);
		return executeUpdate(INSERT_ITEM, statement -> {
			statement.setString(1, item.getName().trim());
			statement.setDouble(2, item.getPrice());
			statement.setInt(3, item.getTotalNumber());
		});
	}

	@Override
	public boolean updateItem(Item item) {
		validateItem(item, true);
		return executeUpdate(UPDATE_ITEM, statement -> {
			statement.setString(1, item.getName().trim());
			statement.setDouble(2, item.getPrice());
			statement.setInt(3, item.getTotalNumber());
			statement.setLong(4, item.getId());
		});
	}

	@Override
	public Item getItemById(Long id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ITEM_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next() ? mapItem(resultSet) : null;
			}
		} catch (SQLException exception) {
			throw databaseException("retrieve the item", exception);
		}
	}

	@Override
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ITEMS);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				items.add(mapItem(resultSet));
			}
			return items;
		} catch (SQLException exception) {
			throw databaseException("retrieve items", exception);
		}
	}

	@Override
	public boolean removeItemById(Long id) {
		try (Connection connection = dataSource.getConnection()) {
			connection.setAutoCommit(false);
			try (PreparedStatement deleteDetails = connection.prepareStatement(DELETE_ITEM_DETAILS);
					PreparedStatement deleteItem = connection.prepareStatement(DELETE_ITEM)) {
				deleteDetails.setLong(1, id);
				deleteDetails.executeUpdate();
				deleteItem.setLong(1, id);
				boolean itemDeleted = deleteItem.executeUpdate() > 0;
				connection.commit();
				return itemDeleted;
			} catch (SQLException exception) {
				connection.rollback();
				throw exception;
			}
		} catch (SQLException exception) {
			throw databaseException("delete the item", exception);
		}
	}

	private boolean executeUpdate(String sql, StatementParameterSetter parameterSetter) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			parameterSetter.setParameters(statement);
			return statement.executeUpdate() > 0;
		} catch (SQLException exception) {
			throw databaseException("modify the item", exception);
		}
	}

	private Item mapItem(ResultSet resultSet) throws SQLException {
		return new Item(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getDouble("PRICE"),
				resultSet.getInt("TOTAL_NUMBER"));
	}

	private void validateItem(Item item, boolean requiresId) {
		if (item == null || isBlank(item.getName()) || !Double.isFinite(item.getPrice()) || item.getPrice() <= 0 || item.getTotalNumber() < 0) {
			throw new IllegalArgumentException("Name, a positive price, and a non-negative total number are required.");
		}
		if (item.getName().trim().length() > 255) {
			throw new IllegalArgumentException("Item name is too long.");
		}
		if (requiresId && (item.getId() == null || item.getId() <= 0)) {
			throw new IllegalArgumentException("A valid item ID is required.");
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
