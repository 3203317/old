/**
 * 作者：黄鑫
 * 日期：2013-03-08
 * 描述：代码类型Toolbar
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/_base/connect",
	"dijit/Toolbar",
	"dijit/form/Button",
	"dijit/Tooltip",
	"dijit/layout/ContentPane",
	"dijit/form/Form",
	"dijit/form/ValidationTextBox"], function($declare, $lang, $connect,$Toolbar,$Button,$Tooltip,$ContentPane) {
	return $declare($ContentPane, {
		_dialog: null,
		_store: null,
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		bindStore: function($store){
			this._store = $store;
		},
		postCreate : function() {
			this.inherited(arguments);
			this._addButtons();
		},
		startup:function(){
			this.inherited(arguments);
			this._addEvents();
		},
		_addEvents: function(){
			this._connect1 = this._addBtn.on("click", $lang.hitch(this, "_clickAdd"));
			this._connect2 = this._editBtn.on("click", $lang.hitch(this, "_clickEdit"));
			this._connect3 = this._delBtn.on("click", $lang.hitch(this, "_clickDel"));
		},
		_clickAdd: function(){
			this._dialog.show({ model: "Create" });
		},
		_clickEdit: function(){
			this._dialog.show({ model:"Update" });
		},
		_clickDel: function(){
			$connect.publish("/show/DeleteDialog/", [$lang.hitch(this._store, "removeItem")]);
		},
		disableDel: function($bool){
			this._delBtn.set("disabled", $bool);
		},
		_addButtons: function(){
			this._toolbar = new $Toolbar({},document.createElement("div"));
			this.domNode.appendChild(this._toolbar.domNode);

			this._addBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconNewPage',
				label: "新增"
			});
			this._editBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconWikiword',
				label: "编辑"
			});
			this._delBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "删除",
				disabled: true
			});

			this._toolbar.addChild(this._addBtn);
			this._toolbar.addChild(this._editBtn);
			this._toolbar.addChild(this._delBtn);
		},
		_removeHandles: function(){
			this._connect1.remove();
			this._connect2.remove();
			this._connect3.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});