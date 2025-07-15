<%--
  Created by IntelliJ IDEA.
  User: lakeh
  Date: 6/23/2025
  Time: 9:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link href="assets/css/tiny-slider.css" rel="stylesheet">

    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/product.css" rel="stylesheet">
    <title>Furni</title>
</head>

<body>

<!-- Start Header/Navigation -->
<%@include file="inc/header.jsp"%>
<!-- End Header/Navigation -->

<!-- Start Product Details Section -->
<section id="prodetails" class="section-p1 my-2">
    <div class="container">
        <small class="text-muted"><a href="index.jsp">Home</a> / ${product.name}</small>
        <h2 class="text-sm-start my-3">Product Details</h2>
        <div class="row align-items-start g-5">
            <!-- Product Images -->
            <div class="col-md-6" style="min-height: 650px">
                <div class="single-pro-img bg-white p-3 rounded shadow-sm">
                    <img src="${product.thumbnail}" alt="Main Product Image" id="MainImg" class="img-fluid rounded mb-3">
                    <div class="d-flex gap-2 justify-content-between small-img-group">
                        <img src="assets/images/product-1.png" alt="Thumbnail 1" class="small-img img-thumbnail rounded" style="height: 100px">
                        <img src="assets/images/product-2.png" alt="Thumbnail 2" class="small-img img-thumbnail rounded" style="height: 100px">
                        <img src="assets/images/product-3.png" alt="Thumbnail 3" class="small-img img-thumbnail rounded" style="height: 100px">
                        <img src="assets/images/sofa.png" alt="Thumbnail 4" class="small-img img-thumbnail rounded" style="height: 100px">
                    </div>
                </div>
            </div>

            <!-- Product Details -->
            <div class="col-md-6" style="min-height: 650px">
                <form action="CartServlet" style="min-height: 650px" method="post" class="single-pro-details bg-white p-4 rounded shadow-sm">
                    <input type="hidden" name="action" value="create" />
                    <input type="hidden" name="productId" value="${product.id}" />
                    <input type="hidden" name="price" value="${product.price}" />

                    <h2 class="fw-bold mt-2">${product.name}</h2>
                    <h3 class="text-primary fw-semibold mb-3"><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="VND" maxFractionDigits="0" /></h3>

                    <div class="mb-3">
                        <label for="quantity" class="form-label fw-medium">Quantity</label>
                        <input name="quantity" id="quantity" type="number" class="form-control w-50" min="1" value="1">
                    </div>

                    <button type="submit" class="btn btn-dark px-4 py-2 rounded-pill">Add to Cart</button>

                    <div class="mt-4">
                        <h5 class="fw-semibold">Product Details</h5>
                        <p class="text-muted mt-2">${product.description}</p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<!-- Related Products -->
<section id="product1" class="section-p1" style="margin-bottom: 170px; margin-top: 20px; background-color: #dce5e4">
    <div class="container">
        <h2 class="text-center my-4">Related Products</h2>
        <div class="row gy-4">
            <c:forEach items="${relatedProductList}" var="product">
                <div class="col-12 col-md-6 col-lg-3">
                    <a href="ProductServlet?productId=${product.id}" class="product-item text-decoration-none text-dark bg-white rounded shadow-sm p-3 d-block h-100">
                        <img src="${product.thumbnail}" class="img-fluid rounded product-thumbnail mb-3" alt="Product">
                        <h5 class="product-title fw-medium mb-1">${product.name}</h5>
                        <p class="product-price text-primary fw-bold mb-2">$${product.price}</p>
                        <span class="icon-cross d-inline-block">
              <img src="assets/images/cross.svg" class="img-fluid" width="20">
            </span>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- End Product Details Section -->

<!-- Start Footer Section -->
<%@include file="inc/footer.jsp"%>
<!-- End Footer Section -->


<script src="assets/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/tiny-slider.js"></script>
<script src="assets/js/custom.js"></script>
</body>

</html>
