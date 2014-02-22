/**
 * 作者：黄鑫
 * 日期：2013-03-07
 * 描述：组织表单
 */
define(["dojo/_base/connect",
	"dojo/_base/declare",
	"dojo/_base/lang",
    "capec/utils/ajax",
	"capec/dialog/FormDialog",
	"dijit/form/Button",
	"internal/widgets/comboBox/CodeComboBox",
	"dijit/form/DateTextBox",
	"dijit/form/SimpleTextarea",
	"dijit/form/NumberSpinner",
	"dijit/form/Select",
	"capec/form/ComboBoxTree",
	"internal/sys/group/UserRelatGrid",
	"internal/sys/group/UnUserRelatGrid"], function($connect,$declare,$lang,$ajax,$FormDialog,$Button) {
	return $declare($FormDialog,{
		_addButtons: function(){
			var __btnParams = {};
			__btnParams["class"] = this.bassClass +"dialogPaneButton";

			var __newBtn = null;
			var __connect = null;

			__btnParams.label = "关闭";
			__newBtn = new $Button(__btnParams);
			this._div.appendChild(__newBtn.domNode);
			this._connects_.push($connect.connect(__newBtn, "onClick", this, "hide"));
		},
		fid: "internal_sys_group_UserForm",
		title: "用户分配",
		href: "sys/group/userForm.html",
		style: { height: "394px", width: "600px" },
		_grid: null,
		bindGrid: function($grid){
			this._grid = $grid;
		},
		_valid: function(){
			var __frm = dijit.byId(this.fid);
			return __frm.isValid();
		},
		_submit: function(){
//			var __data_3 = this._grid.getSelectItem();
//			var __lGrid = dijit.byId(this.fid +"_lGrid");
//			var __itemArr = [];
//			for(var __i_3=0,__item_3; __item_3=__lGrid.getAllItems()[__i_3]; __i_3++){
//				var __item_4 = {
//					userid: __item_3.userid[0]
//				};
//				__itemArr.push(__item_4);
//			}
//			console.log(__itemArr);
//			$ajax({
//				async : true,
//				content: { groupid: __data_3.i.id, users: dojo.toJson(__itemArr) },
//				url : this.vpath +"testData/user_o0.json?orgId=",
//				callback : $lang.hitch(this, "_saveData")
//			});
		},
		_saveData: function($data){
			console.log($data);
			//this.hide();
		},
		_saveData1: function(){
			var __lGrid = dijit.byId(this.fid +"_lGrid");
			var __rGrid = dijit.byId(this.fid +"_rGrid");
			var __sels = __rGrid.getSelectItems();
			if(__sels.length == 0) return;
			console.log(__sels);
			var __data_3 = this._grid.getSelectItem();
			$ajax({
				async : true,
				content: { groupid: __data_3.i.id, users: dojo.toJson(__sels), type: "add" },
				url : this.vpath +"testData/user_o0.json?orgId=",
				callback : function(){
					__lGrid.addItems(__sels);
				}
			});
		},
		_saveData2: function(){
			var __lGrid = dijit.byId(this.fid +"_lGrid");
			var __rGrid = dijit.byId(this.fid +"_rGrid");
			var __sels = __lGrid.getSelectItems();
			if(__sels.length == 0) return;
			console.log(__sels);
			var __data_3 = this._grid.getSelectItem();
			$ajax({
				async : true,
				content: { groupid: __data_3.i.id, users: dojo.toJson(__sels), type: "del" },
				url : this.vpath +"testData/user_o0.json?orgId=",
				callback : function(){
					__rGrid.addItems(__sels);
				}
			});
		},
		_reset: function(){
			if(this.model == "Create"){
				dijit.byId(this.fid).reset();
			}else{
				this._setData();
			}
		},
		_searchUnUser: function(){
			var __data_3 = this._grid.getSelectItem();
			var __param = {
				depcode: dijit.byId(this.fid +"_dept_codeText").key,
				username: dijit.byId(this.fid +"_usernameText").getValue(),
				groupid: __data_3.i.id
			};
			var __rGrid = dijit.byId(this.fid +"_rGrid");
			__rGrid.loadData(__param);
		},
		_draw: function(){
			console.log("绘制表单");
			console.log("Ajax请求需同步或其他方式");

			var __searchBtn = dijit.byId(this.fid +"_searchBtn");
			__searchBtn.on("click", $lang.hitch(this, "_searchUnUser"));

			var __leftBtn = dijit.byId(this.fid +"_leftBtn");
			var __rightBtn = dijit.byId(this.fid +"_rightBtn");

			__leftBtn.on("click", $lang.hitch(this, "_saveData1"));
			__rightBtn.on("click", $lang.hitch(this, "_saveData2"));
		},
		_setTitle: function(){
			switch(this.model){
			case "Update":
				this.set("title", "用户分配    [组："+ this._grid.getSelectItem().i.groupname+"]");
				break;
			}
		},
		_setData: function(){
			if(this.model == "Create"){
				this._reset();
			}else{
				var __data_3 = this._grid.getSelectItem();
				if(__data_3 == null || __data_3 == undefined) return;
				/* 为左Grid赋值  */
				var __lGrid = dijit.byId(this.fid +"_lGrid");
				console.log("组"+__data_3.i.id);
				__lGrid.loadData(__data_3.i.id);

				/* 为右Grid赋值 */
				dijit.byId(this.fid).reset()
				dijit.byId(this.fid +"_dept_codeText").set("key",0);
				this._searchUnUser();
			}
			this._valid();
		}
	});
});
