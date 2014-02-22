<%
Function Sysmanage_user_SelectById(FIELDS, OBJECT)
	If Not IsArray(OBJECT) Then
		Sysmanage_user_SelectById = "{'foreworld.net':'1.0v','opt':'f','msg':'参数错误','rows':[]}"
		Exit Function
	End If
	
	If FIELDS = "" Then
		FIELDS = "id, uuid, user_code, user_name, password, lpassword, realname, email2, sensitize_email1, sensitize_email2, sex, country, province, city, birthday, last_logintime, last_logouttime, isrememberpassword, isautologin, addtime, opt_sysmanage_user_id, availsign, startusing" 
	End If
	
	Dim sql
	sql = "select "
	sql = sql & FIELDS
	sql = sql & " from sysmanage_user where 1 = 1"
	
	Dim count, i
	count = UBound(OBJECT, 1)
	
	For i = 0 To count
		sql = sql & " and " & OBJECT(i)(0) & OBJECT(i)(1) & OBJECT(i)(2)
	Next

	Set fw = new FrameWork
	Sysmanage_user_SelectById = fw.[SELECT3](sql)
	Set fw = Nothing
	
End Function
%>
