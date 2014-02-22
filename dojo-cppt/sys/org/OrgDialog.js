/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：组织表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dojo/i18n!./nls/OrgDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"internal/widgets/comboBox/BatchComboBox",
	"dijit/form/DateTextBox",
	"dijit/form/SimpleTextarea"], function($connect,$declare,$FormDialog,$i18n) {
	return $declare($FormDialog,{
		fid: "internal_sys_org_OrgForm",
		title: $i18n.title_Create,
		href: "sys/org/orgForm.html",
		style: { height: "205px", width: "480px" },
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
				__newItem.batchid = dijit.byId(this.fid +"_batchidText").item.id
				__newItem.curdate = dijit.byId(this.fid +"_curdateText").displayedValue;
				__newItem.orgid = this._tree.getSelectedItem().id[0];
				if(this.model == "Create"){
					this._store.newItem(__newItem);
				}else{
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
			$connect.connect(dijit.byId(this.fid +"_batchidText"), "onChange", this, "_setBatchDate");
		},
		_setBatchDate: function(){
			if(dijit.byId(this.fid +"_batchidText").item != null){
				dijit.byId(this.fid +"_begindateText").set("value", dijit.byId(this.fid +"_batchidText").item.begindate);
				dijit.byId(this.fid +"_enddateText").set("value", dijit.byId(this.fid +"_batchidText").item.enddate);
			}
		},
		_setTitle: function(){
			switch(this.model){
			case "Create":
				this.set("title",$i18n.title_Create);
				break;
			case "Update":
				this.set("title",$i18n.title_Update);
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
				dijit.byId(this.fid +"_idText").set("value", __data_3.i.id);
				dijit.byId(this.fid +"_orgnameText").set("value", __data_3.i.orgname);
				dijit.byId(this.fid +"_batchidText").setDisplayedValue(__data_3.i.batchname);
//				dijit.byId(this.fid +"_create_dateText").set("value", __data_3.addtime[0]);
//				dijit.byId(this.fid +"_idText").set("disabled",true);
//				dijit.byId(this.fid +"_idText").set("value",__data.id[0]);
//				dijit.byId(this.fid +"_codetypedescText").set("value", __data.codetypedesc[0]);
			}
			this._valid();
		}
	});
});
