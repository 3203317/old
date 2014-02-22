package net.foreworld.rss2.views;

import net.foreworld.rss2.Activator;
import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.utils.MyUtil;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 11:54:57 AM
 */
public class IEView extends AbstractTableViewPart {
	
	private class SaveHtmlAction extends Action {
		public SaveHtmlAction(){
			this.setText("保存当前网页(&S)");
			this.setEnabled(false);
			this.setImageDescriptor(Activator.getImageDescriptor("icons/QuickReader_380.gif"));
		}
		public void run(){
			FileDialog fileDialog = new FileDialog(getShell(),SWT.SAVE);
			fileDialog.setFilterExtensions(new String[]{"*.mht"});
			fileDialog.setFilterNames(new String[]{"Web 档案，单个文件(*.mht)"});
			fileDialog.setFilterPath("%userprofile%/My Documents");
			fileDialog.setFileName(getPartName());
			String fileName = fileDialog.open();
			if(fileName != null){
				//Program.launch(fileName);
				//fileName 为文件路径
				System.out.println(fileName);
			}
		}
	}

	private SaveHtmlAction saveHtmlAction;
	/*******************************************************************************/
	public static final String ID = IEView.class.getName();
	private Browser browser;
	private Document xslDocument;

	
	public void createPartControl(Composite arg0) {
		Composite container = new Composite(arg0, SWT.NONE);
		container.setLayout(arg0.getLayout());
		//首先载入样式表
		this.xslDocument = RssXml2.getDocumentByXmlName("xsl1.xsl");
		this.browser = new Browser(container, SWT.NONE);
		// 直接设置浏览器对象的url
		this.browser.setUrl("http://www.foreworld.net");
		
		this.getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(new ISelectionListener(){
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				Object o = ((StructuredSelection)selection).getFirstElement();
				if(!(o instanceof Element)) return;
				saveHtmlAction.setEnabled(true);
				if(part instanceof DetailListView){
					Element item = (Element)o;
					setPartName(item.elementText("title"));
					setStyle("style.css");
					try {
						browser.setText(MyUtil.getHtmlByXmlXsl(item.asXML(),xslDocument.asXML()));
					} 
					catch (Exception e) {
						saveHtmlAction.setEnabled(false);
					}					
				}
				else if(part instanceof ChannelNavigatorView){
					DetailListView detailListView = (DetailListView)MyRcpUtil.getView(DetailListView.ID);
					if(detailListView.getChannelDoc() == null) return;
					//设置标题
					Element outline = (Element)detailListView.getChannelDoc().selectSingleNode("//channel/title");
					setPartName(outline.getTextTrim());
					setStyle("style.css");
					try {
						browser.setText(MyUtil.getHtmlByXmlXsl(detailListView.getChannelDoc().asXML(),xslDocument.asXML()));
					} 
					catch (Exception e) {
						saveHtmlAction.setEnabled(false);
					}
				}
			}
		});
		
		super.createPartControl(arg0);
		
	}

	public void setFocus() {
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	/**
	 * 设置显示样式
	 * @param cssName
	 */	
	private void setStyle(String cssName){
		if(this.xslDocument == null) return;
		Element link = (Element)this.xslDocument.selectSingleNode("//link");
		link.attribute("href").setText(Activator.getDefault().getStateLocation() +"/"+ cssName);
	}

	
	protected void createAction() {
		this.saveHtmlAction = new SaveHtmlAction();
	}

	
	protected void createContextMenu(MenuManager menuManager) {
	}

	
	protected void createMenu(IMenuManager menu) {
		menu.add(this.saveHtmlAction);
	}

	
	protected void createToolBar(IToolBarManager toolBar) {
		toolBar.add(this.saveHtmlAction);
	}

	
	protected void hookGlobalAction(IActionBars actionBars) {
	}

}
