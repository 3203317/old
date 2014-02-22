package net.foreworld.rss2.views;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.foreworld.rss2.Activator;
import net.foreworld.rss2.CacheImage;
import net.foreworld.rss2.Messages;
import net.foreworld.rss2.actions.RSS2ActionFactory;
import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.dialogs.AttributeOfChannelDialog;
import net.foreworld.rss2.update.MyTask4;
import net.foreworld.rss2.update.UpdateJob;
import net.foreworld.rss2.update.WorkQueue;
import net.foreworld.rss2.utils.MyRcpUtil;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

/**
 * 
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 11:18:04 AM
 */
public class ChannelNavigatorView extends AbstractTreeViewPart {
	/*
	 * ����ͼ��������
	 */
	private class TreeViewMouseListener implements MouseListener {
		public void mouseDoubleClick(MouseEvent arg0) {
		}
		public void mouseDown(MouseEvent arg0) {
		}
		public void mouseUp(MouseEvent arg0) {
			//3�����Ҽ�
			if(arg0.button == 3){				
				treeViewer.getControl().getMenu().setVisible(arg0.data == null ? true : true);
			}
		}
	}

	public class AttributeOfChannelAction extends Action implements ISelectionListener{
		public AttributeOfChannelAction(){
			this.setText("Ƶ������(&A)@Alt+Enter");
			this.setImageDescriptor(Activator.getImageDescriptor("icons/QuickReader_383.gif"));
			getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		}
		public void run(){
			AttributeOfChannelDialog dialog = new AttributeOfChannelDialog(getShell());
			dialog.open();
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			Object o = getFirstElement();
			if(o instanceof Element){
				Element outline = (Element)o;
				this.setEnabled(outline.attribute("type") != null);
			}
			else{
				this.setEnabled(false);
			}
		}
	}

	private class ExportChannelListAction extends Action {
		public ExportChannelListAction(){
			this.setText("����Ƶ���б�(&E)");
		}
		public void run(){
		}
	}

	private class ImportChannelListAction extends Action {
		public ImportChannelListAction(){
			this.setText("����Ƶ���б�(&I)");
		}
		public void run(){
		}
	}

	private class DeleteAction extends Action implements ISelectionListener{
		public DeleteAction(){
			this.setText("ɾ��(&D)");
			this.setImageDescriptor(Activator.getImageDescriptor("icons/delete_obj.gif"));
			getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		}
		public void run(){
			if(MessageDialog.openConfirm(getShell(), Platform.getProduct().getName(), "��ȷ��Ҫִ��ɾ��������")){
				delOutline();
			}
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			if(arg1 instanceof IStructuredSelection){
				if(arg0 instanceof ChannelNavigatorView){
					this.setEnabled(((IStructuredSelection)arg1).size() == 1);
				}
			}
		}
	}

