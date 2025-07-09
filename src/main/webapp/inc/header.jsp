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
                <li class="nav-item <%= uri.endsWith("AboutServlet") ? "active" : "" %>">
                    <a class="nav-link" href="AboutServlet">About us</a>
                </li>
                <li class="nav-item <%= uri.endsWith("ServiceServlet") ? "active" : "" %>">
                    <a class="nav-link" href="ServiceServlet">Services</a>
                </li>
                <li class="nav-item <%= uri.endsWith("BlogServlet") ? "active" : "" %>">
                    <a class="nav-link" href="BlogServlet">Blog</a>
                </li>
                <li class="nav-item <%= uri.endsWith("ContactServlet") ? "active" : "" %>">
                    <a class="nav-link" href="ContactServlet">Contact us</a>
                </li>
            </ul>

            <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                <li><a class="nav-link" href="CartServlet"><img src="assets/images/cart.svg"></a></li>
                <li class="nav-item dropdown user-menu">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                        <i class="fa-solid fa-user"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <c:if test="${sessionScope.user == null}">
                            <li><a class="dropdown-item" href="LoginServlet">Login</a></li>
                            <li><a class="dropdown-item" href="RegisterServlet">Register</a></li>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <li><a class="dropdown-item" href="ProfileServlet">Xem hồ sơ</a></li>
                            <li><a class="dropdown-item" href="LogoutServlet">Logout</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

</nav>