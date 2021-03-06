<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Izmena lozinke</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>PROMENA LOZINKE</p>
        <br/>
        <form:form method="POST" modelAttribute="changePasswordForm">
            <form:errors path="*" element="div"/>
            <form:input type="hidden" path="id" placeholder="${changePasswordForm.id}"/>
            <form:input type="hidden" path="name" placeholder="${changePasswordForm.name}"/>
            <form:input type="hidden" path="lastName" placeholder="${changePasswordForm.lastName}"/>
            <form:input type="hidden" path="username" placeholder="${changePasswordForm.username}"/>
            <form:input type="hidden" path="email" placeholder="${changePasswordForm.email}"/>
            <div>
                <label>Lozinka</label>
                <form:input type="password" path="oldPassword" id="inputOldPassword"
                            placeholder="Unesite staru lozinku"/>
            </div>
            <div>
                <label>Nova Lozinka</label>
                <form:input type="password" path="newPassword" id="inputNewPassword"
                            placeholder="Ponovite novu lozinku"/>
            </div>
            <div>
                <label>Nova Lozinka</label>
                <form:input type="password" path="matchingNewPassword" id="inputMatchingNewPassword"
                            placeholder="Ponovite novu lozinku"/>
            </div>
            <button type="submit" class="btn btn-primary btn-sm">Izmeni</button>
        </form:form>
    </div>
</div>
</body>
</html>
