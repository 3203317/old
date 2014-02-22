package net.foreworld.rss2.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.foreworld.rss2.Activator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 针对rss编写的类，适用于b/s和c/s
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 19, 2008 11:52:15 AM
 */
public class RssXml2 {

	/**
	 * 获取文档
	 * @param xmlPath 文件路径
	 * @return
	 */
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
    
    /**
     * 获取网络上的xml
     * @param xmlURL 地址
     * @return
     */
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
    
    /**
     * 创建文件
     * @param document
     * @param filePath
     * @return
     */
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
	
	/**
	 * 删除文件
	 * @param xmlPath
	 * @return
	 */
	public static boolean delXmlFile(String xmlName){
		return new File(Activator.getDefault().getStateLocation().append(xmlName).toString()).delete();
	}
	
	/**
	 * 保存文件
	 * @param document
	 * @param xmlPath
	 */
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
	
	/**
	 * 获取元素
	 * @param document
	 * @param xpath
	 * @return
	 */
	public static Element getElement(Document document,String xpath){
		return (Element)document.selectSingleNode(xpath);
	}
	
	/**
	 * 获取元素列表
	 * @param document
	 * @param xpath
	 * @return
	 */
	public static List getElements(Document document,String xpath){
		return document.selectNodes(xpath);
	}
	
	/**
	 * 添加新元素
	 * @param parentElement 父元素
	 * @param elementName 元素名
	 * @param attributeMap 属性图
	 * @return 新元素
	 */
	public static Element addElement(Element parentElement,String elementName,HashMap attributeMap){
		Element element = parentElement.addElement(elementName);
		for(Iterator it = attributeMap.keySet().iterator();it.hasNext();){
			String key = (String)it.next();
			element.addAttribute(key, attributeMap.get(key).toString());
		}
		return element;
	}
	
	/**
	 * 编辑元素
	 * @param element
	 * @param attributeMap
	 */
	public static void editElement(Element element,HashMap attributeMap){
		for(Iterator it = attributeMap.keySet().iterator();it.hasNext();){
			String key = (String)it.next();
			element.attribute(key).setText(attributeMap.get(key).toString());
		}
	}
	
	/**
	 * 删除元素
	 * @param element
	 */
	public static void delElement(Element element){
		element.getParent().remove(element);
	}
	
	/**
	 * 检查元素是否存在
	 * @param document
	 * @param xpath
	 * @return 真或假
	 */
	public static boolean checkElementIsExist(Document document,String xpath){
		return document.selectSingleNode(xpath) != null;
	}
	
	/**
	 * 获取所要操作的频道列表
	 * @param document
	 * @param outline
	 * @return
	 */
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
}
