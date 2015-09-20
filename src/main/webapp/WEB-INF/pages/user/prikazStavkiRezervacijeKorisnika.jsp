<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Rezervacija</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>STAVKE REZERVACIJE</p>
        <br/>
        <table class="table table-striped">
            <tr>
                <th>Broj stavke</th>
                <th>Tip vozila</th>
                <th>Broj vozila</th>
                <th>Datum zaduženja</th>
                <th>Datum razduženja</th>
                <th></th>
            </tr>
            <c:forEach items="${reservationEntries}" var="reservationEntry">
                <td>${reservationEntry.id}</td>
                <td>${reservationEntry.item.itemType.name}</td>
                <td>${reservationEntry.item.id}</td>
                <td><fmt:formatDate value="${reservationEntry.reservationStartDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${reservationEntry.reservationEndDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
