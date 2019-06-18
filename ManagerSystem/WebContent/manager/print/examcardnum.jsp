<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=basePath %>css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath %>css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="../../images/main/favicon.ico" />
<script src="<%=basePath %>js/jquery-1.12.2.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.print-preview.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.PrintArea.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.jqprint-0.3.js" type="text/javascript"></script>
<style>
body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
#searchmain{ font-size:14px;}
#smain a.add{ no-repeat -3px 7px #548fc9; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#548fc9; }
#smain a:hover.add{ text-decoration:underline; color:#d2e9ff;}
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
$(function(){
	$("#js-export").click(function(){
		alert("aa");
	    $.ajax({
			type: "post",
			url: "../ExportBooksServlet?action=ExportExamCardNumBySchool",
			data: null,
			dataType: "json",
			success: function(data){
				var rs = data.success;
				//var json= JSON.stringify(data);
				
			}
		});
	})
})


$(function(){ 
	 $("#print_btn").click(function(){ 
	 $("#main-tab").jqprint({
		 debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
	     importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
	     printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
	     operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true

	 })
	 }); 
	 });
 
</script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
  	<td width="99%">
  	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="smain">
  		<tr>
  		<td width="50%" align="left" valign="top">您的位置：打印准考证</td>
  		</tr>
  	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="PrintServlet?action=printExatCardNum">        
	         <span>学校名：<select name="schoolName" id="level"> 
	         
	         <option value="" ></option>
	         
	         <c:forEach var="item" items="${sessionScope.schools }" varStatus="status">
	    		<option value="${item.schoolName}" >&nbsp;&nbsp;&nbsp;&nbsp;${item.schoolName}&nbsp;&nbsp;</option>
	   			</c:forEach>
	   			
	   		</select></span>
	         <input name="submit" type="submit" value="查询" class="text-but">
	         </form>
         </td>
  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:150px;"><a href="#" id="print_btn" onFocus="this.blur()" class="add">打印</a></td> 
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="2" cellspacing="0" cellpadding="0" id="main-tab" style="border-collapse:separate; border-spacing:1px; page-break-after: always;">
  		
      
      <c:forEach var="item" items="${sessionScope.examCardNum }" varStatus="status">
      	
      		<c:if test="${status.index % 2 == 0}">
      			<tr style="font-size:12px; line-height:24px;" onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">    
      			
      		</c:if>
      		
      		<td align="center" valign="middle" class="borderright borderbottom" style="text-align: left;padding-left:10px; padding-top: 10px; padding-bottom:10px;">
       				<span style="font-size:13px; text-align:center;display:block;">2018年湖南省高职职业院校学生专业技能抽查考试</span>
       				<span style="font-size:13px; text-align: center;display:block;">准考证</span>
       				<!--  <span style="text-align: center;display:block;">软件技术专业抽查考试准考证</span>-->
    				<span>姓名：</span><span>${item.students.stuName }</span><br />
    				<span>身份证：</span><span>${item.students.IDCard }</span><br />
    				<span>准考证：</span><span>${item.examCardNum }</span><br />
    				<span>场次：</span><span>${item.examRoom.examSession }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>考场：</span><span>${item.roomNum }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>座位号：</span><span>${item.deviceNum }</span><br />
    				<span>考试时间：</span><span>${fn:substring(item.examRoom.startTime, 0 ,10 )}</span>&nbsp;&nbsp;${fn:substring(item.examRoom.startTime, 10 ,16 )}&nbsp;-&nbsp;${fn:substring(item.examRoom.endTime, 10,16 )}<br />
      		</td>
      		<c:if test="${status.index %2==1}">
      			</tr>
      		</c:if>
      		
      </c:forEach>
      
      </table></td>
 </tr>

  <tr>
  	<td align="center" valign="middle" class="fenye"><span id="tipMsg" style="display:none; color:red; font-size:12px;">修改成功！</span><td>
  </tr>
</table>
</body>
</html>