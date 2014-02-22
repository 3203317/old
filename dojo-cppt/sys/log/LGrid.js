/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：日志类型Grid
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
		_logStore: null,
		bindStore: function($value){
			this._store = $value;
			this._store.bindGrid(this);
		},
		bindLogStore: function($value){
			this._logStore = $value;
			this._logStore.bindTypeGrid(this);
		},
		_onSelectedItem: function(){
			this._logStore.loadData(this._grid.selection.getSelected()[0].id[0]);
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
		},
		_addGrid: function(){
			this._grid = new $EnhancedGrid({
				rowSelector: "5px",
				style: { height: "100%", margin: 0, padding: 0 ,width:"100%"},
				structure: [[
					{ name: "日志类型名称", field: "logtypename", width: "auto" }
					]
				]
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._grid, "onSelected", this, "_onSelectedItem");
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