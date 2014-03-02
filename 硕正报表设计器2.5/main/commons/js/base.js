/* 黄鑫 */
var capec = {
	base : {
		context_path : window.location.href,
		name : "newcapec.com",
		ver : "2.0v",
		corp : "郑州新开普电子股份有限公司",
		sname : "产品平台公共基础应用",
		theme : "pink",
		locale : "zh",
		expires : 30,
		language : {
			zh : {
				"identifier" : "value",
				"label" : "label",
				"items" : [ {
					"label" : "中文",
					"value" : "zh"
				}, {
					"label" : "英语",
					"value" : "en"
				} ]
			},
			en : {
				"identifier" : "value",
				"label" : "label",
				"items" : [ {
					"label" : "Chinese",
					"value" : "zh"
				}, {
					"label" : "English",
					"value" : "en"
				} ]
			}
		},
		skin : {
			zh : {
				"identifier" : "value",
				"label" : "label",
				"items" : [ {
					"label" : "默认",
					"value" : "capec"
				}, {
					"label" : "Pink",
					"value" : "pink"
				} ]
			},
			en : {
				"identifier" : "value",
				"label" : "label",
				"items" : [ {
					"label" : "Default",
					"value" : "capec"
				}, {
					"label" : "Pink",
					"value" : "pink"
				} ]
			}
		}

	}
};

Request = {
	QueryString: function($item){
		var __svalue = location.search.match(new RegExp('[\?\&]' + $item + '=([^\&]*)(\&?)','i'));
		return __svalue ? __svalue[1] : __svalue;
	}
};

capec.base.sessionId = Request.QueryString("sessionId");

if(capec.base.sessionId == null || capec.base.sessionId == "") location.href='/login/';

capec.base.context_path = "/"
		+ capec.base.context_path.substring(
				capec.base.context_path.indexOf("//") + 2).split("/")[1] + "/";
document.title = capec.base.sname;
