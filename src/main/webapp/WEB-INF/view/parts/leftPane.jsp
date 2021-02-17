<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/leftPane.css"/>
    <title></title>
</head>
<body>

<div class="left-pane">
    <c:if test="${userRole eq 'GUEST'}">
        <form class="userForm" id="search" method="post" action="controller?command=applicationHistory">
            <input type="submit" value="<fmt:message key="label.left_pane.guest.all_applications"/>"/>
        </form>

        <form class="userForm" id="search" method="post" action="controller?command=guestInvoices">
            <input type="submit" value="<fmt:message key="label.left_pane.guest.all_invoices"/>"/>
        </form>
        <form class="userForm" id="search" method="post" action="controller?command=bookingPage">
            <input type="submit" value="<fmt:message key="label.left_pane.guest.add_application"/>"/>
        </form>
    </c:if>


    <c:if test="${userRole eq 'ADMINISTRATOR'}">
        <form class="adminForm" method="post" action="controller?command=allRooms">
            <input type="submit"  value="<fmt:message key="label.left_pane.admin.all_rooms"/>"/>
        </form>
        <form class="adminForm" id="search" method="post" action="controller?command=allUsers">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.all_users"/>"/>
        </form>
        <form class="adminForm" id="search" method="post" action="controller?command=allApplications">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.all_applications"/>"/>
        </form>

        <form class="adminForm" id="search" method="post" action="controller?command=allInvoices">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.all_invoices"/>"/>
        </form>

        <form class="adminForm" id="leftBtn" method="post" action="controller?command=inProgressApplication">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.in_process_applications"/>"/>
        </form>
        <form class="adminForm" id="leftBtn" method="post" action="controller?command=addRoomPage">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.add_room"/>"/>
        </form>


    </c:if>
</div>
</body>
</html>

