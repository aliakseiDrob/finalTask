<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    <title>
        <fmt:message key="label.login.title"/>
    </title>
</head>
<body>

<div class="header">
    <jsp:include page="parts/header.jsp"/>
</div>

<div class="content">

    <div class="main">
        <form class="loginForm" method="post" action="controller?command=login">
            <p>
                <fmt:message key="label.login"/></p>
            <label>
                <input type="text" name="login" value="" required/>
            </label>

            <p>
                <fmt:message key="label.password"/></p>
            <label>
                <input type="password" name="password" value="" required/>
            </label>


            <input type="submit" value="<fmt:message key="label.log_in"/>"/>
            <div class="errorMessage">
                <c:if test="${requestScope.errorMessage eq 'wrongLogin'}">
                    <fmt:message key="label.error.wrong_login"/>
                </c:if>
                <c:if test="${requestScope.errorMessage eq 'userBlocked'}">
                    <fmt:message key="label.error.blocked_user"/>
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