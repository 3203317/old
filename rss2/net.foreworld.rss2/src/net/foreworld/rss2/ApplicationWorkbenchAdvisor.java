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
		//Saving Window Location and Size 选择true将保存
		configurer.setSaveAndRestore(false);
		//控制台
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{new DebugConsole()});
	}
	/*
	 * 事件循环,执行时在console可看到运行过程，这一段加上，就算不实现内容，cpu利用率猛增，没啥事还是不用的好
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#eventLoopIdle(org.eclipse.swt.widgets.Display)
	 */
//	public void eventLoopIdle(Display display){
//		//监听到剪切板发生的改变
////		ClipBoardMonitor.getInstance().addClipboardChangeListener(new IClipboardChangeListener(){
////			public void clipboardChange(String text) {
////				System.out.println(text);
////			}
////		});
//	}
}
