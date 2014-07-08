<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>User Login</title>
  </head>
  
  <body>
    <s:form action="addUser" name="addUser">
    	<s:textfield name="name" label="user name"></s:textfield>
    	<s:textfield name="gender" label="user gender"></s:textfield>
    	<s:submit value="submit" label="add"></s:submit>
    </s:form>
  </body>
</html>
