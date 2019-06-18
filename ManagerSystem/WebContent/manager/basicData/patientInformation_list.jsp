<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主要内容区main</title>
<link href="<%=basePath %>css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath %>css/reset.min.css">
<link href="<%=basePath %>css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="../../images/main/favicon.ico" />
<script src="<%=basePath %>js/jquery-1.12.2.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.js" type="text/javascript"></script>
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
td.fenye{ padding:10px 0 0 0; text-align:center;}
.bggray{ background:#f9f9f9}
</style>
<script type="text/javascript">
function popWindow(aa){
	var i=0;	
	var s="";
<c:forEach var="item" items="${sessionScope.PatientInformation.pageData}" varStatus="status"> 
	
	if(aa == i){
		s = "";
		s += "<table width='99%' border='1' border='1' cellspacing='0' cellpadding='10' id='popTable' >";
		s += "<tr><td>姓名</td><td width='30%'>"+"${item.name}"+"</td><td width='20%'>编号</td><td width='30%'>"+"${item.IUID}"+"</td></tr>";
		s += "<tr><td>性别</td><td>"+"${item.sex}"+"</td><td>出生日期</td><td>"+"${item.birthday}"+"</td></tr>";
		s += "<tr><td>年龄</td><td>"+"${item.age}"+"</td><td>身高</td><td>"+"${item.height}"+"</td></tr>";
		s += "<tr><td>体重</td><td>"+"${item.weight}"+"</td><td>户口所在</td><td>"+"${item.registeredResidence}"+"</td></tr>";
		s += "<tr><td>电话</td><td>"+"${item.tel}"+"</td><td>地址是否和户口相同</td><td>"+"${item.address}"+"</td></tr>";
		s += "<tr><td>常住地址</td><td>"+"${item.permanentAddress}"+"</td><td>民族</td><td>"+"${item.nation}"+"</td></tr>";
		s += "<tr><td>文化水平</td><td>"+"${item.culturalLevel}"+"</td><td>职业</td><td>"+"${item.occupation}"+"</td></tr>";
		s += "<tr><td>工作状况</td><td>"+"${item.workingStaus}"+"</td><td>工作性质</td><td>"+"${item.jobNature}"+"</td></tr>";
		s += "<tr><td>婚姻状况</td><td>"+"${item.maritalStatus}"+"</td><td>有无子女</td><td>"+"${item.isChild}"+"</td></tr>";
		s += "<tr><td>生活方式</td><td>"+"${item.lifeStyle}"+"</td><td>独立程度</td><td>"+"${item.independece}"+"</td></tr>";
		s += "<tr><td>医疗保险</td><td>"+"${item.medicalInsurance}"+"</td><td>其他</td><td>"+"${item.other}"+"</td></tr>";
		s += "<tr><td>宗教信仰</td><td>"+"${item.religiousBelief}"+"</td><td></td><td>"+""+"</td></tr>";
		s += "</table>";
		s += "<a class='popup__close' href='#'>X</a>";
	}
   		i++;
    </c:forEach>
    $(".popup-inner").empty();
    $(".popup-inner").append(s);
    location.href = "#popup";//跳转
}

var isCheckAll = false;
function checkAll() {
    	if (isCheckAll) {				
    		$("input[type='checkbox']").each(function() {		
    			this.checked = false;				
    		});				
    		isCheckAll = false;			
    	} else {				
    		$("input[type='checkbox']").each(function() {
    			this.checked = true;				
    			});				
    		isCheckAll = true;			
    	}  	    	
}
//单选 设置name=id
function userCheck(ths) {
    if (ths.checked == false) {
        $("input[name=all]:checkbox").prop('checked', false);
    }
    else {
        var count = $("input[name='id']:checkbox:checked").length;
        if (count == $("input[name='id']:checkbox").length) {
            $("input[name=all]:checkbox").prop("checked", true);
        }
    }
}

function updataStudent(th){
	//th: IUID
	
	var str = $("#upstudent").val() == "编辑" ? "确定" : "编辑";
	$("#upstudent").val(str);
	
	
	$("#upstudent").parent().siblings("td:eq(2)").each(function(){
		obj_text = $(this).find("input:text");    
        if(!obj_text.length)   
            $(this).html("<input type='text' value='"+$(this).text()+"'>");
        else   
            $(this).html(obj_text.val()); 
	})

}

$(function(){
	$('#main-tab').on('dblclick','td',function(){
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
		    			url: "BasicDataServlet?action=ajaxUpStudents",
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

</script>
</head>
<body>
<!--main_top-->
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：患者管理</td>
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
  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:150px;"><a href="basicData/patientInformation_import.jsp" target="mainFrame" onFocus="this.blur()" class="add">导入患者</a></td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr>
      <th align="center" valign="middle" class="borderright">
      <input type="checkbox" name="all" onclick="checkAll()" />&nbsp;&nbsp;全选</th>
      <th align="center" valign="middle" class="borderright">序号</th>
        <th align="center" valign="middle" class="borderright">患者ID</th>
        <th align="center" valign="middle" class="borderright">姓名</th>
        <th align="center" valign="middle" class="borderright">性别</th>
        <th align="center" valign="middle" class="borderright">出生日期</th>
        <th align="center" valign="middle" class="borderright">年龄</th>
        <th align="center" valign="middle">操作</th>
      </tr>
      <c:forEach var="item" items="${sessionScope.PatientInformation.pageData}" varStatus="status">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="center" valign="middle" class="borderright borderbottom">
      			<input type="checkbox" name="id" value="${item.IUID }" onclick=usercheck(this)/>
      		</td>
      		<td align="center" valign="middle" class="borderright borderbottom">${status.index+1 }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.IUID }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.name }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.sex }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.birthday }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.age }</td>
        	<td align="center" valign="middle" class="borderbottom">
	        	<a  href="javascript:popWindow(${status.index})" >编辑</a>

	        	<!--<span class="gray">&nbsp;|&nbsp;</span>
	        	<a href="add.html" target="mainFrame" onFocus="this.blur()" class="add">删除</a>
	        	-->
        	</td>
		
      </tr></c:forEach>
      </table></td>
    </tr>
  <tr>
    <td align="center" valign="middle" class="fenye">
    	当前&nbsp;${sessionScope.PatientInformation.currentPage }&nbsp;/&nbsp;${sessionScope.PatientInformation.totalPage }&nbsp;页   &nbsp;&nbsp;
    	<a href="BasicDataServlet?action=PF&currentPage=1" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
    	<a href="BasicDataServlet?action=PF&currentPage=${sessionScope.PatientInformation.currentPage-1}" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
    	<a href="BasicDataServlet?action=PF&currentPage=${sessionScope.PatientInformation.currentPage+1}" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
    	<a href="BasicDataServlet?action=PF&currentPage=${sessionScope.PatientInformation.totalPage}" target="mainFrame" onFocus="this.blur()">尾页</a>
    </td>
  </tr>
</table>
	<div class="popup" id="popup">
    <div class="popup-inner" style="background-color: #EEEEEE;text-align: center">
    
    </div>
  </div>
</body>
</html>