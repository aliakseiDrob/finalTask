<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <title>
        <fmt:message key="label.add_invoice.title"/>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
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
        <caption><fmt:message key="label.add_invoice.in_process_application"/></caption>
        <tr>
            <th><fmt:message key="label.all_application.check_in"/></th>
            <th><fmt:message key="label.all_application.check_out"/></th>
            <th><fmt:message key="label.all_application.room_type"/></th>
            <th><fmt:message key="label.all_application.guests"/></th>
            <th><fmt:message key="label.all_application.status"/></th>
        </tr>
        <tr>
            <td><ctg:date-format date="${application.dateCheckIn}" locale="${sessionScope.localization}"/></td>
            <td><ctg:date-format date="${application.dateCheckOut}" locale="${sessionScope.localization}"/></td>
            <td><fmt:message key="label.room_type.${requestScope.application.type}"/></td>
            <td>${requestScope.application.capacity}</td>
            <td><fmt:message key="label.application_status.${requestScope.application.status}"/><br></td>
        </tr>
    </table>
    <table>
        <caption><fmt:message key="label.add_invoice.all_rooms"/></caption>
        <tr>
            <th>№</th>

            <th><fmt:message key="label.all_rooms.type"/></th>
            <th><fmt:message key="label.all_rooms.price"/></th>
            <th><fmt:message key="label.all_rooms.status"/></th>
            <th><fmt:message key="label.all_rooms.capacity"/></th>
            <th><fmt:message key="label.all_rooms.number"/></th>
            <th><fmt:message key="label.add_invoice.add_invoice"/></th>
        </tr>
        <c:forEach var="elem" items="${requestScope.allRooms}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td><fmt:message key="label.room_type.${elem.type}"/></td>
                <td>${elem.price}</td>
                <td><fmt:message key="label.room_status.${elem.status}"/></td>
                <td>${elem.capacity}</td>
                <td>${elem.number}</td>
                <td>
                    <form name="addInvoice" method="post" action="controller?command=addInvoice">
                        <input type="hidden" name="roomId" value="${elem.id}">
                        <input type="hidden" name="applicationId" value="${requestScope.application.id}">
                            <input type="submit" value="<fmt:message key="label.add_invoice.add_invoice"/>">

                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
</body>
</html>