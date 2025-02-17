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

<title>Staff: Kurma Ordering System</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="assets/css/owl.css">
</head>
<body>
<!-- ***** Preloader Start ***** -->
    <div id="preloader">
        <div class="jumper">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>  
    <!-- ***** Preloader End ***** -->

    <!-- Header -->
    <header class="">
      <nav class="navbar navbar-expand-lg">
        <div class="container">
          <a class="navbar-brand" href="StaffMainMenuController"><h2>my<em>Kurma</em></h2></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item active">
                <a class="nav-link" href="StaffMainMenuController">Home
                  <span class="sr-only">(current)</span>
                </a>
              </li> 
              <li class="nav-item">
                <a class="nav-link" href="ProductListController">Products</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="ListCustomerOrderController">Customer's Order</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="SalesListController">Sales</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="StaffListController">Staff</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="StaffLogoutServlet">Logout</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>

    <!-- Page Content -->
    <!-- Banner Starts Here -->
    <div class="banner header-text">
      <div class="owl-banner owl-carousel">
        <div class="banner-item-01">
          <div class="text-content">
            <h4>Welcome <c:out value="${stf.staff_name}"></c:out></h4>
            <h2>Kurma Ordering System</h2>
          </div>
        </div>
        <div class="banner-item-02">
          <div class="text-content">
            <h4>Salam Ramadhan</h4>
            <h2>Get your best kurma products here</h2>
          </div>
        </div>
        <div class="banner-item-03">
          <div class="text-content">
            <h4>New Arrival</h4>
            <h2>Grab these product while it last!</h2>
          </div>
        </div>
      </div>
    </div>
    <!-- Banner Ends Here -->

    <div class="latest-products">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="section-heading">
              <h2>Products</h2>
              <a href="ProductListController">View all products <i class="fa fa-angle-right"></i></a>
            </div>
          </div>
          <c:forEach items="${prods}" var="prod" begin="0" end="8" >
          <div class="col-md-4">
            <div class="product-item">
              <a href="ProductViewController?product_ID=<c:out value="${prod.product_ID}"/>"><img src="data:image/jpg;base64,${prod.prodimg}" /></a>
              <div class="down-content">
                <a href="ProductViewController?product_ID=<c:out value="${prod.product_ID}"/>"><h4><c:out value="${prod.product_name}"/></h4></a>
                <h6>RM <c:out value="${prod.product_price}"/></h6>
              </div>
            </div>
          </div>
          </c:forEach>
          
        </div>
      </div>
    </div>

    
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="inner-content">
              <p>Copyright &copy; 2020 Sixteen Clothing Co., Ltd.
            
            - Design: <a rel="nofollow noopener" href="https://templatemo.com" target="_blank">TemplateMo</a></p>
            </div>
          </div>
        </div>
      </div>
    </footer>


    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    <!-- Additional Scripts -->
    <script src="assets/js/custom.js"></script>
    <script src="assets/js/owl.js"></script>
    <script src="assets/js/slick.js"></script>
    <script src="assets/js/isotope.js"></script>
    <script src="assets/js/accordions.js"></script>


    <script language = "text/Javascript"> 
      cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
      function clearField(t){                   //declaring the array outside of the
      if(! cleared[t.id]){                      // function makes it static and global
          cleared[t.id] = 1;  // you could use true and false, but that's more typing
          t.value='';         // with more chance of typos
          t.style.color='#fff';
          }
      }
    </script>
</body>
</html>