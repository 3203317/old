/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：代码管理模块各组件加载
 */
if(dijit.byId("_main_tab_157") != undefined){
	dijit.byId("_main_tab_157").on("unLoad", function(){
		console.log("角色资源释放");
	});

	dijit.byId("_main_tab_157").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/batch/RGrid",
			"internal/sys/batch/Toolbar",
			"internal/sys/batch/GridStore",
			"internal/sys/batch/SearchForm",
			"internal/sys/batch/BatchDialog",
			"internal/sys/batch/BatchEditDialog",
			"dojox/layout/ToggleSplitter",
		    "dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$RGrid,$rToolbar,$GridStore,$SearchForm,$BatchDialog,$BatchEditDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_157").vpath == undefined ? "" : dijit.byId("_main_tab_157").vpath;

			/**/
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __toolbar = new $rToolbar({}).placeAt("_capec_sys_batch_top_top");
			var __grid = new $RGrid({}).placeAt("_capec_sys_batch_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_batch_top_center");
			var __batchDialog = new $BatchDialog({ vpath: __vpath });
			var __batchEditDialog = new $BatchEditDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__batchDialog);
			__toolbar.bindEditDialog(__batchEditDialog);
			__toolbar.bindStore(__gridStore);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__batchDialog);
			__grid.bindEditDialog(__batchEditDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__batchDialog.bindStore(__gridStore);
			__batchEditDialog.bindStore(__gridStore);
			/**/

			/**/
			dijit.byId("_main_tab_157").resize();
			dijit.byId("_main_tab_157")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_157").resize();
				dijit.byId("_main_tab_157")._layout();
			})
		});
	});
}