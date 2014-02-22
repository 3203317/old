/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：用户Store
 */
define(["dojo/data/ItemFileWriteStore",
	"dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
    "capec/utils/ajax",
	"capec/data/QueryReadStore"], function($ItemFileWriteStore,$declare,$connect,$lang,$ajax,$QueryReadStore){
	return $declare("internal/sys/org/GridStore", null,{
		vpath: "",
		_grid: null,
		_store: null,
		_dialog: null,
		_tree: null,
		bindGrid: function($grid){
			this._grid = $grid;
			this.loadData(0);
		},
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		bindTree: function($tree){
			this._tree = $tree;
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments[0].vpath) this.vpath = arguments[0].vpath;
		},
		loadData: function($orgId){
			this._grid.setStore(new $QueryReadStore({
				url : this.vpath +"testData/org_l"+ $orgId +".json?orgId="+ $orgId,
				requestMethod: "post"
			}));
		},
		searchData: function($params){
			$params.orgId = this._tree.getSelectedItem().id[0];
			this._grid.setQuery($params);

//			$ajax({
//				async : false,
//				content: $formParams,
//				url : this.vpath +"testData/user_o"+ $formParams.orgId +".json?orgId="+ ,
//				callback : $lang.hitch(this, "_onDataLoaded")
//			});
		},
		_onDataLoaded: function($data){
			if(this._store != null){
				this._store.close();
				this._store = null;
			}
			this._store = new $ItemFileWriteStore({ data: $data });
			this._grid.setStore(this._store);
		},
		_onError: function(){
			console.log(arguments);
		},
		_newItem: function($data){
			console.log("新增组织");
			console.log($data.responseInfo);
			this._dialog.hide();
			this._grid.refresh();
//			this._store.newItem($data.responseInfo);
//			this._store.save();
		},
		newItem: function($item){
			console.log($item);
			$ajax({
				async : true,
				content: $item,
				url : this.vpath +"testData/codetype_add.json",
				callback: $lang.hitch(this, "_newItem")
			});
		},
		_setAllValue: function($item){
			var __newValue = arguments[0][0].responseInfo;
			var __item = arguments[1];

			for(var __prop_3 in __newValue){
				if(__prop_3 != "id") {
					this._store.setValue(__item, __prop_3, __newValue[__prop_3]);
				}
			}
			this._store.save();
		},
		_setItem: function($data){
			console.log("更新组织");
			console.log($data.responseInfo);
			this._dialog.hide();
			this._grid.refresh();
//			this._store.fetchItemByIdentity({
//				identity: $data.responseInfo.id,
//				onItem: $lang.hitch(this, "_setAllValue", arguments)
//			});
		},
		setItem: function($item){
			console.log($item);
			$ajax({
				async : true,
				content: $item,
				url : this.vpath +"testData/codetype_edit.json",
				callback: $lang.hitch(this, "_setItem")
			});
		},
		_removeItem: function($data){
			console.log("删除组织");
//			this.searchData({});
			this._grid.refresh();
		},
		removeItem: function(){
			var __ids = this._grid.getSelectItemIds();
			$ajax({
				async : true,
				content: { userIds: __ids },
				url : this.vpath +"testData/user_del.json",
				callback: $lang.hitch(this, "_removeItem")
			});
		}
	})
});