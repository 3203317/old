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
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 7, 2008 11:36:19 AM
 */
public class AddChannelGroupDialog extends TitleAreaDialog {
	
	//Ƶ������
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
		//���ÿǴ�С
		this.getShell().setSize(400, 200);
		//���ÿǾ���
		Rectangle screenSize = this.getShell().getDisplay().getClientArea();
		Rectangle frameSize = this.getShell().getBounds();
		this.getShell().setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		if(this.dialogType.equals("CREATE")){
			this.getShell().setText("����Ƶ����");
		}
		else if(this.dialogType.equals("UPDATE")){
			this.getShell().setText("�޸�Ƶ����");
		}
	}
	
	/**
	 * ����Button��
	 */
	protected Control createButtonBar(Composite arg0){
		Control c = super.createButtonBar(arg0);
        this.enableOK(false);
    	this.getButton(IDialogConstants.OK_ID).setText("����(&S)");
        this.getButton(IDialogConstants.CANCEL_ID).setText("�ر�(&C)");
        return c;
	}
	
	/**
	 * ����OK�����û����
	 * @param enable
	 */
    private void enableOK(boolean enable){
    	Button b = this.getButton(IDialogConstants.OK_ID);
    	if(b != null) b.setEnabled(enable);
    }
    
    /**
     * ���OK��
     */
    protected void okPressed(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	//�����µ�Ƶ����
    	HashMap attributeMap = new HashMap();
    	attributeMap.put("id", dateFormat.format(new Date()));
    	attributeMap.put("title", this.channelGroupName_Text.getText().trim());
    	
    	//��ȡ��ͼ
    	ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
    	//��ȡѡ�е�Ԫ��
    	Object o = channelNavigatorView.getFirstElement();
    	Element outline = null;
    	if(o instanceof Element){//�ж϶����Ƿ���ת����Ԫ��
    		outline = (Element)o;
    		//���ѡ��Ԫ����Ƶ������
    		if(outline.attribute("type") != null){
    			outline = outline.getParent();
    		}
    	}
    	else{//˵��û��ѡ���κ�Ԫ�أ���ʱ��ȡbodyԪ��
    		outline = channelNavigatorView.getDoc().getRootElement().element("body");
    	}
    	Element newOutline = RssXml2.addElement(outline, "outline", attributeMap);
    	//�����ĵ�
    	channelNavigatorView.saveXmlFile();
    	//ˢ��Ƶ����ͼ
    	channelNavigatorView.refreshAll();
    	//��λ��Ԫ��
    	channelNavigatorView.setSelection(newOutline);
    	//
    	super.okPressed();
    }
    
    /**
     * �����Ի�������
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
        label.setText("Ƶ��������");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        //����Ƶ����¼���
        this.channelGroupName_Text = new Text(composite,SWT.BORDER);
        this.channelGroupName_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));        
        this.channelGroupName_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(channelGroupName_Text.getText().trim().equals("")){
					setErrorMessage("Ƶ����������Ϊ�գ�");
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
