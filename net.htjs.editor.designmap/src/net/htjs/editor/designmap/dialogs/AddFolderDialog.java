package net.htjs.editor.designmap.dialogs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.RssXml2;
import net.htjs.editor.designmap.views.ProjectView;
import net.htjs.util.property.PropertyConfigFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;

public class AddFolderDialog extends TitleAreaDialog {

	private Text packageName_Text;
	private Text packageName_Text2;

	public AddFolderDialog(Shell parentShell) {
		super(parentShell);
        this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);
        this.setTitleAreaColor(new RGB(255, 255, 255));
        this.setHelpAvailable(true);
	}


	
	public void create(){
		super.create();
		this.getShell().setSize(400, 250);
		Rectangle screenSize = this.getShell().getDisplay().getClientArea();
		Rectangle frameSize = this.getShell().getBounds();
		this.getShell().setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		try {
			this.getShell().setText(new String("添加目录".getBytes(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 创建Button键
	 */
	protected Control createButtonBar(Composite arg0){
		Control c = super.createButtonBar(arg0);
        this.enableOK(false);
    	try {
			this.getButton(IDialogConstants.OK_ID).setText(new String("保存".getBytes(),"utf-8")+"(&S)");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			this.getButton(IDialogConstants.CANCEL_ID).setText(new String("关闭".getBytes(),"utf-8")+"(&C)");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return c;
	}
	
    private void enableOK(boolean enable){
    	Button b = this.getButton(IDialogConstants.OK_ID);
    	if(b != null) b.setEnabled(enable);
    }
    
    protected void okPressed(){

    	String zw = this.packageName_Text.getText().trim();
    	String yw = this.packageName_Text2.getText().trim();
    	
    	HashMap attributeMap = new HashMap();
//    	attributeMap.put("name", yw);    
//    	attributeMap.put("name2", zw);   
    	
    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	//选中的对象
    	Element firstelement = (Element)channelNavigatorView.getFirstElement();
    	
    	Element parentElement = null;
    	
    	String path1 = "";
    	
    	//根据选中节点是 子系统、包或模块 package  translate   module
    	if("package".equals(firstelement.getName())){
    		
    		parentElement = firstelement;
//    		attributeMap.put("name", "/"+yw);    
//        	attributeMap.put("containerName", parentElement.attributeValue("containerName")); 
//        	attributeMap.put("resource", "/"+ parentElement.attributeValue("name").replaceAll("\\.", "/")); 
//        	attributeMap.put("resource2", "/"+ parentElement.attributeValue("name").replaceAll("\\.", "/"));  
//    		
//        	path1 = parentElement.attributeValue("containerName")+"/src/"+ parentElement.attributeValue("name").replaceAll("\\.", "/");

    		attributeMap.put("name", yw);
    		attributeMap.put("name2", zw);
    		attributeMap.put("projectName", firstelement.attributeValue("projectName"));
    		attributeMap.put("packageName", "/"+ firstelement.attributeValue("name").replaceAll("\\.", "/"));
    		attributeMap.put("fullPath", "/"+ yw);
    		
    		path1 = "/"+ attributeMap.get("projectName") +"/src"+ attributeMap.get("packageName");
    	}
    	else if("folder".equals(firstelement.getName())){
    		
    		parentElement = firstelement;
    		
//    		attributeMap.put("name", parentElement.attributeValue("name") +"/"+yw);    
//        	attributeMap.put("containerName", parentElement.attributeValue("containerName")); 
//        	attributeMap.put("resource", parentElement.attributeValue("resource") +"/"+ parentElement.attributeValue("name")); 
//        	attributeMap.put("resource2", parentElement.attributeValue("resource2")); 
//        	
//        	path1 = parentElement.attributeValue("containerName")+"/src"+ parentElement.attributeValue("resource") +"/"+ parentElement.attributeValue("name");

    		attributeMap.put("name", yw);
    		attributeMap.put("name2", zw);
    		attributeMap.put("projectName", firstelement.attributeValue("projectName"));
    		attributeMap.put("packageName", firstelement.attributeValue("packageName"));
    		attributeMap.put("fullPath", firstelement.attributeValue("fullPath") +"/"+ yw);
    		
    		path1 = "/"+ attributeMap.get("projectName") +"/src"+ attributeMap.get("packageName") + firstelement.attributeValue("fullPath");
    		
//    		GMFConsole.println("------"+ parentElement);
//    		GMFConsole.println("---:::2 "+ path1);
    		
    	}
    	

    	//在树内存中增加节点，并刷新，然后选中，定位新元素
    	Element newOutline = RssXml2.addElement(firstelement, "folder", attributeMap);
    	channelNavigatorView.refreshAll();
    	
    	
    	/**
    	 * 
    	 * 创建目录
    	 */
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(path1));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			GMFConsole.println("!resource.exists() || !(resource instanceof IContainer)");	
		}
		IContainer container = (IContainer) resource;
		IFolder folder = container.getFolder(new Path(yw));
		try {
			folder.create(true, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			GMFConsole.println(e);
			e.printStackTrace();
		}
		
		/**
		 * 
		 * 添加到designs.dm
		 */
    	IWorkspaceRoot root2 = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource2 = root2.findMember(new Path(attributeMap.get("projectName") +"/src"+ attributeMap.get("packageName")));
		if (!resource2.exists() || !(resource2 instanceof IContainer)) {
			GMFConsole.println("!resource.exists() || !(resource instanceof IContainer)");				
		}
		IContainer container2 = (IContainer) resource2;
		final IFile file2 = container2.getFile(new Path("/designs.dm"));
		
		if(file2.exists()){
			
			
			try {
				InputStream is = file2.getContents();
				String xml = convertStreamToString(is);
				
				try {
					Document doc1 = DocumentHelper.parseText(xml);
					
					Element ee = doc1.getRootElement().addElement("translate");
					ee.addAttribute("name", attributeMap.get("fullPath").toString());
					ee.addAttribute("name2", zw);
					
					try {
						file2.setContents(new ByteArrayInputStream(doc1.asXML().getBytes("UTF-8")), true,true, null);
//						channelNavigatorView.updateTree();
					} catch (UnsupportedEncodingException e) {
						GMFConsole.println(e);
					}
				} catch (DocumentException e) {
					GMFConsole.println(e);
				}
			
			} catch (CoreException e) {
				GMFConsole.println(e);
			}
		}

    	channelNavigatorView.setSelection(newOutline);
    	
    	super.okPressed();
    }
    
    protected void okPressed2(){
//    	HashMap attributeMap = new HashMap();
//    	attributeMap.put("name", this.packageName_Text.getText().trim());
//    	
//    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
//    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
//    	Object o = channelNavigatorView.getFirstElement();
//    	Element outline = null;
//    	if(o instanceof Element){//判断对象是否能转换成元素
//    		outline = (Element)o;
//    		//如果选中元素是频道，则
//    		if(outline.getName().equals("module")){
//    			outline = outline.getParent();
//    		}
//    	}
//    	else{//说明没有选中任何元素，此时获取body元素
//    		outline = channelNavigatorView.getDoc().getRootElement();
//    	}
//    	
//    	Element element = RssXml2.addElement(outline, "package", attributeMap);
//    	
//    	channelNavigatorView.saveXmlFile();
//    	//刷新频道视图
//    	channelNavigatorView.refreshAll();
//    	//定位新元素
//    	channelNavigatorView.setSelection(element);
//    	
////    	GMFConsole.println(outline);
//    	
//    	this.createPackage(channelNavigatorView.getProject(), outline, this.packageName_Text.getText().trim());
    	
    	String zw = this.packageName_Text.getText().trim();
    	String yw = this.packageName_Text2.getText().trim();
    	
//    	GMFConsole.println(zw+" "+yw);
    	
    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	Element ele = (Element)channelNavigatorView.getFirstElement();
    	
//    	GMFConsole.println(ele.asXML());

    	String rq = "";
    	String rq2 = "";
    	
    	String fullpath = "";
    	
    	if("package".equals(ele.getName())){
//    		GMFConsole.println(ele.attributeValue("containerName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/")+"/"+yw);

    		rq = ele.attributeValue("containerName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/");
    		rq2 = ele.attributeValue("containerName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/");
    		fullpath = "/"+yw;
    	}
    	else if("module".equals(ele.getName())){
    		if("package".equals(ele.getParent().getName())){
//    			GMFConsole.println(ele.getParent().attributeValue("containerName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/")+"/"+yw);
    			rq = ele.getParent().attributeValue("containerName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/");
    			rq2 = ele.getParent().attributeValue("containerName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/");
    			fullpath = "/"+yw;
    		}
    		else{
//    			GMFConsole.println(ele.getParent().attributeValue("containerName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/")+ele.getParent().attributeValue("fullpath")+"/"+yw);
    			rq = ele.getParent().attributeValue("containerName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/")+ele.getParent().attributeValue("fullpath");
    			rq2 = ele.getParent().attributeValue("containerName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/");
    			fullpath = ele.getParent().attributeValue("fullpath")+"/"+yw;
    		}
    	}
    	else{
//    		GMFConsole.println(ele.attributeValue("containerName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/")+ele.attributeValue("fullpath")+"/"+yw);
    		rq = ele.attributeValue("containerName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/")+ele.attributeValue("fullpath");
    		rq2 = ele.attributeValue("containerName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/");
    		fullpath = ele.attributeValue("fullpath")+"/"+yw;
    	}
    	
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(rq));
//		GMFConsole.println(rq);
//		GMFConsole.println(rq2);
		if (!resource.exists() || !(resource instanceof IContainer)) {
			GMFConsole.println("!resource.exists() || !(resource instanceof IContainer)");	
		}
		IContainer container = (IContainer) resource;


		IFolder folder = container.getFolder(new Path(yw));
		try {
			folder.create(true, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			GMFConsole.println(e);
			e.printStackTrace();
		}

		
		//添加到designs.dm

    	IWorkspaceRoot root2 = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource2 = root2.findMember(new Path(rq2));
		if (!resource2.exists() || !(resource2 instanceof IContainer)) {
			GMFConsole.println("!resource.exists() || !(resource instanceof IContainer)");				
		}
		IContainer container2 = (IContainer) resource2;
		final IFile file2 = container2.getFile(new Path("/designs.dm"));
		if(file2.exists()){

			
			try {
				InputStream is = file2.getContents();
				String xml = convertStreamToString(is);
				
				try {
					Document doc1 = DocumentHelper.parseText(xml);
					
					Element ee = doc1.getRootElement().addElement("translate");
					ee.addAttribute("name", fullpath);
					ee.addAttribute("name2", zw);
					
//					GMFConsole.println(":::"+doc1.asXML());
					
					try {
						file2.setContents(new ByteArrayInputStream(doc1.asXML().getBytes("UTF-8")), true,true, null);
						channelNavigatorView.updateTree();
					} catch (UnsupportedEncodingException e) {
						GMFConsole.println(e);
						e.printStackTrace();
					}
					
					
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		channelNavigatorView.refreshAll();
		
    	super.okPressed();
    }
    
    private void createPackage(IProject project,Element outline,String fileName){
		try {
			PropertyConfigFactory pcf = PropertyConfigFactory.loadFile(project.getLocation().toString()+"/src/buildCode.properties");
			GMFConsole.println("KK:"+pcf.getProperty("DMPath"));
			GMFConsole.println("KK:"+outline.getName());
			GMFConsole.println("KK:"+fileName);
			GMFConsole.println("KK:"+project.getFullPath());
			
//			GMFConsole.println(project.getLocation()+"/src"+pcf.getProperty("DMPath"));

			try{
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IResource resource = root.findMember(new Path(project.getFullPath()+"/src"));
				if (!resource.exists() || !(resource instanceof IContainer)) {
					//				
				}
				IContainer container = (IContainer) resource;
//				final IFolder folder = container.getFolder(new Path(pcf.getProperty("DMPath")));
//				folder.create(true, true, null);
				
//				GMFConsole.println(Boolean.toString(container.exists(new Path(pcf.getProperty("DMPath")))));
				
				IProject[] ipp = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			
				for(int i=0;i<ipp.length;i++){
					IProject ii = ipp[i];
					GMFConsole.println("++"+ii.getName()+ii.isOpen());
				}
			}
			catch(Exception e){
				GMFConsole.println("----"+e);
			}
//			folder.getFolder("").create
//			folder.create(true, true, null);
		}
		catch(Exception e){
			GMFConsole.println(e);
		}
    }
    
    /**
     * 创建对话框区域
     */
    protected Control createDialogArea(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(data);
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.marginWidth = 15;
        gridLayout.marginHeight = 10;
        gridLayout.makeColumnsEqualWidth = false;
        composite.setLayout(gridLayout);
        
        Label label = new Label(composite, SWT.NONE);
        //目录名称
        label.setText("\u4e2d\u6587\u540d\u79f0\uff1a");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.packageName_Text = new Text(composite,SWT.BORDER);
        this.packageName_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.packageName_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(packageName_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("\u4e2d\u6587\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else if(packageName_Text2.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("\u82f1\u6587\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
        Label label2 = new Label(composite, SWT.NONE);
        //目录名称
        label2.setText("\u82f1\u6587\u540d\u79f0\uff1a");
        label2.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.packageName_Text2 = new Text(composite,SWT.BORDER);
        this.packageName_Text2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.packageName_Text2.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(packageName_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("\u4e2d\u6587\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else if(packageName_Text2.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("\u82f1\u6587\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
		return parent;
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
}
