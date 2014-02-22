package com.myfvn.ui.views;

import java.util.logging.Logger;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.IEmailbox;
import com.myfvn.core.persist.IUser;
import com.myfvn.ui.FvnUI;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class FileNavigatorLabelProvider extends CellLabelProvider {
	final Logger _logger = Logger.getLogger(FileNavigatorLabelProvider.class
			.getName());

	private LocalResourceManager _resources;
	private boolean _indicateState;
	private boolean _useDialogFont;

	private Image _emailIcon;
	private Image _emailboxIcon;
	private Image _attachmentIcon;

	public FileNavigatorLabelProvider() {
		this(true);
	}

	public FileNavigatorLabelProvider(boolean $indicateState) {
		this($indicateState, false);
	}

	public FileNavigatorLabelProvider(boolean $indicateState,
			boolean $useDialogFont) {
		_indicateState = $indicateState;
		_useDialogFont = $useDialogFont;
		_resources = new LocalResourceManager(JFaceResources.getResources());
		this.createResources();
	}

	private void createResources() {
		this._emailIcon = FvnUI.getImage(this._resources, FvnUI.FOLDER_NEW);
		this._emailboxIcon = this._emailIcon;
		this._attachmentIcon = FvnUI
				.getImage(this._resources, FvnUI.ATTACHMENT);
	}

	@Override
	public void update(ViewerCell $cell) {
		Object __element = $cell.getElement();

		if (__element instanceof IEmail) {
			IEmail ___email = (IEmail) __element;
			$cell.setText(___email.getName());
			if (___email.getChildren().isEmpty()) {
				$cell.setImage(FvnUI.getImage(this._resources, FvnUI.FOLDER));
			} else {
				$cell.setImage(this._emailIcon);
			}
		} else if (__element instanceof IAttachment) {
			IAttachment ___attachment = (IAttachment) __element;
			$cell.setText(___attachment.getName());
			$cell.setImage(this._attachmentIcon);
		} else if (__element instanceof IEmailbox) {
			IEmailbox ___emailbox = (IEmailbox) __element;
			$cell.setText(___emailbox.getName());
			$cell.setImage(this._emailboxIcon);
		} else if (__element instanceof IUser) {
			IUser ___user = (IUser) __element;
			$cell.setText(___user.getName());
		}
	}
}
