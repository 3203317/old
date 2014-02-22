package net.foreworld.rss2.update;

import org.dom4j.Element;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.progress.UIJob;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Oct 9, 2008 9:57:44 AM
 */
public class MyTask extends Job {
	private class UpdateChannelJob extends UIJob {
		public UpdateChannelJob(String name,String status,TreeViewer viewer,Element outline) {
			super(name);
		}
		public IStatus runInUIThread(IProgressMonitor arg0) {
			System.out.println("aaaaaaa:"+arg0);
			return Status.OK_STATUS;
		}
	}

	private UpdateChannelJob updateChannelJob;
	public MyTask() {
		super("哈哈");
	}

	
	protected IStatus run(IProgressMonitor arg0) {
		this.updateChannelJob = new UpdateChannelJob("更新频道...","waiting",null,null);
		this.updateChannelJob.setPriority(UpdateChannelJob.SHORT);
		this.updateChannelJob.schedule(3000);
		try {
			this.updateChannelJob.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Status.OK_STATUS;
	}

}
