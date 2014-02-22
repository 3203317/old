package net.foreworld.rss2;

import net.foreworld.rss2.perspectives.RssPerspective;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;

/**
 * This workbench advisor creates the window advisor, and specifies
 * the perspective id for the initial window.
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
	
//	private static final String PERSPECTIVE_ID = "net.foreworld.rss2.perspective";

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
//    	configurer.setShowFastViewBars(true);
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return RssPerspective.ID;
	} 
	
	public void initialize(IWorkbenchConfigurer configurer)	{
		super.initialize(configurer);
		//Saving Window Location and Size ѡ��true������
		configurer.setSaveAndRestore(false);
		//����̨
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{new DebugConsole()});
	}
	/*
	 * �¼�ѭ��,ִ��ʱ��console�ɿ������й��̣���һ�μ��ϣ����㲻ʵ�����ݣ�cpu������������ûɶ�»��ǲ��õĺ�
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#eventLoopIdle(org.eclipse.swt.widgets.Display)
	 */
//	public void eventLoopIdle(Display display){
//		//���������а巢���ĸı�
////		ClipBoardMonitor.getInstance().addClipboardChangeListener(new IClipboardChangeListener(){
////			public void clipboardChange(String text) {
////				System.out.println(text);
////			}
////		});
//	}
}
