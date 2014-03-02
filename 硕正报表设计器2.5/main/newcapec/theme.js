/* 赵鹏飞 黄鑫 */
require([ "require", "dojo/_base/array", "dojo/dom-class",
		"dojo/dom-construct", "dojo/query", "dojo/ready", "dojo/_base/window",
		"dojo/cookie" ], function(require, array, domClass, domConstruct,
		query, ready, win, cookie) {

	capec.changetheme = function($theme) {

		cookie("theme", $theme, {
			expires : capec.base.expires
		});

		capec.base.theme = $theme;

		ready(1, function() {
			query('link[href*="theme"]').orphan();
			var __csss = [ require.toUrl("main/dijit/themes/" + $theme + "/"
					+ $theme + ".css") ];
			var __head = query("head")[0];
			array.forEach(__csss, function($css) {
				if (document.createStyleSheet) {
					document.createStyleSheet($css);
				} else {
					domConstruct.place(
							'<link rel="stylesheet" type="text/css" href="'
									+ $css + '"/>', __head);
				}
			});
		});
	};
});
