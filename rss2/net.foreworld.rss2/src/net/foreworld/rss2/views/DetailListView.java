package net.foreworld.rss2.views;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import net.foreworld.rss2.Activator;
import net.foreworld.rss2.CacheImage;
import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.utils.MyRcpUtil;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.progress.UIJob;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 11:53:35 AM
 */
public class DetailListView extends AbstractTableViewPart {

	private class MyUIJob extends UIJob{
		private Element item;
		public MyUIJob(Element item) {
			super("");
			this.item = item;
		}
		public IStatus runInUIThread(IProgressMonitor arg0) {
			//修改读和未读的状态
			if(this.item.attributeValue("read").equals("false")){
				//先修改频道的属性
				this.item.attribute("read").setText("true");
				//保存频道文件
				saveFile();
				//刷新本视图
				refresh();
				//
				ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
				channelNavigatorView.setUnReadCount(this.item.selectNodes("//item[@read='false']").size());
			}
			return Status.OK_STATUS;
		}
		public boolean belongsTo(Object family){
			return family == MYUIJOBFAMILY;
		}
	}

	private class TableViewMouseListener implements MouseListener{
		public void mouseDoubleClick(MouseEvent arg0) {
		}
		public void mouseDown(MouseEvent arg0) {
		}
		public void mouseUp(MouseEvent arg0) {
			//3代表右键
			if(arg0.button == 3){				
				tableViewer.getControl().getMenu().setVisible(!getSelection().isEmpty());
			}
		}
	}

	private class DeleteAction extends Action implements IAction,ISelectionListener{
		public DeleteAction(){
			this.setText("删除(&D)");
			this.setEnabled(false);
			this.setImageDescriptor(Activator.getImageDescriptor("icons/delete_obj.gif"));
			window.getSelectionService().addSelectionListener(this);
		}
		public void run(){
			//取消线程
			MyUIJob.getJobManager().cancel(MYUIJOBFAMILY);
			Element item = (Element)getFirstElement();
			item.getParent().remove(item);
			//获取项列表
			List itemList = getItemList();
			int size = itemList.size();
			//重设数据源
			tableViewer.setInput(size == 0 ? null : itemList);
			//显示网格线
			table.setLinesVisible(size > 0);
			saveFile();
			ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
			channelNavigatorView.setUnReadCountAndItemCount(getUnReadCount(), size);
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			if(arg1 instanceof IStructuredSelection){
				if(arg0 instanceof DetailListView){
					this.setEnabled(((IStructuredSelection)arg1).size() == 1);
				}
			}
		}
	}

	private class ToFriendAction extends Action implements ISelectionListener{
		public ToFriendAction(){
			this.setText("推荐给好友(&M)");
			this.setEnabled(false);
			window.getSelectionService().addSelectionListener(this);
		}
		public void run(){
			Element item = (Element)getFirstElement();
			Properties properties = System.getProperties();
			String osName = properties.getProperty("os.name");
			if(osName.indexOf("Windows") != -1){
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler mailto:?subject="+ item.elementText("title") +"&body="+ item.elementText("description"));
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			if(arg1 instanceof IStructuredSelection){
				if(arg0 instanceof DetailListView){
					this.setEnabled(((IStructuredSelection)arg1).size() == 1);
				}
			}
		}
	}

	private class AddToFavoritesAction extends Action {
		public AddToFavoritesAction(){
			this.setText("添加到收藏夹(&F)");
		}
		public void run(){
			System.out.println(this.getText());
		}
	}

	private class SignAllUnReadAction extends Action {
		public SignAllUnReadAction(){
			this.setText("全部标记为未读(&N)");
		}
		public void run(){
			signAllItemRead(false);
			//
			ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
			channelNavigatorView.setUnReadCount(channelDoc.selectNodes("//item").size());
		}
	}

	private class SignAllReadAction extends Action {
		public SignAllReadAction(){
			this.setText("全部标记为已读(&Y)");
		}
		public void run(){
			signAllItemRead(true);
			//
			ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
			channelNavigatorView.setUnReadCount(0);
		}
	}

