<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript">
	function deleteCon() {
		if (checkId()) {
			alert("对不起 ,选中的数据有上报或委托的装箱单!!!")
		} else {
			if (confirm("您确定要删除么???"))
				formSubmit('packingListAction_delete','_self')
		}
	}

	// 已上报合同检查修改
	function updateCon(){
		if (checkId()) {
			alert("对不起 ,选中的数据有上报或委托的装箱单!!!")
		} else {
			formSubmit('packingListAction_toupdate','_self');
		}
	}

	//检查选中的装箱单是否有选中的合同,有选中的返回true 
	function checkId(){
		var count = 0;
		var arr = $("input[name='id']:checked");
		arr.each(function() {
			<c:forEach items="${page.results}" var="o">
			if ($(this).val() == "${o.id}") {
				state = "${o.state}";
				if (state == 1 || state==2) {
					count++;
				}
			}
			</c:forEach>
		})
		if (count > 0) {
			return true;
		}
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
   		 formSubmit('packingListAction_toview','_self');this.blur();
   	 }else{
   		 alert("请先选择一项并且只能选择一项，再进行操作！");
   	 }
    }
    function cancel(){
		if(checkCancel()){
			alert("对不起 ,选中的数据已经委托，无法修改")
		} else {
			if (confirm("您确定要取消么???")){
				formSubmit('packingListAction_cancel','_self');
			}
			
		}
	}
    function checkCancel(){
		var count = 0;
		var arr = $("input[name='id']:checked");
		arr.each(function() {
			<c:forEach items="${page.results}" var="o">
			if ($(this).val() == "${o.id}") {
				state = "${o.state}";
				if (state==2) {
					count++;
				}
			}
			</c:forEach>
		})
		if (count > 0) {
			return true;
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
<!-- <li id="view"><a href="#" onclick="formSubmit('packingListAction_toview','_self');this.blur();">查看</a></li> -->
<li id="view"><a href="#" onclick="toView()">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('packingListAction_tocreate','_self');this.blur();">新增</a></li>
<!-- <li id="update"><a href="#" onclick="formSubmit('packingListAction_toupdate','_self');this.blur();">修改</a></li> -->
<li id="update"><a href="#" onclick="updateCon()">修改</a></li>
<!-- <li id="delete"><a href="#" onclick="formSubmit('packingListAction_delete','_self');this.blur();">删除</a></li> -->
<li id="new"><a href="#" onclick="deleteCon()">删除</a></li>
<li id="delete"><a href="#" onclick="formSubmit('packingListAction_submit','_self');this.blur();">上报</a></li>
<!-- <li id="delete"><a href="#" onclick="formSubmit('packingListAction_cancel','_self');this.blur();">取消</a></li> -->
<li id="delete"><a href="#" onclick="cancel()">取消</a></li>
<li id="new"><a href="#" onclick="formSubmit('packingListAction_print','_self');this.blur();">打印</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    装箱单列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">卖方</td>
		<td class="tableHeader">买方</td>
		<td class="tableHeader">发票号</td>
		<td class="tableHeader">发票日期</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.seller}</td>
		<td>${o.buyer}</td>
		<td>${o.invoiceNo}</td>
		<td>${o.invoiceDate}</td>
		<td>
		<c:if test="${o.state==0}">草稿</c:if>
		<c:if test="${o.state==1}"><b><font color="green">已上报</font></b></c:if>
		<c:if test="${o.state==2}"><b><font color="green">已委托</font></b></c:if>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 </form>
</body>
</html>

