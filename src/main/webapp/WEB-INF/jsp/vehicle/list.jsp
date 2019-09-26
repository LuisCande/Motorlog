<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="vehicle.licensePlate" var="licensePlate"/>
<spring:message code="vehicle.type" var="type"/>
<spring:message code="vehicle.brand" var="brand"/>
<spring:message code="vehicle.model" var="model"/>
<spring:message code="vehicle.empty" var="vehiclesEmpty"/>
<spring:message code="formatDateHours" var="formatDateHours"/>
<spring:message code="revision.entryDate" var="entryDate"/>
<spring:message code="vehicle.delete" var="deleteMsg"/>
<spring:message code="vehicle.list" var="listMsg"/>
<spring:message code="repair.list" var="listRepairMsg"/>
<spring:message code="revision.list" var="listRevisionMsg"/>
<spring:message code="display" var="displayMSG"/>
<spring:message code="car" var="car"/>
<spring:message code="motorbike" var="motorbike"/>


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
        <div class="hoverable z-depth-2 card amber lighten-5">
            <div class="flow-text card-content">
        <h4><jstl:out value="${listMsg}"/></h4>

        <a id="filter-button"  onclick="showFilter()" class="btn-floating btn-large waves-effect waves-light red darken-4"><i class="material-icons">search</i></a>
        <br>
        <br>
        <br>
        <jstl:if test="${empty vehicles}">
            <div class="row center"><jstl:out value="${vehiclesEmpty}"/></div>
        </jstl:if>
        <jstl:if test="${not empty vehicles}">
            <c:forEach items="${vehicles}" var="item">

                <spring:url var="displayUrl" value="/vehicle/contentManager/display">
                    <spring:param name="varId" value="${item.id}" />
                </spring:url>

                <div id="one-revision" class="row valign-wrapper" onclick="location.href=('${displayUrl}${varId}')">
                    <div class="truncate left-align col s8 m5 l4">
                        <i style="vertical-align: sub" class="hide-on-small-only small material-icons prefix">local_play</i><i class="flow-text" id="revision-entryDate"><jstl:out value="${item.licensePlate}"/></i>
                    </div>
                    <div class="hide-on-small-only center-align col m3 l4 truncate">
                        <i style="vertical-align: sub" class="hide-on-small-only center small material-icons prefix">home</i><i class="center flow-text" id="revision-comments" class="center"><jstl:out value="${item.brand}"/></i>
                    </div>

                        <spring:url var="listRevisionUrl" value="/revision/contentManager/listByVehicle">
                            <spring:param name="varId" value="${item.id}" />
                        </spring:url>

                        <spring:url var="listRepairUrl" value="/repair/contentManager/listByVehicle">
                            <spring:param name="varId" value="${item.id}" />
                        </spring:url>

                        <spring:url var="deleteUrl" value="/vehicle/contentManager/delete">
                            <spring:param name="varId" value="${item.id}" />
                        </spring:url>


                        <div class="right-align col s4 m4 l4">
                            <a href="${listRevisionUrl}"><i style="margin-top: 0.5%;" class="material-icons">update</i></a>
                        </div>

                        <div class="right-align col s4 m4 l4">
                            <a href="${listRepairUrl}"><i style="margin-top: 0.5%;" class="material-icons">build</i></a>
                        </div>

                        <div class="right-align col s4 m4 l4">
                            <a href="${deleteUrl}"><i style="margin-top: 0.5%;" class="material-icons">close</i></a>
                        </div>

                </div>
            </c:forEach>
        </jstl:if>

<div class="container filter" id="hidden-filter">
    <div class="col s10 m10">
        <div class="hoverable z-depth-5 card orange lighten-5">
            <div class="flow-text card-content">
                <h4>Filtrar</h4>


                    <form action="/vehicle/contentManager/search" method="GET">
                        <div class="input-field col m6 s6">
                            <i class="material-icons prefix">keyboard</i>
                            <input id="keyword" type="text" name="search" value="${search}">
                            <label path="keyword" for="keyword">Palabra</label>
                        </div>
                        <div class="row center">
                            <button onclick="hideFilter()" type="submit" class="btn-floating btn-large waves-effect waves-light orange"><i class="material-icons">search</i></button>
                            <button type="reset" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></button>
                            <a id="hide-filter"  onclick="hideFilter()" class="btn-floating btn-large waves-effect waves-light red darken-4"><i class="material-icons">undo</i></a>
                        </div>
                    </form>
                <%--</c:otherwise>
            </c:choose>--%>
            </div>
        </div>
    </div>
</div>

            </div>
        </div>
    </div>
        <div class="row center">
        <br>
        <a class="btn-floating btn-large waves-effect waves-light red darken-4" href="/"><i class="material-icons">undo</i></a>
        </div>

    </div>

<jsp:include page="../misc/footer.jsp"/>

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

