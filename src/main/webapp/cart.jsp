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



<div class="untree_co-section before-footer-section">
    <div class="container">
        <div class="row mb-5">
            <form class="col-md-12" method="post">
                <div class="site-blocks-table">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="product-thumbnail">Image</th>
                            <th class="product-name">Product</th>
                            <th class="product-price">Price</th>
                            <th class="product-quantity">Quantity</th>
                            <th class="product-total">Total</th>
                            <th class="product-remove">Remove</th>
                        </tr>
                        </thead>
                        <tbody style="background-color: beige">
                        <c:forEach items="${cart}" var="orderItem">
                            <tr>
                                <td><img src="${orderItem.product.thumbnail}" class="img-thumbnail" alt=""></td>
                                <td>${orderItem.product.name}</td>
                                <td><fmt:formatNumber value="${orderItem.price}" type="currency" currencySymbol="$" maxFractionDigits="0" /></td>                                <td>
                                <td>
                                    <form action="CartServlet" method="post">
                                        <input type="hidden" name="action" value="update"/>
                                        <input type="hidden" name="productId" value="${orderItem.productId}"/>
                                        <input onchange="this.form.submit()" name="quantity" type="number" value="${orderItem.quantity}" min="1">

                                    </form>
                                </td>
                                <td><fmt:formatNumber value="${orderItem.price * orderItem.quantity}" type="currency" currencySymbol="$" maxFractionDigits="0" /></td>                                <td>
                                    <form action="CartServlet" method="post">
                                        <input type="hidden" name="action" value="delete"/>
                                        <input type="hidden" name="productId" value="${orderItem.productId}"/>
                                        <button type="submit" class="btn btn-black btn-sm">X</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="row mb-5">
                    <div class="col-md-6 mb-3 mb-md-0">
                        <button class="btn btn-black btn-sm btn-block">Update Cart</button>
                    </div>
                    <div class="col-md-6">
                        <a href="index.jsp"><button class="btn btn-outline-black btn-sm btn-block">Continue Shopping</button></a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <label class="text-black h4" for="coupon">Coupon</label>
                        <p>Enter your coupon code if you have one.</p>
                    </div>
                    <div class="col-md-8 mb-3 mb-md-0">
                        <input type="text" class="form-control py-3" id="coupon" placeholder="Coupon Code">
                    </div>
                    <div class="col-md-4">
                        <button class="btn btn-black">Apply Coupon</button>
                    </div>
                </div>
            </div>
            <div class="col-md-6 pl-5">
                <div class="row justify-content-end">
                    <div class="col-md-7">
                        <div class="row">
                            <div class="col-md-12 text-right border-bottom mb-5">
                                <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
                            </div>
                        </div>
                        <c:set var="total" value="0" />
                        <c:forEach items="${cart}" var="orderItem">
                            <c:set var="subtotal" value="${orderItem.quantity * orderItem.price}" />
                            <c:set var="total" value="${total + subtotal}" />
                        </c:forEach>

                        <!-- ✅ Hiển thị chỉ MỘT lần sau khi tính xong -->
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <span class="text-black">Subtotal</span>
                            </div>
                            <div class="col-md-6 text-right">
                                <strong class="text-black">
                                    <fmt:formatNumber value="${total}" type="currency" currencySymbol="VND" maxFractionDigits="0"/>
                                </strong>
                            </div>
                        </div>
                        <div class="row mb-5">
                            <div class="col-md-6">
                                <span class="text-black">Total</span>
                            </div>
                            <div class="col-md-6 text-right">
                                <strong class="text-black">
                                    <fmt:formatNumber value="${total}" type="currency" currencySymbol="VND" maxFractionDigits="0"/>
                                </strong>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <button class="btn btn-black btn-lg py-3 btn-block" onclick="window.location='CheckoutServlet'">Proceed To Checkout</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
