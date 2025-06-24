<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar">

    <div class="container">
        <a class="navbar-brand" href="index.jsp">Furni<span>.</span></a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsFurni">
            <%
                String uri = request.getRequestURI();
            %>

            <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
                <li class="nav-item <%= uri.endsWith("HomeServlet") ? "active" : "" %>">
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item <%= uri.contains("ShopServlet") ? "active" : "" %>">
                    <a class="nav-link" href="ShopServlet">Shop</a>
                </li>
                <li class="nav-item <%= uri.endsWith("about.html") ? "active" : "" %>">
                    <a class="nav-link" href="about.html">About us</a>
                </li>
                <li class="nav-item <%= uri.endsWith("services.html") ? "active" : "" %>">
                    <a class="nav-link" href="services.html">Services</a>
                </li>
                <li class="nav-item <%= uri.endsWith("blog.html") ? "active" : "" %>">
                    <a class="nav-link" href="blog.html">Blog</a>
                </li>
                <li class="nav-item <%= uri.endsWith("contact.html") ? "active" : "" %>">
                    <a class="nav-link" href="contact.html">Contact us</a>
                </li>
            </ul>

            <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                <li><a class="nav-link" href="#"><img src="assets/images/user.svg"></a></li>
                <li><a class="nav-link" href="CartServlet"><img src="assets/images/cart.svg"></a></li>
            </ul>
        </div>
    </div>

</nav>