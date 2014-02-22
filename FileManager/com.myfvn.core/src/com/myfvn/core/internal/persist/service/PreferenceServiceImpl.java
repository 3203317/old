package com.myfvn.core.internal.persist.service;

import java.util.logging.Logger;

import net.foreworld.utils.rcp.core.internal.persist.service.DatabaseEvent;
import net.foreworld.utils.rcp.core.internal.persist.service.IDatabaseListener;
import net.foreworld.utils.rcp.core.persist.IEntity;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.myfvn.core.internal.persist.pref.DefaultScope;
import com.myfvn.core.internal.persist.pref.EclipseScope;
import com.myfvn.core.internal.persist.pref.EntityScope;
import com.myfvn.core.internal.persist.pref.GlobalScope;
import com.myfvn.core.persist.pref.IPreferenceScope;
import com.myfvn.core.persist.pref.IPreferencesInitializer;
import com.myfvn.core.persist.service.IPreferenceService;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class PreferenceServiceImpl implements IPreferenceService {
	final Logger _logger = Logger.getLogger(PreferenceServiceImpl.class.getName());

	/* Extension Point: Preferences Initializer */
	private static final String PREFERENCES_INITIALIZER_EXTENSION_POINT = "com.myfvn.core.PreferencesInitializer"; //$NON-NLS-1$

	/* Scoped Preferences */
	private final IPreferenceScope _defaultScope;
	private final IPreferenceScope _globalScope;
	private final IPreferenceScope _eclipseScope;

	public PreferenceServiceImpl() {
		_defaultScope = new DefaultScope();
		_globalScope = new GlobalScope(_defaultScope);
		_eclipseScope = new EclipseScope(_defaultScope);
		initScopedPreferences();
		registerListeners();
	}

	private void initScopedPreferences() {
		/* Pass Service through Initializers */
		IExtensionRegistry __reg = Platform.getExtensionRegistry();
		IConfigurationElement __elements[] = __reg.getConfigurationElementsFor(PREFERENCES_INITIALIZER_EXTENSION_POINT);
		for (IConfigurationElement ___element : __elements) {
			try {
				IPreferencesInitializer ____initializer = (IPreferencesInitializer) ___element.createExecutableExtension("class"); //$NON-NLS-1$
				____initializer.initialize(_defaultScope);
			} catch (CoreException $ex) {
				_logger.warning($ex.getMessage());
			}
		}
	}

	public IPreferenceScope getDefaultScope() {
		return _defaultScope;
	}

	public IPreferenceScope getEclipseScope() {
		return _eclipseScope;
	}

	public IPreferenceScope getEntityScope(IEntity $entity) {
		return new EntityScope($entity, _globalScope);
	}

	public IPreferenceScope getGlobalScope() {
		return _globalScope;
	}

	private void registerListeners() {
		DBManager.getDefault().addDatabaseListener(new IDatabaseListener() {
			public void databaseClosed(DatabaseEvent $event) {
				savePreference();
			}

			public void databaseOpened(DatabaseEvent $event) {

			}
		});
	}

	private void savePreference() {
		_globalScope.save();
	}

}
