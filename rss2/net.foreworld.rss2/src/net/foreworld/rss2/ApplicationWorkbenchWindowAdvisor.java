package net.foreworld.rss2;

import net.foreworld.rss2.monitor.ClipBoardMonitor;
import net.foreworld.rss2.monitor.IClipboardChangeListener;
import net.foreworld.rss2.utils.MyRcpUtil;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchWindow;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	private HookSysTray hookSysTray;
	
	/**
	 * 创建系统托盘
	 */
	private void createSystemTray() {
		this.hookSysTray = new HookSysTray();
		this.hookSysTray.createSysTray(getWindowConfigurer().getWindow());
	}
	
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
    	super.preWindowOpen();
        IWorkbenchWindowConfigurer configurer = this.getWindowConfigurer();
        configurer.setInitialSize(new Point(600, 400));
        // 设置工具栏显示
        configurer.setShowCoolBar(true);
        // 设置快显栏显示
        configurer.setShowFastViewBars(true);
        // 设置状态栏显示
        configurer.setShowStatusLine(true);
        // 设置透视图栏显示
        configurer.setShowPerspectiveBar(true); 
        // 设置进度条显示
        configurer.setShowProgressIndicator(true);
        // 设置菜单显示
        configurer.setShowMenuBar(true);
		//显示垃圾回收器
		PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.SHOW_MEMORY_MONITOR, true);
		//显示系统线程
		PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.SHOW_SYSTEM_JOBS, true);
		//快速显示视图栏显示在透视图下面
		PlatformUI.getPreferenceStore().putValue(IWorkbenchPreferenceConstants.INITIAL_FAST_VIEW_BAR_LOCATION, IWorkbenchPreferenceConstants.BOTTOM);
    }
    
    public void postWindowOpen(){
    	super.postWindowOpen();
    	this.createSystemTray();
    	//设置窗体居中
    	Shell shell = this.getWindowConfigurer().getWindow().getShell();
    	Rectangle screenSize = Display.getDefault().getClientArea();
		Rectangle frameSize = shell.getBounds();
		shell.setLocation((screenSize.width - frameSize.width) / 2,(screenSize.height - frameSize.height) / 2);
		//设置窗体打开后最大化
    	this.getWindowConfigurer().getWindow().getShell().setMaximized(true);
//    	//启动window时显示视图
//		IWorkbenchPage page = getWindowConfigurer().getWindow().getActivePage();
//		try {
//			page.showView(ChannelNavigatorView.ID);
//			page.showView(ContentMonitorView.ID);
//		} catch (PartInitException e) {
//			e.printStackTrace();
//		}
    	//工具栏右键
    	WorkbenchWindow ww = (WorkbenchWindow)Activator.getDefault().getWorkbench().getActiveWorkbenchWindow();
    	//ww.getCoolBarManager().setLockLayout(true);//锁住工具栏
    	MenuManager mm = new MenuManager("#PopupMenu");
    	IWorkbenchAction action = ActionFactory.LOCK_TOOL_BAR.create(ww);
    	action.setText("锁定工具栏");
    	mm.add(action);
    	ww.getCoolBarManager().setContextMenuManager(mm);
    	//状态栏显示产品名称
    	MyRcpUtil.setStatusLine(Platform.getProduct().getName());    	
    	
//        //启动时最小化视图
//        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//        IViewReference ref = page.findViewReference(ChannelNavigatorView.ID);
//        page.setPartState(ref, page.STATE_MINIMIZED);
    	//剪切板监视 启动
    	ClipBoardMonitor.getInstance().start();
		//监听到剪切板发生的改变
		ClipBoardMonitor.getInstance().addClipboardChangeListener(new IClipboardChangeListener(){
			public void clipboardChange(String text) {
				System.out.println("--------------剪切板发生改变--------------");
				System.out.println(text);
				System.out.println("---------------------------------------");
			}
		});
    }
    
    /*
     * 这个方法在Workbench窗口关闭之前 ( 严格的讲是它的
     * Shell被关闭之前 ) 由关联到这个窗口的ShellListener调用.
     * 如果窗口由于其他什么原因已经关闭了, 则不会调用这个方法.
     * 如果这个方法返回false, 那么关闭Shell的请求将会被忽
     * 略, 所以, 这个是唯一的一个可以阻止用户关闭窗口行为的
     * 地方, 也是提示用户是否保存当前工作和设置的最佳场所.
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowShellClose()
     */
    public boolean preWindowShellClose(){
		this.hookSysTray.windowMinimized(this.getWindowConfigurer().getWindow().getShell());
		return false;
    	//return super.preWindowShellClose();
    }
    
    /**
     * 释放
     */
    public void dispose(){
    	this.hookSysTray.dispose();
    	CacheImage.getInstance().dispose();
		super.dispose();
    }
}
