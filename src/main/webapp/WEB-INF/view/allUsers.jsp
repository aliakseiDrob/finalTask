<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <title>
        <fmt:message key="label.all_users.title"/>

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
        <div class="pageHeader">

        </div>
        <table>
            <caption><fmt:message key="label.left_pane.admin.all_users"/></caption>
            <tr>
                <th>№</th>
                <th><fmt:message key="label.all_users.login"/></th>
                <th><fmt:message key="label.all_users.role"/></th>
                <th><fmt:message key="label.all_users.status"/></th>
                <th><fmt:message key="label.all_users.blocked_unblocked"/></th>
                <th><fmt:message key="label.all_users.delete"/></th>
            </tr>
            <c:forEach var="elem" items="${requestScope.allUsers}" varStatus="status">
                <tr>
                    <td>${status.count+requestScope.counter}</td>
                    <td>${elem.login}</td>
                    <td><fmt:message key="label.user_role.${elem.role}"/></td>
                    <td><fmt:message key="label.user_status.${elem.status}"/></td>
                    <td>
                        <form name="blockUser" method="post" action="controller?command=blockUnBlockUser">
                            <input type="hidden" name="userId" value="${elem.id}">
                            <input type="hidden" name="page" value="${requestScope.page}">
                            <c:if test="${elem.id != sessionScope.userId}">
                                <c:if test="${elem.status eq 'BLOCKED'}">
                                    <input type="submit" value="<fmt:message key="label.all_users.unblock"/>"/>
                                </c:if>
                                <c:if test="${elem.status eq 'ACTIVE'}">
                                    <input type="submit" value="<fmt:message key="label.all_users.block"/>"/>
                                </c:if>
                            </c:if>
                        </form>
                    </td>
                    <td>
                        <form onsubmit="return confirm('<fmt:message key="label.all_users.delete_confirm"/>')" name="deleteUser" method="post" action="controller?command=deleteUser">
                            <input type="hidden" name="deletedUserId" value="${elem.id}">
                            <input type="hidden" name="page" value="${requestScope.page}">
                            <c:if test="${elem.id != sessionScope.userId and elem.status ne 'DELETED'}">
                                <input type="submit" value="<fmt:message key="label.all_users.delete"/>"/>
                            </c:if>
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