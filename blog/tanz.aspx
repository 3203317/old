<%@ Page Language="C#" AutoEventWireup="true" Trace="true" %> 
<%@ Import Namespace="System.Diagnostics" %> 
<%@ Import Namespace="System.Globalization" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" > 
<head> 
   <title>ASP.NET2.0探针</title> 
</head> 
<body> 
     计算机名： 
   http://<%=HttpContext.Current.Request.Url.Host%> <%=HttpContext.Current.Request.ApplicationPath %><br /> 
    IP地址： 
  <%= Request.ServerVariables["LOCAl_ADDR"]   %><br /> 
      域名： 
   <%= Request.ServerVariables["SERVER_NAME"]%><br /> 
     端口： 
    <%=Request.ServerVariables["Server_Port"].ToString() %><br /> 
     本文件所在路径： 
     <%= Request.PhysicalApplicationPath %><br /> 
        操作系统： 
      <%= Environment.OSVersion.ToString()%> <br /> 
       操作系统所在文件夹： 
      <%=Environment.SystemDirectory.ToString() %> <br /> 
        脚本超时时间： 
      <%=(Server.ScriptTimeout / 1000).ToString() %> 秒<br /> 
       系统语言： 
       <%=CultureInfo.InstalledUICulture.EnglishName %><br /> 
        .NET版本： 
   <%= Environment.Version.Major%>.<%=Environment.Version.Minor%>. 
  <%= Environment.Version.Build%>.<%= Environment.Version.Revision%> <br /> 
       IE版本： 
  <% Microsoft.Win32.RegistryKey key = Microsoft.Win32.Registry.LocalMachine.OpenSubKey(@"SOFTWARE\Microsoft\Internet Explorer\Version Vector"); %>     
      <%=key.GetValue("IE", "未检测到").ToString() %><br /> 
        启动到现在已运行： 
      <%=((Environment.TickCount / 0x3e8) / 60).ToString() %> 分钟<br /> 
  
        CPU 数量： 
      <%= Environment.GetEnvironmentVariable("NUMBER_OF_PROCESSORS").ToString()%> <br /> 
       CPU类型： 
      <%=Environment.GetEnvironmentVariable("PROCESSOR_IDENTIFIER").ToString() %> <br /> 
       ASP.NET所站内存： 
     <%=((Double)Process.GetCurrentProcess().WorkingSet64 / 1048576).ToString("N2") %> M<br /> 
      ASP.NET所占CPU： 
   <%= ((TimeSpan)Process.GetCurrentProcess().TotalProcessorTime).TotalSeconds.ToString("N0")%> %<br /> 
       当前系统用户名： 
     <%=Environment.UserName %><br /> 

</body> 
</html> 
