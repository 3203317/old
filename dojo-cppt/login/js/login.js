var checkSubmitFlg = false; // 防止重复提交
window.onload = function() {
	document.getElementById("userId").focus();
}
function keyLogin(event) {
	var evt = event?event:(window.event?window.event:null);
	if (document.all) {
		if (event.keyCode == 13) { // 回车键的键值为13
			checkout();
		}
	} else {
		if (evt.keyCode == 13) { // 回车键的键值为13
			checkout();
		}
	}

}
//检测只能是0----9数字的函数
function checkNum(e)
{
	var ok = "1234567890";
	for(var i=0; i<e.length; i++)
	{
		if (ok.indexOf(e.charAt(i))<0)
		{
			return false;
		}
	}
	return true;
}
/** 取消按钮事件 */
function fnClearText() {
	var userId = document.getElementById("userId");
	var pwd = document.getElementById("pwd");
	var verifycode = document.getElementById("verifycode");
	userId.value = "";
	pwd.value = "";
	verifycode.value = "";
	userId.focus();
}
/** 确定按钮事件 */
function fnLoginCheck() {
	if (checkout() != 1)
		return false;
	if (checkSubmitFlg)
		return false;
	checkSubmitFlg = true;
	//document.getElementById("myform").submit();

	window.location.href = 'welcome.html';
}
// 判断数据的合法性
function checkout() {
	var userId = document.getElementById("userId");
	var pwd = document.getElementById("pwd");
	if ("" == userId.value) {
		alert("用户名不能为空!");
		userId.focus();
		return ;
	}
	if ("" == pwd.value) {
		alert("密码不能为空!");
		pwd.focus();
		return ;
	}
	var username = userId.value+"";
	var passrowd = pwd.value+"";


	var loginDomain = null;

	var  param = {name:username,password:passrowd,sex:12};
	userPasswordService.encodePassword(param).addCallback(function(data){
		var userinfo = {name:username,password:data.password,sex:12};
		login.loginVerify(userinfo).addCallback(function(value){

			if(value.resultMsg == null){
				location.href=(loginDomain?"/"+loginDomain:"")+'/main/?sessionId='+ value.sessionId;
			}
			else{
				document.getElementById("errorMsg").innerHTML = '<font color="red">'+value.resultMsg.errorMsg+'</font>';
				document.getElementById("errorMsg").style.display="";
				setTimeout(function(){
					document.getElementById("errorMsg").style.display="none";
				}, 1000);
			}
		});
	});

}
