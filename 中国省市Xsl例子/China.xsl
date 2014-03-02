<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
<xsl:template match="/">
<xsl:comment>用来在xsl文件中加入注释，其功能与html注释相同，但只能出现在template模板之间。</xsl:comment>
<html>
	<head>
		<title>中国省市列表</title>
		<LINK href="China.css" type="text/css" rel="stylesheet"/>
		<script language="javascript">
			<xsl:comment>
				<![CDATA[
function buttons(str)
{
	alert(str);
}
function getover(src)
{
	src.style.background = "#ccffff";
}
function getout(src)
{
	src.style.background = "";
}
function XML_Load()
{	
	var xmldoc = new ActiveXObject("Microsoft.XMLDOM");
	xmldoc.async = "false";
	xmldoc.load("China1.xml");				
	var rowslength = xmldoc.documentElement.childNodes.length;				
	Province.options[0] = new Option("=请选择省=","0");
	City.options[0] = new Option("=请选择市=","00");
	
	for(var i=0;i<rowslength;i++)
	{
		Province.options[i+1] = new Option(xmldoc.documentElement.childNodes[i].childNodes[0].text,xmldoc.documentElement.childNodes[i].childNodes[0].text);
	}	
}
function getCity(str)
{
	City.innerText = "";
	City.options[0] = new Option("=请选择市=","00");
	if(str.value != 0)
	{
		var xmldoc = new ActiveXObject("Microsoft.XMLDOM");
		xmldoc.async = "false";
		xmldoc.load("China1.xml");

		var xmlstr = xmldoc.documentElement.selectSingleNode("//China[Province='"+ str.value +"']").childNodes[1].text;
		//alert(xmlstr);

		var xmls = xmlstr.split('|');

		for(var i=0;i<xmls.length;i++)
		{
			if(xmls[i] != "")
			{
				//document.form1.City.options[j] = new Option(xmls[i],xmls[i]);
				var oOption = document.createElement("OPTION");
				City.options.add(oOption);
				oOption.innerText = xmls[i];
				oOption.value = xmls[i];
				
			}						
		}	
	}
}
function buttons2()
{
	if(Province.value != "0" && City.value != "00")
	{
		alert(Province.value + " " +City.value);
	}
}
]]>//
			</xsl:comment>
		</script>
	</head>
	<body onload="XML_Load();" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<table cellSpacing="0" cellPadding="1" width="98%" border="1" align="center">
			<tr>
				<td colspan="5" align="center" class="title">中国省市列表</td>
			</tr>
			<xsl:for-each select="//China">
			<tr onmouseover="getover(this);" onmouseout="getout(this);">
				<td align="center" nowrap="" class="td3"><xsl:value-of select="@Id"/></td>
				<td align="center" nowrap="" class="td1"><xsl:value-of select="Province"/></td>
				<td class="td2"><xsl:value-of select="City"/></td>
				<td nowrap="" class="td2">
					<xsl:element name="a">
						<xsl:attribute name="href"><xsl:value-of select="Url"/></xsl:attribute>
						<xsl:attribute name="title">标题</xsl:attribute>
						<xsl:value-of select="Url"/>
					</xsl:element>
				</td>
				<td nowrap="" align="center">
					<xsl:element name="input">
						<xsl:attribute name="type">button</xsl:attribute>
						<xsl:attribute name="class">bt1</xsl:attribute>
						<xsl:attribute name="value">测试</xsl:attribute>
						<xsl:attribute name="onclick">buttons('<xsl:value-of select="Province"/>')</xsl:attribute>
					</xsl:element>
				</td>
			</tr>
			</xsl:for-each>
		</table><br/>
		<table cellSpacing="0" cellPadding="1" width="98%" border="1" align="center">
			<tr>
				<td align="center">
					<select name="Province" onchange="getCity(this);">							
					</select>
					<select name="City">
					</select>
					<xsl:element name="input">
						<xsl:attribute name="type">button</xsl:attribute>
						<xsl:attribute name="value">点击</xsl:attribute>
						<xsl:attribute name="class">bt1</xsl:attribute>
						<xsl:attribute name="onclick">buttons2();</xsl:attribute>
					</xsl:element>
				</td>
			</tr>
			<tr>
				<td align="center"><a href="javascript:close();">【关闭窗体】</a></td>
			</tr>
		</table>
		<br/>
		<table cellSpacing="0" cellPadding="0" width="98%" border="0">
			<tr>
				<td align="center">			
					<xsl:element name="iframe">
						<xsl:attribute name="name">xmlreply</xsl:attribute>
						<xsl:attribute name="marginwidth">0</xsl:attribute>
						<xsl:attribute name="marginheight">0</xsl:attribute>
						<xsl:attribute name="frameborder">0</xsl:attribute>
						<xsl:attribute name="scrolling">yes</xsl:attribute>
						<xsl:attribute name="width">750</xsl:attribute>						
						<xsl:attribute name="height">300</xsl:attribute>
						<xsl:attribute name="src">http://www.zz.ha.cn?Id=<xsl:value-of select="//China[@Id=1]/City"/></xsl:attribute>必须要放
					</xsl:element>
				</td>
			</tr>
			<tr>
				<td>abcdefghigh</td>
			</tr>
		</table>
	</body>
</html>
</xsl:template>
</xsl:stylesheet>