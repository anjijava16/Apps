<%@ page contentType="text/html; charset=UTF-8" %> 
 
<html> 
<head><title>Register successfully</title>
<META 
     HTTP-EQUIV="Refresh"
     CONTENT="5; URL=index.jsp">
</head> 
 
<body> 
<h2>Hello, ${sessionScope.username}</h2>  
<%=session.getAttribute("username") %>

You have registered successfully. Your browser should automatically go to login page after five seconds.
<br>
or you can click
<A href="index.jsp" title="login">login</A>
</body> 
</html> 