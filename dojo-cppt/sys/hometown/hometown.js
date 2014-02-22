/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：户籍管理模块各组件加载
 */
if(dijit.byId("_main_tab_1602") != undefined){
	dijit.byId("_main_tab_1602").on("unLoad", function(){
		console.log("户籍管理资源释放");
	});

	dijit.byId("_main_tab_1602").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/hometown/RGrid",
			"internal/sys/hometown/Toolbar",
			"internal/sys/hometown/GridStore",
			"internal/sys/hometown/SearchForm",
			"internal/sys/hometown/HometownDialog",
			"dojox/layout/ToggleSplitter",
		    "dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$RGrid,$rToolbar,$GridStore,$SearchForm,$HometownDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_1602").vpath == undefined ? "" : dijit.byId("_main_tab_1602").vpath;

			/**/
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __toolbar = new $rToolbar({}).placeAt("_capec_sys_hometown_top_top");
			var __grid = new $RGrid({}).placeAt("_capec_sys_hometown_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_hometown_top_center");
			var __hometownDialog = new $HometownDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__hometownDialog);
			__toolbar.bindStore(__gridStore);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__hometownDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__hometownDialog.bindStore(__gridStore);
			/**/

			/**/
			dijit.byId("_main_tab_1602").resize();
			dijit.byId("_main_tab_1602")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_1602").resize();
				dijit.byId("_main_tab_1602")._layout();
			})
		});
	});
}