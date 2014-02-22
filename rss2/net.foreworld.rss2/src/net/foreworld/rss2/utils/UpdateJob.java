package net.foreworld.rss2.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.progress.UIJob;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 17, 2008 11:30:36 AM
 */
public class UpdateJob extends Job {
	private class UpdateChannelJob extends UIJob {
		private TreeViewer viewer;
		private Element outline;
		private String status;
		public UpdateChannelJob(String name,String status,TreeViewer viewer,Element outline) {
			super(name);
			this.viewer = viewer;
			this.outline = outline;
			this.status = status;
		}
		public IStatus runInUIThread(IProgressMonitor arg0) {
			outline.attribute("status").setText(this.status);
			viewer.refresh(outline);
//			outline.attribute("status").setText("waiting");
//			viewer.refresh(outline);
			return Status.OK_STATUS;
		}
	}

	private TreeViewer viewer;
	private Element outline;
	private Iterator it;

	private UpdateChannelJob updateChannelJob;
	
	private boolean isRun;
	
	public UpdateJob(String name){
		super(name);
	}

	public UpdateJob(String name,TreeViewer viewer,Element outline) {
		super(name);
		this.viewer = viewer;
		this.outline = outline;
		if(this.outline == null){
			this.it = this.outline.selectNodes("//outline[@type='rss']").iterator();
		}
		else if(this.outline.hasContent()){
			this.it = outline.selectNodes(outline.getPath()+"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']").iterator();
		}
		else if(this.outline.attribute("type") != null){
			List list = new ArrayList();
			list.add(this.outline);
			this.it = list.iterator();
		}
	}
	
	public void run(){
		if(isRun) return;
		this.isRun = true;
//		if(this.outline == null){
////			this.it = ChannelNavigator.getDoc().selectNodes("//outline[@type='rss']").iterator();
//			ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
//			this.it = RssXml2.getElements(channelNavigatorView.getDoc(), "//outline[@type='rss']").iterator();
//		}
//		else if(outline.hasContent()){
//			this.it = outline.selectNodes(outline.getPath()+"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']").iterator();
//		}
//		else if(this.outline.attribute("type") != null){
//			List list = new ArrayList();
//			list.add(this.outline);
//			this.it = list.iterator();
//		}
		ChannelNavigatorView cnv = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
//		this.it = cnv.getSelectionList().iterator();
		super.schedule();
	}

	protected IStatus run(IProgressMonitor arg0) {
		
//		Iterator it = this.outline.selectNodes("//outline[@type='rss']").iterator();
		while(this.it.hasNext()){
			Element element = (Element)it.next();
			String xmlName = element.attributeValue("id");
			
			this.updateChannelJob = new UpdateChannelJob("更新频道...","running",this.viewer,element);
			this.updateChannelJob.setPriority(UpdateChannelJob.SHORT);
			this.updateChannelJob.schedule();
			try {
				this.updateChannelJob.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			Document newDoc = XmlUtil.download(element.attributeValue("xmlUrl"));
			Document newDoc = RssXml2.getDocumentByURL(element.attributeValue("xmlUrl"));
			if(newDoc == null){

				this.updateChannelJob = new UpdateChannelJob("更新频道...","waiting",this.viewer,element);
				this.updateChannelJob.setPriority(UpdateChannelJob.SHORT);
				this.updateChannelJob.schedule(3000);
				try {
					this.updateChannelJob.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			this.updateChannel(xmlName, newDoc);

			this.updateChannelJob = new UpdateChannelJob("更新频道...","waiting",this.viewer,element);
			this.updateChannelJob.setPriority(UpdateChannelJob.SHORT);
			this.updateChannelJob.schedule(3000);
			try {
				this.updateChannelJob.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		this.isRun = false;
		
		return Status.OK_STATUS;
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	public void setViewer(TreeViewer viewer) {
		this.viewer = viewer;
	}

	public Element getOutline() {
		return outline;
	}

	public void setOutline(Element outline) {
		this.outline = outline;
	}
	
	
	public void updateChannel(String xmlName,Document doc){
//		Document olddoc = XmlUtil.loadFile(RssXml.getFilePath(outline.attributeValue("id").trim()));
		Document olddoc = RssXml2.getDocumentByXmlName(xmlName);
		
		Element oldchannel = (Element)olddoc.getRootElement().element("channel");
		System.out.println(oldchannel.asXML());
		
		Iterator itt = doc.selectNodes("//item").iterator();
		
		while(itt.hasNext()){
			Element ele = (Element)itt.next();
			ele.addAttribute("read", "false");
			oldchannel.add((Node)ele.clone());
		}
		
//		XmlUtil.saveDoc(olddoc,RssXml.getFilePath(((Element)olddoc.selectSingleNode("//channel")).attributeValue("name")));
		RssXml2.saveXmlFile(olddoc, ((Element)olddoc.selectSingleNode("//channel")).attributeValue("name"));
	}

}
