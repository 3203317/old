define(
		[ "dojo/_base/declare", "dijit/form/ComboBox", "dojo/_base/lang",
				"dojo/data/ItemFileReadStore",
				"dijit/layout/AccordionContainer", "dijit/layout/ContentPane",
				"capec/Tree", "dijit/tree/ForestStoreModel",
				"dojo/data/ItemFileReadStore", "dojo/_base/connect",
				"dojo/i18n!./nls/ModSearchCBox" ],
		function($declare, $ComboBox, $lang, $ItemFileReadStore,
				$AccordionContainer, $ContentPane, $Tree, $ForestStoreModel,
				$ItemFileReadStore, $connect, $i18n) {
			return $declare(
					"internal.main.MenuTree",
					$AccordionContainer,
					{
						postCreate : function() {
							this.inherited(arguments);
							this._loadData();
						},
						_loadData : function() {
							dojo.xhrGet({
								async : false,
								handleAs : "json",
								url : "testData/module.json",
								load : $lang.hitch(this, "_onDataLoaded"),
								error : function($response, $ioArgs) {
									console.log("xhr get failed:", $response);
									return $response;
								}
							});
						},
						_onDataLoaded : function($data, $ioArgs) {
							if ($data == null || $data.opt != null)
								return;

							var __treeObj = capec.utils.data2TreeObject($data,
									0, {
										identifier : $data.identifier,
										fidentifier : "p_id",
										label : $data.label
									});

							for ( var __i_3 = 0, __tree_3; __tree_3 = __treeObj.children[__i_3]; __i_3++) {
								var __pane_4 = new $ContentPane({
									title : __tree_3.modulename,
									iconClass : __tree_3.icon,
									style : {
										margin : 0,
										padding : "2px"
									}
								});

								this.addChild(__pane_4);

								if (__tree_3.children != null) {
									var __tree_5 = new $Tree({
										showRoot : false,
										openOnDblClick : true,
										model : new $ForestStoreModel({
											store : new $ItemFileReadStore({
												data : {
													"identifier" : "id",
													"label" : "modulename",
													"items" : __tree_3.children
												}
											}),
											query : {
												"type" : "module"
											},
											rootId : "id",
											rootLabel : "modulename",
											childrenAttrs : [ "children" ]
										}),
										style : {
											margin : 0,
											padding : 0
										}
									}).placeAt(__pane_4.containerNode);

									__tree_5
											.on(
													"click",
													function($item) {
														$connect
																.publish(
																		"/internal/main/MainTabs/openNewTab/",
																		[ {
																			id : $item.id[0],
																			name : $item.modulename[0],
																			href : $item.href[0],
																			openMode : $item.openMode[0]
																		} ]);
													});
								}
							}
						}
					});
		});