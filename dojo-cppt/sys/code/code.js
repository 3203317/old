/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：代码管理模块各组件加载
 */
if(dijit.byId("_main_tab_41") != undefined){
	dijit.byId("_main_tab_41").on("unLoad", function(){
		console.log("代码维护资源释放");
	});

	dijit.byId("_main_tab_41").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/code/RGrid",
			"internal/sys/code/LGrid",
			"internal/sys/code/LToolbar",
			"internal/sys/code/RToolbar",
			"internal/sys/code/CodeTypeDialog",
			"internal/sys/code/CodeDialog",
			"internal/sys/code/CodeTypeStore",
			"internal/sys/code/CodeStore",
			"dojox/layout/ToggleSplitter",
			"dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$RGrid,$LGrid,$lToolbar,$rToolbar,$CodeTypeDialog,$CodeDialog,$CodeTypeStore,$CodeStore){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_41").vpath == undefined ? "" : dijit.byId("_main_tab_41").vpath;

			/**/
			var __codeTypeStore = new $CodeTypeStore({ vpath: __vpath });
			var __lToolbar = new $lToolbar({}).placeAt("_capec_sys_code_left_top");
			var __lGrid = new $LGrid({}).placeAt("_capec_sys_code_left_center");
			var __codeTypeDialog = new $CodeTypeDialog({ vpath: __vpath });
			/**/

			/**/
			var __codeStore = new $CodeStore({ vpath: __vpath });
			var __rToolbar = new $rToolbar({}).placeAt("_capec_sys_code_top");
			var __rGrid = new $RGrid({ vpath: __vpath }).placeAt("_capec_sys_code_top_center");
			var __codeDialog = new $CodeDialog({ vpath: __vpath });
			/**/

			/**/
			__rToolbar.startup();
			__rToolbar.bindDialog(__codeDialog);
			__rToolbar.bindStore(__codeStore);
			__rGrid.startup();
			__rGrid.bindStore(__codeStore);
			__rGrid.bindDialog(__codeDialog);
			__rGrid.bindToolbar(__rToolbar);
			__codeStore.bindToolbar(__lToolbar);
			__codeStore.bindTypeGrid(__lGrid);
			__codeDialog.bindStore(__codeStore);
			/**/

			/**/
			__lToolbar.startup();
			__lToolbar.bindDialog(__codeTypeDialog);
			__lToolbar.bindStore(__codeTypeStore);
			__lGrid.startup();
			__lGrid.bindStore(__codeTypeStore);
			__lGrid.bindDialog(__codeTypeDialog);
			__lGrid.bindCodeStore(__codeStore);
			__codeTypeDialog.bindStore(__codeTypeStore);
			/**/

			/**/
			dijit.byId("_main_tab_41").resize();
			dijit.byId("_main_tab_41")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_41").resize();
				dijit.byId("_main_tab_41")._layout();
			})
		});
	});
}