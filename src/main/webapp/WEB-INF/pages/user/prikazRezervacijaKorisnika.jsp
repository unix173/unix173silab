<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Prethodne rezervaije</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>REZERVACIJE</p>
        <br/>
        <table class="table table-striped">
            <tr>
                <th>Broj rezervacije</th>
                <th>Datum kreiranja</th>
                <th>Cena rezervacije</th>
                <th></th>
            </tr>
            <c:forEach items="${reservations}" var="reservation">
                <tr>
                    <td>
                            ${reservation.id}
                    </td>
                    <td>
                        <fmt:formatDate value="${reservation.datumKreiranja}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                            ${reservation.ukupnaCena}
                    </td>
                    <td>
                        <form action="prikazStavkiRezervacijeKorisnika" method="get">
                            <input type="hidden" name="id" value="${reservation.id}"/>
                            <input type="submit" value="DETALJI" class="btn btn-primary btn-sm">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
