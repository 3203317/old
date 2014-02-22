package net.foreworld.rss2.dialogs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.dom4j.Element;
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

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 7, 2008 11:36:19 AM
 */
public class AddChannelGroupDialog extends TitleAreaDialog {
	
	//频道组名
	private Text channelGroupName_Text;
	//
	private String dialogType;

	public AddChannelGroupDialog(Shell parentShell,String dialogType) {
		super(parentShell);
		this.dialogType = dialogType;
        this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);
        this.setTitleAreaColor(new RGB(255, 255, 255));
        this.setHelpAvailable(true);
	}
	
	public void create(){
		super.create();
		//设置壳大小
		this.getShell().setSize(400, 200);
		//设置壳居中
		Rectangle screenSize = this.getShell().getDisplay().getClientArea();
		Rectangle frameSize = this.getShell().getBounds();
		this.getShell().setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		if(this.dialogType.equals("CREATE")){
			this.getShell().setText("新增频道组");
		}
		else if(this.dialogType.equals("UPDATE")){
			this.getShell().setText("修改频道组");
		}
	}
	
	/**
	 * 创建Button键
	 */
	protected Control createButtonBar(Composite arg0){
		Control c = super.createButtonBar(arg0);
        this.enableOK(false);
    	this.getButton(IDialogConstants.OK_ID).setText("保存(&S)");
        this.getButton(IDialogConstants.CANCEL_ID).setText("关闭(&C)");
        return c;
	}
	
	/**
	 * 设置OK键启用或禁用
	 * @param enable
	 */
    private void enableOK(boolean enable){
    	Button b = this.getButton(IDialogConstants.OK_ID);
    	if(b != null) b.setEnabled(enable);
    }
    
    /**
     * 点击OK键
     */
    protected void okPressed(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	//创建新的频道组
    	HashMap attributeMap = new HashMap();
    	attributeMap.put("id", dateFormat.format(new Date()));
    	attributeMap.put("title", this.channelGroupName_Text.getText().trim());
    	
    	//获取视图
    	ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
    	//获取选中的元素
    	Object o = channelNavigatorView.getFirstElement();
    	Element outline = null;
    	if(o instanceof Element){//判断对象是否能转换成元素
    		outline = (Element)o;
    		//如果选中元素是频道，则
    		if(outline.attribute("type") != null){
    			outline = outline.getParent();
    		}
    	}
    	else{//说明没有选中任何元素，此时获取body元素
    		outline = channelNavigatorView.getDoc().getRootElement().element("body");
    	}
    	Element newOutline = RssXml2.addElement(outline, "outline", attributeMap);
    	//保存文档
    	channelNavigatorView.saveXmlFile();
    	//刷新频道视图
    	channelNavigatorView.refreshAll();
    	//定位新元素
    	channelNavigatorView.setSelection(newOutline);
    	//
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
        label.setText("频道组名：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        //创建频道组录入框
        this.channelGroupName_Text = new Text(composite,SWT.BORDER);
        this.channelGroupName_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.channelGroupName_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(channelGroupName_Text.getText().trim().equals("")){
					setErrorMessage("频道组名不能为空！");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        
		return parent;
    }

}
