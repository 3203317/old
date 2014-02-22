package net.foreworld.rss2.actions;

import net.foreworld.rss2.Activator;

import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.RetargetAction;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Aug 30, 2008 10:38:01 AM
 */
public class DeleteRetargetAction extends RetargetAction {

	public DeleteRetargetAction() {
		super(IWorkbenchActionConstants.DELETE, "删除(&D)");
		this.setActionDefinitionId("net.foreworld.rss2.bindings.command.delete");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/delete_obj.gif"));
	}

}
