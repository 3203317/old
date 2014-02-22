package net.htjs.editor.designmap.editors.xml;

import org.eclipse.ui.texteditor.AbstractTextEditor;

/**
 * A simple text editor.
 * 
 * @see org.eclipse.ui.examples.rcp.texteditor.editors.SimpleDocumentProvider
 * @since 3.0
 */
public class SimpleEditor extends AbstractTextEditor {

	public SimpleEditor() {
		super();
		// make sure we inherit all the text editing commands (delete line etc).
		setKeyBindingScopes(new String[] { "org.eclipse.ui.textEditorScope" }); //$NON-NLS-1$
		internal_init();
	}

	/**
	 * Initializes the document provider and source viewer configuration. Called
	 * by the constructor. Subclasses may replace this method.
	 */
	protected void internal_init() {
		configureInsertMode(SMART_INSERT, false);
		setDocumentProvider(new SimpleDocumentProvider());
	}

}