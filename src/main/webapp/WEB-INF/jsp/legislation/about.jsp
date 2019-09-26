<html>
<head>
    <title>Sobre nosotros</title>
</head>
<body>


<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<header>
    <jsp:include page="../misc/header.jsp"/>
</header>
<div class="section no-pad-bot">
    <div class="container">

        <blockquote>
            <p id="companyName"><spring:message code="legislation.about.companyName" /></p>
        </blockquote>
        <blockquote>
            <p id="VAT"><spring:message code="legislation.about.VAT" /></p>
        </blockquote>
        <blockquote>
            <p id="email"><spring:message code="legislation.about.email" /></p>
        </blockquote>
        <blockquote>
            <p id="info"><spring:message code="legislation.about.info" /></p>
        </blockquote>

    </div>
</div>

<jsp:include page="../misc/footer.jsp"/>

</body>
</html>