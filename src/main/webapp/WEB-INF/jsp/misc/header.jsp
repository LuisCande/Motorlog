<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:directive.page contentType="text/html;charset=utf-8" />
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oleo+Script" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/materialize.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<link href="https://fonts.googleapis.com/css?family=Comfortaa|Satisfy" rel="stylesheet">

<spring:message code="motorlog" var="Motorlog"/>
<spring:message code="header.scan" var="headerScan"/>
<spring:message code="header.revision" var="headerRevision"/>
<spring:message code="header.repair" var="headerRepair"/>
<spring:message code="header.vehicle.list" var="headerVehicleList"/>
<spring:message code="header.create.garage.account" var="createGarageAccount"/>
<spring:message code="header.create.contentManager.account" var="createContentManagerAccount"/>
<spring:message code="header.dashboard" var="msgDashboard"/>
<spring:message code="header.notification" var="msgNotify"/>



<nav class="banner  orange accent-3" role="navigation" style="margin-bottom:2%;    border-radius: 0px !important;">
    <div class="nav-wrapper container center"><a id="center logo-container top-100" href="/" class="center brand"><jstl:out value="${Motorlog}"/></a>

        <ul id="nav-mobile" class="sidenav">
            <li><a href="#"><jstl:out value="${Motorlog}"/></a></li>
        </ul>
    </div>
</nav>

<div class="row">
    <sec:authorize access="isAuthenticated()">
    <div class="col s12 m4 l4 offset-m8 offset-l8" style="margin-top: 5px">
            <a style="font-size: 17px; opacity: 0.6;" href="#" data-target='dropdown1' class="hoverable btn-small dropdown-trigger right red darken-4 white-text"><sec:authentication
                    property="principal.username" /><i style="font-size: 25px" class="left small material-icons">account_circle</i></a>

        <ul id='dropdown1' class='dropdown-content'>
            <li><a class="inverse red darken-4 white-text" href="/profile">Ver perfil<i style="font-size: 25px" class="left small material-icons">account_circle</i></a></li>
            <li><a class="inverse red darken-4 white-text" href="/logout">Cerrar sesion<i style="font-size: 25px" class="left small material-icons">power_settings_new</i></a></li>
        </ul>

        </sec:authorize>
    </div>
    <div class="row center">

        <sec:authorize access="hasAuthority('CONTENTMANAGER')">
            <a id="vehicles" class="btn waves-effect waves-light red darken-4"href="/vehicle/contentManager/list"><jstl:out value="${headerVehicleList}"/><i class="material-icons right">directions_car</i></a>
            <a id="garages" class="btn waves-effect waves-light red darken-4"href="/contentManager/createGarage"><jstl:out value="${createGarageAccount}"/><i class="material-icons right">add</i></a>
            <a id="Cms" class="btn waves-effect waves-light red darken-4"href="/contentManager/createContentManager"><jstl:out value="${createContentManagerAccount}"/><i class="material-icons right">add</i></a>
        </sec:authorize>

        <sec:authorize access="hasAuthority('ADMIN')">
            <a id="vehicles" class="btn waves-effect waves-light red darken-4" href="/administrator/dashboard"><jstl:out value="${msgDashboard}"/><i class="material-icons right">dashboard</i></a>
            <a id="vehicles" class="btn waves-effect waves-light red darken-4" href="/actor/list"><jstl:out value="${msgNotify}"/><i class="material-icons right">mail</i></a>
        </sec:authorize>
    </div>

        <div class="modal-overlay" id="overlay" style="z-index: 1002; display: block; opacity: 0.3;"></div>

        <div class="progress red" id="preloader-top">
            <div class="indeterminate red lighten-3"></div>
        </div>


        <sec:authorize access="isAuthenticated()">
        <div class="fixed-action-btn direction-top">
            <a class="btn-floating btn-large red modal-trigger" href="#modal1">
                <i class="large material-icons">list</i>
            </a>

            <div id="modal1" class="modal bottom-sheet">
                <div class="modal-content">
                    <h4>Mis acciones</h4>
                    <sec:authorize access="hasAuthority('GARAGE')">
                        <c:choose>
                            <c:when test="${isSubscribed}">
                                <div class="row">
                                    <div class="col s12 m4 l4 offset-m4 offset-l4">
                                        <a href="/vehicle/garage/scan" class="btn red">${headerScan}</a>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s12 m4 l4 offset-m4 offset-l4">
                                        <a href="/revision/garage/listByGarage" class="btn orange">${headerRevision}</a>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s12 m4 l4 offset-m4 offset-l4">
                                        <a href="/repair/garage/listByGarage" class="btn brown">${headerRepair}</a>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="col s12 m4 l4 offset-m4 offset-l4">
                                        <a class="disabled btn orange">${headerScan}</a>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </sec:authorize>
                </div>
            </div>
        </div>
        </sec:authorize>

        <script>
            $(window).load(function() {
                $('.progress').delay(100).fadeOut('slow');

                $('.indeterminate')
                    .delay(100)
                    .fadeOut();
            });
            $(window).load(function() {
                $( "div.modal-overlay" ).delay( 100 ).fadeOut( 300 );
            });

            $('.dropdown-trigger').dropdown();

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