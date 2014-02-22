package net.foreworld.rss2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 3:26:47 PM
 */
public class SearchAction extends BaseAction {
	public static final String ID = SearchAction.class.getName();
	
	public SearchAction(IWorkbenchWindow window) {
		super(window);
		this.setMenuCreator(new IMenuCreator(){
        	Menu menu;

			public void dispose() {
				// TODO Auto-generated method stub				
			}

			public Menu getMenu(Control arg0) {      				
				MenuManager manager = new MenuManager();

				manager.add(new Action("Search One"){
					public void run(){
						System.out.println(this.getText());
					}
				});

				manager.add(new Action("Search Two"){
					public void run(){
						System.out.println(this.getText());
					}
				});
				
				this.menu = manager.createContextMenu(arg0);
                
        		return this.menu;
			}

			public Menu getMenu(Menu arg0) {
				// TODO Auto-generated method stub
				return null;
			}
        });
	}
	
	public void run(){
		System.out.println(ID);
	}

}