	private class SignUnReadAction extends Action implements ISelectionListener{
		public SignUnReadAction(){
			this.setText("标记为未读(&U)");
			this.setEnabled(false);
			window.getSelectionService().addSelectionListener(this);
		}
		public void run(){
			Element item = (Element)getFirstElement();
			item.attribute("read").setText("false");
			saveFile();
			tableViewer.refresh(item);
			//
			ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
			channelNavigatorView.setUnReadCount(getUnReadCount());
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			if(arg1 instanceof IStructuredSelection){
				if(arg0 instanceof DetailListView){
					this.setEnabled(((IStructuredSelection)arg1).size() == 1);
				}
			}
		}
	}

	private class SignReadAction extends Action {
		public SignReadAction(){
			this.setText("标记为已读(&R)");
			this.setEnabled(false);
		}
		public void run(){
		}
	}

	private class OpenFromIEAction extends Action implements ISelectionListener{
		public OpenFromIEAction(){
			this.setText("在外部浏览器中打开(&O)");
			this.setEnabled(false);
			window.getSelectionService().addSelectionListener(this);
		}
		public void run(){
			Element item = (Element)getFirstElement();
			Properties properties = System.getProperties();
			String osName = properties.getProperty("os.name");
			if(osName.indexOf("Windows") != -1){
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ item.elementText("link"));
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			if(arg1 instanceof IStructuredSelection){
				if(arg0 instanceof DetailListView){
					this.setEnabled(((IStructuredSelection)arg1).size() == 1);
				}
			}
		}
	}

	private class OpenNewIEViewAction extends Action implements ISelectionListener{
		public OpenNewIEViewAction(){
			this.setText("在新浏览器中打开(&B)");
			this.setEnabled(false);
			window.getSelectionService().addSelectionListener(this);
		}
		public void run(){
			Object o = getFirstElement();
			if(o instanceof Element){
				Element item = (Element)o;
				IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IEView iEView = (IEView)workbenchPage.findView(IEView.ID);
				iEView.getBrowser().setUrl(item.elementText("link").trim());
			}
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			if(arg1 instanceof IStructuredSelection){
				if(arg0 instanceof DetailListView){
					this.setEnabled(((IStructuredSelection)arg1).size() == 1);
				}
			}
		}
	}

