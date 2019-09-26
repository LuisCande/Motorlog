<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:message code="substituted" var="msgSubstituted"/>
<spring:message code="inspected" var="msgInspected"/>

<spring:message code="actor.details" var="detailsMsg"/>

<spring:message code="actor.name" var="name"/>
<spring:message code="actor.email" var="email"/>
<spring:message code="actor.identifier" var="identifier"/>
<spring:message code="actor.phone" var="phone"/>
<spring:message code="actor.country" var="country"/>
<spring:message code="actor.city" var="city"/>
<spring:message code="actor.postalCode" var="postalCode"/>
<spring:message code="actor.address" var="address"/>
<spring:message code="welcome.export" var="exportMsg"/>
<spring:message code="welcome.deleteGarage" var="deleteMsg"/>
<spring:message code="welcome.unSub" var="unSub"/>

<spring:message code="formatDateHours" var="formatDateHours"/>
<spring:message code="money" var="msgMoney"/>
<spring:message code="return" var="msgReturn"/>
<spring:message code="edit" var="editMSG"/>

<html>

<header>
    <jsp:include page="misc/header.jsp"/>
</header>

<head>
    <title><jstl:out value="${detailsMsg}"/></title>
</head>
<body>


<!-- Modal Structure -->
<div id="modal1" class="modal">
    <div class="center modal-content">
        <h4><spring:message code="profile.delete.header"/></h4>
        <p><spring:message code="profile.delete.message"/></p>
    </div>
    <div class="modal-footer">
        <a href="/garage/delete" class="red modal-close waves-effect waves-red btn-flat">Borrar</a>
        <a href="#!" class="black-text modal-close waves-effect waves-green btn-flat">Cancelar</a>
    </div>
</div>

<div class="container">

    <div class="card z-depth-5">
        <div class="card-content">
            <h4>${detailsMsg}</h4>
            <div class="center section">
                <p class="flow-text"><b><i style="margin-top:5px" class="small material-icons">account_circle</i> </b><jstl:out value="${actor.name}"/></p>

                <p class="flow-text"><b><i style="margin-top:5px" class="small material-icons">phone</i> </b><jstl:out value="${actor.phone}"/></p>
            </div>
            <br>
        <div class="card-tabs">
            <ul class="tabs tabs-fixed-width tabs-transparent">
                <li class="tab"><a class="profile-option" href="#test4"><spring:message code="profile.information"/></a></li>
                <sec:authorize access="hasAuthority('GARAGE')">
                <li class="tab"><a class="active profile-option" href="#test5"><spring:message code="profile.options"/></a></li>
                </sec:authorize>
            </ul>
        </div>
        <div class="center card-content">
            <div id="test4">
                <div class="section">
                    <p class="flow-text"><b><jstl:out value="${identifier}"/>: </b><jstl:out value="${actor.identifier}"/></p>

                    <p class="flow-text"><b><jstl:out value="${phone}"/>: </b><jstl:out value="${actor.phone}"/></p>

                    <p class="flow-text"><b><jstl:out value="${country}"/>: </b><jstl:out value="${actor.country}"/></p>

                    <p class="flow-text"><b><jstl:out value="${city}"/>: </b><jstl:out value="${actor.city}" /></p>

                    <p class="flow-text"><b><jstl:out value="${postalCode}"/>: </b><jstl:out value="${actor.postalCode}" />

                    <p class="flow-text"><b><jstl:out value="${address}"/>: </b><jstl:out value="${actor.address}" />
                </div>
            </div>
            <sec:authorize access="hasAuthority('GARAGE')">
            <div id="test5">


                        <div class="row center">
                            <div class="col s12 m6 l6 offset-m3 offset-l3">
                        <a href="/export" class="btn btn-lg btn-primary btn red orange"><jstl:out value="${exportMsg}"/></a>
                            </div>
                        </div>
                        <jstl:if test="${isSubscribed}">
                        <div class="row center">
                        <div class="col s12 m6 l6 offset-m3 offset-l3">
                            <a href="/unsubscribe" class="btn btn-lg btn-primary btn waves-light red"><jstl:out value="${unSub}"/></a>
                        </div>
                        </div>
                        </jstl:if>
                        <div class="hide-on-small-only row right">
                            <a href="#modal1" id="scan-botton" class="btn modal-trigger right btn btn-lg btn-primary btn red darken-4"><jstl:out value="${deleteMsg}"/></a>
                        </div>
                        <div class="hide-on-med-and-up row center">
                            <div class="col s12">
                            <a href="#modal1" id="scan-botton" class="btn modal-trigger right btn btn-lg btn-primary btn red darken-4"><jstl:out value="${deleteMsg}"/></a>
                            </div>
                        </div>
                        <br>
            </div>
            </sec:authorize>

        </div>
    </div>
    </div>
</div>
        <jsp:include page="misc/footer.jsp"/>

</div>

</body>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.modal');
        var instances = M.Modal.init(elems);
    });

    // Or with jQuery

    $(document).ready(function(){
        $('.modal').modal();
    });
    var elem = document.querySelector('.tabs'); var instance = M.Tabs.init(elem, {});
</script>
</html>