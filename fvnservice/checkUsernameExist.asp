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

Dim user_name

user_name = "'"& Trim(CheckStr(Request("model.user_name"))) &"'"

Response.Write Sysmanage_user_SelectById("user_name", Array(Array("user_name", "=", user_name)))
Response.End
%>