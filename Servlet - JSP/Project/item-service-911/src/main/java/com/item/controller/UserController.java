package com.item.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.item.model.User;
import com.item.service.UserService;
import com.item.service.impl.UserServiceImpl;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String LOGGED_IN_USER = "loggedInUser";
	private static final String REMEMBERED_EMAIL_COOKIE = "rememberedEmail";

	@Resource(name = "jdbc/item")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("logout".equals(action)) {
			logout(request, response);
			return;
		}
		if (isLoggedIn(request)) {
			redirectToItems(request, response);
			return;
		}
		if ("forgotPassword".equals(action)) {
			request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("signup".equals(action) ? "signup.jsp" : "login.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if ("signup".equals(action)) {
				signup(request, response);
			} else if ("login".equals(action)) {
				login(request, response);
			} else if ("forgotPassword".equals(action)) {
				forgotPassword(request, response);
			} else if ("deleteAccount".equals(action)) {
				deleteAccount(request, response);
			} else {
				redirectWithMessage(request, response, "login.jsp", "Invalid request.");
			}
		} catch (IllegalArgumentException exception) {
			redirectWithMessage(request, response, pageForAction(action), exception.getMessage());
		} catch (RuntimeException exception) {
			log("User operation failed.", exception);
			redirectWithMessage(request, response, pageForAction(action),
					"The request could not be completed. Please try again.");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		User user = getUserService().authenticate(email, request.getParameter("password"));
		if (user == null) {
			redirectWithMessage(request, response, "login.jsp", "Invalid email or password.");
			return;
		}

		storeLoggedInUser(request, user);
		rememberEmail(request, response, user.getEmail());
		redirectToItems(request, response);
	}

	private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"));
		if (!getUserService().register(user)) {
			redirectWithMessage(request, response, "signup.jsp", "Unable to create the account. Please try again.");
			return;
		}

		User authenticatedUser = getUserService().authenticate(user.getEmail(), user.getPassword());
		if (authenticatedUser == null) {
			redirectWithMessage(request, response, "login.jsp", "Account created. Please log in.");
			return;
		}

		storeLoggedInUser(request, authenticatedUser);
		rememberEmail(request, response, authenticatedUser.getEmail());
		redirectToItems(request, response);
	}

	private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean passwordReset = getUserService().resetPassword(request.getParameter("email"), request.getParameter("password"));
		String message = passwordReset ? "Your password has been reset. Please log in." : "No account was found for that email address.";
		redirectWithMessage(request, response, "login.jsp", message);
	}

	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = getLoggedInUser(request);
		if (user == null) {
			redirectWithMessage(request, response, "login.jsp", "Please log in to delete your account.");
			return;
		}
		if (!getUserService().deleteUserById(user.getId())) {
			redirectWithMessage(request, response, "error.jsp", "The account could not be deleted.");
			return;
		}
		logout(request, response, "Your account has been deleted.");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logout(request, response, "You have been logged out.");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		deleteRememberedEmail(request, response);
		deleteSessionCookie(request, response);
		redirectWithMessage(request, response, "login.jsp", message);
	}

	private void storeLoggedInUser(HttpServletRequest request, User user) {
		HttpSession session = request.getSession(true);
		request.changeSessionId();
		session.setAttribute(LOGGED_IN_USER, user);
	}

	private boolean isLoggedIn(HttpServletRequest request) {
		return getLoggedInUser(request) != null;
	}

	private User getLoggedInUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Object user = session == null ? null : session.getAttribute(LOGGED_IN_USER);
		return user instanceof User ? (User) user : null;
	}

	private UserService getUserService() {
		return new UserServiceImpl(dataSource);
	}

	private void rememberEmail(HttpServletRequest request, HttpServletResponse response, String email) {
		Cookie cookie = new Cookie(REMEMBERED_EMAIL_COOKIE, email);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(30 * 24 * 60 * 60);
		cookie.setPath(getCookiePath(request));
		response.addCookie(cookie);
	}

	private void deleteRememberedEmail(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(REMEMBERED_EMAIL_COOKIE, "");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setPath(getCookiePath(request));
		response.addCookie(cookie);
	}

	private void deleteSessionCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("JSESSIONID", "");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setPath(getCookiePath(request));
		response.addCookie(cookie);
	}

	private String getCookiePath(HttpServletRequest request) {
		return request.getContextPath().isEmpty() ? "/" : request.getContextPath();
	}

	private void redirectToItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/ItemController?action=showItems"));
	}

	private void redirectWithMessage(HttpServletRequest request, HttpServletResponse response, String page, String message) throws IOException {
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + page + "?message=" + URLEncoder.encode(message, "UTF-8")));
	}

	private String pageForAction(String action) {
		if ("signup".equals(action)) {
			return "signup.jsp";
		}
		return "forgotPassword".equals(action) ? "forgot-password.jsp" : "login.jsp";
	}
}
