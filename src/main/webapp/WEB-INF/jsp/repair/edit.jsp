
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<property name="contentType" value="text/html;charset=UTF-8" />
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="repair.entryDate" var="entryDateMSG"/>
<spring:message code="repair.departureDate" var="departureDateMSG"/>
<spring:message code="repair.cause" var="causeMSG"/>
<spring:message code="repair.actions" var="actionsMSG"/>
<spring:message code="repair.personalInfo" var="personalInfoMSG"/>
<spring:message code="repair.labourPrice" var="labourMSG"/>
<spring:message code="repair.itemsPrice" var="itemsPriceMSG"/>
<spring:message code="fullName" var="fullNameMsg"/>
<spring:message code="phoneNumber" var="phoneNumberMsg"/>
<spring:message code="vehicle.km" var="kmMsg"/>
<spring:message code="address" var="addressMsg"/>
<spring:message code="repair.details" var="repairDetailsMSG"/>

<html>
<head>
    <title><jstl:out value="${repairDetailsMSG}"/></title>
</head>
<body>

<header>
    <jsp:include page="../misc/header.jsp"/>
</header>
  
        <div class="container">
            <div class="flow-text col s12 m12">
                <div class="card amber lighten-5">
                    <div class="card-content">
                        <h4><jstl:out value="${repairDetailsMSG}"/></h4>

                        <form:form action="${requestURI}" modelAttribute="repair">

                        <form:hidden path="id" />
                        <form:hidden path="version" />
                        <form:hidden path="finalPrice" />
                        <form:hidden path="vehicle" />
                        <form:hidden path="garage" />
                        <form:hidden path="entryDate" />
                        <form:hidden path="departureDate" />

                        <jstl:if test="${not empty message}">
                            <hr>
                            <h4><p style="color: #e51c23" align="center"><jstl:out value="${message}"/></p></h4>
                            <hr>
                            <br>
                        </jstl:if>

                            <jstl:if test="${empty fullName and empty phoneNumber and empty address}">
                        <div class="input-field col s6">
                            <i class="material-icons prefix">person_pin</i>
                            <label for="fullName1"><jstl:out value="${fullNameMsg}"></jstl:out>:</label>
                            <input id="fullName1" name="fullName" type="text" class="validate" maxlength="225" required/>
                            <br>

                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">phone</i>
                            <label for="phoneNumber1"><jstl:out value="${phoneNumberMsg}"></jstl:out>:</label>
                            <input id="phoneNumber1" name="phoneNumber" type="text" pattern="\d*" title="Debe contener solo numeros. Por ejemplo: 628694423" class="validate" maxlength="100" required/>
                            <br>

                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">home</i>
                            <label for="address1"><jstl:out value="${addressMsg}"></jstl:out>:</label>
                            <input id="address1" name="address" type="text" class="validate" maxlength="225" required/>
                            <br>

                        </div>
                        </jstl:if>

                            <jstl:if test="${not empty fullName and not empty phoneNumber and not empty address}">
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <label for="fullName"><jstl:out value="${fullNameMsg}"></jstl:out>:</label>
                                    <input id="fullName" name="fullName" type="text" value="${fullName}" class="validate" maxlength="225" required/>
                                    <br>

                                </div>

                                <div class="input-field col s6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <label for="phoneNumber"><jstl:out value="${phoneNumberMsg}"></jstl:out>:</label>
                                    <input id="phoneNumber" name="phoneNumber" type="text" pattern="\d*" title="Debe contener solo numeros. Por ejemplo: 628694423" value="${phoneNumber}" class="validate" maxlength="100" required/>
                                    <br>

                                </div>

                                <div class="input-field col s6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <label for="address"><jstl:out value="${addressMsg}"></jstl:out>:</label>
                                    <input id="address" name="address" type="text" value="${address}" class="validate" maxlength="225" required/>
                                    <br>

                                </div>
                            </jstl:if>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">report</i>
                            <form:label path="cause">
                                <jstl:out value="${causeMSG}"></jstl:out>:
                            </form:label>
                            <form:textarea path="cause" type="text" class="validate materialize-textarea" maxlength="699" />
                            <form:errors cssClass="error" path="cause" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">format_list_numbered</i>
                            <form:label path="actions">
                                <jstl:out value="${actionsMSG}"></jstl:out>:
                            </form:label>
                            <form:textarea path="actions" type="text" class="validate materialize-textarea" maxlength="699" />
                            <form:errors cssClass="error" path="actions" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">gesture</i>
                            <label for="km"><jstl:out value="${kmMsg}"></jstl:out>:</label>
                            <input id="km" name="km" type="text" pattern="^\d*$" value="${km}" class="validate" maxlength="225" required/>
                            <br>

                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">euro_symbol</i>
                            <form:label path="labourPrice">
                                <jstl:out value="${labourMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="labourPrice" pattern="^\d*(\.\d{0,2})?$" />
                            <form:errors cssClass="error" path="labourPrice" />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">euro_symbol</i>
                            <form:label path="itemsPrice">
                                <jstl:out value="${itemsPriceMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="itemsPrice" pattern="^\d*(\.\d{0,2})?$" />
                            <form:errors cssClass="error" path="itemsPrice" />
                        </div>

                            <spring:url var="optionsUrl" value="/repair/garage/listByVehicle">
                                <spring:param name="varId" value="${vehicle.id}"/>
                            </spring:url>
                        <div class="row center">
                            <input class="btn btn-lg btn-primary btn orange" type="submit" value="Guardar" name="save">
                            <a href="${optionsUrl}" class="btn btn-lg btn-primary btn red darken-4">Cancelar</a>
                        </div>
                        </form:form>


                    </div>
                </div>
            </div>
        </div>

<jsp:include page="../misc/footer.jsp"/>

</body>
</div>
</html>
<script>
    var input = document.getElementById('phoneNumber1');
    var input2 = document.getElementById('phoneNumber');
    input2.oninvalid = function(event){
        event.target.setCustomValidity('Debe contener solo numeros. Por ejemplo: 628694423');
    }
    input.oninvalid = function(event){
        event.target.setCustomValidity('Debe contener solo numeros. Por ejemplo: 628694423');
    }

</script>