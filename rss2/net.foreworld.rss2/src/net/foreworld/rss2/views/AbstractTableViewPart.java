package net.foreworld.rss2.views;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
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
 * @Aug 19, 2008 11:18:50 AM
 */
public abstract class AbstractTableViewPart extends ViewPart {
	protected TableViewer tableViewer;

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
		this.tableViewer.refresh(this.getFirstElement());
	}
	
	/**
	 * 刷新全部视图
	 */
	public void refreshAll(){
		this.tableViewer.refresh();
	}
	
	/**
	 * 获取当前所选对象
	 * @return
	 */
	public Object getFirstElement(){
		return ((StructuredSelection)this.tableViewer.getSelection()).getFirstElement();
	}
	
	/**
	 * 
	 * @return
	 */
	public StructuredSelection getSelection(){
		return (StructuredSelection)this.tableViewer.getSelection();
	}
	
	/**
	 * 获取当前选中对象列表
	 * @return
	 */
	public List getElements(){
		return ((StructuredSelection)this.tableViewer.getSelection()).toList();
	}
	
	/**
	 * 重定向当前选中项
	 */
	public void setSelection(){
		this.tableViewer.setSelection(new StructuredSelection(this.getFirstElement()),true);
	}
	
	/**
	 * 定位到指定对象
	 */
	public void setSelection(Object o){
		this.tableViewer.setSelection(new StructuredSelection(o),true);
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
		if(this.tableViewer == null) return;
		Control control = this.tableViewer.getControl();
		MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(control);
        control.setMenu(menu);
		this.createContextMenu(menuManager);
        this.getSite().registerContextMenu(menuManager,this.tableViewer);
	}
	
	/**
	 * 获取表的行数
	 * @return
	 */
	public int getTableItemCount(){
		return this.tableViewer.getTable().getItemCount();
	}
	
	/**
	 * 获取表的列数
	 * @return
	 */
	public int getTableColumnCount(){
		return this.tableViewer.getTable().getColumnCount();
	}
	
	/**
	 * 获取数据列表
	 * @return
	 */
	public List getInputList(){
		return (List)this.tableViewer.getInput();
	}

	/**
	 * 设置标题头
	 */
	public void setPartName(String partName){
		super.setPartName(partName);
	}
}
