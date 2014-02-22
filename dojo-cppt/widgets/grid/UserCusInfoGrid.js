/**
 * 作者：黄鑫
 * 日期：2013-04-07
 * 描述：用户自定义信息Grid
 */
define(["dojo/_base/declare",
	"dojo/data/ItemFileReadStore",
	"dojo/_base/connect",
	"dojo/data/ItemFileWriteStore",
	"dojox/grid/EnhancedGrid",
	"dojo/_base/lang",
	"dijit/layout/ContentPane",
	"capec/utils/randomUrl"], function($declare, $ItemFileReadStore, $connect,$ItemFileWriteStore,$EnhancedGrid, $lang, $ContentPane,$randomUrl) {
	return $declare("internal.widgets.grid.UserCusInfoGrid", $ContentPane, {
		_onDblClickItem: function(){
		},
		_onSelectedItem: function(){
		},
		postCreate : function() {
			this.inherited(arguments);
			this._addGrid();
		},
		startup:function(){
			this.inherited(arguments);
			this._grid.startup();
			this._addEvents();
			this._loadData();
		},
		_loadData: function(){
			var __param = {
				async : false,
				handleAs : "json",
				url : $randomUrl(this.vpath +"testData/user_cusInfo.json"),
				load : $lang.hitch(this, "_onDataLoaded"),
				error : $lang.hitch(this, "_onError")
			};
			dojo.xhrGet(__param);
		},
		_onDataLoaded: function($data){
			if($data != null){
				var __store = new $ItemFileWriteStore({ data: $data });
				this._grid.setStore(__store);
			}
		},
		_onError: function(){
			console.log(arguments);
		},
		_removeHandles: function(){
		},
		_addGrid: function(){
			this._grid = new $EnhancedGrid({
				rowSelector: "5px",
				style: { height: "100%", margin: 0, padding: 0 ,width:"100%"},
				structure: [[
					{ name: "字段名", field: "fieldname", width: "120px" },
					{ name: "字段值", field: "fieldvalue", width: "auto", editable: true }
					]
				]
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		_addEvents: function(){
		},
		setStore: function($store){
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});