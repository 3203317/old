 <%
'==================================
'  底部页面
'    更新时间: 2005-8-25
'==================================

%>
 <!--底部-->
  <div id="foot">
    <p>Powered By <a href="http://www.pjhome.net" target="_blank"><strong>PJBlog3 v<%=blog_version%></strong></a> CopyRight 2005 - 2008, <strong><%=SiteName%></strong> <a href="http://validator.w3.org/check/referer" target="_blank">xhtml</a> | <a href="http://jigsaw.w3.org/css-validator/validator-uri.html">css</a></p>
    <p style="font-size:11px;">Processed in <b><%=FormatNumber(Timer()-StartTime,6,-1)%></b> second(s) , <b><%=SQLQueryNums%></b> queries<%=SkinInfo%>
    <br/><a href="http://www.miibeian.gov.cn" style="font-size:12px"><b><%=blogabout%></b></a>
    </p>
   </div>
 </div>
<iframe name="visitorframe" width="100%" height="0" noresize frameborder=0 framespacing=0 marginheight=0 marginwidth=0></iframe>
<FORM id="visitor" name="visitor" METHOD=POST ACTION="ext/visitor/insert.asp" target="visitorframe">
	<INPUT TYPE="hidden" id="param" NAME="param" value='{"screenResolutionX":800,"screenResolutionY":600}'>
</FORM>
<script type="text/javascript">initAccessKey()  //转换AccessKey For IE
//alert(document.getElementById("visitor"));
//alert(document.getElementById("param").value);
document.getElementById("param").value = '{"screenResolutionX":'+window.screen.width+',"screenResolutionY":'+window.screen.height+'}';
document.getElementById("visitor").submit();

//请求FWLookIdiomsAction 更新标题头获取成语
var ajax_lookIdioms = new AJAXRequest();
ajax_lookIdioms.get("ext/idioms/getData.asp?f=&type=3", function(obj) {
	var _result = eval("("+ obj.responseText +")");
	document.title += " - "+ _result.rows[0].data[1] +" - "+ _result.rows[0].data[3];
});
</script>
</body>
</html>
<%
Session.CodePage = 936
Session(CookieName&"_LastDo") = "" '最近的一次数据库操作
'Session(CookieName&"_LastDo")返回值说明
'DelComment 删除评论
'AddComment 添加评论
'EditUser 用户编辑个人资料
'RegisterUser 新用户注册
'AddArticle 添加新日志
'EditArticle 编辑日志
'DelArticle 删除日志
'DelMessage 删除留言 (需要留言本插件支持)
'AddMessage 添加留言 (需要留言本插件支持)
Call CloseDB
%>