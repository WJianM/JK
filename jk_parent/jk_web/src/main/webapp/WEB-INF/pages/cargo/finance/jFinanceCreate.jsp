<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
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
<li id="save"><a href="#" onclick="formSubmit('financeAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
		<img src="${ctx }/skin/default/images/icon/folder_edit.png"/>
   
   新增财务报表
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	       	
	        <tr>
	            <td class="columnTitle">制表人 ：</td>
	            <td class="tableContent"><input type="text" name="inputBy" value="${_CURRENT_USER.userInfo.name}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">制表日期 ：</td>
	         <td class="tableContent">
					<input type="text" style="width:90px;" name="inputDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>	
	        </tr>
	        
	        
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
		<td><input type="radio" name="invoiceId" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.id}</td>
	<%-- 	<td><fmt:formatDate value="${o.create_time }" pattern="yyyy-MM-dd"/></td> --%>
		<td>${o.state==0?"草稿":"已上报"}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 

 
 
</form>
</body>
</html>

