package net.foreworld.utils.rcp;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class RCPUtil {

	/**
	 * getWorkbenchWindow
	 * 
	 * @return IWorkbenchWindow
	 */
	public final static IWorkbenchWindow getWorkbenchWindow() {

		// first try active window
		IWorkbenchWindow __activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (__activeWorkbenchWindow != null)
			return __activeWorkbenchWindow;

		// finally try any window
		IWorkbenchWindow __workbenchWindows[] = PlatformUI.getWorkbench().getWorkbenchWindows();
		return __workbenchWindows.length > 0 ? __workbenchWindows[0] : null;
	}

	/**
	 * getWorkbenchPage
	 * 
	 * @param $workbenchWindow
	 * @return IWorkbenchPage
	 */
	public final static IWorkbenchPage getWorkbenchPage(IWorkbenchWindow $workbenchWindow) {

		if ($workbenchWindow != null) {
			IWorkbenchPage __workbenchPage = $workbenchWindow.getActivePage();

			if (__workbenchPage != null)
				return __workbenchPage;

			IWorkbenchPage[] __workbenchPages = $workbenchWindow.getPages();
			return __workbenchPages.length > 0 ? __workbenchPages[0] : null;
		}

		return null;
	}
}
