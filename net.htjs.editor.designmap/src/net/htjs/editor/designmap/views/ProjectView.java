package net.htjs.editor.designmap.views;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.dialogs.AddFolderDialog;
import net.htjs.editor.designmap.dialogs.AddModuleDialog;
import net.htjs.editor.designmap.dialogs.AddPackageDialog;
import net.htjs.editor.designmap.dialogs.ImportDialog;
import net.htjs.editor.designmap.dialogs.RenameDialog;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

public class ProjectView extends ViewPart {
	
	/**
	 * 重命名
	 * @author hx
	 *
	 */
	public class RenameAction extends Action{
		public RenameAction(){
			this.setText("\u91cd\u547d\u540d(&R)");
		}
		
		public void run(){
			RenameDialog dialog = new RenameDialog(getSite().getShell());
			if(dialog.open() == 0){
				//焦点
//				channelNavigatorView.getTreeViewer().getControl().setFocus();
			}
		}
	}

	public class ImportAction extends Action {
		public ImportAction(){
			try {
				this.setText(new String("导入".getBytes(),"utf-8")+"(&F)");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setImageDescriptor(Activator.getImageDescriptor("icons/import.gif"));
		}
		
		public void run(){
			ImportDialog dialog = new ImportDialog(getSite().getShell());
			if(dialog.open() == 0){
				//焦点
//				channelNavigatorView.getTreeViewer().getControl().setFocus();
			}
		}
	}

	public class AddFolderAction extends Action implements ISelectionListener{
		public AddFolderAction(){
			try {
				this.setText(new String("添加目录".getBytes(),"utf-8")+"(&F)");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getViewSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
			this.setEnabled(false);
		}
		
		public void run(){
			AddFolderDialog dialog = new AddFolderDialog(getSite().getShell());
			if(dialog.open() == 0){
				//焦点
//				channelNavigatorView.getTreeViewer().getControl().setFocus();
			}
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			Object o = getFirstElement();
			if(o instanceof Element){
				Element outline = (Element)o;
				
				if("project".equals(outline.getName()) || "module".equals(outline.getName())){
					this.setEnabled(false);
				}
				else{
					this.setEnabled(true);				
				}
				
//				if("module".equals(outline.getName())){
//					this.setEnabled(false);
//				}
			}
		}
	}

	public class AddPackageAction extends Action implements ISelectionListener{
		public AddPackageAction(){
			this.setText("\u6dfb\u52a0\u5b50\u7cfb\u7edf(&F)");
			getViewSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
			this.setEnabled(false);
		}
		
		public void run(){
			AddPackageDialog dialog = new AddPackageDialog(getSite().getShell());
			if(dialog.open() == 0){
				//焦点
//				channelNavigatorView.getTreeViewer().getControl().setFocus();
			}
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			Object o = getFirstElement();
			if(o instanceof Element){
				Element outline = (Element)o;
				this.setEnabled("project".equals(outline.getName()));
			}
		}
	}

