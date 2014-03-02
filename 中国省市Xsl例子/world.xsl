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
				
var xmldoc = new ActiveXObject("Microsoft.XMLDOM");
xmldoc.async = "false";
xmldoc.load("world1.xml");
var objCountry = xmldoc.documentElement.selectSingleNode("//country[@name='中国']");
//获取中国的省的总数
var chinaProvinceNum = objCountry.childNodes.length;	

function getCity(str)
{
	City.innerText = "";
	City.options[0] = new Option("=请选择市=","");

	if(str.value == "")
	{		
		return;
	}

	//获取城市的数量
	var cityNum = objCountry.selectSingleNode("//province[@name='"+str.value+"']").childNodes.length;
	//获取省的对象
	var objProvince = objCountry.selectSingleNode("//province[@name='"+str.value+"']");
	
	for(var i=0;i<cityNum;i++)
	{
		var key = objProvince.childNodes[i].getAttribute("name");
		var value = key;
		City.options[i+1] = new Option(key,value);
	}


}
function XML_Load()
{	
	getProvince();
}
function getProvince()
{
	
	Province.options[0] = new Option("-=请选择省=-","");
	City.options[0] = new Option("-=请选择市=-","");

	for(var i=0;i<chinaProvinceNum;i++)
	{
		//获取省的名字
		var key = objCountry.childNodes[i].getAttribute("name");
		var value = key;
		Province.options[i+1] = new Option(key,value);
	}
}
]]>//
			</xsl:comment>
		</script>
	</head>
	<body onload="XML_Load();" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<table cellSpacing="0" cellPadding="1" width="98%" border="1" align="center">
			<tr>
				<td align="center">中国省市列表</td>
			</tr>
		</table>
		<table cellSpacing="0" cellPadding="1" width="98%" border="1" align="center">
			<tr>
				<td align="center">
					<select name="Province" onchange="getCity(this);">							
					</select>
					<select name="City">
					</select>
				</td>
			</tr>
			<tr>
				<td align="center"><a href="javascript:close();">【关闭窗体】</a></td>
			</tr>
		</table>
	</body>
</html>
</xsl:template>
</xsl:stylesheet>