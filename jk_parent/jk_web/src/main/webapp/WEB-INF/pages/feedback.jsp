<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('feedbackAction_send','_self');this.blur();">提交</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/desktop.png"/>
  	意见反馈
  </div> 

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">主题：</td>
	            <td class="tableContent">
					<input type="text" name="subject"/>
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">内容：</td>
	            <td class="tableContent">
					<textarea style="height:200px;" name="text"></textarea>
				</td>
	        </tr>
	  
	    
		</table>
	</div>
 
 
</form>
</body>
</html>

