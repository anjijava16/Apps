<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Add Note</title>
  </head>
  
  <body>
    <s:form action="addNote" name="addNote">
    	<s:textfield name="title" label="note title"></s:textfield>
    	<s:textarea name="content" label="note content"  cols="50" rows="10"></s:textarea>    	
    	<s:submit value="submit" label="add"></s:submit>
    </s:form>
  </body>
</html>
