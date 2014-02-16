package net.htjs.editor.designmap.editors.dmpages;

import net.htjs.editor.designmap.Activator;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class DMExtensionsPage extends FormPage {
	
	public static final String ID = DMExtensionsPage.class.getName();
	
	private String title;
	private FormToolkit toolkit;
	private ScrolledForm form;
	
	private DMExtensionsMasterDetailsBlock masterDetailsBlock;

	public DMExtensionsPage(FormEditor editor, String title) {
		super(editor, ID, title);
		this.title = title;		
		this.masterDetailsBlock = new DMExtensionsMasterDetailsBlock(this,editor);
	}
	
	protected void createFormContent(IManagedForm managedForm){
		this.toolkit = managedForm.getToolkit();
		this.form = managedForm.getForm();
		this.toolkit.decorateFormHeading(this.form.getForm());
		this.form.setText(this.title);
		this.form.setImage(Activator.getImageDescriptor("icons/extensions_obj.gif").createImage());
		
		//该方法负责创建master和detail区域，尽量在最后调用
		this.masterDetailsBlock.createContent(managedForm);
	}

	public DMExtensionsMasterDetailsBlock getMasterDetailsBlock() {
		return masterDetailsBlock;
	}

	public void setMasterDetailsBlock(DMExtensionsMasterDetailsBlock masterDetailsBlock) {
		this.masterDetailsBlock = masterDetailsBlock;
	}
}
