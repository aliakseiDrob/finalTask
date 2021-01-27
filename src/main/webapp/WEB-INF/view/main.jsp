<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <title>
        <fmt:message key="label.main.title"/>
    </title>
</head>
<body>

<div class="header">
    <jsp:include page="parts/header.jsp"/>
</div>

<div class="content">
    <div class="main">
    <c:choose>
    <c:when test="${sessionScope.userRole eq null}">
        <div class="welcome"><fmt:message key="label.main.welcome"/></div>
    </c:when>
    <c:otherwise>
        <div class="welcome"><fmt:message key="label.main.continue"/></div>

    <div class="continueButton">
        <c:if test="${sessionScope.userRole eq 'ADMINISTRATOR'}">
            <form method="post" action="controller?command=inProgressApplication">
                <input type="submit" value="<fmt:message key="label.main.button"/>"/>
            </form>
        </c:if>
        <c:if test="${sessionScope.userRole eq 'GUEST'}">
            <form method="post" action="controller?command=bookingPage">
                <input type="submit" value="<fmt:message key="label.main.button"/>"/>
            </form>
        </c:if>
        </c:otherwise>
        </c:choose>
    </div>
    </div>
<div>
        <img alt="main" src="images/hotel.jpg" alt="Hotel" class="mainImage" />
</div>

</div>
<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>