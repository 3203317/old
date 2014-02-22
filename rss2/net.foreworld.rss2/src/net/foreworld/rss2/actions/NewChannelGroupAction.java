package net.foreworld.rss2.actions;

import net.foreworld.rss2.dialogs.AddChannelGroupDialog;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 1:45:07 PM
 */
public class NewChannelGroupAction extends BaseAction{
	
	public static final String ID = NewChannelGroupAction.class.getName();
	
	public NewChannelGroupAction(IWorkbenchWindow window){
		super(window);
	}
	
	public void run(){
		ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
		AddChannelGroupDialog dialog = new AddChannelGroupDialog(channelNavigatorView.getSite().getShell(),"CREATE");
		if(dialog.open() == 0){
			//½¹µã
			channelNavigatorView.getTreeViewer().getControl().setFocus();
		}
	}
}