	private class TreeLabelProvider implements ITableLabelProvider,IFontProvider {
		public Image getColumnImage(Object arg0, int arg1) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				switch(arg1){
				case 0:
					if(outline.attribute("status") != null && outline.attributeValue("status").equals("running")){
						return CacheImage.getInstance().getImage("icons/update.gif");
					}
					if(outline.attribute("status") != null && outline.attributeValue("status").equals("waiting")){
						return CacheImage.getInstance().getImage("icons/channel.ico");
					}
					if(outline.attribute("type") == null){
						return CacheImage.getInstance().getImage("icons/folder.gif");
					}
					return CacheImage.getInstance().getImage("icons/channel.ico");
				}
			}
			return null;
		}
		public String getColumnText(Object arg0, int arg1) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				switch(arg1){
				case 0:
					if(outline.attribute("type") == null){
						return outline.attributeValue("title");
					}
					return outline.attributeValue("title") + "("+ outline.attributeValue("unReadCount") +"/"+ outline.attributeValue("itemCount") +")";
				}
			}
			return null;
		}
		public void addListener(ILabelProviderListener arg0) {
		}
		public void dispose() {
		}
		public boolean isLabelProperty(Object arg0, String arg1) {
			return false;
		}
		public void removeListener(ILabelProviderListener arg0) {
		}
		public Font getFont(Object arg0) {
			Element outline = (Element)arg0;
			if(outline.attribute("type") != null){
				if(outline.attributeValue("itemCount").equals("0")){
					return null;
				}
				if(outline.attributeValue("unReadCount").equals("0")){
					return null;
				}
				return new Font(null,"����",9,SWT.BOLD);
			}
			return null;
		}
	}

	private class TreeContentProvider implements ITreeContentProvider {
		public Object[] getChildren(Object arg0) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				return outline.elements("outline").toArray();				
			}
			return new Object[0];
		}
		public Object getParent(Object arg0) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				return outline.getParent();
			}
			return null;
		}
		public boolean hasChildren(Object arg0) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				List list = outline.elements("outline");
				if(list == null)
					return false;
				return list.size() > 0;
			}
			return false;
		}
		public Object[] getElements(Object arg0) {
			return this.getChildren(arg0);
		}
		public void dispose() {
		}
		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		}
	}

	private class CloseAllChannelGroupAction extends Action {
		public CloseAllChannelGroupAction(){
			this.setText("��������Ƶ����(&O)");
		}
		public void run(){
			treeViewer.collapseAll();
		}
	}

	private class OpenAllChannelGroupAction extends Action {
		public OpenAllChannelGroupAction(){
			this.setText("չ������Ƶ����(&X)");
		}
		public void run(){
			treeViewer.expandAll();
		}
	}

	private class SignAllUnReadAction extends Action {
		public SignAllUnReadAction(){
			this.setText("ȫ�����Ϊδ��(&U)");
		}
		public void run(){
			signOutline(false);
		}
	}

	private class SignAllReadAction extends Action{
		public SignAllReadAction(){
			this.setText("ȫ�����Ϊ�Ѷ�(&Y)");
		}
		public void run(){
			signOutline(true);
		}
	}

	private class ClearChannelContentAction extends Action{
		public ClearChannelContentAction(){
			this.setText("���Ƶ������(&L)");
		}
		public void run(){
			cleanChannelItem();
		}
	}

	private class ReNameChannelAction extends Action implements ISelectionListener{
		public ReNameChannelAction(){
			this.setText("������(&R)@F2");
			getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		}
		public void run(){
			treeViewer.setColumnProperties(new String[]{"title"});
			treeViewer.editElement(((IStructuredSelection)treeViewer.getSelection()).getFirstElement(), 0);
		}

		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			if(arg1 instanceof IStructuredSelection){
				this.setEnabled(((IStructuredSelection)arg1).size() == 1);
			}
		}
	}

	private class UpdateChannelAction extends Action {
		public UpdateChannelAction(){
			this.setText("����(&U)");
			this.setImageDescriptor(Activator.getImageDescriptor("/icons/update.gif"));
		}
		public void run(){
			Object o = getFirstElement();
			Iterator it = null;
			//û��ѡ���κ�Ƶ���鼰Ƶ��
			if(o == null){
				it = RssXml2.getElements(doc, "//outline[@type='rss']").iterator();
				while(it.hasNext()){
					workQueue.execute(new MyTask4((Element)it.next()));
				}
			}	
			else if(o instanceof Element){
				Element outline = (Element)o;
				//�ж������Ƶ���飬�������֮�µ�ȫ��Ƶ�����и���
				if(outline.attribute("type") == null){
					it = RssXml2.getElements(doc, outline.getPath()+"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']").iterator();
					while(it.hasNext()){
						workQueue.execute(new MyTask4((Element)it.next()));
					}
				}
				else{
					workQueue.execute(new MyTask4(outline));
				}
			}
		}
	}

	private UpdateChannelAction updateChannelAction;
	private DeleteAction deleteAction;
	
	private IWorkbenchAction newChannelAction;
	private IWorkbenchAction newChannelGroupAction;
	private ReNameChannelAction reNameChannelAction;
	
	private ClearChannelContentAction clearChannelContentAction;
	private SignAllReadAction signAllReadAction;
	private SignAllUnReadAction signAllUnReadAction;
	
	private OpenAllChannelGroupAction openAllChannelGroupAction;
	private CloseAllChannelGroupAction closeAllChannelGroupAction;
	
	private ImportChannelListAction importChannelListAction;
	private ExportChannelListAction exportChannelListAction;

	private IWorkbenchAction channelAttributeAction;
	
	//����label
	private Label fSeparator;
	
	/***************************************************************************************************/
	private WorkQueue workQueue = null;
	public static final String ID = ChannelNavigatorView.class.getName();
	
	private Document doc;

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public void createPartControl(Composite arg0) {
		//���ù������У�ÿ��ִ������
		this.workQueue = new WorkQueue(2);
		//
		this.doc = RssXml2.getDocumentByXmlName(Messages.ChannelNavigator_Name);
		//�趨ÿ��Ƶ���ĸ���ʱ��
		Iterator it = RssXml2.getElements(this.doc, "//outline[@type='rss']").iterator();
		Date date = new Date();
		while(it.hasNext()){
			Element outline = (Element)it.next();
			date.setSeconds(date.getSeconds() + Integer.parseInt(outline.attributeValue("updateTime"))/1000);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(outline.attribute("updateNextTime") == null){
				outline.addAttribute("updateNextTime", df.format(date));
			}
			else{
				outline.attribute("updateNextTime").setText(df.format(date));
			}
		}
		/*
		 * ����Job ����ÿ��Ƶ����ʱ�������и���
		 * ����ǰʱ��>Ƶ���ĸ���ʱ��ʱ ����������
		 */
		UpdateJob uj = new UpdateJob(this.doc,this.workQueue);
		uj.setSystem(true);
		uj.schedule();
		
		//��������ͼ����������ʽ
		this.treeViewer = new TreeViewer(arg0, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		//�����ṩ��
		this.treeViewer.setContentProvider(new TreeContentProvider());
		//��ʾ�ṩ��
		this.treeViewer.setLabelProvider(new TreeLabelProvider());
		//д������
		this.treeViewer.setInput(RssXml2.getElement(this.doc, "//body"));		
		//��Ӽ�����
		this.getSite().setSelectionProvider(this.treeViewer);
		//Ĭ��չ����
		this.treeViewer.expandAll();	
		//�������Ĺ���ʵ��
		this.reNameChannel();
		//�������Ҽ�����
		this.treeViewer.getTree().addMouseListener(new TreeViewMouseListener());
		//���ѡ��ı����
		this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent arg0) {
				Object o = ((StructuredSelection)arg0.getSelection()).getFirstElement();
				if(o instanceof Element){
					Element outline = (Element)o;
					System.out.println("--ѡ��ı䣺"+ outline);
					//�ı�״̬����ʾ
					MyRcpUtil.setStatusLine(outline.attributeValue("title"));
				}
			}
		});
		/**
		 * �����ͼ����ͼֱ�ӵļ���
		 */
		this.getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(new ISelectionListener(){
			public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
				if(arg1 instanceof IStructuredSelection){
					IStructuredSelection incoming = (IStructuredSelection)arg1;
					/**
					 * ����������ϸ�б����ͼ
					 */
					if(arg0 instanceof DetailListView){
						if(incoming.size() == 1){
							/**
							 * ���Ȼ�ȡ��ϸ�б���ͼ��ѡ�еĶ���
							 * ������Ҫ��Ϊ�˸���Ƶ����δ����
							 * ��Ҫ������ϸ�б���ͼ��addSelectionChangedListener������֧��
							 * Ϊʲô��Ҫ��ô����ԭ���ǰ��ռ������Ⱥ�˳�������
							 * ��Ҫ��ִ�ж�Ƶ������ѡ���δ�����Ե��޸ģ�Ȼ����Ҫ������
							 * ˢ������ͼ�Ĺ�����Ҫ���ڱ���ִ�У���Ϊ����ϸ�б����л�ȡ��������
							 * ��������ȡ����ͼ��ѡ�еĶ��󣬾���ͨ���ö����ȡ��Ƶ���б�
							 * Ȼ���жϸö����ͷ��ܹ�ת����Element�����ˢ�¸ö���
							 */
							Object o = ((StructuredSelection)arg1).getFirstElement();
							if(o instanceof Element){
								Element item = (Element)o;
								//��ȡδ���������޸���
//								setUnReadCount(item.selectNodes("//item[@read='false']").size());
							}
						}
					}
				}
			}
		});
		
	    /**
	     * ˫���ڵ�չ���رչ���
	     */
	    this.treeViewer.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent arg0) {
				treeViewer.setExpandedState(getFirstElement(), !treeViewer.getExpandedState(getFirstElement()));
			}
	    });
		
		//ע������ҳ
		Platform.getAdapterManager().registerAdapters(new XmlElementAdapterFactory(), Element.class);
		
		//��������ҳ
