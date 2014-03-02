/**
 * 2012-08-17
 * 黄鑫
 */
dojo.provide("newcapec.dialog.FormDialog");
require(["dijit/Dialog",
         "dijit/form/Button"]);
dojo.declare("newcapec.dialog.FormDialog",dijit.Dialog,{

	style: "width:250px",
	_href: null,
	_state: 'add',

	constructor: function($params){
		this.id = $params.id;
		this._href = $params.url;
		this._state = $params.state;
		var __btns = [];
		dojo.forEach($params.buttons, function($item){
			__btns.push("<button class='foreDialogPaneButton' data-dojo-type='dijit.form.Button' data-dojo-props='type:\"button\"' id='"+ this.id +"_"+ $item[0] +"'>"+ $item[1] +"</button>");
		});
		this.content = "<div id='"+ this.id +"_' data-dojo-type='dojox.layout.ContentPane' data-dojo-props='href:\""+ this._href +"\"'>" +
							"<script type='dojo/method' event='onLoad'>" +
								"eval('"+ this.id +"_.init()');" +
								"dijit.byId('"+ this.id +"').layout();" +
							"</script>" +
						"</div>" +
						"<div class='foreDialogPaneActionBar'>" +
							__btns.join("") +
							"<button class='foreDialogPaneButton' data-dojo-type='dijit.form.Button' data-dojo-props='type:\"button\"' id='"+ this.id +"_btn_submit'>提交</button>" +
							"<button class='foreDialogPaneButton' data-dojo-type='dijit.form.Button' data-dojo-props='type:\"button\"' id='"+ this.id +"_btn_reset'>重置</button>" +
						"</div>";
	},
	setState: function($state){
		this._state = $state;
	},
	onCancel: function(){
		console.log("onCancel");
		this.hide();
	}
});

