/**
 * 作者：黄鑫
 * 日期：2013-04-27
 * 描述：批量数据导入
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"capec/dialog/FormDialog",
	"dojo/_base/lang"], function($connect,$declare,$FormDialog,$lang) {
	return $declare($FormDialog,{
		fid: "internal_sys_user_ImportForm",
		title: "",
		href: "sys/user/importForm.html",
		style: { height: "128px", width: "280px" },
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
				this.set("title","批量数据导入");
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				dijit.byId(this.fid).reset();
			}
			this._valid();
		}
	});
});
