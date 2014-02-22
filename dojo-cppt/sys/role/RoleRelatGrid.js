/**
 * 作者：黄鑫
 * 日期：2013-04-07
 * 描述：角色关系Grid
 */
define(["dojo/_base/declare",
	"dojo/data/ItemFileReadStore",
	"dojo/_base/connect",
	"capec/utils/ajax",
	"dojo/data/ItemFileWriteStore",
	"dojox/grid/EnhancedGrid",
	"dojo/_base/lang",
	"dijit/layout/ContentPane",
	"capec/utils/randomUrl",
	"dojox/grid/_RadioSelector"], function($declare, $ItemFileReadStore, $connect,$ajax,$ItemFileWriteStore,$EnhancedGrid, $lang, $ContentPane,$randomUrl) {
	return $declare("internal.sys.role.RoleRelatGrid", $ContentPane, {
		_tree: null,
		bindTree: function($value){
			this._tree = $value;
		},
		_onDblClickItem: function(){
		},
		_onSelectedItem: function(){
			/* 判断左侧树选中节点是否被选中，没有选中则清空Grid的选中项 */
			if(this._grid.selection.getSelected()[0]){
				if(!this._tree.getSelNodeCheckedAttr()){
					this.unSelectItem();
					return;
				}
				var __col = this._grid.selection.getSelected()[0].id[0];
				$ajax({
					async: true,
					content: { roleid: this.roleid, opt: __col, optid: this._tree.getSelectedItem()["id"][0], type: this._tree.getSelectedItem()["type"][0]},
					url : this.vpath +"testData/optdesc.json",
					callback: $lang.hitch(this, "_clickItemBack", __col)
				});
			}
		},
		_clickItemBack: function($data){
			console.log("保存角色的操作设置,选择domain或module时，保存下级所有的operate的操作类型");
			/*设置树选中项的操作值*/
			this._tree.setItemOpt($data);
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
				url : this.vpath +"testData/optdesc.json",
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
		_onError: function(){
			console.log(arguments);
		},
		_removeHandles: function(){
			this._connect1.remove();
		},
		_addGrid: function(){
			this._grid = new $EnhancedGrid({
				style: { height: "100%", margin: 0, padding: 0 ,width:"100%"},
				structure: [{
					type: "dojox.grid._RadioSelector"
				},[
					{ name: "操作描述", field: "optdesc", width: "auto" }
					]
				],
				canSort: function(){
					return false;
				}
			},document.createElement("div"));
			this.domNode.appendChild(this._grid.domNode);
		},
		_addEvents: function(){
			this._connect1 = $connect.connect(this._grid, "onClick", this, "_onSelectedItem");
		},
		setStore: function($store){
		},
		selectItem: function($col){
			this.unSelectItem();
			this._grid.selection.setSelected($col-1, true);
		},
		unSelectItem: function(){
			this._grid.selection.setSelected(0, false);
			this._grid.selection.setSelected(1, false);
			this._grid.selection.setSelected(2, false);
			this._grid.selection.setSelected(3, false);
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});