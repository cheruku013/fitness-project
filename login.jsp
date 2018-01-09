<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Fitness Center</title>

<link href="css/login.css" rel="stylesheet" />

<!--REQUIRED STYLE SHEETS-->
<link href="css/common.css" rel="stylesheet" />
<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLE CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--ANIMATED FONTAWESOME STYLE CSS -->
<link href="css/font-awesome-animation.css" rel="stylesheet" />
<!--PRETTYPHOTO MAIN STYLE -->
<link href="css/prettyPhoto.css" rel="stylesheet" />
<!--        CUSTOM STYLE CSS -->
<link href="css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
</head>


<body background = "../img/gymBg2.jpg">
<!-- NAV SECTION -->
	<div class="navbar navbar-inverse navbar-fixed-top" style="background-color:#ffe0f1">

		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="color:#660c0c"><img src="img/fitLogo.png"
					width="50" height="50" "> Fitness Center</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="welcome"><img src="img/homeLogo.png" width="40"
							height="40"></a></a></li>
					<li><a href="login"><img src="img/loginLogo1.png" width="45"
							height="45"></a></li>
					<li><a href="register"><img src="img/registerLogo1.png" width="40"
							height="40"></a></li>
					
				</ul>
			</div>

		</div>
	</div>

	<!--END NAV SECTION -->

	<!--HOME SECTION-->
	<br>
	<br>
	<br>
	
	<div class="logo"></div>
<div class="login-block">
	<h3 align="center" style="color: blue">${success}</h3>
    <h3 align="center" style="color: red">${invalid}</h3>
    <h1>Login</h1>
    <form action="/login" method="post">
    
    <input type="text" name="id" placeholder="Username" id="username" />
    <input type="password" name="pwd" placeholder="Password" id="password" />
    <label>Role:&nbsp;&nbsp;</label>
             <select name="role">
			   <option value="admin">Admin</option>
  			   <option value="customer">Customer</option>
  			   <option value="trainer">Trainer</option>
			 </select>
    <br>
     <br>

    <button type="submit">Submit</button>
    </form>
</div>
	
	
	
</body>
</html>