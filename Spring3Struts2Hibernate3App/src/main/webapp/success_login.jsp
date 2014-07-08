<%@ page contentType="text/html; charset=UTF-8" %> 
 
<html> 
<head><title>Login successfully</title></head> 
 
<body> 
<h2>Hello, ${sessionScope.username}</h2>  
<%=session.getAttribute("USER_ID") %> 
<A href="listUser.action" title="list all users">list all users</A>
</body> 
</html> 