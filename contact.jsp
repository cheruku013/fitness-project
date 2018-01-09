<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/contact.css" type="text/css" media="all" />

<title>Fitness Center</title>
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
<body background = "../img/adminBg.jpg">
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
					<li><a href="contact"><img src="img/contactLogo.png" width="40"
							height="40"></a></li>
				</ul>
			</div>

		</div>
	</div>

	<!--END NAV SECTION -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

<div id="content">
       <h1> Contact me </h1>
        
        <form action=" " method="post"  autocomplete="on">
			<p> <label for="username" class="iconic user" > Name <span class="required">*</span></label> <input type="text" name="username" id="username"  required="required"   /> </p>

			<p> <label for="usermail" class="iconic mail-alt"> E-mail address <span class="required">*</span></label> <input type="email" name="usermail" id="usermail"  required="required"  /> </p>

			<p> <label for="subject" class="iconic quote-alt"> Subject </label> <input type="text" name="subject" id="subject"   /> </p>

			<p> <label for="message" class="iconic comment"> Message  <span class="required">*</span></label> <textarea   required="required" ></textarea> </p>
			
			<input type="submit" value="Send the mail !" />		
	</form>		
    </div> 
</body>
</html>