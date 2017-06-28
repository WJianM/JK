<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${ctx }/js/datepicker/WdatePicker.js""></script>
<script type="text/javascript">
	function deleteCon() {
		if (checkId()) {
			alert("对不起 ,选中的数据有上报的合同!!!")
		} else {
			if (confirm("您确定要删除么???"))
				formSubmit('contractAction_delete', '_self');
		}
	}

	// 已上报合同检查修改
	function updateCon() {
		if (checkId()) {
			alert("对不起 ,选中的数据有上报的合同!!!")
		} else {
			formSubmit('contractAction_toupdate', '_self');
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
							<li id="view"><a href="#"
								onclick="formSubmit('contractAction_toview','_self');this.blur();">查看</a></li>
							<li id="new"><a href="#"
								onclick="formSubmit('contractAction_tocreate','_self');this.blur();">新增</a></li>
							<!-- <li id="update"><a href="#"
								onclick="formSubmit('contractAction_toupdate','_self');this.blur();">修改</a></li> -->
							<li id="update"><a href="JavaScript:;" onclick="updateCon()">修改</a></li>
							<!-- <li id="delete"><a href="#"
								onclick="formSubmit('contractAction_delete','_self');this.blur();">删除</a></li>
							<li id="delete"><a href="#"
								onclick="formSubmit('contractAction_delete','_self');this.blur();">删除</a></li> -->
							<!-- <!-- <li id="delete"><a href="#"
								onclick="formSubmit('contractAction_delete','_self');this.blur();">删除</a></li> -->
							-->
							<li id="delete"><a href="#" onclick="deleteCon()">删除</a></li>
							<li id="new"><a href="#"
								onclick="formSubmit('contractAction_submit','_self');this.blur();">提交</a></li>
							<li id="new"><a href="#"
								onclick="formSubmit('contractAction_cancel','_self');this.blur();">取消</a></li>
							<li id="new"><a href="#"
								onclick="formSubmit('contractAction_print','_self');this.blur();">打印</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">
						<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
						购销合同列表
					</div>
				</div>
			</div>
			<!-- 模糊查询的字段框 -->
			<div>
				<table>

					<tr>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>客户名称: <input type="text" name="cname" value="${cname }" />
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>

						<td>贸易条款: <select name="ctradeTerms">

								<option value="">--请选择--</option>
								<option <c:if test="${ctradeTerms == 'FOB' }">selected</c:if>
									value="FOB">FOB</option>
								<option <c:if test="${ctradeTerms == 'CRF' }">selected</c:if>
									value="CFR">CFR</option>
								<option <c:if test="${ctradeTerms == 'CIF' }">selected</c:if>
									value="CIF">CIF</option>
								<option <c:if test="${ctradeTerms == 'L/C' }">selected</c:if>
									value="L/C">信用证</option>
								<option <c:if test="${ctradeTerms == 'T/T' }">selected</c:if>
									value="T/T">电汇</option>
						</select></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<%-- <td>交货日期:
						<input type="text" style="width: 90px;" name="cshipTime"
							value="${cshipTime }"
							onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
						</td> --%>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><!-- <a
							style="display: block; width: 80px; height: 20px; border: 1px solid #CCC; background: #99CC99;"
							href="#"
							onclick="formSubmit('contractAction_list','_self');this.blur();">查 询</a> -->
							<div id="menubar">
								<div id="middleMenubar">
									<div id="innerMenubar">
										<div id="navMenubar">
							                <li id="view"><a href="#" onclick="formSubmit('contractAction_list','_self');this.blur();">查询</a></li>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>


			<div>


				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<thead>
							<tr>
								<td class="tableHeader"><input type="checkbox" name="selid"
									onclick="checkAll('id',this)"></td>
								<td class="tableHeader">序号</td>
								<td class="tableHeader">客户名称</td>
								<td class="tableHeader">合同号</td>
								<td class="tableHeader">货物数/附件数</td>
								<td class="tableHeader">制单人</td>
								<td class="tableHeader">审单人</td>
								<td class="tableHeader">验货员</td>
								<td class="tableHeader">签单日期</td>
								<td class="tableHeader">交货期限</td>
								<td class="tableHeader">船期</td>
								<td class="tableHeader">贸易条款</td>
								<td class="tableHeader">总金额</td>
								<td class="tableHeader">状态</td>
								<td class="tableHeader">操作</td>
							</tr>
						</thead>
						<tbody class="tableBody">
							<%-- ${links } --%>
							<c:forEach items="${results}" var="o" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id" value="${o.id}" /></td>
									<td>${status.index+1}</td>
									<td>${o.customName}</td>
									<td><a href="contractAction_toview?id=${o.id}">${o.contractNo}</a></td>
									<td>${o.contractProducts.size() }/<c:set var="extNo"
											value="0"></c:set> <c:forEach items="${o.contractProducts }"
											var="cp">
											<c:if test="${cp.extCproducts.size()!=0 }">
												<c:set var="extNo" value="${extNo+cp.extCproducts.size()}"></c:set>
											</c:if>
										</c:forEach> ${extNo }
									</td>
									<td>${o.inputBy}</td>
									<td>${o.checkBy}</td>
									<td>${o.inspector}</td>
									<td><fmt:formatDate value="${o.signingDate}"
											pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${o.deliveryPeriod}"
											pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${o.shipTime}"
											pattern="yyyy-MM-dd" /></td>
									<td>${o.tradeTerms}</td>
									<td>${o.totalAmount}</td>
									<td><c:if test="${o.state==0}">草稿</c:if> <c:if
											test="${o.state==1}">
											<font color="green">已上报</font>
										</c:if></td>
									<c:if test="${o.state==1}">
										<td><a href="#">[货物]</a></td>
									</c:if>
									<c:if test="${o.state==0}">
										<td><a
											href="${ctx }/cargo/contractProductAction_tocreate?contract.id=${o.id}">[货物]</a></td>
									</c:if>
								</tr>
							</c:forEach>
							${links }
						</tbody>
					</table>
				</div>
			</div>
	</form>
</body>
</html>

