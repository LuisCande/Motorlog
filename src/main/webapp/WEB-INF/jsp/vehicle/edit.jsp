
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="car" var="car"/>
<spring:message code="motorbike" var="motorbike"/>
<spring:message code="vehicle.type" var="vehicleType"/>
<spring:message code="vehicle.licensePlate" var="vehicleLicensePlate"/>
<spring:message code="vehicle.brand" var="vehicleBrand"/>
<spring:message code="vehicle.model" var="vehicleModel"/>
<spring:message code="vehicle.color" var="vehicleColor"/>
<spring:message code="vehicle.chassisNumber" var="vehicleChassisNumber"/>
<spring:message code="vehicle.cc" var="vehicleCc"/>
<spring:message code="vehicle.km" var="vehicleKm"/>
<spring:message code="vehicle.registrationDate" var="vehicleRegistrationDate"/>
<spring:message code="vehicle.nextRevision" var="vehicleNextRevision"/>
<spring:message code="vehicle.picture" var="vehiclePhoto"/>
<spring:message code="vehicle.details" var="vehicleDetails"/>
<spring:message code="save" var="saveMsg"/>
<spring:message code="cancel" var="cancelMsg"/>

<html>
<head>
    <title><jstl:out value="${vehicleDetails}"/></title>
</head>
<body>
<header>
    <jsp:include page="../misc/header.jsp"/>
</header>

<div class="container">
    <div class="flow-text col s12 m12">
        <div class="card amber lighten-5">
            <div class="card-content">
                <h4 class="card-title"><jstl:out value="${vehicleDetails}"/></h4>
                    <form:form action="${requestURI}" modelAttribute="vehicle">

                        <form:hidden path="id" />
                        <form:hidden path="version"/>
                        <form:hidden path="manual"/>
                        <form:hidden path="nextRevision" />

                        <jstl:if test="${not empty message}">
                            <hr>
                            <h4><p class="flow-text" style="color: #e51c23" align="center"><jstl:out value="${message}"/></p></h4>
                            <hr>
                            <br>
                        </jstl:if>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">directions_car</i>
                            <form:label path="type">
                                <jstl:out value="${vehicleType}"/>:
                            </form:label>
                            <br>
                            <br>
                            <c:choose>
                                <c:when test="${vehicle.getId() == 0}">
                                    <p>
                                        <label>
                                            <input value="car" name="type" type="radio" class="filled-in" checked="${vehicle.getType().getId() == 0}"/>
                                            <span><jstl:out value="${car}"/></span>
                                        </label>
                                    </p>
                                    <p>
                                        <label>
                                            <input value="motorbike" name="type" type="radio" class="filled-in" checked="${vehicle.getType().getId() == 1}"/>
                                            <span><jstl:out value="${motorbike}"/></span>
                                        </label>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${vehicle.getType().getId() == 0}">
                                            <p>
                                                <label>
                                                    <input value="car" name="type" type="radio" class="filled-in" checked/>
                                                    <span><jstl:out value="${car}"/></span>
                                                </label>
                                            </p>
                                            <p>
                                                <label>
                                                    <input value="motorbike" name="type" type="radio" class="filled-in" disabled/>
                                                    <span><jstl:out value="${motorbike}"/></span>
                                                </label>
                                            </p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>
                                                <label>
                                                    <input value="car" name="type" type="radio" class="filled-in" disabled/>
                                                    <span><jstl:out value="${car}"/></span>
                                                </label>
                                            </p>
                                            <p>
                                                <label>
                                                    <input value="motorbike" name="type" type="radio" class="filled-in" checked/>
                                                    <span><jstl:out value="${motorbike}"/></span>
                                                </label>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">local_play</i>
                            <form:label path="licensePlate" for="licensePlate">
                                <jstl:out value="${vehicleLicensePlate}"/>:
                            </form:label>
                            <form:input path="licensePlate" readonly="true"/>
                            <form:errors cssClass="error" path="licensePlate" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">home</i>
                            <form:label path="brand">
                                <jstl:out value="${vehicleBrand}"/>:
                            </form:label>
                            <form:input path="brand" maxlength="100"/>
                            <form:errors cssClass="error" path="brand" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">fiber_new</i>
                        <form:label path="model">
                            <jstl:out value="${vehicleModel}"/>:
                        </form:label>
                        <form:input path="model" maxlength="100"/>
                        <form:errors cssClass="error" path="model" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">palette</i>
                        <form:label path="color">
                            <jstl:out value="${vehicleColor}"/>:
                        </form:label>
                        <form:input path="color" maxlength="100"/>
                        <form:errors cssClass="error" path="color" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">fiber_pin</i>
                        <form:label path="chassisNumber">
                            <jstl:out value="${vehicleChassisNumber}"/>:
                        </form:label>
                        <form:input path="chassisNumber" maxlength="17"/>
                        <form:errors cssClass="error" path="chassisNumber" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">local_gas_station</i>
                        <form:label path="cc">
                            <jstl:out value="${vehicleCc}"/>:
                        </form:label>
                        <form:input path="cc" pattern="^\d*(\.\d{0,2})?$" maxlength="200"/>
                        <form:errors cssClass="error" path="cc" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">gesture</i>
                        <form:label path="km">
                            <jstl:out value="${vehicleKm}"/>:
                        </form:label>
                        <form:input path="km" pattern="^\d*$" maxlength="200"/>
                        <form:errors cssClass="error" path="km" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">date_range</i>
                        <form:label path="registrationDate">
                            <jstl:out value="${vehicleRegistrationDate}"/>:
                        </form:label>
                        <form:input path="registrationDate" pattern="^\d{4}$" maxlength="4"/>
                        <form:errors cssClass="error" path="registrationDate" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">insert_photo</i>
                        <form:label path="picture">
                            <jstl:out value="${vehiclePhoto}"/>:
                        </form:label>
                        <form:input path="picture" placeholder="https://www..." maxlength="250"/>
                        <form:errors cssClass="error" path="picture" />
                                        <br />
                        <div class="row center">
                            <input class="btn btn-lg btn-primary btn orange" type="submit" value="${saveMsg}" name="save">
                            <jstl:if test="${createBoton eq false}">
                                <a href="/vehicle/garage/display?varId=${vehicle.id}" class="btn btn-lg btn-primary btn red darken-4"> <jstl:out value="${cancelMsg}"/></a>
                            </jstl:if>
                            <jstl:if test="${createBoton eq true}">
                                <a href="/" class="btn btn-lg btn-primary btn red darken-4"><jstl:out value="${cancelMsg}"/></a>
                            </jstl:if>
                        </div>
                    </form:form>

            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="../misc/footer.jsp"/>

</body>
</html>