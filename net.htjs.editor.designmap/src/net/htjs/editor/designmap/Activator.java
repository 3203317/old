package net.htjs.editor.designmap;

import java.net.URL;

import net.htjs.editor.designmap.views.XmlElementAdapterFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.htjs.editor.designmap";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		//
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		Platform.getAdapterManager().registerAdapters(new XmlElementAdapterFactory(), Element.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * 获取xsd
	 * @return
	 */
	public static final Document getXSD(){
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		if(!BundleUtility.isReady(bundle)){
			
	    }

	    // look for the image (this will check both the plugin and fragment folders
	    URL fullPathString = BundleUtility.find(bundle, "/designMap-2.0.xsd");

	    Document xsdDoc = null;
	    try{
	    	SAXReader saxReader = new SAXReader();
	    	xsdDoc = saxReader.read(fullPathString);
	    }
	    catch(Exception ex){
	    	ex.printStackTrace();
	    }
		return xsdDoc;
	}
}
