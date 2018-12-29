<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>listUser</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="users" type="java.util.List"--%>
    <%--Мне кажется здесь какое то нарушение инкапсуляции?????? мы передаем данные и возможно их могут увидеть?--%>
    <c:forEach items="${listContact}" var="user">
        <tr>
            <td><c:out value="${user.getId()}" /></td>
            <td><c:out value="${user.getName()}" /></td>
            <td><c:out value="${user.getLogin()}" /></td>
            <td><c:out value="${user.getPassword()}" /></td>
            <td><a href="/admin/edit?action=edit&id=<c:out value="${user.getId()}"/>">Update</a></td>
            <td><a href="/admin/delete?action=delete&id=<c:out value="${user.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<p><a href="/admin/add">Add User</a></p>
</body>
</html>