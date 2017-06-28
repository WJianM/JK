<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('assignmentAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${pageContext.request.contextPath }/skin/default/images/icon/currency_yen.png"/>
   修改部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="id" value="${id}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="userid" value="${userid}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="content" value="${content}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="overtime" value="${overtime}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="state" value="${state}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="senderids" value="${senderids}"/></td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

