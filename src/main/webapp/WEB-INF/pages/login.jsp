<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login Page</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="common/top.jsp"/>

<jsp:include page="common/menu.jsp"/>

<div class="container">
    <div class="contentmain">
        <p>PRIJAVLJIVANJE</p>
        <br/>

        <div id="login-box">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
                Razlog: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <form name='loginForm'
                  action="<c:url value='j_spring_security_check' />" method='POST'>

                <table>
                    <tr>
                        <td>Korisničko ime:</td>
                        <td><input type='text' name='username' value=''></td>
                    </tr>
                    <tr>
                        <td>Lozinka:</td>
                        <td><input type='password' name='password'/></td>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit"
                                               value="PRIJAVA" class="btn btn-primary btn-sm"/></td>
                    </tr>
                </table>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>
        </div>
    </div>
</div>

</body>
</html>
