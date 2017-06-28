<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   浏览发票
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">	
	        <tr>
	            <td class="columnTitle">报运的合同号：</td>
	            <td class="tableContent">${scNo}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent">${blNo}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">贸易条款：</td>
	            <td class="tableContent">${tradeTerms}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">${state}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建人：</td>
	            <td class="tableContent">${createBy}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建部门：</td>
	            <td class="tableContent">${createDept}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建时间：</td>
	            <td class="tableContent">${createTime}</td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

