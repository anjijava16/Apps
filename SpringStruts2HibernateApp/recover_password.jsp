<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Recover Password</title>
  </head>
  
  <body>
    <s:form action="recoverPassword" name="recoverPassword">
    	<s:actionerror/><!-- must use it, otherwise message will not show up -->
    	<s:textfield name="name" label="user name"></s:textfield>
    	<s:textfield name="email" label="user email"></s:textfield>
    	<s:submit value="submit" label="Submit"></s:submit>
    </s:form>
  </body>
</html>
