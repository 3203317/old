/**
 * 作者：黄鑫
 * 日期：2013-03-11
 * 描述：登陆表单组件抽象类
 */
define(["dojo/_base/declare",
	"dojo/_base/lang",
	"dojo/i18n!./nls/_LoginForm",
	"dijit/form/Button",
	"dijit/form/ValidationTextBox",
	"dijit/form/Form"],function($declare,$lang,$i18n){
	return $declare("internal.main2._CommUseToolbar",null,{
		baseClass: "capecLoginForm",
		//自定义参数
		_labelUsername: $i18n.labelUsername,
		_labelPassword: $i18n.labelPassword,
		_buttonLabLogin: $i18n.buttonLabLogin,
		_buttonLabReset: $i18n.buttonLabReset,
		_imgAltVerifyCode: $i18n.imgAltVerifyCode,
		_labelVerifyCode: $i18n.labelVerifyCode,
		_imgTipVerifyCode: $i18n.imgTipVerifyCode,
		_missInputUsername: $i18n.missInputUsername,
		_missInputPassword: $i18n.missInputPassword,
		_missInputVerifyCode: $i18n.missInputVerifyCode,
		//登陆验证
		_onLogin: function(){
			//表单参数验证
			if(this._form.validate()){
				//登陆验证,用户自定义验证
				this.loginValidate();
			}
		},
		//重置表单
		_onReset: function(){
			this._form.reset();
		},
		//刷新图片验证码
		_refreshImgVerifyCode: function(){
			this.ImgVerifyCode.src = this.imgSrcVerifyCode +"&r="+ new Date();
		},
		//设置提示信息
		setMessage: function($message){
			this._message.innerHTML = $message;
		},
		//登陆验证
		loginValidate: function(){
			var __params = {
				async : false,
				handleAs : "json",
				content: this._form.getValues(),
				url : this.loginUrl +"&r="+ new Date(),
				load : $lang.hitch(this, "_onDataLoaded"),
				error : $lang.hitch(this, "_onError"),
			};
			dojo.xhrGet(__params);
		},
		_onDataLoaded: function($data){
			switch($data.opt){
			//重复登陆
			case 11:
			case 1:
				this.setMessage("");
				console.log("登陆成功，跳转页面："+ this.nextUrl);
				//location.href = this.nextUrl;
				break;
			default:
				this.setMessage($data.msg);
				break;
			}
		},
		_onError: function(){
			this.setMessage($i18n.loginErr);
		}
	});
});