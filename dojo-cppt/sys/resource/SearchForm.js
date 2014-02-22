/*
 * 作者：黄鑫
 * 日期：2013-03-08
 * 描述：用户管理Toolbar
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/_base/connect",
	"dijit/Toolbar",
	"dijit/form/Button",
	"dijit/Tooltip",
	"dijit/ToolbarSeparator",
	"dijit/layout/ContentPane",
	"dijit/form/Form",
	"dijit/form/ValidationTextBox",
	"dijit/form/CheckBox"], function($declare, $lang, $connect,$Toolbar,$Button,$Tooltip,$ToolbarSeparator,$ContentPane) {
	return $declare($ContentPane, {
		_store: null,
		bindStore: function($store){
			this._store = $store;
		},
		href: "sys/resource/searchForm.html",
		constructor: function(){
			this.href = arguments[0].vpath + this.href;
			this.inherited(arguments);
		},
		postCreate : function() {
			this.inherited(arguments);
		},
		onLoad: function(){
			var __frmChildren = this.getChildren()[0].getChildren();
			var __searchBtn = __frmChildren[__frmChildren.length-1];
			this._connect1 = __searchBtn.on("click", $lang.hitch(this, "_click"));
		},
		_click: function(){
			var __frm = this.getChildren()[0];
			var __param = __frm.getValues();
			__param.searchall = __param.searchall.length == 0 ? "0" : "1";
			this._store.searchData(__param);
		},
		reset: function(){
			this.getChildren()[0].reset();
		},
		_removeHandles: function(){
			if(this._connect1 != null) this._connect1.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});