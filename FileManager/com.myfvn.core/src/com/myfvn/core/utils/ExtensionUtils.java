package com.myfvn.core.utils;

import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ExtensionUtils {
	final static Logger _logger = Logger.getLogger(ExtensionUtils.class.getName());

	/** The FileManager Namespace for extension points */
	public static final String FileManager_NAMESPACE = "com.myfvn"; //$NON-NLS-1$

	/** The FileManager Namespace for extension points */
	public static final String FileManager_TESTS_NAMESPACE = "com.myfvn.core.tests"; //$NON-NLS-1$

	/* Attribute for executable extensions */
	private static final String EXECUTABLE_ATTRIBUTE = "class"; //$NON-NLS-1$

	public static Object loadSingletonExecutableExtension(String $extensionPoint) {
		return loadSingletonExecutableExtension($extensionPoint, null);
	}

	public static Object loadSingletonExecutableExtension(String $extensionPoint, Object $defaultExecutable) {
		IExtensionRegistry __reg = Platform.getExtensionRegistry();
		IConfigurationElement __elements[] = __reg.getConfigurationElementsFor($extensionPoint);

		/* More than one contribution - Choose 3d party over our own */
		if (__elements.length > 1) {
			for (IConfigurationElement ___element : __elements) {

				/* Let 3d-Party contributions override our contributions */
				if (!___element.getNamespaceIdentifier().contains(FileManager_NAMESPACE)) {
					try {
						return ___element.createExecutableExtension(EXECUTABLE_ATTRIBUTE);
					} catch (CoreException $ex) {
						_logger.warning($ex.getMessage());
					}
				}
			}
		}

		/**
		 * One Contribution or fallback if more than one Contrib matches
		 * com.myfvn
		 */
		else if (__elements.length == 1) {
			try {
				return __elements[0].createExecutableExtension(EXECUTABLE_ATTRIBUTE);
			} catch (CoreException $ex) {
				_logger.warning($ex.getMessage());
			}
		}

		/* Return default if provided */
		if ($defaultExecutable != null)
			return $defaultExecutable;

		/* Indicate missing extension with Exception */
		throw new IllegalStateException("Unable to load contributions for " + $extensionPoint); //$NON-NLS-1$
	}
}
