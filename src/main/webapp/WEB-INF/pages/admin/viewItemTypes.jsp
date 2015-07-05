<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Pregled tipova vozila</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>TIPOVI VOZILA</p>
        <br/>
        <form method="get">
            <input type="text" name="keyword"/>
            <input type="submit" value="PRETRAŽI" class="btn btn-primary btn-sm">
        </form>
        <table class="table table-striped">
            <tr>
                <th>Tip vozila</th>
                <th>Cena po danu</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${itemTypes}" var="itemType">
                <tr>
                    <td>${itemType.name}</td>
                    <td>${itemType.price}</td>
                    <td>
                        <form:form name="viewItems" method="get" action="viewItems" commandName="itemType">
                            <form:input type="hidden" path="id" value="${itemType.id}"/>
                            <input type="submit" name="button" value="VOZILA" class="btn btn-primary btn-sm"/>
                        </form:form>
                    </td>
                    <td>
                        <form:form name="modify" method="post" action="modifyItemTypeAction" commandName="itemType">
                            <form:input type="hidden" path="id" value="${itemType.id}"/>
                            <input type="submit" name="button" value="IZMENI" class="btn btn-primary btn-sm"/>
                        </form:form>
                    </td>
                    <td>
                        <form:form name="delete" method="post" action="deleteItemType" commandName="itemType">
                            <form:input type="hidden" path="id" value="${itemType.id}"/>
                            <input type="submit" name="button" value="UKLONI" class="btn btn-primary btn-sm"
                                   <c:if test="${itemType.items.size() !=0 }">disabled</c:if> />
                        </form:form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
