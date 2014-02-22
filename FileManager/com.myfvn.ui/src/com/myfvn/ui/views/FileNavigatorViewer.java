package com.myfvn.ui.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

public class FileNavigatorViewer extends TreeViewer {
	
	private final FileNavigatorView _view;

	public FileNavigatorViewer(FileNavigatorView $view, Composite $parent, int $style){
		super($parent, $style);
		
		this._view = $view;
	}
	
	/**********************************************/
}
