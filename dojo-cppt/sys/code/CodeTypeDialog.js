/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：代码类型表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"internal/widgets/MyInfo",
	"dojo/i18n!./nls/CodeTypeDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/SimpleTextarea"], function($connect,$declare,$FormDialog,$MyInfo,$i18n) {
	return $declare($FormDialog,{
		fid: "internal_sys_code_CodeTypeForm",
		title: $i18n.title_Create,
		href: "sys/code/codeTypeForm.html",
		style: { height: "181px", width: "500px" },
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
				dijit.byId(this.fid +"_idText").set("disabled",false);
				var __newItem = dijit.byId(this.fid).getValues();
				if(this.model == "Create"){
					this._store.newItem(__newItem);
				}else{
					dijit.byId(this.fid +"_idText").set("disabled",true);
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
				dijit.byId(this.fid +"_idText").set("disabled", false);
				var __myinfo = $MyInfo(this.vpath);
				this._reset();
				dijit.byId(this.fid +"_userunitText").set("value", __myinfo.corpname);
			}else{
				var __data = this._grid.getSelectItem();
				if(__data == null) return;
				dijit.byId(this.fid +"_codeText").set("value", __data.code[0]);
				dijit.byId(this.fid +"_idText").set("value", __data.id[0]);

				dijit.byId(this.fid +"_codetypenameText").set("value", __data.codetypename[0]);
				dijit.byId(this.fid +"_userunitText").set("value", __data.userunit[0]);
				dijit.byId(this.fid +"_infoText").set("value", __data.info[0]);
				dijit.byId(this.fid +"_openidText").set("value", __data.openid[0]);

				dijit.byId(this.fid +"_idText").set("disabled", true);
			}

			dijit.byId(this.fid +"_userunitText").set("disabled", true);
			this._valid();
		}
	});
});
