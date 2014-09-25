
<html>
<head>
<title>Seats</title>
</head>
<body>
<form action="bookingController">
	Seat1:<input id="seat1" type="checkbox"/>
	Seat2:<input id="seat2" type="checkbox"/>
	Seat3:<input id="seat3" type="checkbox"/>
	Seat4:<input id="seat4" type="checkbox"/>
	<p>Boarding Point:
		<select id="boardingPoint">
			<option>Begumpet</option>
			<option>SR Nagar</option>
			<option>Ameerpet</option>
		</select>
	</p>
	<p><input id="bookBtn" type="submit" value="Book"/></p>
	
</form>
<div id="loginPage" style="border:0px">
		<%@ include file="/WEB-INF/pages/login.jsp" %>  
	</div>
</body>
</html>
<!-- <script>
	$(document).ready(function() {
		$("#bookBtn").click(function() {
			$("#paymentDetailsPage").toggle();
		});
	});
</script> -->