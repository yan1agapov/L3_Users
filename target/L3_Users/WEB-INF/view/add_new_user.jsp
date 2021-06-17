<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 79817
  Date: 13.06.2021
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding new user</title>
</head>
<body>

<h2> Enter new record</h2>

<br>

<form:form action="save_user" method="post" modelAttribute="user">

    <form:hidden path="ID"/>

    firstName <form:input path="firstName" type="text" required="required"/>
    <br><br>
    secondName <form:input path="secondName" type="text" required="required"/>
    <br><br>
    age <form:input path="age" type="number" required="required" min="1" max="150"/>
    <br><br>
    email <form:input path="email" type="text" required="required"/>
    <br><br>
    <input type="submit" value="SAVE">
</form:form>

</body>
</html>
