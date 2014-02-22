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
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojox/grid/resources/Grid.css" />
<link rel="stylesheet" type="text/css" href="http://192.168.131.91:8083/resjs/commons/components/dojo-release-1.8.3/dojox/grid/resources/soriaGrid.css" />

<style>
html,body {
	width: 100%;
	height: 100%;
	overflow: hidden;
	padding: 0;
	margin: 0;
}
</style>

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

<script type="text/javascript">
	require([ "dojo", "dojo/ready", "dojo/_base/declare", "dojo/dom",
	  		"dojo/_base/lang", "dijit/layout/BorderContainer",
	  		"dijit/layout/ContentPane", "dojox/layout/ToggleSplitter",
	  		"dijit/Toolbar", "dijit/form/Button", "dojox/layout/ContentPane",
	  		"dijit/Tooltip", "dijit/MenuItem", "dijit/Menu", "dijit/MenuItem" ], function() {
	});
</script>

</head>

<body class="soria">
	<div id="main_tab_5" jsId="main_tab_5" data-dojo-type="dojox/layout/ContentPane" data-dojo-props='title:"模块管理(IFrame)",href:"module.jsp",vpath:"../../"' style="width:100%;height:100%"></div>
</body>
</html>