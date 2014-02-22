/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：组织表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dojo/_base/lang",
	"capec/utils/ajax",
	"dijit/form/Button",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/DateTextBox",
	"dijit/form/SimpleTextarea",
	"dijit/form/NumberSpinner",
	"dijit/form/Select",
	"internal/sys/role/RoleRelatGrid",
	"internal/sys/role/ResourceTree"], function($connect,$declare,$FormDialog,$lang,$ajax,$Button) {
	return $declare($FormDialog,{
		_addButtons: function(){
			var __btnParams = {};
			__btnParams["class"] = this.bassClass +"dialogPaneButton";

			var __newBtn = null;
			var __connect = null;

			__btnParams.label = "关闭";
			__newBtn = new $Button(__btnParams);
			this._div.appendChild(__newBtn.domNode);
			this._connects_.push($connect.connect(__newBtn, "onClick", this, "hide"));
		},
		fid: "internal_sys_role_PermitForm",
		title: "权限分配",
		href: "sys/role/permitForm.html",
		style: { height: "412px", width: "600px" },
		_grid: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		_valid: function(){
			var __frm = dijit.byId(this.fid);
			return __frm.isValid();
		},
		_submit: function(){
		},
		_reset: function(){
			if(this.model == "Create"){
				dijit.byId(this.fid).reset();
			}else{
				this._setData();
			}
		},
		_draw: function(){
			console.log("绘制表单");
			console.log("Ajax请求需同步或其他方式");

			/* 树与Grid建立关系 */
			var __tree = dijit.byId(this.fid +"_lTree");
			var __grid = dijit.byId(this.fid +"_rGrid");
			__tree.bindGrid(__grid);
		},
		_setTitle: function(){
			switch(this.model){
			case "Update":
				this.set("title", "权限分配    [角色："+ this._grid.getSelectItem().i.rolename+"]");
				break;
			}
		},
		_setRoleResTreeCheckBox: function($roleid){
			$ajax({
				async : true,
				content: { roleid: $roleid },
				url : this.vpath +"testData/domainresource_role"+ $roleid +".json",
				callback : $lang.hitch(this, "_setRoleResTreeCheckBoxBack")
			});
		},
		_setRoleResTreeCheckBoxBack: function($data){
			var __tree = dijit.byId(this.fid +"_lTree");
			for(var __i_3=0,__item_3; __item_3=$data.items[__i_3]; __i_3++){
				__tree.checkTreeNode(__item_3);
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data_3 = this._grid.getSelectItem();
				if(__data_3 == null || __data_3 == undefined) return;
				var __tree = dijit.byId(this.fid +"_lTree");
				var __grid = dijit.byId(this.fid +"_rGrid");
				__tree.set("roleid", __data_3.i.id);
				__grid.set("roleid", __data_3.i.id);
				__tree.cleanAllCheckBox();
				/* 绑定Checkbox */
				this._setRoleResTreeCheckBox(__data_3.i.id);
			}
			this._valid();
		}
	});
});
