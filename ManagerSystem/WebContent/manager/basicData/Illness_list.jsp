<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 	%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主要内容区main</title>
<link href="<%=basePath %>css/css.css" type="text/css" rel="stylesheet" />
 <link rel="stylesheet" href="<%=basePath %>css/reset.min.css">
<link href="<%=basePath %>css/main.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath %>js/jquery-1.12.2.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.js" type="text/javascript"></script>
<link rel="shortcut icon" href="../../images/main/favicon.ico" />
<style>
body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
#searchmain{ font-size:12px;}
#search{ font-size:12px; background:#548fc9; margin:10px 10px 0 0; display:inline; width:100%; color:#FFF; float:left}
#search form span{height:40px; line-height:40px; padding:0 0px 0 10px; float:left;}
#search form input.text-word{height:24px; line-height:24px; width:180px; margin:8px 0 6px 0; padding:0 0px 0 10px; float:left; border:1px solid #FFF;}
#search form input.text-but{height:24px; line-height:24px; width:55px; background:url(<%=basePath %>images/main/list_input.jpg) no-repeat left top; border:none; cursor:pointer; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666; float:left; margin:8px 0 0 6px; display:inline;}
#search a.add{ background:url(<%=basePath %>images/main/add.jpg) no-repeat -3px 7px #548fc9; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#FFF; float:right}
#search a:hover.add{ text-decoration:underline; color:#d2e9ff;}
#main-tab{ border:1px solid #eaeaea; background:#FFF; font-size:12px;}
#main-tab th{ font-size:12px; background:url(<%=basePath %>images/main/list_bg.jpg) repeat-x; height:32px; line-height:32px;}
#main-tab td{ font-size:12px; line-height:40px;}
#main-tab td a{ font-size:12px; color:#548fc9;}
#main-tab td a:hover{color:#565656; text-decoration:underline;}
.bordertop{ border-top:1px solid #ebebeb}
.borderright{ border-right:1px solid #ebebeb}
.borderbottom{ border-bottom:1px solid #ebebeb}
.borderleft{ border-left:1px solid #ebebeb}
.gray{ color:#dbdbdb;}
td.fenye{ padding:10px 0 0 0; text-align:right;}
.bggray{ background:#f9f9f9}
</style>
<script type="text/javascript">
$(function(){
	$('#popTable').on('dblclick','td',function(){
	//$('#main-tab').find("td:eq(2)").on('dblclick',function(){
	    //console.info($(this).text());
	    //alert("行号： "+$(this).parent("tr").index()+"  列号："+$(this).index());
	    if ($(this).index() == 2 || $(this).index() == 4){
		    var IUID = $(this).parent("tr").find("td:eq(1)").text();
	    	var oldVal = $(this).text();
		    var input = "<input type='text' id='tmpId' value='" + oldVal + "' >";
		    $(this).text('');
		    $(this).append(input);
		    $('#tmpId').focus();
		    $('#tmpId').blur(function(){
		    	//丢失光标，如果新值与旧值相等不发送请求，否则发送请求
		    	if($(this).val() != oldVal){
		    		oldVal = $(this).val();
		    		//发送ajax请求，更新数据库
		    		$.ajax({
		    			type: "post",
		    			url: "BasicDataServlet?action=ajaxUpIllness",
    					data: {"newVal":oldVal, "IUID":IUID},
    					dataType: "json",
    					success: function(data){
    						var json= JSON.stringify(data);
    						alert(json);
    					}
		    		})
		    	}
		        //closest：是从当前元素开始，沿Dom树向上遍历直到找到已应用选择器的一个匹配为止。
		       $(this).closest('td').text(oldVal);
		    });
	    }
	});
	
})

function popWindow(aa){

	var i=0;
	var s="";
	<c:forEach var="item" items="${sessionScope.Illness}" varStatus="status"> 
	
	if(aa == i){
		//alert(aa+" ---  "+i+" +++  "+"${item.patientInformation.IUID}");
		s += "<table border='1' border='1' cellspacing='0' cellpadding='10' id='popTable'>";
		s += "<tr><td width='20%'>姓名</td><td width='30%'>"+"${item.patientInformation.name}"+"</td><td width='20%'>编号</td><td width='30%'>"+"${item.patientInformation.IUID}"+"</td></tr>";
		s += "<tr><td>是否吸烟</td><td>"+"${item.smoke}"+"</td><td>是否饮酒</td><td>"+"${item.drink}"+"</td></tr>";
		s += "<tr><td>嗜高盐饮食</td><td>"+"${item.highSalinity}"+"</td><td>嗜高脂饮食</td><td>"+"${item.highFat}</td></tr>";
		s += "<tr><td>嗜辛辣饮食</td><td>"+"${item.piquancy}"+"</td><td>嗜高糖饮食</td><td>"+"${item.highGlucose}"+"</td></tr>";
		s += "<tr><td>每晚睡眠情况</td><td>"+"${item.sleepQuality}"+"</td><td>糖尿病史</td><td>"+"${item.diabetes}"+"</td></tr>";
		s += "<tr><td>目前血糖控制情况</td><td>"+"${item.glucoseControl}"+"</td><td>高血压病史</td><td>"+"${item.hypertension}"+"</td></tr>";
		s += "<tr><td>高血脂病史</td><td>"+"${item.hyperlipemia}"+"</td><td>冠心病史</td><td>"+"${item.coronaryDisease}"+"</td></tr>";
		s += "<tr><td>短暂性脑缺血发作史</td><td>"+"${item.shortCerebral}"+"</td><td>脑卒中史</td><td>"+"${item.stroke}"+"</td></tr>";
		s += "<tr><td>头部外伤史</td><td>"+"${item.headInjury}"+"</td><td>既往重大生活事件史</td><td>"+"${item.majorLifeEvents}"+"</td></tr>";
		s += "<tr><td>手术史</td><td>"+"${item.operation}"+"</td><td>其他疾病史</td><td>"+"${item.elseIllness}"+"</td></tr>";
		s += "<tr><td>药物过敏史/不良反应史</td><td>"+"${item.drugAllergy}"+"</td><td>药物滥用或依赖史</td><td>"+"${item.relyOn}"+"</td></tr>";
		s += "<tr><td>本次病前日常运动习惯</td><td>"+"${item.dailyExercise}"+"</td><td>本次病前有无喝茶习惯</td><td>"+"${item.teaHabit}"+"</td></tr>";
		s += "<tr><td>本次病前有无喝咖啡习惯</td><td>"+"${item.coffeeHabit}"+"</td><td>本次病前有无饲养宠物习惯</td><td>"+"${item.keepingPets}"+"</td></tr>";
		s += "<tr><td>其他常坚持的兴趣爱好如唱歌绘画打牌等</td><td>"+"${item.hobbiesInterests}"+"</td><td>本次病前有无特殊生活习惯(如保健养生中医等)</td><td>"+"${item.mannerism}"+"</td></tr>";
		s += "</table>";
		s += "<a class='popup__close' href='#'>X</a>";
	}
   		i++;
    </c:forEach>
    $(".popup-inner").empty();
    $(".popup-inner").append(s);
    location.href = "#popup";//跳转
}

</script>
</head>
<body>
<!--main_top-->
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：患者病史管理</td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="BasicDataServlet?action=conditionStudents">
	         <span>身份证：</span>
	         <input type="text" id="idCard" name="idCard" value="" class="text-word">
	         <span>姓名：</span>
	         <input type="text" id="schoolName" name="schoolName" value="" class="text-word">
	         <input name="submit" type="submit" value="查询" class="text-but">
	         </form>
         </td>
  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:150px;"><a href="basicData/Illness_import.jsp" target="mainFrame" onFocus="this.blur()" class="add">导入患者病史</a></td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr>
        <th align="center" valign="middle" class="borderright">患者姓名</th>
        <th align="center" valign="middle" class="borderright">患者ID</th>
        <!-- <th align="center" valign="middle" class="borderright">吸烟</th>-->
        <th align="center" valign="middle">操作</th>
      </tr>
      <c:forEach var="item" items="${sessionScope.Illness}" varStatus="status">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
      		
	        <td align="center" valign="middle" class="borderright borderbottom">${item.patientInformation.name }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.patientInformation.IUID}</td>
        	<td align="center" valign="middle" class="borderbottom">
        	<a  href="javascript:popWindow(${status.index})" >编辑</a>
        	</td>
      </tr></c:forEach>
      </table></td>
    </tr>
  <!--  
  <tr>
    <td align="left" valign="top" class="fenye">11 条数据 1/1 页&nbsp;&nbsp;<a href="#" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;<a href="#" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;<a href="#" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;<a href="#" target="mainFrame" onFocus="this.blur()">尾页</a></td>
  </tr>-->
</table>
<div class="popup" id="popup">
    <div class="popup-inner" style="background-color: #EEEEEE;text-align: center">
    
    </div>
  </div>
</body>
</html>