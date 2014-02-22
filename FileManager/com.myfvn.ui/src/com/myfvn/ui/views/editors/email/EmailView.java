package com.myfvn.ui.views.editors.email;

import net.foreworld.utils.rcp.core.internal.util.LayoutUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.myfvn.ui.FvnUI;
import com.myfvn.ui.FvnUI.Layout;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EmailView extends EditorPart implements IReusableEditor {

	/** ID of this EditorPart */
	public static final String ID = "com.myfvn.ui.EmailView"; //$NON-NLS-1$

	/* Editor Data */
	private EmailViewInput _input;
	private IEditorSite _editorSite;
	private EmailViewSite _emailViewSite;

	/* Settings */
	private int _initialWeights[] = { 372, 621 };
	Layout _layout;

	/* Listeners */
	private IPartListener2 _partListener;

	/* Misc */
	private Composite _parent;
	private Composite _rootComposite;
	private LocalResourceManager _resourceManager;
	private boolean _created;
	private ImageDescriptor _titleImageDescriptor;
	private SashForm _sashForm;

	@Override
	public void doSave(IProgressMonitor $monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite $site, IEditorInput $input) throws PartInitException {
		Assert.isTrue($input instanceof EmailViewInput);

		this._editorSite = $site;
		this._emailViewSite = new EmailViewSite(this, $site);
		this.setSite($site);
		this._resourceManager = new LocalResourceManager(JFaceResources.getResources());

		/* Load Settings */
		this.loadSettings((EmailViewInput) $input);

		/* Apply Input */
		this.setInput($input);

		/* Hook into Global Actions */
		createGlobalActions();
		setGlobalActions();

		/* Register Listeners */
		registerListeners();
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite $parent) {
		this._created = true;
		this._parent = $parent;

		/* Top-Most root Composite in Editor */
		this._rootComposite = new Composite(_parent, SWT.NONE);
		_rootComposite.setLayout(LayoutUtils.createGridLayout(1, 0, 0));
		((GridLayout) _rootComposite.getLayout()).verticalSpacing = 0;

		/* SashForm dividing Email and News View */
		boolean __useClassicLayout = (_layout != Layout.VERTICAL);
		_sashForm = new SashForm(_rootComposite, (__useClassicLayout ? SWT.VERTICAL : SWT.HORIZONTAL) | SWT.SMOOTH);
		_sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		/* SashForm weights */

	}

	@Override
	public void setFocus() {

	}

	public void setInput(IEditorInput $input) {
		Assert.isTrue($input instanceof EmailViewInput);

		/*
		 * Quickly cancel any caching Job and dispose content provider since
		 * input changed
		 */
		if (this._created) {

		}

		super.setInput($input);
		this._input = (EmailViewInput) $input;

		/* Update UI of Email-View if new Editor */
		if (!_created) {
			this.updateTab(this._input);
		}

	}

	private void loadSettings(EmailViewInput $input) {

	}

	private void createGlobalActions() {

	}

	private void setGlobalActions() {

	}

	private void registerListeners() {
		_partListener = new IPartListener2() {
			public void partActivated(IWorkbenchPartReference $partRef) {
				if (EmailView.this.equals($partRef.getPart(false))) {
					FvnUI.updateWindowTitle(_input != null ? _input.getEmail() : null);
				}
			}

			public void partBroughtToTop(IWorkbenchPartReference $partRef) {
				if (EmailView.this.equals($partRef.getPart(false))) {
					FvnUI.updateWindowTitle(_input != null ? _input.getEmail() : null);
				}
			}

			public void partClosed(IWorkbenchPartReference $partRef) {
				if (EmailView.this.equals($partRef.getPart(false))) {
					FvnUI.updateWindowTitle(_input != null ? _input.getEmail() : null);
				}
			}

			public void partDeactivated(IWorkbenchPartReference $partRef) {
				// TODO Auto-generated method stub

			}

			public void partHidden(IWorkbenchPartReference $partRef) {
				// TODO Auto-generated method stub

			}

			public void partInputChanged(IWorkbenchPartReference $partRef) {
				if (EmailView.this.equals($partRef.getPart(false))) {
					FvnUI.updateWindowTitle(_input != null ? _input.getEmail() : null);
				}
			}

			public void partOpened(IWorkbenchPartReference $partRef) {
				if (EmailView.this.equals($partRef.getPart(false))) {
					FvnUI.updateWindowTitle(_input != null ? _input.getEmail() : null);
				}
			}

			public void partVisible(IWorkbenchPartReference $partRef) {
				if (EmailView.this.equals($partRef.getPart(false))) {
					FvnUI.updateWindowTitle(_input != null ? _input.getEmail() : null);
				}
			}

		};
		_editorSite.getPage().addPartListener(_partListener);
	}

	private void unregisterListeners() {

	}

	@Override
	public void dispose() {
		saveSettings();
		unregisterListeners();

		super.dispose();
	}

	private void saveSettings() {

	}

	/* Update Title and Image of the EmailView's Tab */
	private void updateTab(EmailViewInput $input) {
		this.setPartName($input.getName());
		this._titleImageDescriptor = $input.getImageDescriptor();
		this.setTitleImage(FvnUI.getImage(this._resourceManager, this._titleImageDescriptor));
	}

}
