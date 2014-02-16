package net.htjs.editor.designmap.dialogs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import net.htjs.build.buildcodes.BuildBase;
import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.RssXml2;
import net.htjs.editor.designmap.views.ProjectView;
import net.htjs.editor.designmap.wizards.DesignMapNewWizard;
import net.htjs.util.UtilBase;
import net.htjs.util.io.UtilFile;
import net.htjs.util.property.PropertyConfigFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.ide.IDE;

public class AddModuleDialog extends TitleAreaDialog {

	private Text packageName_Text;
	private Text containerText;

	private Text fileText;

	private ISelection selection;

	public AddModuleDialog(Shell parentShell) {
		super(parentShell);
        this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);
        this.setTitleAreaColor(new RGB(255, 255, 255));
        this.setHelpAvailable(true);
	}


	
	public void create(){
		super.create();
		this.getShell().setSize(550, 300);
		Rectangle screenSize = this.getShell().getDisplay().getClientArea();
		Rectangle frameSize = this.getShell().getBounds();
		this.getShell().setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		try {
			this.getShell().setText(new String("添加模块".getBytes(),"utf-8"));
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

    	//创建新的频道
    	HashMap attributeMap = new HashMap();
//    	attributeMap.put("fileName", this.fileText.getText().trim());
//    	attributeMap.put("containerName", this.containerText.getText().trim());
//    	attributeMap.put("name", this.packageName_Text.getText().trim());

//    	GMFConsole.println(this.fileText.getText().trim());
//    	GMFConsole.println(this.containerText.getText().trim());
//    	GMFConsole.println(this.packageName_Text.getText().trim());

//    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
//    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
//    	Object o = channelNavigatorView.getFirstElement();
//    	Element outline = null;
//    	if(o instanceof Element){//判断对象是否能转换成元素
//    		outline = (Element)o;
//    		//如果选中元素是频道，则
////    		if(outline.getName().equals("module")){
////    			outline = outline.getParent();
////    		}
//    	}
//    	else{//说明没有选中任何元素，此时获取body元素
//    		outline = channelNavigatorView.getDoc().getRootElement();
//    	}
//    	
//    	GMFConsole.println(outline.asXML());
    	
//    	//创建新元素并返回
//    	Element newOutline = RssXml2.addElement(outline, "module", attributeMap);
//    	channelNavigatorView.saveXmlFile();
//    	//刷新频道视图
//    	channelNavigatorView.refreshAll();
//    	//定位新元素
//    	channelNavigatorView.setSelection(newOutline);
//    	
//    	this.createDm(this.containerText.getText().trim(), this.fileText.getText().trim());
    	
    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	Element ele = (Element)channelNavigatorView.getFirstElement();
    	
//    	GMFConsole.println(ele.asXML());

    	String zw = this.packageName_Text.getText().trim();
    	String rqq = this.containerText.getText().trim();
    	String yw = this.fileText.getText().trim();
    	

    	String rq = "";
    	String rq2 = "";
    	String rq3 = "";
    	
    	String path1 = "";
    	
    	if("package".equals(ele.getName())){

    		attributeMap.put("projectName", ele.attributeValue("projectName"));
    		attributeMap.put("packageName", "/"+ ele.attributeValue("name").replaceAll("\\.", "/"));
    		attributeMap.put("name", this.fileText.getText().trim());
    		attributeMap.put("name2", this.packageName_Text.getText().trim());
    		attributeMap.put("resource", attributeMap.get("packageName") +"/"+ attributeMap.get("name"));
    		
    		path1 = "/"+ attributeMap.get("projectName") +"/src"+ attributeMap.get("packageName");
    	}
    	else if("folder".equals(ele.getName())){
    		attributeMap.put("projectName", ele.attributeValue("projectName"));
    		attributeMap.put("packageName", ele.attributeValue("packageName"));
    		attributeMap.put("name", this.fileText.getText().trim());
    		attributeMap.put("name2", this.packageName_Text.getText().trim());
    		attributeMap.put("resource", attributeMap.get("packageName") + ele.attributeValue("fullPath") +"/"+ attributeMap.get("name"));

    		path1 = "/"+ attributeMap.get("projectName") +"/src"+ attributeMap.get("packageName") + ele.attributeValue("fullPath");
    	}
    	
//    	if("package".equals(ele.getName())){
////    		GMFConsole.println(ele.attributeValue("containerName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/")+"/"+yw);
//
//    		rq = ele.attributeValue("containerName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/");
//    		rq2 = ele.attributeValue("containerName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/");
//    		fullpath = "/"+yw;
//    		rq3 = "/"+ele.attributeValue("name").replaceAll("\\.", "/")+"/"+yw;
//    	}
//    	else if("module".equals(ele.getName())){
//    		if("package".equals(ele.getParent().getName())){
////    			GMFConsole.println(ele.getParent().attributeValue("containerName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/")+"/"+yw);
//    			rq = ele.getParent().attributeValue("containerName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/");
//    			rq2 = ele.getParent().attributeValue("containerName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/");
//    			fullpath = "/"+yw;
//        		rq3 = "/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/")+"/"+yw;
//    		}
//    		else{
////    			GMFConsole.println(ele.getParent().attributeValue("containerName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/")+ele.getParent().attributeValue("fullpath")+"/"+yw);
//    			rq = ele.getParent().attributeValue("containerName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/")+ele.getParent().attributeValue("fullpath");
//    			rq2 = ele.getParent().attributeValue("containerName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/");
//    			fullpath = ele.getParent().attributeValue("fullpath")+"/"+yw;
//        		rq3 = ele.getParent().attributeValue("packageName").replaceAll("\\.", "/")+ele.getParent().attributeValue("fullpath")+"/"+yw;
//    		}
//    	}
//    	else{
////    		GMFConsole.println(ele.attributeValue("containerName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/")+ele.attributeValue("fullpath")+"/"+yw);
//    		rq = ele.attributeValue("containerName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/")+ele.attributeValue("fullpath");
//    		rq2 = ele.attributeValue("containerName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/");
//    		fullpath = ele.attributeValue("fullpath")+"/"+yw;
//    		rq3 = ele.attributeValue("packageName").replaceAll("\\.", "/")+ele.attributeValue("fullpath")+"/"+yw;
//    	}
    	
    	//1、/ptbase_pt/src/config/ysfpycrz/designs/rzgl/aaa.dm 文件是否存在 存在则提示，否则继续。。。
    	//2、/ptbase_pt/src/config/ysfpycrz/designs/designs.dm 中添加一个import元素 /rzgl/aaa.dm 及name属性
    	//3、在目标容器添加aaa.dm文件
    	
//    	this.createDm(rq, yw);
//
//    	this.updateDesigns(rq2, rq3, zw);
//    	GMFConsole.println(rq);
//    	GMFConsole.println(rq2);
//    	GMFConsole.println(fullpath);
    	
    	//创建dm文件
    	
    	try {
			this.doFinish(path1, this.fileText.getText().trim());
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		//添加到designs.dm
    	IWorkspaceRoot root2 = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource2 = root2.findMember(new Path("/"+ attributeMap.get("projectName") +"/src"+ attributeMap.get("packageName")));
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
					
					Element ee = doc1.getRootElement().addElement("import");
					ee.addAttribute("resource", attributeMap.get("resource").toString());
					ee.addAttribute("name2", attributeMap.get("name2").toString());
					
//					GMFConsole.println(":::"+doc1.asXML());
					
					try {
						file2.setContents(new ByteArrayInputStream(doc1.asXML().getBytes("UTF-8")), true,true, null);
//						channelNavigatorView.updateTree();
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
   

    	Element newOutline = RssXml2.addElement(ele, "module", attributeMap);
    	channelNavigatorView.refreshAll();
    	channelNavigatorView.setSelection(newOutline);
    	
    	super.okPressed();
    }
    
    private void updateDesigns(String containerName,String fullpath,String fileName){
    	IWorkspaceRoot root2 = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource2 = root2.findMember(new Path(containerName));
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
					
					Element ee = doc1.getRootElement().addElement("import");
					ee.addAttribute("resource", fullpath);
					ee.addAttribute("name", fileName);
					
//					GMFConsole.println(":::"+doc1.asXML());
					
					IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
			    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
					
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
    }
    
    
    private void createDm(String containerName,String fileName){
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
//			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
    String projectPath = file.getProject().getLocation().toString();
    
    	GMFConsole.println(containerName +"--"+ new Path(fileName).toString());
    
		try {
		  PropertyConfigFactory pcf = PropertyConfigFactory.loadFile(projectPath
	        + "/src/buildCode.properties");
	    BuildBase.setConfigFactory(pcf);
			InputStream stream = DesignMapNewWizard.openContentStream(file.getName());
			if (file.exists()) {
				//file.setContents(stream, true, true, monitor);
				GMFConsole.println(fileName+" is exist...");
			} else {
				file.create(stream, true, null);
				String path = file.getFullPath().toString();
        path = path.substring(path.indexOf("/",2));
        path = file.getProject().getLocation().toString() + path;
        GMFConsole.println("addToBaseDesign file=" + path);
				BuildBase.addToBaseDesign(path);
			}
			stream.close();
		} 
		catch (Exception e) {
		}
//		monitor.worked(1);
//		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
//		monitor.worked(1);
    }
    
    /**
     * 创建对话框区域
     */
    protected Control createDialogArea(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(data);
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginWidth = 15;
        gridLayout.marginHeight = 10;
        gridLayout.makeColumnsEqualWidth = false;
        composite.setLayout(gridLayout);
        
        Label label = new Label(composite, SWT.NONE);
        //目录名称
        try {
			label.setText(new String("模块".getBytes(),"utf-8")+"\u540d\u79f0\uff1a");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.packageName_Text = new Text(composite,SWT.BORDER | SWT.SINGLE);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 2;
        this.packageName_Text.setLayoutData(data);        
        this.packageName_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(packageName_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("\u76ee\u5f55\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
        label = new Label(composite, SWT.NULL);
		label.setText("&Container:");

		containerText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
//				dialogChanged();
			}
		});

		Button button = new Button(composite, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		
		label = new Label(composite, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
//				dialogChanged();
			}
		});
		
		/////
    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	Element ele = (Element)channelNavigatorView.getFirstElement();
    	String rq = "";
    	String rq2 = "";
    	
    	
    	if("package".equals(ele.getName())){
//    		GMFConsole.println(ele.attributeValue("containerName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/")+"/"+yw);

    		rq = ele.attributeValue("projectName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/");
    		rq2 = ele.attributeValue("projectName")+"/src/"+ele.attributeValue("name").replaceAll("\\.", "/");
    	}
    	else if("module".equals(ele.getName())){
    		if("package".equals(ele.getParent().getName())){
//    			GMFConsole.println(ele.getParent().attributeValue("containerName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/")+"/"+yw);
    			rq = ele.getParent().attributeValue("projectName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/");
    			rq2 = ele.getParent().attributeValue("projectName")+"/src/"+ele.getParent().attributeValue("name").replaceAll("\\.", "/");
    		}
    		else{
//    			GMFConsole.println(ele.getParent().attributeValue("containerName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/")+ele.getParent().attributeValue("fullpath")+"/"+yw);
    			rq = ele.getParent().attributeValue("projectName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/")+ele.getParent().attributeValue("fullpath");
    			rq2 = ele.getParent().attributeValue("projectName")+"/src"+ele.getParent().attributeValue("packageName").replaceAll("\\.", "/");
    		}
    	}
    	else{
//    		GMFConsole.println(ele.attributeValue("containerName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/")+ele.attributeValue("fullpath")+"/"+yw);
    		rq = ele.attributeValue("projectName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/")+ele.attributeValue("fullPath");
    		rq2 = ele.attributeValue("projectName")+"/src"+ele.attributeValue("packageName").replaceAll("\\.", "/");
    	}
    	
    	this.containerText.setText("/"+rq);
        
		return parent;
    }
    
	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
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
    
    
	private void doFinish(
			String containerName,
			String fileName)
			throws CoreException {
			// create a sample file
//			monitor.beginTask("Creating " + fileName, 2);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IResource resource = root.findMember(new Path(containerName));
			if (!resource.exists() || !(resource instanceof IContainer)) {
				throwCoreException("Container \"" + containerName + "\" does not exist.");
			}
			IContainer container = (IContainer) resource;
			final IFile file = container.getFile(new Path(fileName));
	    String projectPath = file.getProject().getLocation().toString();
	    
			try {
			  PropertyConfigFactory pcf = PropertyConfigFactory.loadFile(projectPath
		        + "/src/buildCode.properties");
		    BuildBase.setConfigFactory(pcf);
				InputStream stream = openContentStream(file.getName());
				if (file.exists()) {
					//file.setContents(stream, true, true, monitor);
					GMFConsole.println(fileName+" is exist...");
				} else {
					file.create(stream, true,null);
					String path = file.getFullPath().toString();
	        path = path.substring(path.indexOf("/",2));
	        path = file.getProject().getLocation().toString() + path;
	        GMFConsole.println("addToBaseDesign file=" + path);
					BuildBase.addToBaseDesign(path);
				}
				stream.close();
			} 
			catch (Exception e) {
			}
//			monitor.worked(1);
//			monitor.setTaskName("Opening file for editing...");
			getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage page =
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor(page, file, true);
					} catch (PartInitException e) {
					}
				}
			});
