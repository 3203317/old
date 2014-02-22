/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：角色组Grid
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/data/ItemFileReadStore",
	"dojo/_base/connect",
	"dijit/layout/ContentPane",
	"dojo/data/ItemFileWriteStore",
	"dojox/grid/EnhancedGrid",
	"dojox/grid/_RadioSelector"], function($declare, $lang, $ItemFileReadStore, $connect, $ContentPane,$ItemFileWriteStore,$EnhancedGrid) {
	return $declare("internal.sys.role.LGrid", $ContentPane, {
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
			this._grid.startup();
			this._addEvents();
			this._onDataLoad();
		},
		_removeHandles: function(){
			this._connect1.remove();
		},
		_addGrid: function(){
			this._grid = new $EnhancedGrid({
				style: { height: "100%", margin: 0, padding: 0 },
				structure: [{
					type: "dojox.grid._RadioSelector"
				}, [
				    { name: "角色组编码", field: "id", width: "80px" },
					{ name: "角色组描述", field: "rolegroupdesc", width: "130px" },
					{ name: "创建时间", field: "addtime", width: "120px" },
					{ name: "创建人", field: "ad_s_user_name", width: "80px" },
					{ name: "状态", field: "isenable_text" }
					]
				]
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._grid, "onSelected", this, "_selGridItem");
		},
		_selGridItem: function(){
			$connect.publish("/internal/sys/role/RGrid/loadData/",[this._grid.selection.getSelected()[0].id[0]]);
		},
		_onDataLoad: function(){
			var __params = {
				async : false,
				handleAs : "json",
				url : this.vpath +"testData/rolegroup.json?r="+ new Date(),
				load : $lang.hitch(this, "_onDataLoaded"),
				error : function($response, $ioArgs) {
					console.log(arguments);
					return $response;
				}
			};
			dojo.xhrGet(__params);
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
			this._grid.selection.setSelected(0,true);
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});