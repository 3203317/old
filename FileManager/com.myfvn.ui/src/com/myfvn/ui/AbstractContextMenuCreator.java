package com.myfvn.ui;

import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class AbstractContextMenuCreator implements IMenuCreator {

	private Menu _menu;

	public abstract Menu createMenu(Control $parent);

	public void dispose() {
		if (_menu != null)
			FvnUI.safeDispose(_menu);
	}

	public Menu getMenu(Control $parent) {
		if (_menu != null)
			FvnUI.safeDispose(_menu);

		_menu = createMenu($parent);

		return _menu;
	}

	public Menu getMenu(Menu $parent) {
		return null;
	}

}
