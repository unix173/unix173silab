<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title> Svi korisnici </title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>KORISNICI</p>
        <br/>
        <form method="get">
            <input type="text" name="keyword"/>
            <input type="submit" value="PRETRAŽI" class="btn btn-primary btn-sm">
        </form>
        <table class="table table-striped">

            <tr>
                <th>Ime</th>
                <th>Prezime</th>
                <th>Korisničko ime</th>
                <th>Mail</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.lastName}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>
                        <form:form name="delete" method="post" action="deleteUserAction" commandName="user">
                            <form:input type="hidden" path="id" value="${user.id}"/>
                            <input type="submit" name="button" value="OBRIŠI" class="btn btn-primary btn-sm"
                                   <c:if test="${user.IsAdmin()}">disabled</c:if> />
                        </form:form>
                    </td>
                    <td>
                        <form name="viewReservations" method="get" action="viewReservationsPerUser">
                            <input type="hidden" name="id" value="${user.id}"/>
                            <input type="submit" value="REZERVACIJE" class="btn btn-primary btn-sm"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
