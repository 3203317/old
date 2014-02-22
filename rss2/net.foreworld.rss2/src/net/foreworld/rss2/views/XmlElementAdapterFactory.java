package net.foreworld.rss2.views;

import org.dom4j.Element;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Oct 23, 2008 3:18:53 PM
 */
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
