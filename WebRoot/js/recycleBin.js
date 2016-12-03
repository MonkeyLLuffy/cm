﻿var tableData;
var page = 1;
var rows = 10;
var sort="desc";
var condition;
$(function(){
	condition="";
	loadTable(condition);
});
function loadTable(){
	$.ajax({
    type: "POST",
    url: "/cm/contractRecord/getRecycleBinData.action",
    async: true,
    data:
    {
        page: page,
        rows: rows,
        sort:sort,
        condition:condition
    },
    success: function (result) {
        if (result && result != "") {
            var json = eval('('+result+')');
            var tableData = json.data;
            for (var i in tableData) {
                var d = tableData[i];
                var num = i;
                var id = d.ID;
                num = parseInt(num) + 1;
                $('#tableBody').append('<tr><td>'+
                    '<input type="checkbox" name="checkItems" />' +
                    '</td><td>' + num +
                    '</td><td>' + d.id +
                    '</td><td>' + d.contractName +
                    '</td><td>' + d.partyA +
                    '</td><td>' + d.partyB +
                    '</td><td>' + d.contractType +
                    '</td><td>' + d.signingDate +
                    '</td><td>' + d.deadline +
                    '</td><td>' + d.importDate +
                    '</td><td>' + d.contractAmount +
                    '</td><td>' + d.depart +
                    '</td><td>' + d.operator +
                    '</td><td>' + d.remark +
                    '</td><td>' +
                    '<a href="javascript:restore('+id+')"><span>还原</span></a></br>' +
                    '<a href="javascript:deleteById(' + id + ')"><span>彻底删除</span></a>' +
                    '</td></tr>');
            }

            //分页
           var total = json.total;
           var totalPage = json.totalPage;
           paging(total,totalPage);
        }
    }
});

}

$('#checkAll').change(function(){
	if($('#checkAll').prop("checked")){
		//全选
		 $("[name = checkItems]:checkbox").attr("checked", true);
	}else{
		//全不选
		 $("[name = checkItems]:checkbox").attr("checked", false);
		
	}
	
});
var time = 0;
function importDateSort(){
	if (time%2 == 0) {
		sort="desc";
	}
	else{
		sort = "asc";
	}
	time += 1;
	condition="importDate";//导入日期
	PageReload(page, rows);
	
}
/**
 * 还原
 * @param id
 */
function restore(id) {
}

/**
 * 批量还原
 */
function restoreAll() {
}
/*
 * 批量删除
 * */
