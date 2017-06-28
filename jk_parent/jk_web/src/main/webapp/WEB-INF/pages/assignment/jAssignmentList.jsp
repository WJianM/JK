<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.js"></script>
	<script>
	     function isChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				}
			if(count>0)
				return true;
			else
				return false;
	     }
	     function isOnlyChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				}
			//jquery
			//var count = $("[input name='id']:checked").size();
			if(count==1)
				return true;
			else
				return false;
	     }
	     function toView(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('assignmentAction_toview','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(){
	    	 if(isChecked()){
	    		 formSubmit('assignmentAction_toupdate','_self');
	    	 }else{
	    		 alert("请先选择一项或多选项，再进行操作！");
	    	 }
	     }
	     function toUpdate2(){
	    	 if(isChecked()){
	    		 formSubmit('assignmentAction_toupdate2','_self');
	    	 }else{
	    		 alert("请先选择一项或多选项，再进行操作！");
	    	 }
	     }
	     function delete2(){
	    	 if(isChecked()){
	    		 formSubmit('assignmentAction_delete','_self');
	    	 }else{
	    		 alert("请先选择一项或多选项，再进行操作！");
	    	 }
	     }
	</script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<!-- <li id="view"><a href="#" onclick="formSubmit('assignmentAction_toview','_self');this.blur();">查看详情</a></li> -->
<li id="view"><a href="#" onclick="toView()">查看详情</a></li>
<li id="new"><a href="#" onclick="formSubmit('assignmentAction_tocreate','_self');this.blur();">指派任务</a></li>
<!-- <li id="update"><a href="#" onclick="formSubmit('assignmentAction_toupdate','_self');this.blur();">标记已读</a></li> -->
<li id="update"><a href="#" onclick="toUpdate()">标记已读</a></li>


<li id="update"><a href="#" onclick="toUpdate2();">标记已办</a></li>
<!-- <li id="update"><a href="#" onclick="formSubmit('assignmentAction_toupdate2','_self');this.blur();">标记已办</a></li> -->
<!-- <li id="delete"><a href="#" onclick="formSubmit('assignmentAction_delete','_self');this.blur();">删除</a></li> -->
<li id="delete"><a href="#" onclick="delete2()">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${pageContext.request.contextPath }/skin/default/images/icon/currency_yen.png"/>
    任务列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">任务发布人</td>
		<td class="tableHeader">任务概述</td>
		<td class="tableHeader">结束时间</td>
		<td class="tableHeader">任务状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.username}</td>
		<td>${o.content}</td>
		<td>${o.overtime}</td>
		<td>
			<c:if test="${o.state==0.0}"><span style="color: red">未读</span></c:if>
			<c:if test="${o.state==1.0}"><span style="color: blue">已读</span></c:if>
			<c:if test="${o.state==2.0}"><span style="color: green">已办</span></c:if>
		
		</td>
		<td>${o.senderids}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