	public class EditAction extends Action {
		public EditAction(){
			try {
				this.setText(new String("修改".getBytes(),"utf-8")+"(&E)");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run(){
			GMFConsole.println(this.getText());
		}
	}

	public class DelAction extends Action implements ISelectionListener{
		public DelAction(){
			try {
				this.setText(new String("删除".getBytes(),"utf-8")+"(&D)");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setImageDescriptor(Activator.getImageDescriptor("icons/delete_obj.gif"));
			getViewSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
			this.setEnabled(false);
		}
		
		public void run(){
			Object o = getFirstElement();
			
	    	Element outline = null;
	    	
	    	if(o instanceof Element){//判断对象是否能转换成元素 您确定要执行删除操作吗？
	    		if(MessageDialog.openConfirm(getSite().getShell(), Platform.getProduct().getName(), "\u60a8\u786e\u5b9a\u8981\u6267\u884c\u5220\u9664\u64cd\u4f5c\u5417\uff1f")){

		    		outline = (Element)o;
					GMFConsole.println("+++"+outline.asXML());
					GMFConsole.println("+++"+outline.getParent().asXML());
	    		}
//	    		
//				try {
//					if(MessageDialog.openConfirm(getSite().getShell(), Platform.getProduct().getName(), new String("您确定要执行删除操作吗？".getBytes(),"utf-8"))){
//			    		//如果选中元素是频道，则
//			    		if(outline.getName().equals("module")){
////			    			RssXml2.delXmlFile(outline.attributeValue("containerName")+"/"+outline.attributeValue("fileName"));
//			    			delFile(outline.attributeValue("containerName"),outline.attributeValue("fileName"));
//			    		}
//			    		else{
//		    				//****获取某个元素下的所有频道，这个比较难，主要是把xpath条件写对就ok了，搞了2个小时终于在摸索中写对，期间试了jdom，还没dom4j好用呢
////		    				Iterator it = outline.selectNodes(outline.getPath()+"[@id='"+ outline.attributeValue("id") +"']//outline[@type='rss']").iterator();
////		    				while(it.hasNext()){
////		    					Element ele = (Element)it.next();
////		    					RssXml2.delXmlFile(ele.attributeValue("id"));
////		    				}
//			    			
//			    			Iterator it = outline.selectNodes(outline.getPath()+"[@name='"+ outline.attributeValue("name") +"']//module").iterator();
//		    				while(it.hasNext()){
//		    					Element ele = (Element)it.next();
//		    					delFile(ele.attributeValue("containerName"),ele.attributeValue("fileName"));
//		    				}
//			    		}
//
//		    			//获取父元素
//		    			Element parentOutline = outline.getParent();
//		    			//通过父元素删除当前选中的元素
//		    			parentOutline.remove(outline);
//		    			//保存xml文档
//		    			saveXmlFile();
//		    			//定位到父元素
//		    			setSelection(parentOutline);
//		    			//刷新父元素
//		    			refresh();
//						
//						GMFConsole.println("---"+outline.asXML());
//					}
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	    	}    	
		}
		
		private void delFile(String containerName,String fileName){
	    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IResource resource = root.findMember(new Path(containerName));
			if (!resource.exists() || !(resource instanceof IContainer)) {
//				throwCoreException("Container \"" + containerName + "\" does not exist.");
			}
			IContainer container = (IContainer) resource;
			final IFile file = container.getFile(new Path(fileName));
			try {
				file.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			Object o = getFirstElement();
			if(o instanceof Element){
				this.setEnabled(true);
				
			}
		}
	}

	public class AddModuleAction extends Action implements ISelectionListener{
		public AddModuleAction(){
			try {
				this.setText(new String("添加模块".getBytes(),"utf-8")+"(&M)");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getViewSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
			this.setEnabled(false);
		}
		
		public void run(){
			AddModuleDialog dialog = new AddModuleDialog(getSite().getShell());
			if(dialog.open() == 0){
				//焦点
//				channelNavigatorView.getTreeViewer().getControl().setFocus();
			}
		}
		public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
			Object o = getFirstElement();
			if(o instanceof Element){
				Element outline = (Element)o;
				if("project".equals(outline.getName()) || "module".equals(outline.getName())){
					this.setEnabled(false);
				}
				else{
					this.setEnabled(true);
				}
				
			}
		}

	}

	public class TreeLabelProvider implements ITableLabelProvider,
			IFontProvider {
		public Image getColumnImage(Object arg0, int arg1) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				switch(arg1){
				case 0:
					if(outline.getName().equals("project")){
						return Activator.getImageDescriptor("icons/projects.gif").createImage();
					}
					else if(outline.getName().equals("package")){
						return Activator.getImageDescriptor("icons/package_obj.gif").createImage();
					}
					else if(outline.getName().equals("module")){
						return Activator.getImageDescriptor("icons/prop_ps(2).gif").createImage();
					}
					return Activator.getImageDescriptor("icons/fldr_obj.gif").createImage();
				}
			}
			return null;
		}
		public String getColumnText(Object arg0, int arg1) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				if(outline.attribute("name2") != null){
					return outline.attributeValue("name2");
				}
				if(outline.attribute("name") != null){
					return outline.attributeValue("name");
				}
//				if(outline.attribute("name") == null){
//					return outline.getName();
//				}
//				if(outline.attribute("name2") == null){
//					return outline.attributeValue("name");
//				}
				return outline.attributeValue("name");
//				return outline.attributeValue("name2") +" "+ outline.attributeValue("name");
//				return outline.getName() +" name="+ outline.attributeValue("name");
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
//			Element outline = (Element)arg0;
//			if(outline.attribute("type") != null){
//				if(outline.attributeValue("itemCount").equals("0")){
//					return null;
//				}
//				if(outline.attributeValue("unReadCount").equals("0")){
//					return null;
//				}
//				return new Font(null,"宋体",9,SWT.BOLD);
//			}
			return null;
		}

	}

