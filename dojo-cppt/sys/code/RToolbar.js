/**
 * 作者：黄鑫
 * 日期：2013-03-08
 * 描述：代码Toolbar
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/_base/connect",
	"dijit/Toolbar",
	"dijit/form/Button",
	"dijit/Tooltip",
	"dijit/form/ComboButton",
	"dijit/Menu",
	"dijit/MenuItem",
	"dijit/layout/ContentPane"], function($declare, $lang, $connect,$Toolbar,$Button,$Tooltip,$ComboButton,$Menu,$MenuItem,$ContentPane) {
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
		disableEdit: function($bool){
			this._editBtn.set("disabled",$bool);
		},
		disableDel: function($bool){
			this._delBtn.set("disabled",$bool);
		},
		disableAddChild: function($bool){
			this._addChildBtn.set("disabled",$bool);
		},
		_addEvents: function(){
			this._connect1 = this._addBtn.on("click", $lang.hitch(this, "_clickAdd"));
			this._connect2 = this._addChildBtn.on("click", $lang.hitch(this, "_clickAddChild"));
			this._connect3 = this._editBtn.on("click", $lang.hitch(this, "_clickEdit"));
			this._connect4 = this._delBtn.on("click", $lang.hitch(this, "_clickDel"));
		},
		_clickAdd: function(){
			this._dialog.show({model:"Create"});
		},
		_clickAddChild: function(){
			this._dialog.show({model:"CreateChild"});
		},
		_clickEdit: function(){
			this._dialog.show({model:"Update"});
		},
		_clickDel: function(){
			$connect.publish("/show/DeleteDialog/", [$lang.hitch(this._store, "removeItem")]);
		},
		_addButtons: function(){
			this._addChildBtn = new $MenuItem({
				iconClass: "dijitEditorIcon dijitEditorIconNewPage",
				disabled: true,
				label: "新增子项"
			});

			var __addMenu = new $Menu({ style:{ display: "none" } });
			__addMenu.addChild(this._addChildBtn);

			this._addBtn = new $ComboButton({
				iconClass: 'dijitEditorIcon dijitEditorIconNewPage',
				label: "新增",
				optionsTitle: "新增选项",
				showLabel: true,
				dropDown: __addMenu
			});

			this._editBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconWikiword',
				disabled:true,
				label: "编辑"
			});
			this._delBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				disabled:true,
				label: "删除"
			});

			this._toolbar = new $Toolbar({},document.createElement("div"));

			this._toolbar.addChild(this._addBtn);
			this._toolbar.addChild(this._editBtn);
			this._toolbar.addChild(this._delBtn);

			this.domNode.appendChild(this._toolbar.domNode);
		},
		_removeHandles: function(){
			this._connect1.remove();
			this._connect2.remove();
			this._connect3.remove();
			this._connect4.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});