/* 黄鑫 */
capec.form = {
	async: false,
	dataType: "json",
	url: [],
	eUrl: [],
	fail: [],
	error: function(){
		alert(capec.form.fail[0].toString());
		location.href = "about:blank";
	}
};

/**
 * submit失败代码
 * */
capec.form.fail[0] = "访问远程数据失败，请重试！";
capec.form.fail[1] = "用户名或密码输入错误，请重试！";
capec.form.fail[2] = "会话超时，请重新登陆系统！";
capec.form.fail[3] = "您没有操作权限，请联系系统管理员！";
capec.form.fail[4] = "操作异常，请重试！";
capec.form.fail[5] = "您没有登陆权限，请联系系统管理员！";
capec.form.fail[6] = "表单验证失败！";
capec.form.fail[7] = "数据库操作失败或语法错误！";


capec.form.url[0] = capec.base.context_path;
capec.form.url[3] = "r=";

capec.form.eUrl[0] = capec.base.context_path;
capec.form.eUrl[1] = "login.html?msg=";

/**
 * 数据提交方法
 * {
 * url:"*.do",				//数据源
 * method:"get",			//默认为post
 * dataType:"json",			//默认为json
 * forms:["form"],			//提交表单组
 * validate:true,			//验证参数,默认为false
 * params:{p1:'v1',p2:'v2'},//额外参数
 * obj:this					//调用对象自身
 * }
 * */
capec.form.submit = function($request){
	if(!$request.cbe()){
		return;
	}
	capec.form.url[1] = $request.url;
	capec.form.url[2] = $request.url.indexOf("?") == -1 ? "?" : "&";
	capec.form.url[4] = new Date();
	dojo.xhrPost({
		async: capec.form.async,
		handleAs: capec.form.dataType,
		url: capec.form.url.join(""),
		content: $request.params,
		load: function($data,$ioArgs){
			if($data.opt == "s"){
				$request.cba($data,$data.widget);
			}
			else{
				switch($data.code){
					case "1":
						form.eUrl[2] = form.fail[1].toString();
						location.href = form.eUrl.join("");
						break;
					case "2":
						form.eUrl[2] = form.fail[2].toString();
						location.href = form.eUrl.join("");
						break;
					case "3":
						$params.cba($data,$params.widget);
						break;
					case "5":
						form.eUrl[2] = form.fail[5].toString();
						location.href = form.eUrl.join("");
						break;
					case "6":
						$params.cba($data,$data.widget);
						break;
					case "7":
						alert($data.oem);
						$params.cba($data,$data.widget);
						break;
					default:
						alert(form.fail[4]);
						break;
				}
			}
			$data = null;
			$ioArgs = null;
		},
		error: function($error,$ioArgs){
			alert($error.message);
		}
	});
}