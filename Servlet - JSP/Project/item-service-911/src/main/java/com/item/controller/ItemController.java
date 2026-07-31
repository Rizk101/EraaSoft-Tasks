package com.item.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.ItemService;
import com.item.service.ItemDetailsService;
import com.item.service.impl.ItemDetailsServiceImpl;
import com.item.service.impl.ItemServiceImpl;

@WebServlet("/ItemController")
public class ItemController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_ACTION = "showItems";

	@Resource(name = "jdbc/item")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!isAuthenticated(request)) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
			return;
		}

		try {
			switch (getAction(request)) {
			case "showItem":
				showItem(request, response);
				break;
			case "addItem":
				addItem(request, response);
				break;
			case "updateItem":
				updateItem(request, response);
				break;
			case "deleteItem":
				deleteItem(request, response);
				break;
			case "showItems":
			default:
				showItems(request, response);
				break;
			}
		} catch (NumberFormatException exception) {
			redirectToError(response, "Invalid item data was provided.");
		} catch (RuntimeException exception) {
			log("Item operation failed.", exception);
			redirectToError(response, "The item operation could not be completed. Please try again.");
		} catch (ServletException exception) {
			log("Item view could not be displayed.", exception);
			redirectToError(response, "The requested page could not be displayed. Please try again.");
		} catch (IOException exception) {
			if (response.isCommitted()) {
				throw exception;
			}
			log("Item request failed.", exception);
			redirectToError(response, "The request could not be completed. Please try again.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String getAction(HttpServletRequest request) {
		String action = request.getParameter("action");
		return action == null || action.trim().isEmpty() ? DEFAULT_ACTION : action;
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (getItemService().removeItemById(getItemId(request))) {
			showItems(request, response);
		} else {
			redirectToError(response, "The requested item could not be deleted.");
		}
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (getItemService().updateItem(createItem(request, getItemId(request)))) {
			showItems(request, response);
		} else {
			redirectToError(response, "The requested item could not be updated.");
		}
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (getItemService().addItem(createItem(request, null))) {
			showItems(request, response);
		} else {
			redirectToError(response, "The item could not be added.");
		}
	}

	private void showItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Item item = getItemService().getItemById(getItemId(request));
		if (item == null) {
			redirectToError(response, "The requested item was not found.");
			return;
		}

		request.setAttribute("itemData", item);
		request.getRequestDispatcher("update-item.jsp").forward(request, response);
	}

	private void showItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> items = getItemService().getItems();
		Set<Long> itemIdsWithDetails = getItemDetailsService().getItemIdsWithDetails();
		request.setAttribute("itemsData", items);
		request.setAttribute("itemIdsWithDetails", itemIdsWithDetails);
		request.getRequestDispatcher("showItems.jsp").forward(request, response);
	}

	private Item createItem(HttpServletRequest request, Long id) {
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int totalNumber = Integer.parseInt(request.getParameter("totalNumber"));
		return id == null ? new Item(name, price, totalNumber) : new Item(id, name, price, totalNumber);
	}

	private Long getItemId(HttpServletRequest request) {
		return Long.parseLong(request.getParameter("id"));
	}

	private ItemService getItemService() {
		return new ItemServiceImpl(dataSource);
	}

	private ItemDetailsService getItemDetailsService() {
		return new ItemDetailsServiceImpl(dataSource);
	}

	private boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute(UserController.LOGGED_IN_USER) != null;
	}

	private void redirectToError(HttpServletResponse response, String message) throws IOException {
		String encodedMessage = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect(response.encodeRedirectURL("error.jsp?message=" + encodedMessage));
	}
}
