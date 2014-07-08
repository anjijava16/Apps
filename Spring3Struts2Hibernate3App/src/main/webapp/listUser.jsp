<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <script type="text/javascript">    
    var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" ||window.ActiveXObject);
     
    if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
        function XMLHttpRequest() {
            var arrSignatures = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
                                 "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP",
                                 "Microsoft.XMLHTTP"];
                             
            for (var i=0; i < arrSignatures.length; i++) {
                try {        
                    var oRequest = new ActiveXObject(arrSignatures[i]);            
                    return oRequest;        
                } catch (oError) { /*ignore*/ }
            }          
    
            throw new Error("MSXML is not installed on your system.");               
        }
    }    
            
    function retrieveBook() {        
        if(bXmlHttpSupport) {
            var sUrl = 'ajaxExample.action';
            var oRequest = new XMLHttpRequest();
            oRequest.onreadystatechange = function() {
                if(oRequest.readyState == 4) {                    
                    var oBook = eval('(' + oRequest.responseText + ')');
                    var bookHolder = document.getElementById('bookHolder');
                    var sBook = '<p><b>ISBN: </b>' + oBook.ISBN + '</p>';
                    sBook += ('<p><b>Title: </b>' + oBook.title + '</p>');
                    sBook += ('<p><b>Price: </b>$' + oBook.price + '</p>');
                    sBook += ('<b><i>Comments: </i></b><hr/>');
                    for(i = 0; i < oBook.comments.length; i++) {
                        sBook += ('<p><b>#' + (i + 1) + ' </b>' + oBook.comments[i] + '</p>');
                    }
                    bookHolder.innerHTML = sBook;
                    retrivebookbutton.innerHTML = "test success";
                }
            };
            oRequest.open('POST', sUrl);
            oRequest.send(null);
        }
    }
    </script>
</head>
 
<body>

<h2>All Users</h2>

<s:if test="userList.size() > 0">
<table border="1px" cellpadding="8px">
	<tr>
		<th>Name</th>
		<th>Email</th>
		<th>Gender</th>
	</tr>
	<s:iterator value="userList" status="userStatus">
		<tr>
			<td><s:property value="name" /></td>
			<td><s:property value="email" /></td>
			<td><s:property value="gender" /></td>

		</tr>
	</s:iterator>
</table>
</s:if>
<br/>
<br/>

</body>
</html>