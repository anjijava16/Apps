<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<style type="text/css">
#notes
{
font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
border-collapse:collapse;
word-wrap:break-word;
table-layout: fixed;
}

#notes td, #notes th 
{
font-size:1em;
border:1px solid #98bf21;
padding-top:0px;
padding-left:0px;
padding-right:0px;
padding-bottom:0px;
}
#notes th 
{
font-size:1.1em;
text-align:center;
padding-top:0px;
padding-bottom:0px;
background-color:#A7C942;
color:#ffffff;
}
#notes tr.alt td 
{
color:#000000;
background-color:#EAF2D3;
}

/*popup menu style*/ 
.skin { 
	padding-top:10px; 
	cursor:default; 
	font:menutext; 
	position:absolute; 
	text-align:left; 
	font-size: 12pt; 
	width:150px; /*..........................*/ 
	background-color:#A7C942;
	border:2 solid buttonface; 
	border:2 outset buttonhighlight; 
}
</style>

</head>
    <script language ="javascript">   
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
            
    var currentCell;
      
    var inputTitle = document.createElement("input");
    inputTitle.type="text";
    inputTitle.size="27";
    var titleCell;
    function editCellTitle(event){
        if(event==null){
            currentCell=window.event.srcElement;
        }
        else{
            currentCell=event.target;
        }
        var tmp = currentCell.id;
        var titleId = tmp.replace("editTitle","");
        titleId = "title" + titleId;
        titleCell = document.getElementById(titleId);
       
        inputTitle.value=titleCell.innerHTML;
        inputTitle.onblur=updateNoteTitle;
        titleCell.innerHTML="";
        titleCell.appendChild(inputTitle);
        inputTitle.style.position="relative";
	inputTitle.style.top="0";
	inputTitle.style.left="0";
        titleCell.focus();
		//or inputTitle.select();
    }   

    var inputContent = document.createElement("TEXTAREA");
    //inputContent.rows=20;
    inputContent.cols=75;
    var contentCell;
    function editCellContent(event){
        if(event==null){
            currentCell=window.event.srcElement;
        }
        else{
            currentCell=event.target;
        }

        var tmp = currentCell.id;
        var contentId = tmp.replace("editContent","");
        contentId = "content" + contentId;
        contentCell = document.getElementById(contentId);
	inputContent.style.height=contentCell.getBoundingClientRect().bottom-contentCell.getBoundingClientRect().top;
//getBoundingClientRect() not working for chrome???
        var tmp2 = contentCell.innerHTML;
        tmp2 = tmp2.replace(/<PRE>/g,"");
        tmp2 = tmp2.replace(/<\/PRE>/g,"");
        inputContent.value=tmp2;

        inputContent.onblur=updateNoteContent;
        contentCell.innerHTML="";
        contentCell.appendChild(inputContent);
	inputContent.style.position="relative";
	inputContent.style.top="0";
	inputContent.style.left="0";
        contentCell.focus();
		//or inputContent.select();
    }       
    function updateNoteTitle() {        
        if(bXmlHttpSupport) {
            var title = inputTitle.value;
            var noteId = titleCell.id;
            noteId = noteId.replace("title","");
            var sUrl = 'updateNote.action?noteId=' + noteId + '&title=' + title;
            var oRequest = new XMLHttpRequest();
            oRequest.onreadystatechange = function() {
                if(oRequest.readyState == 4) {
                    var oUpdateNote = eval('(' + oRequest.responseText + ')');
                    titleCell.innerHTML = oUpdateNote.title;
                }
            };
            oRequest.open('POST', sUrl);
            oRequest.send(null);
        }
    }  

    function updateNoteContent() {        
        if(bXmlHttpSupport) {
            var content = inputContent.value;
            content = content.replace(/<PRE>/g,"");
            content = content.replace(/<\/PRE>/g,"");
//alert(content);
            var noteId = contentCell.id;
//alert(noteId);
            noteId = noteId.replace("content","");            
//alert(noteId);
            var sUrl = 'updateNote.action?noteId=' + noteId + '&content=' + content;
            sUrl  = encodeURI(sUrl);
            var oRequest = new XMLHttpRequest();
            oRequest.onreadystatechange = function() {
                if(oRequest.readyState == 4) {
                    var oUpdateNote = eval('(' + oRequest.responseText + ')');
		    var t = oUpdateNote.content;	
                    t='<pre>' + t + '</pre>';
//alert(t);
                    contentCell.innerHTML = t;
                }
            };
            oRequest.open('POST', sUrl);
            //oRequest.setRequestHeader("Content-Type","text/xml;charset=UTF-8");
            oRequest.send(null);
        }
    }   
    
    var currentTarget;
    function deleteNote(event) {        
        if(event==null){
            currentTarget=window.event.srcElement;
        }
        else{
            currentTarget=event.target;
        }
        
        if(bXmlHttpSupport) {
            var noteId = currentTarget.id;
	    	var row_to_be_removed = currentTarget.id;
            noteId = noteId.replace("delete","");
            row_to_be_removed = row_to_be_removed.replace("delete","row");
            var sUrl = 'deleteNote.action?noteId=' + noteId;
            var oRequest = new XMLHttpRequest();
            oRequest.onreadystatechange = function() {
                if(oRequest.readyState == 4) {
                    var oDeleteNote = eval('(' + oRequest.responseText + ')');
                    removeElement(row_to_be_removed);
                    //currentCell.innerHTML = oDeleteNote.noteId;
                }
            };
            oRequest.open('POST', sUrl);
            oRequest.send(null);
        }
    }  
    
    function removeElement(id) {
    	var element = document.getElementById(id);
    	element.parentNode.removeChild(element);
	//alert(id);
    } 

    function repstr(str){       
        return str.replace(/\r\n/ig,"<br>")       
    }  

    function makevisible(cur,which){
        if (which==0)
            cur.filters.alpha.opacity=100
        else
            cur.filters.alpha.opacity=20
    }

    function popUp() { 
//alert(window.event.button);
	if (window.event.button != 2)
		return;
	var menu = document.getElementById('menu')
	var x
	var y
	if(typeof window.pageYOffset != 'undefined') {
		x = window.pageXOffset;
		y = window.pageYOffset;
	}
	else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
		x = document.documentElement.scrollLeft;
		y = document.documentElement.scrollTop;
	}
	else if(typeof document.body != 'undefined') {
		x = document.body.scrollLeft;
		y = document.body.scrollTop;
	}
 
	x += window.event.clientX;
	y += window.event.clientY;


	if ( menu.style.display == ""){ 
		menu.style.display = "none" 
	} 
	else { 
		menu.style.display = ""
	} 
	menu.style.left = x
	menu.style.top = y
    } 

    function clearMenu() { //used to make the menu disappear
        //this function should be used at the beginning of any function that is called from the menu
        var menu = document.getElementById('menu');
        menu.style.display = "none"; //don't show menu
    }

    function clearShareNoteMenu() { //used to make the menu disappear
        var menu2 = document.getElementById('email_list');
        menu2.style.display = "none";
    }

    function shareNoteMenu(){
        clearMenu;
        var menu = document.getElementById('email_list');
        var x
        var y
        if(typeof window.pageYOffset != 'undefined') {
                x = window.pageXOffset;
                y = window.pageYOffset;
        }
        else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
                x = document.documentElement.scrollLeft;
                y = document.documentElement.scrollTop;
        }
        else if(typeof document.body != 'undefined') {
                x = document.body.scrollLeft;
                y = document.body.scrollTop;
        }

        x += window.event.clientX;
        y += window.event.clientY;


        if ( menu.style.display == ""){
                menu.style.display = "none"
        }
        else {
                menu.style.display = ""
        }
        menu.style.left = x
        menu.style.top = y

    }


    </script>

