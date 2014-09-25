<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Ticket Booking</title>
<script src="/iTravel/js/jquery-2.1.1.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript">
	/* function clearText(){
		
	} */
</script>
</head>
<body>
<form action="searchcontroller" name="searchForm" id="searchForm">
	<p><input type="text" id="searchId" name="searchId" style="color:grey;"/></p>
	<p><input type="text" id="from" name="from" value="Enter city" style="color:grey;"/></p>
	<p><input type="text" id="to" name="to" value="Enter city" style="color:grey;"/></p>
	<p><input type="text" id="doj" name="doj" style="color:grey;"/></p>
	<p><input type="text" id="dor" name="dor" style="color:grey;"/></p>
	<p><input type="submit" value="Search buses" id="searchBtn" /></p>
	<span id="fromspan"></span>
	<span id="tospan"></span>
</form>

</body>
</html>
<script>
	$(document).ready(function(){
		var cityText = "Entercity"
		$("#from").attr('value', cityText).focus(function() {
			if($(this).val() == cityText){
				$(this).attr('value','');	
			}
		}).blur(function(){
			if($(this).val() == ''){
				$(this).attr('value',cityText);
			}
		});

		/* $("#to").click(function() {
			$("#to").val("");
		});
		$("#from").click(function() {
			$("#fromspan").html("Enter city");
		});

		$("#to").click(function() {
			$("#tospan").html("Enter city");
		}); */
		
	});
	
</script>