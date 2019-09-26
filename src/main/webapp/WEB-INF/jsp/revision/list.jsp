<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<spring:message code="revision.create" var="createrevisionMSG"/>
<spring:message code="display" var="displayMSG"/>
<spring:message code="edit" var="editMSG"/>
<spring:message code="revision.entryDate" var="entryDate"/>
<spring:message code="revision.departureDate" var="departureDate"/>
<spring:message code="revision.comments" var="comments"/>
<spring:message code="revision.empty" var="revisionsEmpty"/>
<spring:message code="formatDateHours" var="formatDateHours"/>
<spring:message code="return" var="msgReturn"/>
<spring:message code="revision.error.pending" var="errorMsg"/>
<spring:message code="revision.list" var="listMsg"/>
<spring:message code="revision.close" var="closeMSG"/>
<spring:message code="revision.delete" var="deleteMsg"/>

<jstl:set value="/revision/garage/listByVehicle" var="linkVehicle"/>
<jstl:set value="/revision/garage/listByGarage" var="linkGarage"/>
<jstl:set value="/revision/garage/filterByGarage" var="linkFGarage"/>
<jstl:set value="/revision/garage/filterByVehicle" var="linkFVehicle"/>

<html>
<header>
    <jsp:include page="../misc/header.jsp"/>
</header>

<head>
    <title><jstl:out value="${listMsg}"/></title>
</head>
<body>

<div id="shown-revisions" class="container">
    <div class="col s12 m12">
        <div class="hoverable z-depth-2 card">
            <div class="flow-text card-content">
                <h4><jstl:out value="${listMsg}"/></h4>

                <a id="filter-button"  onclick="showFilter()" class="btn-floating btn-large waves-effect waves-light brown"><i class="material-icons">search</i></a>
                <br>
                <br>
                <br>
                <!-- Quitar dos br si se quiere implementar
                <div class="row">
                    <div class="flow-text col s8 m5 l4">Fecha (E:Entrada/S:Salida)</div>
                </div>
                -->
                <jstl:if test="${empty revisions}">
                    <div class="row center"><jstl:out value="${revisionsEmpty}"/></div>
                </jstl:if>
                <jstl:if test="${not empty revisions}">
                <c:forEach items="${revisions}" var="item">
                <sec:authorize access="hasAuthority('CONTENTMANAGER')">
                <spring:url var="displayUrl" value="/revision/contentManager/display">
                    <spring:param name="varId" value="${item.id}" />
                </spring:url>
                <div id="one-revision" class="row valign-wrapper" onclick="location.href=('${displayUrl}${varId}')">
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('GARAGE')">
                    <jstl:if test="${requestURI == linkVehicle or requestURI == linkFVehicle}">
                    <spring:url var="displayUrl" value="/revision/garage/displayV">
                        <spring:param name="varId" value="${item.id}" />
                    </spring:url>
                    <div id="one-revision" class="row valign-wrapper" onclick="location.href=('${displayUrl}${varId}')">
                        </jstl:if>
                        <jstl:if test="${requestURI == linkGarage or requestURI == linkFGarage}">
                        <spring:url var="displayUrl" value="/revision/garage/display">
                            <spring:param name="varId" value="${item.id}" />
                        </spring:url>
                        <div id="one-revision" class="row valign-wrapper" onclick="location.href=('${displayUrl}${varId}')">
                            </jstl:if>
                            </sec:authorize>
                        <div class="truncate left-align col s8 m5 l4">
                            <i style="vertical-align: sub" class="hide-on-small-only small material-icons prefix">date_range</i><i class="flow-text" id="revision-entryDate"><jstl:choose>
                            <jstl:when test="${empty item.departureDate}">
                            <fmt:formatDate value="${item.entryDate}" pattern="${formatDateHours}"/></i>
                            </jstl:when>
                            <jstl:when test="${not empty item.departureDate}">
                                <fmt:formatDate value="${item.departureDate}" pattern="${formatDateHours}"/></i>
                            </jstl:when>
                            </jstl:choose>
                        </div>
                        <div class="hide-on-small-only center-align col m3 l4 truncate">
                            <i style="vertical-align: sub" class="hide-on-small-only center small material-icons prefix">report</i><i class="center flow-text" id="revision-comments" class="center">${item.comments}</i>
                        </div>
                        <sec:authorize access="hasAuthority('GARAGE')">
                            <spring:url var="editUrl" value="/revision/garage/edit">
                                <spring:param name="varId" value="${item.id}" />
                            </spring:url>

                            <spring:url var="closeUrl" value="/revision/garage/close">
                                <spring:param name="varId" value="${item.id}" />
                            </spring:url>

                            <div class="right-align col s4 m4 l4">
                                <jstl:if test="${empty item.departureDate}">
                                    <jstl:if test="${item.garage.id eq actorId}">
                                        <a id="revision-icon" href="${editUrl}"><i style="margin-top: 0.5%;" class="material-icons">create</i></a>
                                    </jstl:if>
                                </jstl:if>
                                <jstl:if test="${empty item.departureDate}">
                                    <jstl:if test="${item.garage.id eq actorId}">
                                        <a id="revision-icon" href="${closeUrl}"><i style="margin-top: 0.5%;" class="material-icons">archive</i></a>
                                    </jstl:if>
                                </jstl:if>
                            </div>
                        </sec:authorize>

                        <sec:authorize access="hasAuthority('CONTENTMANAGER')">
                            <spring:url var="closeUrl" value="/revision/contentManager/close">
                                <spring:param name="varId" value="${item.id}" />
                            </spring:url>

                            <div class="right-align col s4 m4 l4">
                                <jstl:if test="${empty item.departureDate}">
                                    <a href="${closeUrl}"><i style="margin-top: 0.5%;" class="material-icons">archive</i></a>
                                </jstl:if>

                                <spring:url var="deleteUrl" value="/revision/contentManager/delete">
                                    <spring:param name="varId" value="${item.id}" />
                                </spring:url>

                                <a href="${deleteUrl}"><i style="margin-top: 0.5%;" class="material-icons">close</i></a>

                            </div>
                        </sec:authorize>

                    </div>
                </c:forEach>
                </jstl:if>

            </div>
        </div>
    </div>