//			monitor.worked(1);
		}
	
	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "net.htjs.editor.designmap", IStatus.OK, message, null);
		throw new CoreException(status);
	}
	
	public static InputStream openContentStream(String mkmc) {
		  String projectName = BuildBase.getProjectName();
		    mkmc = UtilFile.removeFileExt(mkmc);
		  String clsName = UtilBase.firstToUpper(mkmc.trim());
		  String paramClsName = "Object";
		  String idName = mkmc.trim();
		    StringBuffer strBuf = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		    strBuf.append("<designMap validate=\"false\" namespace=\""+mkmc+"\">\r\n");
		    strBuf.append("\t<pojos>\r\n");
		    strBuf.append("\t<pojo id=\""+idName+"\" class=\""+BuildBase.getPojoPackagePath(mkmc)+"."+clsName+"\" dataset=\"\">");
	      strBuf.append("\t</pojo>");
		    strBuf.append("\t</pojos>\r\n");
		    strBuf.append("\t<daos>\r\n");
		    strBuf.append("\t<dao id=\""+idName+"DAO\" class=\""+BuildBase.getDaoPackagePath(mkmc)+"."+clsName+"DAO\" dataset=\"\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<method id=\"selectByPOJO\" methodName=\"selectByPOJO\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"PaginatedList\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method methodName=\"selectByPrimaryKey\" id=\"selectByPrimaryKey\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"Object\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"insert\" methodName=\"insert\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"updateByPrimaryKey\" methodName=\"updateByPrimaryKey\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"deleteByPrimaryKey\" methodName=\"deleteByPrimaryKey\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"updateByPrimaryKeySelective\" methodName=\"updateByPrimaryKeySelective\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"selectAllByPOJO\" methodName=\"selectAllByPOJO\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"List\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t</dao>");
		    strBuf.append("\t</daos>\r\n");
		    strBuf.append("\t<services>\r\n");
		    strBuf.append("\t<service id=\""+idName+"Service\" class=\""+BuildBase.getServicePackagePath(mkmc)+"."+clsName+"Service\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<method id=\"selectByPOJO\" methodName=\"selectByPOJO\" ref=\""+idName+"DAO.selectByPOJO\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t<return description=\"\" type=\"PaginatedList\"></return>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"selectByPrimaryKey\" methodName=\"selectByPrimaryKey\" ref=\""+idName+"DAO.selectByPrimaryKey\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"insert\" methodName=\"insert\" ref=\""+idName+"DAO.insert\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"updateByPrimaryKey\" methodName=\"updateByPrimaryKey\" ref=\""+idName+"DAO.updateByPrimaryKey\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t<return type=\"int\" description=\"\"></return>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"deleteByPrimaryKey\" methodName=\"deleteByPrimaryKey\" ref=\""+idName+"DAO.deleteByPrimaryKey\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t<return type=\"int\" description=\"\"></return>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"deleteByPrimaryKeyList\" methodName=\"deleteByPrimaryKeyList\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\"primaryKeyList\" type=\"List\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	      strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"updateByPrimaryKeySelective\" methodName=\"updateByPrimaryKeySelective\" ref=\""+idName+"DAO.updateByPrimaryKeySelective\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t<return type=\"int\"></return>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"selectAllByPOJO\" methodName=\"selectAllByPOJO\" ref=\""+idName+"DAO.selectAllByPOJO\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"List\"></return>");
	      strBuf.append("\t</method>");
		    strBuf.append("\t</service>");
		    strBuf.append("\t</services>\r\n");
		    strBuf.append("\t<ejbs>\r\n");
		    strBuf.append("\t</ejbs>\r\n");
		    strBuf.append("\t<contentProviders>\r\n");
		    strBuf.append("\t<contentProvider id=\""+idName+"Bean\" class=\""+BuildBase.getBeanPackagePath(mkmc)+"."+clsName+"Bean\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<method id=\"selectByPOJO\" methodName=\"selectByPOJO\" ref=\""+idName+"Service.selectByPOJO\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return description=\"\" type=\"PaginatedList\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"selectByPrimaryKey\" methodName=\"selectByPrimaryKey\" ref=\""+idName+"Service.selectByPrimaryKey\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"insert\" methodName=\"insert\" ref=\""+idName+"Service.insert\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"updateByPrimaryKey\" methodName=\"updateByPrimaryKey\" ref=\""+idName+"Service.updateByPrimaryKey\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"deleteByPrimaryKey\" methodName=\"deleteByPrimaryKey\" ref=\""+idName+"Service.deleteByPrimaryKey\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"deleteByPrimaryKeyList\" methodName=\"deleteByPrimaryKeyList\" ref=\""+idName+"Service.deleteByPrimaryKeyList\">");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\"primaryKeyList\" type=\"List\" description=\"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"updateByPrimaryKeySelective\" methodName=\"updateByPrimaryKeySelective\" ref=\""+idName+"Service.updateByPrimaryKeySelective\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"int\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t<method id=\"selectAllByPOJO\" methodName=\"selectAllByPOJO\" ref=\""+idName+"Service.selectAllByPOJO\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<params>");
	      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	      strBuf.append("\t</params>");
	      strBuf.append("\t<return type=\"List\"></return>");
	      strBuf.append("\t</method>");
	      strBuf.append("\t</contentProvider>");
		    strBuf.append("\t</contentProviders>\r\n");
		    strBuf.append("\t<labelProviders>\r\n");
		    strBuf.append("\t<labelProvider id=\"viewMain\" name=\"viewMain\"></labelProvider>");
		    strBuf.append("\t<labelProvider id=\"viewAdd\" name=\"viewAdd\"></labelProvider>");
		    strBuf.append("\t<labelProvider id=\"viewEdit\" name=\"viewEdit\" extends=\"viewAdd\"></labelProvider>");
		    strBuf.append("\t<labelProvider id=\"select\" name=\"select\"></labelProvider>");
		    strBuf.append("\t</labelProviders>\r\n");
		    strBuf.append("\t<action id=\""+idName+"Action\" class=\""+BuildBase.getActionPackagePath(mkmc)+"."+clsName+"Action\" path=\"/server/"+projectName+"/"+idName+"/\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<method id=\"viewMain\" methodName=\"viewMain\" page=\"index.html\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
		    /*strBuf.append("\t<description></description>");
		    strBuf.append("\t<contentProvider ref=\"module.moduleBean.selectUserOperateByPid\" store=\"security\"></contentProvider>");
		    strBuf.append("\t<labelProvider ref=\"module.getChildrenModule\" refStore=\"security\" prefix=\"model\"></labelProvider>");
		    strBuf.append("\t</data>");
		    strBuf.append("\t<data id=\"d2\">");*/
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<labelProvider ref=\"viewMain\" prefix=\"model\"></labelProvider>");
	      strBuf.append("\t</data>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"viewAdd\" methodName=\"viewAdd\" page=\"viewAdd.html\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
		    strBuf.append("\t<description></description>");
		    strBuf.append("\t<labelProvider ref=\"viewAdd\" prefix=\"model\"></labelProvider>");
		    strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
	      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	      strBuf.append("\t</params>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"add\" methodName=\"add\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.insert\"></contentProvider>");
	      strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"viewEdit\" methodName=\"viewEdit\" page=\"viewEdit.html\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
		    strBuf.append("\t<contentProvider store=\"data\" ref=\""+idName+"Bean.selectByPrimaryKey\"></contentProvider>");
		    strBuf.append("\t<labelProvider ref=\"viewEdit\" refStore=\"data\" prefix=\"model\"></labelProvider>");
		    strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
	      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	      strBuf.append("\t</params>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"edit\" methodName=\"edit\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.updateByPrimaryKey\"></contentProvider>");
	      strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"select\" methodName=\"select\" page=\"select.html\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
		    strBuf.append("\t<contentProvider store=\"data\" ref=\""+idName+"Bean.selectByPOJO\"></contentProvider>");
		    strBuf.append("\t<labelProvider ref=\"select\" refStore=\"data\"></labelProvider>");
		    strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
		    strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
		    strBuf.append("\t</params>");
		    strBuf.append("\t</method>");
		    strBuf.append("\t<method id=\"delete\" methodName=\"delete\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.deleteByPrimaryKey\"></contentProvider>");
	      strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
	      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	      strBuf.append("\t</params>");
		    strBuf.append("</method>");
		    strBuf.append("\t<method id=\"enabled\" methodName=\"enabled\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.updateByPrimaryKeySelective\"></contentProvider>");
	      strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
	      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	      strBuf.append("\t</params>");
		    strBuf.append("</method>");
		    strBuf.append("\t<method id=\"disabled\" methodName=\"disabled\" resultType=\"json\">");
		    strBuf.append("\t<data id=\"d1\">");
	      strBuf.append("\t<description></description>");
	      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.updateByPrimaryKeySelective\"></contentProvider>");
	      strBuf.append("\t</data>");
		    strBuf.append("\t<params>");
	      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	      strBuf.append("\t</params>");
		    strBuf.append("</method>");
		    strBuf.append("\t</action>\r\n");
		    strBuf.append("</designMap>");
			try {
				return new ByteArrayInputStream(strBuf.toString().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				GMFConsole.println(e.getMessage());
				return null;
			}
		}
}
