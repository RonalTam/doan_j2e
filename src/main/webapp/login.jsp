<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Untree.co">
    <link rel="shortcut icon" href="assets/images/favicon.png">

    <meta name="description" content="" />
    <meta name="keywords" content="bootstrap, bootstrap4" />

    <!-- Bootstrap CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link href="assets/css/tiny-slider.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/user-style.css" rel="stylesheet">
    <title>Furni</title>
</head>
    <body>
        <%@include file="inc/header.jsp"%>
        <div id="container-bg">
            <section id="wrapper">
                <div class="form-box login">
                    <form action="LoginServlet" method="post">
                        <h1>Login</h1>
                        <span class="error">${error}</span>
                        <c:remove var="error" scope="session"/>
                        <div class="input-box">
                            <input name="email" type="text" placeholder="email" required>
                            <i class="fa-solid fa-user"></i>
                        </div>
                        <div class="input-box">
                            <input name="password" type="password" placeholder="Password" required>
                            <i class="fa-solid fa-lock"></i>
                        </div>
                        <div class="remember-forgot">
                            <label><input type="checkbox">Remember Me</label>
                            <a href="#">Forgot Password</a>
                        </div>
                        <button type="submit" class="normal">Login</button>
                        <p style="text-align: end; margin-top: 15px">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/doan_web_j2e_war_exploded/LoginGoogleHandler&response_type=code&client_id=402368277332-1hbu7sh1jodol11pili71ec7psf6qh1j.apps.googleusercontent.com&approval_prompt=force">
                            <img src="https://developers.google.com/identity/images/btn_google_signin_dark_normal_web.png" />
                        </a>
                        </p>
                        <div class="register-link">
                            <p>Dont have an Account? <a href="RegisterServlet">Register</a></p>
                        </div>
                    </form>
                </div>
            </section>
        </div>
        <!-- Start Footer Section -->
        <%@include file="inc/footer.jsp"%>
        <!-- End Footer Section -->


        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/tiny-slider.js"></script>
        <script src="assets/js/custom.js"></script>

    </body>
