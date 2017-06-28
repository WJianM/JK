<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('homeAction_insertmemo','_self');this.blur();">提交</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/desktop.png"/>
  	添加备忘录
  </div> 

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">请选择备忘录背景样式：</td>
	        </tr>
	        <tr>
	        	<td colspan="2">
					<c:forEach items="${memoBgList}" var="bg" varStatus="s">
						
						<input type="radio" name="memoBg.id" value="${bg.id}" /><img alt="" src="${ctx}/${bg.headpath}">
						<c:if test="${s.count == 4}"></br></c:if>
					</c:forEach>
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">请选择备忘录图标样式：</td>
	        	<td> 
					<c:forEach items="${memoIconList}" var="icon">
						<input type="radio" name="memoIcon.id" value="${icon.id}" /><img alt="" src="${ctx}/${icon.iconpath}">
					</c:forEach>
				</td>
	        </tr>
	        <tr></tr>
	  		<tr>
	            <td class="columnTitle">请填写备忘录内容：</td>
	            <td class="tableContent">
					<textarea style="height:200px;" name="remark"></textarea>
				</td>
	        </tr>
	    
		</table>
	</div>
 
 
</form>
</body>
</html>

