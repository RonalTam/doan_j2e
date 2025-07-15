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
                    <h1>Shop</h1>
                </div>
            </div>
            <div class="col-lg-7">

            </div>
        </div>
    </div>
</div>
<!-- End Hero Section -->

<div class="container mt-3">
    <form action="SearchServlet" method="get" class="row g-2 mb-4 align-items-center">
        <div class="col-md-4">
            <input type="text" name="keyword" class="form-control" style="height: 35px"
                   placeholder="Từ khóa sản phẩm..." value="${param.keyword}">
        </div>

        <div class="col-md-4">
            <select name="categoryId" class="form-select" style="height: 35px">
                <option value="">Tất cả danh mục</option>
                <c:forEach items="${categoryList}" var="cate">
                    <option value="${cate.id}" ${param.categoryId == cate.id ? 'selected' : ''}>
                            ${cate.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-4">
            <button type="submit" class="btn btn-dark w-100" style="height: 35px; padding: 0">Tìm kiếm</button>
        </div>
    </form>
    <c:if test="${not empty keyword or not empty categoryId}">
        <h3 class="text-center mb-4">
            Kết quả cho từ khóa "${keyword}"
            <c:if test="${not empty categoryName}">
                trong danh mục: <strong>${categoryName}</strong>
            </c:if>
        </h3>
    </c:if>
</div>

<div class="untree_co-section product-section before-footer-section">
    <div class="container">
        <div class="row">

            <!-- Start Column 1 -->
            <c:forEach items="${productList}" var="product">
                <div class="col-12 col-md-4 col-lg-3 mb-5">
                    <a class="product-item" href="ProductServlet?productId=${product.id}">
                        <img src="${product.thumbnail}" class="img-fluid product-thumbnail">
                        <h3 class="product-title">${product.name}</h3>
                        <strong class="product-price"><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="VND" maxFractionDigits="0" /></strong>

                        <span class="icon-cross">
								<img src="assets/images/cross.svg" class="img-fluid">
                        </span>
                    </a>
                </div>
            </c:forEach>
            <!-- End Column 1 -->
        </div>
        <div class="pagination">
            <c:forEach var="i" begin="1" end="${numberPage}">
                <c:choose>
                    <c:when test="${i == page}">
                        <span class="page-item active-page">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a class="page-item"
                           href="ShopServlet?page=${i}&categoryId=${selectedCategoryId}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
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

