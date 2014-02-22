/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：代码管理模块各组件加载
 */
if(dijit.byId("_main_tab_156") != undefined){
	dijit.byId("_main_tab_156").on("unLoad", function(){
		console.log("角色资源释放");
	});

	dijit.byId("_main_tab_156").on("load", function(){
		require(["dojo/_base/connect",
		     "internal/sys/role/RGrid",
			"internal/sys/role/RToolbar",
			"internal/sys/role/GridStore",
			"internal/sys/role/SearchForm",
			"internal/sys/role/RoleDialog",
			"internal/sys/role/PermitDialog",
			"internal/sys/role/UserDialog",
			"dojox/layout/ToggleSplitter",
		    "dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$RGrid,$rToolbar,$GridStore,$SearchForm,$RoleDialog,$PermitDialog,$UserDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_156").vpath == undefined ? "" : dijit.byId("_main_tab_156").vpath;

			/**/
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __toolbar = new $rToolbar({ vpath: __vpath, moduleid: 156 }).placeAt("_capec_sys_role_top_top");
			var __grid = new $RGrid({}).placeAt("_capec_sys_role_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_role_top_center");
			var __roleDialog = new $RoleDialog({ vpath: __vpath });
			var __permitDialog = new $PermitDialog({ vpath: __vpath });
			var __userDialog = new $UserDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__roleDialog);
			__toolbar.bindStore(__gridStore);
			__toolbar.bindPermitDialog(__permitDialog);
			__toolbar.bindUserDialog(__userDialog);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__roleDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__roleDialog.bindStore(__gridStore);

			__permitDialog.bindGrid(__grid);
			__userDialog.bindGrid(__grid);
			/**/

			/**/
			dijit.byId("_main_tab_156").resize();
			dijit.byId("_main_tab_156")._layout();

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_156").resize();
				dijit.byId("_main_tab_156")._layout();
			})
		});
	});
}