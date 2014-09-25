<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
<head>
<title>payment details</title>
<script src="/iTravel/js/jquery-2.1.1.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<script src="/iTravel/js/itravel.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<form action="bookingController" class="travelling_txt">
	<p><input id="name" type="text" value="Enter Name" name="name" style="color:grey;"/></p>
	<p><input id="age" type="text" value="Enter Age" name="age" style="color:grey;"/></p>
	<p><input id="email" type="text" value="Enter email" name="email" style="color:grey;"/></p>
	<p><input id="mobile" type="text" value="Enter Mobile Number" name="mobile" style="color:grey;"/></p>
	<p>Credit Card Type:<input id="visa" type="radio" name="cardType" value="visa" >Visa<input id="master" type="radio" name="cardType" value="master" >Master</p>
	<p><input id="cardno" type="text" value="Enter Credit card number" size="30" name="cardno" style="color:grey;"/></p>
	<p>Expiry Date:
	<select id="expiryMonth" name="expiryMonth">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
			<option>6</option>
			<option>7</option>
			<option>8</option>
			<option>9</option>
			<option>10</option>
			<option>11</option>
			<option>12</option>
		</select>
		<select id="expiryYear" name="expiryYear">
			<option>2015</option>
			<option>2016</option>
			<option>2017</option>
			<option>2018</option>
			<option>2019</option>
		</select>
		
		</p>
	<p><input id="payBtn" type="submit" value="Pay"></p>
	
</form>
</body>
</html>