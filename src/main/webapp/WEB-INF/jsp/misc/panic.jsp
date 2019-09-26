
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
</head>
<body>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<p><spring:message code="panic.text" /> <code>${name}</code>.</p>

<h2><spring:message code="panic.message" /></h2>

<p style="font-family: 'Courier New'">
<jstl:out value="${exception}"/>
</p>

<h2><spring:message code="panic.stack.trace" /></h2>

<p style="font-family: 'Courier New'">
<jstl:out value="${stackTrace}"/>
</p>


</body>
</html>
