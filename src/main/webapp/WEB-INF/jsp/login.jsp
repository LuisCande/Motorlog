<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jstl:set var="contextPath" value="${pageContext.request.contextPath}"/>

<spring:message code="username" var="usernameMsg"/>
<spring:message code="password" var="passwordMsg"/>
<spring:message code="login" var="loginMsg"/>
<spring:message code="access" var="accessMsg"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><jstl:out value="${accessMsg}"/></title>
</head>
<body>
<header>
    <jsp:include page="misc/header.jsp"/>
</header>


<div class="container">
    <div class="row">
    <div class="col s12 m8 l6 offset-m2 offset-l3">
        <div class="z-depth-5 card">
            <div class="flow-text  card-content">
                <h4 class="form-heading"><jstl:out value="${loginMsg}"/></h4>

                <form method="POST" action="${contextPath}/login" class="form-signin">

                    <div class="form-group ${error != null ? 'has-error' : ''}">
                        <div class="center input-field col s12 m12 l12">
                            <span>${message}</span>
                            <i class="material-icons prefix">account_box</i>
                            <label for="username"><jstl:out value="${usernameMsg}"></jstl:out>:</label>
                            <input id="username" name="username" type="text" class="validate"/>
                        </div>
                        <div class="center input-field col s12 m12 l12">
                            <i class="material-icons prefix">lock</i>
                            <label for="password"><jstl:out value="${passwordMsg}"></jstl:out>:</label>
                            <input id="password" name="password" type="password" class="validate"/>
                            <p class="error">${error}</p>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </div>

                    </div>

                <div class="row center">
                    <div class="col s12 m12 l12">
                        <button class="btn btn-lg btn-primary btn orange" type="submit"><jstl:out value="${loginMsg}"/></button>
                    </div>
                </div>
            </form>

            </div>
        </div>
    </div>
</div>

</div>
<jsp:include page="misc/footer.jsp"/>
</body>
</html>