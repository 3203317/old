//PJBlog 3 Ajax Action File
//Author:evio
var GetFile = ["Action.asp?action="];
var GetAction = ["Hidden&", "checkname&", "PostSave&", "UpdateSave&"];

// 关于 [Hidden] 标签的修复代码
function Hidden(i){
	var TempStr;
	var ajax = new AJAXRequest;
	ajax.get(
			 GetFile[0]+GetAction[0]+"TimeStamp="+new Date().getTime(),
			 function(obj) {
				 TempStr = obj.responseText;
				 if ( TempStr == "1" ){
					 $("hidden1_"+i).style.display = "";
					 $("hidden2_"+i).style.display = "none";
				 }else{
					 $("hidden1_"+i).style.display = "none";
					 $("hidden2_"+i).style.display = "";
				 }
			 }
	 );
}

//关于注册页面的用户名判断
function CheckName(){
	var TempStr, StrHtml, Bool;
	$("CheckName").innerHTML = "<font color='#ccc'>检测用户名...</font>";
	var UserName = document.forms["frm"].username.value;
	var HoldValue = $("PostBack_UserName").value;
	var ajax = new AJAXRequest;
	ajax.get(
			 GetFile[0]+GetAction[1]+"usename="+escape(UserName)+"&TimeStamp="+new Date().getTime(),
			 function(obj) {
				 TempStr = obj.responseText;
				 StrHtml = TempStr.split("|$|")[0];
				 Bool = TempStr.split("|$|")[1];
				 if ( Bool == "True" ){
					 $("PostBack_UserName").value = "True|$|" + HoldValue.split("|$|")[1];
					 $("CheckName").innerHTML = StrHtml;
				 }else{
					 $("PostBack_UserName").value = "False|$|" + HoldValue.split("|$|")[1];
					 $("CheckName").innerHTML = StrHtml;
				 }
			 }
	 );
}
function IsPost(){
	if ($("PostBack_UserName").value == "True|$|True"){
		document.forms["frm"].submit();
	}else{
		alert("你填写的资料不正确，或者用户名已被注册！");
	}
}

//关于密码的判断
function CheckPwd(){
	$("CheckPwds").innerHTML = "正在检测密码";
	var Pwd = $("cpassword").value;
	var SePwd = $("cConfirmpassword").value;
	var HoldValue = $("PostBack_UserName").value;
	if (Pwd != SePwd){
		$("PostBack_UserName").value = HoldValue.split("|$|")[0] + "|$|False";
		$("CheckPwds").innerHTML = "&nbsp;&nbsp;<font color=red>两次输入的密码不同</font>";
	}else{
		$("PostBack_UserName").value = HoldValue.split("|$|")[0] + "|$|True";
		$("CheckPwds").innerHTML = "&nbsp;&nbsp;<font color='blue'>两次输入的密码相同！</font>"
	}
}

