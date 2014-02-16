package net.htjs.editor.designmap.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;


public class XmlElementPropertySource implements IPropertySource {
	
	private Element outline;
	
	public XmlElementPropertySource(Element outline){
		this.outline = outline;
	}

	public Object getEditableValue() {
		return this.getClass().getName();
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		List list = new ArrayList();		
		for(Iterator it = this.outline.attributeIterator();it.hasNext();){
			Attribute attr = (Attribute)it.next();
			list.add(new PropertyDescriptor(attr.getName(),attr.getName()));
		}
		for(Iterator it = this.outline.elementIterator();it.hasNext();){
			Element ele = (Element)it.next();
			list.add(new PropertyDescriptor(ele.getName(),ele.getName()));
		}
		return (IPropertyDescriptor[])list.toArray(new IPropertyDescriptor[list.size()]);
	}

	public Object getPropertyValue(Object arg0) {
		if(this.outline.attribute(arg0.toString()) != null){
			return this.outline.attribute(arg0.toString()).getValue();
		}
		return this.outline.element(arg0.toString()).getText();
	}

	public boolean isPropertySet(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void resetPropertyValue(Object arg0) {
		// TODO Auto-generated method stub

	}

	public void setPropertyValue(Object arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
