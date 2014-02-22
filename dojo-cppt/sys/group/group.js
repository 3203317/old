/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：组织机构管理模块各组件加载
 */
if(dijit.byId("_main_tab_1605") != undefined){
	dijit.byId("_main_tab_1605").on("unLoad", function(){
		console.log("组织机构资源释放");
	});

	dijit.byId("_main_tab_1605").on("load", function(){
		require(["dojo/_base/connect",
		    "internal/sys/group/LTree",
			"internal/sys/group/Toolbar",
			"internal/sys/group/Grid",
			"internal/sys/group/LTreeStore",
			"internal/sys/group/GridStore",
			"internal/sys/group/SearchForm",
			"internal/sys/group/GroupDialog",
			"internal/sys/group/GroupRelatDialog",
			"internal/sys/group/UserDialog",
			"internal/sys/group/RoleDialog",
			"dojox/layout/ToggleSplitter",
			"dojox/layout/ContentPane",
			"dijit/layout/BorderContainer"],function($connect,$LTree,$Toolbar,$Grid,$LTreeStore,$GridStore,$SearchForm,$GroupDialog,$GroupRelatDialog,$UserDialog,$RoleDialog){

			//获取Ajax数据的虚拟路径
			var __vpath = dijit.byId("_main_tab_1605").vpath == undefined ? "" : dijit.byId("_main_tab_1605").vpath;

			/**/
			var __lTreeStore = new $LTreeStore({ vpath: __vpath });
			var __gridStore = new $GridStore({ vpath: __vpath });
			var __lTree = new $LTree({ vpath: __vpath }).placeAt("_capec_sys_group_left");
			var __toolbar = new $Toolbar({}).placeAt("_capec_sys_group_top_top");
			var __grid = new $Grid({}).placeAt("_capec_sys_group_center");
			var __searchForm = new $SearchForm({ vpath: __vpath }).placeAt("_capec_sys_group_top_center");
			var __groupDialog = new $GroupDialog({ vpath: __vpath });
			var __groupRelatDialog = new $GroupRelatDialog({ vpath: __vpath });
			var __userDialog = new $UserDialog({ vpath: __vpath });
			var __roleDialog = new $RoleDialog({ vpath: __vpath });
			/**/

			/**/
			__toolbar.startup();
			__toolbar.bindDialog(__groupDialog);
			__toolbar.bindRelatDialog(__groupRelatDialog);
			__toolbar.bindUserDialog(__userDialog);
			__toolbar.bindRoleDialog(__roleDialog);
			__toolbar.bindStore(__gridStore);

			__lTree.startup();
			__lTree.bindStore(__lTreeStore);
			__lTree.bindGridStore(__gridStore);
			__lTree.bindSearchForm(__searchForm);

			__grid.startup();
			__grid.bindStore(__gridStore);
			__grid.bindDialog(__groupDialog);
			__grid.bindToolbar(__toolbar);

			__searchForm.bindStore(__gridStore);
			__groupDialog.bindStore(__gridStore);
			__groupDialog.bindTree(__lTree);

			__groupRelatDialog.bindStore(__gridStore);
			__groupRelatDialog.bindTree(__lTree);
			__groupRelatDialog.bindGrid(__grid);

			__userDialog.bindGrid(__grid);
			__roleDialog.bindGrid(__grid);
			/**/

			/**/
			dijit.byId("_main_tab_1605").resize();
			dijit.byId("_main_tab_1605")._layout();
			/**/

			$connect.connect(window, "resize", function(){
				dijit.byId("_main_tab_1605").resize();
				dijit.byId("_main_tab_1605")._layout();
			})
		});
	});
}