	private class TableContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object arg0) {
			if(arg0 instanceof List){
				return ((List)arg0).toArray();
			}		
			return null;
		}
		public void dispose() {
		}
		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		}
	}

	private class TableLabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object arg0, int arg1) {
			if(arg0 instanceof Element){
				Element item = (Element)arg0;
				switch(arg1){
				case 0:
					if(item.attributeValue("read").equals("true"))
						return CacheImage.getInstance().getImage("icons/ico_readed.gif");
					return CacheImage.getInstance().getImage("icons/ico_unread.gif");
				}
			}
			return null;
		}
		public String getColumnText(Object arg0, int arg1) {
			if(arg0 instanceof Element){
				Element item = (Element)arg0;
				switch(arg1){
				case 1:
					return item.element("title") == null ? "" : item.elementText("title").trim();
				case 2:
					return item.element("pubDate") == null ? "" : item.elementText("pubDate").trim();
				case 3:
					return item.element("author") == null ? "" : item.elementText("author").trim();
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
	}
	
	private OpenNewIEViewAction openNewIEViewAction;
	private OpenFromIEAction openFromIEAction;

	private SignReadAction signReadAction;
	private SignUnReadAction signUnReadAction;
	private SignAllReadAction signAllReadAction;
	private SignAllUnReadAction signAllUnReadAction;
	
	private AddToFavoritesAction addToFavoritesAction;
	private ToFriendAction toFriendAction;
	
	private DeleteAction deleteAction;

	/***************************************************************************************************/
	private final static Object MYUIJOBFAMILY = new Object();
	public static final String ID = DetailListView.class.getName();
//	public TableViewer viewer;
	private IWorkbenchWindow window;
	private Table table;
	private Document channelDoc;
	private Element outline;

	
	public void createPartControl(Composite arg0) {
		this.window = this.getViewSite().getWorkbenchWindow();
		// 创建树视图，定义树样式
		this.tableViewer = new TableViewer(arg0, SWT.MULTI | SWT.FULL_SELECTION);
		this.table = this.tableViewer.getTable();
		/**
		 * 设置表的行高和宽度
		 * 向表格增加一个SWT.MeasureItem监听器，每当需要单元内容的大小时就会被调用
		 */
		this.table.addListener(SWT.MeasureItem, new Listener(){
			public void handleEvent(Event arg0) {
				/**
				 * 设置宽度
				 */
				arg0.width = table.getGridLineWidth();
				/**
				 * 设置高度为字体高度的1.5倍，也可以自定义设置
				 */
				arg0.height = (int)Math.floor(arg0.gc.getFontMetrics().getHeight() * 1.5);
			}
		});

		this.createTable();

		//添加鼠标右键事件
		this.table.addMouseListener(new TableViewMouseListener());
		
		//注册属性页
		Platform.getAdapterManager().registerAdapters(new XmlElementAdapterFactory(), Element.class);
		
		super.createPartControl(arg0);
	}

	private void createTable() {
		TableColumn column = new TableColumn(this.table, SWT.LEFT);
        column.setText("");
        column.setWidth(30);
        column.setToolTipText("");
        column.setMoveable(false);
        
        column = new TableColumn(this.table,SWT.LEFT);
        column.setText("标题");
        column.setWidth(450);
        column.setToolTipText("标题");
        column.setMoveable(true);
        
        column = new TableColumn(this.table,SWT.LEFT);
        column.setText("发布时间");
        column.setWidth(126);
        column.setToolTipText("发布时间");
        column.setMoveable(true);
        this.table.setSortColumn(column);

        column = new TableColumn(this.table,SWT.LEFT);
        column.setText("作者");
        column.setWidth(150);
        column.setToolTipText("作者");
        column.setMoveable(true);
		
        this.table.setHeaderVisible(true);
//        this.table.setLinesVisible(true);
        this.table.setSortDirection(SWT.DOWN);
        
		this.tableViewer.setContentProvider(new TableContentProvider());
		this.tableViewer.setLabelProvider(new TableLabelProvider());	
		this.tableViewer.setInput(null);
		/*
		 * 添加监听器
		 */
		this.getSite().setSelectionProvider(this.tableViewer);

		/**
		 * 添加选择改变监听
		 */
		this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent arg0) {
				Object o = ((StructuredSelection)arg0.getSelection()).getFirstElement();
				if(o instanceof Element){
					Element item = (Element)o;
					System.out.println("--选择改变："+ item);
					//改变状态栏提示
					MyRcpUtil.setStatusLine(item.elementText("title").trim());
//					//修改读和未读的状态 首先取消执行线程
					MyUIJob.getJobManager().cancel(MYUIJOBFAMILY);
					if(item.attributeValue("read").equals("false")){
						//1秒之后执行已读操作
						final MyUIJob job = new MyUIJob(item);
						job.schedule(1000);
					}
				}
			}
		});
		
		//添加视图之间的监听
		this.getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(new ISelectionListener(){
			public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
				if(arg1 instanceof IStructuredSelection){
					IStructuredSelection incoming = (IStructuredSelection)arg1;
					/**
					 * 如果监听到来自“频道导航”视图的消息
					 */
					if(arg0 instanceof ChannelNavigatorView){
						//取消执行线程的任务
						MyUIJob.getJobManager().cancel(MYUIJOBFAMILY);
						if(incoming.size() == 1){
							Object o = ((StructuredSelection)arg1).getFirstElement();
							outline = (Element)o;
							if(outline.attribute("type") == null){
								table.setLinesVisible(false);
								tableViewer.setInput(null);
								//设置标题名
								setPartName("RSS频道");
								return;
							}
							//根据属性id得到doc
							channelDoc = RssXml2.getDocumentByXmlName(outline.attributeValue("id"));
							//获取项列表
							List itemList = getItemList();
							//倒序排列
//							Collections.reverse(itemList);
							int size = itemList.size();
							//重设数据源
							tableViewer.setInput(size == 0 ? null : itemList);
							//显示网格线
							table.setLinesVisible(size > 0);
							//设置标题名
							setPartName(outline.attributeValue("title").trim());
						}
						else{//如果没有选中任何对象
							tableViewer.setInput(null);
							table.setLinesVisible(false);
							//设置标题名
							setPartName("RSS频道");
						}
					}
					/**
					 * 监听来自本视图的消息
					 */
					else if(arg0 instanceof DetailListView){
						if(incoming.size() == 1){
							//
						}
					}
				}
			}
		});
		
		// 双击监听事件
		this.tableViewer.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent arg0) {
				Object o = ((StructuredSelection)arg0.getSelection()).getFirstElement();
				if(o instanceof Element){
					Element item = (Element)o;
					IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IEView iEView = (IEView)workbenchPage.findView(IEView.ID);
					iEView.getBrowser().setUrl(item.elementText("link").trim());
				}
			}
		});
	}

	
	public void setFocus() {
	}

	/**
	 * 标记全部
	 * @param value
	 */
	public void signAllItemRead(boolean read){
		String value = read ? "false" : "true";
		Iterator it = this.channelDoc.selectNodes("//item[@read='"+ value +"']").iterator();
		while(it.hasNext()){
			Element item = (Element)it.next();
			item.attribute("read").setText(read ? "true" : "false");
		}
		saveFile();
		this.tableViewer.refresh();
	}
	
	/**
	 * 获取当前文件的名字
	 * @return
	 */
	private String getChannelName(){
		if(this.channelDoc == null) return "";
		return ((Element)this.channelDoc.selectSingleNode("//channel")).attributeValue("name");
	}
	
	/**
	 * 获取未读数
	 * @return
	 */
	public int getUnReadCount(){
		if(this.channelDoc == null) return 0;
		return this.channelDoc.selectNodes("//item[@read='false']").size();		
	}

	private List getItemList(){
		if(this.channelDoc == null) return null;
		List list = this.channelDoc.selectNodes("//item");
		Collections.reverse(list);
		return list;
	}
	
	/**
	 * 保存
	 */
	public void saveFile(){
		RssXml2.saveXmlFile(channelDoc, ((Element)channelDoc.selectSingleNode("//channel")).attributeValue("name"));
	}

	public Document getChannelDoc() {
		return channelDoc;
	}

	public void setChannelDoc(Document channelDoc) {
		this.channelDoc = channelDoc;
	}

	
	protected void createAction() {
		this.openNewIEViewAction = new OpenNewIEViewAction();
		this.openFromIEAction = new OpenFromIEAction();
		
		this.signReadAction = new SignReadAction();
		this.signUnReadAction = new SignUnReadAction();
		this.signAllReadAction = new SignAllReadAction();
		this.signAllUnReadAction = new SignAllUnReadAction();
		
		this.addToFavoritesAction = new AddToFavoritesAction();
		this.toFriendAction = new ToFriendAction();
		
		this.deleteAction = new DeleteAction();
	}

	
	protected void createContextMenu(MenuManager menuManager) {
        menuManager.add(this.openNewIEViewAction);
        menuManager.add(this.openFromIEAction);
        menuManager.add(new Separator());

        menuManager.add(this.signReadAction);
        menuManager.add(this.signUnReadAction);
        menuManager.add(this.signAllReadAction);
        menuManager.add(this.signAllUnReadAction);
        menuManager.add(new Separator());
        
        menuManager.add(this.addToFavoritesAction);
        menuManager.add(this.toFriendAction);
        menuManager.add(new Separator());
        
        menuManager.add(this.deleteAction);
	}

	
	protected void createMenu(IMenuManager menu) {
        menu.add(this.openNewIEViewAction);
        menu.add(this.openFromIEAction);
        menu.add(new Separator());

        menu.add(this.signReadAction);
        menu.add(this.signUnReadAction);
        menu.add(this.signAllReadAction);
        menu.add(this.signAllUnReadAction);
        menu.add(new Separator());
        
        menu.add(this.addToFavoritesAction);
        menu.add(this.toFriendAction);
        menu.add(new Separator());
        
        menu.add(this.deleteAction);
	}

	
	protected void createToolBar(IToolBarManager toolBar) {
		toolBar.add(this.deleteAction);
	}

	
	protected void hookGlobalAction(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(IWorkbenchActionConstants.DELETE,this.deleteAction);
	}

}
