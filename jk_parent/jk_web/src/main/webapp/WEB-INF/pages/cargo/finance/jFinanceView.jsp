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
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    浏览财务单
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">财务单号：</td>
	            <td class="tableContent">${Id}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建时间：</td>
	            <td class="tableContent">${inputDate}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建人：</td>
	            <td class="tableContent">${inputBy}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">
	           <c:if test="${state==0}">草稿</c:if> 
	           <c:if test="${state==1}">
				<font color="green">已上报</font>
						</c:if>
	              <c:if test="${state==2}">
	            <font color="red">已配置</font>
				</c:if>
				</td>
			<tr>
	            <td class="columnTitle">从属发票:</td>
	        </tr>
			
	
	 		<tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent">${id}</td>
	            <td class="columnTitle">装箱号：</td>
	            <td class="tableContent">${PackingList_id}</td>
	            <td class="columnTitle">创建人：</td>
	            <td class="tableContent">${create_by}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建时间：</td>
	            <td class="tableContent">${create_time}</td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">
	           <c:if test="${state==0}">草稿</c:if> 
	           <c:if test="${state==1}">
				<font color="green">已上报</font>
						</c:if>
	              <c:if test="${state==2}">
	            <font color="red">已配置</font>
				</c:if>
				</td>
	        </tr>	
	          
	            
	       	
	      
		</table>
	</div>
 
 
</form>
</body>
</html>

