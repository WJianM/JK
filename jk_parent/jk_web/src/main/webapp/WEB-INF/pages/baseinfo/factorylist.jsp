<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
	<script>
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
	    		 formSubmit('factoryAction_toview','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('factoryAction_toupdate','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //删除提示
	     function toDelete(){
	    	 var flag=window.confirm("确定要删除吗？")
	    	 if (flag) {
	    		 formSubmit('factoryAction_delete','_self');
			}
	     }
	   //实现条件查询
	     /* function toSearch(){
	    	 alert("aaa")
	    	 var chaxun=$("#chaxun").val();
	    	 if(chaxun!=""){
	    		 formSubmit('factoryAction_search','_self');
	    	 }else{
	    		 alert("请输入查询关键字");
	    	 } 
	     } */
	</script>
</head>

<body>
<form name="icform" method="post">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="javascript:toView()">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('factoryAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toUpdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:toDelete()">删除</a></li>
</ul>
		

  </div>
</div>
</div>
</div>
   
   <div>
				<table>

					<tr>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>厂家名称缩写: <input type="text" id="chaxun" name="name" value="${name }" />
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						
						<td><a
							style="display: block; width: 80px; height: 20px; border: 1px solid #CCC; background: #99CC99;"
							href="#"
							onclick="formSubmit('factoryAction_search','_self');this.blur();">查 询</a>
						</td>
					</tr>
				</table>
			</div>
   
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
     厂家信息
  </div> 
  </div>
  </div>
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">编号</td>
		<td class="tableHeader">类型</td>
		<td class="tableHeader">厂家全称</td>
		<td class="tableHeader">名称缩写</td>
		<td class="tableHeader">地址</td>
		<td class="tableHeader"></td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.ctype}</a></td>
		<td><a href="factoryAction_toview?id=${o.id}">${o.fullName }</a></td>
		<td><a href="factoryAction_toview?id=${o.id}">${o.factoryName }</a></td>
		<td>${o.address }</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

