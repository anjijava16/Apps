<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>my first struts2 application</title>
</head>
<body>
<h2>Hello, ${sessionScope.username}</h2>
<h2><s:property value="name"/></h2>
<h2><s:property value="gender"/></h2>
sorry. something is wrong.
</body>
</html>