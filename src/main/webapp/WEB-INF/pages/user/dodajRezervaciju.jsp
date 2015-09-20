<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Kreiranje rezervacije</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <script>
        $(function () {
            $("#reservationStartDate").datepicker();
            $("#reservationEndDate").datepicker();
        });
    </script>
</head>
<body>
<jsp:include page="../common/top.jsp"/>

<jsp:include page="../common/menu.jsp"/>

<div class="container">
    <div class="contentmain">

        <p>KREIRANJE REZERVACIJE</p>
        <br/>
        <c:if test="${successfulReservation != null}">
            <c:if test="${successfulReservation}">
                <div class="text-success">Rezervacija je uspešno sačuvana!</div>
            </c:if>
            <c:if test="${!successfulReservation}">
                <div class="text-warning">Došlo je do greške, molimo pokušajte ponovo.</div>
            </c:if>
        </c:if>
        <div>
            <table class="table table-striped">
                <tr>
                    <th>Vozilo</th>
                    <th>Količina</th>
                    <th>Datum zaduženja</th>
                    <th>Datum razduženja</th>
                </tr>
                <c:forEach items="${addReservationEntryForms}" var="addReservationEntryForm">
                    <tr>
                        <td>${addReservationEntryForm.itemTypeName}</td>
                        <td>${addReservationEntryForm.desiredQuantity}</td>
                        <td><fmt:formatDate value="${addReservationEntryForm.reservationStartDate}"
                                            pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${addReservationEntryForm.reservationEndDate}"
                                            pattern="yyyy-MM-dd"/></td>

                    </tr>
                </c:forEach>
            </table>
            <hr/>
            <br/><br/>
        </div>
        <c:if test="${!(reservationInProgress || validationFailed)}">
            <div>${dateValidationError}</div>
            <div>
                <label>Unesite željeni period korišćenja vozila</label>
                <form:form commandName="addReservationEntryForm">
                    <label>Datum zaduženja: </label><form:input path="reservationStartDate"/>
                    <label>Datum razduženja: </label><form:input path="reservationEndDate"/>
                    <input type="submit" value="DALJE" class="btn btn-primary btn-sm">
                </form:form>
            </div>
        </c:if>

        <c:if test="${reservationInProgress || validationFailed}">

            <c:if test="${validationFailed}">
                <div class="text-warning">Količina nije dobro upisana!</div>
            </c:if>

            <c:forEach items="${itemTypeDTOList}" var="itemTypeDTO">
                <form:form action="dodajStavkuRezervacijeAkcija" method="post" commandName="addReservationEntryForm">
                    <div>
                        <span>${itemTypeDTO.itemTypeName},</span><span> količina : ${itemTypeDTO.quantity}</span>
                        <form:input path="itemTypeId" value="${itemTypeDTO.itemTypeId}" type="hidden"/>
                        <form:input path="desiredQuantity" type="text"/>
                        <form:input path="realQuantity" type="hidden" value="${itemTypeDTO.quantity}"/>
                        <form:input path="itemTypeName" type="hidden" value="${itemTypeDTO.itemTypeName}"/>
                        <fmt:formatDate value="${addReservationEntryForm.reservationStartDate}" pattern="MM/dd/yyyy"
                                        var="startDate"/>
                        <fmt:formatDate value="${addReservationEntryForm.reservationEndDate}" pattern="MM/dd/yyyy"
                                        var="endDate"/>
                        <form:input path="reservationStartDate" type="hidden" value="${startDate}"/>
                        <form:input path="reservationEndDate" type="hidden" value="${endDate}"/>
                        <input type="submit" value="+" class="btn btn-primary btn-sm"/>
                    </div>
                </form:form>
            </c:forEach>

        </c:if>

        <c:if test="${addReservationEntryForms.size() > 0}">
            <table class="table table-striped">
                <tr>
                    <td>
                        <form:form action="restartujRezervacijuAkcija">
                            <form:errors path="*" element="div"/>
                            <input type="submit" value="KREIRAJ NOVU REZERVACIJU" class="btn btn-primary btn-sm"/>
                        </form:form>
                    </td>
                    <td>
                        <form:form action="dodajRezervacijuAkcija">
                            <form:errors path="*" element="div"/>
                            <input type="submit" value="SAČUVAJ REZERVACIJU" class="btn btn-primary btn-sm"/>
                        </form:form>
                    </td>
                </tr>
            </table>
        </c:if>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>

<script href="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>
