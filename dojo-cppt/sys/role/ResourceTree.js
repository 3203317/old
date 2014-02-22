/**
 * 作者：黄鑫
 * 日期：2013-04-01
 * 描述：资源树
 */
define(["dojo/_base/declare",
	"capec/CheckboxTree",
	"capec/tree/CbForestStoreModel",
	"dojo/_base/lang",
	"dojo/_base/connect",
	"capec/utils/ajax",
	"capec/utils/toTreeObj",
	"dojo/data/ItemFileWriteStore",
	"dijit/layout/ContentPane"], function($declare, $Tree, $ForestStoreModel, $lang, $connect, $ajax, $toTreeObj, $ItemFileWriteStore, $ContentPane) {
	return $declare("internal.sys.role.ResourceTree", $ContentPane, {
		vpath: "",
		_grid: null,
		_store: null,
		bindStore: function($store){
			this._store = $store;
			this._store.bindTree(this);
		},
		bindGrid: function($value){
			this._grid = $value;
			this._grid.bindTree(this);
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
		_onCheckBoxClick: function($item,$node,$evt){
			var __params = {
				resourceid: $item.id[0],
				resourcetype: $item.type[0],
				checked: $node._getCheckedAttr(),
				roleid: this.roleid
			};
//			console.log(__params);
			$ajax({
				async : true,
				content: __params,
				url : this.vpath +"testData/domainresource.json",
				callback : $lang.hitch(this, "_clickCheckBoxBack", __params.checked)
			});
		},
		_clickCheckBoxBack: function($checked, $data){
			console.log("点击左侧树Checkbox后保存数据");
			if($checked){
				this._grid.selectItem(1);
				this.setItemOpt(1);
			}else{
				this._grid.unSelectItem();
			}
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._tree, "onClick", this, "_onSelectedItem");
			this._connect2 = $connect.connect(this._tree, "onCheckBoxClick", this, "_onCheckBoxClick");
		},
		_onSelectedItem: function($item,$node,$evt){
			if($item.checked[0] && $item.type[0] == "operate"){
				if($item.opt){
					this._grid.selectItem($item.opt[0]);
				}else{
					if($node._getCheckedAttr()) this._grid.selectItem(1);
				}
			}else{
				this._grid.unSelectItem();
			}
		},
		_addTree: function($store){
			this._tree = new $Tree({
				showRoot: false,
				openOnDblClick: true,
				keepSelection: true,
				defaultOpen: true,
				rowSelector: false,
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
				url : this.vpath +"testData/domainresource.json",
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
				this._tree.set("path", this._tree._getRootOrFirstNode().getTreePath());
			}
		},
		getSelectedItem: function(){
			return this._tree.selectedItem;
		},
		setItemOpt: function($opt){
			this._tree.model.store.setValue(this.getSelectedItem(),"opt", $opt);
		},
		getSelNodeCheckedAttr: function(){
			return this._tree.selectedNode._getCheckedAttr();
		},
		/* 清空树所有Checkbox */
		cleanAllCheckBox: function(){
			if(this._tree){
				var __rootItem = this._tree._getRootOrFirstNode().getTreePath()[0];
				this._tree.model.setChecked(__rootItem,false);
			}
		},
		/* 初始化Checkbox节点 */
		checkTreeNode: function($params){
			if(this._tree){
				var __nodes = this._tree._itemNodesMap[$params.id];
				if(__nodes){
					var __node = __nodes[0];
					this._tree.model.setChecked(__node.item, true);
					this._tree.model.store.setValue(__node.item,"opt", $params.opt);
					this._tree.set("path", this._tree._getRootOrFirstNode().getTreePath());
				}
			}
		},
		_removeHandles: function(){
			this._connect1.remove();
			this._connect2.remove();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});