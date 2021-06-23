<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">


<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-between">
        <a href="<c:url value="/app/mainMenu"/>" class="navbar-brand main-logo main-logo-smaller">
            Zaplanuj <span>Jedzonko</span>
        </a>
        <div class="d-flex justify-content-around center">
            <h4 class="text-light mr-3"><c:out value="${firstName}"></c:out></h4>
            <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
            <div class="center">
                <a href="<c:url value="/logout"/>" class="navbar-brand main-logo main-logo-smaller">
                    <span><h4>Wyloguj</h4></span>
                </a>
            </div>
        </div>
    </nav>
</header>
