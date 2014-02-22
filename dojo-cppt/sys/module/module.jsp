<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String __ip = "192.168.131.91:8083";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title></title>
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojox/grid/resources/Grid.css" />
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojox/grid/resources/soriaGrid.css" />

<style type="text/css">
#_capec_sys_module {
	height: 100%;
	width: 100%;
	font-size: 12px;
}

#_capec_sys_module_left {
	width: 200px;
	height: 100%;
	margin: 0;
	padding: 2px;
}

#_capec_sys_module_center {
	height: 100%;
	margin: 0;
	padding: 0px;
}

#_capec_sys_module_top {
	margin: 0;
	padding: 0px;
}
</style>
<script type="text/javascript" src="module.js?v12"></script>

</head>

<body class="soria">
	<div id="_capec_sys_module" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props='liveSplitters:false,design:"sidebar",_splitterClass:"dojox.layout.ToggleSplitter"'>
		<!--div id="_capec_sys_module_left" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"left",splitter:true,minSize:150,maxSize:250'>
			<div id="_capec_sys_module_modTree" style="height: 100%; width: 100%;"></div>
		</div-->
		<div id="_capec_sys_module_top" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"top"'>
			<!-- div id="_capec_sys_module_toolbar" data-dojo-type="dijit.Toolbar">
				<div id="_capec_sys_module_toolbar_addBtn" data-dojo-type="dijit.form.Button" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconNewPage'">新增</div>
				<div id="_capec_sys_module_toolbar_editBtn" data-dojo-type="dijit.form.Button" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconWikiword',disabled:true">编辑</div>
				<div id="_capec_sys_module_toolbar_delBtn" data-dojo-type="dijit.form.Button" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete',disabled:true">删除</div>
				<div id="_capec_sys_module_toolbar_editBtnTip" data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'_capec_sys_module_toolbar_editBtn',position:['above','below']"></div>
				<div id="_capec_sys_module_toolbar_delBtnTip" data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'_capec_sys_module_toolbar_delBtn',position:['above','below']"></div>
			</div-->
		</div>
		<!-- div id="_capec_sys_module_center" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"center"'>
			<div id="_capec_sys_module_modGrid"></div>
		</div-->
	</div>
</body>
</html>