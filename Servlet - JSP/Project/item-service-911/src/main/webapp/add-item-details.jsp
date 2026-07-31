<%@ page import="com.item.controller.UserController" %>
<%@ page import="com.item.model.Item" %>
<%!
    private String escapeHtml(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    if (session.getAttribute(UserController.LOGGED_IN_USER) == null) { response.sendRedirect(request.getContextPath() + "/login.jsp"); return; }
    Item item = (Item) request.getAttribute("item");
    if (item == null) { response.sendRedirect(request.getContextPath() + "/ItemController?action=showItems"); return; }
%>
<!DOCTYPE html>
<html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Add Item Details</title>
<style>body{margin:0;min-height:100vh;display:flex;align-items:center;justify-content:center;padding:20px;box-sizing:border-box;font-family:'Segoe UI',Tahoma,Geneva,Verdana,sans-serif;background:linear-gradient(135deg,#71b7e6,#9b59b6)}.container{width:100%;max-width:620px;padding:40px 50px;background:rgba(255,255,255,.95);border-radius:20px;box-shadow:0 15px 50px rgba(0,0,0,.2)}h1{text-align:center;color:#333;margin:0 0 12px;font-size:2.6rem}.item{text-align:center;color:#666;margin-bottom:30px}label{display:block;margin:18px 0 7px;color:#555;font-weight:600}input,textarea{width:100%;padding:14px;box-sizing:border-box;border:0;border-bottom:2px solid #bbb;font:inherit}textarea{min-height:120px;resize:vertical}button{width:100%;margin-top:32px;padding:16px;border:0;border-radius:50px;color:#fff;font-weight:600;font-size:1rem;cursor:pointer;background:linear-gradient(45deg,#71b7e6,#9b59b6)}.back{text-align:center;margin:25px 0 0}.back a{color:#6b55b3;font-weight:600;text-decoration:none}</style></head>
<body><main class="container"><h1>Add Item Details</h1><p class="item"><%= escapeHtml(item.getName()) %></p><form action="<%= request.getContextPath() %>/ItemDetailsController" method="post"><input type="hidden" name="action" value="add"><input type="hidden" name="itemId" value="<%= item.getId() %>"><label>Description</label><textarea name="description" required maxlength="1000"></textarea><label>Category</label><input type="text" name="category" required maxlength="100"><button type="submit">Add Item Details</button></form><p class="back"><a href="<%= request.getContextPath() %>/ItemController?action=showItems">Back To Items</a></p></main></body></html>
