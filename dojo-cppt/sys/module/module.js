/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 作用：模块管理
 */
if(dijit.byId("_main_tab_5") != undefined){
	dijit.byId("_main_tab_5").on("unLoad", function(){});

	dijit.byId("_main_tab_5").on("load", function(){
		require(["internal/sys/module/ModuleTree",
		    "internal/sys/module/ModuleGrid",
			"internal/sys/module/Toolbar",
			"dojox/layout/ToggleSplitter",
			"dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($ModuleTree,$ModuleGrid,$Toolbar){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_5").vpath == undefined ? "" : dijit.byId("_main_tab_5").vpath;

			var __tree = $ModuleTree({
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

			var __rGrid = $ModuleGrid({
				region: "center",
				vpath: __vpath,
				style:{
					margin:0,
					padding:0
				}
			});

			var __toolbar = $Toolbar({
				region: "top",
				style:{
					margin:0,
					padding:0
				}
			});

			dijit.byId("_capec_sys_module").addChild(__tree);
			dijit.byId("_capec_sys_module").addChild(__rGrid);
			dijit.byId("_capec_sys_module").addChild(__toolbar);
		});
	});
}