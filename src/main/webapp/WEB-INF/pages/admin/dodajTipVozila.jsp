<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dodavanje tipa vozila</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>DODAVANJE TIPA VOZILA</p>
        <br/>
        <form:form method="POST" modelAttribute="itemType">
            <div>
                <label>Model: </label>
                <form:input path="ime" id="inputName" placeholder="Unesite model vozila"/>
                <label>Cena :</label>
                <form:input path="cena" id="inputPrice" placeholder="Unesite cenu vozila"/>
                <button type="submit"  class="btn btn-primary btn-sm">SAČUVAJ</button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
