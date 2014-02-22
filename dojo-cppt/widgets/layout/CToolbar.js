/**
 * 作者：黄鑫
 * 日期：2013-05-13
 * 描述：CToolbar
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
    "capec/utils/ajax",
	"dijit/layout/ContentPane"], function($declare, $lang, $ajax, $ContentPane){
	return $declare("internal.widgets.layout.CToolbar", $ContentPane, {
		_btnVerifyUrl: "testData/domainresource_role3.json",
		_setModuleidAttr: function($value){
			$ajax({
				async: true,
				content: { moduleid: $value },
				url : this.vpath + this._btnVerifyUrl,
				callback: $lang.hitch(this, "_btnPermitBack")
			});
		},
		_btnPermitBack: function($data){
			for(var __i_3=0,__item_3=null;__item_3=$data.items[__i_3];__i_3++){
				var __id_4 = this.id +"_"+ __item_3.id;
				var __btn_4 = dijit.byId(__id_4);
				if(__btn_4){
					if(__item_3.opt == 1){
						__btn_4.domNode.style.display = "";
						__btn_4.set("optStatus", 1);
					}else if(__item_3.opt == 2){
						__btn_4.domNode.style.display = "";
						__btn_4.set("disabled", true);
						__btn_4.set("optStatus", 2);
					}
					__btn_4.set("iconClass", __item_3.iconClass);
				}
			}
		}
	});
});
