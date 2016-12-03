<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>

<c:choose><c:when test="${type eq 'download' }">下载</c:when><c:when test="${type eq upload }">上传</c:when><c:when test="${type eq input }">导入</c:when><c:when test="${type eq output }">导出</c:when><c:when test="${type eq edit }">修改</c:when></c:choose>记录
</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/downloadHistory.js"></script>

<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../css/history_paging.css">
<link rel="stylesheet" type="text/css" href="../css/history_table.css">



</head>

<body style="margin: 0px;OVERFLOW-Y: hidden; OVERFLOW-X: hidden;">

	<div class="history_background">
		<font class="history_font_head_font_top">个人中心&gt;历史记录&gt;<c:choose><c:when test="${type eq 'download' }">下载</c:when><c:when test="${type eq 'upload' }">上传</c:when><c:when test="${type eq 'input' }">导入</c:when><c:when test="${type eq 'output' }">导出</c:when><c:when test="${type eq 'edit' }">修改</c:when></c:choose>记录</font>

		<div class="history_textBox_location">

			<form action="/cm/history/downHistory.action?type=${type }"
				method="post" id="history_search_form">
				<img src="../img/search.png" class="history_img id_search">
				<img src="../img/search.png" class="history_img contractName_search">
				<img src="../img/search.png" class="history_img user_search"> 
				<img src="../img/search.png" class="history_img userType_search"> 
				<div class="history_search_textBox_location"><font>流程编号:<input type="text" name="id" class="history_search_textBox"></font></div>
				<div class="history_search_textBox_location"><font>合同名称:<input type="text" name="contractName" class="history_search_textBox"></font> </div>
				<div class="history_search_textBox_location"><font><c:choose><c:when test="${type eq 'download' }">下载</c:when><c:when test="${type eq 'upload' }">上传</c:when><c:when test="${type eq 'input' }">导入</c:when><c:when test="${type eq 'output' }">导出</c:when><c:when test="${type eq 'edit' }">修改</c:when></c:choose>用户:<input type="text" name="user" class="history_search_textBox"></font> </div>
				<div class="history_search_textBox_location"><font>用户身份:<input type="text" name="userType" class="history_search_textBox userType"></font> </div>
			</form>
		</div>
		
		<font class="history_font_head_font_button"><c:choose><c:when test="${type eq 'download' }">下载</c:when><c:when test="${type eq 'upload' }">上传</c:when><c:when test="${type eq 'input' }">导入</c:when><c:when test="${type eq 'output' }">导出</c:when><c:when test="${type eq 'edit' }">修改</c:when></c:choose>记录</font>
	</div>


	<table id="hisTable">                             
		<tr>
			<th>序号</th>
			<th>流程编号</th>
			<c:choose>
			<c:when test="${type eq 'edit' }">
				<th>修改项</th>
			</c:when>
			<c:otherwise>
				<th>合同名称</th>
				<th>合同甲方</th>
				<th>合同乙方</th>
				<th>合同类型</th>
			</c:otherwise>
			
			</c:choose>
			<th><c:choose><c:when test="${type eq 'download' }">下载</c:when><c:when test="${type eq 'upload' }">上传</c:when><c:when test="${type eq 'input' }">导入</c:when><c:when test="${type eq 'output' }">导出</c:when><c:when test="${type eq 'edit' }">修改</c:when></c:choose>用户</th>
			<th>姓名</th>
			<th>用户身份</th>
			<th><c:choose><c:when test="${type eq 'download' }">下载</c:when><c:when test="${type eq 'upload' }">上传</c:when><c:when test="${type eq 'input' }">导入</c:when><c:when test="${type eq 'output' }">导出</c:when><c:when test="${type eq 'edit' }">修改</c:when></c:choose>时间</th>
		</tr>
		<c:forEach items="${pageBean.pageRecord }" var="hisRecord"
			varStatus="his">
			<tr>
				<td>${(pageBean.currentPage-1)*pageBean.pageSize+his.index+1 }</td>
				<td>${hisRecord.id }</td>
				<c:choose>
				<c:when test="${type eq 'edit' }">
					<td>${hisRecord.operate }</td>
				</c:when>
				<c:otherwise>
					<td>${hisRecord.contractName }</td>
					<td>${hisRecord.partyA }</td>
					<td>${hisRecord.partyB }</td>
					<td>${hisRecord.contractType }</td>
				</c:otherwise>
				</c:choose>
				<td>${hisRecord.username }</td>
				<td>${hisRecord.trueName }</td>
				<td><c:choose>
						<c:when test="${hisRecord.isAdmin }">
    				管理员
    			</c:when>
						<c:otherwise>
    			普通用户
    			</c:otherwise>
					</c:choose></td>
				<td>${hisRecord.time }</td>
			</tr>
		</c:forEach>
	</table>

	<div class="paging">
		<c:choose>
			<c:when test="${pageBean.pageNum ==0 }">
				<font class="page_all">对不起,暂时没有数据</font>
			</c:when>

			<c:when test="${pageBean.pageNum ==1 }">
				<font class="page_all">1</font>
				<div class="page_all">共${pageBean.pageNum }页</div>
			</c:when>

			<c:when test="${pageBean.pageNum<=5&&pageBean.pageNum>1 }">
				<c:if test="${pageBean.currentPage != 1 }">
					<div class="page_bottomOrNext page_all">
						<a class="page_link pageButton"
							href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage-1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>&lt;&lt;上一页</a>
					</div>
				</c:if>
				<c:if test="${pageBean.currentPage == 1 }">
					<div class="page_bottomOrNext page_all none" style="border: none;"></div>
				</c:if>
				<c:choose>
					<c:when test="${pageBean.currentPage eq 1 }">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">1</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="page_link_div page_all left">
							<a class="page_link page_all"
								href='<c:url value="/contractRecord/advancedSearch.action?currentPage=1&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>1</a>
						</div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.pageNum>=2&&pageBean.currentPage eq 2 }">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">2</a>
						</div>
					</c:when>
					<c:otherwise>
						<c:if test="${pageBean.pageNum>=2}">
							<div class="page_link_div page_all left">
								<a class="page_link page_all"
									href='<c:url value="/contractRecord/advancedSearch.action?currentPage=2&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>2</a>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.pageNum>=3&&pageBean.currentPage eq 3 }">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">3</a>
						</div>
					</c:when>
					<c:otherwise>
						<c:if test="${pageBean.pageNum>=3}">
							<div class="page_link_div page_all left">
								<a class="page_link page_all"
									href='<c:url value="/contractRecord/advancedSearch.action?currentPage=3&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>3</a>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.pageNum>=4&&pageBean.currentPage eq 4}">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">4</a>
						</div>
					</c:when>
					<c:otherwise>
						<c:if test="${pageBean.pageNum>=4}">
							<div class="page_link_div page_all left">
								<a class="page_link page_all"
									href='<c:url value="/contractRecord/advancedSearch.action?currentPage=4&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>4</a>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.pageNum>=5&&pageBean.currentPage eq 5}">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">5</a>
						</div>
					</c:when>
					<c:otherwise>
						<c:if test="${pageBean.pageNum>=5}">
							<div class="page_link_div page_all left">
								<a class="page_link page_all"
									href='<c:url value="/contractRecord/advancedSearch.action?currentPage=5&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>5</a>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<c:if test="${pageBean.currentPage != pageBean.pageNum }">
					<div class="page_bottomOrNext page_all">
						<a class="page_link pageNext"
							href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>下一页&gt;&gt;</a>
					</div>
				</c:if>
				<c:if test="${pageBean.currentPage == pageBean.pageNum }">
					<div class="page_all"
						style="border: none;width: 10px;height: 23px;"></div>
				</c:if>
				<form
					action='<c:url value="/contractRecord/advancedSearch.action?type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'
					method="post">
					<div class="page_all">
						共${pageBean.pageNum }页,到第<input type="text" name="currentPage"
							class="page_textBox">页
					</div>
					<input type="submit" class="page_sub page_all" value="确定">
				</form>
			</c:when>

			<c:when test="${pageBean.pageNum<pageBean.currentPage+4 }">
				<div class="page_bottomOrNext page_all">
					<a class="page_link pageNext"
						href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage-1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>&lt;&lt;上一页</a>
				</div>
				<div class="page_link_div page_all left">
					<a class="page_link page_all"
						href='<c:url value="/contractRecord/advancedSearch.action?currentPage=1&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>1</a>
				</div>
				<font class="page_all dian">...</font>
				<c:choose>
					<c:when test="${pageBean.currentPage eq pageBean.pageNum-3 }">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">${pageBean.pageNum-3
								}</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="page_link_div page_all left">
							<a class="page_link page_all"
								href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum-3 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.pageNum-3
								}</a>
						</div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.currentPage eq pageBean.pageNum-2 }">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">${pageBean.pageNum-2}</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="page_link_div page_all left">
							<a class="page_link page_all"
								href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum-2 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.pageNum-2
								}</a>
						</div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.currentPage eq pageBean.pageNum-1 }">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">${pageBean.pageNum-1}</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="page_link_div page_all left">
							<a class="page_link page_all"
								href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum-1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.pageNum-1
								}</a>
						</div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.currentPage eq pageBean.pageNum }">
						<div class="page_link_div page_all left"
							style="background: #EFEFEF;cursor: auto;">
							<a class="page_link" style="color:gray;cursor: default;">${pageBean.pageNum}</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="page_link_div page_all left">
							<a class="page_link page_all"
								href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.pageNum
								}</a>
						</div>
					</c:otherwise>
				</c:choose>
				<c:if test="${pageBean.currentPage != pageBean.pageNum }">
					<div class="page_bottomOrNext page_all">
						<a class="page_link pageNext"
							href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>下一页&gt;&gt;</a>
					</div>
				</c:if>
				<c:if test="${pageBean.currentPage == pageBean.pageNum }">
					<div class="page_all"
						style="border: none;width: 10px;height: 23px;"></div>
				</c:if>
				<form
					action='<c:url value="/contractRecord/advancedSearch.action?type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'
					method="post">
					<div class="page_all">
						共${pageBean.pageNum }页,到第<input type="text" name="currentPage"
							class="page_textBox">页
					</div>
					<input type="submit" class="page_sub page_all" value="确定">
				</form>
			</c:when>

			<c:otherwise>
				<c:if test="${pageBean.currentPage != 1 }">
					<div class="page_bottomOrNext page_all">
						<a class="page_link pageButton"
							href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage-1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>&lt;&lt;上一页</a>
					</div>
				</c:if>
				<c:if test="${pageBean.currentPage == 1 }">
					<div class="page_bottomOrNext page_all none" style="border: none;"></div>
				</c:if>
				<div class="page_link_div page_all left"
					style="background: #EFEFEF;cursor: auto;">
					<a class="page_link" style="color:gray;cursor: default;">${pageBean.currentPage}</a>
				</div>
				<div class="page_link_div page_all">
					<a class="page_link"
						href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.currentPage+1
						}</a>
				</div>
				<div class="page_link_div page_all">
					<a class="page_link"
						href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+2 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.currentPage+2
						}</a>
				</div>
				<div class="page_link_div page_all">
					<a class="page_link"
						href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+3 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.currentPage+3
						}</a>
				</div>
				<font class="page_all dian">...</font>
				<div class="page_link_div page_all left">
					<a class="page_link"
						href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.pageNum }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>${pageBean.pageNum
						}</a>
				</div>
				<div class="page_bottomOrNext page_all">
					<a class="page_link pageNext"
						href='<c:url value="/contractRecord/advancedSearch.action?currentPage=${pageBean.currentPage+1 }&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'>下一页&gt;&gt;</a>
				</div>
				<form
					action='<c:url value="/contractRecord/advancedSearch.action?type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}"></c:url>'
					method="post">
					<div class="page_all">
						共${pageBean.pageNum }页,到第<input type="text" name="currentPage"
							class="page_textBox">页
					</div>
					<input type="submit" class="page_sub page_all" value="确定">
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
