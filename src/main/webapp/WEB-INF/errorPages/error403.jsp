<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css" />
    <title>
        <fmt:message key="label.error_403.title"/>
    </title>
</head>
<body>

<div class="header">
    <jsp:include page="../view/parts/header.jsp"/>
</div>

<div class="content">

    <div class="main">
        <h1><fmt:message key="label.error_403.description"/></h1>
        <form action="controller?command=mainPage" method="post">
            <input type="submit" value="<fmt:message key="label.error_page.button"/>"/>
        </form>
    </div>
</div>

<div class="footer">
    <jsp:include page="../view/parts/footer.jsp"/>
</div>

</body>
</html>