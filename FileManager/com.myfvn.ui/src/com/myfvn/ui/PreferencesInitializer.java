package com.myfvn.ui;

import com.myfvn.core.persist.pref.IPreferenceScope;
import com.myfvn.core.persist.pref.IPreferencesInitializer;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class PreferencesInitializer implements IPreferencesInitializer {

	public void initialize(IPreferenceScope $defaultScope) {

		/* Default Globals */
		initGlobalDefaults($defaultScope);

		/* Default Eclipse Globals */
		initGlobalEclipseDefaults($defaultScope);

		/* Default Clean Up */
		initCleanUpDefaults($defaultScope);

		/* Default Display Settings */
		initDisplayDefaults($defaultScope);

		/* Toolbar Item Settings */
		initToolbarDefaults($defaultScope);
	}

	private void initGlobalDefaults(IPreferenceScope $defaultScope) {

	}

	private void initGlobalEclipseDefaults(IPreferenceScope $defaultScope) {

	}

	private void initCleanUpDefaults(IPreferenceScope $defaultScope) {

	}

	private void initDisplayDefaults(IPreferenceScope $defaultScope) {

	}

	private void initToolbarDefaults(IPreferenceScope $defaultScope) {

	}

}
