package net.foreworld.rss2.dialogs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.foreworld.rss2.Messages;
import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.db.UpdateTime;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.views.ChannelNavigatorView;
import net.foreworld.rss2.views.DetailListView;
import net.foreworld.rss2.views.IEView;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 7, 2008 8:45:34 PM
 */
public class AttributeOfChannelDialog extends TitleAreaDialog {
	
	private ChannelNavigatorView channelNavigatorView;
	private Element outline;
	//
	private Text title_Text;
	private Text xmlUrl_Text;
	private Combo updateTime_Combo;
	private Spinner saveCount_Spinner;
	
	public AttributeOfChannelDialog(Shell parentShell){
		super(parentShell);
        this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);
        this.setTitleAreaColor(new RGB(255, 255, 255));
		//
		this.channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
		this.outline = (Element)this.channelNavigatorView.getFirstElement();
	}

	public void create(){
		super.create();
		//设置壳大小
		this.getShell().setSize(400, 250);
		//设置壳居中
		Rectangle screenSize = this.getShell().getDisplay().getClientArea();
		Rectangle frameSize = this.getShell().getBounds();
		this.getShell().setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		this.getShell().setText("频道属性");
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
     * 
     */
    protected Control createDialogArea(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(data);
        //创建对录入框的监听
        /*KeyListener keyListener = new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				enableOK(false);
				if(title_Text.getText().trim().equals("")){
					setErrorMessage("频道名称不能为空！");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        };*/
        //只允许录入数字
        VerifyListener verifyListener = new VerifyListener(){
			public void verifyText(VerifyEvent arg0) {
				Pattern pattern = Pattern.compile("[0-9]\\d*");
				Matcher matcher = pattern.matcher(arg0.text);
				if(matcher.matches()) {
					arg0.doit = true;
				}
				else if(arg0.text.length()>0){
					arg0.doit = false;
				}
				else{
					arg0.doit = true;
				}
			}
        };
        //创建grid
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.marginWidth = 15;
        gridLayout.marginHeight = 10;
        gridLayout.makeColumnsEqualWidth = false;
        composite.setLayout(gridLayout);
        //
        Label label = new Label(composite, SWT.NONE);
        label.setText("频道名称：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));

        this.title_Text = new Text(composite,SWT.BORDER);
        this.title_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//        this.title_Text.addKeyListener(keyListener);
        this.title_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				enableOK(false);
				if(title_Text.getText().trim().equals("")){
					setErrorMessage("频道名称不能为空！");
				}
				else{
					setErrorMessage(null);
					enableOK(true);
				}
			}
        });
        //
        label = new Label(composite, SWT.NONE);
        label.setText("频道地址：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));

        this.xmlUrl_Text = new Text(composite,SWT.BORDER | SWT.READ_ONLY);
        this.xmlUrl_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        //
        label = new Label(composite, SWT.NONE);
        label.setText("更新间隔：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));

        this.updateTime_Combo = new Combo(composite,SWT.READ_ONLY);
        this.updateTime_Combo.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        for(Iterator it = UpdateTime.getData2().iterator();it.hasNext();){
        	HashMap map = (HashMap)it.next();
        	this.updateTime_Combo.add(map.get("desc").toString(), Integer.parseInt(map.get("time").toString()));
        }
        
        //
        label = new Label(composite, SWT.NONE);
        label.setText("保存条目：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));

        this.saveCount_Spinner = new Spinner(composite,SWT.BORDER);
        this.saveCount_Spinner.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,false,false));
        this.saveCount_Spinner.setMinimum(1);
        this.saveCount_Spinner.setMaximum(1000);
        
        //填充数据
        this.fillData();
        
        return parent;
    }

    /**
     * 填充数据
     */
	private void fillData() {
		//		
		this.title_Text.setText(this.outline.attributeValue("title"));
		
		this.xmlUrl_Text.setText(this.outline.attributeValue("xmlUrl"));
        this.xmlUrl_Text.setToolTipText("双击拷贝频道地址到剪切板");
        this.xmlUrl_Text.addMouseListener(new MouseListener(){
        	//双击拷贝值到剪切板
			public void mouseDoubleClick(MouseEvent arg0) {
				xmlUrl_Text.copy();
			}
			public void mouseDown(MouseEvent arg0) {
			}
			public void mouseUp(MouseEvent arg0) {
			}
        });
        
		this.updateTime_Combo.select(UpdateTime.getSelectionIndex(Integer.parseInt(this.outline.attributeValue("updateTime"))));		
        this.updateTime_Combo.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				System.out.println("--更改频道属性更新间隔："+arg0);
				enableOK(true);
			}
        });
        
        this.saveCount_Spinner.setSelection(Integer.parseInt(this.outline.attributeValue("saveCount")));		
        this.saveCount_Spinner.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				System.out.println("--更改频道属性保存条目："+arg0);
				enableOK(true);
			}
        });
        
	}
	
	/**
	 * 点击确定
	 */
	protected void okPressed(){
		//
    	this.outline.attribute("title").setText(this.title_Text.getText().trim());
    	this.outline.attribute("xmlUrl").setText(this.xmlUrl_Text.getText().trim());
    	this.outline.attribute("updateTime").setText(String.valueOf(UpdateTime.getUpdateTime(this.updateTime_Combo.getSelectionIndex())));
    	this.outline.attribute("saveCount").setText(String.valueOf(this.saveCount_Spinner.getSelection()));
    	//数据保存
    	RssXml2.saveXmlFile(this.channelNavigatorView.getDoc(), Messages.ChannelNavigator_Name);
    	//刷新视图
    	this.channelNavigatorView.refresh();
    	System.out.println("++修改频道属性："+ this.outline);
    	//对频道标题头进行更新
		DetailListView detailListView = (DetailListView)MyRcpUtil.getView(DetailListView.ID);
		detailListView.setPartName(this.title_Text.getText().trim());
		//对ie标题头进行更新
		if(detailListView.getSelection().isEmpty()){
			IEView iEView = (IEView)MyRcpUtil.getView(IEView.ID);
			iEView.setPartName(detailListView.getPartName());
		}
    	super.okPressed();
	}
    
}
