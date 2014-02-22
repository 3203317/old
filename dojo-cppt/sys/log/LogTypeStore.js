/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：日志类型Store
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
		bindGrid: function($grid){
			this._grid = $grid;
			this._loadData();
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments[0].vpath) this.vpath = arguments[0].vpath;
		},
		_loadData: function(){
			$ajax({
				async : true,
				url : this.vpath +"testData/logtype.json",
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
		}
	})
});