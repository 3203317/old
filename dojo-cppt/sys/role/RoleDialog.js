/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：角色表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"internal/widgets/MyInfo",
	"dojo/i18n!./nls/RoleDialog",
	"internal/widgets/comboBox/CodeComboBox",
	"internal/widgets/comboBox/DomainComboBox",
	"dijit/form/NumberSpinner",
	"dijit/form/Select"], function($connect,$declare,$FormDialog,$MyInfo,$i18n) {
	return $declare($FormDialog,{
		fid: "internal_sys_role_RoleForm",
		title: $i18n.title_Create,
		href: "sys/role/roleForm.html",
		style: { height: "181px", width: "500px" },
		_grid: null,
		_store: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		bindStore: function($store){
			this._store = $store;
			this._store.bindDialog(this);
		},
		_valid: function(){
			var __frm = dijit.byId(this.fid);
			return __frm.isValid();
		},
		_submit: function(){
			if(this._valid()){
				var __newItem = dijit.byId(this.fid).getValues();
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
				var __myinfo = $MyInfo(this.vpath);
				this._reset();
				dijit.byId(this.fid +"_userunitText").set("value", __myinfo.corpname);
			}else{
				var __data = this._grid.getSelectItem();
				if(__data == null) return;
				dijit.byId(this.fid +"_idText").set("value", __data.i.id);

				dijit.byId(this.fid +"_rolenameText").set("value", __data.i.rolename);
				dijit.byId(this.fid +"_userunitText").set("value", __data.i.userunit);
				dijit.byId(this.fid +"_domainText").set("value", __data.i.domainid);
				dijit.byId(this.fid +"_enabledinheritText").set("value", __data.i.enabledinherit);

				dijit.byId(this.fid +"_maxgroupnumberText").set("value", __data.i.maxgroupnumber);
				dijit.byId(this.fid +"_enabledgroupnumberText").set("value", __data.i.enabledgroupnumber);

				dijit.byId(this.fid +"_maxusernumberText").set("value", __data.i.maxusernumber);
				dijit.byId(this.fid +"_enabledusernumberText").set("value", __data.i.enabledusernumber);
			}

			dijit.byId(this.fid +"_userunitText").set("disabled", true);
			this._valid();
		}
	});
});
