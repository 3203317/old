/*
 * 作者：黄鑫
 * 日期：2013-03-08
 * 描述：角色Toolbar
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"capec/utils/ajax",
	"dojo/_base/connect",
	"capec/Toolbar",
	"dijit/form/Button",
	"dijit/Tooltip",
	"dijit/ToolbarSeparator",
	"capec/ToolbarSpace",
	"internal/widgets/layout/CToolbar"], function($declare, $lang, $ajax, $connect,$Toolbar,$Button,$Tooltip,$ToolbarSeparator,$ToolbarSpace,$CToolbar) {
	return $declare($CToolbar, {
		vpath: null,
		_dialog: null,
		_permitDialog: null,
		_userDialog: null,
		_store: null,
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		bindPermitDialog: function($value){
			this._permitDialog = $value;
		},
		bindUserDialog: function($value){
			this._userDialog = $value;
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
			this._connect4 = this._perBtn.on("click", $lang.hitch(this, "_clickPer"));
//			this._connect5 = this._relatBtn.on("click", $lang.hitch(this, "_clickRelat"));
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
		_clickPer: function(){
			this._permitDialog.show({ model:"Update" });
		},
		_clickUser: function(){
			this._userDialog.show({ model:"Update" });
		},
		disableEdit: function($bool){
			if(this._editBtn.get("optStatus") == 1) this._editBtn.set("disabled", $bool);
		},
		disableDel: function($bool){
			this._delBtn.set("disabled", $bool);
		},
		disablePer: function($bool){
			this._perBtn.set("disabled", $bool);
		},
		disableRelat: function($bool){
//			this._relatBtn.set("disabled", $bool);
		},
		disableUser: function($bool){
			this._userBtn.set("disabled", $bool);
		},
		_addButtons: function(){
			this._toolbar = new $Toolbar({},document.createElement("div"));
			this.domNode.appendChild(this._toolbar.domNode);

			this._addBtn = new $Button({
//				iconClass: 'dijitEditorIcon dijitEditorIconNewPage',
				id: this.id +"_o21",
				label: "新增",
				disabled: false,
				style: "display:none"
			});
			this._editBtn = new $Button({
//				iconClass: 'dijitEditorIcon dijitEditorIconWikiword',
				id: this.id +"_o22",
				label: "编辑",
				disabled: true,
				style: "display:none"
			});
			this._delBtn = new $Button({
//				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				id: this.id +"_o23",
				label: "删除",
				disabled: true,
				style: "display:none"
			});
			this._perBtn = new $Button({
//				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				id: this.id +"_o24",
				label: "权限分配",
				disabled: true,
				style: "display:none"
			});
//			this._relatBtn = new $Button({
//				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
//				label: "角色关系",
//				disabled: true
//			});
			this._userBtn = new $Button({
//				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				id: this.id +"_o25",
				label: "用户分配",
				disabled: true,
				style: "display:none"
			});

			this._toolbar.addChild(this._addBtn);
			this._toolbar.addChild(this._editBtn);
			this._toolbar.addChild(this._delBtn);
			this._toolbar.addChild(new $ToolbarSeparator());

			this._toolbar.addChild(this._perBtn);
//			this._toolbar.addChild(this._relatBtn);
			this._toolbar.addChild(this._userBtn);
		},
		_removeHandles: function(){
			if(this._connect1 != null) this._connect1.remove();
			if(this._connect2 != null) this._connect2.remove();
			if(this._connect3 != null) this._connect3.remove();
			if(this._connect4 != null) this._connect4.remove();
//			if(this._connect5 != null) this._connect5.remove();
			if(this._connect6 != null) this._connect6.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});