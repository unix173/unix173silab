<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title> Registracija </title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="common/top.jsp"/>

<jsp:include page="common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>REGISTRACIJA</p>
        <br/>
        <form:form method="POST" modelAttribute="userRegistrationForm">
            <form:errors path="*" element="div"/>
            <div class="row formany">
                <div class="col-sm-6 text-right">
                    <label>Korisničko ime:</label>
                </div>
                <div class="col-sm-6 text-left">
                    <form:input path="username" id="inputUsername" placeholder="Korisničko ime"/>
                </div>
            </div>
            <div class="row formany">
                <div class="col-sm-6 text-right">
                    <label>Ime:</label>
                </div>
                <div class="col-sm-6 text-left">
                    <form:input path="name" id="inputName" placeholder="Ime"/>
                </div>
            </div>
            <div class="row formany">
                <div class="col-sm-6 text-right">
                    <label>Prezime:</label>
                </div>
                <div class="col-sm-6 text-left">
                    <form:input path="lastName" id="inputLastname" placeholder="Prezime"/>
                </div>
            </div>
            <div class="row formany">
                <div class="col-sm-6 text-right">
                    <label>Lozinka</label>
                </div>
                <div class="col-sm-6 text-left">
                    <form:input type="password" path="password" id="inputPassword" placeholder="Lozinka"/>
                </div>
            </div>
            <div class="row formany">
                <div class="col-sm-6 text-right">
                    <label>Ponovite lozinku:</label>
                </div>
                <div class="col-sm-6 text-left">
                    <form:input type="password" path="matchingPassword" id="inputMatchingPassword"
                                placeholder="Lozinka"/>
                </div>
            </div>
            <div class="row formany">
                <div class="col-sm-6 text-right">
                    <label>Email</label>
                </div>
                <div class="col-sm-6 text-left">
                    <form:input path="email" id="inputEmail" placeholder="Email"/>
                </div>
            </div>
            <div class="row text-center  formany">
                <button class="btn btn-primary" type="submit">REGISTRACIJA</button>
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script href="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>