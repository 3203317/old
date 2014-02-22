package net.foreworld.rss2.actions;

import net.foreworld.rss2.Activator;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;

public abstract class RCPActionFactory extends ActionFactory {

	protected RCPActionFactory(String actionId) {
		super(actionId);
	}
	
	public static final ActionFactory ABOUT = new ActionFactory(ActionFactory.ABOUT.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.ABOUT.create(arg0);
			if(action != null){
				action.setText("����(&A)");
			}
			return action;
		}
	};
	
	public static final ActionFactory CLOSE = new ActionFactory(ActionFactory.CLOSE.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.CLOSE.create(arg0);
			if(action != null){
				action.setText("�ر�(&C)");
			}
			return action;
		}
	};
	
	public static final ActionFactory CLOSE_ALL = new ActionFactory(ActionFactory.CLOSE_ALL.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.CLOSE_ALL.create(arg0);
			if(action != null){
				action.setText("�ر�ȫ��(&L)");
			}
			return action;
		}
	};
	
	public static final ActionFactory SAVE_AS = new ActionFactory(ActionFactory.SAVE_AS.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.SAVE_AS.create(arg0);
			if(action != null){
				action.setText("���Ϊ(&A)...");
			}
			return action;
		}
	};
	
	public static final ActionFactory PRINT = new ActionFactory(ActionFactory.PRINT.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.PRINT.create(arg0);
			if(action != null){
				action.setText("��ӡ(&P)");
			}
			return action;
		}
	};
	
	public static final ActionFactory QUIT = new ActionFactory(ActionFactory.QUIT.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.QUIT.create(arg0);
			if(action != null){
				action.setText("�˳�(&X)");
				action.setImageDescriptor(Activator.getImageDescriptor("/icons/nav_stop.gif"));
				action.setActionDefinitionId("net.foreworld.rss2.bindings.command.exit");
			}
			return action;
		}
	};
	
	public static final ActionFactory UNDO = new ActionFactory(ActionFactory.UNDO.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.UNDO.create(arg0);
			if(action != null){
				action.setText("����(&U)");
			}
			return action;
		}
	};
	
	public static final ActionFactory REDO = new ActionFactory(ActionFactory.REDO.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.REDO.create(arg0);
			if(action != null){
				action.setText("����(&R)");
			}
			return action;
		}
	};
	
	public static final ActionFactory CUT = new ActionFactory(ActionFactory.CUT.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.CUT.create(arg0);
			if(action != null){
				action.setText("����(&T)");
			}
			return action;
		}
	};
	
	public static final ActionFactory COPY = new ActionFactory(ActionFactory.COPY.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.COPY.create(arg0);
			if(action != null){
				action.setText("����(&C)");
			}
			return action;
		}
	};
	
	public static final ActionFactory PASTE = new ActionFactory(ActionFactory.PASTE.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.PASTE.create(arg0);
			if(action != null){
				action.setText("ճ��(&P)");
			}
			return action;
		}
	};
	
	public static final ActionFactory DELETE = new ActionFactory(ActionFactory.DELETE.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.DELETE.create(arg0);
			if(action != null){
				action.setText("ɾ��(&D)");
			}
			return action;
		}
	};
	
	public static final ActionFactory SELECT_ALL = new ActionFactory(ActionFactory.SELECT_ALL.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.SELECT_ALL.create(arg0);
			if(action != null){
				action.setText("ȫѡ(&A)");
			}
			return action;
		}
	};
	
	public static final ActionFactory TOGGLE_COOLBAR = new ActionFactory(ActionFactory.TOGGLE_COOLBAR.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.TOGGLE_COOLBAR.create(arg0);
			if(action != null){
				action.setText("������(&T)");
				action.setChecked(true);
			}
			return action;
		}
	};
	
	public static final ActionFactory PREFERENCES = new ActionFactory(ActionFactory.PREFERENCES.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.PREFERENCES.create(arg0);
			if(action != null){
				action.setText("��ѡ��(&P)");
				action.setImageDescriptor(Activator.getImageDescriptor("/icons/QuickReader_151.gif"));
			}
			return action;
		}
	};
	
	public static final ActionFactory OPEN_NEW_WINDOW = new ActionFactory(ActionFactory.OPEN_NEW_WINDOW.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.OPEN_NEW_WINDOW.create(arg0);
			if(action != null){
				action.setText("���´���(&N)");
				action.setActionDefinitionId("net.foreworld.rss2.bindings.command.open_new_window");
			}
			return action;
		}
	};
	
	public static final ActionFactory HELP_CONTENTS = new ActionFactory(ActionFactory.HELP_CONTENTS.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.HELP_CONTENTS.create(arg0);
			if(action != null){
				action.setText("�����ĵ�(&H)");
			}
			return action;
		}
	};
	
	public static final ActionFactory HELP_SEARCH = new ActionFactory(ActionFactory.HELP_SEARCH.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.HELP_SEARCH.create(arg0);
			if(action != null){
				action.setText("��������(&E)");
			}
			return action;
		}
	};
	
	public static final ActionFactory DYNAMIC_HELP = new ActionFactory(ActionFactory.DYNAMIC_HELP.getId()){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			IWorkbenchAction action = ActionFactory.DYNAMIC_HELP.create(arg0);
			if(action != null){
				action.setText("��̬����(&D)");
				action.setActionDefinitionId("net.foreworld.rss2.bindings.command.dynamic_help");
			}
			return action;
		}
	};
	
	

}
