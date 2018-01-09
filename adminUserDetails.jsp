<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
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

<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

table#t01 {
	width: 100%;
	background-color: #f1f1c1;
}
</style>

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
	<br><br>
	<br>	
	<h2 align="center" style="color: red">Customer Details</h2>
	<br>
	<table id="t01" style="width: 100%">
		<tr>
			<th>ID</th>
			<th>PWD</th>
			<th>Name</th>
			<th>Age</th>
			<th>DOB</th>
			<th>Gender</th>
			<th>Address</th>
			<th>Email-ID</th>
			<th>Phone</th>
			<th>Height</th>
			<th>Weight</th>
			<th>FeeType</th>
			<th>Alter</th>
			
		</tr>

		<c:forEach var="custRecords" items="${customerRecords}">
			<tr>
				<c:set var="numberOfRows" value="0" />
				<c:forEach var="eachCol" items="${custRecords}">
				
					<c:choose> 
					<c:when test="${numberOfRows=='0'}">
						<td><c:out value="${eachCol}" /></td>
						<c:set var="numberOfRows" value="${numberOfRows+1}" />
						<c:set var="custID" value="${eachCol}" />
					</c:when>
					<c:otherwise>
					<td><c:out value="${eachCol}" /></td>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				<td>
					<a href="adminUpdateCustomer?custID=${custID}">Update</a>
							
					<a href="adminDeleteCustomer?custID=${custID}">Remove</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	<br>
	<h2 align="center" style="color: red">Trainer Details</h2>
	<br>
	<table id="t01">
		<tr>
			<th>ID</th>
			<th>PWD</th>
			<th>Name</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Email-ID</th>
			<th>Phone</th>
			<th>Alter</th>
		</tr>
		<c:forEach var="trRecords" items="${trainerRecords}">
			<tr>
				<c:set var="numberOfRows" value="0" />
				<c:forEach var="eachCol" items="${trRecords}" varStatus="theCount">
				
					<c:choose> 
					<c:when test="${numberOfRows=='0'}">
						<td><c:out value="${eachCol}" /></td>
						<c:set var="numberOfRows" value="${numberOfRows+1}" />
						<c:set var="trID" value="${eachCol}" />
					</c:when>
					<c:otherwise>
					<td><c:out value="${eachCol}" /></td>
					</c:otherwise>
					</c:choose>

				</c:forEach>
				<td>
				
					<a href="adminUpdateTrainer?trID=${trID}">Update</a>							
					<a href="adminDeleteTrainer?trID=${trID}">Remove</a>
					
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>