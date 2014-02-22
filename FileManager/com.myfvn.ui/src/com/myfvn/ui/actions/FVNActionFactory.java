package com.myfvn.ui.actions;

import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.actions.BaseActionFactory;
import net.foreworld.utils.rcp.actions.MinimizeShellAction;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;

import com.myfvn.ui.Activator;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class FVNActionFactory extends BaseActionFactory {

	public FVNActionFactory(String $actionId) {
		super($actionId);
	}

	public static final ActionFactory OPEN_NEW_WINDOW = new ActionFactory(ActionFactory.OPEN_NEW_WINDOW.getId()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			IWorkbenchAction __action = ActionFactory.OPEN_NEW_WINDOW.create($window);
			if (__action != null) {
				__action.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/elcl16/newwindow.gif"));
			}
			return __action;
		}
	};

	public static final ActionFactory PREFERENCES = new ActionFactory(ActionFactory.PREFERENCES.getId()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			IWorkbenchAction __action = ActionFactory.PREFERENCES.create($window);
			__action.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/elcl16/preferences.gif"));
			return __action;
		}
	};

	public static final ActionFactory OPEN_HOMEPAGE = new ActionFactory(OpenHomepageAction.class.getName()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			if ($window == null) {
				throw new IllegalArgumentException();
			}
			IWorkbenchAction __action = new OpenHomepageAction($window);
			if (__action != null) {
				__action.setId(this.getId());
				__action.setText(Messages.FVNActionFactory_OPEN_HOMEPAGE);
			}
			return __action;
		}
	};

	public static final ActionFactory FIND = new ActionFactory(FindAction.class.getName()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			if ($window == null) {
				throw new IllegalArgumentException();
			}
			IWorkbenchAction __action = new FindAction($window);
			if (__action != null) {
				__action.setId(this.getId());
				__action.setActionDefinitionId("com.myfvn.ui.FindAction"); //$NON-NLS-1$
				__action.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/find.gif"));
				__action.setText(Messages.FVNActionFactory_FIND);
			}
			return __action;
		}
	};

	public static final ActionFactory SEARCH = new ActionFactory(SearchAction.class.getName()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			if ($window == null) {
				throw new IllegalArgumentException();
			}
			IWorkbenchAction __action = new SearchAction($window);
			if (__action != null) {
				__action.setId(this.getId());
				__action.setActionDefinitionId(this.getId());
				__action.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/obj16/searchmark.gif"));
				__action.setText(Messages.FVNActionFactory_SEARCH);
			}
			return __action;
		}
	};

	public static final ActionFactory MINIMIZE_SHELL = new ActionFactory(MinimizeShellAction.class.getName()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			if ($window == null) {
				throw new IllegalArgumentException();
			}
			IWorkbenchAction __action = new MinimizeShellAction($window);
			if (__action != null) {
				__action.setId(this.getId());
				__action.setText(Messages.FVNActionFactory_MINIMIZE_SHELL);
			}
			return __action;
		}
	};

}
