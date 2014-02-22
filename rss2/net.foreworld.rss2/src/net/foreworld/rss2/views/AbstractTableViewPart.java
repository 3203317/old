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
 * @author ����
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
	 * ˢ�µ�ǰѡ�еĶ���
	 */
	public void refresh(){
		this.tableViewer.refresh(this.getFirstElement());
	}
	
	/**
	 * ˢ��ȫ����ͼ
	 */
	public void refreshAll(){
		this.tableViewer.refresh();
	}
	
	/**
	 * ��ȡ��ǰ��ѡ����
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
	 * ��ȡ��ǰѡ�ж����б�
	 * @return
	 */
	public List getElements(){
		return ((StructuredSelection)this.tableViewer.getSelection()).toList();
	}
	
	/**
	 * �ض���ǰѡ����
	 */
	public void setSelection(){
		this.tableViewer.setSelection(new StructuredSelection(this.getFirstElement()),true);
	}
	
	/**
	 * ��λ��ָ������
	 */
	public void setSelection(Object o){
		this.tableViewer.setSelection(new StructuredSelection(o),true);
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
		if(this.tableViewer == null) return;
		Control control = this.tableViewer.getControl();
		MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(control);
        control.setMenu(menu);
		this.createContextMenu(menuManager);
        this.getSite().registerContextMenu(menuManager,this.tableViewer);
	}
	
	/**
	 * ��ȡ�������
	 * @return
	 */
	public int getTableItemCount(){
		return this.tableViewer.getTable().getItemCount();
	}
	
	/**
	 * ��ȡ�������
	 * @return
	 */
	public int getTableColumnCount(){
		return this.tableViewer.getTable().getColumnCount();
	}
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public List getInputList(){
		return (List)this.tableViewer.getInput();
	}

	/**
	 * ���ñ���ͷ
	 */
	public void setPartName(String partName){
		super.setPartName(partName);
	}
}
