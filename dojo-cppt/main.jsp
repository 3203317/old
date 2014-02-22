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
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dijit/themes/capec/capec.css" />
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojox/layout/resources/ToggleSplitter.css" />
<link rel="stylesheet" type="text/css" href="main/main.css" />

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

<script type="text/javascript" src="main/main.js?v12"></script>

</head>

<body class="soria">
	<div id="main_loader">
		<div id="main_loaderInner">页面正在加载 ...</div>
	</div>
	<div id="main" data-dojo-type="dijit/layout/BorderContainer" data-dojo-props='liveSplitters:false,design:"headline",_splitterClass:"dojox.layout.ToggleSplitter"'>

		<div id="main_top" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"top",style:"height:65px\9;padding:0px;border:0px"'>
			<div id="main_logo"></div>
			<div id="main_top_right">
				<div id="main_top_bar">
					<ul>
						<li><input type="checkbox" id="main_chk_help" />&nbsp;&nbsp;<label id="main_helpLab">实时帮助</label></li>
						<li><a href="javascript:void(0);" id="l2"><label id="main_myInfoLab">个人信息管理</label> </a></li>
						<li><a href="javascript:void(0);" id="l3"><label id="main_lngSetLab">语言设置</label> </a></li>
						<li><a href="javascript:void(0);" id="l4"><label id="main_themeSetLab">主题设置</label> </a></li>
						<li style="background: none"><a href="javascript:void(0);" id="l5"><label id="main_logoutLab">退出</label> </a></li>
					</ul>
				</div>
			</div>
		</div>


		<div id="main_left" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props='region:"left",splitter:true,minSize:150,maxSize:250'>
			<div id="main_left_top" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='region:"top"'>
				<div id="main_left_top_search">
					<div jsId="main_modSearchCBox" style="width: 70%" data-dojo-type="internal/main/ModSearchCBox" data-dojo-props='autoComplete:false,placeHolder:"模块名",pageSize:5,searchAttr:"modulename",region:"middle"'></div>
					<div data-dojo-type="dijit/form/Button" label="搜索"></div>
				</div>
			</div>
			<div data-dojo-type="internal/main/MenuTree" data-dojo-props='region:"center"'></div>
		</div>

		<div id="main_tabs" jsId="main_tabs" style="padding-Right: 3px;" data-dojo-type="internal/main/MainTabs" data-dojo-props='region:"center"' selectedchild="main_tab_def">
			<div id="main_tab_def" jsId="main_tab_def" data-dojo-type="dijit.layout.ContentPane" data-dojo-props='title:"首页",content:"欢迎您"'></div>
		</div>

		<div id="main_copyright" jsId="main_copyright" data-dojo-type="dijit/layout/ContentPane" region="bottom">Copyright @ 2012 郑州新开普电子股份有限公司 版权所有</div>
	</div>


</body>
</html>