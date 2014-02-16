package net.htjs.editor.designmap.editors.dmpages;

import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.editors.DMMutiForm;

import org.dom4j.Document;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public class DMOverviewPage extends FormPage {

	public class BtnSelectionListener implements SelectionListener {
		public void widgetDefaultSelected(SelectionEvent arg0) {
		}
		public void widgetSelected(SelectionEvent arg0) {
			String val = "true";
			if(!validate_Radio_True.getSelection()){
				val = "false";
			}
	        //如果属性不存在则，创建一个新属性
	        if(xmlDoc.getRootElement().attribute("validate") == null){
	        	xmlDoc.getRootElement().addAttribute("validate", val);
	        }
	        //如果存在则判断当前属性值是否和选中的值相同，如果相同则函数返回
	        else if(xmlDoc.getRootElement().attributeValue("validate").trim().equals(val)){
	        	return;
	        }
	        xmlDoc.getRootElement().attribute("validate").setText(val);
	        dmMutiForm.xmlEditorDataReload();
		}
	}

	public static final String ID = DMOverviewPage.class.getName();
	
	private String title;
	private FormToolkit toolkit;
	private ScrolledForm form;

	private Color labColor;

	private Text namespace_Text;
	private Text strutsPath_Text;
	private Text springPath_Text;
	private Text ormPath_Text;
	private Button validate_Radio_True;
	private Button validate_Radio_False; 
	
	private Document xmlDoc;

	private IMessageManager messageManager;
	
	private DMMutiForm dmMutiForm;

	public DMOverviewPage(FormEditor editor, String title) {
		super(editor, ID, title);
		this.title = title;
		this.labColor = new Color(null,10,36,106);
		this.dmMutiForm = (DMMutiForm)editor;
	}
	
	protected void createFormContent(IManagedForm managedForm){
		this.toolkit = managedForm.getToolkit();
		this.form = managedForm.getForm();
		this.toolkit.decorateFormHeading(this.form.getForm());
		this.form.setText(this.title);
		this.form.setImage(Activator.getImageDescriptor("icons/plugin_mf_obj.gif").createImage());
		
		ColumnLayout layout = new ColumnLayout();
	    layout.topMargin = 12;
	    layout.bottomMargin = 5;
	    layout.leftMargin = 6;
	    layout.rightMargin = 6;

	    layout.horizontalSpacing = 20;
	    layout.verticalSpacing = 25;
	    layout.maxNumColumns = 2;
	    layout.minNumColumns = 2;
		
		this.form.getBody().setLayout(layout);

		this.messageManager = new ManagedForm(this.toolkit,this.form).getMessageManager();

		this.createSection1();
		this.createSection3();
		this.createSection2();
	}
	
	private void createSection1(){
		Section section = this.toolkit.createSection(this.form.getBody(), Section.TITLE_BAR | Section.DESCRIPTION | Section.EXPANDED);
		section.setText("General Information");
		section.setDescription("This section describes general information about this designMap.");
		
		Composite client = this.toolkit.createComposite(section);
		
		GridLayout layout = new GridLayout();
		layout.marginTop = 10;
		layout.numColumns = 2;
		layout.horizontalSpacing = 10;
		
		client.setLayout(layout);
		
		section.setClient(client);
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);

		//创建
		Label label = this.toolkit.createLabel(client, "namespace:");
		label.setForeground(this.labColor);
		
		this.namespace_Text = this.toolkit.createText(client, "");
		this.namespace_Text.setLayoutData(gd);
		
		//创建
		this.toolkit.createLabel(client, "validate:").setForeground(this.labColor);

		GridLayout group_layout = new GridLayout();
		group_layout.numColumns = 2;
		Composite group = this.toolkit.createComposite(client);
		group.setLayout(group_layout);

		this.validate_Radio_True = this.toolkit.createButton(group, "true", SWT.RADIO);
		this.validate_Radio_False = this.toolkit.createButton(group, "false", SWT.RADIO);
		

		//创建
		label = this.toolkit.createLabel(client, "strutsPath:");
		label.setForeground(this.labColor);
		
		this.strutsPath_Text = this.toolkit.createText(client, "");
		this.strutsPath_Text.setLayoutData(gd);

		//创建
		label = this.toolkit.createLabel(client, "springPath:");
		label.setForeground(this.labColor);
		
		this.springPath_Text = this.toolkit.createText(client, "");
		this.springPath_Text.setLayoutData(gd);

		//创建
		label = this.toolkit.createLabel(client, "ormPath:");
		label.setForeground(this.labColor);
		
		this.ormPath_Text = this.toolkit.createText(client, "");
		this.ormPath_Text.setLayoutData(gd);
		
		this.toolkit.paintBordersFor(client);
		
		section.addExpansionListener(new ExpansionAdapter(){
			public void expansionStateChanged(ExpansionEvent e){
				((ScrolledForm)form.getBody()).reflow(true);
			}
		});
		
	}
	
	private void createSection2(){
		Section section = this.toolkit.createSection(this.form.getBody(), Section.EXPANDED | Section.TITLE_BAR | Section.DESCRIPTION);
		section.setText("\u751f\u6210\u5de5\u5177");
		
		Composite client = this.toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		layout.numColumns = 2;
		
		client.setLayout(layout);
		
		section.setClient(client);
		
		//创建带有图片的超链接
		ImageHyperlink imageLink = this.toolkit.createImageHyperlink(client, SWT.WRAP);
		//设置超链接的图标
		imageLink.setImage(Activator.getImageDescriptor("icons/debug_persp.gif").createImage());
		//设置超链接的文本
		imageLink.setText("\u6784\u5efa\u4ee3\u7801");
		imageLink.addHyperlinkListener(new HyperlinkAdapter(){
			public void linkActivated(HyperlinkEvent e){
				GMFConsole.println("\u6fc0\u6d3b");
			}			
		});
		
		section.addExpansionListener(new ExpansionAdapter(){
			public void expansionStateChanged(ExpansionEvent e){
				
			}
		});
	}	
	
	private void createSection3(){
		Section section = this.toolkit.createSection(this.form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION | Section.EXPANDED);
		section.setText("Testing");
		section.setDescription("Test this designMap by Checking code safe.");
		
		Composite client = this.toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		layout.numColumns = 2;
		
		client.setLayout(layout);
		
		section.setClient(client);
		
		//创建带有图片的超链接
		ImageHyperlink imageLink = this.toolkit.createImageHyperlink(client, SWT.WRAP);
		//设置超链接的图标
		imageLink.setImage(Activator.getImageDescriptor("icons/debug_persp.gif").createImage());
		//设置超链接的文本
		imageLink.setText("Launch an checker");
		imageLink.addHyperlinkListener(new HyperlinkAdapter(){
			public void linkActivated(HyperlinkEvent e){
				GMFConsole.println("Test Me.");
			}			
		});
		
		section.addExpansionListener(new ExpansionAdapter(){
			public void expansionStateChanged(ExpansionEvent e){
				
			}
		});
	}
	
	public void onload(){
		if(this.xmlDoc == null) return;
		//namespace
		if(this.xmlDoc.getRootElement().attribute("namespace") != null){
			this.namespace_Text.setText(this.xmlDoc.getRootElement().attributeValue("namespace").trim());
		}
		
		this.namespace_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				if(xmlDoc.getRootElement().attribute("namespace") == null){
					xmlDoc.getRootElement().addAttribute("namespace",namespace_Text.getText().trim());
		        }
		        else{
		        	xmlDoc.getRootElement().attribute("namespace").setText(namespace_Text.getText().trim());
		        }
				dmMutiForm.xmlEditorDataReload();
				
				if(namespace_Text.getText().trim().equals("")){
//					form.setMessage("namespace不能为空!",IMessageProvider.ERROR);
//					messageManager.addMessage("空1", "空2", "空3",IMessageProvider.ERROR);
					messageManager.addMessage("arg0", "A value must be specified", "arg2", IMessageProvider.ERROR, namespace_Text);
					dmMutiForm.setError(true);
				}
				else{
//					form.setMessage(null, IMessageProvider.NONE);
					messageManager.removeMessages(namespace_Text);
					dmMutiForm.setError(false);
				}			
			}
		});

		//strutcBase
		if(this.xmlDoc.getRootElement().attribute("strutsPath") != null){
			this.strutsPath_Text.setText(this.xmlDoc.getRootElement().attributeValue("strutsPath").trim());
		}
		
		this.strutsPath_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				if(xmlDoc.getRootElement().attribute("strutsPath") == null){
					xmlDoc.getRootElement().addAttribute("strutsPath",strutsPath_Text.getText().trim());
		        }
		        else{
		        	xmlDoc.getRootElement().attribute("strutsPath").setText(strutsPath_Text.getText().trim());
		        }
				dmMutiForm.xmlEditorDataReload();
				
