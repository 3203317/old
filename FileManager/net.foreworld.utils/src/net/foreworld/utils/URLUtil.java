package net.foreworld.utils;

import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.program.Program;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class URLUtil {

	private static String _url;

	private static final String[] WINDOWS_BROWSERS = new String[] {
			"rundll32 url.dll,FileProtocolHandler %URL%", // $NON-NLS-1$
			// running IE
			"firefox -remote openurl(%URL%,new-window)", // $NON-NLS-1$
			// running FF
			"mozilla -remote openurl(%URL%,new-window)", // $NON-NLS-1$
			// running Moz
			"firefox %URL%", // $NON-NLS-1$ new FF
			"mozilla %URL%", // $NON-NLS-1$ new Moz
			"kfmclient openURL %URL%", // $NON-NLS-1$ Konqueror
			"opera -newwindow %URL%" // $NON-NLS-1$ Opera
	};

	private static Job _job = new Job("") { //$NON-NLS-1$
		protected IStatus run(IProgressMonitor arg0) {
			for (String __cmd : WINDOWS_BROWSERS) {

				__cmd = __cmd.replaceAll("%URL%", _url);//$NON-NLS-1$

				try {
					Process ___proc = Runtime.getRuntime().exec(__cmd);

					if (___proc.waitFor() == 0)
						break;
				} catch (Exception $ex) {
					$ex.printStackTrace();
				}
			}

			return Status.OK_STATUS;
		}
	};

	/**
	 * 打开地址链接
	 * 
	 * @param $url
	 */
	public static void openUrl(String $url) {

		if (!Program.launch($url)) {
			_url = $url;

			_job.schedule();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String url = "http://www.myfvn.com";

		try {
			Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler " + url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
