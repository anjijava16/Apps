<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Search Results</title>
<script src="/iTravel/js/jquery-2.1.1.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<table id="searchtable" border="1" class="travelling_txt">

		<tr>
			<th>ID</th>
			<th>Origin</th>
			<th>Destination</th>
			<th>Date of Journey</th>
			<th>Date of Return</th>
		</tr>

		<c:forEach var="searchResult" items="${searchResult}">
			<tr>
				<td><c:out value="${searchResult.id}" /></td>
				<td><c:out value="${searchResult.fromCity}" /></td>
				<td><c:out value="${searchResult.toCity}" /></td>
				<td><c:out value="${searchResult.journeyDate}" /></td>
				<td><c:out value="${searchResult.returnDate}" /></td>
				<td><input type="button" value="View Seats" id="viewSeatsBtn" onclick="addNew();"/>
				<div id="seatsDiv" style="display: none;">
						<%@ include file="seats.jsp"%>
				</div></td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>
<script>
function addNew(){
var size = '<c:out value="${searchResult.size()}"/>';//var size = '${searchResult.size()}';
	for(var i=0;i<size;i++){
		$("#seatsDiv").toggle();
	}
		
}
</script>

