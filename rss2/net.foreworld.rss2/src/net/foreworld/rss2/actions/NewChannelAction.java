package net.foreworld.rss2.actions;

import net.foreworld.rss2.dialogs.AddChannelWizardDialog;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 1:41:51 PM
 */
public class NewChannelAction extends BaseAction{

	public static final String ID = NewChannelAction.class.getName();

	public NewChannelAction(IWorkbenchWindow window) {
		super(window);
	}
	
	public void run() {
		ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
		AddChannelWizardDialog dialog = new AddChannelWizardDialog(channelNavigatorView.getSite().getShell());
		if(dialog.open() == 0){
			//焦点重定位
			channelNavigatorView.getTreeViewer().getControl().setFocus();
		}
	}
}