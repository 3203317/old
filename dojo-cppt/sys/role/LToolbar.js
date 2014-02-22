/**
 * 作者：黄鑫
 * 日期：2013-03-08
 * 描述：角色组Toolbar
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/_base/connect",
	"dijit/Toolbar",
	"dijit/form/Button",
	"dijit/Tooltip",
	"dijit/layout/ContentPane"], function($declare, $lang, $connect,$Toolbar,$Button,$Tooltip,$ContentPane) {
	return $declare("internal.sys.role.LToolbar", $ContentPane, {
		postCreate : function() {
			this.inherited(arguments);
			this._addButtons();
		},
		startup:function(){
			this.inherited(arguments);
			this._addSubscribes();
			this._addEvents();
		},
		_addSubscribes: function(){
			this._handle1 = $connect.subscribe("/internal/sys/role/LToolbar/DelBtn/disable/", this, "_onDisableDelBtn");
		},
		_onDisableDelBtn: function($boolean){
			this._delBtn.set("disabled",$boolean);
		},
		_removeHandles: function(){
			this._handle1.remove();

			this._connect1.remove();
			this._connect2.remove();
			this._connect3.remove();
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._addBtn, "onClick", this, "_showAddDlg");
			this._connect2 = $connect.connect(this._editBtn, "onClick", this, "_showEditDlg");
			this._connect3 = $connect.connect(this._delBtn, "onClick", this, "_showDelDlg");
		},
		_showAddDlg: function(){
			console.log("显示角色组新增对话框");
		},
		_showEditDlg: function(){
			console.log("显示角色组编辑对话框");
		},
		_showDelDlg: function(){
			console.log("显示角色组删除确认对话框");
		},
		_addButtons: function(){
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

			this._toolbar = new $Toolbar({},document.createElement("div"));

			this._toolbar.addChild(this._addBtn);
			this._toolbar.addChild(this._editBtn);
			this._toolbar.addChild(this._delBtn);

			this.domNode.appendChild(this._toolbar.domNode);
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});