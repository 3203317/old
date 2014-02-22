/**
 * 作者：黄鑫
 * 日期：2013-04-01
 * 描述：组织机构树Store
 */
define(["dojo/data/ItemFileWriteStore",
	"dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
	"capec/utils/toTreeObj",
    "capec/utils/ajax"], function($ItemFileWriteStore,$declare,$connect,$lang,$toTreeObj,$ajax){
	return $declare(null,{
		vpath: "",
		_tree: null,
		_store: null,
		bindTree: function($tree){
			this._tree = $tree;
			setTimeout($lang.hitch(this, "_loadData"),100);
		},
		constructor: function(){
			this.inherited(arguments);
			if(arguments[0].vpath) this.vpath = arguments[0].vpath;
		},
		_loadData: function(){
			$ajax({
				async : true,
				url : this.vpath +"testData/resource.json",
				callback : $lang.hitch(this, "_onDataLoaded")
			});
		},
		_onDataLoaded: function($data){
			var __items = $toTreeObj($data,0,{
				identifier: "id",
				fidentifier: "p_id",
				label: "resourcename"
			}).children;

			this._store = new $ItemFileWriteStore({
				data: {
					identifier: "id",
					label: "resourcename",
					items: __items == undefined ? [] : __items
				}
			});

			this._tree.setStore(this._store);
		},
		_onError: function(){
			console.log(arguments);
		}
	})
});