<!--#include file="sql.asp"-->
<!--#include file="../json.inc"-->
<!--#include file="../framework.asp"-->
<!--#include file="../function.asp"-->
<%
Response.CodePage = 65001
Response.Charset = "utf-8"
'Response.ContentType = "text/json"
Response.Expires = 0

Dim fw

Set fw = new FrameWork

Dim sql

sql = "select top 1 * from YesoulChenYu order by Rnd(id - " & timer() & ")"

'Response.Write fw.[SELECT]("select top 1 * from YesoulChenYu order by Rnd(id - " & timer() & ")")

If Request("type") = "3" Then 
	Response.Write fw.[SELECT3](sql)
Else 
	Response.Write fw.[SELECT](sql)
End If

Set sql = Nothing 
Set fw = Nothing 

Response.End
%>