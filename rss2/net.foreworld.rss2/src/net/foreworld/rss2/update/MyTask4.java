package net.foreworld.rss2.update;

import java.util.Iterator;

import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.progress.UIJob;

/**
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @version ����ʱ�䣺Oct 9, 2008 9:22:44 PM
 */
public class MyTask4 implements Runnable {
	
	private Element outline;
	
	public MyTask4(Element outline){
		this.outline = outline;
	}

	public void run() {

		//���µ�һ��
		UpdateChannelJob job = new UpdateChannelJob("running");
		job.schedule();
		
		//���µڶ���
		Document newDoc = RssXml2.getDocumentByURL(this.outline.attributeValue("xmlUrl"));//�õ������µ�����
		if(newDoc != null){
			//����Ƶ������
			Document oldDoc = RssXml2.getDocumentByXmlName(this.outline.attributeValue("id"));
			
			Element oldChannel = (Element)oldDoc.getRootElement().element("channel");
			System.out.println(oldChannel.asXML());
			
			Iterator it = newDoc.selectNodes("//item").iterator();
			
			while(it.hasNext()){
				Element ele = (Element)it.next();
				ele.addAttribute("read", "false");
				oldChannel.add((Node)ele.clone());
			}
			
			RssXml2.saveXmlFile(oldDoc, ((Element)oldDoc.selectSingleNode("//channel")).attributeValue("name"));
		}
		
		//���µ�����
		job = new UpdateChannelJob("waiting");
		job.schedule(10000);
		try {
			job.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	private class UpdateChannelJob extends UIJob{
		private String status;
		
		public UpdateChannelJob(String status) {
			super("����Ƶ��...");
			this.status = status;
		}

		public IStatus runInUIThread(IProgressMonitor arg0) {
			outline.attribute("status").setText(this.status);
			ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
			channelNavigatorView.refresh(outline);
			return Status.OK_STATUS;
		}
		
	}
}
