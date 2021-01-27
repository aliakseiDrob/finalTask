<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <title>
        <fmt:message key="label.all_rooms.title"/>
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
            <caption><fmt:message key="label.left_pane.admin.all_rooms"/></caption>
            <tr>
                <th>№</th>
                <th><fmt:message key="label.all_rooms.type"/></th>
                <th><fmt:message key="label.all_rooms.price"/></th>
                <th><fmt:message key="label.all_rooms.status"/></th>
                <th><fmt:message key="label.all_rooms.capacity"/></th>
                <th><fmt:message key="label.all_rooms.number"/></th>
                <th><fmt:message key="label.all_rooms.change_status"/></th>
                <th><fmt:message key="label.all_rooms.edit"/></th>
            </tr>
            <c:forEach var="elem" items="${requestScope.allRooms}" varStatus="status">
                <tr>
                    <td>${status.count+requestScope.counter}</td>
                    <td><fmt:message key="label.room_type.${elem.type}"/></td>
                    <td>${elem.price}</td>
                    <td><fmt:message key="label.room_status.${elem.status}"/></td>
                    <td>${elem.capacity}</td>
                    <td>${elem.number}<br></td>
                    <td>
                            <form onsubmit="return confirm('<fmt:message key="label.all_rooms.block_confirm"/>')" name="blockRoom" method="post" action="controller?command=blockUnblockRoom">
                                <input type="hidden" name="roomId" value="${elem.id}">
                                <input type="hidden" name="page" value="${requestScope.page}">
                                <c:if test="${elem.status eq 'AVAILABLE'}">
                                <input type="submit" value="<fmt:message key="label.all_rooms.block"/>">
                                </c:if>
                                <c:if test="${elem.status eq 'UNAVAILABLE'}">
                                    <input type="submit" value="<fmt:message key="label.all_rooms.unblock"/>">
                                </c:if>
                            </form>
                    </td>
                    <td>
                        <form name="editRoom" method="post" action="controller?command=editRoomPage">
                            <input type="hidden" name="roomId" value="${elem.id}">
                            <input type="hidden" name="page" value="${requestScope.page}">
                                <input type="submit" value="<fmt:message key="label.all_rooms.edit"/>">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="pagination">

            <c:if test="${requestScope.amountPages>3}">
                <a href="#">&laquo;</a>
            </c:if>
            <c:if test="${requestScope.amountPages > 1}">
                <a href="controller?command=${requestScope.command}&page=1">1</a>
                <a href="controller?command=${requestScope.command}&page=2">2</a>
            </c:if>
            <c:if test="${requestScope.amountPages > 2}">
                <a href="controller?command=${requestScope.command}&page=3">3</a>
            </c:if>
            <c:if test="${requestScope.amountPages > 3}">
                <a href="#">&raquo;</a>
            </c:if>

        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>