<body oncontextmenu="return false;" onClick="clearMenu()">
<!--oncontextmenu="return false;", disable default right click menu. refer to http://www.tuesdaydeveloper.com/2010/09/building-a-custom-right-click-menu-in-javascript/-->
<a href="logoutUser.action">logout</a>
<h2></h2>

<div id="menu" class="skin" style='display:none'>
	<div class="menuitems" onClick= "shareNoteMenu()">share</div><br>
	<a href='time1.htm' class="cc" >other</a><br>
</div>

<div id="email_list" class="skin" style='display:none'>
	<p>Please enter email list to whom you want to share this note, separated by comma.<br>
	<textarea rows="2" cols="30" id="email_list"></textarea><br>
	<input type="submit" value="Submit" onClick= ""/>
	<input type="Reset" value="Cancel" onClick= "clearShareNoteMenu()"/>

</div>

<table cellspacing="0" id="notes" width="1000">
	<tr>	<th width="20"></th>
		<th width="100">userId</th>
                <th width="20"></th>
		<th width="200">title</th>
                <th width="20"></th>
		<th width="640">content</th>
	</tr>
        <s:if test="noteList.size() > 0">
	<s:iterator value="noteList" status="noteStatus" id='number'>
                <tr id="row<s:property value="noteId" />" class="<s:if test="#noteStatus.odd == true ">alt</s:if><s:else></s:else>" onmouseup=popUp()>
		<!--<tr id="row<s:property value="noteId" />" class="alt">-->
			<td width="20" align="center"><img src="cross.bmp" style="filter:alpha(opacity=30)" alt="double click to delete the record" height="15" width="15" id="delete<s:property value="noteId" />" onMouseOver="makevisible(this,0)" onMouseOut="makevisible(this,1)" ondblclick= "deleteNote()"/></td>
			<td width="100" id="userId<s:property value="noteId" />"><s:property value="userId" /></td>
                        <td width="20" align="center"><img src="edit.jpg" style="filter:alpha(opacity=30)" alt="click to edit the record" height="15" width="15" id="editTitle<s:property value="noteId" />" onMouseOver="makevisible(this,0)" onMouseOut="makevisible(this,1)" onclick= "editCellTitle()"/></td>
 			<td width="200" id="title<s:property value="noteId" />"><s:property value="title" /></td>
                        <td width="20" align="center"><img src="edit.jpg" style="filter:alpha(opacity=30)" alt="click edit the record" height="15" width="15" id="editContent<s:property value="noteId" />" onMouseOver="makevisible(this,0)" onMouseOut="makevisible(this,1)" onclick= "editCellContent()"/></td>
			<td width="640"><pre id="content<s:property value="noteId" />"><s:property value="content" /></pre></td>
			
		</tr>
	</s:iterator>
        </s:if>
</table>


     <s:url id="url_pre" value="listNote.action">
         <s:param name="pageNum" value="pageNum-1"></s:param>
     </s:url>
     <s:url id="url_next" value="listNote.action">
         <s:param name="pageNum" value="pageNum+1"></s:param>
     </s:url>

     <s:if test="pageNum > 0">
     <s:a href="%{url_pre}">previous</s:a>
     </s:if>

     <s:if test="noteList.size() == 10">
     <s:a href="%{url_next}">next</s:a>
     </s:if>

<!--
<br/>
page number:
<p>${pageNum}

-->
<br>
<A href="addNote.jsp" title="Add a note">Add a note</A>

<br/>

</body>
</html>
