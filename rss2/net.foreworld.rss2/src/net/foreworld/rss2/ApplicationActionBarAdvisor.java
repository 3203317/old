package net.foreworld.rss2;

import net.foreworld.rss2.actions.RSS2ActionFactory;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.utils.StatusBarContribution;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * 
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	private IWorkbenchWindow window;
	private StatusBarContribution statusBar;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    
    protected void makeActions(final IWorkbenchWindow window) {
    	this.window = window;
    	//
    	this.register(RSS2ActionFactory.NEW_CHANNEL.create(this.window));
    	//
    	this.register(RSS2ActionFactory.NEW_CHANNELGROUP.create(this.window));
    	//
    	this.register(RSS2ActionFactory.CLOSE.create(this.window));
    	//
    	this.register(RSS2ActionFactory.CLOSE_ALL.create(this.window));
    	//
    	this.register(RSS2ActionFactory.SAVE_AS.create(this.window));
    	//
    	this.register(RSS2ActionFactory.PRINT.create(this.window));
        //
        this.register(RSS2ActionFactory.QUIT.create(this.window));        
        //
        this.register(RSS2ActionFactory.UNDO.create(this.window));
        //
        this.register(RSS2ActionFactory.REDO.create(this.window));
        //
        this.register(RSS2ActionFactory.CUT.create(this.window));
        //
        this.register(RSS2ActionFactory.COPY.create(this.window));
        //
        this.register(RSS2ActionFactory.PASTE.create(this.window));
        //
    	this.register(RSS2ActionFactory.CHANNEL_ATTRIBUTE.create(this.window));
        //
        this.register(RSS2ActionFactory.DELETE.create(this.window));
        //
        this.register(RSS2ActionFactory.SELECT_ALL.create(this.window));
        //
        this.register(RSS2ActionFactory.SEARCH.create(this.window));        
        //        
        this.register(RSS2ActionFactory.TOGGLE_COOLBAR.create(this.window));
        //
        this.register(RSS2ActionFactory.STATUSBAR.create(this.window));        
        //
        this.register(RSS2ActionFactory.UPDATE_ALLCHANNEL.create(this.window));       
        //
        this.register(RSS2ActionFactory.OPEN_NEW_WINDOW.create(this.window));
    	//
        this.register(RSS2ActionFactory.PREFERENCES.create(this.window));        
        //
        this.register(RSS2ActionFactory.HELP_CONTENTS.create(this.window));
        //
        this.register(RSS2ActionFactory.DYNAMIC_HELP.create(this.window));
        //
        this.register(RSS2ActionFactory.FIND_EXTENSIONS.create(this.window));
        //
        this.register(RSS2ActionFactory.ABOUT.create(this.window));
        //
        this.register(RSS2ActionFactory.MANAGE_CONFIGURATION.create(this.window));
        //
        this.register(RSS2ActionFactory.HELP_SEARCH.create(this.window));
        //
        this.statusBar = new StatusBarContribution();
        this.statusBar.setVisible(true);
    }
    
    protected void fillMenuBar(IMenuManager menuBar) {
        this.createFileMenu(menuBar);
        
        this.createEditMenu(menuBar);
        
        this.createViewMenu(menuBar);
        
        this.createToolMenu(menuBar);
        
        this.createWindowMenu(menuBar);
        
        this.createHelpMenu(menuBar);

    }
    
	private void createFileMenu(IMenuManager menuBar){
    	MenuManager fileMenu = new MenuManager("文件(&F)", IWorkbenchActionConstants.M_FILE);
    	menuBar.add(fileMenu);
    	    	
    	fileMenu.add(this.getAction(RSS2ActionFactory.NEW_CHANNEL.getId()));
    	fileMenu.add(this.getAction(RSS2ActionFactory.NEW_CHANNELGROUP.getId()));
    	fileMenu.add(new Separator());
    	
		fileMenu.add(this.getAction(RSS2ActionFactory.CLOSE.getId()));
		fileMenu.add(this.getAction(RSS2ActionFactory.CLOSE_ALL.getId()));
		fileMenu.add(new Separator());
		
		fileMenu.add(this.getAction(RSS2ActionFactory.SAVE_AS.getId()));
		fileMenu.add(new Separator());

		fileMenu.add(this.getAction(RSS2ActionFactory.PRINT.getId()));
		fileMenu.add(new Separator());
		
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.IMPORT_EXT));
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.EXPORT + ".ext"));
		fileMenu.add(new Separator());
		
		fileMenu.add(this.getAction(RSS2ActionFactory.QUIT.getId()));	
    }
    
    private void createEditMenu(IMenuManager menuBar){
    	MenuManager editMenu = new MenuManager("编辑(&E)", IWorkbenchActionConstants.M_EDIT);
    	menuBar.add(editMenu);

    	editMenu.add(this.getAction(RSS2ActionFactory.UNDO.getId()));
    	editMenu.add(this.getAction(RSS2ActionFactory.REDO.getId()));
    	editMenu.add(new Separator());
    	
    	editMenu.add(this.getAction(RSS2ActionFactory.CUT.getId()));
    	editMenu.add(this.getAction(RSS2ActionFactory.COPY.getId()));
    	editMenu.add(this.getAction(RSS2ActionFactory.PASTE.getId()));
    	editMenu.add(new Separator());
    	
    	editMenu.add(this.getAction(RSS2ActionFactory.DELETE.getId()));
    	editMenu.add(this.getAction(RSS2ActionFactory.SELECT_ALL.getId()));
    	editMenu.add(new Separator());
    	
    	editMenu.add(this.getAction(RSS2ActionFactory.SEARCH.getId()));
    	editMenu.add(new Separator());
		
		editMenu.add(this.getAction(RSS2ActionFactory.CHANNEL_ATTRIBUTE.getId()));
    	
    }
    
	private void createViewMenu(IMenuManager menuBar){
        MenuManager viewMenu = new MenuManager("查看(&V)", IWorkbenchActionConstants.M_VIEW);
        menuBar.add(viewMenu);

        viewMenu.add(this.getAction(RSS2ActionFactory.TOGGLE_COOLBAR.getId()));
        viewMenu.add(this.getAction(RSS2ActionFactory.STATUSBAR.getId()));    	
        
    }
    
    private void createToolMenu(IMenuManager menuBar){
    	MenuManager toolMenu = new MenuManager("工具(&T)", "tool");
    	menuBar.add(toolMenu);
    	
    	toolMenu.add(this.getAction(RSS2ActionFactory.UPDATE_ALLCHANNEL.getId()));
    }
    
    private void createWindowMenu(IMenuManager menuBar){
        MenuManager windowMenu = new MenuManager("窗口(&W)", IWorkbenchActionConstants.M_WINDOW);
        menuBar.add(windowMenu);

        windowMenu.add(this.getAction(RSS2ActionFactory.OPEN_NEW_WINDOW.getId()));
        windowMenu.add(new Separator());
        
        MenuManager perspectiveMenu = new MenuManager("透视图(&P)","perspective");
        perspectiveMenu.add(ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(this.window));
        windowMenu.add(perspectiveMenu);
        
        MenuManager viewMenu = new MenuManager("视图(&V)","view");
        viewMenu.add(ContributionItemFactory.VIEWS_SHORTLIST.create(this.window));
        windowMenu.add(viewMenu);
        
        windowMenu.add(new Separator());        
        
        windowMenu.add(this.getAction(RSS2ActionFactory.PREFERENCES.getId()));
           	
    }
    
    private void createHelpMenu(IMenuManager menuBar){
        MenuManager helpMenu = new MenuManager("帮助(&H)", IWorkbenchActionConstants.M_HELP);
        menuBar.add(helpMenu);
        
        helpMenu.add(new Action("主页"){
        	public void run(){
        		MyRcpUtil.openIE("http://www.foreworld.net");
        	}
        });
        helpMenu.add(new Separator());

        helpMenu.add(this.getAction(RSS2ActionFactory.HELP_CONTENTS.getId()));
        helpMenu.add(this.getAction(RSS2ActionFactory.HELP_SEARCH.getId()));
        helpMenu.add(this.getAction(RSS2ActionFactory.DYNAMIC_HELP.getId()));
        helpMenu.add(new Separator("hotkey"));
        helpMenu.add(new Separator());
        
        MenuManager softwareUpdatesMenu = new MenuManager("软件更新(&S)","softwareUpdates");
        helpMenu.add(softwareUpdatesMenu);
        
        softwareUpdatesMenu.add(this.getAction(RSS2ActionFactory.FIND_EXTENSIONS.getId()));
        softwareUpdatesMenu.add(this.getAction(RSS2ActionFactory.MANAGE_CONFIGURATION.getId()));

        helpMenu.add(new Separator());
        helpMenu.add(this.getAction(RSS2ActionFactory.ABOUT.getId()));
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
        IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "fileTool"));   

        ActionContributionItem addChannelActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.NEW_CHANNEL.getId()));
        addChannelActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(addChannelActionCI);

        ActionContributionItem addChannelGroupActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.NEW_CHANNELGROUP.getId()));
        addChannelGroupActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(addChannelGroupActionCI);
        
        //
        toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "editTool"));

        ActionContributionItem deleteActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.DELETE.getId()));
        deleteActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(deleteActionCI);
        toolbar.add(new Separator());
        
        ActionContributionItem searchActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.SEARCH.getId()));
        searchActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(searchActionCI);        
        toolbar.add(new Separator());
        
        ActionContributionItem channelAttributeActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.CHANNEL_ATTRIBUTE.getId()));
        channelAttributeActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(channelAttributeActionCI);
        
        //
        toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "viewTool"));   

        ActionContributionItem updateChannelOfAllActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.UPDATE_ALLCHANNEL.getId()));
        updateChannelOfAllActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(updateChannelOfAllActionCI);
        
        //
        toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar,"windowTool"));

        ActionContributionItem preferencesActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.PREFERENCES.getId()));
        preferencesActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(preferencesActionCI);

        toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "helpTool"));  
        
        ActionContributionItem helpActionCI = new ActionContributionItem(this.getAction(RSS2ActionFactory.HELP_CONTENTS.getId()));
        helpActionCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        toolbar.add(helpActionCI);
    }

    /**
     * 状态栏
     */
    protected void fillStatusLine(IStatusLineManager statusLine){
    	statusLine.add(this.statusBar);
    	super.fillStatusLine(statusLine);
    }
}
