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
			alert("对不起 ,选中的数据有上报的合同!!!")
		} else {
			if (confirm("您确定要删除么???"))
				formSubmit('exportAction_delete', '_self');
		}
	}

	// 已上报合同检查修改
	function updateCon() {
		//alert("1111");
		if (checkId()) {
			alert("对不起 ,选中的数据有上报的合同!!!")
		} else {
			formSubmit('exportAction_toupdate', '_self');
		}
	}

	//检查选中的合同是否有选中的合同,有选中的返回true
	function checkId() {
		var count = 0;
		var arr = $("input[name='id']:checked");
		arr.each(function() {
			<c:forEach items="${results}" var="o">
			if ($(this).val() == "${o.id}") {
				state = "${o.state}";
				if (state > 0) {
					count++;
				}
			}
			</c:forEach>
		})
		if (count > 0) {
			return true;
		}
	}
	function checkCancel(){
		var count=0;
		//获得被选中的对象
		var arr=$("input[name='id']:checked");
		arr.each(function() {
			<c:forEach items="${results}" var="o">
			if ($(this).val() == "${o.id}") {
				state = "${o.state}";
				if (state > 1) {
					count++;
				}
			}
			</c:forEach>
		})
		if (count > 0) {
			return true;
		}
	}
	function cancel(){
		if(checkCancel()){
			alert("对不起 ,选中的数据已经被海关审批,无法取消")
		} else {
			formSubmit('shippingOrderAction_cancle','_self');
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
<li id="view"><a href="#" onclick="formSubmit('exportAction_toview','_self');this.blur();">查看</a></li>
<!-- <li id="update"><a href="#" onclick="formSubmit('exportAction_toupdate','_self');this.blur();">修改</a></li> -->

<li id="update"><a href="JavaScript:;" onclick="updateCon()">修改2</a></li>

<li id="delete"><a href="JavaScript:;" onclick="deleteCon()">删除2</a></li>

<!-- <li id="delete"><a href="#" onclick="formSubmit('exportAction_delete','_self');this.blur();">删除</a></li> -->
<li id="new"><a href="#" onclick="formSubmit('exportAction_submit','_self');this.blur();">提交</a></li>
<!-- <li id="new"><a href="#" onclick="formSubmit('exportAction_submit','_self');this.blur();">提交</a></li> -->
<li id="new"><a href="#" onclick="cancel()">取消</a></li>
<li id="new"><a href="#" onclick="formSubmit('exportAction_wsEmport','_self');this.blur();">电子报运</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    出口报运列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">报运号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">信用证号</td>
		<td class="tableHeader">收货人及地址</td>
		<td class="tableHeader">装运港</td>
		<td class="tableHeader">目的港</td>
		<td class="tableHeader">运输方式</td>
		<td class="tableHeader">价格条件</td>
		<td class="tableHeader">制单日期</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.id}</td>
		<td align="center">
			${o.exportProducts.size()}
			/
			<c:set var="extNumber" value="0"></c:set><!-- 设置一个变量，用来累加，初始值0 -->
			<c:forEach items="${o.exportProducts}" var="ep">
			   <c:if test="${ep.extEproducts.size()!=0 }">
					<c:set var="extNumber" value="${extNumber + ep.extEproducts.size()}"/>
				</c:if>
			</c:forEach>
			${extNumber}
		</td>		
		<td>${o.lcno}</td>
		<td>${o.consignee}</td>
		<td>${o.shipmentPort}</td>
		<td>${o.destinationPort}</td>
		<td>${o.transportMode}</td>
		<td>${o.priceCondition}</td>
		<td><fmt:formatDate value="${o.inputDate }" pattern="yyyy-MM-dd"/></td>
		<%-- <td>${o.state==0?"草稿":"已上报"}</td> --%>
		<c:if test="${o.state==0}"><td><b><font color="green">草稿</font></b></td></c:if>
		<c:if test="${o.state==1}"><td><b><font color="green">已提交</font></b><td></c:if></td>
		<c:if test="${o.state==2}"><td><b><font color="green">已审批</font></b><td></c:if></td>
		<c:if test="${o.state==3}"><td><b><font color="green">已装箱</font></b><td></c:if></td>
		<c:if test="${o.state==4}"><td><b><font color="green">已委托</font></b><td></c:if></td>
		<c:if test="${o.state==5}"><td><b><font color="green">已开发票</font></b><td></c:if></td>
		<c:if test="${o.state==6}"><td><b><font color="green">已报发票</font></b><td></c:if></td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

