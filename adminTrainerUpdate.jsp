<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Fitness Center</title>
<link href="css/register.css" rel="stylesheet" />
<script src="../js/index.js"></script>
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
</head>



<body background = "../img/adminBg3.png">
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
							height="40"></a></li>
					<li><a href="contact"><img src="img/contactLogo.png" width="40"
							height="40"></a></li>
					<li><a href="logout">${loggedInName}<img src="img/logOutLogo.png" width="40"
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
     
     <div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Trainer</a></li>
        
      </ul>
      
      <div >
        <div id="signup">
          
          <form action="/adminUpdateTrainer" method="post">
          
          
            <div class="field-wrap">
            <input type="text" name="id" value="${trID}" readonly>
            <br>
          	<label>Password:</label>          
            <input type="password" placeholder="Password" pattern="^[^-\s][\w\s-]+$" data-type="password" name="pwd" value="${trPwd}" required/>
            <br>
            <label>User Name:</label>
             <input type="text"  placeholder="UserName" name="name" pattern="^[^-\s][\w\s-]+$" value="${trName}" required/>
             <br>
             <label>Age:</label>
             <input type="number" min="10" max="100" value="${trAge}" class="input"  name="age">
             <br>
            
             <label>Gender:&nbsp;&nbsp;</label>
             <select name="gender"  id="gender">
			   <option value="male">Male</option>
  			   <option value="female">Female</option>
			 </select>			
             <br>
             
             <label>Email:</label>             
            <input type="email" name="email" placeholder="example@$#..com" value="${trEmailId}" required>
            <br>
            <label>Phone:</label>
            <input type="tel" pattern="\d{3}[\-]\d{3}[\-]\d{4}" placeholder="123-456-7890" name="phno" value="${trPhone}" required>
            
                       
          </div>
          <script type="text/javascript">
          document.getElementById("gender").value = "${trGender}";
          </script>
          
          <button type="submit" class="button button-block"/>Update Customer</button>
          
          </form>

        </div>
        
        </div>
       
          
</div> <!-- /form -->
   <script src="../js/index2.js"></script>
 <script src="../js/index.js"></script>
	
	
	
</body>
</html>