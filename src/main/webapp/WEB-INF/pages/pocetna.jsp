<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> Sistem za izdavanje vozila </title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="common/top.jsp"/>

<jsp:include page="common/menu.jsp"/>

<div class="container">
    <div class="text-center">
        <br/>

        <h2>DOBRODOŠLI NA SISTEM ZA IZDAVANJE VOZILA</h2>
        <br/>

        <div>
            <c:if test="${not empty success}">${success}</c:if>
        </div>
        <div>
            <c:if test="${not empty izmenjenKorisnik}">${izmenjenKorisnik}</c:if>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script href="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>