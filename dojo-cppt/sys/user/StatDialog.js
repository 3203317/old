/**
 * 作者：黄鑫
 * 日期：2013-04-24
 * 描述：重置密码
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dojo/_base/lang",
	"capec/utils/ajax",
	"dijit/Tooltip"], function($connect,$declare,$FormDialog,$lang,$ajax,$Tooltip) {
	return $declare($FormDialog,{
		fid: "internal_sys_user_StatForm",
		title: "",
		href: "sys/user/statForm.html",
		style: { height: "128px", width: "280px" },
		_grid: null,
		_store: null,
		_tree: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		bindStore: function($store){
			this._store = $store;
			this._store.bindDialog(this);
		},
		bindTree: function($value){
			this._tree = $value;
		},
		_valid: function(){
			var __frm = dijit.byId(this.fid);
			return __frm.isValid();
		},
		_submit: function(){
			if(this._valid()){
				var __newItem = dijit.byId(this.fid).getValues();
				if(this.model == "Create"){
//					this._store.newItem(__newItem);
				}else{
					/*修改密码ajax*/
					console.log(__newItem);
					$ajax({
						async : true,
						content: __newItem,
						url : this.vpath +"testData/codetype_add.json",
						callback: $lang.hitch(this, "hide")
					});
				}
			}else{
				dijit.byId(this.fid).validate();
			}
		},
		_reset: function(){
			if(this.model == "Create"){
				dijit.byId(this.fid).reset();
			}else{
				this._setData();
			}
		},
		_draw: function(){
			console.log("绘制表单");
			console.log("Ajax请求需同步或其他方式");
		},
		_setTitle: function(){
			switch(this.model){
			case "Create":
				this.set("title",$i18n.title_Create);
				break;
			case "Update":
				var __data_3 = this._grid.getSelectItem();
				this.set("title","调整状态 [姓名："+ __data_3.i.username +"]");
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data_3 = this._grid.getSelectItem();
				if(__data_3 == null) return;
				console.log(__data_3);
				dijit.byId(this.fid).reset();
				dijit.byId(this.fid +"_idText").set("value", __data_3.i.id);
			}
			this._valid();
		}
	});
});
