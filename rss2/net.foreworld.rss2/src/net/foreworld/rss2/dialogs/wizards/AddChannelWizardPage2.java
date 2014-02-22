package net.foreworld.rss2.dialogs.wizards;

import java.util.HashMap;
import java.util.Iterator;

import net.foreworld.rss2.db.UpdateTime;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 8, 2008 4:10:18 PM
 */
public class AddChannelWizardPage2 extends WizardPage {
	private Text channelName_Text;
	private Spinner saveCount_Spinner;
//	private ComboViewer updateTime_ComboViewer;
	private Combo updateTime_Combo;

	public Spinner getSaveCount_Spinner() {
		return saveCount_Spinner;
	}
	public void setSaveCount_Spinner(Spinner saveCount_Spinner) {
		this.saveCount_Spinner = saveCount_Spinner;
	}
	public Text getChannelName_Text() {
		return channelName_Text;
	}
	public void setChannelName_Text(Text channelName_Text) {
		this.channelName_Text = channelName_Text;
	}
	protected AddChannelWizardPage2(String pageName) {
		super(pageName);
		this.setTitle(pageName);
	}
	public void createControl(Composite arg0) {
		Composite composite = new Composite(arg0, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(data);
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.marginTop = 20;
        gridLayout.marginLeft = 35;
        gridLayout.marginRight = 35;
        gridLayout.makeColumnsEqualWidth = false;
        composite.setLayout(gridLayout);
        
        Label label = new Label(composite, SWT.NONE);
        label.setText("频道名称：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.channelName_Text = new Text(composite,SWT.BORDER);
        this.channelName_Text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
        this.channelName_Text.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				if(channelName_Text.getText().trim().equals("")){
					setErrorMessage("频道名称不能为空！");
				}
				else{
					setErrorMessage(null);
				}
			}
        });
        
        new Label(composite,SWT.NONE).setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false,2,1));
        
        label = new Label(composite, SWT.NONE);
        label.setText("更新间隔：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
//        this.updateTime_ComboViewer = new ComboViewer(composite,SWT.BORDER);
//        this.updateTime_ComboViewer.setContentProvider(new UpdateTimeContentProvider());
//        this.updateTime_ComboViewer.setLabelProvider(new UpdateTimeLabelProvider());
//        this.updateTime_ComboViewer.setInput(UpdateTime.getData());
        
        this.updateTime_Combo = new Combo(composite,SWT.READ_ONLY);
        this.updateTime_Combo.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        for(Iterator it = UpdateTime.getData2().iterator();it.hasNext();){
        	HashMap map = (HashMap)it.next();
        	this.updateTime_Combo.add(map.get("desc").toString(), Integer.parseInt(map.get("time").toString()));
        	
        }
        this.updateTime_Combo.select(2);
        
        new Label(composite,SWT.NONE).setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false,2,1));

        label = new Label(composite, SWT.NONE);
        label.setText("保存条目：");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.LEFT , false, false));
        
        this.saveCount_Spinner = new Spinner(composite,SWT.BORDER);
        this.saveCount_Spinner.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,false,false));
        this.saveCount_Spinner.setMinimum(1);
        this.saveCount_Spinner.setMaximum(1000);
        this.saveCount_Spinner.setSelection(500);

		this.setControl(composite);
	}
	public Combo getUpdateTime_Combo() {
		return updateTime_Combo;
	}
	public void setUpdateTime_Combo(Combo updateTime_Combo) {
		this.updateTime_Combo = updateTime_Combo;
	}

}
