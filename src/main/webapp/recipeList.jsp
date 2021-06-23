<%--
  Created by IntelliJ IDEA.
  User: vampi
  Date: 02.11.2020
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>

<%--Dodany cały widok recipe list --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>
<%@ include file="fragments/headerDashboard.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <ul class="nav flex-column long-bg">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/app/mainMenu"/>">
                    <span>Pulpit</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/app/recipe/list"/>">
                    <span>Przepisy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/app/plan/list"/>">
                    <span>Plany</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/app/user/edit"/>">
                    <span>Edytuj dane</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="<c:url value="/app/edit/password"/>">
                    <span>Zmień hasło</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <c:if test="${adminUser}">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/super/admin/user"/>">
                    <span>Użytkownicy</span>
                        <i class="fas fa-angle-right"></i>
                    </a>
                </li>
            </c:if>
        </ul>


        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">Lista Przepisów</h3>
                    </div>
                    <div class="col noPadding d-flex justify-content-end mb-2">
                        <a href="<c:url value="/app/recipe/add"/>" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj przepis</a>
                    </div>
                </div>
                <table class="table border-bottom schedules-content">
                    <thead>
                    <tr class="d-flex text-color-darker">
                        <th class="col-1">ID</th>
                        <th class="col-2">NAZWA</th>
                        <th class="col-7">OPIS</th>
                        <th class="col-2 center">AKCJE</th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${recipeList}" var="recipe">
                    <tr class="d-flex">
                        <td class="col-1">${recipe.id}</td>
                        <td class="col-2">${recipe.name}</td>
                        <td class="col-7">${recipe.description}</td>
                        <td class="col-2 center">
                            <a onclick="return confirm('Czy na pewno chcesz usunąć przepis ? ')" href="<c:url value="/app/recipes/deleteRecipe"><c:param name="recipeId" value="${recipe.id}"/></c:url>" id="delete" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            <a href="<c:url value="/app/recipes/details"><c:param name="recipeId" value="${recipe.id}"/></c:url>"  class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            <a href="<c:url value="/app/recipe/edit"><c:param name="recipeId" value="${recipe.id}"/></c:url>" class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
