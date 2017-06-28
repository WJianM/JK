<%@page import="org.apache.struts2.components.If"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
							<li id="save"><a href="#"
								onclick="formSubmit('assignmentAction_insertmymenu','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${pageContext.request.contextPath }/skin/default/images/icon/currency_yen.png" />
			自定义菜单
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<%-- <tr>
					<td class="columnTitle">自定义菜单一 :</td>
					<td class="tableContent"><select name="murl">
							<c:forEach items="${moduleList }" var="o">
								<option value="${o.curl }">${o.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="columnTitle">自定义菜单二 :</td>
					<td class="tableContent"><select name="murl">
							<c:forEach items="${moduleList }" var="o">
								<option value="${o.curl }">${o.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="columnTitle">自定义菜单三 :</td>
					<td class="tableContent"><select name="murl">
							<c:forEach items="${moduleList }" var="o">
								<option value="${o.curl }">${o.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="columnTitle">自定义菜单四 :</td>
					<td class="tableContent"><select name="murl">
							<c:forEach items="${moduleList }" var="o">
								<option value="${o.curl }">${o.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="columnTitle">自定义菜单五 :</td>
					<td class="tableContent"><select name="murl">
							<c:forEach items="${moduleList }" var="o">
								<option value="${o.curl }">${o.name }</option>
							</c:forEach>
					</select></td>
				</tr> --%>
				<h1 style="color: green">选择自定义菜单</h1>
				<tr><td></td><td></td></tr>
				<tr>
					<td></td>
					<c:forEach items="${moduleList }" var="o" begin="0" end="3">
						<td><h4>
								<input type="checkbox" name="murl" value="${o.curl }" /><span
									style="font-size: 7">${o.name }</span>
							</h4>
							</span></td>
						<%-- <option value="${o.curl }">${o.name }</option> --%>
					</c:forEach>
				</tr>
				<tr><td></td>
				
					<c:forEach items="${moduleList }" var="o" begin="4" end="7">
							<td><h4>
							<input type="checkbox" name="murl" value="${o.curl }" /><span
								style="font-size: 7">${o.name }</span>
						</h4></span></td>
						<%-- <option value="${o.curl }">${o.name }</option> --%>
					</c:forEach>
				</tr>
					<tr><td></td>
				<c:forEach items="${moduleList }" var="o" begin="8" end="11">
							<td><h4>
							<input type="checkbox" name="murl" value="${o.curl }" /><span
								style="font-size: 7">${o.name }</span>
						</h4></span></td>
				</c:forEach>
				</tr>
					<tr><td></td>
				<c:forEach items="${moduleList }" var="o" begin="12" end="15">
							<td><h4>
							<input type="checkbox" name="murl" value="${o.curl }" /><span
								style="font-size: 7">${o.name }</span>
						</h4></span></td>
				</c:forEach>
				</tr>
					
			</table>
		</div>


	</form>
</body>
</html>

