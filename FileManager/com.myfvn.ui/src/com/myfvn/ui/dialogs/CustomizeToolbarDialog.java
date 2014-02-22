package com.myfvn.ui.dialogs;

import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.core.internal.util.CColumnLayoutData;
import net.foreworld.utils.rcp.core.internal.util.CTable;
import net.foreworld.utils.rcp.core.internal.util.LayoutUtils;
import net.foreworld.utils.rcp.core.internal.util.CColumnLayoutData.Size;
import net.foreworld.utils.rcp.core.utils.AbstractLoggingSafeRunnable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

import com.myfvn.ui.Activator;
import com.myfvn.ui.Application;
import com.myfvn.ui.ApplicationWorkbenchWindowAdvisor;
import com.myfvn.ui.FvnUI;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class CustomizeToolbarDialog extends Dialog {

	/* Size and Location Settings */
	private static final String DIALOG_SETTINGS_KEY = "com.myfvn.ui.dialogs.CustomizeToolbarDialog"; //$NON-NLS-1$

	private LocalResourceManager _resources;
	private boolean _firstTimeOpen;
	private TableViewer _itemViewer;
	private ComboViewer _modeViewer;
	private Button _addButton;
	private Button _removeButton;
	private Button _moveUpButton;
	private Button _moveDownButton;
	private Button _restoreDefaults;
	private boolean _okPressed;
	private Menu _addMenu;

	/* Remember State when Dialog Opened */
	private int[] _initialToolBarItems;
	private int _initialToolBarMode;

	/* Colors */
	private Color _separatorBorderFg;
	private Color _separatorBg;

	public CustomizeToolbarDialog(Shell $parentShell) {
		super($parentShell);
		_resources = new LocalResourceManager(JFaceResources.getResources());
		_firstTimeOpen = (Activator.getDefault().getDialogSettings().getSection(DIALOG_SETTINGS_KEY) == null);
		/* Colors */
		_separatorBorderFg = FvnUI.getColor(_resources, new RGB(210, 210, 210));
		_separatorBg = FvnUI.getColor(_resources, new RGB(240, 240, 240));
	}

	@Override
	protected void okPressed() {
		_okPressed = true;
		super.okPressed();
	}

	@Override
	public int open() {
		return super.open();
	}

	@Override
	public boolean close() {
		FvnUI.safeDispose(_addMenu);
		_resources.dispose();
		if (!_okPressed) {
		}
		return super.close();
	}

	@Override
	protected void configureShell(Shell $shell) {
		super.configureShell($shell);
		$shell.setText(Messages.CustomizeToolbarDialog_CUSTOMIZE_TOOLBAR);
	}

	@Override
	protected Control createButtonBar(Composite $parent) {
		GridLayout __layout = new GridLayout(1, false);
		__layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		__layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		__layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		__layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);

		Composite __buttonBar = new Composite($parent, SWT.NONE);
		__buttonBar.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		__buttonBar.setLayout(__layout);

		/* Info Container */
		Composite __infoContainer = new Composite(__buttonBar, SWT.None);
		__infoContainer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		__infoContainer.setLayout(LayoutUtils.createGridLayout(2, 0, 0));
		((GridLayout) __infoContainer.getLayout()).marginRight = 10;

		Label __infoImg = new Label(__infoContainer, SWT.NONE);
		__infoImg.setImage(CacheImage.getInstance().getImage(Activator.PLUGIN_ID, "icons/obj16/info.gif")); //$NON-NLS-1$
		__infoImg.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

		Label __infoText = new Label(__infoContainer, SWT.WRAP);
		__infoText.setText(Messages.CustomizeToolbarDialog_USE_MOUSE_INFO);
		__infoText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		applyDialogFont(__infoContainer);

		/* Create Ok / Cancel Buttons */
		createButtonsForButtonBar(__buttonBar);

		return __buttonBar;
	}

	private Composite createContainer(Composite $parent) {
		Composite __composite = new Composite($parent, SWT.NULL);
		GridLayout __layout = new GridLayout(2, false);
		__composite.setLayout(__layout);
		__composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		__composite.setFont($parent.getFont());
		return __composite;
	}

	@Override
	protected Control createDialogArea(Composite $parent) {
		Composite __container = createContainer($parent);

		Label __infoLabel = new Label(__container, SWT.None);
		__infoLabel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
		__infoLabel.setText(Messages.CustomizeToolbarDialog_DIALOG_INFO);

		/* Table showing Tool Items */
		Composite __tableContainer = new Composite(__container, SWT.NONE);
		__tableContainer.setLayout(LayoutUtils.createGridLayout(1, 0, 0));
		__tableContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		CTable __cTable = new CTable(__tableContainer, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION, Application.IS_WINDOWS, Application.IS_LINUX, Application.IS_MAC);

		_itemViewer = new TableViewer(__cTable.getControl());
		_itemViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		_itemViewer.getTable().setHeaderVisible(false);
		((GridData) _itemViewer.getTable().getLayoutData()).heightHint = _itemViewer.getTable().getItemHeight() * 24;
		_itemViewer.getTable().setFocus();
		_itemViewer.getTable().setData(ApplicationWorkbenchWindowAdvisor.FOCUSLESS_SCROLL_HOOK, new Object());

		/* Custom Owner Drawn Category */
		if (!FvnUI.isHighContrast()) {
			_itemViewer.getControl().addListener(SWT.EraseItem, new Listener() {
				public void handleEvent(Event $event) {
				}
			});
		}

		TableColumn __nameCol = new TableColumn(_itemViewer.getTable(), SWT.NONE);

		CColumnLayoutData __data = new CColumnLayoutData(Size.FILL, 100);
		__cTable.manageColumn(__nameCol, __data, Messages.CustomizeToolbarDialog_VISIBLE_ITEMS, null, null, false, false);

		/* ContentProvider returns all selected Items */
		_itemViewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object $inputElement) {
				return null;
			}

			public void dispose() {
			}

			public void inputChanged(Viewer $viewer, Object $oldInput, Object $newInput) {
			}
		});

		/* Label Provider */
		_itemViewer.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell $cell) {
			}
		});

		/* Selection */
		_itemViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent $event) {
				updateButtonEnablement();
			}
		});

		/* Support Keyboard Remove */
		_itemViewer.getTable().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent $event) {
				if ($event.keyCode == SWT.DEL || (Application.IS_MAC && $event.keyCode == SWT.BS))
					onRemove();
			}
		});

		/* Drag Support */
		_itemViewer.addDragSupport(DND.DROP_MOVE, new Transfer[] { LocalSelectionTransfer.getTransfer() }, new DragSourceAdapter() {
			@Override
			public void dragStart(final DragSourceEvent $event) {
				SafeRunnable.run(new AbstractLoggingSafeRunnable() {
					public void run() throws Exception {
						IStructuredSelection __selection = (IStructuredSelection) _itemViewer.getSelection();
						$event.doit = (__selection.size() < _itemViewer.getTable().getItemCount());

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
		ViewerDropAdapter __dropSupport = new ViewerDropAdapter(_itemViewer) {

			@Override
			public boolean validateDrop(Object $target, int $operation, TransferData $transferType) {
				return true;
			}

			@Override
			public boolean performDrop(Object $data) {
				return false;
			}
		};
		__dropSupport.setFeedbackEnabled(true);
		__dropSupport.setScrollExpandEnabled(true);
		__dropSupport.setSelectionFeedbackEnabled(true);
		_itemViewer.addDropSupport(DND.DROP_MOVE, new Transfer[] { LocalSelectionTransfer.getTransfer() }, __dropSupport);

		/* Set Dummy Input */
		_itemViewer.setInput(null);

		/* Container for the Buttons to Manage Providers */
		Composite buttonContainer = new Composite(__container, SWT.None);
		buttonContainer.setLayout(LayoutUtils.createGridLayout(1, 0, 0));
		buttonContainer.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, false));

		/* Add */
		_addMenu = new Menu(getShell(), SWT.POP_UP);
		_addMenu.addMenuListener(new MenuListener() {
			public void menuShown(MenuEvent $event) {
			}

			public void menuHidden(MenuEvent $event) {
			}
		});

		_addButton = new Button(buttonContainer, SWT.DOWN);
		_addButton.setText(Messages.CustomizeToolbarDialog_ADD);
		applyDialogFont(_addButton);
		setButtonLayoutData(_addButton);
		_addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				Rectangle __rect = _addButton.getBounds();
				Point __pt = new Point(__rect.x, __rect.y + __rect.height);
				__pt = _addButton.toDisplay(__pt);
				_addMenu.setLocation(__pt.x, __pt.y);
				_addMenu.setVisible(true);
			}
		});

		/* Remove */
		_removeButton = new Button(buttonContainer, SWT.PUSH);
		_removeButton.setText(Messages.CustomizeToolbarDialog_REMOVE);
		_removeButton.setEnabled(false);
		applyDialogFont(_removeButton);
		setButtonLayoutData(_removeButton);
		_removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onRemove();
			}
		});

		/* Move Provider Up */
		_moveUpButton = new Button(buttonContainer, SWT.PUSH);
		_moveUpButton.setText(Messages.CustomizeToolbarDialog_MOVE_UP);
		_moveUpButton.setEnabled(false);
		applyDialogFont(_moveUpButton);
		setButtonLayoutData(_moveUpButton);
		((GridData) _moveUpButton.getLayoutData()).verticalIndent = 10;
		_moveUpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onMove(true);
			}
		});

		/* Move Provider Down */
		_moveDownButton = new Button(buttonContainer, SWT.PUSH);
		_moveDownButton.setText(Messages.CustomizeToolbarDialog_MOVE_DOWN);
		_moveDownButton.setEnabled(false);
		applyDialogFont(_moveDownButton);
		setButtonLayoutData(_moveDownButton);
		_moveDownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onMove(false);
			}
		});

		/* Restore Defaults */
		_restoreDefaults = new Button(buttonContainer, SWT.PUSH);
		_restoreDefaults.setText(Messages.CustomizeToolbarDialog_RESTORE_DEFAULTS);
		applyDialogFont(_restoreDefaults);
		setButtonLayoutData(_restoreDefaults);
		((GridData) _restoreDefaults.getLayoutData()).grabExcessVerticalSpace = true;
		((GridData) _restoreDefaults.getLayoutData()).verticalAlignment = SWT.END;
		_restoreDefaults.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onRestoreDefaults();
			}
		});

		/* Toolbar Mode */
		Composite __modeContainer = new Composite(__container, SWT.None);
		__modeContainer.setLayout(LayoutUtils.createGridLayout(2, 5, 0));
		__modeContainer.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, false, 2, 1));

		Label __showLabel = new Label(__modeContainer, SWT.NONE);
		__showLabel.setText(Messages.CustomizeToolbarDialog_SHOW);

		_modeViewer = new ComboViewer(__modeContainer, SWT.READ_ONLY | SWT.BORDER);
		_modeViewer.setContentProvider(new ArrayContentProvider());
		_modeViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object $element) {
				return super.getText($element);
			}
		});

		_modeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent $event) {
				Object __selection = ((IStructuredSelection) $event.getSelection()).getFirstElement();
			}
		});

		/* Separator */
		new Label($parent, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		applyDialogFont(__container);

		return __container;
	}

	private void onRestoreDefaults() {

	}

	private void onMove(boolean $up) {

	}

	private void onRemove() {

	}

	private void updateButtonEnablement() {

	}

}
