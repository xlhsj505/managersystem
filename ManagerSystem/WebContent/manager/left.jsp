<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左侧导航menu</title>
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/sdmenu.js"></script>
<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	// ]]>
</script>
<style type=text/css>
html{ SCROLLBAR-FACE-COLOR: #538ec6; SCROLLBAR-HIGHLIGHT-COLOR: #dce5f0; SCROLLBAR-SHADOW-COLOR: #2c6daa; SCROLLBAR-3DLIGHT-COLOR: #dce5f0; SCROLLBAR-ARROW-COLOR: #2c6daa;  SCROLLBAR-TRACK-COLOR: #dce5f0;  SCROLLBAR-DARKSHADOW-COLOR: #dce5f0; overflow-x:hidden;}
body{overflow-x:hidden; background:url(images/main/leftbg.jpg) left top repeat-y #f2f0f5; width:194px;}
</style>
</head>
<body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
<div id="left-top">
	<div><img src="../images/main/member.gif" width="44" height="44" /></div>
    <span>用户：${sessionScope.userName}<br>角色：<c:if test="${sessionScope.UserInfo.isEnable == 1}" >管理员</c:if> </span>
</div>
    <div style="float: left" id="my_menu" class="sdmenu" >
      <div class="collapsed">
        <span>基础数据</span>
        <a href="BasicDataServlet?action=PF" target="mainFrame" onFocus="this.blur()">患者信息</a>
        <a href="BasicDataServlet?action=Illness" target="mainFrame" onFocus="this.blur()">患者病史</a>
        <a href="BasicDataServlet?action=Iconography" target="mainFrame" onFocus="this.blur()">影像学情况</a>
        
      </div>
      <div>
        <span>成绩</span>
        <a href="BasicDataServlet?action=Grade" target="mainFrame" onFocus="this.blur()">患者成绩</a>
      </div>
      <div>
        <span>打印</span>
        <a href="PrintServlet?action=printExatCardNum" target="mainFrame" onFocus="this.blur()">打印数据</a>        
      </div>
      <div>
        <span>导出数据</span>
        <a href="export/abutmentexport.jsp" target="mainFrame" onFocus="this.blur()">对接数据</a>
        <a href="export/export.jsp" target="mainFrame" onFocus="this.blur()">数据</a>
      </div>
    </div>
</body>
</html>