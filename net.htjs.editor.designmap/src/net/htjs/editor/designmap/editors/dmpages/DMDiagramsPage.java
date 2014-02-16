package net.htjs.editor.designmap.editors.dmpages;

import net.htjs.editor.designmap.Activator;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class DMDiagramsPage extends FormPage {
	
	public static final String ID = DMDiagramsPage.class.getName();
	private String title;
	private FormToolkit toolkit;
	private ScrolledForm form;

	public DMDiagramsPage(FormEditor editor, String title) {
		super(editor, ID, title);
		this.title = title;
	}
	
	protected void createFormContent(IManagedForm managedForm){
		this.toolkit = managedForm.getToolkit();
		this.form = managedForm.getForm();
		this.toolkit.decorateFormHeading(this.form.getForm());
		this.form.setText(this.title);
		this.form.setImage(Activator.getImageDescriptor("icons/diagrams_obj.gif").createImage());
		
//		new DMDiagramsEditor(this.form.getBody());
	}
}
