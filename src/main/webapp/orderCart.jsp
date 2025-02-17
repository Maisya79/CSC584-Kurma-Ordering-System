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


<script>
function incrementValue()
{
    var value = parseInt(document.getElementById('quantity').value, 10);
    value = isNaN(value) ? 0 : value;
    value++;
    document.getElementById('quantity').value = value;
}
function decrementValue()
{
    var value = parseInt(document.getElementById('quantity').value, 10);
    value = isNaN(value) ? 0 : value;
    value--;
    document.getElementById('quantity').value = value;
}
function confirmation(){
	
}
</script>

<%double tp = (double) request.getAttribute("totalcartprice"); %>

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
              <li class="nav-item">
                <a class="nav-link" href="CustomerOrderController">Order</a>
              </li>
              <li class="nav-item active">
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
              <h4>Cart</h4>
              <h2>View Cart</h2>
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
              <h2>Your Cart</h2>
            </div>
          </div>
          <div class="col-md-6">
            <div class="right-image">
            
            <c:if test="${empty carts}">
    	<div class="col-md-12">
            <div class="section-heading-one">
              <h2>No items in cart yet!</h2>
              <br><br>
              <a class="fcc-btn" href="CustomerOrderController">Go to Order</a>
            </div>
          </div>
        </c:if>
            
    <c:if test="${carts ne null}">        
    <form action="CheckoutController" method=post>
    <table class="styled-table">
    <thead>
        <tr>
            <th>Product ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Action</th>
            <th colspan="2"></th>
        </tr>
    </thead>
    <tbody>
        
    <c:forEach items="${carts}" var="cart">
    	<input type="hidden" id="cartid" name="cartid" value="<c:out value="${cart.cart_ID}"/>"/>
    	<input type="hidden" id="productid" name="productid" value="<c:out value="${cart.product_ID}"/>"/>
    	<input type="hidden" id="quantity" name="quantity" value="<c:out value="${cart.product_Quantity}"/>"/>
        <tr class="active-row">
            <td><c:out value="${cart.product_ID}"/></td>
            <td><c:out value="${cart.product_name}"/></td>
            <td>
            <c:out value="${cart.product_Quantity}"/>
            </td>
            <td>RM <c:out value="${cart.product_total_price}"/></td>
			<td><a class="fcc-btn-important" href="CartDeleteController?cart_ID=<c:out value="${cart.cart_ID}"/>&product_ID=<c:out value="${cart.product_ID}"/>&product_Quantity=<c:out value="${cart.product_Quantity}"/>">Remove</a></td>	
        </tr>
	</c:forEach>
    </tbody>
 
</table>
<br><br>
<h4>Total Order Price: RM <%=tp %></h4>
<input type="hidden" id="ordertp" name="ordertp" value="<%=tp %>"/>
<input type="submit" value="Proceed to Checkout">

</form>
   </c:if>

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