package abc;

import java.io.File;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class TestAbc {

	@Test
	public void xml2json() {
		XMLSerializer xmlSerializer = new XMLSerializer();
		// String[] sa = { "a", "b", "c" };
		// JSONArray jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer
		// .write(JSONArray.fromObject(sa)));
		// System.out.println(jsonArray.toString());

		SAXReader reader = new SAXReader();
		File file = new File("D:\\GitHub\\study\\db\\sql\\f_article.xml");
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();

		// System.out.println(root.asXML());

		// String xml =
		// "<o number='1'> first <string>json</string> <array> <e>1</e> <e>true</e> </array> </o>";
		String xml = root.asXML();

		JSON json = xmlSerializer.read(xml);
		System.out.println(json.toString());
	}
}
