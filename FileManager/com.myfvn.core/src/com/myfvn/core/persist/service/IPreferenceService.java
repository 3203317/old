package com.myfvn.core.persist.service;

import net.foreworld.utils.rcp.core.persist.IEntity;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;

import com.myfvn.core.persist.pref.IPreferenceScope;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IPreferenceService {

	/**
	 * The default scope can be used to intialize default preferences. It is the
	 * most-outer Scope with no parent scope at all. None of the values stored
	 * in the default scope is persisted.
	 * 
	 * @return The Default Scope for Preferences.
	 */
	IPreferenceScope getDefaultScope();

	/**
	 * The global scope stores global preferences. Most entity-scopes will be
	 * initialized with the values of the global scope.
	 * 
	 * @return The Global Scope for Preferences.
	 */
	IPreferenceScope getGlobalScope();

	/**
	 * The eclipse scope can be used to retrieve preferences that are stored in
	 * the Eclipse platform via {@link IEclipsePreferences} and
	 * {@link IPreferencesService}. It should only be used if the preference is
	 * interpreted by other Eclipse plugins. In any other case, use
	 * {@link #getGlobalScope()}.
	 * 
	 * @return The Eclipse Scope for Preferences.
	 */
	IPreferenceScope getEclipseScope();

	/**
	 * The entity scope stores preferences in the given entity itself.
	 * 
	 * @param $entity
	 *            The Entity to be used for the Scope.
	 * @return The Entity Scope for Preferences as defined by the given Entity.
	 */
	IPreferenceScope getEntityScope(IEntity $entity);
}
