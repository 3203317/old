package net.htjs.editor.designmap.dialogs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.RssXml2;
import net.htjs.editor.designmap.views.ProjectView;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
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


/**
 * 创建包 net.xxxx.xxxx 创建designs.dm文件
 * 写入design-mapping.xml文件 包路径+designs.dm
 * 更新当前项目的designm-mapping 和树内存
 * @author hx
 *
 */
public class AddPackageDialog extends TitleAreaDialog {

	private Text zw_Name_Text;
	private Text packageName_Text;
	private Text namespace_Text;

	public AddPackageDialog(Shell parentShell) {
		super(parentShell);
        this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);
        this.setTitleAreaColor(new RGB(255, 255, 255));
        this.setHelpAvailable(true);
	}


	
	public void create(){
		super.create();
		this.getShell().setSize(400, 300);
		Rectangle screenSize = this.getShell().getDisplay().getClientArea();
		Rectangle frameSize = this.getShell().getBounds();
		this.getShell().setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		this.getShell().setText("\u6dfb\u52a0\u5b50\u7cfb\u7edf");
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
    	
    	HashMap attributeMap = new HashMap();
    	attributeMap.put("name", this.packageName_Text.getText().trim());    	

    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	//选中的对象
    	Element firstelement = (Element)channelNavigatorView.getFirstElement();

    	attributeMap.put("projectName", firstelement.attributeValue("name"));
    	
    	//创建文件夹
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path("/"+ firstelement.attributeValue("name")+"/src"));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			GMFConsole.println("resource is not exists...");
			return;
		}
		IContainer container = (IContainer) resource;
		
		String[] strs = this.packageName_Text.getText().trim().split("\\.");
		
		//创建分级文件夹
		String str = "/";
		for(int i=0;i<strs.length;i++){
			str += strs[i];
			IFolder folder = container.getFolder(new Path(str));
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				GMFConsole.println(e);
				e.printStackTrace();
			}
			str += "/";
		}
    	
		//创建子系统中的designs.dm文件
		final IFile file = container.getFile(new Path(this.packageName_Text.getText().trim().replace('.', '/') +"/designs.dm"));
		if (file.exists()) {
			//file.setContents(stream, true, true, monitor);
			GMFConsole.println(" is exist...");
		} else {
			try {
				file.create(this.openContentStream(this.namespace_Text.getText().trim()), true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//修改design-mapping.dm
		final IFile file2 = container.getFile(new Path("/design-mapping.dm"));
		if(file2.exists()){
			try {
				InputStream is = file2.getContents();
				String xml = convertStreamToString(is);
				
				try {
					Document doc1 = DocumentHelper.parseText(xml);
					
					Element ee = doc1.getRootElement().addElement("import");
					ee.addAttribute("resource", "/"+ this.packageName_Text.getText().trim().replace('.', '/')+"/designs.dm");
					ee.addAttribute("name", this.zw_Name_Text.getText().trim());
//					GMFConsole.println(":::"+doc1.asXML());
					
					try {
						file2.setContents(new ByteArrayInputStream(doc1.asXML().getBytes("UTF-8")), true,true, null);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
    	
    	
    	//在树内存中增加节点，并刷新，然后选中，定位新元素
    	Element newOutline = RssXml2.addElement(firstelement, "package", attributeMap);
    	channelNavigatorView.refreshAll();
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
    	
    	
//    	GMFConsole.println(this.packageName_Text.getText().trim());
    	
    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	Element ele = (Element)channelNavigatorView.getFirstElement();
//    	GMFConsole.println(ele.attributeValue("name"));
    	
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path("/"+ele.attributeValue("name")+"/src"));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			//				
		}
		IContainer container = (IContainer) resource;
//		final IFolder folder = container.getFolder(new Path(this.packageName_Text.getText().trim().replace('.', '/')));
//		try {
//			folder.create(true, true, null);
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			GMFConsole.println(e);
//			e.printStackTrace();
//		}
		
		
		String[] strs = this.packageName_Text.getText().trim().split("\\.");
		
		String str2 = "/";
		for(int i=0;i<strs.length;i++){
			
			str2 = str2 + strs[i];
			
//			GMFConsole.println(str2);
			IFolder folder = container.getFolder(new Path(str2));
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				GMFConsole.println(e);
				e.printStackTrace();
			}
			
			str2 = str2 +  "/";
		}
		

		final IFile file = container.getFile(new Path(this.packageName_Text.getText().trim().replace('.', '/') +"/designs.dm"));
		if (file.exists()) {
			//file.setContents(stream, true, true, monitor);
			GMFConsole.println(" is exist...");
		} else {
			try {
				file.create(this.openContentStream(this.namespace_Text.getText().trim()), true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		final IFile file2 = container.getFile(new Path("/design-mapping.dm"));
		if(file2.exists()){

			
			try {
				InputStream is = file2.getContents();
				String xml = convertStreamToString(is);
				
				try {
					Document doc1 = DocumentHelper.parseText(xml);
					
					Element ee = doc1.getRootElement().addElement("import");
					ee.addAttribute("resource", "/"+ this.packageName_Text.getText().trim().replace('.', '/')+"/designs.dm");
					
//					GMFConsole.println(":::"+doc1.asXML());
					
					try {
						file2.setContents(new ByteArrayInputStream(doc1.asXML().getBytes("UTF-8")), true,true, null);
						channelNavigatorView.updateTree();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
    	
    	super.okPressed();
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
        

        
        Label label3 = new Label(composite, SWT.NONE);
        //中文名称
        label3.setText("\u4e2d\u6587\u540d\u79f0\uff1a");
        label3.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.zw_Name_Text = new Text(composite,SWT.BORDER);
        this.zw_Name_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.zw_Name_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(zw_Name_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("namespace\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else if(zw_Name_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("namespace\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
        Label label2 = new Label(composite, SWT.NONE);
        //目录名称
        label2.setText("namespace\uff1a");
        label2.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.namespace_Text = new Text(composite,SWT.BORDER);
        this.namespace_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.namespace_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(packageName_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("namespace\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else if(namespace_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("namespace\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
        Label label = new Label(composite, SWT.NONE);
        //目录名称
        label.setText("\u5b50\u7cfb\u7edf\u540d\u79f0\uff1a");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.packageName_Text = new Text(composite,SWT.BORDER);
        this.packageName_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.packageName_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(packageName_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("\u5b50\u7cfb\u7edf\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
		return parent;
    }
    
    public static InputStream openContentStream(String namespace) {
    	
    	StringBuffer strBuf = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
	    strBuf.append("<designMap validate=\"false\" namespace=\""+ namespace +"\">\r\n");
	    strBuf.append("</designMap>");
		try {
			return new ByteArrayInputStream(strBuf.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			GMFConsole.println(e.getMessage());
			return null;
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
}
