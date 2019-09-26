<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:message code="header.dashboard" var="msgDashboard"/>


<spring:message code="return" var="msgReturn"/>
<spring:message code="administrator.query1" var="query1"/>
<spring:message code="administrator.query2" var="query2"/>
<spring:message code="administrator.query3" var="query3"/>
<spring:message code="administrator.query4" var="query4"/>
<spring:message code="administrator.query5" var="query5"/>
<spring:message code="administrator.query6" var="query6"/>

<sec:authorize access="hasAuthority('ADMIN')">

<html>

<header>
    <jsp:include page="../misc/header.jsp"/>
</header>

<head>
    <title><jstl:out value="${msgDashboard}"/></title>
</head>
<body>


    <div class="container">
    <div class="col s12 m12">
    <div class="card amber lighten-5">
    <div class="card-content">
        <h4><jstl:out value="${msgDashboard}"/></h4>
        <blockquote>
            <div style="font-weight: bold">
            <jstl:out value="${query1}" />
            </div>
            <jstl:out value="${minMaxAvgRevisionsPerGarage}" />
        </blockquote>
        <blockquote>
            <div style="font-weight: bold">
            <jstl:out value="${query2}" />
            </div>
            <jstl:out value="${minMaxAvgRepairsPerGarage}" />
        </blockquote>
        <blockquote>
            <div style="font-weight: bold">
                <jstl:out value="${query3}" />
            </div>
            <jstl:out value="${minMaxAvgRevisionsPerVehicle}" />
        </blockquote>
        <blockquote>
            <div style="font-weight: bold">
                <jstl:out value="${query4}" />
            </div>
            <jstl:out value="${minMaxAvgRepairsPerVehicle}" />
        </blockquote>
        <blockquote>
            <div style="font-weight: bold">
            <jstl:out value="${query5}" />
            </div>
            <jstl:out value="${top5GaragesInTermsOfRepairs}" />
        </blockquote>
        <blockquote>
            <div style="font-weight: bold">
            <jstl:out value="${query6}" />
            </div>
            <jstl:out value="${top5GaragesInTermsOfRevisions}" />
        </blockquote>

    </div>
    </div>
    </div>
    </div>


</sec:authorize>
<jsp:include page="../misc/footer.jsp"/>

</body>
</html>