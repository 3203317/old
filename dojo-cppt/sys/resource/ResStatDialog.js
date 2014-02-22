/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：组织表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dojo/i18n!./nls/ResourceDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"internal/widgets/comboBox/BatchComboBox",
	"internal/widgets/comboBox/DomainComboBox",
	"internal/widgets/comboBox/ResTypeComboBox",
	"internal/widgets/comboBox/SmallPicComboBox",
	"dijit/form/DateTextBox",
	"dijit/form/SimpleTextarea"], function($connect,$declare,$FormDialog,$i18n) {
	return $declare($FormDialog,{
		fid: "internal_sys_resource_ResStatForm",
		title: $i18n.title_Create,
		href: "sys/resource/resStatForm.html",
		style: { height: "106px", width: "480px" },
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
				var __data_3 = this._grid.getSelectItem();
				this.set("title","调整状态 [资源名称："+ __data_3.i.resourcename +"]");
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data_3 = this._grid.getSelectItem();
				if(__data_3 == null) return;
				console.log(__data_3);
				dijit.byId(this.fid +"_idText").set("value", __data_3.i.id);
				dijit.byId(this.fid +"_restatidText").set("value", 3);
				dijit.byId(this.fid +"_statusidText").set("value", 2);
			}
			this._valid();
		}
	});
});
