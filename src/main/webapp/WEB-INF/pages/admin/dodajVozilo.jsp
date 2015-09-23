<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dodavanje vozila</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>DODAVANJE VOZILA</p>
        <br/>
        <div>
            <c:if test="${not empty dodatoVozilo}">
                <p>${dodatoVozilo}</p>
            </c:if>
        </div>
        <form:form method="POST" modelAttribute="item">
            <div>
                <label>Tip vozila</label>
                <form:select path="tipVozila.id">
                    <c:forEach items="${itemTypes}" var="itemType">
                        <form:option value="${itemType.id} ">${itemType.ime}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <div>
                <label>Broj vozila:</label>
                <form:input path="id" id="inputId" placeholder="Unesite broj vozila"/>
                <button type="submit" class="btn btn-primary btn-sm">SAČUVAJ</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
