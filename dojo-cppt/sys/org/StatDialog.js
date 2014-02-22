/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：角色表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/NumberSpinner",
	"dijit/form/DateTextBox",
	"dijit/form/Select",
	"dijit/form/SimpleTextarea",
	"dijit/form/CheckBox",
	"dijit/form/RadioButton"], function($connect,$declare,$FormDialog) {
	return $declare($FormDialog,{
		fid: "internal_sys_org_StatForm",
		title: "",
		href: "sys/org/statForm.html",
		style: { height: "220px", width: "350px" },
		_grid: null,
		_store: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		bindStore: function($store){
			this._store = $store;
			this._store.bindDialog(this);
		},
		_valid: function(){
			var __frm = dijit.byId(this.fid);
			return __frm.isValid();
		},
		_submit: function(){
			if(this._valid()){
				var __newItem = dijit.byId(this.fid).getValues();
				if(this.model == "Create"){
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
				this.set("title","调整状态");
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data = this._grid.getSelectItem();
				if(__data == null) return;
				dijit.byId(this.fid).reset();
				dijit.byId(this.fid +"_idText").set("value", __data.i.id);
				dijit.byId(this.fid +"_orgnameText").set("value", __data.i.orgname);
				dijit.byId(this.fid +"_orgnameText").set("disabled", true);
				dijit.byId(this.fid +"_statusidText").set("value", 2);
			}

			this._valid();
		}
	});
});
