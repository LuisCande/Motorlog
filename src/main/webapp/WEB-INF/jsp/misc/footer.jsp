<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="motorlog" var="footerMotorlog"/>
<spring:message code="footer.text" var="footerText"/>
<spring:message code="footer.contact" var="footerContact"/>
<spring:message code="footer.mail" var="footerMail"/>
<spring:message code="footer.phone" var="footerPhone"/>
<spring:message code="footer.privacy" var="footerPrivacy"/>
<spring:message code="footer.terms" var="footerTerms"/>
<spring:message code="footer.about" var="footerAbout"/>
<spring:message code="footer.copyright" var="footerCopyright"/>


<footer class="motorlogFooter page-footer brown darken-1">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text"><jstl:out value="${footerMotorlog}"/></h5>
                <p id="texto-footer" class="grey-text text-lighten-4"><jstl:out value="${footerText}"/></p>


            </div>

            <div class="col l6 s12">
                <h5 class="white-text"><jstl:out value="${footerContact}"/></h5>
                <ul>
                    <li><a class="white-text"><jstl:out value="${footerMail}"/></a></li>
                    <li><a class="white-text"><jstl:out value="${footerPhone}"/></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container.copyright">
            <jstl:out value="${footerCopyright}"/> <a id="copyright-text" class="orange-text text-lighten-3" href="https://motorlog-e600e.firebaseapp.com/www.landpage.co/e29d7054-44ea-11e9-884c-02cbf204825c.html"><jstl:out value="${footerMotorlog}"/></a>
            <div id="footer-gdpr" class="col l3 s12">
                <h5 class="white-text"></h5>
                <a class="orange-text text-lighten-3" href="/privacy"> <jstl:out value="${footerPrivacy}"/></a> -
                <a class="orange-text text-lighten-3" href="/terms"><jstl:out value="${footerTerms}"/></a> -
                <a class="orange-text text-lighten-3" href="/about"><jstl:out value="${footerAbout}"/></a>
            </div>
        </div>
    </div>
</footer>
