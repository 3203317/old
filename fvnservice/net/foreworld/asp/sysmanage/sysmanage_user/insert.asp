<%
Function Sysmanage_user_Insert(OBJECT)
	If Not IsArray(OBJECT) Then
		Sysmanage_user_Insert = "{'foreworld.net':'1.0v','opt':'f','msg':'参数错误','rows':[]}"
		Exit Function
	End If
	
	Dim count, i, sql1, sql2
	count = UBound(OBJECT, 1)
	
	For i = 0 To count
		sql1 = sql1 & OBJECT(i)(0) & ", "
		sql2 = sql2 & OBJECT(i)(1) & ", "
	Next
	
	sql1 = sql1 & "addtime, opt_sysmanage_user_id, availsign, startusing"
	sql2 = sql2 & "'" & Now() & "', 1, 1, 1"
	
	
	Dim sql
	sql = "insert into sysmanage_user ("
	sql = sql & sql1 
	sql = sql & ") values ("
	sql = sql & sql2
	sql = sql & ")"
	
	Set sql1 = Nothing
	Set sql2 = Nothing

	Set fw = new FrameWork
	Sysmanage_user_Insert = fw.[INSERT3](sql)
	Set fw = Nothing
	
End Function
%>
