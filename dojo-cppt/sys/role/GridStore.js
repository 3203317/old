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
	return $declare(null,{
		vpath: "",
		_grid: null,
		_store: null,
		_dialog: null,
		bindGrid: function($grid){
			this._grid = $grid;
			this.loadData();
		},
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments[0].vpath) this.vpath = arguments[0].vpath;
		},
		loadData: function(){
			var __query = new $QueryReadStore({
				url: this.vpath +"testData/role_SUPERS.json",
				requestMethod: "post"
			});
			this._grid.setStore(__query);
			__query.grid = this._grid._grid;

			console.log(__query);
		},
		searchData: function($params){
			this._grid.setQuery($params);
		},
		_onDataLoaded: function($data){
			if(this._store != null){
				this._store.close();
				this._store = null;
			}
			this._store = new $ItemFileWriteStore({ data: $data });
			this._grid.setStore(this._store);
		},
		_newItem: function($data){
			this._dialog.hide();
			this._grid.refresh();
		},
		newItem: function($item){
			$ajax({
				async : true,
				content: dojo.toJson({ data: $item }),
				url : this.vpath +"testData/role_add.json",
				callback: $lang.hitch(this, "_newItem")
			});
		},
		_setAllValue: function($item){
			var __item = arguments[1];
			if(__item == null) return;

			var __newValue = arguments[0][0].responseInfo;

			for(var __prop_3 in __newValue){
				if(__prop_3 != "id") {
					this._store.setValue(__item, __prop_3, __newValue[__prop_3]);
				}
			}
			this._store.save();
		},
		_setItem: function($data){
			console.log("更新角色");
			this._dialog.hide();
			this._grid.refresh();
		},
		setItem: function($item){
			$ajax({
				async : true,
				content: dojo.toJson({ data: $item }),
				url : this.vpath +"testData/role_edit.json",
				callback: $lang.hitch(this, "_setItem")
			});
		},
		_removeItem: function($data){
			console.log("删除角色");
			this._grid.refresh();
		},
		removeItem: function(){
			var __ids = this._grid.getSelectItemIds();
			$ajax({
				async : true,
				content: { ids: __ids },
				url : this.vpath +"testData/user_del.json",
				callback: $lang.hitch(this, "_removeItem")
			});
		}
	})
});