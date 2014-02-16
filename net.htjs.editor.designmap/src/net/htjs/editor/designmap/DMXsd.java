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

/**
 * ���rss��д���࣬������b/s��c/s
 * 
 * @author ����
 * @Aug 19, 2008 11:52:15 AM
 */
public class DMXsd
{

  /**
   * ��ȡ�ĵ�
   * 
   * @param xmlPath �ļ�·��
   * @return
   */
  public static Document getDocumentByXmlPath(String xmlPath)
  {
    Document document = null;
    try
    {
      SAXReader saxReader = new SAXReader();
      document = saxReader.read(new File(xmlPath));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return document;
  }

  /**
   * ��ȡ�����ϵ�xml
   * 
   * @param xmlURL ��ַ
   * @return
   */
  public static Document getDocumentByURL(String xmlURL)
  {
    Document document = null;
    try
    {
      SAXReader saxReader = new SAXReader();
      document = saxReader.read(new URL(xmlURL));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return document;
  }

  /**
   * �����ļ�
   * 
   * @param document
   * @param filePath
   * @return
   */
  public static void createXmlFile(Document document, String xmlPath)
  {
    try
    {
      XMLWriter output = new XMLWriter(new FileOutputStream(xmlPath));
      output.write(document);
      output.flush();
      output.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * ɾ���ļ�
   * 
   * @param xmlPath
   * @return
   */
  public static boolean delXmlFile(String xmlPath)
  {
    return new File(xmlPath).delete();
  }

  /**
   * �����ļ�
   * 
   * @param document
   * @param xmlPath
   */
  public static void saveXmlFile(Document document, String xmlPath)
  {
    OutputFormat format = OutputFormat.createPrettyPrint();
    XMLWriter writer = null;
    try
    {
      writer = new XMLWriter(new FileOutputStream(xmlPath), format);
      writer.write(document);
      writer.flush();
      writer.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * ��ȡԪ��
   * 
   * @param document
   * @param xpath
   * @return
   */
  public static Element getElement(Document document, String xpath)
  {
    return (Element) document.selectSingleNode(xpath);
  }

  /**
   * ��ȡԪ���б�
   * 
   * @param document
   * @param xpath
   * @return
   */
  public static List getElements(Document document, String xpath)
  {
    return document.selectNodes(xpath);
  }

  /**
   * �����Ԫ��?
   * 
   * @param parentElement ��Ԫ��
   * @param elementName Ԫ����
   * @param attributeMap ����ͼ
   * @return ��Ԫ��
   */
  public static Element addElement(Element parentElement, String elementName,
      HashMap attributeMap)
  {
    Element element = parentElement.addElement(elementName);
    for (Iterator it = attributeMap.keySet().iterator(); it.hasNext();)
    {
      String key = (String) it.next();
      element.addAttribute(key, attributeMap.get(key).toString());
    }
    return element;
  }

  /**
   * �༭Ԫ��
   * 
   * @param element
   * @param attributeMap
   */
  public static void editElement(Element element, HashMap attributeMap)
  {
    for (Iterator it = attributeMap.keySet().iterator(); it.hasNext();)
    {
      String key = (String) it.next();
      element.attribute(key).setText(attributeMap.get(key).toString());
    }
  }

  /**
   * ɾ��Ԫ��
   * 
   * @param element
   */
  public static void delElement(Element element)
  {
    element.getParent().remove(element);
  }

  /**
   * ���Ԫ���Ƿ����
   * 
   * @param document
   * @param xpath
   * @return ����
   */
  public static boolean checkElementIsExist(Document document, String xpath)
  {
    return document.selectSingleNode(xpath) != null;
  }

  /**
   * ��ȡ�б�
   * 
   * @param document
   * @param xpath
   * @return
   */
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

  public static List getAttribute(Document document, String xpath)
  {
    String[] arr = xpath.split("/");

    Element ele = getElement(document, "//xs:element[@name='" + arr[1]
        + "']/xs:complexType/xs:sequence/xs:element[@name='" + arr[2] + "']");

    if (arr.length == 3)
    {
      return getElements(document, "//xs:complexType[@name='"
          + ele.attributeValue("type") + "']/xs:attribute");
    }
    else
    {
      return getIteratorAttr(document, xpath, ele.attributeValue("type"), 3);
    }
  }

  public static List getIteratorAttr(Document document, String xpath,
      String aaaa, int j)
  {
    String[] arr = xpath.split("/");

    Element ele = getElement(document, "//xs:complexType[@name='" + aaaa
        + "']/xs:sequence/xs:element[@name='" + arr[j] + "']");
    if ((arr.length - 1) == j)
    {
      return getElements(document, "//xs:complexType[@name='"
          + ele.attributeValue("type") + "']/xs:attribute");
    }
    return getIteratorAttr(document, xpath, ele.attributeValue("type"), j + 1);
  }
  

	/**
	 * 获取xsd的根列表
	 * @return
	 */
	public static final List getXSDRootElement(){
		return Activator.getXSD().selectNodes("/xs:schema/xs:element[@name='designMap']/xs:complexType/xs:sequence/xs:element");
	}
}
