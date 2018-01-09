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
	<div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Customer</a></li>
        
      </ul>
      
      <div >
        <div id="signup">
          
          <form action="/trainerCustomerDiet" method="post">
                    
            <div class="field-wrap">
            <label>Customer ID:&nbsp;&nbsp;</label>
            <input type="text" name="id" value="${custID}" readonly>
          	<br>
          	<br>
             <label>Diet:&nbsp;&nbsp;</label>
             <select name="diet">
			   <option value="AtKins">AtKins</option>
  			   <option value="The Zone">The Zone</option>
  			   <option value="Vegetarian">Vegetarian</option>
  			   <option value="Vegan">Vegan</option>
  			   <option value="Weight Watchers">Weight Watchers</option>
  			   <option value="South Beach">South Beach</option>
  			   <option value="Raw Food">Raw Food</option>
  			   <option value="Mediterranean">Mediterranean</option>
			 </select>			
            <br>
            <br>
            <label>Workout:&nbsp;&nbsp;</label>
            <select name="workout">
			   <option value="Endurance">Endurance</option>
  			   <option value="Strength">Strength</option>
  			   <option value="Balance">Balance</option>
  			   <option value="Flexibility">Flexibility</option>
			 </select>           
          </div>
          
          <button type="submit" class="button button-block"/>Assign Diet</button>
          
          </form>

        </div>
        
        </div>
       
          
</div> <!-- /form -->
   <script src="../js/index2.js"></script>
 <script src="../js/index.js"></script>
	
	
	
</body>
</html>