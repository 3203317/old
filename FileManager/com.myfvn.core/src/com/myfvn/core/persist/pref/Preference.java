package com.myfvn.core.persist.pref;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public enum Preference {

	/** FileNavigator Explorer */
	FN_FILTER_TYPE("com.myfvn.ui.views.explorer.FilterType", IPreferenceType.INTEGER), //$NON-NLS-1$
	/** FileNavigator Explorer Index */
	FN_MAILBOX_INDEX("com.myfvn.ui.views.explorer.MailboxIndex", IPreferenceType.INTEGER), //$NON-NLS-1$
	/** */
	MENU_MAILS_FILTER("com.myfvn.ui.menus.MailsFilter", IPreferenceType.INTEGER), //$NON-NLS-1$

	/***************************************************************************
	 * Eclipse Preferences Follow *
	 **************************************************************************/

	/** Global Eclipse: Keybindings */
	ECLIPSE_KEYS("instance/org.eclipse.ui.workbench/org.eclipse.ui.commands", IPreferenceType.STRING, IPreferenceScope.Kind.ECLIPSE); //$NON-NLS-1$

	private String _id;
	private IPreferenceType _type;
	private IPreferenceScope.Kind _kind;

	Preference(String $id, IPreferenceType $type) {
		this($id, $type, IPreferenceScope.Kind.GLOBAL);
	}

	Preference(String $id, IPreferenceType $type, IPreferenceScope.Kind $kind) {
		_id = $id;
		_type = $type;
		_kind = $kind;
	}

	public String getId() {
		return _id;
	}

	public IPreferenceType getType() {
		return _type;
	}

	public IPreferenceScope.Kind getKind() {
		return _kind;
	}
}
