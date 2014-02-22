/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：代码类型表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dijit/form/Form",
	"internal/widgets/comboBox/CodeComboBox",
	"internal/sys/log/LogTypeComboBox",
	"capec/form/ComboBoxTree",
	"dijit/form/ValidationTextBox"], function($connect,$declare,$FormDialog) {
	return $declare($FormDialog,{
		fid: "internal_sys_log_LogForm",
		title: "新增",
		href: "sys/log/logForm.html",
		style: { height: "235px", width: "500px" },
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
				switch(this.model){
				case "Create":
					this._store.newItem(__newItem);
					break;
				case "Update":
					this._store.setItem(__newItem);
					break;
				}
			}else{
				dijit.byId(this.fid).validate();
			}
		},
		_reset: function(){
			switch(this.model){
			case "Create":
				break;
			case "Update":
				this._setData();
				break;
			}
		},
		_draw: function(){
			console.log("绘制表单");
			console.log("Ajax请求需同步或其他方式");
		},
		_setTitle: function(){
			switch(this.model){
			case "Create":
				this.set("title","新增")	;
				break;
			case "Update":
				this.set("title","编辑")	;
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data = this._grid.getSelectItem();
				if(__data == null) return;
				dijit.byId(this.fid +"_lognameText").set("value", __data.logname[0]);
//				dijit.byId(this.fid +"_codekeyText").set("value", __data.codekey[0]);
//				dijit.byId(this.fid +"_codenameText").set("value", __data.codename[0]);
//				dijit.byId(this.fid +"_codealiasText").set("value", __data.codealias[0]);
//
//				dijit.byId(this.fid +"_userunitText").set("value", __data.userunit[0]);
//				dijit.byId(this.fid +"_infoText").set("value", __data.info[0]);
//				dijit.byId(this.fid +"_openidText").set("value", __data.openid[0]);
//				dijit.byId(this.fid +"_sortText").set("value", __data.sort[0]);
//
//				dijit.byId(this.fid +"_codekeyText").set("disabled", true);
			}
			this._valid();
		}
	});
});
