/**
 * 作者：黄鑫
 * 日期：2013-04-28
 * 描述：日志管理模块各组件加载
 */
if(dijit.byId("_main_tab_418") != undefined){
	dijit.byId("_main_tab_418").on("unLoad", function(){
		console.log("日志资源释放");
	});

	dijit.byId("_main_tab_418").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/log/RGrid",
			"internal/sys/log/LGrid",
			"internal/sys/log/RToolbar",
			"internal/sys/log/LogDialog",
			"internal/sys/log/LogTypeStore",
			"internal/sys/log/LogStore",
			"dojox/layout/ToggleSplitter",
			"dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$RGrid,$LGrid,$rToolbar,$LogDialog,$LogTypeStore,$LogStore){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_418").vpath == undefined ? "" : dijit.byId("_main_tab_418").vpath;

			/**/
			var __logTypeStore = new $LogTypeStore({ vpath: __vpath });
			var __lGrid = new $LGrid({}).placeAt("_capec_sys_log_left_center");
			/**/

			/**/
			var __logStore = new $LogStore({ vpath: __vpath });
			var __rToolbar = new $rToolbar({}).placeAt("_capec_sys_log_top");
			var __rGrid = new $RGrid({ vpath: __vpath }).placeAt("_capec_sys_log_top_center");
			var __logDialog = new $LogDialog({ vpath: __vpath });
			/**/

			/**/
			__rToolbar.startup();
			__rToolbar.bindDialog(__logDialog);
			__rToolbar.bindStore(__logStore);
			__rGrid.startup();
			__rGrid.bindStore(__logStore);
			__rGrid.bindDialog(__logDialog);
			__rGrid.bindToolbar(__rToolbar);
			__logDialog.bindStore(__logStore);
			/**/

			/**/
			__lGrid.startup();
			__lGrid.bindStore(__logTypeStore);
			__lGrid.bindLogStore(__logStore);
			/**/

			/**/
			dijit.byId("_main_tab_418").resize();
			dijit.byId("_main_tab_418")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_418").resize();
				dijit.byId("_main_tab_418")._layout();
			})
		});
	});
}