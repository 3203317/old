/**
 * 2012-08-17
 * 黄鑫
 */
dojo.provide("newcapec.dialog.AlertDialog");
require(["dijit/Dialog",
         "dijit/form/Button"]);
dojo.declare("newcapec.dialog.AlertDialog",dijit.Dialog,{

	style: "width:250px",

	constructor: function($params){
		var __msg = dojo.i18n.getLocalization("newcapec.dialog", "AlertConfirm");
		this.id = $params.id;
		this.content = "<div class='dijitDialogPaneContentArea'>" +
						"<div style='height:32px;line-height:32px;'><img style='float:left;' src='"+
						dojo.moduleUrl("newcapec","dialog/images/"+ $params.img) +"'/>&nbsp;&nbsp;&nbsp;&nbsp;"+ $params.msg +"</div>" +
						"</div>" +
						"<div class='dijitDialogPaneActionBar'>" +
						"<div dojoType='dijit.form.Button' id='"+ this.id +"AlertDialogBtnOK'>"+ __msg.ok +"</div>" +
						"</div>";
	},

	postCreate: function(){
		this.inherited(arguments);
		dojo.connect(dijit.byId(this.id +'AlertDialogBtnOK'), "onClick", this, "_btnOK");
	},

	_btnOK: function(){
		dijit.byId(this.id).hide();
	}
});