	public class TreeContentProvider implements ITreeContentProvider {
		public Object[] getChildren(Object arg0) {
			if(arg0 instanceof Element){
				Element outline = (Element)arg0;
				return outline.elements().toArray();				
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
				List list = outline.elements();
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

	protected TreeViewer treeViewer;
	public static final String ID = ProjectView.class.getName();

	private AddModuleAction addModuleAction;
	private AddPackageAction addPackageAction;
	private AddFolderAction addFolderAction;
//	private EditAction editAction;
	private DelAction delAction;
	private ImportAction importAction;
	private RenameAction renameAction;
	
	
	private IFile ifi;
	
	private Document doc;	
	
	
	private IProject project;
	
	public Document getDoc() {
		return doc;
	}
	
	public IProject getProject(){
		return this.project;
	}
	
	private String oldxml = "";
	

	public void createPartControl(Composite arg0) {

		this.treeViewer = new TreeViewer(arg0, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		this.treeViewer.setContentProvider(new TreeContentProvider());
		this.treeViewer.setLabelProvider(new TreeLabelProvider());
		
		this.getSite().getWorkbenchWindow().getPartService().addPartListener(new IPartListener(){

			public void partActivated(IWorkbenchPart arg0) {
				if("net.htjs.editor.designmap.views.ProjectView".equals(arg0.getClass().getName())){
					updateTree();
				}
			}
			public void partBroughtToTop(IWorkbenchPart arg0) {
			}
			public void partClosed(IWorkbenchPart arg0) {
			}
			public void partDeactivated(IWorkbenchPart arg0) {
			}
			public void partOpened(IWorkbenchPart arg0) {
			}			
		});
		
		this.getSite().setSelectionProvider(this.treeViewer);

//		this.getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(new ISelectionListener(){
//
//			public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
////				GMFConsole.println("selectionChanged");
//				if(arg0 instanceof PackageExplorerPart) {
//					
//					IStructuredSelection structured = (IStructuredSelection)arg1;
//								
//					
//					Object o = structured.getFirstElement();
//					
//					if(o instanceof IFile){
//						ifi = (IFile)o;
//						
////						GMFConsole.println(ifi.getProject().getFullPath());
//						project = ifi.getProject();
//						
//						String[] paths = ifi.getFullPath().toString().split("/");
//						
//						if(paths.length == 4 && "project.xml".equals(ifi.getName().trim())){
////							GMFConsole.println(ifi.getFullPath());	
//
//							try {
//								InputStream is = ifi.getContents();
////								GMFConsole.println(convertStreamToString(is));
//								String xml = convertStreamToString(is);
//								
//								try {
//									doc = DocumentHelper.parseText(xml);
////									GMFConsole.println("--"+d.getRootElement().asXML());
//									treeViewer.setInput(doc.getRootElement());
//								} catch (DocumentException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								
////								GMFConsole.println(xml);
//							} catch (CoreException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//				}
//			}
//			
//		});
		
		//添加选择改变监听
		this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent arg0) {
				Object o = ((StructuredSelection)arg0.getSelection()).getFirstElement();
				if(o instanceof Element){
					Element eeee = (Element)o;
					GMFConsole.println("--"+ eeee.asXML());
				}
			}
		});
		
		//双击树
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent arg0) {
				Object o = ((StructuredSelection)arg0.getSelection()).getFirstElement();
				if(o instanceof Element){
					Element outline = (Element)o;
//					GMFConsole.println("-----------"+outline.asXML());
//					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					
//					try {
//						IEditorPart ep = page.openEditor(null,
//								"net.htjs.editor.designmap.editors.DMMutiForm",true);
//					} catch (PartInitException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}

//					GMFConsole.println("+++"+outline.asXML());
					
					if(outline.getName().equals("package") || outline.getName().equals("project") || outline.getName().equals("folder")){
						treeViewer.setExpandedState(getFirstElement(), !treeViewer.getExpandedState(getFirstElement()));
						return;
					}
					
					if(!outline.getName().equals("module")){
						treeViewer.setExpandedState(getFirstElement(), !treeViewer.getExpandedState(getFirstElement()));
						return;
					}
					
//					GMFConsole.println("+++"+outline.attributeValue("containerName"));
//					GMFConsole.println("+++"+outline.attributeValue("resource"));
//					
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					IResource resource = root.findMember(new Path("/"+outline.attributeValue("projectName")));
					if (!resource.exists() || !(resource instanceof IContainer)) {
						GMFConsole.println("Container \"" + outline.attributeValue("containerName") + "\" does not exist.");
					}
					IContainer container = (IContainer) resource;
					
					GMFConsole.println("-------------------"+outline.attributeValue("resource"));
					final IFile file = container.getFile(new Path("/src"+outline.attributeValue("resource")));
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor(page, file, true);
					} catch (PartInitException e) {
					}
				}
			}			
		});
		
