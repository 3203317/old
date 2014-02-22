/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：模块管理Tree
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/data/ItemFileReadStore",
	"dojo/_base/connect",
	"dijit/layout/ContentPane",
	"dojo/data/ItemFileWriteStore",
	"capec/Tree",
	"dijit/tree/ForestStoreModel"], function($declare, $lang, $ItemFileReadStore, $connect, $ContentPane,$ItemFileWriteStore,$Tree,$ForestStoreModel) {
	return $declare("internal.sys.module.ModuleTree", $ContentPane, {
		rootLabel: "新开普",
		vpath: "",
		constructor: function(){
			this.inherited(arguments);
			if(arguments.rootLabel) this.rootLabel = arguments.rootLabel;
			if(arguments.vpath) this.vpath = arguments.vpath;
		},
		postCreate : function() {
			this.inherited(arguments);
			//创建Tree组件
			this._addTree();
		},
		startup:function(){
			this.inherited(arguments);
		},
		_removeHandles: function(){
			this._connect1.remove();
		},
		_addTree: function(){
			var __tree = {};

			__tree.aa = this;

			__tree.rootLabel = this.rootLabel;
			__tree.vpath = this.vpath;

			__tree._init = function(){
				//Ajax获取json数据
				dojo.xhrGet({
					async : false,
					handleAs : "json",
					url : this.vpath +"testData/module.json?r="+ new Date(),
					load : $lang.hitch(this, "_createTreeStore"),
					error : function($response, $ioArgs) {
						console.log("xhr get failed:", $response);
						return $response;
					}
				});
			};

			__tree._createTreeStore = function($data){
				if($data == null || $data.opt != null) return;
				$data.items = capec.utils.data2TreeObject($data,0,{
					identifier: "id",
					fidentifier: "p_id",
					label: "modulename"
				}).children;
				this._treeStore = new $ItemFileWriteStore({
					data: $data
				});
			};

			__tree._createTree = function(){
				if(this._treeStore == null) return;

				//创建Tree
				this._tree = new $Tree({
					model: new $ForestStoreModel({
						store: this._treeStore,
						query: { "type": "module" },
						rootId: "0",
						rootLabel: this.rootLabel,
						childrenAttrs: ["children"]
					}),
					showRoot: true,
					openOnDblClick: true,
					style: { height: "100%", margin: 0, padding: 0 }
				},document.createElement("div"));

				this.aa.domNode.appendChild(this._tree.domNode);

				this._tree.set("path", ["0"]);

				//单击事件消息传递
				this._tree.on("click",function($item){
					$connect.publish("/internal/sys/module/ModuleGrid/loadData/",[$item]);
				});
			};

			this._connect1 = $connect.connect(__tree,"_createTreeStore",__tree,"_createTree");

			__tree._init();
		},
		destroy: function(){
			this._removeHandles();
			this.destroyDescendants();
			this.inherited(arguments);
		}
	});
});