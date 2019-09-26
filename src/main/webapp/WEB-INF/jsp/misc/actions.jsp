<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:directive.page contentType="text/html;charset=utf-8" />
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="header.scan" var="headerScan"/>
<spring:message code="header.revision" var="headerRevision"/>
<spring:message code="header.repair" var="headerRepair"/>
<spring:message code="header.vehicle.list" var="headerVehicleList"/>
<spring:message code="header.create.garage.account" var="createGarageAccount"/>
<spring:message code="header.create.contentManager.account" var="createContentManagerAccount"/>
<spring:message code="header.dashboard" var="msgDashboard"/>
<spring:message code="header.notification" var="msgNotify"/>



<script>
    // Or with jQuery

    $(document).ready(function(){
        $('.fixed-action-btn').floatingActionButton();
    });

    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.tooltipped');
        var instances = M.Tooltip.init(elems);
    });

    // Or with jQuery

    $(document).ready(function(){
        $('.tooltipped').tooltip();
    });

    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.modal');
        var instances = M.Modal.init(elems);
    });

    // Or with jQuery

    $(document).ready(function(){
        $('.modal').modal();
    });
</script>
