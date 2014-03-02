/**
 * 2012-08-17
 * 黄鑫
 */
dojo.provide("newcapec.dialog.AlertConfirm");
require(["dijit/Dialog",
         "dijit/form/Button"]);
dojo.declare("newcapec.dialog.AlertConfirm",dijit.Dialog,{

	style: "width:250px",
	fn: null,

	constructor: function($params){
		var __msg = dojo.i18n.getLocalization("newcapec.dialog", "AlertConfirm");
		this.id = $params.id;
		this.content = "<div class='dijitDialogPaneContentArea'>" +
						"<div style='height:32px;line-height:20px;'><img style='float:left;' src='"+
						dojo.moduleUrl("newcapec","dialog/images/"+ $params.img) +"'/>&nbsp;&nbsp;&nbsp;&nbsp;"+ $params.msg +"</div>" +
						"</div>" +
						"<div class='dijitDialogPaneActionBar'>" +
						"<div dojoType='dijit.form.Button' id='"+ this.id +"AlertConfirmBtnOK'>"+ __msg.ok +"</div>" +
						"<div dojoType='dijit.form.Button' id='"+ this.id +"AlertConfirmBtnCancel'>"+ __msg.cancel +"</div>" +
						"</div>";
	},

	postCreate: function(){
		this.inherited(arguments);
		dojo.connect(dijit.byId(this.id +'AlertConfirmBtnOK'), "onClick", this, "_btnOK");
		dojo.connect(dijit.byId(this.id +'AlertConfirmBtnCancel'), "onClick", this, "_btnCancel");
	},

	buildRendering: function(){
		this.inherited(arguments);
	},

	startup: function(){
		this.inherited(arguments);
	},

	_btnOK: function(){
		dijit.byId(this.id).hide();
		this.fn();
	},

	_btnCancel: function(){
		dijit.byId(this.id).hide();
	},

	confirm: function($fn){
		this.fn = $fn;
		this.show();
	}
});

