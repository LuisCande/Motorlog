<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<spring:message code="vehicle.edit" var="editMsg"/>
<spring:message code="vehicle.options" var="optionsMsg"/>
<spring:message code="vehicle.details" var="detailsMsg"/>
<spring:message code="car" var="car"/>
<spring:message code="motorbike" var="motorbike"/>
<spring:message code="formatDate" var="formatDate"/>

<html>
<head>
    <title><jstl:out value="${detailsMsg}"/></title>
</head>
<body>

<div id="header">
    <jsp:include page="../misc/header.jsp"/>
</div>


    <spring:url var="editUrl" value="/vehicle/garage/edit">
        <spring:param name="varId" value="${vehicle.id}"/>
    </spring:url>
    <spring:url var="optionsUrl" value="/vehicle/garage/options">
        <spring:param name="varId" value="${vehicle.id}"/>
    </spring:url>

<div class="container">

    <div class="col s12 m7">

        <div class="card horizontal amber lighten-5 z-depth-5">
            <div class="card-image hide-on-small-only">
                <img height="auto" src="${vehicle.picture}">
            </div>
            <div class="card-stacked">
                <div class="card-content">
                    <h4 class="red-text text-darken-4">${vehicle.model}</h4>
                    <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.type"/>:</b>
                        <jstl:if test="${tipoMoto eq true}">
                            <jstl:out value="${motorbike}" />
                        </jstl:if>

                        <jstl:if test="${tipoMoto eq false}">
                            <jstl:out value="${car}" />
                        </jstl:if>
                        </p>
                    </blockquote>
                    <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.licensePlate"/>: </b><jstl:out value="${vehicle.licensePlate}" /></p>
                    </blockquote>
                    <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.brand"/>: </b><jstl:out value="${vehicle.brand}" /></p>
                    </blockquote>
                    <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.cc"/>: </b><jstl:out value="${vehicle.cc}" /></p>
                    </blockquote>
                    <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.km"/>: </b><jstl:out value="${vehicle.km}" /></p>
                    </blockquote>
                    <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.chassisNumber"/>: </b><jstl:out value="${vehicle.chassisNumber}" /></p>
                    </blockquote>
                    <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.registrationDate"/>: </b><jstl:out value="${vehicle.registrationDate}" /></p>
                    </blockquote>
                   <%-- <blockquote>
                        <p class="flow-text"><b><spring:message code="vehicle.nextRevision"/>: </b><fmt:formatDate value="${vehicle.nextRevision}" pattern="${formatDate}"/></p>
                    </blockquote>--%>


                    <div class="row center">
                        <sec:authorize access="hasAuthority('GARAGE')">
                            <spring:url var="editUrl" value="/vehicle/garage/edit">
                                <spring:param name="varId" value="${vehicle.id}"/>
                            </spring:url>

                            <a class="btn-floating btn-large waves-effect waves-light orange" href="${editUrl}"><i class="material-icons">create</i></a>

                            <spring:url var="optionsUrl" value="/vehicle/garage/options">
                                <spring:param name="varId" value="${vehicle.id}"/>
                            </spring:url>
                            <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="${optionsUrl}"><i class="material-icons">undo</i></a>
                        </sec:authorize>

                        <sec:authorize access="hasAuthority('CONTENTMANAGER')">
                            <spring:url var="deleteUrl" value="/vehicle/contentManager/delete">
                                <spring:param name="varId" value="${vehicle.id}"/>
                            </spring:url>

                            <a class="btn-floating btn-large waves-effect waves-light orange" href="${deleteUrl}"><i class="material-icons">close</i></a>
                            <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="/vehicle/contentManager/list"><i class="material-icons">undo</i></a>

                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../misc/footer.jsp"/>

</body>
</html>

