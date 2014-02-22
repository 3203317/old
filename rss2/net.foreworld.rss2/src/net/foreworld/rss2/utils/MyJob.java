package net.foreworld.rss2.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 22, 2008 7:16:07 PM
 */
public class MyJob extends Job {

	public MyJob(String name) {
		super(name);
	}

	
	protected IStatus run(IProgressMonitor arg0) {
		System.out.println("111");
		this.schedule(1000);
		return Status.OK_STATUS;
	}

}
