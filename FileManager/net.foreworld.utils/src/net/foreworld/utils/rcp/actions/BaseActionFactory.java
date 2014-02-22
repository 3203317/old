package net.foreworld.utils.rcp.actions;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class BaseActionFactory extends ActionFactory {

	protected BaseActionFactory(String $actionId) {
		super($actionId);
	}

	public static final ActionFactory ABOUT = new ActionFactory(ActionFactory.ABOUT.getId()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			IWorkbenchAction __action = ActionFactory.ABOUT.create($window);
			if (__action != null) {
				// 关于(&A)
				__action.setText("\u5173\u4e8e(&A)");
			}
			return __action;
		}
	};

	public static final ActionFactory QUIT = new ActionFactory(QuitAction.class.getName()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			if ($window == null) {
				throw new IllegalArgumentException();
			}
			IWorkbenchAction __action = new QuitAction($window);
			if (__action != null) {
				__action.setId(this.getId());
				// 退出(&X)
				__action.setText("\u9000\u51fa(&X)");
			}
			return __action;
		}
	};

	public static final ActionFactory SHOW_KEY_ASSIST = new ActionFactory(ShowKeyAssistAction.class.getName()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			if ($window == null) {
				throw new IllegalArgumentException();
			}
			IWorkbenchAction __action = new ShowKeyAssistAction($window);
			if (__action != null) {
				__action.setId(this.getId());
				__action.setActionDefinitionId("net.foreworld.ui.actions.showKeyAssist");//$NON-NLS-1$
				__action.setText(Messages.BaseActionFactory_SHOW_KEY_ASSIST);
			}
			return __action;
		}
	};

	public static final ActionFactory HELP_CONTENTS = new ActionFactory(ActionFactory.HELP_CONTENTS.getId()) {
		public IWorkbenchAction create(IWorkbenchWindow $window) {
			IWorkbenchAction __action = ActionFactory.HELP_CONTENTS.create($window);
			__action.setActionDefinitionId("net.foreworld.ui.actions.help_contents");
			return __action;
		}
	};
}
