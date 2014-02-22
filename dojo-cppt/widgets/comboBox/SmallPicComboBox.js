/**
 * 作者：黄鑫
 * 日期：2013-04-07
 * 描述：性别数据组件
 */
define(["dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
	"dijit/form/ComboBox",
	"dojo/store/Memory",
    "capec/utils/ajax"], function($declare, $connect, $lang ,$ComboBox, $Memory, $ajax) {
	return $declare("internal.widgets.comboBox.SmallPicComboBox", $ComboBox, {
		searchAttr: "codename",
		autoComplete: false,
		selectOnClick: true,
		labelType: "html",
		labelFunc: function($item){
			var __codename = $item.codename;
			var __src = $item.src;
			return "<table><tr><td><img src='"+ this.vpath + __src +"' style='width:30px;height:30px'></td><td>"+ __codename +"</td></tr></table>";
		},
		postCreate : function() {
			this.inherited(arguments);
			this._loadData();
		},
		startup:function(){
			this.inherited(arguments);
		},
		_loadData: function(){
			$ajax({
				sync: true,
				url : this.vpath +"testData/code_SMALLPIC.json",
				callback : $lang.hitch(this, "_onDataLoaded")
			});
		},
		_onDataLoaded: function($data){
			this.store = new $Memory({
				data: $data.items
			});
		},
		_removeHandles: function(){

		},
		onChange: function(){
//			console.log(this.item);
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});