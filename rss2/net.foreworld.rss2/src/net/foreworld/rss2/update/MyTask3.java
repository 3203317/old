package net.foreworld.rss2.update;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @version ����ʱ�䣺Oct 9, 2008 4:48:01 PM
 */
public class MyTask3 extends Job {

	public MyTask3() {
		super("����");
	}

	
	protected IStatus run(IProgressMonitor arg0) {
		System.out.println("--------"+arg0);
		
		return Status.OK_STATUS;
	}

}
