<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<xsl:comment>作者:黄鑫</xsl:comment>
<xsl:comment>QQ:3203317</xsl:comment>
<xsl:comment>Email:3203317@qq.com</xsl:comment>
<html>
	<head>
		<title>http://www.foreworld.net</title>
		<meta http-equiv="Content-type" content="text/html; charset=gb2312"/>
		<link href="" type="text/css" rel="stylesheet"/>
	</head>
	<body bgcolor="#F3F7F8" leftmargin="5" topmargin="5" marginwidth="5" marginheight="5">
		<center>
			<div id='list' class='tree'>
				<xsl:choose>
					<xsl:when test="//channel/title">
						<div id='channel'>
							<xsl:value-of select="//channel/title"/>
						</div>
					</xsl:when>
				</xsl:choose>
				<xsl:for-each select="//item">
					<xsl:sort select="position()" order="descending"/>
					<div id='block'>
						<div id='title'>
							·<xsl:element name="a">
								<xsl:attribute name="href"><xsl:value-of select="link"/></xsl:attribute>
								<xsl:value-of select="title"/>
							</xsl:element>
						</div>
						<div id='author'>
							<xsl:value-of select="author"/>&#160;
						</div>
						<div id='time'>
							<xsl:value-of select="pubDate"/>&#160;
						</div>
						<div id='description'>
							<xsl:value-of select="description"/>&#160;
						</div><p/>
						<div id='readall'>
							[<xsl:element name="a">
								<xsl:attribute name="href"><xsl:value-of select="link"/></xsl:attribute>
								<xsl:attribute name="target">_blank</xsl:attribute>
								阅读全文
							</xsl:element>]
						</div>
					</div>
				</xsl:for-each>
			</div>
		</center>
	</body>
</html>
</xsl:template>
</xsl:stylesheet>