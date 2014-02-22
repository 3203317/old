/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：组织机构管理模块各组件加载
 */
if(dijit.byId("_main_tab_1609") != undefined){
	dijit.byId("_main_tab_1609").on("unLoad", function(){
		console.log("组织机构资源释放");
	});

	dijit.byId("_main_tab_1609").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/resource/LTree",
			"internal/sys/resource/Toolbar",
			"internal/sys/resource/Grid",
			"internal/sys/resource/LTreeStore",
			"internal/sys/resource/GridStore",
			"internal/sys/resource/SearchForm",
			"internal/sys/resource/ResourceDialog",
			"internal/sys/resource/ResStatDialog",
			"dojox/layout/ToggleSplitter",
			"dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$LTree,$Toolbar,$Grid,$LTreeStore,$GridStore,$SearchForm,$ResourceDialog,$ResStatDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_1609").vpath == undefined ? "" : dijit.byId("_main_tab_1609").vpath;

			/**/
			var __lTreeStore = new $LTreeStore({ vpath: __vpath });
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __lTree = new $LTree({ vpath: __vpath }).placeAt("_capec_sys_resource_left");
			var __toolbar = new $Toolbar({}).placeAt("_capec_sys_resource_top_top");
			var __grid = new $Grid({}).placeAt("_capec_sys_resource_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_resource_top_center");
			var __resourceDialog = new $ResourceDialog({ vpath: __vpath });
			var __resStatDialog = new $ResStatDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__resourceDialog);
			__toolbar.bindStatDialog(__resStatDialog);
			__toolbar.bindStore(__gridStore);

			__lTree.startup();
			__lTree.bindStore(__lTreeStore);
			__lTree.bindGridStore(__gridStore);
			__lTree.bindSearchForm(__searchForm);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__resourceDialog);
			__grid.bindStatDialog(__resStatDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__resourceDialog.bindStore(__gridStore);
			__resourceDialog.bindTree(__lTree);
			__resStatDialog.bindStore(__gridStore);
			/**/

			/**/
			dijit.byId("_main_tab_1609").resize();
			dijit.byId("_main_tab_1609")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_1609").resize();
				dijit.byId("_main_tab_1609")._layout();
			})
		});
	});
}