<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
	
</head>

<body>
<form name="icform" method="post">
<!-- <div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
<li id="new"><a href="#" onclick="formSubmit('factoryAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toUpdate()">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('factoryAction_delete','_self');this.blur();">删除</a></li> 
</ul>
  </div>
</div>
</div>
</div> -->
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
     登录日志信息
  </div> 
  </div>
  </div>
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<!-- <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td> -->
		<td class="tableHeader">日志编号</td>
		<td class="tableHeader">登录用户</td>
		<td class="tableHeader">用户ip</td>
		<td class="tableHeader">登录时间</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<%-- <td><input type="checkbox" name="id" value="${o.id}"/></td> --%>
		<td>${status.index+1}</td>
		<td>${o.name}</a></td>
		<td>${o.address }</td>
		<td>${o.time }</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

