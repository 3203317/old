package com.myfvn.core.persist.pref;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IPreferencesInitializer {

	/**
	 * Initializes the given scope of default preferences with values.
	 * 
	 * @param $defaultScope
	 *            The default-scope containing initial preferences. These serve
	 *            as the default-preferences for all other Scopes.
	 */
	void initialize(IPreferenceScope $defaultScope);

}
