package net.foreworld.rss2.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class IMPerspective implements IPerspectiveFactory {
	
	public static final String ID = IMPerspective.class.getName();

	public void createInitialLayout(IPageLayout arg0) {
		String editorArea = arg0.getEditorArea();
		arg0.setEditorAreaVisible(false);
		arg0.setFixed(false);
	}

}
