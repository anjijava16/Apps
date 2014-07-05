<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="action" value="/add.html" ></c:url>
<form:form method="post" action="${action}" commandName="student">
 
    <table>
    <%-- <c:if test="${!empty student.name}"> --%>
	    <tr>
	        <td><form:label path="id"><spring:message code="label.id"/></form:label></td>
	        <td><form:input path="id" /></td>
	    </tr>
    <%-- </c:if> --%>
    <tr>
        <td><form:label path="name"><spring:message code="label.name"/></form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td colspan="2">
	        <c:if test="${empty student.name}">
	            <input type="submit" value="<spring:message code="label.add"/>"/>
	        </c:if>
	        <c:if test="${!empty student.name}">
	            <input type="submit" value="<spring:message code="label.update"/>"/>
	        </c:if>
        </td>
    </tr>
</table> 
</form:form>

<h3>Students</h3>
<c:if  test="${!empty studentList}">
<table >
<tr>
    <th>Id</th>
    <th>Name</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${studentList}" var="student">
    <tr>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td><a href="edit/${student.id}">Edit</a></td>
        <td><a href="delete/${student.id}">Delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
<!-- <form action="/StudentApp/add" method="post">
Id: <input type="text" name="id"/><br>
Name: <input type="text" name="name"/><br>
<input type="submit" name="submit" value="Add"/>
</form> -->