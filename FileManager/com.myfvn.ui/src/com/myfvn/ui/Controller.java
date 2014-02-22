package com.myfvn.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Controller {

	private List<ShareProvider> _shareProviders = new ArrayList<ShareProvider>();
	/* Share News Provider Extension Point */
	private static final String SHARE_PROVIDER_EXTENSION_POINT = "com.myfvn.ui.ShareProvider"; //$NON-NLS-1$
	/* The Singleton Instance */
	private static Controller _instance;

	private Controller() {

	}

	/**
	 * @return The Singleton Instance.
	 */
	public static Controller getDefault() {
		if (_instance == null)
			_instance = new Controller();
		return _instance;
	}

	public void startup() {
		/* Load Contributed Mails Share Providers */
		loadShareProviders();
	}

	private void loadShareProviders() {
		IExtensionRegistry __reg = Platform.getExtensionRegistry();
		IConfigurationElement __elements[] = __reg.getConfigurationElementsFor(SHARE_PROVIDER_EXTENSION_POINT);

		/* For each contributed property keyword feed */
		for (int ___i = 0, ___j = __elements.length; ___i < ___j; ___i++) {
			IConfigurationElement ____element = __elements[___i];

			String ____id = ____element.getAttribute("id"); //$NON-NLS-1$
			String ____name = ____element.getAttribute("name"); //$NON-NLS-1$
			String ____iconPath = ____element.getAttribute("icon"); //$NON-NLS-1$
			String ____url = ____element.getAttribute("url"); //$NON-NLS-1$
			String ____maxTitleLength = ____element.getAttribute("maxTitleLength"); //$NON-NLS-1$
			String ____enabled = ____element.getAttribute("enabled"); //$NON-NLS-1$

			boolean ____isEnabled = (____enabled != null && Boolean.parseBoolean(____enabled));
			_shareProviders.add(new ShareProvider(____id, ____element.getNamespaceIdentifier(), ___i, ____name, ____iconPath, ____url, ____maxTitleLength, ____isEnabled));
		}
	}

	public List<ShareProvider> getShareProviders() {
		return _shareProviders;
	}
}
