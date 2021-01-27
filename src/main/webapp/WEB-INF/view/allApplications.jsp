<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="customtags" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <title>
        <fmt:message key="label.all_application.title"/>
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
            <c:if test="${sessionScope.userRole eq 'ADMINISTRATOR'}">
                <caption> <fmt:message key="label.left_pane.admin.all_applications"/></caption>
            </c:if>
            <c:if test="${sessionScope.userRole eq 'GUEST'}">
              <caption> <fmt:message key="label.left_pane.guest.all_applications"/></caption>
            </c:if>
            <tr>
                <th>№</th>
                <th><fmt:message key="label.all_application.check_in"/></th>
                <th><fmt:message key="label.all_application.check_out"/></th>
                <th><fmt:message key="label.all_application.room_type"/></th>
                <th><fmt:message key="label.all_application.guests"/></th>
                <th><fmt:message key="label.all_application.status"/></th>
                <c:if test="${sessionScope.userRole eq 'ADMINISTRATOR'}">
                    <th><fmt:message key="label.all_application.add_invoice"/></th>
                </c:if>
            </tr>
            <c:forEach var="elem" items="${requestScope.allApplications}" varStatus="status">
                <tr>
                    <td>${status.count+requestScope.counter}</td>
                    <td><ctg:date-format date="${elem.dateCheckIn}" locale="${sessionScope.locale}"/></td>
                    <td><ctg:date-format date="${elem.dateCheckOut}" locale="${sessionScope.locale}"/></td>
                    <td><fmt:message key="label.room_type.${elem.type}"/></td>
                    <td>${elem.capacity}</td>
                    <td><fmt:message key="label.application_status.${elem.status}"/><br></td>
                    <c:if test="${sessionScope.userRole eq 'ADMINISTRATOR'}">
                        <td>
                            <form name="addInvoice" method="post" action="controller?command=availableRooms">
                                <input type="hidden" name="applicationId" value="${elem.id}">
                                <c:if test="${elem.status eq 'IN_PROGRESS'}">
                                    <input type="submit" value="<fmt:message key="label.button.add_invoice"/>">
                                </c:if>
                            </form>

                        </td>
                    </c:if>
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