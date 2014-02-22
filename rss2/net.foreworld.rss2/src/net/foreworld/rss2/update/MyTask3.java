package net.foreworld.rss2.update;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Oct 9, 2008 4:48:01 PM
 */
public class MyTask3 extends Job {

	public MyTask3() {
		super("任务");
	}

	
	protected IStatus run(IProgressMonitor arg0) {
		System.out.println("--------"+arg0);
		
		return Status.OK_STATUS;
	}

}
