<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addForm.css"/>
    <script validateDate src="${pageContext.request.contextPath}/js/dateValidator.js"></script>
    <title>
        <fmt:message key="label.application.title"/>
    </title>
</head>
<body>

<div class="header">
    <jsp:include page="parts/header.jsp"/>
</div>

<div class="content">

    <div class="side">
        <jsp:include page="parts/leftPane.jsp"/>
    </div>
    <div class="main">
        <div class="form">
            <form name="bookingForm" action="controller?command=booking"  onsubmit="return validateDate()" method="post">

                <h2><fmt:message key="label.booking_form.text"/></h2>

                <label for="dateCheckIn"><fmt:message key="label.booking_form.check_in"/> <br>
                    <input type=date id="dateCheckIn" name="dateCheckIn" required>
                </label>
                <div class="warnCheck_in" id="warnCheck_in" style="visibility:hidden"><fmt:message key="label.booking_form.check_in_error"/></div>

                <label for="dateCheckOut">
                    <fmt:message key="label.booking_form.check_out"/> <br>
                    <input type=date id="dateCheckOut" name="dateCheckOut" required>
                <div class="warnCheck_out" id="warnCheck_out" style="visibility:hidden" ><fmt:message key="label.booking_form.check_out_error"/> </div>

                <p>
                    <label for="roomType"><fmt:message key="label.booking_form.room_type"/></label>
                </p>
                <div class="select-wrapper">
                    <select name="roomType" id="roomType">
                        <option value="STANDARD"><fmt:message key="label.room_type.STANDARD"/></option>
                        <option value="COMFORT"><fmt:message key="label.room_type.COMFORT"/></option>
                        <option value="LUXURY"><fmt:message key="label.room_type.LUX"/></option>
                        <option value="PRESIDENT"><fmt:message key="label.room_type.PRESIDENT"/></option>
                    </select>
                </div>

                <p><fmt:message key="label.booking_form.guests"/></p>
                <div class="checkBox">
                    <input type="radio" id="cap1" name="capacity" value="1" required>
                    <label for="cap1">1</label><br>
                    <input type="radio" id="cap2" name="capacity" value="2">
                    <label for="cap2">2</label><br>
                    <input type="radio" id="cap3" name="capacity" value="3">
                    <label for="cap3">3</label><br>
                    <input type="radio" id="cap4" name="capacity" value="7">
                    <label for="cap4">4</label><br>
                </div>
                <p>
                    <input type="submit" value="<fmt:message key="label.booking_form.button"/>"/>
                </p>
            </form>
            <div class="errorMessage">
            <c:if test="${requestScope.errorMessage eq 'incorrectData'}">
            <fmt:message key="label.error.incorrect_data"/>
            </c:if>
            </div>
        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>