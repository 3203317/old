package net.htjs.editor.designmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class RssXml2 { 

    public static Document getDocumentByXmlName(String xmlName){   
        Document document = null;   
        try {   
            SAXReader saxReader = new SAXReader();   
            document = saxReader.read(Activator.getDefault().getStateLocation().append(xmlName).toFile());
        } catch (Exception ex) {   
            ex.printStackTrace();   
        }   
        return document;   
    } 
    
    public static Document getDocumentByURL(String xmlURL){
        Document document = null;   
        try {   
            SAXReader saxReader = new SAXReader();   
            document = saxReader.read(new URL(xmlURL));
        } catch (Exception ex) {   
            ex.printStackTrace();   
        }   
        return document;
    }
    
	public static void createXmlFile(Document document,String xmlName){
		try {
			XMLWriter output = new XMLWriter(new FileOutputStream(Activator.getDefault().getStateLocation().append(xmlName).toString()));
			output.write(document);
			output.flush();
			output.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean delXmlFile(String xmlName){
	  
		return new File(Activator.getDefault().getStateLocation().append(xmlName).toString()).delete();
	}
	
	public static void saveXmlFile(Document document,String xmlName){
		OutputFormat format = OutputFormat.createPrettyPrint();   
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileOutputStream(Activator.getDefault().getStateLocation().append(xmlName).toString()), format);
			writer.write(document);   
			writer.flush();
			writer.close();  
		} 
		catch(Exception e){
			e.printStackTrace();
		}  
	}
	
	public static Element getElement(Document document,String xpath){
		return (Element)document.selectSingleNode(xpath);
	}
	
	public static List getElements(Document document,String xpath){
		return document.selectNodes(xpath);
	}
	
	public static Element addElement(Element parentElement,String elementName,HashMap attributeMap){
		Element element = parentElement.addElement(elementName);
		for(Iterator it = attributeMap.keySet().iterator();it.hasNext();){
			String key = (String)it.next();
			element.addAttribute(key, attributeMap.get(key).toString());
		}
		return element;
	}
	
	public static void editElement(Element element,HashMap attributeMap){
		for(Iterator it = attributeMap.keySet().iterator();it.hasNext();){
			String key = (String)it.next();
			element.attribute(key).setText(attributeMap.get(key).toString());
		}
	}
	
	public static void delElement(Element element){
		element.getParent().remove(element);
	}
	
	public static boolean checkElementIsExist(Document document,String xpath){
		return document.selectSingleNode(xpath) != null;
	}
	
	public static List getRssChannelList(Document document,Object element){
		if(element == null){
			return getElements(document, "//outline[@type='rss']");
		}
		Element outline = (Element)element;
		if(outline.attribute("type") == null){
			return getElements(document, outline.getPath() +"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']");
		}
		return getElements(document, "//outline[@id='"+ outline.attributeValue("id") +"']");
	}
	
	  public static List getIteratorMenu(Document document, String xpath,
		      String aaaa, int j)
		  {
		    String[] arr = xpath.split("/");

		    Element ele = getElement(document, "//xs:complexType[@name='" + aaaa
		        + "']/xs:sequence/xs:element[@name='" + arr[j] + "']");
		    if ((arr.length - 1) == j)
		    {
		      return getElements(document, "//xs:complexType[@name='"
		          + ele.attributeValue("type") + "']/xs:sequence/xs:element");
		    }
		    return getIteratorMenu(document, xpath, ele.attributeValue("type"), j + 1);
		  }
	
	  public static List getNewMenu(Document document, String xpath)
	  {
	    String[] arr = xpath.split("/");

	    Element ele = getElement(document, "//xs:element[@name='" + arr[1]
	        + "']/xs:complexType/xs:sequence/xs:element[@name='" + arr[2] + "']");

	    if (arr.length == 3)
	    {
	      return getElements(document, "//xs:complexType[@name='"
	          + ele.attributeValue("type") + "']/xs:sequence/xs:element");
	    }
	    else
	    {
	      return getIteratorMenu(document, xpath, ele.attributeValue("type"), 3);
	    }
	  }
}
