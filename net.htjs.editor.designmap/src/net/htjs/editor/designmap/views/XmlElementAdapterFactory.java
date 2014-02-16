package net.htjs.editor.designmap.views;

import org.dom4j.Element;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;


public class XmlElementAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object arg0, Class arg1) {
		if(arg1 == IPropertySource.class){
			return arg0 instanceof Element ? new XmlElementPropertySource((Element)arg0) : null;
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[]{IPropertySource.class};
	}

}
