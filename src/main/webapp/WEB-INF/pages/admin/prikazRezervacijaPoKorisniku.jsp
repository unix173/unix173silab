<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Rezervacije Korisnika</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>REZERVACIJE KORISNIKA</p>
        <br/>
        <table class="table table-striped">
            <tr>
                <th>Broj rezervacije</th>
                <th>Datum kreiranja</th>
                <th>Korisnik</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${reservations}" var="reservation">
                <tr>
                    <td>
                            ${reservation.id}
                    </td>
                    <td>
                            <fmt:formatDate value="${reservation.creationDate}" pattern="yyyy-MM-dd"/>
                    <td>
                            ${reservation.user.name} ${reservation.user.lastName}
                    </td>
                    <td>
                        <form:form action="prikazStavkiRezervacija" commandName="reservation" method="get">
                            <input type="hidden" name="id" value="${reservation.id}"/>
                            <input type="hidden" name="user.id" value="${reservation.user.id}">
                            <input type="submit" value="DETALJI" class="btn btn-primary btn-sm">
                        </form:form>
                    </td>
                    <td>
                        <form:form action="obrisiRezervacijuAkcija" commandName="reservation" method="post">
                            <input type="hidden" name="id" value="${reservation.id}"/>
                            <input type="submit" value="OBRIŠI" class="btn btn-primary btn-sm">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
