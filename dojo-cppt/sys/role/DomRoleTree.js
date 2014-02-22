/**
 * 作者：黄鑫
 * 日期：2013-04-01
 * 描述：域角色树
 */
define(["dojo/_base/declare",
	"capec/EnhancedTree",
	"dijit/tree/ForestStoreModel",
	"dojo/_base/lang",
	"dojo/_base/connect",
	"capec/utils/ajax",
	"capec/utils/toTreeObj",
	"dojo/data/ItemFileWriteStore",
	"dijit/layout/ContentPane"], function($declare, $Tree, $ForestStoreModel, $lang, $connect, $ajax, $toTreeObj, $ItemFileWriteStore, $ContentPane) {
	return $declare("internal.sys.role.DomRoleTree", $ContentPane, {
		vpath: "",
		_store: null,
		bindStore: function($store){
			this._store = $store;
			this._store.bindTree(this);
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments.vpath) this.vpath = arguments.vpath;
		},
		postCreate : function() {
			this.inherited(arguments);
			this._loadData();
		},
		startup:function(){
			this.inherited(arguments);
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._tree, "onSelected", this, "_onSelectedItem");
		},
		_onSelectedItem: function($item){
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
		_loadData: function(){
			$ajax({
				async : true,
				url : this.vpath +"testData/domainrole.json",
				callback : $lang.hitch(this, "_onDataLoaded")
			});
		},
		_onDataLoaded: function($data){
			var __items = $toTreeObj($data,0,{
				identifier: "id",
				fidentifier: "p_id",
				label: "itemname"
			}).children;

			this._store = new $ItemFileWriteStore({
				data: {
					identifier: "id",
					label: "itemname",
					items: __items == undefined ? [] : __items
				}
			});

			this._setStore(this._store);
		},
		_setStore: function($store){
			if($store != null) {
				this._addTree($store);
				this._addEvents();
				this._tree.set("path", ["0","d1"]);
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