<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>数据汇总表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="../css/dataSummary.css">
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../js/dataSummary.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">

</head>

<body margintop="0px" style="OVERFLOW-Y: auto; OVERFLOW-X: hidden;">

	<div class="data_background">
		<font class="data_header_font">首页&gt;数据汇总表</font>
		<div class="data_bnt_paixu_location">
		<form id="sortForm" method="post" target="body">
			<!-- <img src="../img/up.png" class="importDateSortUp"> -->
			<!-- <img src="../img/down.png" class="importDateSortDown"> -->
			<input type="button" class="bnt_paixu importDateSort" value="按导入日期排序" onclick="importDateSort()">
			
			<!-- <img src="../img/up.png" class="deadlineSortUp"> -->
			<!-- <img src="../img/down.png" class="deadlineSortDown"> -->
			<input type="button" class="bnt_paixu deadlineSort" value="按合同期限排序" onclick="deadlineSort()">
			
			<!-- <img src="../img/up.png" class="signingDateSortUp"> -->
			<!-- <img src="../img/down.png" class="signingDateSortDown"> -->
			<input type="button" class="bnt_paixu signingDateSort" value="按签订日期排序" onclick="signingDateSort()">
			</form>
		</div>
		<!-- 右上角按钮 -->
		<div class="data_bnt_operate_location">
			<form  method="post" id="data_topLeft_btn">
			<input type="button" class="bnt_operate downloadModel" value="下载模板">
			<input type="button" class="bnt_operate importData" value="导入数据">
			<input type="button" class="bnt_operate outputData" value="导出表格">
			<br>
			<br> <input type="button" class="bnt_operate noupload"
				value="未上传"> <input type="button" class="bnt_operate add"
				value="增加"> <input type="button"
				class="bnt_operate delete" value="删除">
				</form>
		</div>
		
		<!--上传文件  -->
		<div class="upload_location" id="upload_location">
			<form  method="post" enctype="multipart/form-data" class="upload_form" id="upload_form">
				<div class="ipload_top"><font class="ipload_top_font">合同上传</font>
					<img src="../img/upload_close.png" class="upload_close_img">
				</div>
				<div class="upload_remark_location">文件备注:<input type="text" name="remark" class="upload_remark"></div>
				<div class="upload_file_location">合同文件:<input type="file" name="file" class="upload_file"></div>
				<input type="submit" value="上传" class="upload_btn upload_sub">
				<input type="button" value="取消" class="upload_btn upload_close">
			</form>
		</div>
		
		<!-- 导入数据 -->
		<div class="input_location">
		    <div class="input_top"></div>
			<form action="/cm/contractRecord/inputData.action" enctype="multipart/form-data" id="imput_form" method="post">
				<input type="file" name="file" class="input_file_location">
				<input type="submit" value="提交" class="input_sub">
				<input type="button" value="关闭" class="input_close">
			</form>
		</div>
		
		<!-- 新增数据 -->
		<div class="add_position" style="display: none;" id="add_position">
   		<form action='<c:url value="/contractRecord/addData.action"></c:url>' method="post" target="body">
   		<div class="contractNamePosition"><font>合同名称</font><input type="text" name="contractName" class="add_textbox"></div>
   		<div class="contractNumPosition"><font>流程编号</font><input type="text" name="contractNum" class="add_textbox"></div>
   		<div class="idPosition"><font>合同编号</font><input type="text" name="id" class="add_textbox"></div>
   		<div class="partyAPosition"><font>合同甲方</font><input type="text" name="partyA" class="add_textbox" ></div>
   		<div class="partyBPosition"><font>合同乙方</font><input type="text" name="partyB" class="add_textbox" ></div>
   		<div class="departPosition"><font>经办部门</font><select name="depart" class="add_textbox">
   			<option value="">请选择</option>
   			<c:forEach items="${depart }" var="d">
   			<option value="${d.codeName }">${d.codeName }</option>
   			</c:forEach>
   		</select></div>
   		<div class="operatorPosition"><font>经办人</font><input type="text" name="operator" class="add_textbox add_operate"></div>
   		<div class="contractAmountPosition"><font>合同金额</font><input type="text" name="contractAmount" class="add_textbox"></div>
   		<div class="contractTypePosition"><font>合同类型</font><select name="contractType" class="add_textbox">
   			<option value="">请选择</option>
   			<c:forEach items="${contractType }" var="c">
   			<option value="${c.codeName }">${c.codeName }</option>
   			</c:forEach>
   		</select></div>
   		<div class="signingDateStartPosition"><font>签约日期</font><input type="text" name="signingDate" class="add_textbox" ></div>
   		<div class="deadlineStartPosition"><font>合同期限</font><input type="text" name="deadline" class="add_textbox"></div>
   		<div class="remarkPosition"><font>备注</font><textarea rows="5" cols="55"></textarea></div>
   		<input type="submit" class="record_add" value="添加">
   		<input type="button" class="record_close" value="取消">
    </form>
   </div>
   
