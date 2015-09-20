<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Pregled vozila</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>VOZILA</p>
        <br/>
        <c:if test="${itemType.items.size() == 0}">
        <p>Trenutno nema automobila ovog tipa</p>
        </c:if>
        <table class="table table-striped">
            <tr>
                <th>Registarski broj</th>
                <th></th>
            </tr>
            <c:forEach items="${itemType.items}" var="item">
                <tr>
                    <td>${item.id}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
