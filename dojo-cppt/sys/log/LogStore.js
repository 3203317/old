/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：代码Store
 */
define(["dojo/data/ItemFileWriteStore",
	"dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
	"dojo/_base/array",
	"capec/utils/toTreeObj",
    "capec/utils/ajax"], function($ItemFileWriteStore,$declare,$connect,$lang,$array,$toTreeObj,$ajax){
	return $declare(null,{
		vpath: "",
		_grid: null,
		_typeGrid: null,
		_store: null,
		_toolbar: null,
		_dialog: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		bindTypeGrid: function($grid){
			this._typeGrid = $grid;
		},
		bindToolbar: function($toolbar){
			this._toolbar = $toolbar;
		},
		bindDialog: function($dialog){
			this._dialog = $dialog;
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments[0].vpath) this.vpath = arguments[0].vpath;
		},
		loadData: function($logTypeId){
			$ajax({
				async : true,
				content: { logTypeId: $logTypeId },
				url : this.vpath +"testData/log_"+ $logTypeId +".json",
				callback : $lang.hitch(this, "_onDataLoaded")
			});
		},
		_onDataLoaded: function($data){
			this._store = new $ItemFileWriteStore({ data: $data });
			this._grid.setStore(this._store);
		},
		_onError: function(){
			console.log(arguments);
			this._toolbar.disableDel(true);
		},
		_newItem: function($data){
			this._dialog.hide();
			//根据返回结果p_code判断是增加子节点或...
			this.loadData(this._typeGrid.getSelectItem().id[0])
		},
		newItem: function($item){
			console.log($item);
			$ajax({
				async : true,
				content: { data: dojo.toJson($item) },
				url : this.vpath +"testData/code_add.json",
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
			this._dialog.hide();
			var __data = this._typeGrid.getSelectItem();
			this.loadData(__data.id[0]);
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
				url : this.vpath +"testData/code_edit.json",
				callback: $lang.hitch(this, "_setItem")
			});
		},
		_removeItem_: function($item){
			if($item == null) return;
			this._store.deleteItem($item);
			this._store.save();
		},
		_removeItem: function($data){
			console.log($data.responseInfo);
			var __data = this._typeGrid.getSelectItem();
			this.loadData(__data.id[0]);
//			this._store.fetchItemByIdentity({
//				identity: $data.responseInfo.id,
//				onItem: $lang.hitch(this, "_removeItem_")
//			});
		},
		removeItem: function(){
			var __ids = this._grid.getSelectItemIds();
			console.log(__ids);
			$ajax({
				async : true,
				content: { ids: __ids },
				url : this.vpath +"testData/code_del.json",
				callback: $lang.hitch(this, "_removeItem")
			});
		}
	})
});