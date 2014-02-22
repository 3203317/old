/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：模块管理Grid组件
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/data/ItemFileReadStore",
	"dojo/_base/connect",
	"dijit/layout/ContentPane",
	"dojo/data/ItemFileWriteStore",
	"dojox/grid/EnhancedGrid",
	"dojox/grid/_CheckBoxSelector"], function($declare, $lang, $ItemFileReadStore, $connect, $ContentPane,$ItemFileWriteStore,$EnhancedGrid) {
	return $declare("internal.sys.module.ModuleGrid", $ContentPane, {
		vpath: "",
		constructor: function(){
			this.inherited(arguments);
			if(arguments.vpath) this.vpath = arguments.vpath;
		},
		postCreate : function() {
			this.inherited(arguments);
			this._addGrid();
		},
		startup:function(){
			this.inherited(arguments);
			this._addSubscribes();
			this._addEvents();
			this._onDataLoad({id: "0"});
		},
		_addSubscribes : function() {
			this._handle1 = $connect.subscribe("/internal/sys/module/ModuleGrid/loadData/", this, "_onDataLoad");
		},
		_removeHandles: function(){
			this._handle1.remove();

			this._connect1.remove();
			this._connect2.remove();
		},
		_addGrid: function(){
			this._grid = new $EnhancedGrid({
				style: { height: "100%", margin: 0, padding: 0 },
				structure: [{
						type: "dojox.grid._CheckBoxSelector"
					}, {
						//cells: [{ name: "编号", field: "id", width: "35px", datatype: "number", editable: false }],
						cells: [{ name: "模块名称", field: "modulename", width: "150px" }],
						noscroll: true
					}, [
						{ name: "图标", field: "icon", width: "100px" },
						{ name: "排序", field: "sort", width: "80px" },
						{ name: "模块地址", field: "href", width: "300px" },
						{ name: "创建时间", field: "addtime", width: "120px" },
						{ name: "创建人", field: "ad_s_user_name", width: "80px" },
						{ name: "状态", field: "isenable_text" }
					]
				]
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._grid, "onSelected", this, "_setButtonDisable");
			this._connect2 = $connect.connect(this._grid, "onDeselected", this, "_setButtonDisable");
		},
		_setButtonDisable: function(){
			$connect.publish("/internal/sys/module/Toolbar/EditBtn/disable/",[this._grid.selection.getSelectedCount() != 1]);
			$connect.publish("/internal/sys/module/Toolbar/DelBtn/disable/",[this._grid.selection.getSelectedCount() == 0]);
		},
		_onDataLoad: function($module){
			var __id = (typeof $module.id == "string") ? $module.id : $module.id[0];

			if(__id == 0 || __id == 82){
				var __params = {
					async : false,
					handleAs : "json",
					url : this.vpath +"testData/module_p"+ __id +".json",
					load : $lang.hitch(this, "_onDataLoaded"),
					error : function($response, $ioArgs) {
						console.log(arguments);
						return $response;
					}
				};
				dojo.xhrGet(__params);
			}
		},
		_onDataLoaded: function($data){
			this._grid.selection.clear();
			var __store = null;
			if($data != null && $data.opt == null){
				__store = new $ItemFileWriteStore({
					data: $data
				});
			}
			this._grid.setStore(__store);
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});