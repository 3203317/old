/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：组织表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/DateTextBox",
	"dijit/form/SimpleTextarea",
	"dijit/form/NumberSpinner",
	"dijit/form/Select",
	"internal/sys/group/GroRelatGrid",
	"internal/sys/group/DomGroTree"], function($connect,$declare,$FormDialog) {
	return $declare($FormDialog,{
		fid: "internal_sys_group_GroupRelatForm",
		title: "组关系管理",
		href: "sys/group/groupRelatForm.html",
		style: { height: "312px", width: "500px" },
		_grid: null,
		_store: null,
		_tree: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		bindStore: function($store){
			this._store = $store;
			this._store.bindRelatDialog(this);
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
				__newItem.domainid = this._tree.getSelectedItem().id[0];
				if(this.model == "Update"){
					this._store.setItem(__newItem);
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
			case "Update":
				this.set("title", "组关系管理    ["+ this._grid.getSelectItem().i.groupname+"]");
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data_3 = this._grid.getSelectItem();
				if(__data_3 == null || __data_3 == undefined) return;
				dijit.byId(this.fid +"_idText").set("value", __data_3.i.id);

				/*重新加载树数据*/
				var __group_id =  __data_3.i.id;
				console.log(__group_id);
				dijit.byId(this.fid +"_lTree")._loadData(__group_id);

			}
			this._valid();
		}
	});
});
