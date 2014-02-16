package net.htjs.editor.designmap.dialogs;

import java.io.UnsupportedEncodingException;

import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.Pinyin;
import net.htjs.editor.designmap.views.ProjectView;
import net.htjs.util.property.PropertyConfigFactory;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
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

public class ImportDialog extends TitleAreaDialog {
	
	private Text packageName_Text;

	public ImportDialog(Shell parentShell) {
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
		try {
			this.getShell().setText(new String("导入".getBytes(),"utf-8"));
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
    	
//    	GMFConsole.println(outline);

//    	GMFConsole.println(this.packageName_Text.getText());
    	
    	try{
    	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	ProjectView channelNavigatorView = (ProjectView)workbenchPage.findView(ProjectView.ID);
    	PropertyConfigFactory pcf = PropertyConfigFactory.loadFile(channelNavigatorView.getProject().getFullPath()
    	        + "/src/buildCode.properties");
    		  GMFConsole.println(pcf.toString());
    		
    	}
		catch(Exception e){
			GMFConsole.println(e);
		}
    	
    	
//    	Pinyin py = new Pinyin();
//    	String str = py.getPinyin(this.packageName_Text.getText());
//    	String[] strs = str.split("\r\n");
//    	this.runImport(strs);
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
        //目录名称
        label.setText("\u5185\u5bb9\uff1a");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.packageName_Text = new Text(composite,SWT.BORDER|SWT.WRAP|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
        
        GridData gd = new GridData(GridData.FILL_BOTH|GridData.FILL_HORIZONTAL);
        gd.heightHint = 10;
        
        this.packageName_Text.setLayoutData(gd);        
        this.packageName_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(packageName_Text.getText().trim().equals("")){
					//目录名称不能为空！
					setErrorMessage("\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a\uff01");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
		return parent;
    }
    
    /**
     * 执行导入方法
     * @param strs
     */
    private void runImport(String[] strs){
//    	int length = strs.length;
//    	
//    	for(int i=0;i<length;i++){
//    		String val = strs[i].toString().trim();
//    		
//    		if(!"".equals(val)){
//    			int lastindexof = strs[i].toString().lastIndexOf("-");
//    			
//    			GMFConsole.println(Integer.toString(lastindexof));
//    		}
//    	}

    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot(); 
    	GMFConsole.println("----"+root.getProject().getLocation().toString());
    }
}
