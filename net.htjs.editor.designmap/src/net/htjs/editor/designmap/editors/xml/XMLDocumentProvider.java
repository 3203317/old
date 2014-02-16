package net.htjs.editor.designmap.editors.xml;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.IAnnotationModel;


public class XMLDocumentProvider extends SimpleDocumentProvider {

	protected void setupDocument(IDocument document) {
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new XMLPartitionScanner(),
					new String[] {
						XMLPartitionScanner.XML_TAG,
						XMLPartitionScanner.XML_COMMENT });
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
	}

	protected IAnnotationModel createAnnotationModel(Object element) throws CoreException {
		return null;
	}
}