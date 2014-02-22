package net.foreworld.rss2.actions;

import net.foreworld.rss2.Activator;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;



/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Nov 5, 2008 2:30:34 PM
 */
public abstract class RSS2ActionFactory extends RCPActionFactory {
	protected RSS2ActionFactory(String actionId) {
		super(actionId);
	}
	
	public static final ActionFactory NEW_CHANNEL = new ActionFactory(NewChannelAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new NewChannelAction(arg0);
			action.setId(this.getId());
			action.setText("添加频道(&C)");
			action.setActionDefinitionId("net.foreworld.rss2.bindings.command.addchannel");
			action.setImageDescriptor(Activator.getImageDescriptor("icons/file_new.gif"));
			return action;
		}
	};
	
	public static final ActionFactory NEW_CHANNELGROUP = new ActionFactory(NewChannelGroupAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new NewChannelGroupAction(arg0);
			action.setId(this.getId());
			action.setText("新增频道组(&F)");
			action.setActionDefinitionId("net.foreworld.rss2.bindings.command.addchannelgroup");
			action.setImageDescriptor(Activator.getImageDescriptor("icons/folder_new.ico"));
			return action;
		}		
	};

	public static final ActionFactory CHANNEL_ATTRIBUTE = new ActionFactory(ChannelAttributeAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new ChannelAttributeAction(arg0);
			action.setId(this.getId());
			action.setText("频道属性(&A)");
			action.setActionDefinitionId("net.foreworld.rss2.bindings.command.properties");
			action.setImageDescriptor(Activator.getImageDescriptor("icons/QuickReader_383.gif"));
			return action;
		}		
	};

	public static final ActionFactory SEARCH = new ActionFactory(SearchAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new SearchAction(arg0);
			action.setId(this.getId());
			action.setText("搜索(&F)");
			action.setActionDefinitionId("net.foreworld.rss2.bindings.command.search");
			action.setImageDescriptor(Activator.getImageDescriptor("/icons/QuickReader_135.gif"));
			return action;
		}		
	};

	public static final ActionFactory UPDATE_ALLCHANNEL = new ActionFactory(UpdateAllChannelAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new UpdateAllChannelAction(arg0);
			action.setId(this.getId());
			action.setText("全部更新(&U)");
			action.setImageDescriptor(Activator.getImageDescriptor("/icons/update.gif"));
			return action;
		}		
	};

	public static final ActionFactory STATUSBAR = new ActionFactory(StatusBarAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new StatusBarAction(arg0);
			action.setId(this.getId());
			action.setText("状态栏(&S)");
			action.setChecked(true);
			return action;
		}		
	};

	public static final ActionFactory MANAGE_CONFIGURATION = new ActionFactory(ManageConfigurationAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new ManageConfigurationAction(arg0);
			action.setId(this.getId());
			action.setImageDescriptor(Activator.getImageDescriptor("platform:/plugin/org.eclipse.update.ui/icons/obj16/config_obj.gif"));
			action.setText("管理配置(&M)");
			return action;
		}		
	};

	public static final ActionFactory FIND_EXTENSIONS = new ActionFactory(FindExtensionsAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow arg0) {
			if(arg0 == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new FindExtensionsAction(arg0);
			action.setId(this.getId());
			action.setImageDescriptor(Activator.getImageDescriptor("platform:/plugin/org.eclipse.update.ui/icons/obj16/usearch_obj.gif"));
			action.setText("查找并安装(&H)...");
			action.setToolTipText("查找并安装...");
			return action;
		}		
	};
}
