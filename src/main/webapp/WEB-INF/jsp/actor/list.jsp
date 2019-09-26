<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<spring:message code="actor.breach" var="broadcastMsg"/>
<spring:message code="actor.list" var="listMsg"/>
<spring:message code="actor.empty" var="actorsEmpty"/>
<html>
<header>
    <jsp:include page="../misc/header.jsp"/>
</header>

<head>
    <title><jstl:out value="${broadcastMsg}"/></title>
</head>
<body>
<div id="shown-repairs" class="container">
    <div class="col s12 m12 l12">

        <div class="hoverable z-depth-2 card amber lighten-5">
            <div class="flow-text card-content">
                <h4><jstl:out value="${listMsg}"/></h4>

                <br>
                <br>
<sec:authorize access="hasAuthority('ADMIN')">
                <jstl:if test="${empty actors}">
                    <div class="row center"><jstl:out value="${actorsEmpty}"/></div>
                </jstl:if>
                <jstl:if test="${not empty actors}">
                <c:forEach items="${actors}" var="item">

                <div id="one-repair" class="row valign-wrapper">
                    <div class="left-align col s8 m5 l8">
                            ${item.name}
                    </div>

                    <div class="hide-on-small-only center-align col m3 l8 truncate">
                    <i style="vertical-align: sub" class="hide-on-small-only center small material-icons prefix"></i><i class="center flow-text" id="repair-cause" class="center">
                        <a href="mailto:${item.email}?subject=[Motorlog] Brecha de seguridad en el sistema.&body=Lo sentimos, Motorlog ha sufrido una brecha de seguridad y su información podría estar comprometida.%0D%0A%0D%0APóngase en contacto con nosotros para más información.">${item.email}</a></i>
                    </div>


                </div>
                </c:forEach>
                </jstl:if>
</sec:authorize>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../misc/footer.jsp"/>


</body>
</html>

