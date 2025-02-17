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

<title>Order</title>

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
              <h4>Order</h4>
              <h2>Order your product today</h2>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="products">
      <div class="container">
        <div class="row">
          
          <div class="col-md-12">
            			<div class="section-heading">
             	 			<h2>Dates</h2>
            			</div>
          			</div>
          
          <div class="col-md-12">
            <div class="filters-content">
                <div class="row grid">
                	
                	<c:forEach items="${prods}" var="prod">
                		<c:if test = "${prod.product_category == 'Dates'}">
                    		<div class="col-lg-4 col-md-4 all des">
                      			<div class="product-item">
                        			<a href="OrderViewProductController?product_ID=<c:out value="${prod.product_ID}"/>"><img src="data:image/jpg;base64,${prod.prodimg}" /></a>
                        				<div class="down-content">
                          					<a href="OrderViewProductController?product_ID=<c:out value="${prod.product_ID}"/>"><h4><c:out value="${prod.product_name}"/></h4></a>
                          					<h6>RM <c:out value="${prod.product_price}"/></h6>
                          					<p>Product Category: <c:out value="${prod.product_category}"/></p>
                         				</div>
                      			</div>
                    		</div>
                    	</c:if>
                    </c:forEach>
                  </div>
              </div>
            </div>
                    
            <div class="col-md-12">
            	<div class="section-heading">
             	 	<h2>Raisins</h2>
            	</div>
          	</div>
          	
          	<div class="col-md-12">
            	<div class="filters-content">
                	<div class="row grid">
                	<c:forEach items="${prods}" var="prod">
                		<c:if test = "${prod.product_category == 'Raisins'}">
                    		<div class="col-lg-4 col-md-4 all des">
                      			<div class="product-item">
                        			<a href="OrderViewProductController?product_ID=<c:out value="${prod.product_ID}"/>"><img src="data:image/jpg;base64,${prod.prodimg}" /></a>
                        				<div class="down-content">
                          					<a href="OrderViewProductController?product_ID=<c:out value="${prod.product_ID}"/>"><h4><c:out value="${prod.product_name}"/></h4></a>
                          					<h6>RM <c:out value="${prod.product_price}"/></h6>
                          					<p>Product Category: <c:out value="${prod.product_category}"/></p>
                         				</div>
                      			</div>
                    		</div>
                    	</c:if>
                    </c:forEach>
                   </div>
                 </div>   
             </div>
                    
                    <div class="col-md-12">
            			<div class="section-heading">
             	 			<h2>Dried Fruit</h2>
            			</div>
          			</div>
                	<div class="col-md-12">
            	<div class="filters-content">
                	<div class="row grid">
                	<c:forEach items="${prods}" var="prod">
                		<c:if test = "${prod.product_category == 'Dried Fruit'}">
                    		<div class="col-lg-4 col-md-4 all des">
                      			<div class="product-item">
                        			<a href="OrderViewProductController?product_ID=<c:out value="${prod.product_ID}"/>"><img src="data:image/jpg;base64,${prod.prodimg}" /></a>
                        				<div class="down-content">
                          					<a href="OrderViewProductController?product_ID=<c:out value="${prod.product_ID}"/>"><h4><c:out value="${prod.product_name}"/></h4></a>
                          					<h6>RM <c:out value="${prod.product_price}"/></h6>
                          					<p>Product Category: <c:out value="${prod.product_category}"/></p>
                         				</div>
                      			</div>
                    		</div>
                    	</c:if>
                    </c:forEach>
                   </div>
                 </div>   
             </div>
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