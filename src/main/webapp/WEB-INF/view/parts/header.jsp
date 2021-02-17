<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>
<div class="topnav">
    <div class = "topnavhref">
    <a href="controller?command=mainPage"><fmt:message key="label.header.home"/></a>
    <a href="controller?command=aboutUsPage"><fmt:message key="label.header.about"/> </a>
    <a href="controller?command=contactsPage"><fmt:message key="label.header.contacts"/></a>
    </div>
    <div class="log-container">
                <form method="post"
                      action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                    <input type="hidden" name="locale" value="en" />
                    <input type="submit" value="<fmt:message key="label.header.language.eng"/>"/>
                </form>
                <form method="post"
                      action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                    <input type="hidden" name="locale" value="ru" />
                    <input type="submit" value="<fmt:message key="label.header.language.rus"/>"/>
                </form>
                <form method="post"
                      action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                    <input type="hidden" name="locale" value="be" />
                    <input type="submit" value="<fmt:message key="label.header.language.by"/>"/>
                </form>
        <c:choose>
        <c:when test="${sessionScope.userId==null}">
            <form method="post" action="controller?command=authorization">
                <input type="submit" value="<fmt:message key="label.header.sign_in"/>"/>
            </form>
            </c:when>
            <c:otherwise>
                <form method="post" action="controller?command=logout">
                    <input type="submit" value="<fmt:message key="label.header.sign_out"/>"/>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
