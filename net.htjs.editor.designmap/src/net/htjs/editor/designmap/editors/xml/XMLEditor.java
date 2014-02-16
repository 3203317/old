package net.htjs.editor.designmap.editors.xml;

public class XMLEditor extends SimpleEditor {

	private ColorManager colorManager;

	protected void internal_init() {
		configureInsertMode(SMART_INSERT, false);
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

	public XMLEditor() {
//		setPartName("br");
//		setContentDescription("bra");
//		setTitle("index.xml");
		// TODO Auto-generated constructor stub
	}
}
