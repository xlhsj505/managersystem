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
<script src="<%=basePath %>js/jquery.print-preview.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.PrintArea.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.jqprint-0.3.js" type="text/javascript"></script>
<style >
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
	    if ($(this).index() == 4 ){
		    var IUID = $(this).parent("tr").find("td:eq(0)").text();
		    var title = $(this).parent("tr").find("td:eq(5)");
	    	var oldVal = $(this).text();
		    var input = "<input type='text' id='tmpId' value='" + oldVal + "' >";
		    $(this).text('');
		    $(this).append(input);
		    $('#tmpId').focus();
		    $('#tmpId').blur(function(){
		    	//丢失光标，如果新值与旧值相等不发送请求，否则发送请求
		    	if($(this).val() != oldVal){		    	
		    		//获取文本值
		    		var newVal = $(this).val();
		    		oldVal = $(this).val();
		    		//发送ajax请求，更新数据库
		    		$.ajax({
		    			type: "post",
		    			url: "QuestionsManagerServlet?action=ajaxUpExtractingResultBySeriaNum",
    					data: {"newVal":oldVal, "IUID":IUID},
    					dataType: "json",
    					success: function(data){
    						var rs = data.success;
    						//var json= JSON.stringify(data);
    						if (rs != 0){
    						
    							title.text(rs.title);
    							$("temTitle").text(rs.title);
    							$("#tipMsg").css({"display":"block"});
    							setTimeout("hide()",1000);
    						}
    					}
		    		})
		    		//closest：是从当前元素开始，沿Dom树向上遍历直到找到已应用选择器的一个匹配为止。
				       $(this).closest('td').text(oldVal);
		    	}else{
		    		$(this).closest('td').text(oldVal);
		    	}
		        
		    });
	    }
	});
	
})

function hide(){
	$("#tipMsg").css("display","none");
}

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
 
 $(function(){ 
	 $("#print_btn").click(function(){ 
		 $("#main-tab").jqprint({
		     debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
		     importCSS: false, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
		     printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
		     operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
		})
	 }) 
	})	
 


</script>
</head>
<body>
<!--main_top-->
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
  	<td width="99%">
  	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="smain">
  		<tr>
  		<td width="50%" align="left" valign="top">您的位置：抽题结果</td>
    	<td width="49%" align="left" valign="middle">
    	<a href="javascript:post('QuestionsManagerServlet?action=startExtracting',{'flag':'1'})" target="mainFrame" onFocus="this.blur()" class="add">抽题</a></td>
  		</tr>
  	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="QuestionsManagerServlet?action=queryExtractingResult">
	         <span>学校名：<select name="schoolName" id="level" onchange="javascript:post('QuestionsManagerServlet?action=queryExtractingResult',{'schoolName':this.value})"> 
	         
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
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr>
        <th align="center" valign="middle" class="borderright">编号</th>
        <th align="center" valign="middle" class="borderright">学校</th>
        <th align="center" valign="middle" class="borderright">身份证</th>
        <th align="center" valign="middle" class="borderright">姓名</th>
        <th align="center" valign="middle" class="borderright">试题编号</th>
        <th align="center" valign="middle" class="borderright">标题</th>
        <th align="center" valign="middle" class="borderright">所属模块</th>
        <!--  <th align="center" valign="middle">操作</th>-->
      </tr>
      <c:forEach var="item" items="${sessionScope.TempExtractResult.pageData }" varStatus="status">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="center" valign="middle" class="borderright borderbottom">${item.IUID }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.schoolName }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.IDCard }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.stuName }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.seriaNum }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.title }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${item.moduleName }</td>		
      </tr></c:forEach>
      </table></td>
 </tr>
   
  <tr>
    <td align="center" valign="middle" class="fenye">
    	当前&nbsp;${sessionScope.TempExtractResult.currentPage }&nbsp;/&nbsp;${sessionScope.TempExtractResult.totalPage }&nbsp;页   &nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryExtractingResult',
    	{'schoolName':'${sessionScope.schoolName }',
    	'currentPage':'1'})" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryExtractingResult',
    	{'schoolName':'${sessionScope.schoolName }',
    	'currentPage':'${sessionScope.TempExtractResult.currentPage-1}'})" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryExtractingResult',
    	{'schoolName':'${sessionScope.schoolName }',
    	'currentPage':'${sessionScope.TempExtractResult.currentPage+1}'})" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
    	<a href="javascript:post('QuestionsManagerServlet?action=queryExtractingResult',
    	{'schoolName':'${sessionScope.schoolName }',
    	'currentPage':'${sessionScope.TempExtractResult.totalPage}'})" target="mainFrame" onFocus="this.blur()">尾页</a>&nbsp;&nbsp;
    	&nbsp;&nbsp;总共 ${sessionScope.TempExtractResult.totalCount }条
    </td>
  </tr>
  <tr>
  	<td align="center" valign="middle" class="fenye"><span id="tipMsg" style="display:none; color:red; font-size:12px;">修改成功！</span><td>
  </tr>
</table>
</body>
</html>