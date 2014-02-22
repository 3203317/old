package net.foreworld.rss2.dialogs.wizards;

import net.foreworld.rss2.Activator;
import net.foreworld.rss2.monitor.ClipBoardMonitor;
import net.foreworld.rss2.utils.StringUtils;

import org.dom4j.Document;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 8, 2008 4:09:46 PM
 */
public class AddChannelWizardPage1 extends WizardPage {
	private Label msg_Label;
	private Text xmlUrl_Text;
	private ProgressBar progressBar;
	private Document newChannel;

	public Document getNewChannel() {
		return newChannel;
	}

	public void setNewChannel(Document newChannel) {
		this.newChannel = newChannel;
	}

	protected AddChannelWizardPage1(String pageName) {
		super(pageName);
		this.setTitle(pageName);
	}

	public void createControl(Composite arg0) {
		Composite composite = new Composite(arg0, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH | GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        composite.setLayoutData(data);
        
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 0;//grid���
        layout.marginHeight = 0;//grid���
        layout.marginTop = 20;
        layout.marginLeft = 35;
        layout.marginRight = 35;
        layout.makeColumnsEqualWidth = false;
        composite.setLayout(layout);
        
        Label lab = new Label(composite,SWT.NONE);
        lab.setText("Ƶ����ַ��");
        GridData gd = new GridData();
        gd.verticalSpan = 3;
        lab.setLayoutData(gd);
        
        CLabel label = new CLabel(composite, SWT.LEFT);
        label.setText("��������������Ƶ�������ӵ�ַ");
        label.setImage(Activator.getImageDescriptor("icons/xml1.gif").createImage());
        
        this.xmlUrl_Text = new Text(composite,SWT.BORDER);
        this.xmlUrl_Text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        this.xmlUrl_Text.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				if(xmlUrl_Text.getText().trim().equals("")){
					setErrorMessage("Ƶ����ַ����Ϊ�գ�");
					return;
				}
				if(StringUtils.getURLString(xmlUrl_Text.getText().trim()).equals("")){
					setErrorMessage("������Ч��URL��ַ��");
				}
				else{
					setErrorMessage(null);
				}
			}
        });
        this.xmlUrl_Text.setText(StringUtils.getURLString(ClipBoardMonitor.getInstance().getLast().trim()));
        

        label = new CLabel(composite, SWT.NONE);
        label.setText("����������鿴������Ƶ����");
        label.setLayoutData(new GridData(SWT.RIGHT,SWT.RIGHT,false,false,1,1));
        
        
        //��Ϣ��ʾ
        this.msg_Label = new Label(composite, SWT.NONE);
        gd = new GridData();
        gd.horizontalSpan = 2;
        this.msg_Label.setLayoutData(gd);

        //������
        this.progressBar = new ProgressBar(composite,SWT.NONE | SWT.SMOOTH);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        this.progressBar.setLayoutData(gd);
        this.progressBar.setMinimum(0);
        this.progressBar.setMaximum(2);
        this.progressBar.setVisible(false);
        
		this.setControl(composite);
	}


	/**
	 * �������չʾ������һ�ֱ��ķ�ʽ��������� ȥ��_Test
	 * @param arg0
	 */
	public void createControl_Test(Composite arg0) {
		Composite composite = new Composite(arg0, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH | GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        composite.setLayoutData(data);
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginWidth = 0;//grid���
        gridLayout.marginHeight = 0;//grid���
        gridLayout.marginTop = 20;
        gridLayout.marginLeft = 35;
        gridLayout.marginRight = 35;
        gridLayout.makeColumnsEqualWidth = false;
        composite.setLayout(gridLayout);
        
        new Label(composite,SWT.NONE);
//        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        Label label = new Label(composite, SWT.NONE);
        label.setText("��������������Ƶ�������ӵ�ַ");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false, 2, 1));//���������Ĳ��ֻ���Ҫ����������
        
        label = new Label(composite, SWT.NONE);
        label.setText("Ƶ����ַ��");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.xmlUrl_Text = new Text(composite,SWT.BORDER);
        this.xmlUrl_Text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,2,1));
        this.xmlUrl_Text.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				if(xmlUrl_Text.getText().trim().equals("")){
					setErrorMessage("Ƶ����ַ����Ϊ�գ�");
					return;
				}
				if(StringUtils.getURLString(xmlUrl_Text.getText().trim()).equals("")){
					setErrorMessage("������Ч��URL��ַ��");
				}
				else{
					setErrorMessage(null);
				}
			}
        });
        this.xmlUrl_Text.setText(StringUtils.getURLString(ClipBoardMonitor.getInstance().getLast().trim()));
        

        label = new Label(composite, SWT.NONE);
        label.setText("����������鿴������Ƶ����");
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.RIGHT , false, false,3,1));
        
        new Label(composite,SWT.NONE).setLayoutData(new GridData(SWT.RIGHT, SWT.RIGHT , false, false,3,1));
        
        //��Ϣ��ʾ
        this.msg_Label = new Label(composite, SWT.NONE);
        this.msg_Label.setLayoutData(new GridData(SWT.FILL, SWT.LEFT , false, false,3,1));

        //������
        this.progressBar = new ProgressBar(composite,SWT.HORIZONTAL | SWT.SMOOTH);
        this.progressBar.setLayoutData(new GridData(SWT.FILL, SWT.LEFT , false, false,3,1));
        this.progressBar.setMinimum(0);
        this.progressBar.setMaximum(2);
        this.progressBar.setVisible(false);
        
		this.setControl(composite);
	}

	public Label getMsg_Label() {
		return msg_Label;
	}

	public void setMsg_Label(Label msg_Label) {
		this.msg_Label = msg_Label;
	}

	public Text getXmlUrl_Text() {
		return xmlUrl_Text;
	}

	public void setXmlUrl_Text(Text xmlUrl_Text) {
		this.xmlUrl_Text = xmlUrl_Text;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

}
