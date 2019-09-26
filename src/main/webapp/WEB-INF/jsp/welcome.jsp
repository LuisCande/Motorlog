


<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="header">
    <jsp:include page="misc/header.jsp"/>
</div>

<spring:message code="welcome.message" var="welcomeMessage"/>
<spring:message code="welcome.suffix" var="welcomeSuffix"/>
<spring:message code="welcome.bottom.text" var="welcomeText"/>
<spring:message code="welcome.login" var="welcomeLogin"/>
<spring:message code="welcome.money" var="money"/>
<spring:message code="welcome.registerNewRepRev" var="registerNewRepRev"/>
<spring:message code="welcome.easyToWork" var="easyToWork"/>
<spring:message code="welcome.fastAccess" var="fastAccess"/>
<spring:message code="welcome.focusedUX" var="focusedUX"/>
<spring:message code="welcome.detailedInfo" var="detailedInfo"/>
<spring:message code="welcome.fastDev" var="fastDev"/>
<spring:message code="welcome.motorlog" var="welcomeMotorlog"/>
<spring:message code="welcome.deleteGarage" var="deleteGarageMsg"/>
<spring:message code="welcome.unSub" var="unSub"/>

<html>
<head>
    <title><jstl:out value="${welcomeMotorlog}"/></title>
</head>
<body>


<div class="section no-pad-bot">
    <div class="container">
        <sec:authorize access="isAnonymous()">
        <br><br>

        <div id="index-banner" class="row center">
            <a href="/login" id="home-button" class="btn-large waves-effect waves-light orange"><jstl:out value="${welcomeLogin}"/></a>
           <br><br>
            <!--<a href="#" id="home-button" class="btn-large waves-effect waves-light red darken-4"><spring:message code="welcome.signin"/></a>-->
        </div>
        <br><br>
        <div class="row center">
            <h6 class="header center orange-text"><jstl:out value="${welcomeText}"/></h6>
        </div>

        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
        <sec:authorize access="hasAuthority('GARAGE')">



            <div class="hoverable z-depth-2 card amber lighten-5">
            <div class="flow-text card-content">
                <c:choose>
                    <c:when test="${!isSubscribed}">

                                <div class="hide-on-small-only row">
                                    <div class="col s4">
                                        <div class="center promo promo-example">
                                            <i id="welcome-icons" class="material-icons">photo_camera</i>
                                            <p class="promo-caption"><jstl:out value="${fastDev}"/></p>
                                            <p class="light center"><jstl:out value="${detailedInfo}"/></p>
                                        </div>
                                    </div>
                                    <div class="col s4">
                                        <div class="center promo promo-example">
                                            <i id="welcome-icons" class="material-icons">build</i>
                                            <p class="promo-caption"><jstl:out value="${focusedUX}"/></p>
                                            <p class="light center"><jstl:out value="${fastAccess}"/></p>
                                        </div>
                                    </div>
                                    <div class="col s4">
                                        <div id="welcome-icons" class="center promo promo-example">
                                            <i class="material-icons">content_paste</i>
                                            <p class="promo-caption"><jstl:out value="${easyToWork}"/></p>
                                            <p class="light center"><jstl:out value="${registerNewRepRev}"/></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="hide-on-med-and-up row">
                                    <div class="col s12">
                                        <div class="center promo promo-example">
                                            <i id="welcome-icons" class="material-icons">photo_camera</i>
                                            <p class="promo-caption"><jstl:out value="${fastDev}"/></p>
                                            <p class="light center"><jstl:out value="${detailedInfo}"/></p>
                                        </div>
                                    </div>
                                    <div class="col s12">
                                        <div class="center promo promo-example">
                                            <i id="welcome-icons" class="material-icons">build</i>
                                            <p class="promo-caption"><jstl:out value="${focusedUX}"/></p>
                                            <p class="light center"><jstl:out value="${fastAccess}"/></p>
                                        </div>
                                    </div>
                                    <div class="col s12">
                                        <div id="welcome-icons" class="center promo promo-example">
                                            <i class="material-icons">content_paste</i>
                                            <p class="promo-caption"><jstl:out value="${easyToWork}"/></p>
                                            <p class="light center"><jstl:out value="${registerNewRepRev}"/></p>
                                        </div>
                                    </div>
                                </div>
                                <br>
                        <form:form action="/subscribe" method="POST">
                            <div class="row center">
                            <script
                                    src="https://checkout.stripe.com/checkout.js" class="stripe-button"
                                    data-key="pk_test_rZGhozuOoqa51ktdQJARwg9g00TsJrlCOX"
                                    data-email="${email}"
                                    data-amount="1999"
                                    data-name="Motorlog"
                                    data-label="Suscribirse"
                                    data-description="Widget"
                                    data-image="https://stripe.com/img/documentation/checkout/marketplace.png"
                                    data-locale="es"
                                    data-currency="eur">
                            </script>
                            <button type="submit" class="orange waves-effect waves-light btn-large"><i class="material-icons left">payment</i><jstl:out value="${money}"/></button>
                            </div>
                        </form:form>
                    </c:when>


                    <c:otherwise>
                        <div class="row center">
                            <h6 class="header center orange-text"><jstl:out value="${welcomeText}"/></h6>
                        </div>
                    </c:otherwise>

                </c:choose>
            </div>
            </div>
            </sec:authorize>



        </sec:authorize>

    </div>
</div>

<jsp:include page="misc/footer.jsp"/>
</body>
</html>

