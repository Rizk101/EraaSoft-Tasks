<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="com.item.controller.UserController" %>
<%!
    private String escapeHtml(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    if (session.getAttribute(UserController.LOGGED_IN_USER) != null) {
        response.sendRedirect(request.getContextPath() + "/ItemController?action=showItems");
        return;
    }
    String rememberedEmail = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("rememberedEmail".equals(cookie.getName())) {
                rememberedEmail = cookie.getValue();
                break;
            }
        }
    }
    String message = request.getParameter("message");
%>
<!DOCTYPE html>
<html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Login</title>
<style>body{margin:0;min-height:100vh;display:flex;align-items:center;justify-content:center;padding:20px;box-sizing:border-box;font-family:'Segoe UI',Tahoma,Geneva,Verdana,sans-serif;background:linear-gradient(135deg,#71b7e6,#9b59b6)}.container{width:100%;max-width:520px;padding:40px 50px;background:rgba(255,255,255,.95);border-radius:20px;box-shadow:0 15px 50px rgba(0,0,0,.2)}h1{text-align:center;color:#333;margin:0 0 35px;font-size:2.6rem}label{display:block;margin:18px 0 7px;color:#555;font-weight:600}input{width:100%;padding:14px;box-sizing:border-box;border:0;border-bottom:2px solid #bbb;font-size:1rem}button{width:100%;margin-top:32px;padding:16px;border:0;border-radius:50px;color:#fff;font-weight:600;font-size:1rem;cursor:pointer;background:linear-gradient(45deg,#71b7e6,#9b59b6)}.message{padding:12px;border-radius:8px;background:#fdecec;color:#a33;text-align:center}.link{text-align:center;margin:25px 0 0}.link a{color:#6b55b3;font-weight:600;text-decoration:none}</style></head>
<body><main class="container"><h1>Login</h1><% if (message != null) { %><p class="message"><%= escapeHtml(message) %></p><% } %>
<form action="<%= request.getContextPath() %>/UserController" method="post"><input type="hidden" name="action" value="login"><label>Email</label><input type="email" name="email" required value="<%= escapeHtml(rememberedEmail) %>"><label>Password</label><input type="password" name="password" required><button type="submit">Login</button></form><p class="link">New here? <a href="<%= request.getContextPath() %>/signup.jsp">Create an account</a><br><a href="<%= request.getContextPath() %>/forgot-password.jsp">Forgot password?</a></p></main></body></html>