// Ajax草稿保存
var e_event = null;
function UBB_AjaxLogSave(){
	var obj = $("AjaxTimeSave");
	if (obj.style.display == "none"){ obj.style.display = ""; }
	if (e_event == null){
		OutTime();
	}else{
		clearTimeout(e_event);
		e_event = null;
		obj.innerHTML = (obj.innerHTML + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:9px;text-decoration:none'><a href='javascript:void(0);' onclick=$('AjaxTimeSave').style.display='none'><b>Close</b></a></span>");
	}
}
function OutTime(){    
    var loop = time;    
    $("AjaxTimeSave").innerHTML = loop + " 秒后自动保存为草稿!";    
    e_event = setTimeout('goTime('+loop+');',2000);    
}    
function goTime(i){    
    i = i - 1;    
    if ( i != 0 ){    
        $("AjaxTimeSave").innerHTML = i + " 秒后自动保存为草稿!";    
        e_event = setTimeout("goTime("+i+");",1000);    
    }else{    
        PostSave();   
		try{
        	e_event = setTimeout('goTime('+(time+1)+')',3000); 
		}catch(e){
			if (e.description.length > 0){
				e_event = setTimeout('goTime('+(time+1)+')',3000);
			}
		}
    }    
}    
   
//发表时的保存    
function PostSave(){    
    var TempStr, left, right, ToWhere, postId;    
    var ajax = new AJAXRequest;    
    //var str = ReadCode();  	
    var FirstPost = document.forms["frm"].FirstPost.value;    
    if (FirstPost == 1){    
        //var zpt =document.forms["frm"].postbackId.value;    
        ToWhere = GetAction[3];    
    }else{    
        ToWhere = GetAction[2];    
    }   
	//alert(ReadCode());
	//alert(document.forms["frm"].postbackId.value)
    ajax.post(    
             GetFile[0] + ToWhere + "TimeStamp=" + new Date().getTime(),
			 ReadCode(),
             function(obj) {    
                 TempStr = obj.responseText;    
                 left = TempStr.split("|$|")[0];    
                 right = TempStr.split("|$|")[1];    
                 postId = TempStr.split("|$|")[2];    
                 $("AjaxTimeSave").innerHTML = left;    
                 if ( right == 1 ){    
                     document.forms["frm"].FirstPost.value = 1;    
                     document.forms["frm"].postbackId.value = postId;    
                 }    
             }    
     );    
}    
   
// 从表单中读取数据    
function ReadCode(){ 
	var mCateID, str, cname, ctype, logweather, logLevel;
	
	//title
    var title = document.forms["frm"].title.value;  
	
	//cname
    try {
		cname = document.forms["frm"].cname.value;
	} catch(e){
		if (e.description != "" ){
			cname = "";
		} 
	} 
	
	//content
    var Message = document.forms["frm"].Message.value; 
	
	//tag
	var Tags = document.forms["frm"].tags.value; 
	
	//cate
	try{
		mCateID = $("select2").options[$("select2").options.selectedIndex].value;	
	}catch(e){
		if (e.description != "" ){
			try{
				mCateID = $("log_CateID").value;
			}catch(e){
				alert(e.description);
			}
		}
	}
	
	//ctype
	try{
		ctype = document.forms["frm"].ctype.options[document.forms["frm"].ctype.options.selectedIndex].value;	
	}catch(e){
		if (e.description != "" ){
			ctype = "1";
		}
	}
	
	//logweather
	logweather = select_model("logweather", "sunny");
	
	//logLevel
	logLevel = select_model("logLevel", "level3");
	
	//评论倒序
	logcomorder = checkbox_model("label");
	
	//禁止评论
	logDisComment = checkbox_model("label2");
	
	//日志置顶
	logIsTop = checkbox_model("label3");
	
	//隐私和META
	//logIsHidden = checkbox_model("Secret");
	logMeta = checkbox_model("Meta");
	
	//来源网址
	logFrom = $("log_From").value;
	logFromURL = $("log_FromURL").value;
	
	//禁止显示图片
	logdisImg = checkbox_model("label4");
	
	//禁止表情转换
	logDisSM = checkbox_model("label5");
	
	//禁止自动转换链接
	logDisURL = checkbox_model("label6");
	
	//禁止自动转换关键字
	logDisKey = checkbox_model("label7");
	
	//引用通告
	logQuote = $("logQuote").value;
	
    //str = "title=" + escape(title) + "&cname=" + escape(cname) + "&ctype=" + escape(ctype) + "&logweather=" + escape(logweather) + "&logLevel=" + escape(logLevel) + "&logcomorder=" + escape(logcomorder) + "&logDisComment=" + escape(logDisComment) + "&logIsTop=" + escape(logIsTop) + "&logMeta=" + escape(logMeta) + "&logFrom=" + escape(logFrom) + "&logFromURL=" + escape(logFromURL) + "&logdisImg=" + escape(logdisImg) + "&logDisSM=" + escape(logDisSM) + "&logDisURL=" + escape(logDisURL) + "&logDisKey=" + escape(logDisKey) + "&logQuote=" + escape(logQuote) + "&tags=" + escape(Tags) + "&cateid=" + escape(mCateID) + "&Message=" + escape(Message) + "&";
	
	str = escape(title) + "|$|" + escape(cname) + "|$|" + escape(ctype) + "|$|" + escape(logweather) + "|$|" + escape(logLevel) + "|$|" + escape(logcomorder) + "|$|" + escape(logDisComment) + "|$|" + escape(logIsTop) + "|$|" + escape(logMeta) + "|$|" + escape(logFrom) + "|$|" + escape(logFromURL) + "|$|" + escape(logdisImg) + "|$|" + escape(logDisSM) + "|$|" + escape(logDisURL) + "|$|" + escape(logDisKey) + "|$|" + escape(logQuote) + "|$|" + escape(Tags) + "|$|" + escape(mCateID) + "|$|" + escape(Message);
	 if (document.forms["frm"].FirstPost.value == 1){
		 var zpt =document.forms["frm"].postbackId.value;    
         str = str + "|$|" + escape(zpt);
	 }  
	
    return str;    
}

//select 选择器
function select_model(A, B){
	var c;
	try{
		c = $(A).options[$(A).options.selectedIndex].value;
	}catch(e){
		if (e.description != ""){
			c = B;
		}
	}
	return c;
}

// checkbox 选择器
function checkbox_model(A){
	var temp;
	if ($(A).checked){
		temp = $(A).value;
	}else{
		temp = "0";
	}
	return temp;
}