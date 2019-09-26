
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="vehicle.licensePlate" var="vehicleLicensePlate"/>
<spring:message code="scan" var="scan"/>
<spring:message code="cancel" var="cancelMsg"/>
<spring:message code="vehicle.scan" var="vehicleScan"/>
<spring:message code="vehicle.licensePlate.error" var="errorMsg"/>
<spring:message code="camera" var="camera"/>

<html>
<head>
    <title><jstl:out value="${vehicleScan}"/></title>
</head>
<body>

<header>
    <jsp:include page="../misc/header.jsp"/>
</header>


        <div class="container">
            <div class="row">
            <div class="col s12 m8 l6 offset-m2 offset-l3">
                <div class="z-depth-5 card">
                    <div class="flow-text card-content center">
                        <h4><jstl:out value="${vehicleLicensePlate}"/></h4>
                        <form:form action="/vehicle/garage/scan">

                            <jstl:if test="${empty licensePlate}">
                                <input class="center" name="licensePlate" placeholder="C1111AAA | 1111AAA | A1111AA" maxlength="8"/>
                                <br />
                            </jstl:if>

                            <jstl:if test="${not empty licensePlate}">
                                <input id="license-plate" name="licensePlate" value="${licensePlate}" maxlength="8"/>
                                <br />
                            </jstl:if>

                            <jstl:if test="${not empty message}">
                                <p class="error"> <jstl:out value="${errorMsg}"/></p>
                            </jstl:if>

                            <br>
                        <div class="row center">
                            <div class="col s12 m12 l12">
                                <input class="btn btn-lg btn-primary btn orange" type="submit" value="${scan}" name="scan">
                            </div>
                        </div>

                        <div class="row center">
                            <div class="col s12 m12 l12">
                                <a href="/" class="btn btn-lg btn-primary btn red darken-4"><jstl:out value="${cancelMsg}"/></a>
                            </div>

                        </div>
                        </form:form>

                        <form:form action="/vehicle/garage/uploadFile" method="POST" enctype="multipart/form-data"><div class="row center">
                        <div class="file-field input-field hide-on-small-only">
                            <div class="btn-floating waves-effect waves-light brown darken-1">
                                <i class="material-icons center">camera_alt</i>
                                <input type="file" accept="image/*;capture=camera" name="file" onchange="this.form.submit()">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate hide" type="text">
                            </div>
                        </div>
                        <div class="col s6 offset-s3 file-field input-field hide-on-med-and-up">
                            <div id="upload-phone" class="btn-floating waves-effect waves-light brown darken-1 flow-text"><i class="material-icons center">camera_alt</i>
                                <span><jstl:out value="${camera}"/></span>
                                <input type="file" accept="image/*;capture=camera" name="file" onchange="this.form.submit()">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate hide" type="text">
                            </div>
                        </div>
                        </form:form>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="../misc/footer.jsp"/>
</body>
</html>