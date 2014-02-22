/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：代码Grid
 */
define(["dojo/_base/declare",
	"dojox/grid/TreeGrid",
	"dijit/tree/ForestStoreModel",
	"dojo/_base/lang",
	"dojo/data/ItemFileReadStore",
	"dojo/_base/connect",
	"dijit/layout/ContentPane",
	"dojo/data/ItemFileWriteStore",
	"dojox/grid/_RadioSelector"], function($declare, $TreeGrid, $ForestStoreModel, $lang, $ItemFileReadStore, $connect, $ContentPane,$ItemFileWriteStore) {
	return $declare($ContentPane, {
		vpath: "",
		_dialog: null,
		_toolbar: null,
		_store: null,
		bindStore: function($store){
			this._store = $store;
			this._store.bindGrid(this);
		},
		bindDialog: function($dialog){
			this._dialog = $dialog;
			this._dialog.bindGrid(this);
		},
		bindToolbar: function($toolbar){
			this._toolbar = $toolbar;
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments.vpath) this.vpath = arguments.vpath;
		},
		postCreate : function() {
			this.inherited(arguments);
			this._addGrid();
		},
		startup:function(){
			this.inherited(arguments);
			this._grid.startup();
			this._addEvents();
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._grid, "onSelected", this, "_onSelectedItem");
			this._connect2 = $connect.connect(this._grid, "onDeselected", this, "_onSelectedItem");
			this._connect3 = $connect.connect(this._grid, "onDblClick", this, "_onDblClickItem");
		},
		_onDblClickItem: function(){
			if(this._grid.selection.getSelectedCount() == 1) this._dialog.show({ model: "Update" });
		},
		_onSelectedItem: function(){
			var __bool = this._grid.selection.getSelectedCount() != 1;
			this._toolbar.disableEdit(__bool);
			this._toolbar.disableAddChild(__bool);
			this._toolbar.disableDel(__bool ? true : (this._grid.selection.getSelected()[0].children != undefined));
		},
		_addGrid: function(){
			this._treeModel = new $ForestStoreModel({
				store: new $ItemFileWriteStore({
					data: {
						identifier: "id",
						label: "codename",
						items: []
					}
				}),
				query: { type: "code" },
				rootId: "0",
				rootLabel: "代码名称",
				childrenAttrs: [ "children" ]
			});
			this._grid = new $TreeGrid({
				keepSelection: true,
				defaultOpen: true,
				rowSelector: true,
				style: { height: "100%", margin: 0, padding: 0 },
				treeModel: this._treeModel,
				structure: [
					{ name: "代码名称", field: "codename", width: "100px" },
					{ name: "代码别名", field: "codealias", width: "100px" },
					{ name: "代码编码", field: "codekey", width: "100px" },
					{ name: "代码描述", field: "info", width: "150px" },
					{ name: "排序", field: "sort", width: "100px" },
					{ name: "公开", field: "open", width: "100px" },
					{ name: "用户单位", field: "userunit", width: "100px" },
					{ name: "创建时间", field: "createdate", width: "120px" },
					{ name: "创建人", field: "createuser", width: "80px" },
					{ name: "版本号", field: "ver" }
				]
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		setStore: function($store){
			this._grid.selection.clear();
			if($store == null) {
				this._grid.setStore($store);
			}else{
				this._treeModel.destroy();
				this._treeModel.store = null;
				this._treeModel = null;
				this._treeModel = new $ForestStoreModel({
					store: $store,
					query: { type: "code" },
					rootId: "0",
					rootLabel: "代码名称",
					childrenAttrs: [ "children" ]
				});
				this._grid.setModel(this._treeModel);
			}
		},
		getSelectItem: function(){
			var __items = this._grid.selection.getSelected();
			return __items.length == 1 ? __items[0] : null;
		},
		_removeHandles: function(){
			this._connect1.remove();
			this._connect2.remove();
			this._connect3.remove();
		},
		destroy: function(){
			console.log("代码Grid资源释放");
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});