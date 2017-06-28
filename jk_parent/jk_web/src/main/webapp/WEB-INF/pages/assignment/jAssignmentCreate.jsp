<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/datepicker/WdatePicker.js"></script>
	
	

</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('assignmentAction_insert','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${pageContext.request.contextPath }/skin/default/images/icon/currency_yen.png" />
			新增任务
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">：发布人</td>
					<td class="tableContent"><input type="text"
						disabled="disabled" name="username"
						value="${_CURRENT_USER.userInfo.name}" /></td>
					<input type="hidden" name="userid" value="${_CURRENT_USER.id}" />
					</td>
				</tr>
				<!-- <tr>
					<td class="columnTitle">发布内容 :</td>
					<td class="tableContent"><input type="text" name="content"
						value="" /></td>
				</tr> -->
				<tr>
					<td class="columnTitle">结束时间 :</td>
					<td class="tableContent"><input type="text"
						style="width: 90px;" name="overtime"
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
					</td>
					<!-- <input type="text" name="overtime"
						value="" /></td> -->
				</tr>
				<tr>
					<td class="columnTitle">通知给 :</td>



					<%-- <c:forEach items="${userList }" var="o">

						<td>
						<br>
						<input type="checkbox" name="senderids" value="${o.id }">${o.userInfo.name }</td>

					</c:forEach> --%>
				</tr>
				<!-- <input type="text" name="senderids" value=""/> -->




				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<tbody>
							<c:forEach items="${userList }" var="o">

								<td><br> <input type="checkbox" name="senderids"
									value="${o.id }">${o.userInfo.name }</td>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</table>
		
			<table class="commonTable" cellspacing="1">
				<!-- <tr>
					<td class="columnTitle">发布内容 :</td>
					<td class="tableContent"><input type="text" name="content"
						value="" /></td>
				</tr> -->
				<tr>
	            <td class="columnTitle">发布内容：</td>
	            <td class="tableContent">
					<textarea style="height:200px;" name="content"></textarea>
				</td>
	        </tr>
				
				
			</table>
		</div>
<div id="container" style="overflow:hidden;">
    <div style=""></div>
</div>


	</form>
</body>
</html>

