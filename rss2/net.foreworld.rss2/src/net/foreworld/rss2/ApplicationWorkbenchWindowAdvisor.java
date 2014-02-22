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
	 * ����ϵͳ����
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
        // ���ù�������ʾ
        configurer.setShowCoolBar(true);
        // ���ÿ�������ʾ
        configurer.setShowFastViewBars(true);
        // ����״̬����ʾ
        configurer.setShowStatusLine(true);
        // ����͸��ͼ����ʾ
        configurer.setShowPerspectiveBar(true); 
        // ���ý�������ʾ
        configurer.setShowProgressIndicator(true);
        // ���ò˵���ʾ
        configurer.setShowMenuBar(true);
		//��ʾ����������
		PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.SHOW_MEMORY_MONITOR, true);
		//��ʾϵͳ�߳�
		PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.SHOW_SYSTEM_JOBS, true);
		//������ʾ��ͼ����ʾ��͸��ͼ����
		PlatformUI.getPreferenceStore().putValue(IWorkbenchPreferenceConstants.INITIAL_FAST_VIEW_BAR_LOCATION, IWorkbenchPreferenceConstants.BOTTOM);
    }
    
    public void postWindowOpen(){
    	super.postWindowOpen();
    	this.createSystemTray();
    	//���ô������
    	Shell shell = this.getWindowConfigurer().getWindow().getShell();
    	Rectangle screenSize = Display.getDefault().getClientArea();
		Rectangle frameSize = shell.getBounds();
		shell.setLocation((screenSize.width - frameSize.width) / 2,(screenSize.height - frameSize.height) / 2);
		//���ô���򿪺����
    	this.getWindowConfigurer().getWindow().getShell().setMaximized(true);
//    	//����windowʱ��ʾ��ͼ
//		IWorkbenchPage page = getWindowConfigurer().getWindow().getActivePage();
//		try {
//			page.showView(ChannelNavigatorView.ID);
//			page.showView(ContentMonitorView.ID);
//		} catch (PartInitException e) {
//			e.printStackTrace();
//		}
    	//�������Ҽ�
    	WorkbenchWindow ww = (WorkbenchWindow)Activator.getDefault().getWorkbench().getActiveWorkbenchWindow();
    	//ww.getCoolBarManager().setLockLayout(true);//��ס������
    	MenuManager mm = new MenuManager("#PopupMenu");
    	IWorkbenchAction action = ActionFactory.LOCK_TOOL_BAR.create(ww);
    	action.setText("����������");
    	mm.add(action);
    	ww.getCoolBarManager().setContextMenuManager(mm);
    	//״̬����ʾ��Ʒ����
    	MyRcpUtil.setStatusLine(Platform.getProduct().getName());    	
    	
//        //����ʱ��С����ͼ
//        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//        IViewReference ref = page.findViewReference(ChannelNavigatorView.ID);
//        page.setPartState(ref, page.STATE_MINIMIZED);
    	//���а���� ����
    	ClipBoardMonitor.getInstance().start();
		//���������а巢���ĸı�
		ClipBoardMonitor.getInstance().addClipboardChangeListener(new IClipboardChangeListener(){
			public void clipboardChange(String text) {
				System.out.println("--------------���а巢���ı�--------------");
				System.out.println(text);
				System.out.println("---------------------------------------");
			}
		});
    }
    
    /*
     * ���������Workbench���ڹر�֮ǰ ( �ϸ�Ľ�������
     * Shell���ر�֮ǰ ) �ɹ�����������ڵ�ShellListener����.
     * ���������������ʲôԭ���Ѿ��ر���, �򲻻�����������.
     * ��������������false, ��ô�ر�Shell�����󽫻ᱻ��
     * ��, ����, �����Ψһ��һ��������ֹ�û��رմ�����Ϊ��
     * �ط�, Ҳ����ʾ�û��Ƿ񱣴浱ǰ���������õ���ѳ���.
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowShellClose()
     */
    public boolean preWindowShellClose(){
		this.hookSysTray.windowMinimized(this.getWindowConfigurer().getWindow().getShell());
		return false;
    	//return super.preWindowShellClose();
    }
    
    /**
     * �ͷ�
     */
    public void dispose(){
    	this.hookSysTray.dispose();
    	CacheImage.getInstance().dispose();
		super.dispose();
    }
}
