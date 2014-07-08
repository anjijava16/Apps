<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>User register</title>
  </head>
  
  <body>
    <s:form action="registerUser" name="registerUser">
    	<s:textfield name="name" label="user name"></s:textfield>
    	<s:textfield name="password" label="user password"></s:textfield>
    	<s:textfield name="email" label="email address"></s:textfield>
    	<s:textfield name="gender" label="user gender"></s:textfield>
    	<s:submit value="submit" label="Submit"></s:submit>
    </s:form>
  </body>
</html>
