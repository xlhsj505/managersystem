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

 function post(url, params) {
	var temp = document.createElement("form"); //创建form表单
	 temp.action = url;
	 temp.method = "post";
	 temp.style.display = "none";//表单样式为隐藏
	 for (var item in params) {//初始化表单内部的控件
	    //根据实际情况创建不同的标签元素
	     var opt1 =document.createElement("input");  //添加input标签
	     opt1.type="text";   //类型为text 
	     //opt1.id = item;      //设置id属性
	     opt1.name = item;    //设置name属性
	     opt1.value = params[item];   //设置value属性
	     temp.appendChild(opt1);
	 }

	 document.body.appendChild(temp);
	 temp.submit();
	 return temp;
 
}


</script>
</head>
<body>
<!--main_top-->
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：试题管理</td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="QuestionsManagerServlet?action=queryQuestions">
	         <span>试题编号：</span>
	         <input type="text" id="seriaNum" name="seriaNum" value="" class="text-word">
	         <span>试题名：</span>
	         <input type="text" id="title" name="title" value="" class="text-word">
	         <span>模块名：</span>
	   		<input type="text" id="moduleName" name="moduleName" value="" class="text-word">
	         <input name="submit" type="submit" value="查询" class="text-but">
	         </form>
         </td>
  		  <!-- <td width="10%" align="center" valign="middle" style="text-align:right; width:150px;"><a href="basicData/students_import.jsp" target="mainFrame" onFocus="this.blur()" class="add">导入试题</a></td> -->
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr>
      <!--  <th align="center" valign="middle" class="borderright">
      <input type="checkbox" name="all" onclick="checkAll()" />&nbsp;&nbsp;全选</th>-->
        <th align="center" valign="middle" class="borderright">序号</th>
        <th align="center" valign="middle" class="borderright">试题序号</th>
        <th align="center" valign="middle" class="borderright">试题编号</th>
        <th align="center" valign="middle" class="borderright">标题</th>
        <th align="center" valign="middle" class="borderright">路径</th>
        <th align="center" valign="middle" class="borderright">是否可用</th>
        <th align="center" valign="middle" class="borderright">模块名</th>
        <!--  <th align="center" valign="middle">操作</th>-->
      </tr>
      <c:forEach var="item" items="${sessionScope.questions.pageData }" varStatus="status">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <!-- <td align="center" valign="middle" class="borderright borderbottom">
      			<input type="checkbox" name="id" value="${item.IUID }" onclick=usercheck(this)/>
      		</td>-->
	        <td align="center" valign="middle" class="borderright borderbottom">${item.IUID }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.questionsNum }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.seriaNum }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.title }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.path }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.disEnable eq 1? "可用" : "不可用" }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.module.moduleName }</td>
        	<td align="center" valign="middle" class="borderbottom">
        		 <!--<input type="button" value="编辑" onFocus="this.blur()" id="upstudent" class="add" onclick=updataStudent(this) />
	        	  <a href="javascript:updataStudent(this)" target="mainFrame" onFocus="this.blur()" class="add" >编辑</a>-->
	        	<!--<span class="gray">&nbsp;|&nbsp;</span>
	        	<a href="add.html" target="mainFrame" onFocus="this.blur()" class="add">删除</a>
	        	-->
        	</td>
		
      </tr></c:forEach>
      </table></td>
    </tr>
   
  <tr>
    <td align="center" valign="middle" class="fenye">
    	当前&nbsp;${sessionScope.questions.currentPage }&nbsp;/&nbsp;${sessionScope.questions.totalPage }&nbsp;页   &nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryQuestions',
    	{'seriaNum':'${sessionScope.seriaNum }', 'title':'${sessionScope.title }', 'moduleName':'${sessionScope.moduleName }',
    	'currentPage':'1'})" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryQuestions',
    	{'seriaNum':'${sessionScope.seriaNum }', 'title':'${sessionScope.title }', 'moduleName':'${sessionScope.moduleName }',
    	'currentPage':'${sessionScope.questions.currentPage-1}'})" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryQuestions',
    	{'seriaNum':'${sessionScope.seriaNum }', 'title':'${sessionScope.title }', 'moduleName':'${sessionScope.moduleName }',
    	'currentPage':'${sessionScope.questions.currentPage+1}'})" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryQuestions',
    	{'seriaNum':'${sessionScope.seriaNum }', 'title':'${sessionScope.title }', 'moduleName':'${sessionScope.moduleName }', 
    	'currentPage':'${sessionScope.questions.totalPage}'})" target="mainFrame" onFocus="this.blur()">尾页</a>
    	&nbsp;&nbsp;总共 ${sessionScope.questions.totalCount }条
    </td>
  </tr>
</table>
</body>
</html>