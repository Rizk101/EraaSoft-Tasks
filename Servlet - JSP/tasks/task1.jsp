
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%!
    // Define two variables
    int id = 101;
    String name = "Karim";

    // Function that takes id and name and returns concatenation
    String getInfo(int id, String name) {
        return id + " " + name;
    }
%>

<html>
<head>
    <title>JSP Example</title>
</head>
<body>

<%
    // Call the function and print the result
    out.println(getInfo(id, name));
%>

</body>
</html>