//		this.createSearchBar(arg0);
		
		
		super.createPartControl(arg0);
	}
	
	/**
	 * ����ҳ
	 * @param parent
	 */
	private void createSearchBar(final Composite parent){
		this.fSeparator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
	    this.fSeparator.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	}

	//�������Ĺ���ʵ��
	private void reNameChannel() {
		this.treeViewer.setCellEditors(new CellEditor[]{new TextCellEditor(this.treeViewer.getTree())});
		
		this.treeViewer.setCellModifier(new ICellModifier(){
			public boolean canModify(Object arg0, String arg1) {
				return arg1.equals("title");
			}
			public Object getValue(Object arg0, String arg1) {
				if(arg0 instanceof Element && arg1.equals("title")){
					Element outline = (Element)arg0;
					return outline.attributeValue("title");
				}
				return null;
			}
			public void modify(Object arg0, String arg1, Object arg2) {
				if(arg0 instanceof Item){
					Item item = (Item)arg0;
					Object o = item.getData();
					if(o instanceof Element){
						Element outline = (Element)o;
						String newTitle = ((String)arg2).trim();
						//�ж���¼��ı����Ƿ�Ϊ�գ����Ϊ�շ���
						if(newTitle.equals("")){
							treeViewer.setColumnProperties(new String[]{"noTitle"});
							return;
						}
						//��ʼ�������ˣ��޸�xml���޸Ķ���
						if(!outline.attributeValue("title").equals(newTitle)){
							//�޸�Ƶ������
							outline.attribute("title").setText(newTitle);
							RssXml2.saveXmlFile(doc, Messages.ChannelNavigator_Name);
							refresh();
						}
					}
				}
				treeViewer.setColumnProperties(new String[]{"noTitle"});
			}
		});
		this.treeViewer.setColumnProperties(new String[]{"noTitle"});
	}

	
	public void setFocus() {
	}
	
	/******************************************************************************************/
	/**
	 * ����δ����
	 * @param item
	 */
	public void setUnReadCount(int unReadCount){
		//��ȡ������ͼ��ѡ�еĶ���
		Object o = ((StructuredSelection)treeViewer.getSelection()).getFirstElement();
		if(o instanceof Element){
			Element outline = (Element)o;
			//��ȡδ���������޸���
			outline.attribute("unReadCount").setText(Integer.toString(unReadCount));
			//������
			RssXml2.saveXmlFile(this.doc, Messages.ChannelNavigator_Name);
			this.refresh();
		}
	}
	
	/**
	 * ����δ������������
	 * @param unReadCount
	 * @param itemCount
	 */
	public void setUnReadCountAndItemCount(int unReadCount,int itemCount){
		//��ȡ������ͼ��ѡ�еĶ���
		Object o = ((StructuredSelection)treeViewer.getSelection()).getFirstElement();
		if(o instanceof Element){
			Element outline = (Element)o;
			outline.attribute("unReadCount").setText(Integer.toString(unReadCount));
			outline.attribute("itemCount").setText(Integer.toString(itemCount));
			//������
			RssXml2.saveXmlFile(this.doc, Messages.ChannelNavigator_Name);
			this.refresh();
		}
	}

	
	protected void createAction() {
		this.updateChannelAction = new UpdateChannelAction();
		this.deleteAction = new DeleteAction();
		
		this.newChannelAction = RSS2ActionFactory.NEW_CHANNEL.create(this.getWorkbenchWindow());
		this.newChannelGroupAction = RSS2ActionFactory.NEW_CHANNELGROUP.create(this.getWorkbenchWindow());
		this.reNameChannelAction = new ReNameChannelAction();
		
		this.clearChannelContentAction = new ClearChannelContentAction();
		this.signAllReadAction = new SignAllReadAction();
		this.signAllUnReadAction = new SignAllUnReadAction();
		
		this.openAllChannelGroupAction = new OpenAllChannelGroupAction();
		this.closeAllChannelGroupAction = new CloseAllChannelGroupAction();
		
		this.importChannelListAction = new ImportChannelListAction();
		this.exportChannelListAction = new ExportChannelListAction();

		this.channelAttributeAction = RSS2ActionFactory.CHANNEL_ATTRIBUTE.create(this.getWorkbenchWindow());
	}

	
	protected void createContextMenu(MenuManager menuManager) {
        menuManager.add(this.updateChannelAction);
        menuManager.add(this.deleteAction);
        menuManager.add(new Separator());
        
        menuManager.add(this.newChannelAction);
        menuManager.add(this.newChannelGroupAction);
        menuManager.add(this.reNameChannelAction);
        menuManager.add(new Separator());
        
        menuManager.add(this.clearChannelContentAction);
        menuManager.add(this.signAllReadAction);
        menuManager.add(this.signAllUnReadAction);
        menuManager.add(new Separator());
        
        menuManager.add(this.openAllChannelGroupAction);
        menuManager.add(this.closeAllChannelGroupAction);
        menuManager.add(new Separator());
        
        menuManager.add(this.importChannelListAction);
        menuManager.add(this.exportChannelListAction);
        menuManager.add(new Separator());

        menuManager.add(this.channelAttributeAction);		
	}

	
	protected void createMenu(IMenuManager menu) {
        menu.add(this.updateChannelAction);
        menu.add(this.deleteAction);
        menu.add(new Separator());
        
        menu.add(this.newChannelAction);
        menu.add(this.newChannelGroupAction);
        menu.add(this.reNameChannelAction);
        menu.add(new Separator());
        
        menu.add(this.clearChannelContentAction);
        menu.add(this.signAllReadAction);
        menu.add(this.signAllUnReadAction);
        menu.add(new Separator());
        
        menu.add(this.openAllChannelGroupAction);
        menu.add(this.closeAllChannelGroupAction);
        menu.add(new Separator());
        
        menu.add(this.importChannelListAction);
        menu.add(this.exportChannelListAction);
        menu.add(new Separator());

        menu.add(this.channelAttributeAction);
	}

	
	protected void createToolBar(IToolBarManager toolBar) {
		toolBar.add(this.deleteAction);
	}
	
	/**
	 * ɾ��Ԫ�أ�ͬ����Ҫɾ���ļ�
	 */
	public void delOutline(){
		Object o = this.getFirstElement();
		if(o == null) return;
		Element outline = (Element)o;
		if(outline.attribute("type") == null){//Ƶ����
			//****��ȡĳ��Ԫ���µ�����Ƶ��������Ƚ��ѣ���Ҫ�ǰ�xpath����д�Ծ�ok�ˣ�����2��Сʱ������������д�ԣ��ڼ�����jdom����ûdom4j������
			Iterator it = outline.selectNodes(outline.getPath()+"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']").iterator();
			while(it.hasNext()){
				Element ele = (Element)it.next();
				RssXml2.delXmlFile(ele.attributeValue("id"));
			}
		}
		else{
			RssXml2.delXmlFile(outline.attributeValue("id"));
		}
		//��ȡ��Ԫ��
		Element parentOutline = outline.getParent();
		//ͨ����Ԫ��ɾ����ǰѡ�е�Ԫ��
		parentOutline.remove(outline);
		//����xml�ĵ�
		this.saveXmlFile();
		//��λ����Ԫ��
		this.setSelection(parentOutline);
		//ˢ�¸�Ԫ��
		this.refresh();
	}
	
	/**
	 * ���
	 * @param read
	 */
	public void signOutline(boolean read){
		Object o = this.getFirstElement();
		Iterator it = null;
		if(o == null){
			it = RssXml2.getElements(this.doc, "//outline[@type='rss']").iterator();
		}
		else if(o instanceof Element){
			Element outline = (Element)o;
			if(outline.attribute("type") == null){
				it = RssXml2.getElements(this.doc, outline.getPath()+"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']").iterator();
			}
			else{
				it = RssXml2.getElements(this.doc, "//outline[@id='"+ outline.attributeValue("id") +"']").iterator();
			}
		}
		
		while(it.hasNext()){
			Element ele = (Element)it.next();

			Document channelDoc = RssXml2.getDocumentByXmlName(ele.attributeValue("id"));
			Iterator it2 = RssXml2.getElements(channelDoc, "//item[@read='"+ (read ? "false" : "true") +"']").iterator();
			while(it2.hasNext()){
				Element ele2 = (Element)it2.next();
				ele2.attribute("read").setText(read ? "true" : "false");
			}
			RssXml2.saveXmlFile(channelDoc, ele.attributeValue("id"));

			ele.attribute("unReadCount").setText(read ? "0" : ele.attributeValue("itemCount"));
		}
		
		this.saveXmlFile();

		this.refresh();
		this.setSelection();
	}
	
	/**
	 * ����xml�ĵ�
	 */
	public void saveXmlFile(){
		RssXml2.saveXmlFile(this.doc, Messages.ChannelNavigator_Name);
	}
	
	/**
	 * ���Ƶ������
	 */
	public void cleanChannelItem(){
		Object o = this.getFirstElement();
		Iterator it = RssXml2.getRssChannelList(this.doc, o).iterator();
		
		while(it.hasNext()){
			Element ele = (Element)it.next();

			Document channelDoc = RssXml2.getDocumentByXmlName(ele.attributeValue("id"));
			Iterator it2 = RssXml2.getElements(channelDoc, "//item").iterator();
			while(it2.hasNext()){
				Element ele2 = (Element)it2.next();
				ele2.getParent().remove(ele2);
			}
			RssXml2.saveXmlFile(channelDoc, ele.attributeValue("id"));

			ele.attribute("unReadCount").setText("0");
			ele.attribute("itemCount").setText("0");
		}
		
		RssXml2.saveXmlFile(this.doc, Messages.ChannelNavigator_Name);

		this.refresh();
		this.setSelection();
	}
	
//	/**
//	 * ��ȡ��ִ�е�Ƶ��
//	 * @return
//	 */
//	public List getSelectionList(){
//		Object o = this.getFirstElement();
//		List list = null;
//		if(o == null){
//			list = RssXml2.getElements(this.doc, "//outline[@type='rss']");
//		}
//		else if(o instanceof Element){
//			Element outline = (Element)o;
//			if(outline.attribute("type") == null){
//				list = RssXml2.getElements(this.doc, outline.getPath()+"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']");
//			}
//			else{
//				list = RssXml2.getElements(this.doc, "//outline[@id='"+ outline.attributeValue("id") +"']");
//			}
//		}
//		return list;
//	}
	
	protected void hookGlobalAction(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(IWorkbenchActionConstants.DELETE, this.deleteAction);
		actionBars.setGlobalActionHandler(IWorkbenchActionConstants.PROPERTIES, this.channelAttributeAction);
	}

}
