<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${pageContext.request.contextPath }/skin/default/images/icon/currency_yen.png"/>
   任务详情:
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发布人:</td>
	            <td class="tableContent">${username}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">截止时间:</td>
	            <td class="tableContent">${overtime}</td>
	        </tr>	
	        	
	        <tr>
	            <td class="columnTitle">任务状态:</td>
	            <td class="tableContent">
	            <c:if test="${state==0.0}">未读</c:if>
	            <c:if test="${state==1.0}">已读</c:if>
	            <c:if test="${state==2.0}">已完成</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">主要内容:</td>
	            <td class="tableContent">${content}</td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

