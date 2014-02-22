/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：组织表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/DateTextBox",
	"dijit/form/SimpleTextarea",
	"dijit/form/NumberSpinner",
	"dijit/form/Select",
	"internal/sys/role/RoleRelatGrid",
	"internal/sys/role/DomRoleTree"], function($connect,$declare,$FormDialog) {
	return $declare($FormDialog,{
		fid: "internal_sys_role_RelatForm",
		title: "角色关系管理",
		href: "sys/role/relatForm.html",
		style: { height: "312px", width: "500px" },
		_grid: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		_valid: function(){
			var __frm = dijit.byId(this.fid);
			return __frm.isValid();
		},
		_submit: function(){
			if(this._valid()){
				var __newItem = dijit.byId(this.fid).getValues();
				console.log(__newItem);
				/* ajax提交数据并隐藏dialog */
//				__newItem.domainid = this._tree.getSelectedItem().id[0];
//				if(this.model == "Update"){
//					this._store.setItem(__newItem);
//				}
			}else{
				dijit.byId(this.fid).validate();
			}
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
		},
		_setTitle: function(){
			switch(this.model){
			case "Update":
				this.set("title", "角色关系管理    [角色："+ this._grid.getSelectItem().i.rolename+"]");
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data_3 = this._grid.getSelectItem();
				if(__data_3 == null || __data_3 == undefined) return;
				dijit.byId(this.fid +"_idText").set("value", __data_3.i.id);
			}
			this._valid();
		}
	});
});
