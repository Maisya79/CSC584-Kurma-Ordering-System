<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

<title>Cart</title>

<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="assets/css/owl.css">
</head>
<body>
    <!-- Header -->
    <header class="">
      <nav class="navbar navbar-expand-lg">
        <div class="container">
          <a class="navbar-brand" href="CustomerMainMenuController"><h2>my<em>Kurma</em></h2></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <a class="nav-link" href="CustomerMainMenuController">Home
                  <span class="sr-only">(current)</span>
                </a>
              </li> 
              <li class="nav-item">
                <a class="nav-link" href="CustomerProductListController">Products</a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="CustomerOrderController">Order</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="CustomerCartController">Cart</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="CustomerProfileController">Profile</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="CustomerLogoutServlet">Logout</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>

    <!-- Page Content -->
    <div class="page-heading order-heading header-text">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="text-content">
              <h4>Order Complete</h4>
              <h2>Your order has been added into the system</h2>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="best-features about-features">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="section-heading">
              <h2>Your Order</h2>
            </div>
          </div>
          <c:forEach items="${ords}" var="ord" begin="0" end="0">
          <div class="col-md-12">
            <div class="section-heading-one">
              <h3>Customer ID: <c:out value="${ord.cust_ID}"/></h3>
            </div>
          </div>
          <div class="col-md-12">
            <div class="section-heading-one">
              <h3>Order ID: <c:out value="${ord.order_ID}"/></h3>
            </div>
          </div>
          <div class="col-md-12">
            <div class="section-heading-one">
              <h3>Order Date: <c:out value="${ord.date_Order}"/></h3>
            </div>
          </div>
          </c:forEach>
          <div class="col-md-6">
            <div class="right-image">
    <table class="styled-table">
    <thead>
        <tr>
            <th>Product ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th></th>
        </tr>
    </thead>
     <c:forEach items="${ords}" var="ord">
    <tbody>
        <tr class="active-row">
           
            <td><c:out value="${ord.product_ID}"/></td>
            <td><c:out value="${ord.product_name}"/></td>
            <td><c:out value="${ord.product_Quantity}"/></td>
            <td>RM <c:out value="${ord.product_total_price}"/></td>
			
        </tr>
    </tbody>
    </c:forEach>
</table>
<div class="displayprice">
<c:forEach items="${ords}" var="ord" begin="0" end="0">
<h4>Total Order Price: RM <c:out value="${ord.total_price}"/></h4>
</c:forEach>
 </div>
 </div>
          </div>
        </div>
      </div>
    </div>

</body>
</html>