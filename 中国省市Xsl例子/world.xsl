<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
<xsl:template match="/">
<xsl:comment>������xsl�ļ��м���ע�ͣ��书����htmlע����ͬ����ֻ�ܳ�����templateģ��֮�䡣</xsl:comment>
<html>
	<head>
		<title>�й�ʡ���б�</title>
		<LINK href="China.css" type="text/css" rel="stylesheet"/>
		<script language="javascript">
			<xsl:comment>
				<![CDATA[
				
var xmldoc = new ActiveXObject("Microsoft.XMLDOM");
xmldoc.async = "false";
xmldoc.load("world1.xml");
var objCountry = xmldoc.documentElement.selectSingleNode("//country[@name='�й�']");
//��ȡ�й���ʡ������
var chinaProvinceNum = objCountry.childNodes.length;	

function getCity(str)
{
	City.innerText = "";
	City.options[0] = new Option("=��ѡ����=","");

	if(str.value == "")
	{		
		return;
	}

	//��ȡ���е�����
	var cityNum = objCountry.selectSingleNode("//province[@name='"+str.value+"']").childNodes.length;
	//��ȡʡ�Ķ���
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
	
	Province.options[0] = new Option("-=��ѡ��ʡ=-","");
	City.options[0] = new Option("-=��ѡ����=-","");

	for(var i=0;i<chinaProvinceNum;i++)
	{
		//��ȡʡ������
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
				<td align="center">�й�ʡ���б�</td>
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
				<td align="center"><a href="javascript:close();">���رմ��塿</a></td>
			</tr>
		</table>
	</body>
</html>
</xsl:template>
</xsl:stylesheet>