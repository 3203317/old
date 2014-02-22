package com.myfvn.ui.dialogs;

import java.util.Collection;
import java.util.List;

import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.core.internal.util.LayoutUtils;
import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.IParent;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.IEmailbox;
import com.myfvn.core.persist.IUser;
import com.myfvn.core.persist.reference.UserReference;
import com.myfvn.ui.Activator;
import com.myfvn.ui.ApplicationContext;
import com.myfvn.ui.ApplicationWorkbenchWindowAdvisor;
import com.myfvn.ui.actions.DeleteTypesAction;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ManageSetsDialog extends TitleAreaDialog {

	/* Keep the visible instance saved */
	private static ManageSetsDialog _gVisibleInstance;

	private TableViewer _viewer;
	private LocalResourceManager _resources;
	private IEmailbox _selectedSet;
	private Label _statusLabel;
	private Button _editButton;
	private Button _deleteButton;

	public ManageSetsDialog(Shell $parentShell, IEmailbox $selectedSet) {
		super($parentShell);
		this._selectedSet = $selectedSet;
		_resources = new LocalResourceManager(JFaceResources.getResources());
	}

	public static ManageSetsDialog getVisibleInstance() {
		return _gVisibleInstance;
	}

	@Override
	public int open() {
		_gVisibleInstance = this;
		registerListeners();
		return super.open();
	}

	private void registerListeners() {

	}

	@Override
	public boolean close() {
		unregisterListeners();
		_gVisibleInstance = null;
		_resources.dispose();
		return super.close();
	}

	private void unregisterListeners() {

	}

	@Override
	protected void configureShell(Shell $shell) {
		super.configureShell($shell);
		$shell.setText(Messages.ManageSetsDialog_MANAGE_SETS);
	}

	@Override
	protected int getShellStyle() {
		int __style = SWT.TITLE | SWT.BORDER | SWT.CLOSE | getDefaultOrientation();
		return __style;
	}

	@Override
	protected Control createDialogArea(Composite $parent) {
		/* Title */
		setTitle(Messages.ManageSetsDialog_SETS);

		/* Title Image */
		setTitleImage(CacheImage.getInstance().getImage(Activator.PLUGIN_ID, "icons/wizban/bkmrk_set_title.gif")); //$NON-NLS-1$

		/* Title Message */
		showInfo();

		/* Separator */
		new Label($parent, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		/* Composite to hold all components */
		Composite __composite = new Composite($parent, SWT.NONE);
		__composite.setLayout(LayoutUtils.createGridLayout(2, 5, 10));
		__composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		/* TableViewer to display Emailbox Sets */
		_viewer = new TableViewer(__composite, SWT.BORDER);
		_viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		((GridData) _viewer.getTable().getLayoutData()).heightHint = _viewer.getTable().getItemHeight() * 7;
		_viewer.getTable().setData(ApplicationWorkbenchWindowAdvisor.FOCUSLESS_SCROLL_HOOK, new Object());

		/* Drag and Drop */
		initDragAndDrop();

		/* ContentProvider returns Root-Folders */
		_viewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object $inputElement) {
				IUser ___user = new UserReference(ApplicationContext.getDefault().getUser_uuid()).resolve();
				return ___user.getEmailboxs().toArray();
			}

			public void dispose() {
			}

			public void inputChanged(Viewer $viewer, Object $oldInput, Object $newInput) {
			}
		});

		/* Simple LabelProvider */
		_viewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object $element) {
				return ((IEntity) $element).getName();
			}

			@Override
			public Image getImage(Object $element) {
				return CacheImage.getInstance().getImage(Activator.PLUGIN_ID, "icons/obj16/bkmrk_set.gif");//$NON-NLS-1$
			}
		});

		/* Sort by ID to show latest Set at bottom */
		_viewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer $viewer, Object $o1, Object $o2) {
				IEntity ___entity1 = (IEntity) $o1;
				IEntity ___entity2 = (IEntity) $o2;

				return ___entity1.getUuid().compareTo(___entity2.getUuid());
			}
		});

		/* Edit on Doubleclick */
		_viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent $event) {
				onEdit();
			}
		});

		/* Set input (ignored by ContentProvider anyways) */
		_viewer.setInput(this);

		/* Container for the Buttons to Manage Sets */
		Composite __buttonContainer = new Composite(__composite, SWT.None);
		__buttonContainer.setLayout(LayoutUtils.createGridLayout(1, 0, 0));
		__buttonContainer.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, false));

		/* Adds a new Email Set */
		Button __addButton = new Button(__buttonContainer, SWT.PUSH);
		__addButton.setText(Messages.ManageSetsDialog_NEW);
		applyDialogFont(__addButton);
		setButtonLayoutData(__addButton);
		__addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onAdd();
			}
		});

		/* Edits a selected Email Set */
		_editButton = new Button(__buttonContainer, SWT.PUSH);
		_editButton.setText(Messages.ManageSetsDialog_EDIT);
		applyDialogFont(_editButton);
		setButtonLayoutData(_editButton);
		_editButton.setEnabled(false);
		_editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onEdit();
			}
		});

		/* Deletes the selected Email Set */
		_deleteButton = new Button(__buttonContainer, SWT.PUSH);
		_deleteButton.setText(Messages.ManageSetsDialog_DELETE);
		applyDialogFont(_deleteButton);
		setButtonLayoutData(_deleteButton);
		((GridData) _deleteButton.getLayoutData()).verticalAlignment = SWT.END;
		((GridData) _deleteButton.getLayoutData()).grabExcessVerticalSpace = true;
		_deleteButton.setEnabled(false);
		_deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent $event) {
				onDelete();
			}
		});

		/* Container for the status-message */
		Composite __statusContainer = new Composite(__composite, SWT.None);
		__statusContainer.setLayout(LayoutUtils.createGridLayout(1, 5, 0));
		__statusContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false, 2, 1));

		_statusLabel = new Label(__statusContainer, SWT.NONE);
		_statusLabel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		/* Update Status Label when selection changes */
		_viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent $event) {
				onSelectionChange();
			}
		});

		/* Pre-Select the current visible Set */
		IUser __user = new UserReference(ApplicationContext.getDefault().getUser_uuid()).resolve();
		Collection<IEmailbox> __rootEmailboxs = __user.getEmailboxs();
		for (IEmailbox ___rootEmailbox : __rootEmailboxs) {
			if (___rootEmailbox.equals(_selectedSet)) {
				_viewer.setSelection(new StructuredSelection(___rootEmailbox));
				break;
			}
		}

		/* Separator */
		new Label($parent, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		applyDialogFont(__composite);

		return __composite;
	}

	private void showInfo() {
		setErrorMessage(null);
		setMessage(Messages.ManageSetsDialog_SELECT_SET);
	}

	private void initDragAndDrop() {

	}

	private void onEdit() {

	}

	private void onAdd() {

	}

	private void onDelete() {
		showInfo();

		/* Require at least 1 Set to remain undeleted */
		if (_viewer.getTable().getItemCount() == 1) {
			setErrorMessage(Messages.ManageSetsDialog_DELETE_LAST_SET_ERROR);
			return;
		}

		IStructuredSelection __selection = (IStructuredSelection) _viewer.getSelection();
		if (!__selection.isEmpty()) {
			DeleteTypesAction ___deleteAction = new DeleteTypesAction(getShell(), __selection);
			___deleteAction.run();

			if (___deleteAction.isConfirmed())
				_viewer.remove(__selection.getFirstElement());
		}
	}

	private void onSelectionChange() {
		updateStatusLabel();

		ISelection __selection = _viewer.getSelection();
		_editButton.setEnabled(!__selection.isEmpty());
		_deleteButton.setEnabled(!__selection.isEmpty());
	}

	private void updateStatusLabel() {
		IStructuredSelection selection = (IStructuredSelection) _viewer.getSelection();
		if (selection.isEmpty()) {
			_statusLabel.setText(""); //$NON-NLS-1$
		} else {
			IEmailbox ___emailboxSet = (IEmailbox) selection.getFirstElement();
			int ___counter[] = new int[4];
			count(___emailboxSet, ___counter);

			StringBuilder ___sb = new StringBuilder();
			if (___counter[0] > 0)
				___sb.append(___counter[0] == 1 ? Messages.ManageSetsDialog_1_FILE : (NLS.bind(Messages.ManageSetsDialog_N_FILES, ___counter[0]))).append(", "); //$NON-NLS-1$
			if (___counter[1] > 0)
				___sb.append(___counter[1] == 1 ? Messages.ManageSetsDialog_1_ATTACHMENT : (NLS.bind(Messages.ManageSetsDialog_N_ATTACHMENTS, ___counter[1]))).append(", "); //$NON-NLS-1$

			/* Set is Empty */
			if (___counter[0] == 0 && ___counter[1] == 0)
				___sb = new StringBuilder(Messages.ManageSetsDialog_EMPTY_SET);
			else if (___sb.length() > 0)
				___sb.delete(___sb.length() - 2, ___sb.length());

			_statusLabel.setText(NLS.bind(Messages.ManageSetsDialog_SET_CONTENT, ___emailboxSet.getName(), ___sb.toString()));
		}
	}

	private void count(IParent $parent, int[] $counter) {
		List<IChild> __childs = $parent.getChildren();

		for (IChild ___child : __childs) {
			if (___child instanceof IAttachment) {
				$counter[1]++;
			} else if (___child instanceof IEmail) {
				$counter[0]++;
				count((IParent) ___child, $counter);
			}
		}
	}

}
