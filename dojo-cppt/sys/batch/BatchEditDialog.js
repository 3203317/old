/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：角色表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dojo/i18n!./nls/BatchDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/NumberSpinner",
	"dijit/form/DateTextBox",
	"dijit/form/Select",
	"dijit/form/SimpleTextarea",
	"dijit/form/CheckBox",
	"dijit/form/RadioButton"], function($connect,$declare,$FormDialog,$i18n) {
	return $declare($FormDialog,{
		fid: "internal_sys_batch_BatchEditForm",
		title: $i18n.title_Create,
		href: "sys/batch/batchEditForm.html",
		style: { height: "345px", width: "350px" },
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
				__newItem.begindate = dijit.byId(this.fid +"_begindateText").displayedValue;
				__newItem.enddate = dijit.byId(this.fid +"_enddateText").displayedValue;
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
			$connect.connect(dijit.byId(this.fid +"_dateChk"), "onClick", this, "_setValidDate");
		},
		_setValidDate: function(){
			if(dijit.byId(this.fid +"_begindateText").get("disabled")){
				dijit.byId(this.fid +"_begindateText").set("disabled",false);
				dijit.byId(this.fid +"_enddateText").set("disabled", false);
			}else{
				dijit.byId(this.fid +"_begindateText").set("disabled",true);
				dijit.byId(this.fid +"_enddateText").set("disabled", true);
			}
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
				var __data = this._grid.getSelectItem();
				if(__data == null) return;

				dijit.byId(this.fid +"_idText").set("value", __data.i.id);
				dijit.byId(this.fid +"_batchnameText").set("value", __data.i.batchname);
				dijit.byId(this.fid +"_statusidText").set("value", __data.i.statusid);
				dijit.byId(this.fid +"_infoText").set("value", __data.i.info);
				dijit.byId(this.fid +"_begindateText").set("value", __data.i.begindate);
				dijit.byId(this.fid +"_enddateText").set("value", __data.i.enddate);
			}

			this._valid();
		}
	});
});
