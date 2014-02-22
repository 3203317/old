package net.foreworld.utils.rcp;

import net.foreworld.utils.StringUtil;
import net.foreworld.utils.rcp.dialogs.CustomWizardDialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class RcpUI {

	/* Packed Wizard Width per OS (in DLUs) */
	private static final int WINDOWS_PACKED_WIZARD_WIDTH = 380;
	private static final int LINUX_PACKED_WIZARD_WIDTH = 370;
	private static final int MAC_PACKED_WIZARD_WIDTH = 300;

	/**
	 * 
	 * @param $shell
	 * @param $wizard
	 * @param $modal
	 * @param $needsProgressPart
	 * @param $dialogSettingsKey
	 * @param $dialogSettings
	 * @param $IS_WINDOWS
	 * @param $IS_LINUX
	 */
	public static void openWizard(Shell $shell, Wizard $wizard, final boolean $modal, final boolean $needsProgressPart, final String $dialogSettingsKey, IDialogSettings $dialogSettings, boolean $IS_WINDOWS, boolean $IS_LINUX) {

		openWizard($shell, $wizard, $modal, $needsProgressPart, $dialogSettingsKey, false, null, $dialogSettings, $IS_WINDOWS, $IS_LINUX);
	}

	/**
	 * 
	 * @param $shell
	 * @param $wizard
	 * @param $modal
	 * @param $needsProgressPart
	 * @param $dialogSettingsKey
	 * @param $pack
	 * @param $finishLabel
	 * @param $dialogSettings
	 * @param $IS_WINDOWS
	 * @param $IS_LINUX
	 */
	public static void openWizard(Shell $shell, Wizard $wizard, final boolean $modal, final boolean $needsProgressPart, final String $dialogSettingsKey, final boolean $pack, final String $finishLabel, final IDialogSettings $dialogSettings, final boolean $IS_WINDOWS, final boolean $IS_LINUX) {

		CustomWizardDialog __dialog = new CustomWizardDialog($shell, $wizard) {
			private ProgressMonitorPart ___progressMonitorPart;

			protected boolean isResizable() {
				return true;
			}

			protected Control createDialogArea(Composite $parent) {
				Control ____control = super.createDialogArea($parent);
				if (___progressMonitorPart != null && !$needsProgressPart)
					((GridData) ___progressMonitorPart.getLayoutData()).exclude = true;
				return ____control;
			}

			public boolean close() {
				___progressMonitorPart = null;
				return super.close();
			}

			protected ProgressMonitorPart createProgressMonitorPart(Composite $composite, GridLayout $pmlayout) {
				___progressMonitorPart = super.createProgressMonitorPart($composite, $pmlayout);
				return ___progressMonitorPart;
			}

			protected IDialogSettings getDialogBoundsSettings() {
				if ($dialogSettingsKey != null) {
					IDialogSettings ____settings = $dialogSettings;
					IDialogSettings ____section = ____settings.getSection($dialogSettingsKey);
					if (____section != null)
						return ____section;

					return ____settings.addNewSection($dialogSettingsKey);
				}

				return super.getDialogBoundsSettings();
			}

			protected int getShellStyle() {
				if ($modal)
					return super.getShellStyle();

				return SWT.TITLE | SWT.BORDER | SWT.MIN | SWT.RESIZE | SWT.CLOSE | getDefaultOrientation();
			}

			protected int getDialogBoundsStrategy() {
				return DIALOG_PERSISTSIZE;
			}

			protected Button createButton(Composite $parent, int $id, String $label, boolean $defaultButton) {
				if (IDialogConstants.FINISH_ID == $id && StringUtil.isSet($finishLabel))
					$label = $finishLabel;

				return super.createButton($parent, $id, $label, $defaultButton);
			}

			protected Point getInitialSize() {
				if ($pack) {
					int ____width = $IS_WINDOWS ? WINDOWS_PACKED_WIZARD_WIDTH : $IS_LINUX ? LINUX_PACKED_WIZARD_WIDTH : MAC_PACKED_WIZARD_WIDTH;
					return getShell().computeSize(convertHorizontalDLUsToPixels(____width), SWT.DEFAULT, true);
				}

				return super.getInitialSize();
			}
		};
		__dialog.setMinimumPageSize(0, 0);
		__dialog.create();
		__dialog.open();
	}
}
