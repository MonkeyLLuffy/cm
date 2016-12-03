
$(function(){
	
	loadYearData();
	var timeType = $("input[name='Sex']");
	timeType[0].onchange=function(){
		if (this.checked) {
				//获取年的数据
			loadYearData();
			
		}
		};
		
		timeType[1].onchange=function(){
			if (this.checked) {
				//获取月的数据
				loadMonthData();
			}
		};
	
	
	
	
	
	
});
function loadMonthData(){
	var x;
	var y;
	//默认为年
	var statisticalData,ranking,total,closingDate;
	var com_statisData,com_ranking,com_total;
	//第一次加载默认为年
	$.ajax({
        type: "POST",
        url: "/cm/contractRecord/monthContractCount.action",
        async: true,
        traditional: true,
        success: function (result) {
        	var json = eval('('+result+')');
        	x = json.x;
        	y = json.y;
        	statisticalData = json.statisticalData;
        	com_statisData = json.com_statisData;
        	ranking = statisticalData.ranking;
        	total = statisticalData.total;
        	closingDate = statisticalData.closingDate;
        	
        	com_ranking = com_statisData.ranking;
        	com_total = com_statisData.total;
        	//今年的数据，ranking_left
        	var ranking_left_html = '<span style="color:yellow;font-size:18pt;"> 第'+ranking+'名</span></br>';
        	ranking_left_html += '<span style="color:#FFFFFF;"> 贵公司签订的合同总数已达</br><span style="font-size:24pt;">'+total+'</span>份</span></br>';
        	ranking_left_html += '<span style="background-color:#B1ECFD;border-radius:10px;padding:2px;">截至'+closingDate+'</span></br>';
        	ranking_left_html += '';
        	
        	var ranking_bottom_html = '<p style=" white-space: nowrap;position: absolute;top: 230px;left: 190px;background-color:#3DCFFA;color:#FFFFFF;">环比上月</p>';
        	ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 50px;color:#FFFFFF;">';
        	if (ranking < com_ranking ) {
				ranking_bottom_html += '<img src="../img/xia_jiantou.png" style="width:15px;height:15px;">'+(com_ranking-ranking)+'<p>名次</p></div>';
			}else{
				ranking_bottom_html += '<img src="../img/shang_jiantou.png" style="width:15px;height:15px;">'+(ranking-com_ranking)+' <p>名次</p></div>';
			}
        	if (total >= com_total) {
            	ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 190px;color:#FFFFFF;"> +'+(total-com_total)+' <p>份</p></div>';
        	}else{
        		ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 190px;color:#FFFFFF;"> -'+(com_total-total)+' <p>份</p></div>';
        	}
        	if (total>=com_total) {
        		ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 330px;color:#FFFFFF;"><img src="../img/shang_jiantou.png" style="width:15px;height:15px;">'+((total/com_total)-1)*100+'%<p>增幅</p></div>';
			}else{
				ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 330px;color:#FFFFFF;"><img src="../img/xia_jiantou.png" style="width:15px;height:15px;">'+(1-(total/com_total))*100+'%<p>减幅</p></div>';
			}
        	$('#ranking_left').empty();
        	$('#ranking_bottom').empty();
        	$('#ranking_left').append(ranking_left_html);
        	$("#ranking_bottom").append(ranking_bottom_html);
        	
        	lineMap(x,y);
        	
        }
    });
}

function loadYearData(){
	var x;
	var y;
	//默认为年
	var statisticalData,ranking,total,closingDate;
	var com_statisData,com_ranking,com_total;
	//第一次加载默认为年
	$.ajax({
        type: "POST",
        url: "/cm/contractRecord/yearContractCount.action",
        async: true,
        traditional: true,
        success: function (result) {
        	var json = eval('('+result+')');
        	x = json.x;
        	y = json.y;
        	statisticalData = json.statisticalData;
        	com_statisData = json.com_statisData;
        	ranking = statisticalData.ranking;
        	total = statisticalData.total;
        	closingDate = statisticalData.closingDate;
        	
        	com_ranking = com_statisData.ranking;
        	com_total = com_statisData.total;
        	//今年的数据，ranking_left
        	var ranking_left_html = '<span style="color:yellow;font-size:18pt;"> 第'+ranking+'名</span></br>';
        	ranking_left_html += '<span style="color:#FFFFFF;"> 贵公司签订的合同总数已达</br><span style="font-size:24pt;">'+total+'</span>份</span></br>';
        	ranking_left_html += '<span style="background-color:#B1ECFD;border-radius:10px;padding:2px;">截至'+closingDate+'</span></br>';
        	ranking_left_html += '';
        	
        	var ranking_bottom_html = '<p style=" white-space: nowrap;position: absolute;top: 230px;left: 190px;background-color:#3DCFFA;color:#FFFFFF;">环比去年 </p>';
        	ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 50px;color:#FFFFFF;">';
        	if (ranking < com_ranking ) {
				ranking_bottom_html += '<img src="../img/xia_jiantou.png" style="width:15px;height:15px;">'+(com_ranking - ranking)+'<p>名次</p></div>';
			}else{
				ranking_bottom_html += '<img src="../img/shang_jiantou.png" style="width:15px;height:15px;">'+(ranking - com_ranking)+' <p>名次</p></div>';
			}
        	if (total >= com_total) {
            	ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 190px;color:#FFFFFF;"> +'+(total - com_total)+' <p>份</p></div>';
        	}else{
        		ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 190px;color:#FFFFFF;"> -'+(com_total - total)+' <p>份</p></div>';
        	}
        	if (total>=com_total) {
        		ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 330px;color:#FFFFFF;"><img src="../img/shang_jiantou.png" style="width:15px;height:15px;">'+((total/com_total)-1)*100+'%<p>增幅</p></div>';
			}else{
				ranking_bottom_html += '<div style="position: absolute;top: 250px;left: 330px;color:#FFFFFF;"><img src="../img/xia_jiantou.png" style="width:15px;height:15px;">'+(1-(total/com_total))*100+'%<p>减幅</p></div>';
			}
        	$('#ranking_left').empty();
        	$('#ranking_bottom').empty();
        	$('#ranking_left').append(ranking_left_html);
        	$("#ranking_bottom").append(ranking_bottom_html);
        	
        	lineMap(x,y);
        	
        }
    });
}


function onCompanyChange(value){//公司ID
	alert(value);
}
function lineMap(x,y){
	option = {
		    title: {
		        text: '每年/每月签订合同总数'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    color:["#3DCFFA"],
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            interval:0,
		            data : x
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'年度合同签订份数',
		            type:'line',
		            stack: '总量',
		            symbolSize:7,
		            showAllSymbol:true,
		            areaStyle: {normal: {}},
		            data:y
		        }
		    ]
		};


	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('div_lineMap'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);	
	
}