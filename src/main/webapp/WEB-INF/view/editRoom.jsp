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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <title>
        <fmt:message key="label.edit_room.title"/>
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

        <table>
            <caption><fmt:message key="label.edit_room.room"/></caption>
            <tr>
                <th><fmt:message key="label.all_rooms.type"/></th>
                <th><fmt:message key="label.all_rooms.price"/></th>
                <th><fmt:message key="label.all_rooms.status"/></th>
                <th><fmt:message key="label.all_rooms.capacity"/></th>
                <th><fmt:message key="label.all_rooms.number"/></th>
            </tr>
                <tr>
                    <td><fmt:message key="label.room_type.${requestScope.room.type}"/></td>
                    <td>${requestScope.room.price}</td>
                    <td><fmt:message key="label.room_status.${requestScope.room.status}"/></td>
                    <td>${requestScope.room.capacity}</td>
                    <td>${requestScope.room.number}<br></td>
                </tr>
        </table>

        <form method="post" action="controller?command=editRoom">
            <input type="hidden" name="roomId" value="${requestScope.room.id}">
            <input type="hidden" name="status" value="${requestScope.room.status}">
            <h2><fmt:message key="label.add_room.text"/></h2>
            <p>
                <label for="roomType"><fmt:message key="label.add_room.type"/></label>
            </p>
            <div class="select-wrapper">
                <select name="roomType" id="roomType">
                    <option value="STANDARD"><fmt:message key="label.room_type.STANDARD"/></option>
                    <option value="COMFORT"><fmt:message key="label.room_type.COMFORT"/></option>
                    <option value="LUXURY"><fmt:message key="label.room_type.LUX"/></option>
                    <option value="PRESIDENT"><fmt:message key="label.room_type.PRESIDENT"/></option>
                </select>
            </div>
            <p><fmt:message key="label.add_room.price"/></p>
            <label>
                <input type="number" name="price" required min="1" step="0.01"/>
            </label>

            <p><fmt:message key="label.add_room.number"/></p>
            <label>
                <input type="number" name="number" value="" required/>
            </label>

            <p><fmt:message key="label.add_room.guests"/></p>
            <div class="checkBox">
                <input type="radio" id="cap1" name="capacity" value="1" required>
                <label for="cap1">1</label><br>
                <input type="radio" id="cap2" name="capacity" value="2">
                <label for="cap2">2</label><br>
                <input type="radio" id="cap3" name="capacity" value="3">
                <label for="cap3">3</label><br>
                <input type="radio" id="cap4" name="capacity" value="4">
                <label for="cap4">4</label><br>
            </div>
            <input type="submit" value="<fmt:message key="label.edit_room.button"/>"/>
            <div class="errorMessage">
                <c:if test="${requestScope.errorMessage eq 'wrongParameters'}">
                    <fmt:message key="label.error.incorrect_data"/>
                </c:if>
            </div>
        </form>


    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
