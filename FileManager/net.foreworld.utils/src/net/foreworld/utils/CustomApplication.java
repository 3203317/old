package net.foreworld.utils;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.swt.SWT;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public abstract class CustomApplication implements IApplication {

	// 判断程序当前运行的操作系统是否是 Windows
	public static final boolean IS_WINDOWS = "win32".equals(SWT.getPlatform()); //$NON-NLS-1$

	// LINUX
	public static final boolean IS_LINUX = "gtk".equals(SWT.getPlatform()); //$NON-NLS-1$

	// MAC
	public static final boolean IS_MAC = "carbon".equals(SWT.getPlatform()); //$NON-NLS-1$

	/** Flag to indicate FileManager integrated to Eclipse or not */
	public static final boolean IS_ECLIPSE = false;

	public static boolean isWindows7() {
		return "6.1".equals(System.getProperty("os.version")); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
