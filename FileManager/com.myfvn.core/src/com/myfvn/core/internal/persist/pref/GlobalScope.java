package com.myfvn.core.internal.persist.pref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.foreworld.java.exceptions.ServiceException;
import net.foreworld.java.models.MapResultModel;
import net.foreworld.java.services.IService;
import net.foreworld.java.services.ServiceFactory;
import net.foreworld.java.sysmanage.sysmanage_user_preference.Sysmanage_user_preference;
import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.events.UserAdapter;
import net.foreworld.utils.rcp.core.persist.events.UserEvent;

import com.myfvn.core.internal.persist.Preference;
import com.myfvn.core.persist.event.UserEventManager;
import com.myfvn.core.persist.pref.IPreferenceScope;
import com.myfvn.core.persist.reference.WebSiteReference;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class GlobalScope implements IPreferenceScope {
	final Logger _logger = Logger.getLogger(GlobalScope.class.getName());

	private UserAdapter _userAdapter = null;
	private final Map<String, String> _cache;
	private final IPreferenceScope _parent;

	public GlobalScope(IPreferenceScope $parent) {
		_parent = $parent;
		_cache = new HashMap<String, String>();
		registerListeners();
	}

	private void registerListeners() {
		this._userAdapter = new UserAdapter() {
			public void loginSuccess(UserEvent $event) {
				loadUserPreference();
			}
		};
		UserEventManager.getDefault().addUserEventListener(this._userAdapter);
	}

	@SuppressWarnings("unchecked")
	private void loadUserPreference() {
		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.SYSMANAGE_USER_PREFERENCESERVICE);
		IUser __user = new WebSiteReference().resolve().getLastLoginUser();
		Sysmanage_user_preference __sysmanage_user_preference = new Sysmanage_user_preference();
		__sysmanage_user_preference.setTab_sysmanage_user_uuid(__user.getUuid());

		List<MapResultModel> __list = null;

		try {
			__list = __service.select(__sysmanage_user_preference, null);
		} catch (ServiceException $ex) {
			_logger.warning($ex.getMessage());
		}

		for (MapResultModel ___model : __list) {
			Preference ____preference = this.createPreference(___model);
			____preference.setParent(__user);
			__user.addChild(____preference, null, false);
			_cache.put(___model.get("keyy").toString(), ___model.get("valuee").toString());
		}
	}

	private Preference createPreference(MapResultModel $model) {
		Preference __preference = new Preference();
		__preference.setId(Integer.parseInt($model.get("id").toString()));//$NON-NLS-1$
		__preference.setTab_sysmanage_user_id(Integer.parseInt($model.get("tab_sysmanage_user_id").toString()));//$NON-NLS-1$
		__preference.setTab_sysmanage_user_uuid($model.get("tab_sysmanage_user_uuid") == null ? "" : $model.get("tab_sysmanage_user_uuid").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__preference.setKeyy($model.get("keyy") == null ? "" : $model.get("keyy").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		__preference.setValuee($model.get("valuee") == null ? "" : $model.get("valuee").toString());//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		return __preference;
	}

	public void delete(String $key) {

	}

	public void flush() {

	}

	public boolean getBoolean(String $key) {
		return false;
	}

	public int getInteger(String $key) {
		synchronized (_cache) {

			String __cachedPref = _cache.get($key);

			if (__cachedPref != null && !"".equals(__cachedPref)) {
				return Integer.valueOf(__cachedPref);
			}

			int __parentValue = _parent.getInteger($key);

			return __parentValue;
		}
	}

	public int[] getIntegers(String $key) {
		return null;
	}

	public long getLong(String $key) {
		return 0;
	}

	public long[] getLongs(String $key) {
		return null;
	}

	public IPreferenceScope getParent() {
		return _parent;
	}

	public String getString(String $key) {
		return null;
	}

	public String[] getStrings(String $key) {
		return null;
	}

	public boolean hasKey(String $key) {
		return false;
	}

	public void putBoolean(String $key, boolean $value) {

	}

	public void putInteger(String $key, int $value) {
		_cache.put($key, String.valueOf($value));
	}

	public void putIntegers(String $key, int[] $values) {

	}

	public void putLong(String $key, long $value) {

	}

	public void putLongs(String $key, long[] $values) {

	}

	public void putString(String $key, String $value) {

	}

	public void putStrings(String $key, String[] $values) {

	}

	public void save() {
		IUser __user = new WebSiteReference().resolve().getLastLoginUser();

		List<Sysmanage_user_preference> __list = new ArrayList<Sysmanage_user_preference>();

		for (Map.Entry<String, String> ___map : _cache.entrySet()) {
			Sysmanage_user_preference ____sysmanage_user_preference = new Sysmanage_user_preference();
			____sysmanage_user_preference.setTab_sysmanage_user_id(__user.getId());
			____sysmanage_user_preference.setTab_sysmanage_user_uuid(__user.getUuid());
			____sysmanage_user_preference.setKeyy(___map.getKey());
			____sysmanage_user_preference.setValuee(___map.getValue());
			__list.add(____sysmanage_user_preference);
		}

		IService __service = ServiceFactory.getInstance().getService(ServiceFactory.SYSMANAGE_USER_PREFERENCESERVICE);

		Sysmanage_user_preference __sysmanage_user_preference = new Sysmanage_user_preference();
		__sysmanage_user_preference.setTab_sysmanage_user_id(__user.getId());
		try {
			__service.delete(__sysmanage_user_preference, null);
			__service.inserts(__list, null);
		} catch (ServiceException $ex) {
			_logger.warning($ex.getMessage());
		}

	}
}
