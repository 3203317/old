package net.foreworld.rss2.actions;

import net.foreworld.rss2.dialogs.AttributeOfChannelDialog;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.dom4j.Element;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 3:27:24 PM
 */
public class ChannelAttributeAction extends BaseAction{

	public static final String ID = ChannelAttributeAction.class.getName();
	
	public ChannelAttributeAction(IWorkbenchWindow window){
		super(window);
		window.getSelectionService().addSelectionListener(this);
	}
	public void run(){
		ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
		AttributeOfChannelDialog dialog = new AttributeOfChannelDialog(channelNavigatorView.getSite().getShell());
		dialog.open();
	}
	public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
		ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
		Object o = channelNavigatorView.getFirstElement();
		if(o instanceof Element){
			Element outline = (Element)o;
			this.setEnabled(outline.attribute("type") != null);
		}
		else{
			this.setEnabled(false);
		}
	}
}
