<%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 27.10.2020
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zaplanuj Jedzonko</title>
</head>
<body>
<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-around">
        <a href="/" class="navbar-brand main-logo">
            Zaplanuj <span>Jedzonko</span>
        </a>
        <ul class="nav nounderline text-uppercase">
            <li class="nav-item ml-4">
                <a class="nav-link color-header" href="<c:url value="/login"/>">logowanie</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link color-header" href="<c:url value="/register"/>">rejestracja</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link" href="<c:url value="/about"/>">o aplikacji</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="<c:url value="/recipes/search"/>">Przepisy</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="<c:url value="/contact"/>">Kontakt</a>
            </li>
        </ul>
    </nav>
</header>
</body>
</html>
