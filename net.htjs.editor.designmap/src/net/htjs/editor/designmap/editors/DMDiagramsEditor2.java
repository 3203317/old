package net.htjs.editor.designmap.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class DMDiagramsEditor2 extends TextEditor {

	private ColorManager colorManager;

	public DMDiagramsEditor2() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
