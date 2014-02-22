/**
 * 作者：黄鑫
 * 日期：2013-04-07
 * 描述：性别数据组件
 */
define(["dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
	"dijit/form/FilteringSelect",
	"dojo/store/Memory",
    "capec/utils/ajax"], function($declare, $connect, $lang ,$ComboBox, $Memory, $ajax) {
	return $declare("internal.widgets.comboBox.BatchComboBox", $ComboBox, {
		searchAttr: "batchname",
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
				url : this.vpath +"testData/batch.json",
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