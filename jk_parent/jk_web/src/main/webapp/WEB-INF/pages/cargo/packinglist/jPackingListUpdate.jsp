<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
	     $(function(){
	    	  var v=$("input[name='exportIds']");
	    	  v.each(function(){
	    		  <c:forEach items="${exportList}" var="e">
		    	     if($(this).val() == "${e}"){
		    	    	 $(this).prop("checked","checked");
		    	     }
		    	  </c:forEach>
	    	  })
	    	     
	    	  
	     });
	</script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('packingListAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改装箱单
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        
	        <tr>
	            <td class="columnTitle">卖方：</td>
	            <td class="tableContent"><input type="text" name="seller" value="${seller}"/></td>
	      
	            <td class="columnTitle">买方：</td>
	            <td class="tableContent"><input type="text" name="buyer" value="${buyer}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent"><input type="text" name="invoiceNo" value="${invoiceNo}"/></td>
	        
	            <td class="columnTitle">发票日期：</td>
	            <td class="tableContent">
	             <input type="text" style="width:90px;" name="invoiceDate"
	            	 value="${invoiceDate}"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">唛头：</td>
	            <td class="tableContent"><input type="text" name="marks" value="${marks}"/></td>
	      
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="descriptions" value="${descriptions}"/></td>
	        </tr>	
	       
		</table>
	</div>
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
			<td>
			   <input type="checkbox" name="exportIds" value="${o.id}"/>
			</td>
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
			<td>已审批</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
 
 
</form>
</body>
</html>

