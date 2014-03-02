import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class Test {

	private String _xml = "<?xml version='1.0' encoding='UTF-8' ?><Report><WorkSheet></WorkSheet><DataSources></DataSources></Report>";

	private Document getXMLObj() {
		Document doc = null;

		try {
			doc = DocumentHelper.parseText(_xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Test t = new Test();
		Document doc = t.getXMLObj();

		Element ele = doc.getRootElement().element("DataSources");
		Element datasource = ele.addElement("DataSource");
		datasource.addAttribute("type", "4");

		Element data = datasource.addElement("Data");

		Element id = data.addElement("ID");
		id.setText("ds201_201");

		Element version = data.addElement("Version");
		version.setText("2");

		Element type = data.addElement("Type");
		type.setText("4");

		Element typeMeaning = data.addElement("TypeMeaning");
		typeMeaning.setText("JSON");

		Element memo = data.addElement("Memo");
		memo.setText("memo");

		Element xML_RecordAble_Nodes = data.addElement("XML_RecordAble_Nodes");
		Element node = xML_RecordAble_Nodes.addElement("Node");

		Element node_name = node.addElement("name");
		node_name.setText("items");

		Element columns = data.addElement("Columns");

		for (int i = 0, j = 3; i < j; i++) {

			Element column = columns.addElement("Column");
			Element name = column.addElement("name");
			name.setText("items\\name" + i);

			Element text = column.addElement("text");
			text.setText("name" + i);

			Element __type = column.addElement("type");
			__type.setText("name" + i);

			Element visible = column.addElement("visible");
			visible.setText("name" + i);

			Element sequence = column.addElement("sequence");
			sequence.setText("0");
		}

		// System.out.println(doc.asXML());

		Element __ds = doc.getRootElement().element("DataSources");
		System.out.println(__ds.asXML());

		Node node2 = doc.getRootElement().selectSingleNode("//Data[ID='ds201_201']/Columns/Column[name='items\\name1']");

		System.out.println(node2.asXML());

//		e.selectSingleNode("text").setText("aaaaaa");
//		e.selectSingleNode("visible").setText("false");
//		System.out.println(e.asXML());

//
//		Node e = node2.selectSingleNode("//Column[name='name1']");
//		e.selectSingleNode("text").setText("12121");
//
//		//
//		// System.out.println(e);
//
//		// System.out.println(node2.asXML());
//		// node2.getParent().getParent().remove(node2.getParent());
//		// System.out.println("--");
//		System.out.println(doc.asXML());

	}

}
