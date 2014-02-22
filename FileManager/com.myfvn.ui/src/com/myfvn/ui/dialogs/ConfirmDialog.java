package com.myfvn.ui.dialogs;

import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.core.internal.util.LayoutUtils;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.myfvn.ui.Activator;
import com.myfvn.ui.FvnUI;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ConfirmDialog extends TitleAreaDialog {
	private String _dialogHeaderMessage;
	private String _dialogMessage;
	private Button _neverAskAgainCheck;
	private String _title;
	private LocalResourceManager _resources;
	private String _confirmPrefKey;
	private String _buttonName;

	public ConfirmDialog(Shell $parentShell, String $title, String $dialogHeaderMessage, String $dialogMessage, String $confirmPrefKey) {
		this($parentShell, $title, $dialogHeaderMessage, $dialogMessage, Messages.ConfirmDialog_DELETE, $confirmPrefKey);
	}

	public ConfirmDialog(Shell $parentShell, String $title, String $dialogHeaderMessage, String $dialogMessage, String $okButtonName, String $confirmPrefKey) {
		super($parentShell);
		_title = $title;
		_dialogMessage = $dialogMessage;
		_dialogHeaderMessage = $dialogHeaderMessage;
		_buttonName = $okButtonName;
		_confirmPrefKey = $confirmPrefKey;
		_resources = new LocalResourceManager(JFaceResources.getResources());
	}

	@Override
	public boolean close() {
		_resources.dispose();
		return super.close();
	}

	protected void configureShell(Shell $newShell) {
		super.configureShell($newShell);
		$newShell.setText(_title);
	}

	@Override
	protected void initializeBounds() {
		super.initializeBounds();
		Point __bestSize = getShell().computeSize(convertHorizontalDLUsToPixels(FvnUI.MIN_DIALOG_WIDTH_DLU), SWT.DEFAULT);
		Point __location = getInitialLocation(__bestSize);
		getShell().setBounds(__location.x, __location.y, __bestSize.x, __bestSize.y);
	}

	@Override
	protected void createButtonsForButtonBar(Composite $parent) {
		createButton($parent, IDialogConstants.OK_ID, _buttonName, false);
		createButton($parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
		getButton(IDialogConstants.CANCEL_ID).setFocus();
	}

	protected String getTitleImage() {
		return "/icons/wizban/trash.gif"; //$NON-NLS-1$
	}

	@Override
	protected Control createDialogArea(Composite $parent) {
		/* Composite to hold all components */
		Composite __composite = new Composite((Composite) super.createDialogArea($parent), SWT.NONE);
		__composite.setLayout(LayoutUtils.createGridLayout(1, 5, 10));
		__composite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		/* Title */
		setTitle(Messages.ConfirmDialog_DELETE);

		/* Title Image */
		setTitleImage(CacheImage.getInstance().getImage(Activator.PLUGIN_ID, this.getTitleImage()));

		/* Title Message */
		setMessage(_dialogHeaderMessage, IMessageProvider.WARNING);

		/* Dialog Message */
		Label __dialogMessageLabel = new Label(__composite, SWT.WRAP);
		__dialogMessageLabel.setText(_dialogMessage);
		__dialogMessageLabel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		/* Spacer */
		new Label(__composite, SWT.NONE);

		/* Checkbox to disable confirm dialog */
		if (_confirmPrefKey != null) {
			_neverAskAgainCheck = new Button(__composite, SWT.CHECK);
			_neverAskAgainCheck.setText(Messages.ConfirmDialog_NEVER_ASK_AGAIN);
		}

		/* Holder for the separator to the OK and Cancel buttons */
		Composite __sepHolder = new Composite($parent, SWT.NONE);
		__sepHolder.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		__sepHolder.setLayout(LayoutUtils.createGridLayout(1, 0, 0));

		/* Separator */
		Label __separator = new Label(__sepHolder, SWT.SEPARATOR | SWT.HORIZONTAL);
		__separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		applyDialogFont(__composite);

		return __composite;
	}

}
