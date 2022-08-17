<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="resources/css/flaticon.css">
    <link rel="stylesheet" href="resources/css/price_rangs.css">
    <link rel="stylesheet" href="resources/css/slicknav.css">
    <link rel="stylesheet" href="resources/css/animate.min.css">
    <link rel="stylesheet" href="resources/css/magnific-popup.css">
    <link rel="stylesheet" href="resources/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="resources/css/themify-icons.css">
    <link rel="stylesheet" href="resources/css/slick.css">
    <link rel="stylesheet" href="resources/css/nice-select.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="resources/img/favicon.ico">

    <title>Freelancers</title>
</head>
<body>
<jsp:include page="templates/customerheader.jsp"/>
<section>
    <div class="container  fa-align-center">
        <div class="card-wrapper">
            <div class="card fat">
                <div class="card-header">
                    <h4 class="float-left">List of Freelancers</h4>
                    <ul class="float-right">
                        <li>
                            <form action="/searchFreelancer" class="form-inline my-2 my-lg-0 ">
                                <input class="form-control mr-sm-2" name="searchTerm" type="search" placeholder="Search"
                                       aria-label="Search">
                                <input type="hidden" name="page" value="0"/>
                                <input type="hidden" name="size" value="${maxTraySize}"/>
                                <input class="btn btn-outline-primary my-2 my-sm-0" value="Search" type="submit">
                            </form>
                        </li>
                    </ul>
                </div>
                <div class="card card-body table-responsive">
                    <c:choose>
                        <c:when test="${freelancers.size() > 0}">
                            <c:forEach var="freelancer" items="${freelancers}">
                                <img src="${freelancer.getUser().getImage()}"
                                     alt="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                                     class="rounded-circle img-fluid" style="width: 180px; height: 120px">
                                <h5 class="my-3">${freelancer.getUser().getFullName()}</h5>
                                <p class="text-muted mb-1">${freelancer.getUser().getEmail()}</p>
                                <p class="text-muted mb-4">${freelancer.getUser().getPhoneNumber()}</p>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h5>No Freelancer Found... Search again!</h5>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="templates/pageScript.jsp"/>
<!-- JS here -->
<!-- All JS Custom Plugins Link Here here -->
<script src="resources/js/vendor/modernizr-3.5.0.min.js"></script>
<!-- Jquery, Popper, Bootstrap -->
<script src="resources/js/vendor/jquery-1.12.4.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<!-- Jquery Mobile Menu -->
<script src="resources/js/jquery.slicknav.min.js"></script>
<!-- Jquery Slick , Owl-Carousel Plugins -->
<script src="resources/js/owl.carousel.min.js"></script>
<script src="resources/js/slick.min.js"></script>
<script src="resources/js/price_rangs.js"></script>
<!-- One Page, Animated-HeadLin -->
<script src="resources/js/wow.min.js"></script>
<script src="resources/js/animated.headline.js"></script>
<script src="resources/js/jquery.magnific-popup.js"></script>
<!-- Scrollup, nice-select, sticky -->
<script src="resources/js/jquery.scrollUp.min.js"></script>
<script src="resources/js/jquery.nice-select.min.js"></script>
<script src="resources/js/jquery.sticky.js"></script>
<!-- contact js -->
<script src="resources/js/contact.js"></script>
<script src="resources/js/jquery.form.js"></script>
<script src="resources/js/jquery.validate.min.js"></script>
<script src="resources/js/mail-script.js"></script>
<script src="resources/js/jquery.ajaxchimp.min.js"></script>
<!-- Jquery Plugins, main Jquery -->
<script src="resources/js/plugins.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>
