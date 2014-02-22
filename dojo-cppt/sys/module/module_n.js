dijit.byId("main_tab_511").on("unLoad", function(){
});

dijit.byId("main_tab_511").on("load", function(){
	require(["internal/sys/module/ModuleTree",
	         "internal/sys/module/ModuleGrid",
	         "internal/sys/module/Toolbar",
			"dojo/_base/lang",
	 		"dojo/data/ItemFileWriteStore",
			"dijit/Tree",
	 		"dijit/tree/ForestStoreModel",
			"dojo/_base/connect",
	 		"dojo/_base/declare",
	 		"dojo/dom",
	 		"dojox/grid/EnhancedGrid"],function($ModuleTree,$ModuleGrid,$Toolbar,$lang,$ItemFileWriteStore,$Tree,$ForestStoreModel,$connect){

		//获取Ajax数据的虚拟路径
		var __vpath = dijit.byId("main_tab_511").vpath == undefined ? "" : dijit.byId("main_tab_511").vpath;

		var __moduleTree = new $ModuleTree({
			style: {
				width: "200px",
				height: "100%",
				margin: "0",
				padding: "2px"
			},
			region: "left",
			splitter: true,
			minSize: 150,
			maxSize: 250,
			vpath: __vpath,
			rootLabel: "新开普"
		});

		var __moduleGrid = new $ModuleGrid({
			region: "center",
			style: {
				height: "100%",
				margin: "0",
				padding: "0px"
			},
			vpath: __vpath
		});

		var __toolbar = new $Toolbar({});

		dijit.byId("abc").addChild(__moduleGrid);
		dijit.byId("def").addChild(__moduleTree);
		dijit.byId("ghi").addChild(__toolbar);
		dijit.byId("abc").layout();
		__moduleGrid.layout();
		dijit.byId("main_tab_511")._layout();
	});
});