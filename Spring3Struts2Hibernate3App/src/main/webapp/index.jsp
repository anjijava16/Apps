<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>User Login</title>
  </head>
  
  <body>
    <s:form action="loginUser">
    	<s:actionerror/><!-- must use it, otherwise message will not show up -->
    	<s:textfield name="name" label="user name"></s:textfield>
    	<s:textfield name="password" label="password"></s:textfield>
    	<s:submit value="Login"></s:submit>  	
    </s:form>
    <A href="register.jsp" title="sign up">New user sign up</A>
    <A href="recover_password.jsp" title="recover my password">Forgot password?</A>
  </body>
</html>
