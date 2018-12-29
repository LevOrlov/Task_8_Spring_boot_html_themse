<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Edit</title>
</head>
<body>

<%--Мне кажется здесь какое то нарушение инкапсуляции?????? мы передаем данные и возможно их могут увидеть?--%>
<form method="POST" action='/admin/edit?action=edit&id=<c:out value="${user.getId()}"/>&role=<c:out value="${user.getRole()}"/>' modelAttribute="user">
    Name : <input
        type="text" name="name"
        value="<c:out value="${user.getName()}" />" /> <br />
    Login : <input
        type="text" name="login"
        value="<c:out value="${user.getLogin()}" />" /> <br />
    Password : <input type="text" name="password"
        value="<c:out value="${user.getPassword()}" />" /> <br /><input
        type="submit" value="Submit" />
</form>
</body>
</html>