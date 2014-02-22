/**
 * 作者：黄鑫
 * 日期：2013-04-01
 * 描述：左侧组织机构树
 */
define(["dojo/_base/declare",
	"capec/EnhancedTree",
	"dijit/tree/ForestStoreModel",
	"dojo/_base/lang",
	"dojo/_base/connect",
	"dijit/layout/ContentPane"], function($declare, $Tree, $ForestStoreModel, $lang, $connect, $ContentPane) {
	return $declare($ContentPane, {
		vpath: "",
		_gridStore: null,
		_store: null,
		_searchForm: null,
		bindStore: function($store){
			this._store = $store;
			this._store.bindTree(this);
		},
		bindGridStore: function($gridStore){
			this._gridStore = $gridStore;
			this._gridStore.bindTree(this);
		},
		bindSearchForm: function($searchForm){
			this._searchForm = $searchForm;
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments.vpath) this.vpath = arguments.vpath;
		},
		postCreate : function() {
			this.inherited(arguments);
		},
		startup:function(){
			this.inherited(arguments);
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._tree, "onSelected", this, "_onSelectedItem");
		},
		_onSelectedItem: function($item){
			this._gridStore.loadData($item.id);
			this._searchForm.reset();
		},
		_addTree: function($store){
			this._tree = new $Tree({
				showRoot: false,
				openOnDblClick: true,
				keepSelection: true,
				defaultOpen: true,
				rowSelector: true,
				autoExpand: true,
				style: { height: "100%", margin: 0, padding: 0 },
				model: new $ForestStoreModel({
					store: $store,
					query: { type: "*" },
					rootId: "0",
					rootLabel: "郑州新开普电子股份有限公司",
					childrenAttrs: [ "children" ]
				})
			},document.createElement("div"));
			this.domNode.appendChild(this._tree.domNode);
		},
		setStore: function($store){
			if($store != null) {
				this._addTree($store);
				this._addEvents();
				this._tree.set("path", ["0","1"]);
			}
		},
		getSelectedItem: function(){
			return this._tree.selectedItem;
		},
		_removeHandles: function(){
			this._connect1.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});