<%--
 * edit.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<property name="contentType" value="text/html;charset=UTF-8" />
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="garage.name" var="nameMSG"/>
<spring:message code="garage.email" var="emailMSG"/>
<spring:message code="garage.identifier" var="identifierMSG"/>
<spring:message code="garage.phone" var="phoneMSG"/>
<spring:message code="garage.country" var="countryMSG"/>
<spring:message code="garage.city" var="cityMSG"/>
<spring:message code="garage.postalCode" var="postalCodeMSG"/>
<spring:message code="garage.address" var="addressMSG"/>
<spring:message code="garage.create.account" var="createGarageAccount"/>
<spring:message code="username" var="username"/>
<spring:message code="password" var="password"/>
<spring:message code="cancel" var="cancelMSG"/>
<spring:message code="save" var="saveMSG"/>

<%-- Stored message variables --%>

<sec:authorize access="hasAuthority('CONTENTMANAGER')">
    <html>
    <head>
        <title><jstl:out value="${createGarageAccount}"/></title>
    </head>
    <body>

    <header>
        <jsp:include page="../misc/header.jsp"/>
    </header>

    <div class="container">
        <div class="col s12 m12">
            <div class="card amber lighten-5">
                <div class="card-content">
                    <h4><jstl:out value="${createGarageAccount}"/></h4>

                    <form:form action="${requestURI}" modelAttribute="garage">

                        <form:hidden path="id" />
                        <form:hidden path="version" />
                        <form:hidden path="customer" />
                        <form:hidden path="userAccount.banned" />
                        <form:hidden path="userAccount.authorities" />

                        <jstl:if test="${not empty message}">
                            <hr>
                            <h4><p style="color: #e51c23" align="center"><jstl:out value="${message}"/></p></h4>
                            <hr>
                            <br>
                        </jstl:if>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">account_box</i>
                            <form:label path="userAccount.username">
                                <jstl:out value="${username}"></jstl:out>:
                            </form:label>
                            <form:input path="userAccount.username" type="text" class="validate" maxlength="100" required="true"/>
                            <form:errors cssClass="error" path="userAccount.username" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">security</i>
                            <form:label path="userAccount.password">
                                <jstl:out value="${password}"></jstl:out>:
                            </form:label>
                            <form:password path="userAccount.password" class="validate" maxlength="100" required="true"/>
                            <form:errors cssClass="error" path="userAccount.password" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">person_pin</i>
                            <form:label path="name">
                                <jstl:out value="${nameMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="name" type="text" class="validate" maxlength="200" required="true"/>
                            <form:errors cssClass="error" path="name" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">email</i>
                            <form:label path="email">
                                <jstl:out value="${emailMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="email" type="email" class="validate" maxlength="200" required="true"/>
                            <form:errors cssClass="error" path="email" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">perm_identity</i>
                            <form:label path="identifier">
                                <jstl:out value="${identifierMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="identifier" type="text" class="validate" maxlength="200" required="true"/>
                            <form:errors cssClass="error" path="identifier" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">phone</i>
                            <form:label path="phone">
                                <jstl:out value="${phoneMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="phone" type ="text" class="validate" placeholder="687122151" maxlength="200" required="true" pattern="^[0-9-+s()]*$"/>
                            <form:errors cssClass="error" path="phone" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">location_on</i>
                            <form:label path="country">
                                <jstl:out value="${countryMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="country" type="text" class="validate" maxlength="200" required="true"/>
                            <form:errors cssClass="error" path="country" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">location_city</i>
                            <form:label path="city">
                                <jstl:out value="${cityMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="city" type="text" class="validate" maxlength="200" required="true"/>
                            <form:errors cssClass="error" path="city" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">markunread_mailbox</i>
                            <form:label path="postalCode">
                                <jstl:out value="${postalCodeMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="postalCode" type="text" class="validate" required="true" maxlength="200" pattern="^(?:0[1-9]|[1-4]\d|5[0-2])\d{3}$"/>
                            <form:errors cssClass="error" path="postalCode" />
                            <br />
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">home</i>
                            <form:label path="address">
                                <jstl:out value="${addressMSG}"></jstl:out>:
                            </form:label>
                            <form:input path="address" class="validate" maxlength="200" required="true"/>
                            <form:errors cssClass="error" path="address"  />
                            <br />
                        </div>

                        <div class="row center">
                            <input class="btn btn-lg btn-primary btn orange" type="submit" value="Guardar" name="save">
                            <a href="/" class="btn btn-lg btn-primary btn red darken-4"><jstl:out value="${cancelMSG}"/></a>
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
</sec:authorize>