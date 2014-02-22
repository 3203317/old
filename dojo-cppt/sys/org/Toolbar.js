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
	"dijit/ToolbarSeparator"], function($declare, $lang, $connect,$Toolbar,$Button,$Tooltip,$ToolbarSeparator,$ContentPane,$ToolbarSeparator) {
	return $declare("internal/sys/org/Toolbar", $ContentPane, {
		_dialog: null,
		_statDialog: null,
		_batchDialog: null,
		_store: null,
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		bindStatDialog: function($value){
			this._statDialog = $value;
		},
		bindBatchDialog: function($value){
			this._batchDialog = $value;
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

			this._connect3 = this._statBtn.on("click", $lang.hitch(this, "_clickStat"));
			this._connect3 = this._batchBtn.on("click", $lang.hitch(this, "_clickBatch"));
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
		_clickStat: function(){
			this._statDialog.show({ model:"Update" });
		},
		_clickBatch: function(){
			this._batchDialog.show({ model:"Update" });
		},
		disableEdit: function($bool){
			this._editBtn.set("disabled", $bool);
		},
		disableDel: function($bool){
			this._delBtn.set("disabled", $bool);
		},
		disableStat: function($bool){
			this._statBtn.set("disabled", $bool);
		},
		disableBatch: function($bool){
			this._batchBtn.set("disabled", $bool);
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
				label: "编辑",
				disabled: true
			});
			this._delBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "删除",
				disabled: true
			});
			this._statBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "调整状态",
				disabled: true
			});
			this._batchBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "调整批次",
				disabled: true
			});

			this._toolbar.addChild(this._addBtn);
			this._toolbar.addChild(this._editBtn);
			this._toolbar.addChild(this._delBtn);
			this._toolbar.addChild(new $ToolbarSeparator());

			this._toolbar.addChild(this._statBtn);
			this._toolbar.addChild(this._batchBtn);
		},
		_removeHandles: function(){
			if(this._connect1 != null) this._connect1.remove();
			if(this._connect2 != null) this._connect2.remove();
			if(this._connect3 != null) this._connect3.remove();
			if(this._connect4 != null) this._connect4.remove();
			if(this._connect5 != null) this._connect5.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});