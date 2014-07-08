<%@ page contentType="text/html; charset=UTF-8" %> 
 
<html> 
<head><title>Register successfully</title>
<META 
     HTTP-EQUIV="Refresh"
     CONTENT="5; URL=index.jsp">
</head> 
 
<body> 
<h2>Your password has be sent to your email address <%=request.getAttribute("email") %>.</h2>  


Your browser should automatically go to login page after five seconds.
<br>
or you can click
<A href="index.jsp" title="login">login</A>
</body> 
</html> 