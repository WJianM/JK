<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
<li id="save"><a href="#" onclick="formSubmit('financeAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/folder_edit.png"/>
   修改财务列表
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent"><input type="text" name="financeId" value="${Id}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建人：</td>
	            <td class="tableContent"><input type="text" name="inputBy" value="${inputBy}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建时间：</td>
	            <td class="tableContent"><input type="text" name="inputDate" value="${inputDate}"/></td>
	        </tr>	
	       <%--  <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent"><input type="text" name="state" value="${state}"/></td>
	        </tr>	 --%>
	       
	      
	    

	        
	        
	        
	        
		</table>
	</div>
	 <div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader">请选择</td>
		<td class="tableHeader">发票号</td>
		<td class="tableHeader">装箱号</td>
		<!-- <td class="tableHeader">创建人</td>
		<td class="tableHeader">创建时间</td> -->
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	
	
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="radio" name="invoiceId" value="${o.id}" <c:if test="${o.id==invoiceId}">checked</c:if>/></td>
		<td>${status.index+1}</td>
		<td>${o.id}</td>
		<%-- <td>${o.create_by}</td>
		<td><fmt:formatDate value="${o.create_time }" pattern="yyyy-MM-dd"/></td> --%>
		<td>
				<c:if test="${o.state==0}">草稿</c:if> 
	           	<c:if test="${o.state==1}">
				<font color="green">已上报</font>
				</c:if>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
 
</form>
</body>
</html>

