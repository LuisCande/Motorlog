<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:message code="return" var="msgReturn"/>
<spring:message code="edit" var="editMSG"/>
<spring:message code="repair.details" var="detailsMsg"/>
<spring:message code="formatDateHours" var="formatDateHours"/>
<spring:message code="money" var="msgMoney"/>

<html>
<header>
    <jsp:include page="../misc/header.jsp"/>
</header>

<head>
    <title><jstl:out value="${detailsMsg}"/></title>
</head>
<body>

<div class="container">
    <div class="col s12 m12">
        <div class="card amber lighten-5">
            <div class="card-content">
                <h4><jstl:out value="${detailsMsg}"/></h4>
                <jstl:if test="${repair.garage.id eq actorId}">
                <blockquote>
                    <p class="flow-text"><b><spring:message code="fullName"/>: </b><jstl:out value="${fullName}"/></p>
                </blockquote>

                <blockquote>
                    <p class="flow-text"><b><spring:message code="phoneNumber"/>: </b><jstl:out value="${phoneNumber}"/></p>
                </blockquote>

                <blockquote>
                    <p class="flow-text"><b><spring:message code="address"/>: </b><jstl:out value="${address}"/></p>
                </blockquote>
                </jstl:if>
                <blockquote>
                    <p class="flow-text"><b><spring:message code="repair.entryDate"/>: </b><fmt:formatDate value="${repair.entryDate}" pattern="${formatDateHours}"/></p>
                </blockquote>

                <blockquote>
                    <p class="flow-text"><b><spring:message code="repair.departureDate"/>: </b><fmt:formatDate value="${repair.departureDate}" pattern="${formatDateHours}"/></p>
                </blockquote>

                <blockquote>
                    <p class="flow-text"><b><spring:message code="repair.cause"/>: </b><jstl:out value="${repair.cause}"/></p>
                </blockquote>

                <blockquote>
                    <p class="flow-text"><b><spring:message code="repair.actions"/>: </b><jstl:out value="${repair.actions}"/></p>
                </blockquote>

                <jstl:if test="${repair.garage.id eq actorId}">
                <blockquote>
                    <p class="flow-text"><b><spring:message code="repair.labourPrice"/>: </b><jstl:out value="${repair.labourPrice}"/> <jstl:out value="${msgMoney}"/></p></p>
                </blockquote>

                <blockquote>
                    <p class="flow-text"><b><spring:message code="repair.itemsPrice"/>: </b><jstl:out value="${repair.itemsPrice}"/> <jstl:out value="${msgMoney}"/></p></p>
                </blockquote>

                <blockquote>
                    <p class="flow-text"><b><spring:message code="repair.finalPrice"/>: </b><jstl:out value="${repair.finalPrice}"/> <jstl:out value="${msgMoney}"/></p></p>
                </blockquote>
                </jstl:if>
            </div>
        </div>
    </div>
                <div class="row center">
                <sec:authorize access="hasAuthority('GARAGE')">
                    <jstl:if test="${empty repair.departureDate}">
                        <spring:url var="editUrl" value="/repair/garage/edit">
                            <spring:param name="varId" value="${repair.id}"/>
                        </spring:url>
                        <jstl:if test="${repair.garage.id eq actorId}">
                            <a class="btn-floating btn-large waves-effect waves-light orange" href="${editUrl}"><i class="material-icons">create</i></a>
                        </jstl:if>
                    </jstl:if>

                    <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="${returnLink}"><i class="material-icons">undo</i></a>
                </sec:authorize>

                <sec:authorize access="hasAuthority('CONTENTMANAGER')">
                    <spring:url var="deleteUrl" value="/repair/contentManager/delete">
                        <spring:param name="varId" value="${repair.id}"/>
                    </spring:url>

                    <a class="btn-floating btn-large waves-effect waves-light orange" href="${deleteUrl}"><i class="material-icons">close</i></a>
                    <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="/vehicle/contentManager/list"><i class="material-icons">undo</i></a>
                </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</div>

    <jsp:include page="../misc/footer.jsp"/>

</body>
</html>
