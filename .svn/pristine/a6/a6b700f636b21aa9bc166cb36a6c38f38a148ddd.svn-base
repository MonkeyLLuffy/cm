var tableData;
var page = 1;
var rows = 10;
var name;
$(function(){
	name="";
	loadTable("");
});
function loadTable(name){
$.ajax({
    type: "POST",
    url: "/cm/user/getUserWithPaging.action",
    data:
        {
            page: page,
            rows: rows,
            condition:name
    },
    async: true,
    success: function (result) {
        if (result && result != "") {
            var json = eval('('+result+')');
            tableData = json.data;
            for (var i in tableData) {
                var d = tableData[i];
                var num = i;
                num = parseInt(num) + 1;
                $('#tableBody').append('<tr><td>' +
                    '<input type="checkbox" name="checkItems" />' +
                    '</td><td>' + num +
                    '</td><td>' + d.USERNAME +
                    '</td><td>' + d.ISADMIN +
                    '</td><td>' + d.TRUENAME +
                    '</td><td>' + d.DEPARTMENT +
                    '</td><td>' + d.TELEPHONE +
                    '</td><td>' + d.EMAIL +
                    '</td><td>' +
                    '<a href="/cm/user/jumpSetRole.action?id='+d.ID+'"><span>角色选择</span></a></br>' +
                    '<a href="javascript:update(' + num + ')"><span>修改</span></a></br>' +
                    '<a href="javascript:resetPassword(' + d.ID + ')"><span>重置密码</span></a>' +
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
function search(){
	name = $("#input_user").val();
	PageReload(page,rows);//删除成功并重新加载数据
}

function update(i) {
	var itemData = tableData[i];
	var id = itemData.ID;
	$("#username").val(itemData.USERNAME);
	$("#trueName").val(itemData.TRUENAME);
	$("#department").val(itemData.DEPARTMENT);
	$("#email").val(itemData.EMAIL);
	$("#telephone").val(itemData.TELEPHONE);
	var dialog = $("#dialog-form").dialog({
	    autoOpen: false,
	    height: 500,
	    width: 300,
	    modal: true,
	    buttons: {
	        "修改": function () {
	        	var username = $("#username").val();
	        	var trueName = $("#trueName").val();
	        	var department = $("#department").val();
	        	var email = $("#email").val();
	        	var telephone = $("#telephone").val();
	        	//表单验证用失败了，全部手动验证
	        	if (username.length == 0) {
	        		$("#for_username")
					.append( "<font color='red'>(用户名必填)</font>" );
	        		return;
				}else{
					$("#for_username").empty();
					$("#for_username")
					.append("用户名");
				}
	        	
	        	if (trueName.length == 0) {
	        		$("#for_trueName")
					.append( "<font color='red'>(真实名字必填)</font>" );
	        		return;
				}else{
					$("#for_trueName").empty();
					$("#for_trueName")
					.append("真实名字");
				}
	        	if (telephone.length == 0) {
	        		$("#for_telephone")
					.append( "<font color='red'>(电话号码必填)</font>" );
	        		return;
				}else{
					$("#for_telephone").empty();
					$("#for_telephone")
					.append("电话号码");
				}
	        	
	        	
	        	$.ajax({
	        	    type: "POST",
	        	    url: "/cm/user/update.action",
	        	    data:
	        	        {
	        	            id: id,
	        	            username:username,
	        	            trueName:trueName,
	        	            department:department,
	        	            email:email,
	        	            telephone:telephone
	        	        },
	        	    async: true,
	        	    success: function (result) {
	        	        if (result && result != "") {
	        	        	$("#dialog-form").dialog("close");
	        	        }
	        	    }
	        	});
	            $(this).dialog("close");

	        },
	        "取消": function () {
	            $(this).dialog("close");
	        }
	    },
	    close: function () {
	        $(this).dialog("close");
	    }
	});
	dialog.dialog("open");
}


function add() {
	$("#username").val("");
	$("#trueName").val("");
	$("#departmentName").val("");
	$("#telephone").val("");
	$("#email").val("");
	$("#telephone").val("");
	var dialog = $("#dialog-form").dialog({
	    autoOpen: false,
	    height: 500,
	    width: 300,
	    modal: true,
	    title:"新增用户",
	    buttons: {
	        "确定": function () {
	        	var username = $("#username").val();
	        	var trueName = $("#trueName").val();
	        	var departmentName = $("#departmentName").val();
	        	var telephone = $("#telephone").val();
	        	var email = $("#email").val();
	        	var telephone = $("#telephone").val();

	        	if (username.length == 0) {
	        		$("#for_username")
					.append( "<font color='red'>(用户名必填)</font>" );
	        		return;
				}else{
					$("#for_username").empty();
					$("#for_username")
					.append("用户名");
				}
	        	
	        	if (trueName.length == 0) {
	        		$("#for_trueName")
					.append( "<font color='red'>(真实名字必填)</font>" );
	        		return;
				}else{
					$("#for_trueName").empty();
					$("#for_trueName")
					.append("真实名字");
				}
	        	if (telephone.length == 0) {
	        		$("#for_telephone")
					.append( "<font color='red'>(电话号码必填)</font>" );
	        		return;
				}else{
					$("#for_telephone").empty();
					$("#for_telephone")
					.append("电话号码");
				}
	        	$.ajax({
	        	    type: "POST",
	        	    url: "/cm/user/add.action",
	        	    data:
	        	        {
	        	    	name:name,
	        	    	trueName:trueName,
	        	    	departmentName:departmentName,
	        	    	telephone:telephone,
	        	    	email:email,
	        	    	telephone:telephone,
	        	    	remark:"备注"
	        	        },
	        	    async: true,
	        	    success: function (result) {
	        	        if (result && result != "") {
	        	            $("#dialog-form").dialog("close");
	        	        	PageReload(page,rows);//删除成功并重新加载数据
	        	        }
	        	    }
	        	});

	        },
	        "取消": function () {
	            $("#dialog-form").dialog("close");
	        }
	    },
	    close: function () {
	        $(this).dialog("close");
	    }
	});
	$("#dialog-form").dialog("open");
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


/*
 * 重置密码
 * */
function resetPassword(id) {
	var dialog = $( "#dialog-confirm-reset" ).dialog({
	      resizable: false,
	      height:160,
	      modal: true,
	      buttons: {
	        "确定": function() {
	        	$.ajax({
	        	    type: "POST",
	        	    url: "/cm/user/resetPassword.action",
	        	    data:
	        	        {
	        	            id: id
	        	        },
	        	    async: true,
	        	    success: function (result) {
	                	$( "#dialog-confirm-reset" ).dialog( "close" );
		                	PageReload(page,rows);//成功并重新加载数据
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
     * 批量删除
     * */
    function deleteAll(){
    	var dialog = $( "#dialog-confirm-delete" ).dialog({
    	      resizable: false,
    	      height:180,
    	      modal: true,
    	      modal: true,
    		  title:"删除这些用户吗",
    	      buttons: {
    	        "确定删除": function() {
    	        	var IDS = new Array();
    	        	var n = 0;
    	        	for (var i in tableData) {
    	        		if($('[name=checkItems]')[i].checked==true){
    	        			var d = tableData[i];
    	        			IDS[n++] = d.ID;
    	        		}
    	        	}
    	        	$.ajax({
    	                type: "POST",
    	                url: "/cm/user/deleteByIds.action",
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
    	      height:140,
    	      modal: true,
    	      buttons: {
    	        "确定删除": function() {
    	        	$.ajax({
    	        	    type: "POST",
    	        	    url: "/cm/user/delete.action",
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
     loadTable(name);
     $("#input_user").val(name)

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
    	html += '<font class="page_all">1 </font><div class="page_all">共1页</div>';
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