function deleteAll(){
	var dialog = $( "#dialog-confirm-delete" ).dialog({
	      resizable: false,
	      height:180,
	      modal: true,
	      buttons: {
	        "确定删除": function() {
	        	var IDS = new Array();
	        	var n = 0;
	        	for (var i in tableData) {
	        		if($('[name=checkItems]')[i].checked==true){
	        			var d = tableData[i];
	        			IDS[n++] = d.id;
	        		}
	        	}
	        	$.ajax({
	                type: "POST",
	                url: "/cm/getRecycleBinData/deleteByIds.action",
	                data:
	                    {
	                        ids: IDS
	                },
	                async: true,
	                traditional: true,
	                success: function (result) {
	                	$( "#dialog-confirm-delete" ).dialog( "close" );
	        	        	PageReload(page,rows);//删除成功并重新加载数据
	                	
	                }
	            });
	        },
	        "取消": function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
	dialog.dialog("open");
}
/*
 * 删除
 * */
function deleteById(id){//confirm：你确定要删除吗？
	var dialog = $( "#dialog-confirm-delete" ).dialog({
	      resizable: false,
	      height:180,
	      modal: true,
	      buttons: {
	        "确定删除": function() {
	        	$.ajax({
	        	    type: "POST",
	        	    url: "/cm/getRecycleBinData/delete.action",
	        	    data:
	        	        {
	        	            id: id
	        	        },
	        	    async: true,
	        	    success: function (result) {
	                	$( "#dialog-confirm-delete" ).dialog( "close" );
		                	PageReload(page,rows);//删除成功并重新加载数据
	        	    }
	        	});
	        },
	        "取消": function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
	dialog.dialog("open");
}



// 刷新页面
function PageReload(newPage,newRows ) {	

	//清空table内容
	$('#tableBody').empty();
    page = newPage;
    rows = newRows;
    loadTable();
}
//分页
function paging(total,totalPage){
	//清空之前的分页
	$('#paging').empty();
	
   page = parseInt(page);
   total = parseInt(total);
   totalPage = parseInt(totalPage);
   rows = parseInt(rows);
   
   var html = "";
   if( totalPage == 0 ){
   	html+='<font class="page_all">对不起,暂时没有数据</font>';
   }else if( totalPage == 1 ){
   	html += '<font class="page_all">1</font><div class="page_all">共1页</div>';
   }else{ 
   	if( totalPage<=5 && totalPage>1 ){
   	if(page != 1){
   		html += '<div class="page_bottomOrNext page_all">'+
   			'<a class="page_link pageButton" href="javascript:PageReload('+(page-1)+','+rows+')">&lt;&lt;上一页</a></div>';
   	}
   	if(page == 1){
   		html += '<div class="page_bottomOrNext page_all none" style="border: none;"></div>';
   		html += '<div class="page_link_div page_all left" style="">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">1</a></div>';
   	}else{
   		html += '<div class="page_link_div page_all left">'+
   		'<a class="page_link page_all" href="javascript:PageReload(1,'+rows+')">1</a></div>';
   	}
   	
   	if(totalPage>=2&&page == 2){
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">2</a></div>';
   	}else {
   		if (totalPage >= 2){
   			html += '<div class="page_link_div page_all left">'
   				+'<a class="page_link page_all" href="javascript:PageReload(2,'+rows+')">2</a></div>';
   		}
   	}
   	if(totalPage >= 3&&page == 3){
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">3</a></div>';
   	}else{
   		if (totalPage >= 3) {
       		html += '<div class="page_link_div page_all left">'+
       		'<a class="page_link page_all" href="javascript:PageReload(3,'+rows+')">3</a></div>';
			}
   	}            	
   	if(totalPage>=4&&page == 4){
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">4</a></div>';
   	}else{
   		if (totalPage>=4) {
       		html += '<div class="page_link_div page_all left">'+
       		'<a class="page_link page_all" href="javascript:PageReload(4,'+rows+')">4</a></div>';
			}
   	}
   	
   	if(totalPage>=2&&page == 5){
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">5</a></div>';
   	}else{
   		
   		if (totalPage>=5) {
       		html += '<div class="page_link_div page_all left">'+
       		'<a class="page_link page_all" href="javascript:PageReload(5,'+rows+')">5</a></div>';
			}
   	}           	
   	
   	if (totalPage != page) {
			html += '<div class="page_bottomOrNext page_all">'+
			'<a class="page_link pageNext" href="javascript:PageReload('+(page+1)+','+rows+')">下一页&gt;&gt;</a></div></c:if>';
		}
   	if(totalPage == page){
   		html += '<div class="page_all" style="border: none;width: 10px;height: 23px;"></div></c:if>';
   	}
   	            	
   }
   else if (totalPage < page+4) {
   	
   	html += '<div class="page_bottomOrNext page_all">'+
   	'<a class="page_link pageNext" href="javascript:PageReload('+(page-1)+','+rows+')">&lt;&lt;上一页</a>'+
   	' </div><div class="page_link_div page_all left">'+
   	'<a class="page_link page_all" href="javascript:PageReload(1,'+rows+')">1</a></div><font class="page_all dian">...</font>';
   	
   	if (page == totalPage-3){
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">'+(totalPage-3)+'</a></div>';
   	}else{
   		
   		html += '<div class="page_link_div page_all left">'+
   		'<a class="page_link page_all" href="javascript:PageReload('+(totalPage-3)+','+rows+')">'+(totalPage-3)+'</a></div>';
   	}
   	
   	if (page == totalPage-2){
   		
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">'+(totalPage-2)+'</a></div>';
   	}else{
   		
   		html += '<div class="page_link_div page_all left">'+
   		'<a class="page_link page_all" href="javascript:PageReload('+(totalPage-2)+','+rows+')">'+(totalPage-2)+'</a></div>';
   	}
   	
   	if (page == totalPage-1){
   		
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">'+(totalPage-1)+'</a></div>';
   	}else{
   		
   		html += '<div class="page_link_div page_all left">'+
   		'<a class="page_link page_all" href="javascript:PageReload('+(totalPage-3)+','+rows+')">'+(totalPage-1)+'</a></div>';
   	}
   	
   	if (page == totalPage){
   		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
   		'<a class="page_link"  style="color:gray;cursor: default;">'+(totalPage)+'</a></div>';
   	}else{
   		
   		html += '<div class="page_link_div page_all left">'+
   		'<a class="page_link page_all" href="javascript:PageReload('+(totalPage)+','+rows+')">'+(totalPage)+'</a></div>';
   	}
   	
   	html += '<div class="page_bottomOrNext page_all">'+
   	'<a class="page_link pageNext" href="javascript:PageReload('+(page+1)+','+rows+')">下一页&gt;&gt;</a></div>'+
   	'<div class="page_all" style="border: none;width: 10px;height: 23px;"></div>';
	}
   else {
		if (page != 1) {
			html += '<div class="page_bottomOrNext page_all">'+
			'<a class="page_link pageButton" href="javascript:PageReload('+(page-1)+','+rows+')">&lt;&lt;上一页</a></div>';
		}
		if (page == 1) {
			html += '<div class="page_bottomOrNext page_all none" style="border: none;"></div>';
		}
		html += '<div class="page_link_div page_all left" style="background: #EFEFEF;cursor: auto;">'+
		'<a class="page_link"  style="color:gray;cursor: default;">'+page+'</a></div>'+
		'<div class="page_link_div page_all"><a class="page_link" href="javascript:PageReload('+(page+1)+','+rows+')">'+(page+1)+'</a></div>'+
		'<div class="page_link_div page_all"><a class="page_link" href="javascript:PageReload('+(page+2)+','+rows+')">'+(page+2)+'</a></div>'+
		'<div class="page_link_div page_all"><a class="page_link" href="javascript:PageReload('+(page+3)+','+rows+')">'+(page+3)+'</a></div>'+
		'<font class="page_all dian">...</font>'+
		'<div class="page_link_div page_all left"><a class="page_link" href="javascript:PageReload('+(totalPage)+','+rows+')">'+totalPage+'</a></div>'+
		'<div class="page_bottomOrNext page_all"><a class="page_link pageNext" href="javascript:PageReload('+(page+1)+','+rows+')">下一页&gt;&gt;</a></div>';
		
	}
   var formHtml = '<form method="post">'+
	'<div class="page_all">'+
	'共'+totalPage+'页,到第<input type="text" id="currentPage" class="page_textBox">页'+
	'</div>'+
	'<input onclick = "jump()" class="page_sub page_all" value="确定">'+
	'</form>';
	html += formHtml;
   }
   $('#paging').append(html);
}
function jump(){
	var value = $("#currentPage").val();
	PageReload(value,rows);
}