</div>

<div class="container">



    <sec:authorize access="hasAuthority('GARAGE')">
        <c:if test="${canCreate eq true}">
            <spring:url var="createUrl" value="/revision/garage/create">
                <spring:param name="varId" value="${vehicle.id}"/>
            </spring:url>
        </c:if>

        <div class="row center">
            <br>
            <c:if test="${canCreate eq true and vehicle != null}">
                <a href="/revision/garage/create?varId=${vehicle.id}" class="btn-floating btn-large waves-effect waves-light orange"><i class="material-icons">add</i></a>
            </c:if>
            <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="${returnLink}"><i class="material-icons">undo</i></a>
        </div>
    </sec:authorize>

    <sec:authorize access="hasAuthority('CONTENTMANAGER')">
        <div class="row center">
            <br>
            <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="/vehicle/contentManager/list"><i class="material-icons">undo</i></a>
        </div>
    </sec:authorize>
</div>

<jsp:include page="../misc/footer.jsp"/>


<div class="container filter" id="hidden-filter">
    <div class="col s10 m10">
        <div class="hoverable z-depth-5 card">
            <div class="flow-text card-content">
                <h4>Filtrar</h4>

                <sec:authorize access="hasAuthority('GARAGE')">
                <c:choose>
                    <c:when test="${vehicle != null}">
                        <form action="/revision/garage/filterByVehicle" method="GET">
                            <div class="input-field col m6 s6">
                                <input type="hidden" name="varId" value="${vehicle.id}">
                                <i class="material-icons prefix">keyboard</i>
                                <input id="keyword" type="text" name="search" value="${search}">
                                <label path="keyword" for="keyword">Palabra</label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechaEntradaDesde" name="lowerEntryDate" class="datepicker" value="${lowerEntryDate}"/>
                                <label path="fechaEntradaDesde" for="fechaEntradaDesde">Fecha de entrada desde</label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechEntradaHasta" name="upperEntryDate" class="datepicker" value="${upperEntryDate}"/>
                                <label path="fechaEntradaHasta" for="fechEntradaHasta">Fecha de entrada hasta</label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechaSalidaDesde" name="lowerDepartureDate" class="datepicker" value="${lowerDepartureDate}"/>
                                <label path="fechaSalidaDesde" for="fechaSalidaDesde">Fecha de salida desde</label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechaSalidaHasta" name="upperDepartureDate" class="datepicker" value="${upperDepartureDate}"/>
                                <label class="flow-text" path="fechaSalidaHasta" for="fechaSalidaHasta">Fecha de salida hasta</label>
                            </div>

                            <div class="row center">
                                <button onclick="hideFilter()" type="submit" class="btn-floating btn-large waves-effect waves-light orange"><i class="material-icons">search</i></button>
                                <button type="reset" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></button>
                                <a id="hide-filter"  onclick="hideFilter()" class="btn-floating btn-large waves-effect waves-light red darken-4"><i class="material-icons">clear</i></a>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/revision/garage/filterByGarage" method="GET">

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">keyboard</i>
                                <input id="keyword" type="text" name="search" value="${search}">
                                <label path="keyword" for="keyword">Palabra</label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechaEntradaDesde" name="lowerEntryDate" class="datepicker" value="${lowerEntryDate}"/>
                                <label path="fechaEntradaDesde" for="fechaEntradaDesde">Fecha de entrada desde</label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechEntradaHasta" name="upperEntryDate" class="datepicker" value="${upperEntryDate}"/>
                                <label path="fechaEntradaHasta" for="fechEntradaHasta">Fecha de entrada hasta</label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechaSalidaDesde" name="lowerDepartureDate" class="datepicker" value="${lowerDepartureDate}"/>
                                <label path="fechaSalidaDesde" for="fechaSalidaDesde"><i class="flow-text">Fecha de salida desde</i></label>
                            </div>

                            <div class="input-field col m6 s6">
                                <i class="material-icons prefix">date_range</i>
                                <input type="text" id="fechaSalidaHasta" name="upperDepartureDate" class="datepicker" value="${upperDepartureDate}"/>
                                <label path="fechaSalidaHasta" for="fechaSalidaHasta"><i class="flow-text">Fecha de salida hasta</i></label>
                            </div>
                            <div class="row center">
                                <button onclick="hideFilter()" type="submit" class="btn-floating btn-large waves-effect waves-light orange"><i class="material-icons">search</i></button>
                                <button type="reset" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></button>
                                <a id="hide-filter"  onclick="hideFilter()" class="btn-floating btn-large waves-effect waves-light red darken-4"><i class="material-icons">clear</i></a>
                            </div>

                        </form>
                    </c:otherwise>
                </c:choose>
                </sec:authorize>

                <sec:authorize access="hasAuthority('CONTENTMANAGER')">
                    <c:choose>
                        <c:when test="${vehicle != null}">
                            <form action="/revision/contentManager/filterByVehicle" method="GET">
                                <div class="input-field col m6 s6">
                                    <input type="hidden" name="varId" value="${vehicle.id}">
                                    <i class="material-icons prefix">keyboard</i>
                                    <input id="keyword" type="text" name="search" value="${search}">
                                    <label path="keyword" for="keyword">Palabra</label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechaEntradaDesde" name="lowerEntryDate" class="datepicker" value="${lowerEntryDate}"/>
                                    <label path="fechaEntradaDesde" for="fechaEntradaDesde">Fecha de entrada desde</label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechEntradaHasta" name="upperEntryDate" class="datepicker" value="${upperEntryDate}"/>
                                    <label path="fechaEntradaHasta" for="fechEntradaHasta">Fecha de entrada hasta</label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechaSalidaDesde" name="lowerDepartureDate" class="datepicker" value="${lowerDepartureDate}"/>
                                    <label path="fechaSalidaDesde" for="fechaSalidaDesde">Fecha de salida desde</label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechaSalidaHasta" name="upperDepartureDate" class="datepicker" value="${upperDepartureDate}"/>
                                    <label class="flow-text" path="fechaSalidaHasta" for="fechaSalidaHasta">Fecha de salida hasta</label>
                                </div>

                                <div class="row center">
                                    <button onclick="hideFilter()" type="submit" class="btn-floating btn-large waves-effect waves-light orange"><i class="material-icons">search</i></button>
                                    <button type="reset" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></button>
                                    <a id="hide-filter"  onclick="hideFilter()" class="btn-floating btn-large waves-effect waves-light red darken-4"><i class="material-icons">clear</i></a>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="/revision/contentManager/filterByGarage" method="GET">

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">keyboard</i>
                                    <input id="keyword" type="text" name="search" value="${search}">
                                    <label path="keyword" for="keyword">Palabra</label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechaEntradaDesde" name="lowerEntryDate" class="datepicker" value="${lowerEntryDate}"/>
                                    <label path="fechaEntradaDesde" for="fechaEntradaDesde">Fecha de entrada desde</label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechEntradaHasta" name="upperEntryDate" class="datepicker" value="${upperEntryDate}"/>
                                    <label path="fechaEntradaHasta" for="fechEntradaHasta">Fecha de entrada hasta</label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechaSalidaDesde" name="lowerDepartureDate" class="datepicker" value="${lowerDepartureDate}"/>
                                    <label path="fechaSalidaDesde" for="fechaSalidaDesde"><i class="flow-text">Fecha de salida desde</i></label>
                                </div>

                                <div class="input-field col m6 s6">
                                    <i class="material-icons prefix">date_range</i>
                                    <input type="text" id="fechaSalidaHasta" name="upperDepartureDate" class="datepicker" value="${upperDepartureDate}"/>
                                    <label path="fechaSalidaHasta" for="fechaSalidaHasta"><i class="flow-text">Fecha de salida hasta</i></label>
                                </div>
                                <div class="row center">
                                    <button onclick="hideFilter()" type="submit" class="btn-floating btn-large waves-effect waves-light orange"><i class="material-icons">search</i></button>
                                    <button type="reset" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></button>
                                    <a id="hide-filter"  onclick="hideFilter()" class="btn-floating btn-large waves-effect waves-light red darken-4"><i class="material-icons">clear</i></a>
                                </div>

                            </form>
                        </c:otherwise>
                    </c:choose>
                </sec:authorize>

            </div>
        </div>
    </div>
</div>


    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var elems = document.querySelectorAll('.datepicker');
            var options = {format: 'dd/mm/yyyy', setDefaultDate: false};
            var instances = M.Datepicker.init(elems, options);
        });
    </script>

    <script>
        function showFilter() {
            document.getElementById("hidden-filter").style.visibility = "visible";
            document.getElementById("hidden-filter").style.opacity = "1";
        }

        function hideFilter(){
            document.getElementById("hidden-filter").style.visibility = "hidden";
            document.getElementById("hidden-filter").style.opacity = "0";

        }
    </script>


</body>
</html>

