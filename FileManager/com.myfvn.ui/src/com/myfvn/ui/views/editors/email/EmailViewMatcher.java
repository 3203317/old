package com.myfvn.ui.views.editors.email;

import java.util.logging.Logger;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EmailViewMatcher implements IEditorMatchingStrategy {

	final Logger _logger = Logger.getLogger(EmailViewMatcher.class.getName());

	public boolean matches(IEditorReference $editorRef, IEditorInput $input) {
		/* Require EmailViewInput */
		if (!($input instanceof EmailViewInput))
			return false;

		try {
			return $editorRef.getEditorInput().equals($input);
		} catch (PartInitException $ex) {
			_logger.warning($ex.getMessage());
			return false;
		}
	}

}
