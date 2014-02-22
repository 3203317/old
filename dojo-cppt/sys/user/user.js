/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：代码管理模块各组件加载
 */
if(dijit.byId("_main_tab_160") != undefined){
	dijit.byId("_main_tab_160").on("unLoad", function(){
		console.log("用户资源释放");
	});

	dijit.byId("_main_tab_160").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/user/LTree",
			"internal/sys/user/Toolbar",
			"internal/sys/user/Grid",
			"internal/sys/user/LTreeStore",
			"internal/sys/user/GridStore",
			"internal/sys/user/SearchForm",
			"internal/sys/user/UserDialog",
			"internal/sys/user/BatchUserDialog",
			"internal/sys/user/PassDialog",
			"internal/sys/user/StatDialog",
			"internal/sys/user/ImportDialog",
			"dojox/layout/ToggleSplitter",
			"dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$LTree,$Toolbar,$Grid,$LTreeStore,$GridStore,$SearchForm,$UserDialog,$BatchUserDialog,$PassDialog,$StatDialog,$ImportDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_160").vpath == undefined ? "" : dijit.byId("_main_tab_160").vpath;

			/**/
			var __lTreeStore = new $LTreeStore({ vpath: __vpath });
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __lTree = new $LTree({ vpath: __vpath }).placeAt("_capec_sys_user_left");
			var __toolbar = new $Toolbar({}).placeAt("_capec_sys_user_top_top");
			var __grid = new $Grid({}).placeAt("_capec_sys_user_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_user_top_center");
			var __userDialog = new $UserDialog({ vpath: __vpath });
			var __batchUserDialog = new $BatchUserDialog({ vpath: __vpath });
			var __passDialog = new $PassDialog({ vpath: __vpath });
			var __statDialog = new $StatDialog({ vpath: __vpath });
			var __importDialog = new $ImportDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__userDialog);
			__toolbar.bindBatchDialog(__batchUserDialog);
			__toolbar.bindPassDialog(__passDialog);
			__toolbar.bindStatDialog(__statDialog);
			__toolbar.bindImportDialog(__importDialog);
			__toolbar.bindStore(__gridStore);

			__lTree.startup();
			__lTree.bindStore(__lTreeStore);
			__lTree.bindGridStore(__gridStore);
			__lTree.bindSearchForm(__searchForm);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__userDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__userDialog.bindStore(__gridStore);

			__passDialog.bindGrid(__grid);
			__statDialog.bindGrid(__grid);
			/**/

			/**/
			dijit.byId("_main_tab_160").resize();
			dijit.byId("_main_tab_160")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_160").resize();
				dijit.byId("_main_tab_160")._layout();
			})
		});
	});
}