		//注册属性页
//		Platform.getAdapterManager().registerAdapters(new XmlElementAdapterFactory(), Element.class);
		
		
		/**
		 * 创建右键菜单
		 */
		if(this.treeViewer == null) return;
		this.createAction();
		Control control = this.treeViewer.getControl();
		MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(control);
        control.setMenu(menu);
		this.createContextMenu(menuManager);
        this.getSite().registerContextMenu(menuManager,this.treeViewer);
        
        this.createToolBar(this.getViewSite().getActionBars().getToolBarManager());
	}
	
	public void updateTree(){
		IProject[] ips = ResourcesPlugin.getWorkspace().getRoot().getProjects();

		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version='1.0' encoding='utf-8'?>");
		xml.append("<projects>");
		for(int i=0;i<ips.length;i++){
			IProject ip = ips[i];
			if(ip.isOpen()){
//				GMFConsole.println("--"+ip.getName());
				xml.append("<project name='"+ip.getName()+"'>");
				xml.append(createDMapping(ip.getName()));
				xml.append("</project>");
			}
		}
		xml.append("</projects>");
		
		if(!"".equals(oldxml)){
			if(oldxml.equals(xml.toString())) return;
		}
		
		oldxml = xml.toString();
		
		GMFConsole.println("----"+oldxml);
		
		try {
			
//			GMFConsole.println(xml.toString());
			doc = DocumentHelper.parseText(xml.toString());
			treeViewer.setInput(doc.getRootElement());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		GMFConsole.println(xml.toString());
	
	}
	
	private void createAction() {
		this.addModuleAction = new AddModuleAction();
		this.addPackageAction = new AddPackageAction();
		this.addFolderAction = new AddFolderAction();
//		this.editAction = new EditAction();
		this.delAction = new DelAction();
		this.importAction = new ImportAction();
		this.renameAction = new RenameAction();
	}
	
	private void createContextMenu(MenuManager menuManager) {
        menuManager.add(this.addPackageAction);
        menuManager.add(this.addFolderAction);
        menuManager.add(this.addModuleAction);
        menuManager.add(new Separator());
        menuManager.add(this.renameAction);
        menuManager.add(new Separator());
        menuManager.add(this.delAction);
	}
	
	protected void createToolBar(IToolBarManager toolBar) {
		toolBar.add(this.importAction);
		toolBar.add(this.delAction);
	}

	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 获取当前所选对象
	 * @return
	 */
	public Object getFirstElement(){
		return ((StructuredSelection)this.treeViewer.getSelection()).getFirstElement();
	}
	
	/**
	 * 刷新当前选中的对象
	 */
	public void refresh(){
		this.treeViewer.refresh(this.getFirstElement());
	}
	
	/**
	 * 保存xml文档
	 */
	public void saveXmlFile(){
//		GMFConsole.println(this.doc.asXML());
		ByteArrayInputStream in = null;
		try {
			in = new ByteArrayInputStream(this.doc.asXML().getBytes("Utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.ifi.setContents(in,0,null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 刷新全部视图
	 */
	public void refreshAll(){
		this.treeViewer.refresh();
	}
	
	/**
	 * 定位到指定对象
	 */
	public void setSelection(Object o){
		this.treeViewer.setSelection(new StructuredSelection(o),true);
	}
	
    public String convertStreamToString(InputStream is) {    
        /*   
         * To convert the InputStream to String we use the BufferedReader.readLine()   
         * method. We iterate until the BufferedReader return null which means   
         * there's no more data to read. Each line will appended to a StringBuilder   
         * and returned as String.   
         */   
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
        java.lang.StringBuffer sb = new java.lang.StringBuffer();    
     
        String line = null;    
        try {    
            while ((line = reader.readLine()) != null) {    
                sb.append(line + "\n");    
            }    
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
                e.printStackTrace();    
            }    
        }    
     
        return sb.toString();    
    }    
    
    private String createDMapping(String projectPath){
    	StringBuffer sb = new StringBuffer();
//    	sb.append("<package name='config.ysfpycrz.designs'/>");
//    	sb.append("<package name='config.clgl.designs'/>");
    	
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path("/"+projectPath+"/src"));
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path("design-mapping.dm"));
		if(!file.exists())
			return "";
		
		    	
//    	IProject[] ips = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    	
		try {
			InputStream is = file.getContents();
			String xml = convertStreamToString(is);
			
			try {
				Document doc1 = DocumentHelper.parseText(xml);
				
				List list = doc1.getRootElement().selectNodes("//import");
				
				for(int i=0;i<list.size();i++){
					Element ele = (Element)list.get(i);
					String packagename = ele.attributeValue("resource").replaceAll("/designs.dm", "").replace('/', '.').replaceFirst(".","");
					sb.append("<package name='"+packagename+"' projectName='"+projectPath+"'>");
					sb.append(this.createDMFile(projectPath,packagename,ele.attributeValue("resource")));
					sb.append("</package>");
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		
		} catch (CoreException e) {
			e.printStackTrace();
		}
    	
    	return sb.toString().replaceAll("<tree/>", "");
    }
    
    private String createDMFile(String projectPath,String packagename,String dmPath){
//    	GMFConsole.println("----"+ projectPath +"--"+ packagename +"--"+ dmPath);
    	
    	StringBuffer sb = new StringBuffer();
    	Document xmlRoot = null;
    	
    	try {
			xmlRoot = DocumentHelper.parseText("<tree></tree>");
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		GMFConsole.println("++++++"+xmlRoot.asXML());
    	
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path("/"+projectPath+"/src"));
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(dmPath));
		if(!file.exists()){
			return "";
		}
			
		try {
			InputStream is = file.getContents();
			String xml = convertStreamToString(is);
			
//			GMFConsole.println(xml);
			
			try {
				Document doc1 = DocumentHelper.parseText(xml);
				
//				if(doc1.getRootElement().selectSingleNode("tree") != null)
//					sb.append(doc1.getRootElement().selectSingleNode("tree").asXML().replaceAll("<tree>", "").replaceAll("</tree>", ""));
				
				List list = doc1.getRootElement().selectNodes("//import");
				
//				GMFConsole.println("import count:"+ list.size());
				
				Element parentele2 = null;
				
				//子系统下的模块数量
				//下一步需要对designs.dm进行解析 在Project视图中展现目录结构树
				//最后处理子系统、目录、模块的添加、修改、删除。
//		    	GMFConsole.println("----::"+ list.size());
				
			if(list != null){
				
				for(int i=0;i<list.size();i++){
					Element ele = (Element)list.get(i);
					
//					GMFConsole.println(ele.asXML());
					
					String str1 = dmPath.replaceAll("/designs.dm", "");
					String str2 = ele.attributeValue("resource").replaceAll(str1+"/", "");
					
					String[] str3 = str2.split("/");

//					Element ele2 = null;
					
					Element jjj = null;

//					GMFConsole.println(str1);
//					GMFConsole.println("::::::"+str2 +" "+ str3.length);
//					GMFConsole.println(str3);
//					
//					GMFConsole.println("----------------");

					
					//==1说明该模块在根目录，否则说明有模块在目录中
					if(str3.length == 1){
						sb.append("<module name2='"+ele.attributeValue("name2")+"' resource='"+ele.attributeValue("resource")+"' projectName='"+projectPath+"' packageName='/"+ packagename.replaceAll("\\.", "/") +"' name='"+ ele.attributeValue("resource").replaceAll("/"+ packagename.replaceAll("\\.", "/") +"/", "") +"'/>");
					}
					else{
						for(int j=0;j<str3.length-1;j++){
//							GMFConsole.println("+++"+str3[j]);
							
							Element ele2 = null;
							if(j==0){	
								if(xmlRoot.getRootElement().selectSingleNode("//folder[@fullPath='"+str3[j]+"']") == null){
//									ele2 = xmlRoot.getRootElement().addElement(str3[j]);
									ele2 = xmlRoot.getRootElement().addElement("folder");
									
									Element eleele = (Element)doc1.getRootElement().selectSingleNode("//translate[@name='/"+str3[j]+"']");
//									ele2.addAttribute("name", eleele.attributeValue("name2"));
//									ele2.addAttribute("name2", str3[j]);
//									ele2.addAttribute("fullpath", "/"+str3[j]);
//									ele2.addAttribute("containerName", "/"+projectPath);
//									ele2.addAttribute("packageName", "/"+packagename.replaceAll("\\.", "/"));
									
									//////////////////////

									ele2.addAttribute("name", str3[j]);
									ele2.addAttribute("name2", eleele.attributeValue("name2"));
									ele2.addAttribute("projectName", projectPath);
									ele2.addAttribute("packageName", "/"+ packagename.replaceAll("\\.", "/"));
									ele2.addAttribute("fullPath", "/"+str3[j]);
									
									parentele2 = ele2;
								}
							}
							else{
								String ss2 = "";
								for(int k=0;k<j+1;k++){
									ss2 = ss2 + "/" + str3[k];
								}
								String ss = "";
								for(int k=0;k<j;k++){
									ss = ss + "//folder[@fullPath='" + ss2 +"']";
								}
								
								GMFConsole.println("----"+ xmlRoot.asXML());
								
								Element parentElement = (Element)xmlRoot.getRootElement().selectSingleNode(ss);
								
//								GMFConsole.println(parentElement.asXML());
								
								if(parentElement == null){
//									GMFConsole.println(ss);


									GMFConsole.println("----"+ss);
									GMFConsole.println("----"+ss2);
									ele2 = parentElement.addElement("folder");
									
									Element eleele = (Element)doc1.getRootElement().selectSingleNode("//translate[@name='"+ss2+"']");
									ele2.addAttribute("name", str3[j]);
									ele2.addAttribute("name2", eleele.attributeValue("name2"));
									ele2.addAttribute("projectName", projectPath);
									ele2.addAttribute("packageName", "/"+ packagename.replaceAll("\\.", "/"));
									ele2.addAttribute("fullPath", ss2);
									
									parentele2 = ele2;
								}
								
								
//								if(xmlRoot.getRootElement().selectSingleNode(ss) == null){
//									GMFConsole.println("------"+ ss);
//									
////									GMFConsole.println("haha:"+parentele2);
////									GMFConsole.println(xmlRoot.getDocument().asXML());
////									GMFConsole.println(ss+"/"+str3[j]);
////									GMFConsole.println(xmlRoot.getRootElement().selectSingleNode(ss));
//									if(xmlRoot.getRootElement().selectSingleNode(ss) != null){
//										
//										Element ee = (Element)xmlRoot.getRootElement().selectSingleNode(ss);
////										ele2 = ee.addElement(str3[j]);
//										ele2 = ee.addElement("folder");
//										
//										Element eleele = (Element)doc1.getRootElement().selectSingleNode("//translate[@name='"+ ss2 +"/"+str3[j]+"']");
////										ele2.addAttribute("name", eleele.attributeValue("name2"));
////										ele2.addAttribute("name2", str3[j]);
////										ele2.addAttribute("fullpath", ss2+"/"+str3[j]);
////										ele2.addAttribute("containerName", "/"+projectPath);
////										ele2.addAttribute("packageName", "/"+packagename.replaceAll("\\.", "/"));
//										
////										GMFConsole.println(ss2 +"/"+str3[j]);
//										
//
//										ele2.addAttribute("name", str3[j]);
//										ele2.addAttribute("name2", eleele.attributeValue("name2"));
//										ele2.addAttribute("projectName", projectPath);
//										ele2.addAttribute("packageName", "/"+ packagename.replaceAll("\\.", "/"));
//										ele2.addAttribute("fullPath", ss2+"/"+str3[j]);
//									}
//								}
								
//								parentele2 = ele2;
								
								jjj = ele2;
							}
							
//							GMFConsole.println("-----"+parentele2);
						}
						
//						GMFConsole.println("-----"+ele.asXML());
						
//						if(jjj != null){
//							Element eee = jjj.addElement("module");
//							eee.addAttribute("resource", ele.attributeValue("resource"));
//							eee.addAttribute("containerName", "/"+projectPath);
//							eee.addAttribute("name", ele.attributeValue("name"));
//						}
//						sb.append("<module resource='"+ele.attributeValue("resource")+"' containerName='/"+projectPath+"' name='"+ele.attributeValue("name")+"'/>");
						
						String ssss = "/tree";
						for(int t=0;t<str3.length-1;t++){
							ssss = ssss + "/folder[@name='"+ str3[t] +"']";
						}
//						GMFConsole.println("------"+ssss);
//						GMFConsole.println("........"+str3[str3.length-1]);
//						GMFConsole.println(jjj);
						
						if(xmlRoot.getRootElement().selectSingleNode(ssss) != null){
							Element ee = (Element)xmlRoot.getRootElement().selectSingleNode(ssss);
							
//							GMFConsole.println("--------"+ ee.attributeValue("fullPath"));
							
							Element eee = ee.addElement("module");
							eee.addAttribute("name2", ele.attributeValue("name2"));
							eee.addAttribute("resource", ele.attributeValue("resource"));
							eee.addAttribute("projectName", projectPath);
							eee.addAttribute("packageName", "/"+ packagename.replaceAll("\\.", "/"));
							eee.addAttribute("name", ele.attributeValue("resource").replaceAll(packagename.replaceAll("\\.", "/") +"/", "").replaceAll(ee.attributeValue("fullPath")+"/", ""));
						}
					}
				}
			}
//				list = xmlRoot.selectNodes("/");
//				
//				for(int i=0;i<list.size();i++){
//					Element ele = (Element)list.get(i);
//					GMFConsole.println("....."+ele.getName());
//
//				}
//				GMFConsole.println("::::"+xmlRoot.selectSingleNode("//tree").asXML().replaceAll("<tree>", "").replaceAll("</tree>", ""));
				
				sb.append(xmlRoot.selectSingleNode("//tree").asXML().replaceAll("<tree>", "").replaceAll("</tree>", ""));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		
		} catch (CoreException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			GMFConsole.println(e);
		}
		
    	
    	return sb.toString();
    }

}
