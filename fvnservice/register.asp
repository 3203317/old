<script language="javascript" runat="server" src="../json2.min.asp"></script>
<!--#include file="db.asp"-->
<!--#include file="../json.inc"-->
<!--#include file="../framework.asp"-->
<!--#include file="../function.asp"-->
<!--#include file="net/foreworld/asp/sysmanage/sysmanage_user/selectById.asp"-->
<!--#include file="net/foreworld/asp/sysmanage/sysmanage_user/insert.asp"-->
<%
'判断用户名是否存在
'存在返回 不存在写入
Response.CodePage = 65001
Response.Charset = "utf-8"
'Response.ContentType = "text/json"
Response.Expires = 0

Dim user_name

user_name = "'"& Trim(CheckStr(Request("model.user_name"))) &"'"

If ChkDataExist(Sysmanage_user_SelectById("user_name", Array(Array("user_name", "=", user_name)))) Then
	Response.Write "{'foreworld.net':'1.0v','opt':'f','msg':'该用户名已存在'}"
	Response.End
End If

Response.Write Sysmanage_user_Insert(Array(Array("user_name", user_name)))
Response.End
%>