<script type="text/javascript">
	
	/* 排序 */
function importDateSort(){
	if($(".importDateSortUp").css("display")=="none"){
		$(".importDateSortUp").show();
		$(".importDateSortDown").hide();
		document.getElementById("sortForm").action="/cm/contractRecord/advancedSearch.action?importDateSort=desc&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}";
		document.getElementById("sortForm").submit();
	}else{
		$(".importDateSortUp").hide();
		$(".importDateSortDown").show();
		document.getElementById("sortForm").action="/cm/contractRecord/advancedSearch.action?importDateSort=asc&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}";
		document.getElementById("sortForm").submit();
	}
}

function deadlineSort(){
	if($(".deadlineSortUp").css("display")=="none"){
		$(".deadlineSortUp").css("display")=="block";
		$(".deadlineSortDown").css("display")=="none";
		document.getElementById("sortForm").action="/cm/contractRecord/advancedSearch.action?deadlineSort=desc&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}";
		document.getElementById("sortForm").submit();
	}else{
		$(".deadlineSortUp").css("display")=="none";
		$(".deadlineSortDown").css("display")=="block";
		document.getElementById("sortForm").action="/cm/contractRecord/advancedSearch.action?deadlineSort=asc&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}";
		document.getElementById("sortForm").submit();
	}
}

function signingDateSort(){
	if($(".signingDateSortUp").css("display")=="none"){
		$(".signingDateSortUp").css("display")=="block";
		$(".signingDateSortDown").css("display")=="none";
		document.getElementById("sortForm").action="/cm/contractRecord/advancedSearch.action?signingDateSort=desc&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}";
		document.getElementById("sortForm").submit();
	}else{
		$(".signingDateSortUp").css("display")=="none";
		$(".signingDateSortDown").css("display")=="block";
		document.getElementById("sortForm").action="/cm/contractRecord/advancedSearch.action?signingDateSort=asc&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}";
		document.getElementById("sortForm").submit();
	}
}

</script>
<!-- 循环表格 -->
		<form id="id_form" action="" method="post">
		<table class="table_location" id="dataTable">
			<tr>
				<th><input type="checkbox" name="checkAll" class="checkAll""></th>
				<th>序号</th>
				<th>合同编号</th>
				<th>流程编号</th>
				<th>合同名称</th>
				<th>合同甲方</th>
				<th>合同乙方</th>
				<th>合同类型</th>
				<th>签订日期</th>
				<th>合同期限</th>
				<th>导入时间</th>
				<th>合同金额</th>
				<th>经办部门</th>
				<th>经办人</th>
				<th>备注</th>
				<th>操作</th>
			<tr>
			
				<c:forEach items="${pageBean.pageRecord }" var="contractRecord" varStatus="pageindex">
					<tr>
						<td><input type="checkbox" name="id" value="${contractRecord.id }" class="check"></td>
						<td>${(pageBean.currentPage-1)*pageBean.pageSize+pageindex.index+1 }</td>
						<td>${contractRecord.id }</td>
						<td>${contractRecord.contractNum }</td>
						
						<td>${contractRecord.contractName }</td>
						<td>${contractRecord.partyA }</td>
						<td>${contractRecord.partyB}</td>
						<td>${contractRecord.contractType }</td>
						<td>${contractRecord.signingDate }</td>
						<td>${contractRecord.deadline }</td>
						<td>${contractRecord.importDate }</td>
						<td>${contractRecord.contractAmount}</td>
						<td>${contractRecord.depart }</td>
						<td>${contractRecord.operator }</td>
						<td>${contractRecord.remark }</td>
						<td>
						<form  id="operateForm" method="post">
							<select class="operate" >
									<option value="">请选择</option>
									<option value="${contractRecord.id }" onclick="upload(this.value);" id="file_upload">上传</option>
									<option value="/cm/contractRecord/download.action?id=${contractRecord.id }&type=download" onclick="download(this.value);">下载</option>
									<option value="/cm/contractRecord/download.action?id=${contractRecord.id }&type=view" onclick="view(this.value);">查看</option>
									<option value="" onclick="edit();">修改</option>
									<option value="/cm/contractRecord/deleteContractRecord.action?id=${contractRecord.id }" onclick="deleteRecord(this.value);">删除</option>
							</select>
						</form>
						</td>
					</tr>
				</c:forEach>
				
		</table>
		</form>
