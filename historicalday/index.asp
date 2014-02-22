<%@ TRANSACTION="REQUIRED" LANGUAGE="VBSCRIPT" %>

<%
On Error Resume Next

Dim Sql

Set Conn = Server.CreateObject("ADODB.Connection")

Conn.Open "driver={microsoft access driver (*.mdb)};dbq=" & Server.mappath("HistoricalDay.mdb")


Sql = "select * from historicalday where id=2"

Set Rs = Server.CreateObject("ADODB.Recordset")
Rs.Open Sql,Conn

If Err.Number = 0 Then 
	Response.Write Rs.Fields(3).Value
Else 
	Response.Write Err.Description
End If

Rs.Close
Set Rs = Nothing

Conn.Close
Set Conn = nothing
%>