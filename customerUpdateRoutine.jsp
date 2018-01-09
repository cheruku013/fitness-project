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

<link href="css/register.css" rel="stylesheet" />
<script src="../js/index.js"></script>
<script src="../js/moment.js"></script>
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


<body background = "../img/wrkOutBg.jpg">
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
	<br>
     
     <div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Update Progress</a></li>
        
      </ul>
      
      <div >
        <div id="signup">
          
          <form action="/customerUpdateRoutineInfo" method="post">
          
          
            <div class="field-wrap">
            
          	
             <label>Workout Date:</label>
             <input type="date" name="wrkDate" id="wrkDate" required/>
             <br>
             <label>In Time:</label>
             <input type="time" name="inTime"  required/>
             <br>
             <label>Out Time:</label>
             <input type="time" name="outTime" required/>
             <br>
             
            <label>Height(ft):</label>
            <input type="number" step="any" min="4.0" max="100.0" value="${custHeight}" class="input" name="height" required>
            <br>
            <label>Weight(kg):</label>
            <input type="number" step="any" min="10.0" max="500.0" value="${custWeight}" class="input" name="weight" required>
            <br>
            
            
          </div>
          <script type="text/javascript" src="../js/moment.js">
          var tDate = moment().format('YYYY/MM/DD');
          document.getElementById("wrkDate").value = tDate;
          
          </script>
          
          <button type="submit" class="button button-block"/>Customer</button>
          
          </form>

        </div>
        
        </div>
       
          
</div> <!-- /form -->
   <script src="../js/index2.js"></script>
   <script src="../js/index.js"></script>
   <script src="../js/moment.js"></script>
	
	
</body>
</html>