<script type="text/javascript">
	
/*文件上传  */
	function upload(value){
		
		$(".upload_location").show();
		var form=document.getElementById("upload_form");
		var opt = document.createElement("textarea");
	    opt.name = "id";
	    opt.value = value;
	    form.appendChild(opt);
	    $("textarea[name=id]").hide();
	    form.method="post";
	    form.action="/cm/contractRecord/upload.action";
	}
	$(".upload_sub").click(function(){
		$(".upload_form").submit();
	});
	$(".upload_close").click(function(){
		$(".upload_location").hide();
	});
	$(".upload_close_img").click(function(){
		$(".upload_location").hide();
	});
	
	
	/* 文件下载 */
	function download(value){
		document.getElementById("operateForm").action=value;
		document.getElementById("operateForm").submit();
	}
	/* 预览 */
	function view(value){
		document.getElementById("operateForm").action=value;
		document.getElementById("operateForm").submit();
	}
	
	function edit(){
		
	}
	
	function deleteRecord(value){
		document.getElementById("operateForm").action=value;
		if(confirm("确定要删除这条记录吗(将进入回收站)")){
			document.getElementById("operateForm").submit();
		}
	}
	
	
</script>
		<div class="paging">
			<c:choose>
				<c:when test="${pageBean.pageNum ==0 }">
					<font class="page_all">对不起,暂时没有数据</font>
				</c:when>
				
				<c:when test="${pageBean.pageNum ==1 }">
					<font class="page_all">1</font>
					<div class="page_all">
							共${pageBean.pageNum }页
						</div>
				</c:when>
				
				<c:when test="${pageBean.pageNum<=5&&pageBean.pageNum>1 }">
					<c:if test="${pageBean.currentPage != 1 }"><div class="page_bottomOrNext page_all"><a class="page_link pageButton" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage-1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>&lt;&lt;上一页</a></div></c:if>
					<c:if test="${pageBean.currentPage == 1 }"><div class="page_bottomOrNext page_all none" style="border: none;"></div></c:if>
						<c:choose>
							<c:when test="${pageBean.currentPage eq 1 }">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">1</a></div>
							</c:when>
							<c:otherwise>
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=1&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>1</a></div>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pageBean.pageNum>=2&&pageBean.currentPage eq 2 }">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">2</a></div>
							</c:when>
							<c:otherwise>
							<c:if test="${pageBean.pageNum>=2}">
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=2&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>2</a></div>
							</c:if>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pageBean.pageNum>=3&&pageBean.currentPage eq 3 }">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">3</a></div>
							</c:when>
							<c:otherwise>
							<c:if test="${pageBean.pageNum>=3}">
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=3&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>3</a></div>
							</c:if>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pageBean.pageNum>=4&&pageBean.currentPage eq 4}">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">4</a></div>
							</c:when>
							<c:otherwise>
							<c:if test="${pageBean.pageNum>=4}">
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=4&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>4</a></div>
							</c:if>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pageBean.pageNum>=5&&pageBean.currentPage eq 5}">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">5</a></div>
							</c:when>
							<c:otherwise>
								<c:if test="${pageBean.pageNum>=5}">
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=5&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>5</a></div>
								</c:if>
							</c:otherwise>
						</c:choose>
					<c:if test="${pageBean.currentPage != pageBean.pageNum }"><div class="page_bottomOrNext page_all"><a class="page_link pageNext" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>下一页&gt;&gt;</a></div></c:if>
					<c:if test="${pageBean.currentPage == pageBean.pageNum }"><div class="page_all" style="border: none;width: 10px;height: 23px;"></div></c:if>
					<form action='<c:url value="/contractRecord/advancedSearch.action?id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>' method="post">
						 <div class="page_all">
							共${pageBean.pageNum }页,到第<input type="text" name="currentPage" class="page_textBox">页
						</div>
						<input type="submit" class="page_sub page_all" value="确定">
					</form>
				</c:when>
				
				<c:when test="${pageBean.pageNum<pageBean.currentPage+4 }">
					<div class="page_bottomOrNext page_all"><a class="page_link pageNext" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage-1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>&lt;&lt;上一页</a> </div>
					<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=1&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>1</a></div>
					<font class="page_all dian">...</font>
						<c:choose>
							<c:when test="${pageBean.currentPage eq pageBean.pageNum-3 }">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">${pageBean.pageNum-3 }</a></div>
							</c:when>
							<c:otherwise>
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum-3 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.pageNum-3 }</a></div>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pageBean.currentPage eq pageBean.pageNum-2 }">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">${pageBean.pageNum-2 }</a></div>
							</c:when>
							<c:otherwise>
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum-2 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.pageNum-2 }</a></div>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pageBean.currentPage eq pageBean.pageNum-1 }">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">${pageBean.pageNum-1 }</a></div>
							</c:when>
							<c:otherwise>
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum-1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.pageNum-1 }</a></div>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pageBean.currentPage eq pageBean.pageNum }">
								<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">${pageBean.pageNum }</a></div>
							</c:when>
							<c:otherwise>
								<div class="page_link_div page_all left"><a class="page_link page_all" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.pageNum }</a></div>
							</c:otherwise>
						</c:choose>
					<c:if test="${pageBean.currentPage != pageBean.pageNum }"><div class="page_bottomOrNext page_all"><a class="page_link pageNext" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>下一页&gt;&gt;</a></div></c:if>
					<c:if test="${pageBean.currentPage == pageBean.pageNum }"><div class="page_all" style="border: none;width: 10px;height: 23px;"></div></c:if>
					<form action='<c:url value="/contractRecord/advancedSearch.action?id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>' method="post">
						 <div class="page_all">
							共${pageBean.pageNum }页,到第<input type="text" name="currentPage" class="page_textBox">页
						</div>
						<input type="submit" class="page_sub page_all" value="确定">
					</form>
				</c:when>
				
				<c:otherwise>
					<c:if test="${pageBean.currentPage != 1 }"><div class="page_bottomOrNext page_all"><a class="page_link pageButton" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage-1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>&lt;&lt;上一页</a></div></c:if>
					<c:if test="${pageBean.currentPage == 1 }"><div class="page_bottomOrNext page_all none" style="border: none;"></div></c:if>
					<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;"><a class="page_link"  style="color:gray;cursor: default;">${pageBean.currentPage }</a></div>
					<div class="page_link_div page_all"><a class="page_link" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.currentPage+1 }</a></div>
					<div class="page_link_div page_all"><a class="page_link" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+2 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.currentPage+2 }</a></div>
					<div class="page_link_div page_all"><a class="page_link" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+3 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.currentPage+3 }</a></div>
					<font class="page_all dian">...</font>
					<div class="page_link_div page_all left"><a class="page_link" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>${pageBean.pageNum }</a></div>
					<div class="page_bottomOrNext page_all"><a class="page_link pageNext" href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>'>下一页&gt;&gt;</a></div>
					<form action='<c:url value="/contractRecord/advancedSearch.action?id=${contractRecord.id}&contractName=${contractRecord.contractName}&partyA=${contractRecord.partyA}&partyB=${contractRecord.partyB}&contractAmount=${contractRecord.contractAmount}&operator=${contractRecord.operator}&depart=${contractRecord.depart}&contractType=${contractRecord.contractType}&signingDateStart=${signingDateStart}&signingDateEnd=${signingDateEnd}&importDateStart=${importDateStart}&importDateEnd=${importDateEnd}&deadlineStart=${deadlineStart}&deadlineEnd=${deadlineEnd}&importDateSort=${importDateSort}&signingDateSort=${signingDateSort}&deadlineSort=${deadlineSort}&notUpload=${notUpload}"></c:url>' method="post">
						 <div class="page_all">
							共${pageBean.pageNum }页,到第<input type="text" name="currentPage" class="page_textBox">页
						</div>
						<input type="submit" class="page_sub page_all" value="确定">
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	

</body>
</html>
