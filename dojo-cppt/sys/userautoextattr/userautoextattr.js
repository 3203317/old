/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：户籍管理模块各组件加载
 */
if(dijit.byId("_main_tab_1604") != undefined){
	dijit.byId("_main_tab_1604").on("unLoad", function(){
		console.log("户籍管理资源释放");
	});

	dijit.byId("_main_tab_1604").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/userautoextattr/RGrid",
			"internal/sys/userautoextattr/Toolbar",
			"internal/sys/userautoextattr/GridStore",
			"internal/sys/userautoextattr/SearchForm",
			"internal/sys/userautoextattr/UserAutoExtAttrDialog",
			"dojox/layout/ToggleSplitter",
		    "dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$RGrid,$rToolbar,$GridStore,$SearchForm,$UserAutoExtAttrDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_1604").vpath == undefined ? "" : dijit.byId("_main_tab_1604").vpath;

			/**/
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __toolbar = new $rToolbar({}).placeAt("_capec_sys_userautoextattr_top_top");
			var __grid = new $RGrid({}).placeAt("_capec_sys_userautoextattr_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_userautoextattr_top_center");
			var __userautoextattrDialog = new $UserAutoExtAttrDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__userautoextattrDialog);
			__toolbar.bindStore(__gridStore);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__userautoextattrDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__userautoextattrDialog.bindStore(__gridStore);
			/**/

			/**/
			dijit.byId("_main_tab_1604").resize();
			dijit.byId("_main_tab_1604")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_1604").resize();
				dijit.byId("_main_tab_1604")._layout();
			})
		});
	});
}