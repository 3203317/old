/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：代码类型表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"internal/widgets/MyInfo",
	"capec/dialog/FormDialog",
	"dijit/form/NumberSpinner"], function($connect,$declare,$MyInfo,$FormDialog) {
	return $declare($FormDialog,{
		fid: "internal_sys_code_CodeForm",
		title: "新增",
		href: "sys/code/codeForm.html",
		style: { height: "205px", width: "500px" },
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
				case "CreateChild":
					var __data = this._grid.getSelectItem();
					__newItem.p_codekey = __data.codekey[0];
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
			case "CreateChild":
				dijit.byId(this.fid).reset();
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
			case "CreateChild":
				this.set("title","新增子项")	;
				break;
			case "Update":
				this.set("title","编辑")	;
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create" || this.model == "CreateChild"){
				dijit.byId(this.fid +"_codekeyText").set("disabled", false);
				var __myinfo = $MyInfo(this.vpath);
				this._reset();
				dijit.byId(this.fid +"_userunitText").set("value", __myinfo.corpname);
			}else{
				var __data = this._grid.getSelectItem();
				if(__data == null) return;

				dijit.byId(this.fid +"_codeText").set("value", __data.code[0]);
				dijit.byId(this.fid +"_codekeyText").set("value", __data.codekey[0]);
				dijit.byId(this.fid +"_codenameText").set("value", __data.codename[0]);
				dijit.byId(this.fid +"_codealiasText").set("value", __data.codealias[0]);

				dijit.byId(this.fid +"_userunitText").set("value", __data.userunit[0]);
				dijit.byId(this.fid +"_infoText").set("value", __data.info[0]);
				dijit.byId(this.fid +"_openidText").set("value", __data.openid[0]);
				dijit.byId(this.fid +"_sortText").set("value", __data.sort[0]);

				dijit.byId(this.fid +"_codekeyText").set("disabled", true);
			}

			dijit.byId(this.fid +"_userunitText").set("disabled", true);
			this._valid();
		}
	});
});
