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
	"dijit/layout/ContentPane"], function($declare, $lang, $connect,$Toolbar,$Button,$Tooltip,$ToolbarSeparator,$ContentPane) {
	return $declare($ContentPane, {
		_dialog: null,
		_relatDialog: null,
		_userDialog: null,
		_roleDialog: null,
		_store: null,
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		bindRelatDialog: function($value){
			this._relatDialog = $value;
		},
		bindUserDialog: function($value){
			this._userDialog = $value;
		},
		bindRoleDialog: function($value){
			this._roleDialog = $value;
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
			this._connect4 = this._relatBtn.on("click", $lang.hitch(this, "_clickRelat"));
			this._connect5 = this._roleBtn.on("click", $lang.hitch(this, "_clickRole"));
			this._connect6 = this._userBtn.on("click", $lang.hitch(this, "_clickUser"));
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
		_clickRelat: function(){
			this._relatDialog.show({ model:"Update" });
		},
		_clickRole: function(){
			this._roleDialog.show({ model:"Update" });
		},
		_clickUser: function(){
			this._userDialog.show({ model:"Update" });
		},
		disableEdit: function($bool){
			this._editBtn.set("disabled", $bool);
		},
		disableRelat: function($bool){
			this._relatBtn.set("disabled", $bool);
		},
		disableRole: function($bool){
			this._roleBtn.set("disabled", $bool);
		},
		disableUser: function($bool){
			this._userBtn.set("disabled", $bool);
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
				label: "编辑",
				disabled: true
			});
			this._delBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "删除",
				disabled: true
			});
			this._relatBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "组关系管理",
				disabled: true
			});
			this._roleBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "角色分配",
				disabled: true
			});
			this._userBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "用户分配",
				disabled: true
			});

			this._toolbar.addChild(this._addBtn);
			this._toolbar.addChild(this._editBtn);
			this._toolbar.addChild(this._delBtn);
			this._toolbar.addChild(new $ToolbarSeparator());
			this._toolbar.addChild(this._relatBtn);
			this._toolbar.addChild(this._roleBtn);
			this._toolbar.addChild(this._userBtn);
		},
		_removeHandles: function(){
			if(this._connect1 != null) this._connect1.remove();
			if(this._connect2 != null) this._connect2.remove();
			if(this._connect3 != null) this._connect3.remove();
			if(this._connect4 != null) this._connect4.remove();
			if(this._connect5 != null) this._connect5.remove();
			if(this._connect6 != null) this._connect6.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});