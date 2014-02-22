/**
 * 作者：黄鑫
 * 日期：2013-04-07
 * 描述：
 */
define(["dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/data/ItemFileWriteStore",
	"dojox/grid/EnhancedGrid",
	"dojo/_base/lang",
	"dijit/layout/ContentPane",
	"capec/utils/randomUrl",
	"capec/utils/ajax"], function($declare, $connect,$ItemFileWriteStore,$EnhancedGrid, $lang, $ContentPane,$randomUrl,$ajax) {
	return $declare("internal.sys.role.UnUserRelatGrid", $ContentPane, {
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
		},
		loadData: function($param){
			var __param = {
				async : true,
				content: $param,
				url : this.vpath +"testData/role_unuser_r.json",
				callback : $lang.hitch(this, "_onDataLoaded")
			};
			$ajax(__param);
		},
		_onDataLoaded: function($data){
			if($data != null){
				var __store = new $ItemFileWriteStore({ data: $data });
				this._grid.setStore(__store);
			}
		},
		_removeHandles: function(){
		},
		_addGrid: function(){
			this._grid = new $EnhancedGrid({
				style: { height: "100%", margin: 0, padding: 0 ,width:"100%"},
				structure: [{
					type: "dojox.grid._CheckBoxSelector"
				},[
					{ name: "编号", field: "userid", width: "auto" },
					{ name: "用户名", field: "username", width: "auto" },
					{ name: "所属组织", field: "orgname", width: "auto" }
					]
				]
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		_addEvents: function(){
		},
		setStore: function($store){
		},
		selectItem: function(){
			this._grid.selection.setSelected(0, true);
		},
		getSelectItems: function(){
			var __items = this._grid.selection.getSelected();
			var __itemArr = [];
			for(var __i_3=0,__item_3; __item_3=__items[__i_3]; __i_3++){
				var __item_4 = {
					userid: __item_3.userid[0],
					username: __item_3.username[0],
					orgname: __item_3.orgname[0]
				};
				__itemArr.push(__item_4);
				this._grid.store.deleteItem(__item_3);
			}
			if(this._grid.store != null) this._grid.store.save();
			return __itemArr;
		},
		addItems: function($items){
			for(var __i_3=0,__item_3; __item_3=$items[__i_3]; __i_3++){
				this._grid.store.newItem(__item_3);
			}
			if(this._grid.store != null) this._grid.store.save();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});