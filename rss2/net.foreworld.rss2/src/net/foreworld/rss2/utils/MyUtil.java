package net.foreworld.rss2.utils;

import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 29, 2008 10:35:32 AM
 */
public class MyUtil {

	/**
	 * xml与xsl组合生成html
	 * @param xml
	 * @param xsl
	 * @return
	 * @throws Exception
	 */
	public static String getHtmlByXmlXsl(String xml,String xsl) throws Exception{ 
		Reader xslreader = new StringReader(xsl); 
		TransformerFactory factory = TransformerFactory.newInstance(); 
		Transformer transformer = factory.newTransformer(new StreamSource(xslreader)); 
		Properties properties = transformer.getOutputProperties(); 
		properties.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); 
		properties.setProperty(OutputKeys.ENCODING, "utf-8"); 
		properties.setProperty(OutputKeys.METHOD,"html"); 
		properties.setProperty(OutputKeys.VERSION,"4.0"); 
	
		transformer.setOutputProperties(properties); 
	
		Reader xmlReader = new StringReader(xml); 
		SAXReader reader = new SAXReader(); 
		Document doc = reader.read(xmlReader); 
	
		DocumentSource source = new DocumentSource(doc); 
		DocumentResult result = new DocumentResult(); 
		transformer.transform(source, result); 
		return result.getDocument().asXML(); 
	}
}
