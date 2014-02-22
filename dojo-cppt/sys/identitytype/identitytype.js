/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：户籍管理模块各组件加载
 */
if(dijit.byId("_main_tab_1603") != undefined){
	dijit.byId("_main_tab_1603").on("unLoad", function(){
		console.log("户籍管理资源释放");
	});

	dijit.byId("_main_tab_1603").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/identitytype/RGrid",
			"internal/sys/identitytype/Toolbar",
			"internal/sys/identitytype/GridStore",
			"internal/sys/identitytype/SearchForm",
			"internal/sys/identitytype/IdentityTypeDialog",
			"dojox/layout/ToggleSplitter",
		    "dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$RGrid,$rToolbar,$GridStore,$SearchForm,$IdentityTypeDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_1603").vpath == undefined ? "" : dijit.byId("_main_tab_1603").vpath;

			/**/
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __toolbar = new $rToolbar({}).placeAt("_capec_sys_identitytype_top_top");
			var __grid = new $RGrid({}).placeAt("_capec_sys_identitytype_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_identitytype_top_center");
			var __identitytypeDialog = new $IdentityTypeDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__identitytypeDialog);
			__toolbar.bindStore(__gridStore);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__identitytypeDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__identitytypeDialog.bindStore(__gridStore);
			/**/

			/**/
			dijit.byId("_main_tab_1603").resize();
			dijit.byId("_main_tab_1603")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_1603").resize();
				dijit.byId("_main_tab_1603")._layout();
			})
		});
	});
}