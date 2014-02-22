package com.myfvn.ui.preferences;

import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.core.internal.util.CColumnLayoutData;
import net.foreworld.utils.rcp.core.internal.util.CTable;
import net.foreworld.utils.rcp.core.internal.util.LayoutUtils;
import net.foreworld.utils.rcp.core.internal.util.CColumnLayoutData.Size;
import net.foreworld.utils.rcp.core.utils.AbstractLoggingSafeRunnable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.myfvn.ui.Activator;
import com.myfvn.ui.Application;
import com.myfvn.ui.ApplicationWorkbenchWindowAdvisor;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class SharingPreferencesPage extends PreferencePage implements IWorkbenchPreferencePage {

	public static final String ID = "com.myfvn.ui.SharingPreferencesPage"; //$NON-NLS-1$

	private LocalResourceManager _resources;
	private Button _moveDownButton;
	private Button _moveUpButton;
	private CheckboxTableViewer _viewer;
	private int[] _initialShareProviderState;

	public SharingPreferencesPage() {
		this.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/elcl16/share.gif"));//$NON-NLS-1$
		this._resources = new LocalResourceManager(JFaceResources.getResources());
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	public void init(IWorkbench $workbench) {

	}

	@Override
	public void createControl(Composite $parent) {
		super.createControl($parent);
		this.updateApplyEnablement(false);
	}

	private void updateApplyEnablement(boolean $enable) {
		Button __applyButton = getApplyButton();
		if (__applyButton != null && !__applyButton.isDisposed() && __applyButton.isEnabled() != $enable)
			__applyButton.setEnabled($enable);
	}

	@Override
	public void dispose() {
		super.dispose();
		this._resources.dispose();
	}

	@Override
	protected Control createContents(Composite $parent) {
		Composite __container = createContainer($parent);

		Label __infoText = new Label(__container, SWT.WRAP);
		__infoText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		((GridData) __infoText.getLayoutData()).widthHint = 200;
		__infoText.setText(Messages.SharingPreferencesPage_SELECT_COMMUNITY);

		Composite __tableContainer = new Composite(__container, SWT.NONE);
		__tableContainer.setLayout(LayoutUtils.createGridLayout(1, 0, 0));
		__tableContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		CTable __cTable = new CTable(__tableContainer, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION, Application.IS_WINDOWS, Application.IS_LINUX, Application.IS_MAC);

		_viewer = new CheckboxTableViewer(__cTable.getControl());
		_viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		_viewer.getTable().setHeaderVisible(true);
		((GridData) _viewer.getTable().getLayoutData()).heightHint = _viewer.getTable().getItemHeight() * 15;
		_viewer.getTable().setFocus();
		_viewer.getTable().setData(ApplicationWorkbenchWindowAdvisor.FOCUSLESS_SCROLL_HOOK, new Object());

		TableColumn __nameCol = new TableColumn(_viewer.getTable(), SWT.NONE);

		CColumnLayoutData __data = new CColumnLayoutData(Size.FILL, 100);
		__cTable.manageColumn(__nameCol, __data, Messages.SharingPreferencesPage_AVAILABLE_COMMUNITIES, null, null, false, false);

		/* ContentProvider returns all providers */
		_viewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object $inputElement) {
				// return Controller.getDefault().getShareProviders().toArray();
				return null;
			}

			public void dispose() {
			}

			public void inputChanged(Viewer $viewer, Object $oldInput, Object $newInput) {
			}
		});

		/* Label Provider */
		_viewer.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell $cell) {
				// ShareProvider provider = (ShareProvider) cell.getElement();
				$cell.setText("provider.getName()");
				// if (StringUtils.isSet(provider.getIconPath())) {
				// //
				// cell.setImage(_resources.createImage(OwlUI.getImageDescriptor(provider.getPluginId(),
				// // provider.getIconPath())));
				// }
			}
		});

		/* Selection */
		_viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent $event) {
				updateMoveEnablement();
			}
		});

		/* Drag Support */
		_viewer.addDragSupport(DND.DROP_MOVE, new Transfer[] { LocalSelectionTransfer.getTransfer() }, new DragSourceAdapter() {
			@Override
			public void dragStart(final DragSourceEvent $event) {
				SafeRunnable.run(new AbstractLoggingSafeRunnable() {
					public void run() throws Exception {
						IStructuredSelection __selection = (IStructuredSelection) _viewer.getSelection();
						$event.doit = (__selection.size() < _viewer.getTable().getItemCount());

						if ($event.doit) {
							LocalSelectionTransfer.getTransfer().setSelection(__selection);
							LocalSelectionTransfer.getTransfer().setSelectionSetTime($event.time & 0xFFFFFFFFL);
						}
						;
					}
				});
			}

			@Override
			public void dragSetData(final DragSourceEvent $event) {
				SafeRunnable.run(new AbstractLoggingSafeRunnable() {
					public void run() throws Exception {
						if (LocalSelectionTransfer.getTransfer().isSupportedType($event.dataType))
							$event.data = LocalSelectionTransfer.getTransfer().getSelection();
					}
				});
			}

			@Override
			public void dragFinished(DragSourceEvent $event) {
				SafeRunnable.run(new AbstractLoggingSafeRunnable() {
					public void run() throws Exception {
						LocalSelectionTransfer.getTransfer().setSelection(null);
						LocalSelectionTransfer.getTransfer().setSelectionSetTime(0);
					}
				});
			}
		});

		/* Drop Support */
		ViewerDropAdapter __dropSupport = new ViewerDropAdapter(_viewer) {

			@Override
			public boolean validateDrop(Object $target, int $operation, TransferData $transferType) {
				return true;
			}

			@Override
			public boolean performDrop(Object $data) {
				// ShareProvider target = (ShareProvider) getCurrentTarget();
				// if (target != null) {
				// onMove((StructuredSelection) data, target,
				// getCurrentLocation());
				// return true;
				// }

				return false;
			}
		};
		__dropSupport.setFeedbackEnabled(true);
		// dropSupport.setScrollEnabled(true);
		__dropSupport.setSelectionFeedbackEnabled(true);
		_viewer.addDropSupport(DND.DROP_MOVE, new Transfer[] { LocalSelectionTransfer.getTransfer() }, __dropSupport);

		/* Set input (ignored by ContentProvider anyways) */
		// _viewer.setInput(this);
		updateCheckedState();

		/* Ensure that the first checked element is visible */
		TableItem[] __items = _viewer.getTable().getItems();
		for (TableItem ___item : __items) {
			if (___item.getChecked()) {
				_viewer.getTable().showItem(___item);
				break;
			}
		}

		/* Listen on Check State Changes */
		_viewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent $event) {
				// ShareProvider provider = (ShareProvider) event.getElement();
				// provider.setEnabled(event.getChecked());
				save();
				// _viewer.update(provider, null);
			}
		});

		/* Container for the Buttons to Manage Providers */
		Composite __buttonContainer = new Composite(__container, SWT.None);
		__buttonContainer.setLayout(LayoutUtils.createGridLayout(1, 0, 0));
		__buttonContainer.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, false));

		/* Move Provider Up */
		_moveUpButton = new Button(__buttonContainer, SWT.PUSH);
		_moveUpButton.setText(Messages.SharingPreferencesPage_MOVE_UP);
		_moveUpButton.setEnabled(false);
		Dialog.applyDialogFont(_moveUpButton);
		setButtonLayoutData(_moveUpButton);
		_moveUpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onMove(true);
			}
		});

		/* Move Provider Down */
		_moveDownButton = new Button(__buttonContainer, SWT.PUSH);
		_moveDownButton.setText(Messages.SharingPreferencesPage_MOVE_DOWN);
		_moveDownButton.setEnabled(false);
		Dialog.applyDialogFont(_moveDownButton);
		setButtonLayoutData(_moveDownButton);
		_moveDownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onMove(false);
			}
		});

		/* Select All */
		Button __selectAllButton = new Button(__buttonContainer, SWT.PUSH);
		__selectAllButton.setText(Messages.SharingPreferencesPage_SELECT_ALL);
		Dialog.applyDialogFont(__selectAllButton);
		setButtonLayoutData(__selectAllButton);
		((GridData) __selectAllButton.getLayoutData()).verticalIndent = 10;
		__selectAllButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onSelectAll(false);
			}
		});

		/* De-Select All */
		Button __deSelectAllButton = new Button(__buttonContainer, SWT.PUSH);
		__deSelectAllButton.setText(Messages.SharingPreferencesPage_DESELECT_ALL);
		Dialog.applyDialogFont(__deSelectAllButton);
		setButtonLayoutData(__deSelectAllButton);
		__deSelectAllButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onSelectAll(true);
			}
		});

		/* Info Container */
		Composite __infoContainer = new Composite(__container, SWT.None);
		__infoContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		__infoContainer.setLayout(LayoutUtils.createGridLayout(2, 0, 0));

		Label __infoImg = new Label(__infoContainer, SWT.NONE);
		__infoImg.setImage(CacheImage.getInstance().getImage(Activator.PLUGIN_ID, "icons/obj16/info.gif")); //$NON-NLS-1$
		__infoImg.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

		Label __infoTextLabel = new Label(__infoContainer, SWT.WRAP);
		__infoTextLabel.setText(Messages.SharingPreferencesPage_COMMUNITY_INFO);
		__infoTextLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		applyDialogFont(__container);

		/* Enable Apply Button on Selection Changes */
		/*
		 * OwlUI.runOnSelection(new Runnable() { public void run() {
		 * updateApplyEnablement(true); } }, container);
		 */

		return __container;
	}

	private Composite createContainer(Composite $parent) {
		Composite __composite = new Composite($parent, SWT.NULL);
		GridLayout __layout = new GridLayout(2, false);
		__layout.marginWidth = 0;
		__layout.marginHeight = 0;
		__composite.setLayout(__layout);
		__composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		__composite.setFont($parent.getFont());
		return __composite;
	}

	private void onSelectAll(boolean $deselect) {
	}

	private void onMove(boolean $up) {
	}

	private void save() {
	}

	private void updateMoveEnablement() {
	}

	private void updateCheckedState() {
	}

}