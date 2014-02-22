<%@ TRANSACTION="REQUIRED" LANGUAGE="VBSCRIPT" %>

<%
Response.CodePage = 65001
Response.Charset = "utf-8"
On Error Resume Next
Randomize
Dim Sql
Dim result

Set Conn = Server.CreateObject("ADODB.Connection")

Conn.Open "driver={microsoft access driver (*.mdb)};dbq=" & Server.mappath("cnzzz.asp")

Sql = "select top 1 * from YesoulChenYu order by Rnd(id - " & timer() & ")"

Set Rs = Server.CreateObject("ADODB.Recordset")
Rs.Open Sql,Conn

result = "{""foreworld"":{"

If Err.Number = 0 Then 
	result = result & """chengyu"":"""& Rs.Fields(0).Value & ""","
	result = result & """pinyin"":""" & Rs.Fields(1).Value & ""","
	result = result & """diangu"":""" & Rs.Fields(2).Value & ""","
	result = result & """chuchu"":""" & Rs.Fields(3).Value & ""","
	result = result & """lizi"":""" & Rs.Fields(4).Value & ""","
	result = result & """spinyin"":""" & Rs.Fields(5).Value & ""","
	result = result & """id"":""" & Rs.Fields(6).Value & """"
Else 
	'Response.Write Err.Description
End If


result = result & "}}"

Rs.Close
Set Rs = Nothing

Conn.Close
Set Conn = Nothing


Response.Write result
Response.End
%>