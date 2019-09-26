
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="vehicle.options" var="vehicleOptions"/>

<html>
<head>
    <title><jstl:out value="${vehicleOptions}"/></title>
</head>
<body>



<header>
    <jsp:include page="../misc/header.jsp"/>
</header>


    <div class="container">
        <div id="vehicle-options" class="row center">
            <div class="row">
                <div class="center col s12 m8 l4 offset-l4 offset-m2">
                    <div class="hoverable image-container">
                        <img class="z-depth-1 responsive-img center license-plate" src="/img/matricula.png" alt="">
                        <div id="license-plate" class="flow-text text-centered">${vehicle.licensePlate}</div>
                    </div>
                </div>
            </div>

            <br>
            <div class="row">
                <div class="col s12 m8 l4 offset-m2">
                    <div class="hoverable card" id="repairCard" onclick="location.href=('/repair/garage/listByVehicle?varId=${vehicle.id}')">
                        <div class="card-content">
                            <span class="card-title">Reparaciones</span>
                            <a id="circle-options" class="btn-floating  btn-large waves-effect waves-light red" href="/repair/garage/listByVehicle?varId=${vehicle.id}"><i id="option-icon" class="material-icons">build</i></a>
                        </div>
                    </div>
                </div>
                <div class="col s12 m8 l4 offset-m2">
                    <div class="hoverable card" id="revisionCard" onclick="location.href=('/revision/garage/listByVehicle?varId=${vehicle.id}')">
                        <div class="card-content">
                            <span class="card-title">Revisiones</span>
                            <a id="circle-options" class="btn-floating btn-large waves-effect waves-light red" href="/revision/garage/listByVehicle?varId=${vehicle.id}"><i id="option-icon" class="material-icons">update</i></a>
                        </div>
                    </div>
                </div>
                <div class="col s12 m8 l4 offset-m2">
                    <div class="hoverable card" id="vehicleCard" onclick="location.href=('/vehicle/garage/display?varId=${vehicle.id}')">
                        <div class="card-content">
                            <span class="card-title"><spring:message code="options.display"/></span>
                            <a id="circle-options" class="btn-floating btn-large waves-effect waves-light red" href="/vehicle/garage/display?varId=${vehicle.id}"><i id="option-icon" class="material-icons">airport_shuttle</i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="../misc/actions.jsp"/>
<jsp:include page="../misc/footer.jsp"/>

</body>
</html>