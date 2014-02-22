/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：代码类型Grid
 */
define(["dojo/_base/declare",
	"dojo/data/ItemFileReadStore",
	"dojo/_base/connect",
	"dojo/data/ItemFileWriteStore",
	"dojox/grid/EnhancedGrid",
	"dojo/_base/lang",
	"dijit/layout/ContentPane",
	"dojox/grid/_RadioSelector"], function($declare, $ItemFileReadStore, $connect,$ItemFileWriteStore,$EnhancedGrid, $lang, $ContentPane) {
	return $declare($ContentPane, {
		_store: null,
		_dialog: null,
		_codeStore: null,
		bindStore: function($store){
			this._store = $store;
			this._store.bindGrid(this);
		},
		bindDialog: function($dialog){
			this._dialog = $dialog;
			this._dialog.bindGrid(this);
		},
		bindCodeStore: function($codeStore){
			this._codeStore = $codeStore;
		},
		_onDblClickItem: function(){
			if(this._grid.selection.getSelectedCount() == 1) this._dialog.show({ model: "Update" });
		},
		_onSelectedItem: function(){
			this._codeStore.loadData(this._grid.selection.getSelected()[0].id[0]);
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
		_removeHandles: function(){
			this._connect1.remove();
			this._connect2.remove();
		},
		_addGrid: function(){
			this._grid = new $EnhancedGrid({
				style: { height: "100%", margin: 0, padding: 0 ,width:"100%"},
				structure: [{
					type: "dojox.grid._RadioSelector"
				}, {
					cells: [{ name: "代码类型编码", field: "id", width: "130px" }],
					noscroll: true
				}, [
					{ name: "代码类型名称", field: "codetypename", width: "100px" },
					{ name: "代码类型描述", field: "info", width: "100px" },
					{ name: "公开", field: "open", width: "100px" },
					{ name: "用户单位", field: "userunit", width: "100px" },
					{ name: "创建时间", field: "createdate", width: "120px" },
					{ name: "创建人", field: "createuser", width: "80px" },
					{ name: "版本号", field: "ver" }
					]
				]
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._grid, "onSelected", this, "_onSelectedItem");
			this._connect2 = $connect.connect(this._grid, "onDblClick", this, "_onDblClickItem");
		},
		getSelectItem: function(){
			var __items = this._grid.selection.getSelected();
			return __items.length == 1 ? __items[0] : null;
		},
		setStore: function($store){
			this._grid.selection.clear();
			this._grid.setStore($store);
			this.selectItem();
		},
		selectItem: function(){
			this._grid.selection.setSelected(0, true);
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});