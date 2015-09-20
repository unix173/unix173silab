<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/pocetna"><span class="glyphicon glyphicon-home"></span> </a></li>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin/addItem">Dodavanje vozila </a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/addItemType">Dodavanje tipa vozila</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/viewItemTypes">Tipovi vozila</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/viewUsers">Korisnici</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/viewReservations">Rezervacije</a></li>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_USER')">
                    <li><a href="${pageContext.request.contextPath}/user/addReservation">Kreiranje rezervacije</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/changePassword">Promena šifre</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/viewUserReservations">Prethodne rezervacije</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%-- Register and login / logout --%>
                <sec:authorize access="isAnonymous()">
                    <li><a href="${pageContext.request.contextPath}/registracija">Registracija</a></li>
                    <li><a href="${pageContext.request.contextPath}/prijavljivanje">Prijavljivanje</a></li>
                </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <form action="/j_spring_security_logout" method="post" id="logoutForm">
                            <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <li>
                            <a href="#" onclick="document.getElementById('logoutForm').submit()">Odjava</a>
                        </li>
                    </sec:authorize>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav>