//				if(strutsBase_Text.getText().trim().equals("")){
////					form.setMessage("namespace不能为空!",IMessageProvider.ERROR);
////					messageManager.addMessage("空1", "空2", "空3",IMessageProvider.ERROR);
//					messageManager.addMessage("arg0", "A value must be specified", "arg2", IMessageProvider.ERROR, strutsBase_Text);
//					dmMutiForm.setError(true);
//				}
//				else{
////					form.setMessage(null, IMessageProvider.NONE);
//					messageManager.removeMessages(strutsBase_Text);
//					dmMutiForm.setError(false);
//				}			
			}
		});

		//springPath
		if(this.xmlDoc.getRootElement().attribute("springPath") != null){
			this.springPath_Text.setText(this.xmlDoc.getRootElement().attributeValue("springPath").trim());
		}
		
		this.springPath_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				if(xmlDoc.getRootElement().attribute("springPath") == null){
					xmlDoc.getRootElement().addAttribute("springPath",springPath_Text.getText().trim());
		        }
		        else{
		        	xmlDoc.getRootElement().attribute("springPath").setText(springPath_Text.getText().trim());
		        }
				dmMutiForm.xmlEditorDataReload();
				
//				if(springBase_Text.getText().trim().equals("")){
////					form.setMessage("namespace不能为空!",IMessageProvider.ERROR);
////					messageManager.addMessage("空1", "空2", "空3",IMessageProvider.ERROR);
//					messageManager.addMessage("arg0", "A value must be specified", "arg2", IMessageProvider.ERROR, springBase_Text);
//					dmMutiForm.setError(true);
//				}
//				else{
////					form.setMessage(null, IMessageProvider.NONE);
//					messageManager.removeMessages(springBase_Text);
//					dmMutiForm.setError(false);
//				}			
			}
		});

		//ormPath
		if(this.xmlDoc.getRootElement().attribute("ormPath") != null){
			this.ormPath_Text.setText(this.xmlDoc.getRootElement().attributeValue("ormPath").trim());
		}
		
		this.ormPath_Text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				if(xmlDoc.getRootElement().attribute("ormPath") == null){
					xmlDoc.getRootElement().addAttribute("ormPath",ormPath_Text.getText().trim());
		        }
		        else{
		        	xmlDoc.getRootElement().attribute("ormPath").setText(ormPath_Text.getText().trim());
		        }
				dmMutiForm.xmlEditorDataReload();
				
