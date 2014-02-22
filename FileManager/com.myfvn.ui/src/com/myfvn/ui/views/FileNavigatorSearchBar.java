package com.myfvn.ui.views;

import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.JobRunner;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.progress.WorkbenchJob;

import com.myfvn.ui.AbstractContextMenuCreator;
import com.myfvn.ui.Activator;
import com.myfvn.ui.FvnUI;
import com.myfvn.ui.views.FileNavigatorFilter.SearchTarget;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class FileNavigatorSearchBar extends Composite {

	/* Action Bar */
	private static final String BAR_ACTION_ID = "com.myfvn.ui.views.BarAction"; //$NON-NLS-1$

	/* Delay before filtering takes place */
	private static final int FILTER_DELAY = 300;

	private IViewSite _viewSite;

	private TreeViewer _viewer;

	private FileNavigatorFilter _patternFilter;
	private ToolBarManager _filterToolBar;
	private Job _refreshJob;
	private Object _expandedElements[];
	private Object _selectedElements[];
	private Composite _filterComposite;
	private String _initialText = ""; //$NON-NLS-1$

	public FileNavigatorSearchBar(IViewSite $viewSite, Composite $parent, TreeViewer $viewer, FileNavigatorFilter $patternFilter) {
		super($parent, SWT.NONE);

		this._viewSite = $viewSite;
		this._viewer = $viewer;
		this._patternFilter = $patternFilter;

		this.createControl($parent);
		createRefreshJob();

		this.setFont($parent.getFont());

	}

	/** ************************************************************* */

	private void createControl(Composite $parent) {
		GridLayout __layout = new GridLayout();
		__layout.marginHeight = 0;
		__layout.marginWidth = 0;
		this.setLayout(__layout);
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// Container for the Controls
		this._filterComposite = new Composite(this, SWT.NONE);
		GridLayout __filterLayout = new GridLayout(FvnUI.needsCancelControl() ? 3 : 2, false);
		__filterLayout.marginHeight = 0;
		__filterLayout.marginWidth = 0;
		__filterLayout.horizontalSpacing = 3;
		this._filterComposite.setLayout(__filterLayout);
		this._filterComposite.setFont($parent.getFont());
		this._filterComposite.setBackground($parent.getBackground());

		// Create Filter Controls
		this.createFilterControls(this._filterComposite);
		this._filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		this._filterComposite.setBackground($parent.getBackground());

		// Set height as required
		if (this._filterToolBar != null) {
			int ___prefContainerHeight = this._filterComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;

			int ___prefToolbarHeight = this._filterToolBar.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y;

			if (___prefToolbarHeight >= ___prefContainerHeight)
				((GridData) this._filterComposite.getLayoutData()).heightHint = ___prefToolbarHeight;
		}

	}

	private Composite createFilterControls(Composite $parent) {
		this.createFilterTarget($parent);
		this.createFilterText($parent);

		if (FvnUI.needsCancelControl()) {
			createClearText($parent);
		}

		if (this._filterToolBar != null) {
			this._filterToolBar.update(false);
			this._filterToolBar.getControl().setVisible(false);
		}

		return $parent;
	}

	private void createFilterTarget(Composite $parent) {

		final ToolBarManager __filterTargetManager = new ToolBarManager(SWT.FLAT);

		IAction __filterTargetAction = new Action("", IAction.AS_DROP_DOWN_MENU) {
			public void run() {
				FvnUI.positionDropDownMenu(this, __filterTargetManager);
			}

			public String getId() {
				return BAR_ACTION_ID;
			}
		};

		__filterTargetAction.setMenuCreator(new AbstractContextMenuCreator() {

			public Menu createMenu(Control $parent) {

				Menu ___menu = new Menu($parent);

				// Search on: File Name
				final MenuItem ___searchFileName = new MenuItem(___menu, SWT.RADIO);
				___searchFileName.setText(Messages.FileNavigatorSearchBar_FIND_BY_FILE_NAME);
				___searchFileName.setSelection(FileNavigatorFilter.SearchTarget.NAME == _patternFilter.getSearchTarget());
				___searchFileName.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent $event) {
						if (___searchFileName.getSelection() && _patternFilter.getSearchTarget() != FileNavigatorFilter.SearchTarget.NAME) {
							doSearch(FileNavigatorFilter.SearchTarget.NAME);
						}
					}
				});

				// Search on: File Name
				/*
				 * final MenuItem ___searchCreateTIme = new MenuItem(___menu,
				 * SWT.RADIO);
				 * ___searchCreateTIme.setText(Messages.FileNavigatorSearchBar_FIND_BY_CREATETIME);
				 * ___searchCreateTIme.setSelection(FileNavigatorFilter.SearchTarget.CREATETIME ==
				 * _patternFilter.getSearchTarget());
				 * ___searchCreateTIme.addSelectionListener(new
				 * SelectionAdapter() { public void
				 * widgetSelected(SelectionEvent $event) { if
				 * (___searchCreateTIme.getSelection() &&
				 * _patternFilter.getSearchTarget() !=
				 * FileNavigatorFilter.SearchTarget.CREATETIME) {
				 * doSearch(FileNavigatorFilter.SearchTarget.CREATETIME); } }
				 * });
				 */

				return ___menu;
			}
		});

		__filterTargetAction.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/find.gif"));//$NON-NLS-1$
		__filterTargetManager.add(__filterTargetAction);

		__filterTargetManager.createControl($parent);
		__filterTargetManager.getControl().setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, true));

	}

	private Text _filterText;

	private void createFilterText(Composite $parent) {

		this._filterText = new Text($parent, SWT.SINGLE | SWT.BORDER | SWT.SEARCH | SWT.CANCEL);
		this._filterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

		// set message
		if (_patternFilter.getSearchTarget() == SearchTarget.NAME) {
			_filterText.setMessage(Messages.FileNavigatorSearchBar_FIND_BY_FILE_NAME);
		} else {
			_filterText.setMessage(Messages.FileNavigatorSearchBar_FIND_BY_CREATETIME);
		}

		/* Override Accessible */
		this._filterText.getAccessible().addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent $event) {
				String __filterTextString = _filterText.getText();
				$event.result = (__filterTextString.length() == 0) ? "" : __filterTextString;//$NON-NLS-1$
			}
		});

		/* Select All on Focus if input matches Initial Text */
		this._filterText.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent $event) {
				JobRunner.runInUIThread(0, true, _filterText, new Runnable() {
					public void run() {
						if (_initialText.equals(_filterText.getText().trim()))
							_filterText.selectAll();
					}
				});

				/* Enable some Edit Actions */
			}

			public void focusLost(FocusEvent $event) {
				/* Disable some Edit Actions */
			}
		});

		/* Focus Tree on Arrow Up or Down */
		_filterText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent $event) {
				/* Pressed ESC */
				if ($event.character == SWT.ESC) {
					setFilterText("");//$NON-NLS-1$
					_viewer.getTree().setFocus();
					return;
				}

				/* Pressed Arrow Down or Up */
				boolean __hasItems = _viewer.getTree().getItemCount() > 0;
				if (__hasItems && ($event.keyCode == SWT.ARROW_DOWN || $event.keyCode == SWT.ARROW_UP)) {
					_viewer.getTree().setFocus();
				} else if ($event.character == SWT.CR) {
					return;
				}
			}
		});

		/* Handle the CR Key Pressed */
		_filterText.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent $event) {
				if ($event.detail == SWT.TRAVERSE_RETURN) {
					$event.doit = false;

					/* Results available */
					if (_viewer.getTree().getItemCount() > 0) {
						boolean ___hasFocus = _viewer.getTree().setFocus();
						boolean textChanged = !_initialText.equals(_filterText.getText().trim());

						/* Select the first matching Item */
						if (___hasFocus && textChanged && _filterText.getText().trim().length() > 0) {
							TreeItem ____item = getFirstMatchingItem(_viewer.getTree().getItems());
							if (____item != null) {
								_viewer.getTree().setSelection(new TreeItem[] { ____item });
								ISelection _____sel = _viewer.getSelection();
								_viewer.setSelection(_____sel, true);
							}
						}
					}
				}
			}
		});

		/* Update Filter on Modified Input */
		_filterText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent $event) {
				textChanged();
			}
		});

	}

	private void setFilterText(String $string) {
		if (_filterText != null) {
			_filterText.setText($string);
			_filterText.selectAll();
		}
	}

	private void doSearch(SearchTarget $target) {
		this._patternFilter.setSearchTarget($target);
		this._filterText.setFocus();

		/* Set Message */
		if (this._patternFilter.getSearchTarget() == SearchTarget.NAME) {
			this._filterText.setMessage(Messages.FileNavigatorSearchBar_FIND_BY_FILE_NAME);
		} else {
			this._filterText.setMessage(Messages.FileNavigatorSearchBar_FIND_BY_CREATETIME);
		}

		if (this._filterText.getText().length() > 0)
			this.textChanged();
	}

	void textChanged() {
		_refreshJob.cancel();
		_refreshJob.schedule(_filterText.getText().length() != 0 ? FILTER_DELAY : 0);
	}

	Text getControl() {
		return _filterText;
	}

	TreeItem getFirstMatchingItem(TreeItem[] $items) {
		for (TreeItem ___element : $items) {
			if (_patternFilter.isLeafMatch(_viewer, ___element.getData()) && _patternFilter.isElementSelectable(___element.getData()))
				return ___element;
			return getFirstMatchingItem(___element.getItems());
		}
		return null;
	}

	/* Create Toolbar with "Clear" Button */
	private void createClearText(Composite $parent) {
		ToolBar __toolBar = new ToolBar($parent, SWT.FLAT | SWT.HORIZONTAL);
		_filterToolBar = new ToolBarManager(__toolBar);
		_filterToolBar.getControl().setBackground($parent.getBackground());
		_filterToolBar.getControl().setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, true));

		/* Initially Hide */
		((GridData) __toolBar.getLayoutData()).exclude = true;
		__toolBar.setVisible(false);

		IAction __clearTextAction = new Action("", IAction.AS_PUSH_BUTTON) {//$NON-NLS-1$
			@Override
			public void run() {
				setFilterText(""); //$NON-NLS-1$
			}
		};

		__clearTextAction.setToolTipText(Messages.FileNavigatorSearchBar_CLEAR);
		__clearTextAction.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/clear.gif")); //$NON-NLS-1$

		_filterToolBar.add(__clearTextAction);
	}

	/* Create the Job that refreshes the TreeViewer */
	private void createRefreshJob() {
		_refreshJob = new WorkbenchJob("") {//$NON-NLS-1$
			@Override
			public IStatus runInUIThread(IProgressMonitor $monitor) {

				/* Tree Disposed */
				if (_viewer.getControl().isDisposed())
					return Status.CANCEL_STATUS;

				/* Get the Filter Pattern */
				String __text = _filterText != null ? _filterText.getText() : null;
				if (__text == null)
					return Status.OK_STATUS;

				/* Check if the Initial Text was set */
				boolean __initial = _initialText != null && _initialText.equals(__text);
				if (__initial)
					_patternFilter.setPattern(null);
				else
					_patternFilter.setPattern(__text);

				try {
					_viewer.getControl().getParent().setRedraw(false);

					/* Remember Expanded Elements if not yet done */
					if (_expandedElements == null)
						_expandedElements = _viewer.getExpandedElements();

					/* Remember Selected Elements if present */
					IStructuredSelection ___sel = (IStructuredSelection) _viewer.getSelection();
					if (!___sel.isEmpty())
						_selectedElements = ___sel.toArray();

					/* Refresh Tree */
					BusyIndicator.showWhile(getDisplay(), new Runnable() {
						public void run() {
							_viewer.refresh(false);
						}
					});

					/*
					 * Restore Expanded Elements and Selection when Filter is
					 * disabled
					 */
					if (__text.length() == 0) {

						/* Restore Expansion */
						_viewer.collapseAll();
						for (Object ___element : _expandedElements) {
							_viewer.setExpandedState(___element, true);
						}

						/* Restore Selection */
						if (_selectedElements != null)
							_viewer.setSelection(new StructuredSelection(_selectedElements), true);

						/* Clear Fields */
						_expandedElements = null;
						_selectedElements = null;
					}

					/*
					 * Expand elements one at a time. After each is expanded,
					 * check to see if the filter text has been modified. If it
					 * has, then cancel the refresh job so the user doesn't have
					 * to endure expansion of all the nodes.
					 */
					if (__text.length() > 0 && !__initial) {
						IStructuredContentProvider ____provider = (IStructuredContentProvider) _viewer.getContentProvider();
						Object[] elements = ____provider.getElements(_viewer.getInput());
						for (Object _____element : elements) {
							if ($monitor.isCanceled())
								return Status.CANCEL_STATUS;

							_viewer.expandToLevel(_____element, AbstractTreeViewer.ALL_LEVELS);
						}

						/* Make Sure to show the First Item */
						TreeItem[] ____items = _viewer.getTree().getItems();
						if (____items.length > 0)
							_viewer.getTree().showItem(____items[0]);

						/* Enable Toolbar to allow resetting the Filter */
						setToolBarVisible(true);
					}

					/* Disable Toolbar - No Filter is currently activated */
					else {
						setToolBarVisible(false);
					}
				}

				/* Done updating the tree - set redraw back to true */
				finally {
					_viewer.getControl().getParent().setRedraw(true);
				}

				return Status.OK_STATUS;
			}
		};
		_refreshJob.setSystem(true);

		_viewer.getControl().addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent $event) {
				_refreshJob.cancel();
			}
		});
	}

	void setToolBarVisible(boolean $visible) {
		if (_filterToolBar != null && ((GridData) _filterToolBar.getControl().getLayoutData()).exclude == $visible) {
			((GridData) _filterToolBar.getControl().getLayoutData()).exclude = !$visible;
			_filterToolBar.getControl().setVisible($visible);
			_filterComposite.layout();
		}
	}
}
