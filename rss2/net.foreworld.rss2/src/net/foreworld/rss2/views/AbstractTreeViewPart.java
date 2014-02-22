package net.foreworld.rss2.views;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

/**
 * 
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 19, 2008 8:33:34 AM
 */
public abstract class AbstractTreeViewPart extends ViewPart {
	protected TreeViewer treeViewer;

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public void setTreeViewer(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	protected IWorkbenchWindow getWorkbenchWindow(){
		return this.getViewSite().getWorkbenchWindow();
	}
	
	protected Shell getShell(){
		return this.getSite().getShell();
	}

	/**
	 * ˢ�µ�ǰѡ�еĶ���
	 */
	public void refresh(){
		this.treeViewer.refresh(this.getFirstElement());
	}
	
	/**
	 * ˢ��ȫ����ͼ
	 */
	public void refreshAll(){
		this.treeViewer.refresh();
	}
	
	/**
	 * ˢ�¶���
	 * @param o
	 */
	public void refresh(Object o){
		this.treeViewer.refresh(o);
	}
	
	/**
	 * ��ȡ��ǰ��ѡ����
	 * @return
	 */
	public Object getFirstElement(){
		return ((StructuredSelection)this.treeViewer.getSelection()).getFirstElement();
	}
	
	/**
	 * ��ȡ��ǰѡ�ж����б�
	 * @return
	 */
	public List getElements(){
		return ((StructuredSelection)this.treeViewer.getSelection()).toList();
	}
	
	/**
	 * �ض���ǰѡ����
	 */
	public void setSelection(){
		this.treeViewer.setSelection(new StructuredSelection(this.getFirstElement()),true);
	}
	
	/**
	 * ��λ��ָ������
	 */
	public void setSelection(Object o){
		this.treeViewer.setSelection(new StructuredSelection(o),true);
	}
	
	/**
	 * �����Ҽ��˵�
	 */
	protected abstract void createContextMenu(MenuManager menuManager); 
	
	/**
	 * ���������˵�
	 */
	protected abstract void createMenu(IMenuManager menu);
	
	/**
	 * ����������
	 */
	protected abstract void createToolBar(IToolBarManager toolBar);
	
	/**
	 * ��������
	 */
	protected abstract void createAction();
	/**
	 * ��������
	 */
	protected abstract void hookGlobalAction(IActionBars actionBars);
	
	/**
	 * 
	 */
	public void createPartControl(Composite composite) {
		this.createAction();
		this.createMenu(this.getViewSite().getActionBars().getMenuManager());
		this.createToolBar(this.getViewSite().getActionBars().getToolBarManager());
		this.hookGlobalAction(this.getViewSite().getActionBars());
		
		/**
		 * �����Ҽ��˵�
		 */
		if(this.treeViewer == null) return;
		Control control = this.treeViewer.getControl();
		MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(control);
        control.setMenu(menu);
		this.createContextMenu(menuManager);
        this.getSite().registerContextMenu(menuManager,this.treeViewer);
	}

	/**
	 * ���ñ���ͷ
	 */
	public void setPartName(String partName){
		super.setPartName(partName);
	}
}
