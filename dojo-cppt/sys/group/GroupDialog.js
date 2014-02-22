/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：组织表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dojo/i18n!./nls/GroupDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/DateTextBox",
	"dijit/form/SimpleTextarea",
	"dijit/form/NumberSpinner",
	"dijit/form/Select"], function($connect,$declare,$FormDialog,$i18n) {
	return $declare($FormDialog,{
		fid: "internal_sys_group_GroupForm",
		title: $i18n.title_Create,
		href: "sys/group/groupForm.html",
		style: { height: "160px", width: "500px" },
		_grid: null,
		_store: null,
		_tree: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		bindStore: function($store){
			this._store = $store;
			this._store.bindDialog(this);
		},
		bindTree: function($value){
			this._tree = $value;
		},
		_valid: function(){
			var __frm = dijit.byId(this.fid);
			return __frm.isValid();
		},
		_submit: function(){
			if(this._valid()){
				var __newItem = dijit.byId(this.fid).getValues();
				__newItem.domainid = this._tree.getSelectedItem().id[0];
				if(this.model == "Create"){
					this._store.newItem(__newItem);
				}else{
					this._store.setItem(__newItem);
				}
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
			case "Create":
				this.set("title",$i18n.title_Create);
				break;
			case "Update":
				this.set("title",$i18n.title_Update);
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data_3 = this._grid.getSelectItem();
				if(__data_3 == null) return;
				dijit.byId(this.fid +"_idText").set("value", __data_3.i.id);
				dijit.byId(this.fid +"_groupnameText").set("value", __data_3.i.groupname);
//				dijit.byId(this.fid +"_create_dateText").set("value", __data_3.addtime[0]);
//				dijit.byId(this.fid +"_idText").set("disabled",true);
//				dijit.byId(this.fid +"_idText").set("value",__data.id[0]);
//				dijit.byId(this.fid +"_codetypedescText").set("value", __data.codetypedesc[0]);
			}
			this._valid();
		}
	});
});
