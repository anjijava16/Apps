<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Ticket Booking confirmation</title>
<script src="/iTravel/js/jquery-2.1.1.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<script src="/iTravel/js/itravel.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<table id="bookConfirm" border="1" class="travelling_txt">
	<h3>Ticket Booked Successfully. Below are the details</h3>
		<tr>
			<th>Origin</th>
			<th>Destination</th>
			<th>Date of Journey</th>
			<th>Date of Return</th>
		</tr>
		<c:forEach var="user" items="${userList}">
		${user.userName}<%-- 
			<c:if test="${user.userName eq()}">
			
			</c:if> --%>
		</c:forEach>
		<c:forEach var="searchResult" items="${searchResult}">
			<tr>
				<td><c:out value="${searchResult.fromCity}" /></td>
				<td><c:out value="${searchResult.toCity}" /></td>
				<td><c:out value="${searchResult.journeyDate}" /></td>
				<td><c:out value="${searchResult.returnDate}" /></td>
				<%-- <td>${searchResult.}</td>
				<td>${searchResult.}</td>
				<td>${searchResult.}</td> --%>

			</tr>
		</c:forEach>
	</table>

</body>
</html>