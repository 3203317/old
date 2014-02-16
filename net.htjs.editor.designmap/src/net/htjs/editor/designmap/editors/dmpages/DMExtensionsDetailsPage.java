package net.htjs.editor.designmap.editors.dmpages;

import java.util.Iterator;
import java.util.List;

import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.DMXsd;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.editors.DMMutiForm;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class DMExtensionsDetailsPage implements IDetailsPage {
	
	public class BtnSelectionListener implements SelectionListener {
		private String attributeName;
		
		public BtnSelectionListener(String attributeName){
			this.attributeName = attributeName;
		}
		public void widgetDefaultSelected(SelectionEvent arg0) {
		}
		public void widgetSelected(SelectionEvent arg0) {
			Button bt = (Button)arg0.getSource();
			
			//如果属性不存在则，创建一个新属性
	        if(element.attribute(this.attributeName) == null){
	        	element.addAttribute(this.attributeName, bt.getText());
	        }
	        //如果存在则判断当前属性值是否和选中的值相同，如果相同则函数返回
	        else if(element.attributeValue(this.attributeName).trim().equals(bt.getText())){
	        	return;
	        }
	        element.attribute(this.attributeName).setText(bt.getText());
	        dmMutiForm.xmlEditorDataReload();
		}
	}

	private IManagedForm mform;
	private Element element;
	private Section rightSection;
	private Composite client;
	private Composite parent;
	
	private FormToolkit toolkit;
	private Color labColor;

	private IMessageManager messageManager;

	private DMMutiForm dmMutiForm;
	
	public DMExtensionsDetailsPage(FormEditor editor){
		this.dmMutiForm = (DMMutiForm)editor;		
	}
	
	public void createContents(Composite parent) {
		this.parent = parent;
	    this.labColor = new Color(null, 10, 36, 106);
		//设置父类面板的布局
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 12;
		layout.leftMargin = 6;
		layout.rightMargin = 6;
		layout.bottomMargin = 6;
		this.parent.setLayout(layout);
		//获得表单工具对象
		this.toolkit = this.mform.getToolkit();
		
		//创建Detail的内容区
		this.rightSection = this.toolkit.createSection(this.parent, Section.DESCRIPTION|Section.TITLE_BAR);
		this.rightSection.setText("Extension Details");
		this.rightSection.setDescription("Set the properties of the selected extension. Required fields are denoted by \"*\".");
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		this.rightSection.setLayoutData(td);
		//创建内容区的所设置的面板
		this.client = toolkit.createComposite(this.rightSection);
		this.rightSection.setClient(this.client);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		gridLayout.marginTop = 10;
		gridLayout.numColumns = 2;
		gridLayout.marginBottom = 5;
		gridLayout.marginRight = 10;
		this.client.setLayout(gridLayout);
        this.toolkit.paintBordersFor(this.client);	
        
        this.messageManager = new ManagedForm(this.toolkit,this.mform.getForm()).getMessageManager();
	}

	public void initialize(IManagedForm form) {
		this.mform = form ;
	}
	public void dispose() {
		
	}
	public boolean isDirty() {
		return false;
	}
	public void commit(boolean onSave) {
		
	}
	public boolean setFormInput(Object input) {
		return false;
	}
	public void setFocus() {
		
	}
	public boolean isStale() {
		return false;
	}
	public void refresh() {

	}
	//当Master区域选中事件发生时
	public void selectionChanged(IFormPart part, ISelection selection) {
		//首先获得选中的对象
		IStructuredSelection currentSelection = (IStructuredSelection)selection;
		//判断当前的对象是否和选中的对象一致，如果一致则返回
		if (currentSelection.size() == 1){
			Element element = (Element)currentSelection.getFirstElement();
			if(this.element == element){
				GMFConsole.println("Selection is same...");
				return;
			}
			this.element = element;
		}
		GMFConsole.println(currentSelection.toString());
		this.update();
	}
	
	/**
	 * 刷新组件
	 */
	private void update (){		
		//删除原有的组件
		Control[] controls = this.client.getChildren();
		for(int i=0;i<controls.length;i++){
			controls[i].dispose();
		}
		GridData tgd = new GridData(GridData.FILL_HORIZONTAL);
		tgd.heightHint = 50;
		this.toolkit.createLabel(this.client, "text:", SWT.FILL).setForeground(this.labColor);		
		//元素值
		final Text textarea = this.toolkit.createText(this.client, this.element.getTextTrim(), SWT.WRAP|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		textarea.setLayoutData(tgd);
		textarea.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent arg0) {
				//赋值
				element.setText("");
				element.addCDATA(textarea.getText().trim());
				dmMutiForm.xmlEditorDataReload();
			}
		});
		
		
		List list = DMXsd.getAttribute(Activator.getXSD(), this.element.getPath());
		
		if(list == null) {
			this.clientUpdate();
			return;
		}

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);	
		//combo样式高度
		GridData cgd = new GridData(GridData.FILL_HORIZONTAL);	
		cgd.heightHint = 15;
		
		for(Iterator it = list.iterator(); it.hasNext();){
			final Element ele = (Element)it.next();

			GMFConsole.println(ele.asXML());
			
			this.toolkit.createLabel(this.client, ele.attributeValue("name")+":", SWT.FILL).setForeground(this.labColor);
						
			if(ele.attributeValue("type").equals("xs:string")){
				String val = "";
				if(this.element.attribute(ele.attributeValue("name")) != null){
					val = this.element.attributeValue(ele.attributeValue("name"));
				}
				final Text text = this.toolkit.createText(this.client, val);
				text.setLayoutData(gd);
				
				/**
				 * 文本框添加事件
				 */
				text.addModifyListener(new ModifyListener(){
					public void modifyText(ModifyEvent arg0) {
						//赋值
						if(element.attribute(ele.attributeValue("name")) == null){
							element.addAttribute(ele.attributeValue("name"), text.getText());
						}
						else{
							element.attribute(ele.attributeValue("name")).setValue(text.getText());
						}
						dmMutiForm.xmlEditorDataReload();
						//验证
						if(ele.attribute("use") != null){
							String useval = ele.attributeValue("use").trim();
							if(useval.equals("required")){
								if(text.getText().trim().equals("")){
									messageManager.addMessage("arg0", "A value must be specified", "arg2", IMessageProvider.ERROR, text);
									dmMutiForm.setError(true);
								}
								else{
									messageManager.removeMessages(text);
									dmMutiForm.setError(false);
								}
							}
						}
					}
				});
			}
			else if(ele.attributeValue("type").equals("booleanType")){
				Composite group = this.toolkit.createComposite(this.client);
	            GridLayout gl = new GridLayout();
	            gl.numColumns = 2;
	            group.setLayout(gl);
	            Button btntrue = toolkit.createButton(group, "true", SWT.RADIO);
	            Button btnfalse = toolkit.createButton(group, "false", SWT.RADIO);
	            
	            if(this.element.attribute(ele.attributeValue("name")) != null){
	            	if(this.element.attributeValue(ele.attributeValue("name")).equals("true")){
	            		btntrue.setSelection(true);
	            	}
	            	else{
	            		btnfalse.setSelection(true);
	            	}
	            }
	            
	    		BtnSelectionListener bsl = new BtnSelectionListener(ele.attributeValue("name"));
	    		btntrue.addSelectionListener(bsl);
	    		btnfalse.addSelectionListener(bsl);
			}
			else if(ele.attributeValue("type").equals("cacheLevelType")){
//				Spinner spinner = new Spinner(this.client, SWT.NONE);
//	            spinner.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
//	            spinner.setData(FormToolkit.KEY_DRAW_BORDER,FormToolkit.TEXT_BORDER);
//	            spinner.setMaximum(9);
//	            spinner.setMinimum(1);
//	            toolkit.paintBordersFor(spinner);
				
				CCombo ccombo = new CCombo(this.client,SWT.FLAT);
//		        combo.setLayoutData(new GridData(SWT.FILL, SWT.NONE , false, false));
				ccombo.setLayoutData(cgd);
		        ccombo.setData(FormToolkit.KEY_DRAW_BORDER,FormToolkit.TEXT_BORDER);
		        
			}
		}
		this.clientUpdate();
	}
	
	/**
	 * 布局刷新
	 */
	private void clientUpdate(){
		this.client.update();
		this.client.redraw();
		this.client.layout(true);
		this.parent.update();
		this.parent.redraw();
		this.parent.layout(true);
		this.mform.getForm().reflow(true);
        this.mform.reflow(true);
        GMFConsole.println("Form layout update complete...");
	}
}

