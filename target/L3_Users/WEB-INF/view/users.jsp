<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 79817
  Date: 12.06.2021
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h2>Users</h2>
<br>
<table border="1">
    <tr>
        <th>FirstName</th>
        <th>SecondName</th>
        <th>Age</th>
        <th>Email</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="user" items="${users}">

        <c:url var="update_button" value="/update_user">
            <c:param name="ID" value="${user.ID}"/>
            <c:param name="firstName" value="${user.firstName}"/>
            <c:param name="secondName" value="${user.secondName}"/>
            <c:param name="age" value="${user.age}"/>
            <c:param name="email" value="${user.email}"/>
        </c:url>

        <c:url var="delete_button" value="/delete_user">
            <c:param name="ID" value="${user.ID}"/>
        </c:url>

        <tr>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td><input type="button" value="UPDATE"
                       onclick="window.location.href = '${update_button}'"/>
                <input type="button" value="DELETE"
                       onclick="window.location.href = '${delete_button}'"/>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="button" value="ADD"
       onclick="window.location.href='add_new_user'"/>
</body>
</html>
