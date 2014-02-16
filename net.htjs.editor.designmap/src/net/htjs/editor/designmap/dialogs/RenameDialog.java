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

public class RenameDialog extends TitleAreaDialog {

	private Text new_Name_Text;
	
	public RenameDialog(Shell parentShell) {
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
		this.getShell().setText("\u91cd\u547d\u540d");
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
    	
    	String newName = this.new_Name_Text.getText().trim();
    	
//    	attributeMap.put("name", this.packageName_Text.getText().trim());    	

    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	//选中的对象
    	Element firstelement = (Element)channelNavigatorView.getFirstElement();
    	
    	Element parentelement = firstelement.getParent();

    	String packageUrl = "";
    	
    	String resourceUrl = "";
    	
    	String path1 = "";
    	
    	if("project".equals(firstelement.getName())){
    		
    	}
    	else if("package".equals(firstelement.getName())){
    		
    	}
    	else if("module".equals(firstelement.getName())){
    		firstelement.setAttributeValue("name2", newName);
    		
    		path1 = "/"+ firstelement.attributeValue("projectName") +"/src"+ firstelement.attributeValue("packageName");
    		
//    		//父模块是子系统
//    		if("package".equals(parentelement.getName())){
//    			packageUrl = parentelement.attributeValue("containerName") +"/src/"+ parentelement.attributeValue("name").replaceAll("\\.", "/");
//    			resourceUrl = firstelement.attributeValue("resource");
//    		}
//    		//是目录
//    		else{
//    			packageUrl = parentelement.attributeValue("containerName") + "/src"+ parentelement.attributeValue("packageName");
//    			resourceUrl = firstelement.attributeValue("resource");
//    		}
    		
    	}//为目录
    	else if("folder".equals(firstelement.getName())){
    		firstelement.setAttributeValue("name2", newName);
    		
    		path1 = "/"+ firstelement.attributeValue("projectName") +"/src"+ firstelement.attributeValue("packageName");
    		//父模块是子系统
//    		if("package".equals(parentelement.getName())){
//        		packageUrl = firstelement.attributeValue("containerName") +"/src"+ firstelement.attributeValue("packageName");
//        		firstelement.setAttributeValue("name2", newName);
//        		
//        		resourceUrl = firstelement.attributeValue("fullpath");
//    		}
//    		else{
//    			
//    		}
    	}
    	
    	GMFConsole.println(path1);
    	
		/**
		 * 
		 * 修改designs.dm中 重命名
		 */
    	IWorkspaceRoot root2 = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource2 = root2.findMember(new Path(path1));
		if (!resource2.exists() || !(resource2 instanceof IContainer)) {
			GMFConsole.println("!resource.exists() || !(resource instanceof IContainer)");				
		}
		IContainer container2 = (IContainer) resource2;
		final IFile file2 = container2.getFile(new Path("/designs.dm"));
		
		if(file2.exists()){
//			GMFConsole.println("--------------------------存在");
			try {
				InputStream is = file2.getContents();
				String xml = convertStreamToString(is);
				
				try {
					Document doc1 = DocumentHelper.parseText(xml);
					
					if("project".equals(firstelement.getName())){
			    		
			    	}
			    	else if("package".equals(firstelement.getName())){
			    		
			    	}
			    	else if("module".equals(firstelement.getName())){
			    		Element ele = (Element)doc1.selectSingleNode("//import[@resource='"+ firstelement.attributeValue("resource") +"']");
			    		ele.setAttributeValue("name2", newName);
			    		
//			    		GMFConsole.println(ele +" "+ resourceUrl);
			    		
			    	}//为目录
			    	else if("folder".equals(firstelement.getName())){
			    		Element ele = (Element)doc1.selectSingleNode("//translate[@name='"+ firstelement.attributeValue("fullPath") +"']");
			    		ele.setAttributeValue("name2", newName);
			    		
//			    		Element ele = (Element)doc1.selectSingleNode("//translate[@name='"+ resourceUrl +"']");
//			    		ele.setAttributeValue("name2", newName);
//			    		GMFConsole.println(ele +" "+ resourceUrl);
			    	}
					
//					Element ee = doc1.getRootElement().addElement("translate");
//					ee.addAttribute("name", attributeMap.get("name").toString());
//					ee.addAttribute("name2", zw);
					
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
    	
    	channelNavigatorView.refreshAll();
    	
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
        
        
        Label label = new Label(composite, SWT.NONE);
        //重命名
        label.setText("\u91cd\u547d\u540d\uff1a");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.new_Name_Text = new Text(composite,SWT.BORDER);
        this.new_Name_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.new_Name_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(new_Name_Text.getText().trim().equals("")){
					//重命名不能为空！
					setErrorMessage("\u91cd\u547d\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
        IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	
    	//选中的对象
    	Element firstelement = (Element)channelNavigatorView.getFirstElement();
    	
    	this.new_Name_Text.setText(firstelement.attributeValue("name2"));
        
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
