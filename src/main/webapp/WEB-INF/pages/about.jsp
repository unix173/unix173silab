<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="common/top.jsp"/>

<jsp:include page="common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>ABOUT</p>
        <br/>

        <p>WEB Sistem za izdavanje vozila u Java okruženju.</p>

        <p>Korišćene tehnologije:</p>
        <ul>
            <li>Spring MVC 4.1.1.RELEASE</li>
            <li>Hibernate 4.3.10.Final</li>
            <li>Spring Security 3.2.0.RELEASE</li>
            <li>Jsp</li>
            <li>MySQL</li>
            <li>Maven</li>
        </ul>
    </div>
</div>


<jsp:include page="common/footer.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script href="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>
