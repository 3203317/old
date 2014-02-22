package net.foreworld.rss2.update;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.utils.MyDate;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Oct 10, 2008 11:22:51 AM
 */
public class UpdateJob extends Job {
	private Document doc;
	private WorkQueue workQueue;
	
	public UpdateJob(Document doc,WorkQueue workQueue) {
		super("更新频道...");
		this.doc = doc;
		this.workQueue = workQueue;
	}

	
	protected IStatus run(IProgressMonitor arg0) {
		//每秒循环一次
		this.schedule(1000);
		Iterator it = RssXml2.getElements(this.doc, "//outline[@type='rss']").iterator();
		while(it.hasNext()){
			Element outline = (Element)it.next();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt = new Date();
			if(outline.attribute("updateNextTime") == null){
				dt.setSeconds(dt.getSeconds() + Integer.parseInt(outline.attributeValue("updateTime"))/1000);
				outline.addAttribute("updateNextTime", df.format(dt));
			}
			if(MyDate.isDateBefore(outline.attributeValue("updateNextTime"),df.format(dt).toString())){
				MyTask4 mt4 = new MyTask4(outline);
				this.workQueue.execute(mt4);
				//重新规划时间
				dt.setSeconds(dt.getSeconds() + Integer.parseInt(outline.attributeValue("updateTime"))/1000);
				outline.attribute("updateNextTime").setText(df.format(dt));
			}
		}
		return Status.OK_STATUS;
	}

}
