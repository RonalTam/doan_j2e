<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Định dạng số tiền với 2 chữ số thập phân, kiểu tiền tệ USD --%>
<fmt:formatNumber value="${price}" type="currency" currencySymbol="$" />
<!doctype html>
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
    <link href="assets/css/style.css" rel="stylesheet">
    <title>Furni</title>
</head>

<body>

<!-- Start Header/Navigation -->
<%@include file="inc/header.jsp"%>
<!-- End Header/Navigation -->

<!-- Start Hero Section -->
<div class="hero">
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-lg-5">
                <div class="intro-excerpt">
                    <h1>Cart</h1>
                </div>
            </div>
            <div class="col-lg-7">

            </div>
        </div>
    </div>
</div>
<!-- End Hero Section -->



<div class="container" style="margin-top: 100px; margin-bottom: 150px">
    <div class="row">
        <!-- Form bên trái -->
        <div class="col-md-6">
            <%--@declare id="checkoutform"--%><h3 class="mb-4">Giỏ hàng của bạn</h3>
            <div class="card p-3 mb-3">
                <c:forEach var="item" items="${cart}">
                    <div class="d-flex justify-content-between mb-2">
                        <div>${item.product.name} x ${item.quantity}</div>
                        <div><strong><fmt:formatNumber value="${item.price * item.quantity}" type="currency" currencySymbol="VND" maxFractionDigits="0" /></strong></div>
                    </div>
                </c:forEach>
                <hr>
                <c:set var="total" value="0" />
                <c:forEach items="${cart}" var="orderItem">
                    <c:set var="subtotal" value="${orderItem.quantity * orderItem.price}" />
                    <c:set var="total" value="${total + subtotal}" />
                </c:forEach>
                <div class="d-flex justify-content-between">
                    <strong>Tổng cộng:</strong>
                    <strong>
                        <fmt:formatNumber value="${total}" type="currency" currencySymbol="VND" maxFractionDigits="0"/>
                    </strong>
                </div>
            </div>
        </div>

        <!-- Cart bên phải -->
        <div class="col-md-6">
            <h3 class="mb-4">Thông tin giao hàng</h3>
            <form action="CheckoutServlet" method="post">
                <div class="form-group mb-3">
                    <label for="fullname">Họ và tên</label>
                    <input type="text" name="fullname" id="fullname" class="form-control" required>
                </div>

                <div class="form-group mb-3">
                    <label for="phone">Số điện thoại</label>
                    <input type="text" name="phone" id="phone" class="form-control" required>
                </div>

                <div class="form-group mb-3">
                    <%--@declare id="email"--%><label for="email">Email nhận đơn hàng</label>
                    <!-- Hiển thị email nhưng không cho sửa -->
                    <input type="email" name="email"class="form-control" value="${sessionScope.user.email}">
                </div>

                <div class="form-group mb-3">
                    <label for="address">Địa chỉ</label>
                    <input type="text" name="address" id="address" class="form-control" required>
                </div>

                <button type="submit"class="btn btn-success w-100">Xác nhận đặt hàng</button>
            </form>
        </div>
    </div>
</div>


<!-- Start Footer Section -->
<%@include file="inc/footer.jsp"%>
<!-- End Footer Section -->


<script src="assets/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/tiny-slider.js"></script>
<script src="assets/js/custom.js"></script>
</body>

</html>