//				if(ormPath_Text.getText().trim().equals("")){
////					form.setMessage("namespace不能为空!",IMessageProvider.ERROR);
////					messageManager.addMessage("空1", "空2", "空3",IMessageProvider.ERROR);
//					messageManager.addMessage("arg0", "A value must be specified", "arg2", IMessageProvider.ERROR, ormPath_Text);
//					dmMutiForm.setError(true);
//				}
//				else{
////					form.setMessage(null, IMessageProvider.NONE);
//					messageManager.removeMessages(ormPath_Text);
//					dmMutiForm.setError(false);
//				}			
			}
		});
		if(this.xmlDoc.getRootElement().attribute("validate") != null){
			if(this.xmlDoc.getRootElement().attributeValue("validate").trim().equals("true")){
				this.validate_Radio_True.setSelection(true);
			}
			else{
				this.validate_Radio_False.setSelection(true);
			}
		}
		
		//添加按钮事件
		BtnSelectionListener bsl = new BtnSelectionListener();
		this.validate_Radio_True.addSelectionListener(bsl);
		this.validate_Radio_False.addSelectionListener(bsl);
		
	}

	public Document getXmlDoc() {
		return xmlDoc;
	}

	public void setXmlDoc(Document xmlDoc) {
		this.xmlDoc = xmlDoc;
	}
}
