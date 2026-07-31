package com.item.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;
import com.item.service.ItemService;
import com.item.service.impl.ItemDetailsServiceImpl;
import com.item.service.impl.ItemServiceImpl;

@WebServlet("/ItemDetailsController")
public class ItemDetailsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/item")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!isAuthenticated(request)) {
			redirectToLogin(request, response);
			return;
		}
		try {
			if ("showForm".equals(request.getParameter("action"))) {
				showForm(request, response);
			} else {
				redirectToItems(request, response);
			}
		} catch (RuntimeException exception) {
			log("Item details request failed.", exception);
			redirectToError(request, response, "The item details request could not be completed.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!isAuthenticated(request)) {
			redirectToLogin(request, response);
			return;
		}
		try {
			String action = request.getParameter("action");
			if ("add".equals(action)) {
				addItemDetails(request, response);
			} else if ("update".equals(action)) {
				updateItemDetails(request, response);
			} else {
				redirectToItems(request, response);
			}
		} catch (IllegalArgumentException exception) {
			redirectToError(request, response, exception.getMessage());
		} catch (RuntimeException exception) {
			log("Item details update failed.", exception);
			redirectToError(request, response, "The item details could not be saved.");
		}
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long itemId = getItemId(request);
		Item item = getItemService().getItemById(itemId);
		if (item == null) {
			redirectToError(request, response, "The requested item was not found.");
			return;
		}

		ItemDetails itemDetails = getItemDetailsService().getItemDetailsByItemId(itemId);
		request.setAttribute("item", item);
		if (itemDetails == null) {
			request.getRequestDispatcher("add-item-details.jsp").forward(request, response);
		} else {
			request.setAttribute("itemDetails", itemDetails);
			request.getRequestDispatcher("update-item-details.jsp").forward(request, response);
		}
	}

	private void addItemDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ItemDetails itemDetails = createItemDetails(request);
		if (getItemDetailsService().getItemDetailsByItemId(itemDetails.getItemId()) != null) {
			redirectToError(request, response, "Item details already exist. Please update them instead.");
			return;
		}
		if (getItemDetailsService().addItemDetails(itemDetails)) {
			redirectToItems(request, response);
		} else {
			redirectToError(request, response, "The item details could not be added.");
		}
	}

	private void updateItemDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ItemDetails itemDetails = createItemDetails(request);
		if (getItemDetailsService().updateItemDetails(itemDetails)) {
			redirectToItems(request, response);
		} else {
			redirectToError(request, response, "Item details do not exist. Please add them first.");
		}
	}

	private ItemDetails createItemDetails(HttpServletRequest request) {
		return new ItemDetails(getItemId(request), request.getParameter("description"), request.getParameter("category"));
	}

	private long getItemId(HttpServletRequest request) { return Long.parseLong(request.getParameter("itemId")); }
	private boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute(UserController.LOGGED_IN_USER) != null;
	}
	private ItemService getItemService() { return new ItemServiceImpl(dataSource); }
	private ItemDetailsService getItemDetailsService() { return new ItemDetailsServiceImpl(dataSource); }
	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException { response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp")); }
	private void redirectToItems(HttpServletRequest request, HttpServletResponse response) throws IOException { response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/ItemController?action=showItems")); }
	private void redirectToError(HttpServletRequest request, HttpServletResponse response, String message) throws IOException { response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/error.jsp?message=" + java.net.URLEncoder.encode(message, "UTF-8"))); }
}
