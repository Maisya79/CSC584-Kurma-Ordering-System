<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="css/login.css" type="text/css" media="all" />
<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/login.css" type="text/css" media="all" />

<title>Customer Login</title>

<!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    
</head>
<body>

    <!-- Header -->
    <header class="">
      <nav class="navbar navbar-expand-lg">
        <div class="container">
          <a class="navbar-brand" href="#"><h2>my <em>Kurma</em></h2></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <a class="nav-link">
                  <span class="sr-only">(current)</span>
                </a>
              </li> 
              <li class="nav-item">
                <a class="nav-link"></a>
              </li>
              <li class="nav-item">
                <a class="nav-link"></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Login</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    
   <div class="login">
        <div class="container">
            <div class="parentlogin">
                <form action="StaffLoginServlet" method="post"> 
                    <div class="title">
                        <h1>Customer Login</h1>
                    </div>
                    <label class="label" for="custemail">Email</label>
                    <input type="email" name="custemail" placeholder="Email">
                    <label class="label" for="custpassword">Password</label>
                    <input type="password" name="custpassword" placeholder="password">
                    <input type="submit" class="submit" value="Login" >
                    <div class="extra">
                    <div class="invalid">
                          <br><br><br>
                        <h4>Invalid email/password</h4>
                        </div>
                        <div class="forget">
                          <br>
                        <a href="customerRegister.jsp">Not registered yet? Create an account</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="parentdesc">
                <div class="description">
                    <h2>Staff Login</h2>
                    <p>For staffs, please click here to login</p>
                    <a href="StaffLogin.jsp">Staff Login</a>
                 </div>
              </div>
        </div>

     </div>
</body>
</html>