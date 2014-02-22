﻿<!--#include file="BlogCommon.asp" -->
<!--#include file="common/function.asp" -->
<!--#include file="common/library.asp" -->
<!--#include file="common/upfile.asp" -->
<!--#include file="common/cache.asp" -->
<!--#include file="common/checkUser.asp" -->
<!--#include file="class/cls_logAction.asp" -->
<!--#include file="control/f_control.asp" -->
<%
response.expires=-1
response.expiresabsolute=now()-1
response.cachecontrol="no-cache"
'*************************************************
' Ajax 类 ASP 代码 // AjaxRequest.js 框架支持
' evio edit 
'*************************************************
Dim title, cname, Message, lArticle, postLog, SaveId, ReadForm, SplitReadForm
Dim cCateID, e_tags, ctype, logWeather, logLevel, logcomorder, logDisComment, logIsTop, logIsHidden, logMeta, logFrom, logFromURL, logdisImg, logDisSM, logDisURL, logDisKey, logQuote
'-------------- [Alias] -----------------
If request.QueryString("action") = "checkAlias" then
	If ChkPost() Then
   		dim strcname, checkcdb
   		strcname = Checkxss(request.QueryString("cname"))
   		set checkcdb = conn.execute("select * from [blog_Content] where [log_cname]="""&strcname&"""")
       		if checkcdb.bof or checkcdb.eof then
          		response.write "<img src=""images/check_right.gif"">"
       		else
          		response.write "<img src=""images/check_error.gif"">"
       		end if
    	set checkcdb=nothing
	End If
'------------- [mdown] ---------------
elseif request.QueryString("action") = "type1" then
    If ChkPost() Then
		dim mainurl, main, mainstr
        	mainurl = Checkxss(request.QueryString("mainurl"))
        	main = trim(Checkxss(request.QueryString("main")))
        	response.clear()
        	mainstr = ""
    	If Len(memName)>0 Then
        	mainstr = mainstr & "<img src=""images/download.gif"" alt=""下载文件"" style=""margin:0px 2px -4px 0px""/> <a href="""&mainurl&""" target=""_blank"">"&main&"</a>"
    	Else
        	mainstr = mainstr & "<img src=""images/download.gif"" alt=""只允许会员下载"" style=""margin:0px 2px -4px 0px""/> 该文件只允许会员下载! <a href=""login.asp"">登录</a> | <a href=""register.asp"">注册</a>"
    	End If
    	response.write mainstr
	End If
elseif request.QueryString("action") = "type2" then
    If ChkPost() Then
		dim main2, mstr
        	main2 = Checkxss(request.QueryString("main"))
        	response.clear()
        	mstr = ""
    	If Len(memName) > 0 Then
       		mstr=mstr&"<img src=""images/download.gif"" alt=""下载文件"" style=""margin:0px 2px -4px 0px""/> <a href="""&main2&""" target=""_blank"">下载此文件</a>"
    	Else
       		mstr=mstr&"<img src=""images/download.gif"" alt=""只允许会员下载"" style=""margin:0px 2px -4px 0px""/> 该文件只允许会员下载! <a href=""login.asp"">登录</a> | <a href=""register.asp"">注册</a>"
    	End If
    	response.write mstr
	End If
'--------------- [hidden] -----------------
elseif request.QueryString("action") = "Hidden" then
    If Len(memName) > 0 Then
	   response.write "1"
	Else
	   response.Write "0"
	End If
'--------------- [用户名检测] -----------------
elseif request.QueryString("action") = "checkname" then
	If ChkPost() Then
		dim strname, checkdb
			strname = Checkxss(request.QueryString("usename"))
			set checkdb = conn.execute("select * from blog_Member where mem_Name='"&strname&"'")
				if checkdb.bof or checkdb.eof then
					response.write"<font color=""#0000ff"">用户名未注册！</font>|$|True"
				else
					response.write"<font color=""#ff0000"">用户名已注册！</font>|$|False"
				end if
			set checkdb = nothing
	End If
'--------------------------- [Ajax草稿保存 -- 发表时保存] --------------------------    
elseif request.QueryString("action") = "PostSave" then    
    If ChkPost() Then
		ReadForm = Request.Form
		SplitReadForm = Split(ReadForm, "|$|")
'		response.Write(Ubound(SplitReadForm))
'		response.End()
		'str = escape(title) + "|$|" + escape(cname) + "|$|" + escape(ctype) + "|$|" + escape(logweather) + "|$|" + escape(logLevel) + "|$|" + escape(logcomorder) + "|$|" + escape(logDisComment) + "|$|" + escape(logIsTop) + "|$|" + escape(logMeta) + "|$|" + escape(logFrom) + "|$|" + escape(logFromURL) + "|$|" + escape(logdisImg) + "|$|" + escape(logDisSM) + "|$|" + escape(logDisURL) + "|$|" + escape(logDisKey) + "|$|" + escape(logQuote) + "|$|" + escape(Tags) + "|$|" + escape(mCateID) + "|$|" + escape(Message)
		'            0                      1                        2                         3                         4                              5                6                             7                         8                         9                             10                        11              12                        13                        14                              15                       16                     17        18
        title = UnEscape(SplitReadForm(0)) 
        cname = UnEscape(SplitReadForm(1))
		ctype = UnEscape(SplitReadForm(2))
		logWeather = UnEscape(SplitReadForm(3))
		logLevel = UnEscape(SplitReadForm(4))
		logcomorder = UnEscape(SplitReadForm(5))
		logDisComment = UnEscape(SplitReadForm(6))
		logIsTop = UnEscape(SplitReadForm(7))
		'logIsHidden = Request.QueryString("logIsHidden")
		logMeta = UnEscape(SplitReadForm(8))
		logFrom = UnEscape(SplitReadForm(9))
		logFromURL = UnEscape(SplitReadForm(10))
		logdisImg = UnEscape(SplitReadForm(11))
		logDisSM = UnEscape(SplitReadForm(12))
		logDisURL = UnEscape(SplitReadForm(13))
		logDisKey = UnEscape(SplitReadForm(14))
		logQuote = UnEscape(SplitReadForm(15))
		e_tags =  UnEscape(SplitReadForm(16))
		cCateID = UnEscape(SplitReadForm(17))
		Message = UnEscape(SplitReadForm(18))

        Set lArticle = New logArticle    
        lArticle.logTitle = title    
        lArticle.logcname = cname 
		lArticle.logCtype = ctype   
        lArticle.logMessage = Message 
		lArticle.categoryID = cCateID   
        lArticle.logAuthor = memName ' 关键是这个
		lArticle.logTags = e_tags    
        lArticle.logIsDraft = CBool(true)
		lArticle.isajax = true
		lArticle.logWeather = logWeather 
		lArticle.logLevel = logLevel
		lArticle.logCommentOrder = logcomorder  
		lArticle.logDisableComment = logDisComment
		lArticle.logIsTop = logIsTop
		lArticle.logMeta = logMeta
		lArticle.logFrom = logFrom
    	lArticle.logFromURL = logFromURL
		lArticle.logDisableImage = logdisImg
		lArticle.logDisableSmile = logDisSM
		lArticle.logDisableURL = logDisURL
		lArticle.logDisableKeyWord = logDisKey
		lArticle.logTrackback = logQuote
        postLog = lArticle.postLog    
        Set lArticle = Nothing   
            
        response.write "草稿于 " & DateToStr(now(), "Y-m-d H:I:S") & " <font color='#9C0024'>保存</font>成功,请不要刷新本页,以免丢失信息!|$|1|$|"&postLog(2)    
    else    
        response.write "非法提交数据"   
    end if    
'--------------------------- [Ajax草稿保存 -- 编辑时保存] --------------------------    
elseif request.QueryString("action") = "UpdateSave" then    
    If ChkPost() Then 
		ReadForm = Request.Form
		SplitReadForm = Split(ReadForm, "|$|")
'		response.Write(Ubound(SplitReadForm))
'		response.End()
		'str = escape(title) + "|$|" + escape(cname) + "|$|" + escape(ctype) + "|$|" + escape(logweather) + "|$|" + escape(logLevel) + "|$|" + escape(logcomorder) + "|$|" + escape(logDisComment) + "|$|" + escape(logIsTop) + "|$|" + escape(logMeta) + "|$|" + escape(logFrom) + "|$|" + escape(logFromURL) + "|$|" + escape(logdisImg) + "|$|" + escape(logDisSM) + "|$|" + escape(logDisURL) + "|$|" + escape(logDisKey) + "|$|" + escape(logQuote) + "|$|" + escape(Tags) + "|$|" + escape(mCateID) + "|$|" + escape(Message) + "|$|" + escape(id)
		'            0                      1                        2                         3                         4                              5                6                             7                         8                         9                             10                        11              12                        13                        14                              15                       16                     17      18 
        title = UnEscape(SplitReadForm(0))    
        cname = UnEscape(SplitReadForm(1))   
        Message = UnEscape(SplitReadForm(18)) 
		e_tags =  UnEscape(SplitReadForm(16)) 
		ctype = UnEscape(SplitReadForm(2))
		logWeather = UnEscape(SplitReadForm(3)) 
		logLevel = UnEscape(SplitReadForm(4))
		logcomorder = UnEscape(SplitReadForm(5))
		logDisComment = UnEscape(SplitReadForm(6))
		logIsTop = UnEscape(SplitReadForm(7))
		'logIsHidden = Request.QueryString("logIsHidden")
		logMeta = UnEscape(SplitReadForm(8))
		logFrom = UnEscape(SplitReadForm(9))
		logFromURL = UnEscape(SplitReadForm(10))
		logdisImg = UnEscape(SplitReadForm(11))
		logDisSM = UnEscape(SplitReadForm(12))
		logDisURL = UnEscape(SplitReadForm(13))
		logDisKey = UnEscape(SplitReadForm(14))
		logQuote = UnEscape(SplitReadForm(15))
        cCateID = UnEscape(SplitReadForm(17))
		
        SaveId = UnEscape(SplitReadForm(19))   
            
        Set lArticle = New logArticle    
        lArticle.logTitle = title    
        lArticle.logcname = cname 
		lArticle.logCtype = ctype   
        lArticle.logMessage = Message    
        lArticle.logAuthor = memName ' 关键是这个
		lArticle.logTags = e_tags
		lArticle.logIsDraft = CBool(true)
		lArticle.isajax = true   
		lArticle.logWeather = logWeather 
		lArticle.logLevel = logLevel
		lArticle.logCommentOrder = logcomorder
		lArticle.logDisableComment = logDisComment
		lArticle.logIsTop = logIsTop
		lArticle.logMeta = logMeta
		lArticle.logFrom = logFrom
    	lArticle.logFromURL = logFromURL
		lArticle.logDisableImage = logdisImg
		lArticle.logDisableSmile = logDisSM
		lArticle.logDisableURL = logDisURL
		lArticle.logDisableKeyWord = logDisKey
		lArticle.logTrackback = logQuote
		lArticle.categoryID = cCateID
        postLog = lArticle.editLog(SaveId)    
        Set lArticle = Nothing   
            
        response.write "草稿于 " & DateToStr(now(), "Y-m-d H:I:S") & " <font color='#AF5500'>更新</font>成功,请不要刷新本页,以免丢失信息!|$|0|$|"&SaveId    
    else    
        response.write "非法提交数据"   
    end if
'-------------[防盗链]---------------
ElseIf request("action") = "Antidown" or request("action") = "Antimdown" then
	If ChkPost() Then
		Dim down, showdownstr, id
			Session(CookieName & "Antimdown") = "pjblog_Antimdown"
			id = Checkxss(request("id"))
			if instr(id, "&") > 0 then id = split(id,"&")(0)
			Set down = conn.execute("select FilesPath,FilesCounts from blog_Files where id="&id)
				response.clear()
				If request("action") = "Antimdown" and memName = empty Then
					showdownstr = getFileIcons(getFileInfo(down(0))(9))&" 该文件只允许会员下载! <a href=""login.asp"" accesskey=""L"">登录</a> | <a href=""register.asp"">注册</a>"
				Else
					showdownstr = getFileIcons(getFileInfo(down(0))(9))&" <a href="""&request("downurl")&""" target=""_blank"">"&trim(checkstr(request("main")))&"</a><font color=""#999999"">("&getFileInfo(down(0))(0)&")</font><br/><font color=""#999999"">["&Datetostr(getFileInfo(down(0))(10),"Y-m-d H:I A")&"; 下载次数:"&down(1)&"]</font>"
				End If
			response.write showdownstr
	else
		response.write "非法提交数据"
	end if
else
	response.write "非法操作!"
End If
%>
