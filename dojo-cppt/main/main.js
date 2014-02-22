require([ "dojo", "dojo/ready", "dojo/_base/declare", "dojo/dom",
		"dojo/_base/lang", "dijit/layout/BorderContainer",
		"dijit/layout/ContentPane", "dojox/layout/ToggleSplitter",
		"dijit/Toolbar", "dijit/form/Button", "dijit/layout/ContentPane",
		"dijit/Tooltip", "dijit/MenuItem", "dijit/form/ToggleButton",
		"dijit/ToolbarSeparator",
		"dijit/form/Button",
		"dijit/form/DropDownButton",
		"dijit/form/ComboButton",
		"dijit/form/ToggleButton",
		"dijit/ColorPalette",
		"dijit/TooltipDialog" ], function($dojo, $ready, $declare,
		$dom) {

	$ready(function() {
		$dom.byId("main_loaderInner").innerHTML += " done.";
		setTimeout(function() {
			$dojo.fadeOut({
				node : "main_loader",
				duration : 500,
				onEnd : function($n) {
					$n.style.display = "none";
				}
			}).play();
		}, 250);
	});
});
require([ "dijit/form/ComboButton", "dijit/Toolbar", "dijit/Tooltip",
		"dijit/Menu", "dijit/MenuItem" ], function() {
});