/**
 * 作者：黄鑫
 * 日期：2013-04-07
 * 描述：资源类型数据组件
 */
define(["dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
	"dijit/form/Select",
    "capec/utils/ajax"], function($declare, $connect, $lang ,$ComboBox, $ajax) {
	return $declare("internal.widgets.comboBox.ResTypeComboBox", $ComboBox, {
		postCreate : function() {
			this.inherited(arguments);
		},
		startup:function(){
			this.inherited(arguments);
			this._loadData();
		},
		_loadData: function(){
			$ajax({
				sync: true,
				content: { codeType: this.codeType },
				url : this.vpath +"testData/resource_type.json",
				callback : $lang.hitch(this, "_onDataLoaded")
			});
		},
		_onDataLoaded: function($data){
			if($data != null){
				for(var __i=0,__j=$data.items.length; __i<__j; __i++){
					this.addOption({ label: $data.items[__i]["restypename"], value: $data.items[__i]["id"] });
				}
			}
		},
		_removeHandles: function(){

		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});