<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Izmena tipa vozila</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>TIP VOZILA</p>

        <p>IZMENA TIPA VOZILA</p>
        <br/>
        <form:form name="modify" commandName="itemType" method="post" action="updateItemTypeAction">
            <form:input type="hidden" path="id" value="${itemType.id}"/>
            <form:input type="text" path="name" value="${itemType.name}"/>
            <form:input type="text" path="price" value="${itemType.price}"/>
            <button type="submit" class="btn btn-primary btn-sm">Sačuvaj</button>
        </form:form>
    </div>
</div>
</body>
</html>
