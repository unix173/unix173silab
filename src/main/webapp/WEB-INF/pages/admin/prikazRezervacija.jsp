<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Sve Rezervacije</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>SVE REZERVACIJE</p>
        <br/>
        <table class="table table-striped">
            <tr>
                <th>Broj rezervacije</th>
                <th>Datum kreiranja</th>
                <th>Korisnik</th>
                <th>Cena rezervacije</th>
            </tr>
            <c:forEach items="${reservations}" var="reservation">
                <td>
                        ${reservation.id}
                </td>
                <td>
                    <fmt:formatDate value="${reservation.creationDate}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                        ${reservation.user.name} ${reservation.user.lastName}
                </td>
                <td>
                        ${reservation.price}
                </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
