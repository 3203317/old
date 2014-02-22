define([ "dojo/_base/declare", "dojo/_base/lang", "dijit/layout/TabContainer",
		"dojo/_base/connect", "dijit/layout/ContentPane",
		"dojox/layout/ContentPane" ], function($declare, $lang, $TabContainer,
		$connect, $ContentPane, $xContentPane) {
	return $declare("internal.main.MainTabs", $TabContainer, {
		postCreate : function() {
			this.inherited(arguments);
			this._subscribe();
		},
		_subscribe : function() {
			$connect.subscribe("/internal/main/MainTabs/openNewTab/", this,
					"openNewTab");
		},
		openNewTab : function($module) {
			// 判断href是否为空
			if ($module.href) {
				var __main_tab = dijit.byId("main_tab_" + $module.id);
				// 判断tab页是否已经存在，存在则定位到该tab页
				if (!__main_tab) {
					// 创建新tab页
					if($module.openMode == "Inner"){
						__main_tab = new $xContentPane({
							id : "main_tab_" + $module.id,
							title : $module.name,
							href : $module.href +"?r="+ new Date(),
							closable : true,
							adjustPaths : true,
							renderStyles : true,
							executeScripts : true,
							style : "padding:0px"
						});
					}else{
						__main_tab = new $ContentPane({
							id : "main_tab_" + $module.id,
							title : $module.name,
							content:"<iframe frameborder='0' height='100%' width='100%' src='"+ $module.href +"?r="+ new Date() +"' id='main_tab_"+ $module.id +"' name='main_tab_"+ $module.id +"'/>",
							closable : true,
							adjustPaths : true,
							renderStyles : true,
							executeScripts : true,
							style : "padding:0px"
						});
					}
					// 添加新tab页，并定位到该页面
					this.addChild(__main_tab);
				}
				this.selectChild(__main_tab);
			}
		}
	});
});