<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:message code="substituted" var="msgSubstituted"/>
<spring:message code="inspected" var="msgInspected"/>
<spring:message code="revision.details" var="detailsMsg"/>
<spring:message code="formatDateHours" var="formatDateHours"/>
<spring:message code="money" var="msgMoney"/>
<spring:message code="return" var="msgReturn"/>
<spring:message code="edit" var="editMSG"/>

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
                <jstl:if test="${revision.garage.id eq actorId}">
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
                <blockquote><p class="flow-text"><b><spring:message code="revision.entryDate"/>: </b><fmt:formatDate value="${revision.entryDate}" pattern="${formatDateHours}"/></p>
                </blockquote>
                <blockquote><p class="flow-text"><b><spring:message code="revision.departureDate"/>: </b><fmt:formatDate value="${revision.departureDate}" pattern="${formatDateHours}"/></p>
                </blockquote>
                <blockquote><p class="flow-text"><b><spring:message code="revision.comments"/>: </b><jstl:out value="${revision.comments}" /></p>
                </blockquote>
                <jstl:if test="${revision.garage.id eq actorId}">
                <blockquote><p class="flow-text"><b><spring:message code="revision.labourPrice"/>: </b><jstl:out value="${revision.labourPrice}" /> <jstl:out value="${msgMoney}"/></p>
                </blockquote>
                <blockquote><p class="flow-text"><b><spring:message code="revision.itemsPrice"/>: </b><jstl:out value="${revision.itemsPrice}" /> <jstl:out value="${msgMoney}"/></p>
                </blockquote>
                <blockquote><p class="flow-text"><b><spring:message code="revision.finalPrice"/>: </b><jstl:out value="${revision.finalPrice}" /> <jstl:out value="${msgMoney}"/></p>
                </blockquote>
                </jstl:if>

                <%--<blockquote><p class="flow-text"><b><spring:message code="revision.nextRevision"/>:</b><fmt:formatDate value="${revision.nextRevision}" pattern="${formatDateHours}"/></p>
                </blockquote>--%>
                <blockquote><p class="flow-text"><b><spring:message code="revision.items"/>: </b>
                </blockquote>

                <div id="items-revision-display">
                    <jstl:forEach items="${items}" var="item" varStatus="counter">
                        <p id="items" class="flow-text"><jstl:out value="${item}"/>:&nbsp;
                        <p class="flow-text"><jstl:if test="${isSubstituted[counter.index] eq false}">
                            <spring:message code="inspected"/>
                        </jstl:if>
                        <jstl:if test="${isSubstituted[counter.index] eq true}">
                            <spring:message code="substituted"/>
                        </jstl:if>
                        </p>
                    </jstl:forEach>
                </div>
            </div>
    </div>
</div>
    <div class="row center">
    <sec:authorize access="hasAuthority('GARAGE')">
    <jstl:if test="${empty revision.departureDate}">
        <spring:url var="editUrl" value="/revision/garage/edit">
            <spring:param name="varId" value="${revision.id}"/>
        </spring:url>
        <jstl:if test="${revision.garage.id eq actorId}">

            <a class="btn-floating btn-large waves-effect waves-light orange" href="${editUrl}"><i class="material-icons">create</i></a>
        </jstl:if>
    </jstl:if>

        <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="${returnLink}"><i class="material-icons">undo</i></a>
    </sec:authorize>

    <sec:authorize access="hasAuthority('CONTENTMANAGER')">
        <spring:url var="deleteUrl" value="/revision/contentManager/delete">
            <spring:param name="varId" value="${revision.id}"/>
        </spring:url>

        <a class="btn-floating btn-large waves-effect waves-light orange" href="${deleteUrl}"><i class="material-icons">close</i></a>
        <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="/vehicle/contentManager/list"><i class="material-icons">undo</i></a>
    </sec:authorize>
    </div>
</div>
<jsp:include page="../misc/footer.jsp"/>

</body>
</html>