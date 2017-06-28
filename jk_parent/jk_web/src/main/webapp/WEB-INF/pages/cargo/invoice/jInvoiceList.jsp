<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
	<title></title>
	<script type="text/javascript">

	function deleteCon() {
		if (checkId()) {
			alert("对不起 ,选中的数据有上报的委托单!!")
		} else {
			if (confirm("您确定要删除么???"))
				formSubmit('invoiceAction_delete', '_self');
		}
	}

	// 已上报发票检查修改
	function updateCon() {
		if (checkId()) {
			alert("对不起 ,选中的数据有上报的委托单!!")
		} else {
			formSubmit('invoiceAction_toupdate', '_self');
		}
	}

	//检查选中的发票是否有选中的发票,有选中的返回true
	function checkId() {
		var count = 0;
		var arr = $("input[name='id']:checked");
		arr.each(function() {
			<c:forEach items="${results}" var="o">
			if ($(this).val() == "${o.id}") {
				state = "${o.state}";
				if (state == 1 || state == 2) {
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
<li id="view"><a href="#" onclick="formSubmit('invoiceAction_toview','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('invoiceAction_tocreate','_self');this.blur();">新增</a></li>
<!-- <li id="update"><a href="#" onclick="formSubmit('invoiceAction_toupdate','_self');this.blur();">修改</a></li> -->
<li id="update"><a href="#" onclick="updateCon()">修改</a></li>
<li id="delete"><a href="#" onclick="deleteCon()">删除</a></li>
<li id="new"><a href="#" onclick="formSubmit('invoiceAction_submit','_self');this.blur();">提交</a></li>
<li id="delete"><a href="#" onclick="formSubmit('invoiceAction_cacle','_self');this.blur();">取消</a></li>
<li id="print"><a href="#" onclick="formSubmit('invoiceAction_print','_self');this.blur();">打印</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    发票列表
  </div> 
  
<div>
<!-- 模糊查询的字段框 -->
			<div>
				<table>

					<tr>
						<td>发票号: <select name="InvoiceNo">
						
								<option value="">--请选择--</option>
								<option <c:if test="${InvoiceNo == 1 }">selected</c:if> value="1">1</option>
								<option <c:if test="${InvoiceNo == 2 }">selected</c:if> value="2">2</option>
						</select></td>
						
						<td>    &nbsp;&nbsp;&nbsp;</td>
						<td> <a href="#"
								onclick="formSubmit('invoiceAction_list','_self');this.blur();">查询</a> </td>
					</tr>
				</table>
			</div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		
		<td class="tableHeader">序号</td>
		<td class="tableHeader">报运的合同号</td>
		<td class="tableHeader">贸易条款</td>
		<td class="tableHeader">创建部门</td>
		<td class="tableHeader">创建人</td>
		<td class="tableHeader">创建时间</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		
		<td>${o.scNo}</td>
		<td>${o.blNo}</td>
		
		<td>${o.createDept}</td>
		<td>${o.createBy}</td>
		<td>${o.createTime}</td>
		<td><c:if test="${o.state==0}">草稿</c:if>
			<c:if test="${o.state==1}"><font color="green">已提交</font></c:if>
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

