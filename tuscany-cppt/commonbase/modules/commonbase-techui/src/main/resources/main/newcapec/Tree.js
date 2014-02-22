define(["dojo/_base/declare",
	"dijit/Tree"], function($declare, $Tree){
	return $declare("newcapec.Tree", $Tree, {
		onClick: function($item){
			this.attr("selectedItem", $item);
			this.inherited(arguments);
		}
	});
});