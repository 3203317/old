<%@ Page Language="C#" AutoEventWireup="true" Trace="true" %> 
<%@ Import Namespace="System.Diagnostics" %> 
<%@ Import Namespace="System.Globalization" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" > 
<head> 
   <title>ASP.NET2.0̽��</title> 
</head> 
<body> 
     ��������� 
   http://<%=HttpContext.Current.Request.Url.Host%> <%=HttpContext.Current.Request.ApplicationPath %><br /> 
    IP��ַ�� 
  <%= Request.ServerVariables["LOCAl_ADDR"]   %><br /> 
      ������ 
   <%= Request.ServerVariables["SERVER_NAME"]%><br /> 
     �˿ڣ� 
    <%=Request.ServerVariables["Server_Port"].ToString() %><br /> 
     ���ļ�����·���� 
     <%= Request.PhysicalApplicationPath %><br /> 
        ����ϵͳ�� 
      <%= Environment.OSVersion.ToString()%> <br /> 
       ����ϵͳ�����ļ��У� 
      <%=Environment.SystemDirectory.ToString() %> <br /> 
        �ű���ʱʱ�䣺 
      <%=(Server.ScriptTimeout / 1000).ToString() %> ��<br /> 
       ϵͳ���ԣ� 
       <%=CultureInfo.InstalledUICulture.EnglishName %><br /> 
        .NET�汾�� 
   <%= Environment.Version.Major%>.<%=Environment.Version.Minor%>. 
  <%= Environment.Version.Build%>.<%= Environment.Version.Revision%> <br /> 
       IE�汾�� 
  <% Microsoft.Win32.RegistryKey key = Microsoft.Win32.Registry.LocalMachine.OpenSubKey(@"SOFTWARE\Microsoft\Internet Explorer\Version Vector"); %>     
      <%=key.GetValue("IE", "δ��⵽").ToString() %><br /> 
        ���������������У� 
      <%=((Environment.TickCount / 0x3e8) / 60).ToString() %> ����<br /> 
  
        CPU ������ 
      <%= Environment.GetEnvironmentVariable("NUMBER_OF_PROCESSORS").ToString()%> <br /> 
       CPU���ͣ� 
      <%=Environment.GetEnvironmentVariable("PROCESSOR_IDENTIFIER").ToString() %> <br /> 
       ASP.NET��վ�ڴ棺 
     <%=((Double)Process.GetCurrentProcess().WorkingSet64 / 1048576).ToString("N2") %> M<br /> 
      ASP.NET��ռCPU�� 
   <%= ((TimeSpan)Process.GetCurrentProcess().TotalProcessorTime).TotalSeconds.ToString("N0")%> %<br /> 
       ��ǰϵͳ�û����� 
     <%=Environment.UserName %><br /> 

</body> 
</html> 
