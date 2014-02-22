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
		fid: "internal_sys_org_BatchForm",
		title: "",
		href: "sys/org/batchForm.html",
		style: { height: "286px", width: "370px" },
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
			$connect.connect(dijit.byId(this.fid +"_batchidText"), "onChange", this, "_setBatchDate");
			$connect.connect(dijit.byId(this.fid +"_dateChk"), "onClick", this, "_setValidDate");
		},
		_setBatchDate: function(){
			if(dijit.byId(this.fid +"_batchidText").item != null){
				dijit.byId(this.fid +"_begindateText").set("value", dijit.byId(this.fid +"_batchidText").item.begindate);
				dijit.byId(this.fid +"_enddateText").set("value", dijit.byId(this.fid +"_batchidText").item.enddate);
			}
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
				//dijit.byId(this.fid).reset();
				dijit.byId(this.fid +"_idText").set("value", __data.i.id);
				dijit.byId(this.fid +"_orgnameText").set("value", __data.i.orgname);
				dijit.byId(this.fid +"_orgnameText").set("disabled", true);
				dijit.byId(this.fid +"_batchidText").setDisplayedValue(__data.i.batchname);
			}

			this._valid();
		}
	});
});
