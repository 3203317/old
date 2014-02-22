package com.myfvn.ui.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TransferData;

public class FileNavigatorDNDImpl extends ViewerDropAdapter implements
		DragSourceListener {
	
	private final FileNavigatorView _view;

	protected FileNavigatorDNDImpl(FileNavigatorView $view, Viewer $viewer) {
		super($viewer);
		
		this._view = $view;
	}

	public boolean performDrop(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateDrop(Object arg0, int arg1, TransferData arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	public void dragFinished(DragSourceEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void dragSetData(DragSourceEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void dragStart(DragSourceEvent arg0) {
		// TODO Auto-generated method stub

	}

}
