/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：组织机构管理模块各组件加载
 */
if(dijit.byId("_main_tab_1601") != undefined){
	dijit.byId("_main_tab_1601").on("unLoad", function(){
		console.log("组织机构资源释放");
	});

	dijit.byId("_main_tab_1601").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/org/LTree",
			"internal/sys/org/Toolbar",
			"internal/sys/org/Grid",
			"internal/sys/org/LTreeStore",
			"internal/sys/org/GridStore",
			"internal/sys/org/SearchForm",
			"internal/sys/org/OrgDialog",
			"internal/sys/org/StatDialog",
			"internal/sys/org/BatchDialog",
			"dojox/layout/ToggleSplitter",
			"dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$LTree,$Toolbar,$Grid,$LTreeStore,$GridStore,$SearchForm,$OrgDialog,$StatDialog,$BatchDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_1601").vpath == undefined ? "" : dijit.byId("_main_tab_1601").vpath;

			/**/
			var __lTreeStore = new $LTreeStore({ vpath: __vpath });
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __lTree = new $LTree({ vpath: __vpath }).placeAt("_capec_sys_org_left");
			var __toolbar = new $Toolbar({}).placeAt("_capec_sys_org_top_top");
			var __grid = new $Grid({}).placeAt("_capec_sys_org_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_org_top_center");
			var __orgDialog = new $OrgDialog({ vpath: __vpath });
			var __statDialog = new $StatDialog({ vpath: __vpath });
			var __batchDialog = new $BatchDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__orgDialog);
			__toolbar.bindStatDialog(__statDialog);
			__toolbar.bindBatchDialog(__batchDialog);
			__toolbar.bindStore(__gridStore);

			__lTree.startup();
			__lTree.bindStore(__lTreeStore);
			__lTree.bindGridStore(__gridStore);
			__lTree.bindSearchForm(__searchForm);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__orgDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__orgDialog.bindStore(__gridStore);
			__orgDialog.bindTree(__lTree);
			__statDialog.bindStore(__gridStore);
			__statDialog.bindGrid(__grid);
			__batchDialog.bindStore(__gridStore);
			__batchDialog.bindGrid(__grid);
			/**/

			/**/
			dijit.byId("_main_tab_1601").resize();
			dijit.byId("_main_tab_1601")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_1601").resize();
				dijit.byId("_main_tab_1601")._layout();
			})
		});
	});
}