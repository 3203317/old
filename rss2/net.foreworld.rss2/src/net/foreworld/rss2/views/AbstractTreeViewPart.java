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
 * @author 黄鑫
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
	 * 刷新当前选中的对象
	 */
	public void refresh(){
		this.treeViewer.refresh(this.getFirstElement());
	}
	
	/**
	 * 刷新全部视图
	 */
	public void refreshAll(){
		this.treeViewer.refresh();
	}
	
	/**
	 * 刷新对象
	 * @param o
	 */
	public void refresh(Object o){
		this.treeViewer.refresh(o);
	}
	
	/**
	 * 获取当前所选对象
	 * @return
	 */
	public Object getFirstElement(){
		return ((StructuredSelection)this.treeViewer.getSelection()).getFirstElement();
	}
	
	/**
	 * 获取当前选中对象列表
	 * @return
	 */
	public List getElements(){
		return ((StructuredSelection)this.treeViewer.getSelection()).toList();
	}
	
	/**
	 * 重定向当前选中项
	 */
	public void setSelection(){
		this.treeViewer.setSelection(new StructuredSelection(this.getFirstElement()),true);
	}
	
	/**
	 * 定位到指定对象
	 */
	public void setSelection(Object o){
		this.treeViewer.setSelection(new StructuredSelection(o),true);
	}
	
	/**
	 * 创建右键菜单
	 */
	protected abstract void createContextMenu(MenuManager menuManager); 
	
	/**
	 * 创建下拉菜单
	 */
	protected abstract void createMenu(IMenuManager menu);
	
	/**
	 * 创建工具栏
	 */
	protected abstract void createToolBar(IToolBarManager toolBar);
	
	/**
	 * 创建动作
	 */
	protected abstract void createAction();
	/**
	 * 动作钩子
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
		 * 创建右键菜单
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
	 * 设置标题头
	 */
	public void setPartName(String partName){
		super.setPartName(partName);
	}
}
