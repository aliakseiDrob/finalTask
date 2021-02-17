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
        <fmt:message key="label.about_us.title"/>
    </title>
</head>
<body>

<div class="header">
    <jsp:include page="parts/header.jsp"/>
</div>
<div class="content">
    <div class="main">
            Удобное расположение в центре Минска, гармония стиля и комфорта, большой спектр услуг, способность принять и
            разместить большое количество гостей делает гостиницу «Беларусь» популярным местом временного проживания как
            для бизнесменов, так и для туристов.

    </div>
    <div>
        <img alt="main" src="images/hotel.jpg" alt="Hotel" class="mainImage"/>
    </div>

</div>
<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>