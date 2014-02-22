<script language="javascript" runat="server" src="../json2.min.asp"></script>
<!--#include file="db.asp"-->
<!--#include file="../json.inc"-->
<!--#include file="../framework.asp"-->
<!--#include file="../function.asp"-->
<!--#include file="net/foreworld/asp/sysmanage/sysmanage_user/selectById.asp"-->
<%
Response.CodePage = 65001
Response.Charset = "utf-8"
'Response.ContentType = "text/json"
Response.Expires = 0

Dim user_name, password

user_name = "'"& Trim(CheckStr(Request("model.user_name"))) &"'"
password = "'"& Trim(CheckStr(Request("model.password"))) &"'"

Dim str_sysmanage_user
str_sysmanage_user = Sysmanage_user_SelectById("", Array(Array("user_name", "=", user_name), Array("password", "=", password), Array("availsign", "=", 21), Array("startusing", "=", 23)))

If ChkDataExist(str_sysmanage_user) = False Then
	Response.Write "{'foreworld.net':'1.0v','opt':'f','msg':'第一次使用，请联网快速注册帐号！'}"
	Response.End
End If

Dim obj_sysmanage_user
Set obj_sysmanage_user = JSON.parse(str_sysmanage_user)

Dim sensitize_email1
sensitize_email1 = obj_sysmanage_user.rows.Get(0).data.Get(8)

If sensitize_email1 = "0" Then
	Response.Write "{'foreworld.net':'1.0v','opt':'f','msg':'您的邮箱尚未激活，请马上激活！'}"
	Response.End
End If

Response.Write str_sysmanage_user
Response.End
%>