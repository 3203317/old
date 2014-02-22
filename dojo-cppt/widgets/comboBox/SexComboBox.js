/**
 * 作者：黄鑫
 * 日期：2013-04-07
 * 描述：性别数据组件
 */
define(["dojo/_base/declare",
	"dojo/_base/connect",
	"dojo/_base/lang",
	"dijit/form/Select",
    "capec/utils/randomUrl"], function($declare, $connect, $lang ,$ComboBox,$randomUrl) {
	return $declare("internal.widgets.comboBox.SexComboBox", $ComboBox, {
		postCreate : function() {
			this.inherited(arguments);
		},
		startup:function(){
			this.inherited(arguments);
			this._loadData();
		},
		_loadData: function(){
			var __param = {
				async : true,
				handleAs : "json",
				content: {},
				url : $randomUrl(this.vpath +"testData/code_SEX.json"),
				load : $lang.hitch(this, "_onDataLoaded"),
				error : $lang.hitch(this, "_onError")
			};
			dojo.xhrGet(__param);
		},
		_onDataLoaded: function($data){
			if($data != null){
				for(var __i=0,__j=$data.items.length; __i<__j; __i++){
					this.addOption({ label: $data.items[__i]["codename"], value: $data.items[__i]["code"] });
				}
			}
		},
		_onError: function(){
			console.log(arguments);
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