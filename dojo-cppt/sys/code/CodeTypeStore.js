/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：代码类型Store
 */
define(["dojo/data/ItemFileWriteStore",
	"dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
    "capec/utils/ajax"], function($ItemFileWriteStore,$declare,$connect,$lang,$ajax){
	return $declare(null,{
		vpath: "",
		_grid: null,
		_store: null,
		_dialog: null,
		bindGrid: function($grid){
			this._grid = $grid;
			this._loadData();
		},
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments[0].vpath) this.vpath = arguments[0].vpath;
		},
		_loadData: function(){
			$ajax({
				async : true,
				url : this.vpath +"testData/codetype.json",
				callback : $lang.hitch(this, "_onDataLoaded")
			});
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
			console.log("新增代码类型");
			console.log($data.responseInfo);
			this._dialog.hide();
			this._store.newItem($data.responseInfo);
			this._store.save();
		},
		newItem: function($item){
			console.log($item);
			$ajax({
				async : true,
				content: { data: dojo.toJson($item) },
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
			console.log("更新代码类型");
			console.log($data.responseInfo);
			this._dialog.hide();
			this._store.fetchItemByIdentity({
				identity: $data.responseInfo.id,
				onItem: $lang.hitch(this, "_setAllValue", arguments)
			});
		},
		setItem: function($item){
			console.log($item);
			$ajax({
				async : true,
				content: { data: dojo.toJson($item) },
				url : this.vpath +"testData/codetype_edit.json",
				callback: $lang.hitch(this, "_setItem")
			});
		},
		_removeItem_: function($item){
			this._store.deleteItem($item);
			this._store.save();
			this._grid.selectItem();
		},
		_removeItem: function($data){
			console.log("删除代码类型");
			console.log($data.responseInfo);
			this._store.fetchItemByIdentity({
				identity: $data.responseInfo.id,
				onItem: $lang.hitch(this, "_removeItem_")
			});
		},
		removeItem: function(){
			var __id = this._grid.getSelectItem().code[0];
			$ajax({
				async : true,
				content: { id: __id },
				url : this.vpath +"testData/codetype_del.json",
				callback: $lang.hitch(this, "_removeItem")
			});
		}
	})
});