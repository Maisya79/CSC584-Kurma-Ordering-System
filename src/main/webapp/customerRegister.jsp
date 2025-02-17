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

<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/SignUp.css" type="text/css" media="all" />

<title>Registration</title>

<!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    
</head>
<body>

<script>
var check = function() {
	  if (document.getElementById('custpassword').value == document.getElementById('confirm').value) {
	    document.getElementById('message').style.color = 'green';
	    document.getElementById('message').innerHTML = 'matching';
	  } else {
	    document.getElementById('message').style.color = 'red';
	    document.getElementById('message').innerHTML = 'not matching';
	  }
	}
</script>

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
                <a class="nav-link" href="customerLogin.jsp">Login</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    
    <form class="form" action="CustomerRegisterServlet" method=post>
      <h1>Register</h1>
      <div class="form-group">
        <input type="text" name="custname" placeholder="Full name" id="custname" required>
        <label for="custname">Name</label>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
          Error encountered!
        </div>
      </div>
       <div class="form-group">
       <label name="custaddress"><span>Address</span></label>
        <textarea name= "custaddress" placeholder="Address" id="custaddress" required></textarea>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
          Error encountered!
        </div>
      </div>
      <div class="form-group">
        <input type="text" name= "custdob" placeholder="yyyy-MM-dd" id="custdob" required>
        <label for="custdob">Date of Birth</label>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
          Error encountered!
        </div>
      </div>
      <div class="form-group">
        <label for="custgender">Gender</label>
        <select name="custgender" required>
        <option>Male</option>
        <option>Female</option>
        </select>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
          Error encountered!
        </div>
      </div>
      <div class="form-group">
        <input type="email" name="custemail" placeholder="Email" id="custemail" required>
        <label for="custemail">Email</label>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
        </div>
      </div>
      <div class="form-group">
        <input type="text" placeholder="Phone no" id="custphoneno" name="custphoneno" required>
        <label for="custphoneno">Phone Number</label>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
        </div>
      </div>
      <div class="form-group">
        <input type="password" placeholder="Password" id="custpassword" name="custpassword"  onkeyup='check();' required>
        <label for="custpassword">Password</label>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
        </div>
      </div>
      <div class="form-group">
        <input type="password" placeholder="Confirm password" id="confirm" name="confirm"  onkeyup='check();' required>
        <label for="confirm">Confirm</label>
        <div class="error">
          <i class="fas fa-exclamation-circle"></i>
        </div>
        <span id='message'></span>
      </div>
      <input type="submit" value="Register">
    </form>

</body>
</html>