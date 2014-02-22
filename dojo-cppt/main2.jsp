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
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojo/resources/dojo.css" />
<link rel="stylesheet" type="text/css" href="themes/capec/capec.css" />
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojox/layout/resources/ToggleSplitter.css" />
<link rel="stylesheet" type="text/css" href="main2/main2.css" />

<script type="text/javascript" src="http://192.168.131.91:8083/dojox/js/util.js"></script>
<script type="text/javascript" src="http://192.168.131.91:8083/dojox/js/base.js"></script>
<script type="text/javascript" src="http://192.168.131.91:8083/dojox/js/utils.js"></script>

<script type="text/javascript">
	var djConfig = {
		locale : capec.base.locale,
		async : true,
		bindEncoding : "UTF-8",
		modulePaths : {
			"internal" : "http://192.168.131.91:8083/rfs"
		},
		packages : [ {
			name : "capec",
			location : "http://192.168.131.91:8083/dojox/capec"
		} ]
	};
</script>

<script type="text/javascript" src="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojo/dojo.js" data-dojo-config="isDebug:false,parseOnLoad:true"></script>

<script type="text/javascript" src="main2/main2.js?v12"></script>

</head>

<body class="soria">
	<div id="main_loader">
		<div id="main_loaderInner">页面正在加载 ...</div>
	</div>
	<div id="main" data-dojo-type="dijit/layout/BorderContainer" data-dojo-props='liveSplitters:false,design:"headline",_splitterClass:"dojox.layout.ToggleSplitter"'>

		<div id="main_top" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"top",style:"height:65px\9;padding:0px;border:0px"'>
			<div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props='liveSplitters:false,design:"headline",_splitterClass:"dojox.layout.ToggleSplitter"'>
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"top",style:"height:22px\9;padding:0px;border:0px"'>产品管理平台</div>
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"center"'>

					<div id="toolbar1" data-dojo-type="dijit.Toolbar">
						<div id="toolbar1.cut" data-dojo-type="dijit.form.Button" data-dojo-props='showLabel:false'>Cut</div>
						<div id="toolbar1.copy" data-dojo-type="dijit.form.Button" data-dojo-props='showLabel:true,iconClass:"dijitEditorIcon dijitEditorIconBold"'>系统管理</div>
						<div id="toolbar1.bold" data-dojo-type="dijit.form.ToggleButton" data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconBold", showLabel:false'>Bold</div>
						<div id="toolbar1.italic" data-dojo-type="dijit.form.ToggleButton" data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconItalic", showLabel: true'>Italic</div>
					</div>

				</div>
			</div>
		</div>


		<div id="main_tabs" jsId="main_tabs" style="padding-Right: 3px; padding-Left: 3px; padding-Bottom: 3px;" data-dojo-type="internal/main/MainTabs" data-dojo-props='region:"center"' selectedchild="main_tab_def">
			<div id="main_tab_def" jsId="main_tab_def" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='title:"首页",content:"欢迎您"'></div>
		</div>

		<div id="main_copyright" jsId="main_copyright" data-dojo-type="dijit/layout/ContentPane" region="bottom">版权所有：郑州新开普电子股份有限公司</div>
	</div>
</body>
</html>