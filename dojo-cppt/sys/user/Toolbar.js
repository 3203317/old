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
	"dijit/Dialog"], function($declare, $lang, $connect,$Toolbar,$Button,$Tooltip,$ToolbarSeparator,$ContentPane,$Dialog) {
	return $declare($ContentPane, {
		_dialog: null,
		_batchDialog: null,
		_passDialog: null,
		_statDialog: null,
		_importDialog: null,
		_store: null,
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		bindBatchDialog: function($batchDialog){
			this._batchDialog = $batchDialog;
		},
		bindPassDialog: function($value){
			this._passDialog = $value;
		},
		bindStatDialog: function($value){
			this._statDialog = $value;
		},
		bindImportDialog: function($value){
			this._importDialog = $value;
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
			this._connect4 = this._writeBtn.on("click", $lang.hitch(this, "_clickWrite"));

			this._connect5 = this._importBtn.on("click", $lang.hitch(this, "_clickImport"));
			this._connect6 = this._uploadPicBtn.on("click", $lang.hitch(this, "_clickPicUp"));

			this._connect7 = this._passBtn.on("click", $lang.hitch(this, "_clickPass"));
			this._connect8 = this._statBtn.on("click", $lang.hitch(this, "_clickStat"));
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
		_clickPass: function(){
			this._passDialog.show({ model:"Update" });
		},
		_clickStat: function(){
			this._statDialog.show({ model:"Update" });
		},
		_clickWrite: function(){
			this._batchDialog.show({ model:"Create" });
		},
		_clickImport: function(){
			this._importDialog.show({ model: "Update" });
		},
		_clickPicUp: function(){
			this._upPicDialog = new $Dialog({
				title: '批量上传照片',
				style: "width:500px;height:330px",
				content: "<iframe frameborder='0' height='330px' width='100%' src='userPhotoUpload.html' />",
				loadingMessage: "正在打开,请稍等...",
				onHide: function(){
					this.destroyRecursive();
				}
			});
	        this._upPicDialog.show();
		},
		disableEdit: function($bool){
			this._editBtn.set("disabled", $bool);
		},
		disableDel: function($bool){
			this._delBtn.set("disabled", $bool);
		},
		disablePass: function($bool){
			this._passBtn.set("disabled", $bool);
		},
		disableStat: function($bool){
			this._statBtn.set("disabled", $bool);
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
			this._importBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "批量数据导入",
				disabled: false
			});
			this._writeBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "批量数据录入",
				disabled: false
			});
			this._uploadPicBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "批量上传照片",
				disabled: false
			});
			this._passBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "重置密码",
				disabled: true
			});
			this._statBtn = new $Button({
				iconClass: 'dijitEditorIcon dijitEditorIconDelete',
				label: "调整状态",
				disabled: true
			});

			this._toolbar.addChild(this._addBtn);
			this._toolbar.addChild(this._editBtn);
			this._toolbar.addChild(this._delBtn);
			this._toolbar.addChild(new $ToolbarSeparator({}));

//			this._toolbar.addChild(this._importBtn);
			this._toolbar.addChild(this._writeBtn);
			this._toolbar.addChild(this._uploadPicBtn);
			this._toolbar.addChild(new $ToolbarSeparator({}));

			this._toolbar.addChild(this._passBtn);
			this._toolbar.addChild(this._statBtn);
		},
		_removeHandles: function(){
			if(this._connect1 != null) this._connect1.remove();
			if(this._connect2 != null) this._connect2.remove();
			if(this._connect3 != null) this._connect3.remove();
			if(this._connect4 != null) this._connect4.remove();


			if(this._connect7 != null) this._connect7.remove();
			if(this._connect8 != null) this._connect8.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});