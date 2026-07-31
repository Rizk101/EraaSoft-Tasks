<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    String message = request.getParameter("message");
    if (message == null || message.trim().isEmpty()) {
        message = "An unexpected error occurred. Please try again.";
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item Service Error</title>
    <style>
        body { margin: 0; min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; box-sizing: border-box; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }
        .error-card { max-width: 560px; width: 100%; padding: 40px; text-align: center; background: rgba(255, 255, 255, 0.95); border-radius: 20px; box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3); }
        h1 { margin-top: 0; color: #333; }
        p { color: #555; font-size: 1.1rem; line-height: 1.5; }
        a { display: inline-block; margin-top: 16px; padding: 14px 28px; border-radius: 25px; color: white; background: linear-gradient(45deg, #667eea, #764ba2); text-decoration: none; font-weight: 600; }
    </style>
</head>
<body>
    <main class="error-card">
        <h1>Unable to complete request</h1>
        <p><%= escapeHtml(message) %></p>
        <a href="<%= request.getContextPath() %>/ItemController?action=showItems">Back to Items</a>
    </main>
</body>
</html>
