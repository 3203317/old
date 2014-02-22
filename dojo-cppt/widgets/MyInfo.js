/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：获取我的会话信息
 */
define(["capec/utils/ajax"],function($ajax) {
	return function($vpath){
		var __myInfo = null;
		$ajax({
			sync : true,
			url : $vpath +"testData/myinfo.json",
			callback: function($data){ __myInfo = $data.myinfo; }
		});
		return __myInfo;
	};
});