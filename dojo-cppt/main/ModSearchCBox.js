define(
		[ "dojo/_base/declare", "dijit/form/ComboBox", "dojo/_base/lang",
				"dojo/data/ItemFileReadStore", "dojo/_base/connect",
				"dojo/i18n!./nls/ModSearchCBox" ],
		function($declare, $ComboBox, $lang, $ItemFileReadStore, $connect,
				$i18n) {
			return $declare(
					"internal.main.ModSearchCBox",
					$ComboBox,
					{
						postCreate : function() {
							this.inherited(arguments);
							this.set("placeHolder", $i18n.placeHolder);
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
							var __newItems = [];
							for ( var __i_3 = 0, __item_3; __item_3 = $data.items[__i_3]; __i_3++) {
								if (__item_3.href)
									__newItems.push(__item_3);
							}
							this.set("store", new $ItemFileReadStore({
								data : {
									items : __newItems
								}
							}));
						},
						onChange : function($item) {
							var __item = this.item;
							if (__item) {
								$connect.publish("/internal/main/MainTabs/openNewTab/", [ {
									id : __item.id[0],
									name : __item.modulename[0],
									href : __item.href[0],
									openMode : __item.openMode[0]
								} ]);
							}
						}
					});
		});