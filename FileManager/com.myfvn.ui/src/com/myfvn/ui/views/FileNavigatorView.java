package com.myfvn.ui.views;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.StringUtil;
import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.JobRunner;
import net.foreworld.utils.rcp.TreeViewerUtil;
import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.events.IEntityListener;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.ViewPart;

import com.myfvn.core.Fvn;
import com.myfvn.core.internal.persist.pref.DefaultPreferences;
import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.IEmailbox;
import com.myfvn.core.persist.IUser;
import com.myfvn.core.persist.pref.IPreferenceScope;
import com.myfvn.core.persist.reference.UserReference;
import com.myfvn.ui.AbstractContextMenuCreator;
import com.myfvn.ui.Activator;
import com.myfvn.ui.ApplicationContext;
import com.myfvn.ui.Controller;
import com.myfvn.ui.FvnUI;
import com.myfvn.ui.ShareProvider;
import com.myfvn.ui.StatusLineUpdater;
import com.myfvn.ui.actions.DeleteTypesAction;
import com.myfvn.ui.actions.EntityPropertyDialogAction;
import com.myfvn.ui.actions.FVNActionFactory;
import com.myfvn.ui.actions.NewEmailAccountAction;
import com.myfvn.ui.actions.NewFileAction;
import com.myfvn.ui.actions.OpenInNewTabAction;
import com.myfvn.ui.actions.SearchFilesAction;
import com.myfvn.ui.dialogs.ManageSetsDialog;
import com.myfvn.ui.preferences.SharingPreferencesPage;
import com.myfvn.ui.views.editors.email.EmailViewInput;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class FileNavigatorView extends ViewPart {
	public final static String ID = FileNavigatorView.class.getName();

	final static Logger _logger = Logger.getLogger(FileNavigatorView.class.getName());

	/* Local Actions */
	private static final String FILTER_ACTION = "com.myfvn.ui.views.FilterAction"; //$NON-NLS-1$

	/* IDs of Action: Next Mail-Set */
	public static final String NEXT_SET_ACTION = "com.myfvn.ui.views.NextSetAction"; //$NON-NLS-1$

	/* IDs of Action: Previous Mail-Set */
	public static final String PREVIOUS_SET_ACTION = "com.myfvn.ui.views.PreviousSetAction"; //$NON-NLS-1$

	/* Settings */
	private IPreferenceScope _globalPreferences;
	private List<String> _expandedNodes;
	private boolean _linkingEnabled;
	private FileNavigatorFilter.Type _filterType;

	/* Widgets */
	private IToolBarManager _toolBarManager;

	/* Viewer Classes */
	private FileNavigatorFilter _fileNavigatorFilter;

	/* Emailbox Sets */
	private List<IEmailbox> _rootEmailboxs;

	/* Misc. */
	private FileNavigatorFilter.Type _lastFilterType;
	private IPartListener2 _partListener;
	private boolean _blockSaveState;

	private IViewSite _viewSite;

	public void createPartControl(Composite $parent) {

		// update parent
		GridLayout __layout = new GridLayout(1, false);
		__layout.marginWidth = 0;
		__layout.marginHeight = 0;
		__layout.verticalSpacing = 0;

		$parent.setLayout(__layout);
		$parent.setBackground($parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

		// create treeViewer
		this.createViewer($parent);

		// create action
		this.createAction();

		/* Hook into Statusline */
		this._treeViewer.addSelectionChangedListener(new StatusLineUpdater(this.getViewSite().getActionBars().getStatusLineManager()));

		// hook into global actions
		this.hookGlobalActions();

		/* Hook contextual Menu */
		this.hookContextualMenu();

		/* Hook into Toolbar */
		this.hookToolBar();

		/* Hook into View Dropdown */
		this.hookViewMenu();

		/* Register Listeners */
		this.registerListeners();

		// Propagate Selection Events
		if (this._viewSite != null)
			this._viewSite.setSelectionProvider(this._treeViewer);

		// 添加搜索栏
		this.createSearchBar($parent);
	}

	public void setFocus() {
		this._treeViewer.getControl().setFocus();
	}

	/**
	 * 
	 */
	public void init(IViewSite $viewSite) throws PartInitException {
		super.init($viewSite);
		this._viewSite = $viewSite;
		this._globalPreferences = Fvn.getPreferenceService().getGlobalScope();
		this._expandedNodes = new ArrayList<String>();

		/** Load Settings */
		this.loadState();
	}

	private void loadState() {
		_filterType = FileNavigatorFilter.Type.values()[_globalPreferences.getInteger(DefaultPreferences.FN_FILTER_TYPE)];

		IUser __user = new UserReference(ApplicationContext.getDefault().getUser_uuid()).resolve();

		this._rootEmailboxs = __user.getEmailboxs();

		if (_rootEmailboxs != null && _rootEmailboxs.size() > 0) {
			this._selectedEmailboxSet = this._rootEmailboxs.get(_globalPreferences.getInteger(DefaultPreferences.FN_MAILBOX_INDEX));
		}
	}

	private IEmailbox _selectedEmailboxSet;

	private TreeViewer _treeViewer;

	private FileNavigatorContentProvider _treeViewerContentProvider;

	private FileNavigatorLabelProvider _treeViewerLabelProvider;

	private void createViewer(Composite $parent) {

		this._treeViewer = new FileNavigatorViewer(this, $parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		this._treeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this._treeViewer.getTree().setHeaderVisible(false);
		this._treeViewer.getTree().setLinesVisible(false);

		/** Create ContentProvider */
		this._treeViewerContentProvider = new FileNavigatorContentProvider();
		this._treeViewer.setContentProvider(this._treeViewerContentProvider);

		/** Create LabelProvider */
		this._treeViewerLabelProvider = new FileNavigatorLabelProvider();
		this._treeViewer.setLabelProvider(this._treeViewerLabelProvider);

		this._treeViewer.setInput(this._selectedEmailboxSet);
		this._treeViewer.expandToLevel(2);

		TreeViewerUtil.right_key_blank_control_menu(this._treeViewer);

		// 启用拖拽
		this.initDragAndDrop();

		/* Apply Filter */
		this._fileNavigatorFilter = new FileNavigatorFilter();
		this._fileNavigatorFilter.setType(this._filterType);
		this._treeViewer.addFilter(this._fileNavigatorFilter);

		/* Enable "Link to FileView" */
		this._treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent $event) {
				FvnUI.openInEmailView(_viewSite.getPage(), (IStructuredSelection) _treeViewer.getSelection());
			}
		});

		/* Update List of Expanded Nodes */
		this._treeViewer.addTreeListener(new ITreeViewerListener() {
			public void treeCollapsed(TreeExpansionEvent $event) {
				onTreeEvent($event.getElement(), false);
			}

			public void treeExpanded(TreeExpansionEvent $event) {
				onTreeEvent($event.getElement(), true);
			}
		});

		/* Link if enabled */
		if (_linkingEnabled) {
			IWorkbenchPart ___activePart = _viewSite.getPage().getActivePart();
			if (___activePart instanceof IEditorPart)
				editorActivated((IEditorPart) ___activePart);
		}
	}

	private void initDragAndDrop() {

		int __ops = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;

		Transfer[] __dragTransfers = new Transfer[] { LocalSelectionTransfer.getTransfer(), TextTransfer.getInstance() };
		Transfer[] __dropTransfers = new Transfer[] { LocalSelectionTransfer.getTransfer(), TextTransfer.getInstance(), FileTransfer.getInstance() };

		FileNavigatorDNDImpl __fileNavigatorDND = new FileNavigatorDNDImpl(this, this._treeViewer);

		this._treeViewer.addDragSupport(__ops, __dragTransfers, __fileNavigatorDND);

		this._treeViewer.addDropSupport(__ops, __dropTransfers, __fileNavigatorDND);
	}

	private Label _separator;

	private Composite _searchBarContainer;

	private FileNavigatorSearchBar _searchBar;

	private void createSearchBar(Composite $parent) {

		// Add Separator between Tree and Search Bar
		this._separator = new Label($parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		this._separator.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		// Container for the SearchBar
		this._searchBarContainer = new Composite($parent, SWT.NONE);
		this._searchBarContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		// Hide SearchBar in case settings tell so
		((GridData) this._separator.getLayoutData()).exclude = false;
		((GridData) this._searchBarContainer.getLayoutData()).exclude = false;

		// Apply Layout
		GridLayout __searchBarLayout = new GridLayout(1, false);
		__searchBarLayout.marginHeight = 2;
		__searchBarLayout.marginWidth = 2;
		this._searchBarContainer.setLayout(__searchBarLayout);

		// Create the SearchBar
		this._searchBar = new FileNavigatorSearchBar(_viewSite, this._searchBarContainer, this._treeViewer, this._fileNavigatorFilter);

	}

	// SelectAllAction
	public class SelectAllAction extends Action {

		public void run() {
			Control __focusControl = _treeViewer.getControl().getDisplay().getFocusControl();

			// select all in text widget
			if (__focusControl instanceof Text) {
				((Text) __focusControl).selectAll();
			}
			// select all in tree
			else {
				_treeViewer.getTree().selectAll();
			}
		}
	}

	// CopyAction
	public class CopyAction extends Action {

		public void run() {
			Control __focusControl = _treeViewer.getControl().getDisplay().getFocusControl();

			// copy in text widget
			if (__focusControl instanceof Text) {
				((Text) __focusControl).copy();
			}
		}
	}

	// RefreshAction
	public class RefreshAction extends Action {
		public RefreshAction() {
			this.setText(Messages.FileNavigatorView_REFRESH);
		}

		public void run() {
			System.out.println(this.getId());
		}
	}

	private RefreshAction _refreshAction;

	private SelectAllAction _selectAllAction;

	private CopyAction _copyAction;

	private NewEmailAccountAction _newEmailAccountAction;

	private SearchFilesAction _searchFilesAction;

	private DeleteTypesAction _deleteTypesAction;

	private void createAction() {

		this._refreshAction = new RefreshAction();

		this._selectAllAction = new SelectAllAction();

		this._copyAction = new CopyAction();

		this._newEmailAccountAction = new NewEmailAccountAction(this._viewSite.getShell());
		this._newEmailAccountAction.setText(Messages.FileNavigatorView_NEW_EMAILACCOUNT);
		this._newEmailAccountAction.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/emailaccount.png"));

		this._searchFilesAction = new SearchFilesAction(this._viewSite.getWorkbenchWindow(), this._treeViewer);

		this._deleteTypesAction = new DeleteTypesAction(this._treeViewer.getControl().getShell(), (IStructuredSelection) this._treeViewer.getSelection());

	}

	private void hookGlobalActions() {

		// refresh
		this._viewSite.getActionBars().setGlobalActionHandler(FVNActionFactory.REFRESH.getId(), this._refreshAction);

		// select all
		this._viewSite.getActionBars().setGlobalActionHandler(FVNActionFactory.SELECT_ALL.getId(), this._selectAllAction);

		// copy
		this._viewSite.getActionBars().setGlobalActionHandler(FVNActionFactory.COPY.getId(), this._copyAction);

		// properties
		this._viewSite.getActionBars().setGlobalActionHandler(FVNActionFactory.PROPERTIES.getId(), new EntityPropertyDialogAction(this._viewSite, this._treeViewer));

		// disable some edit-actions at first
		this._viewSite.getActionBars().getGlobalActionHandler(FVNActionFactory.COPY.getId()).setEnabled(false);

		/** Delete */
		this._viewSite.getActionBars().setGlobalActionHandler(FVNActionFactory.DELETE.getId(), this._deleteTypesAction);

	}

	private void hookContextualMenu() {
		MenuManager __manager = new MenuManager();
		__manager.setRemoveAllWhenShown(true);

		__manager.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager $manager) {

				// new Menu
				MenuManager ___newMenu = new MenuManager(Messages.FileNavigatorView_NEW);
				$manager.add(___newMenu);

				// new File...
				Action ___newFileAction = new Action(Messages.FileNavigatorView_NEW_FILE) {
					public void run() {
						new NewFileAction(_viewSite.getShell()).run(null);
					}

					public ImageDescriptor getImageDescriptor() {
						return CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/file_obj.gif");//$NON-NLS-1$
					}
				};
				___newFileAction.setId("com.myfvn.ui.actions.NewFile");//$NON-NLS-1$
				___newFileAction.setActionDefinitionId("com.myfvn.ui.actions.NewFile");//$NON-NLS-1$
				___newMenu.add(___newFileAction);

				// new Folder...
				Action ___newFolderAction = new Action(Messages.FileNavigatorView_NEW_FOLDER) {

					public ImageDescriptor getImageDescriptor() {
						return CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/obj16/folder_new.gif");//$NON-NLS-1$
					}
				};
				___newFolderAction.setId("com.myfvn.ui.actions.NewFolder");//$NON-NLS-1$
				___newFolderAction.setActionDefinitionId("com.myfvn.ui.actions.NewFolder");//$NON-NLS-1$
				___newMenu.add(___newFolderAction);
				___newMenu.add(new Separator());

				// new Email Account...
				___newMenu.add(_newEmailAccountAction);
				$manager.add(new Separator());

				final IStructuredSelection ___selection = (IStructuredSelection) _treeViewer.getSelection();

				if (!___selection.isEmpty()) {
					// Open in New Tab || Open All in New Tabs
					$manager.add(new OpenInNewTabAction(getSite().getWorkbenchWindow(), _viewSite.getPage(), _treeViewer));
				}

				// Search Files...
				$manager.add(new Separator());
				$manager.add(_searchFilesAction);
				$manager.add(new GroupMarker(IWorkbenchActionConstants.FIND_EXT));

				/* Share */
				if (getEmail(___selection) != null) {
					$manager.add(new Separator("share"));//$NON-NLS-1$
					MenuManager ___shareMail = new MenuManager(Messages.FileNavigatorView_SHARING, CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/elcl16/share.gif"), "sharemail"); //$NON-NLS-1$//$NON-NLS-2$
					$manager.add(___shareMail);

					List<ShareProvider> ____providers = Controller.getDefault().getShareProviders();
					for (final ShareProvider _____provider : ____providers) {
						if (!_____provider.isEnabled()) {
							___shareMail.add(new Action(_____provider.getName()) {
								@Override
								public ImageDescriptor getImageDescriptor() {
									if (StringUtil.isSet(_____provider.getIconPath()))
										return CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, _____provider.getIconPath());
									return super.getImageDescriptor();
								};
							});
						}
					}

					/* Configure Providers */
					___shareMail.add(new Separator());
					___shareMail.add(new Action(Messages.FileNavigatorView_CONFIGURE) {
						@Override
						public void run() {
							PreferencesUtil.createPreferenceDialogOn(_treeViewer.getTree().getShell(), SharingPreferencesPage.ID, null, null).open();
						}
					});
				}

				$manager.add(new Separator("copy"));//$NON-NLS-1$
				$manager.add(new GroupMarker("edit"));//$NON-NLS-1$

				/* Refresh */
				$manager.add(new Separator());
				$manager.add(_refreshAction);

				/* Helper */
				if (getEmail(___selection) != null) {
					$manager.add(new Separator("helper"));//$NON-NLS-1$
					MenuManager ___helper = new MenuManager(Messages.FileNavigatorView_HELPER, "helper"); //$NON-NLS-1$
					$manager.add(___helper);
					fillHelperMenuAction(___helper);

					MenuManager ___compare = new MenuManager(Messages.FileNavigatorView_COMPARE_WITH, "comparewith"); //$NON-NLS-1$
					$manager.add(___compare);
					fillCompareMenuAction(___compare);

					MenuManager ___replace = new MenuManager(Messages.FileNavigatorView_REPLACE_WITH, "replacewith"); //$NON-NLS-1$
					$manager.add(___replace);
					fillReplaceMenuAction(___replace);
				}

				if (!___selection.isEmpty() && ___selection.getFirstElement() instanceof IAttachment) {
					$manager.add(new Separator());
					Action ____openTheFilePathAction = new Action(Messages.FileNavigatorView_OPEN_THE_FILE_PATH) {

					};
					$manager.add(____openTheFilePathAction);
				}

				// allow contributions
				$manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		});

		// create and register with workbench
		Menu __menu = __manager.createContextMenu(this._treeViewer.getControl());
		this._treeViewer.getControl().setMenu(__menu);
		this._viewSite.registerContextMenu(__manager, this._treeViewer);

	}

	private void fillReplaceMenuAction(MenuManager $parentMenu) {
		$parentMenu.add(new Action(Messages.FileNavigatorView_LATEST_FROM_REPOSITORY) {
		});

		$parentMenu.add(new Action(Messages.FileNavigatorView_LOCAL_HISTORY) {
		});

		$parentMenu.add(new Action(Messages.FileNavigatorView_REMOTE_HISTORY) {
		});
	}

	private void fillCompareMenuAction(MenuManager $parentMenu) {
		$parentMenu.add(new Action(Messages.FileNavigatorView_LATEST_FROM_REPOSITORY) {
		});

		$parentMenu.add(new Action(Messages.FileNavigatorView_LOCAL_HISTORY) {
		});

		$parentMenu.add(new Action(Messages.FileNavigatorView_REMOTE_HISTORY) {
		});
	}

	private void fillHelperMenuAction(MenuManager $parentMenu) {
		$parentMenu.add(new Action(Messages.FileNavigatorView_COMMIT) {
		});

		$parentMenu.add(new Action(Messages.FileNavigatorView_UPDATE) {
		});
		$parentMenu.add(new Separator());

		$parentMenu.add(new Action(Messages.FileNavigatorView_SHOW_HISTORY) {
			public void run() {
				FvnUI.showHistoryView();
			}

			public ImageDescriptor getImageDescriptor() {
				return CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/history_view.gif");
			}
		});
		$parentMenu.add(new Separator());

		$parentMenu.add(new Action(Messages.FileNavigatorView_SHOW_LOCAL_HISTORY) {
		});
		$parentMenu.add(new Separator());

		$parentMenu.add(new Action(Messages.FileNavigatorView_COPY) {
			public ImageDescriptor getImageDescriptor() {
				return CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/copy_edit.gif");
			}
		});
		$parentMenu.add(new Action(Messages.FileNavigatorView_EXPORT) {
		});
		$parentMenu.add(new Separator());

		$parentMenu.add(new Action(Messages.FileNavigatorView_CLEANUP) {
		});
	}

	private IEntityListener _entityListener;

	private void registerListeners() {
		/* Listen for Editors activated for the linking Feature */
		_partListener = new IPartListener2() {
			public void partActivated(IWorkbenchPartReference $ref) {
				if ($ref.getPart(true) instanceof IEditorPart) {

					/* Workaround for Bug 573 */
					JobRunner.runInUIThread(50, _treeViewer.getTree(), new Runnable() {
						public void run() {
							editorActivated(_viewSite.getPage().getActiveEditor());
						}
					});
				}
			}

			public void partBroughtToTop(IWorkbenchPartReference $ref) {
				if ($ref.getPart(true) == FileNavigatorView.this)
					editorActivated(_viewSite.getPage().getActiveEditor());
			}

			public void partOpened(IWorkbenchPartReference $ref) {
				if ($ref.getPart(true) == FileNavigatorView.this)
					editorActivated(_viewSite.getPage().getActiveEditor());
			}

			public void partVisible(IWorkbenchPartReference $ref) {
				if ($ref.getPart(true) == FileNavigatorView.this)
					editorActivated(_viewSite.getPage().getActiveEditor());
			}

			public void partClosed(IWorkbenchPartReference $ref) {
			}

			public void partDeactivated(IWorkbenchPartReference $ref) {
			}

			public void partHidden(IWorkbenchPartReference $ref) {
			}

			public void partInputChanged(IWorkbenchPartReference $ref) {
				if ($ref.getPart(true) instanceof IEditorPart) {

					/* Workaround for Bug 1126 */
					JobRunner.runInUIThread(50, _treeViewer.getTree(), new Runnable() {
						public void run() {
							editorActivated(_viewSite.getPage().getActiveEditor());
						}
					});
				}
			}
		};

		_viewSite.getPage().addPartListener(_partListener);
	}

	private void unregisterListeners() {
		this._viewSite.getPage().removePartListener(_partListener);
		// DynamicDAO.getInstance().removeEntityListener(this._entityListener);
	}

	@Override
	public void dispose() {
		super.dispose();
		this.unregisterListeners();
		this.saveState();
	}

	private void hookToolBar() {
		this._toolBarManager = this._viewSite.getActionBars().getToolBarManager();

		final IAction __fileNavigatorFilter = new Action(Messages.FileNavigatorView_FILTER_ELEMENTS, IAction.AS_DROP_DOWN_MENU) {

			public void run() {
				if (_fileNavigatorFilter.getType() != FileNavigatorFilter.Type.SHOW_ALL) {
					doFilter(FileNavigatorFilter.Type.SHOW_ALL);
				} else if (_lastFilterType != null) {
					doFilter(_lastFilterType);
				} else if (_toolBarManager instanceof ToolBarManager) {
					FvnUI.positionDropDownMenu(this, (ToolBarManager) _toolBarManager);
				}
			}

			public ImageDescriptor getImageDescriptor() {
				if (_fileNavigatorFilter.getType() == FileNavigatorFilter.Type.SHOW_ALL) {
					return FvnUI.FILTER;
				}
				return CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/filter_active.gif");//$NON-NLS-1$
			}
		};
		__fileNavigatorFilter.setId(FILTER_ACTION);

		__fileNavigatorFilter.setMenuCreator(new AbstractContextMenuCreator() {

			@Override
			public Menu createMenu(Control $parent) {
				Menu ___menu = new Menu($parent);

				/* Filter: None */
				final MenuItem ___showAll = new MenuItem(___menu, SWT.RADIO);
				___showAll.setText(Messages.FileNavigatorView_SHOW_ALL);
				___showAll.setSelection(FileNavigatorFilter.Type.SHOW_ALL == _fileNavigatorFilter.getType());
				___menu.setDefaultItem(___showAll);
				___showAll.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent $event) {
						if (___showAll.getSelection() && _fileNavigatorFilter.getType() != FileNavigatorFilter.Type.SHOW_ALL) {
							doFilter(FileNavigatorFilter.Type.SHOW_ALL);
						}
					}
				});

				/* Separator */
				new MenuItem(___menu, SWT.SEPARATOR);

				/* Filter: Mail */
				final MenuItem ___showEmail = new MenuItem(___menu, SWT.RADIO);
				___showEmail.setText(Messages.FileNavigatorView_SHOW_MAIL);
				___showEmail.setSelection(FileNavigatorFilter.Type.SHOW_MAIL == _fileNavigatorFilter.getType());
				___showEmail.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent $event) {
						if (___showEmail.getSelection() && _fileNavigatorFilter.getType() != FileNavigatorFilter.Type.SHOW_MAIL)
							doFilter(FileNavigatorFilter.Type.SHOW_MAIL);
					}
				});

				/* Filter: Attachment */
				final MenuItem ___showAttachment = new MenuItem(___menu, SWT.RADIO);
				___showAttachment.setText(Messages.FileNavigatorView_SHOW_ATTACHMENT);
				___showAttachment.setSelection(FileNavigatorFilter.Type.SHOW_ATTACHMENT == _fileNavigatorFilter.getType());
				___showAttachment.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent $event) {
						if (___showAttachment.getSelection() && _fileNavigatorFilter.getType() != FileNavigatorFilter.Type.SHOW_ATTACHMENT)
							doFilter(FileNavigatorFilter.Type.SHOW_ATTACHMENT);
					}
				});

				return ___menu;
			}
		});

		this._toolBarManager.add(__fileNavigatorFilter);

		/** Collapse All */
		this._toolBarManager.add(new Separator());
		IAction __collapseAll = new Action(Messages.FileNavigatorView_COLLAPSE_ALL) {
			public void run() {
				_treeViewer.collapseAll();
			}
		};
		__collapseAll.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/collapseall.gif"));//$NON-NLS-1$
		this._toolBarManager.add(__collapseAll);

		/** Link with Editor */
		IAction __linkWithEditor = new Action(Messages.FileNavigatorView_LINK_WITH_EDITOR) {
			public void run() {
				_linkingEnabled = isChecked();

				/* Link if enabled */
				if (_linkingEnabled) {
					IEditorPart ___editor = _viewSite.getPage().getActiveEditor();
					if (___editor != null)
						editorActivated(___editor);
				}
			}
		};
		__linkWithEditor.setChecked(false);
		__linkWithEditor.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/synced.gif"));//$NON-NLS-1$
		this._toolBarManager.add(__linkWithEditor);

		this._toolBarManager.add(new Separator());
		/* MailSet Navigation - TODO Consider showing dynamically */
		IAction __previousSet = new Action(Messages.FileNavigatorSearchBar_PREVIOUS_SET) {
			@Override
			public void run() {
				int ___index = getIndexOfRootEmailbox(_selectedEmailboxSet);
				changeSet(getRootEmailboxAt(___index - 1));
			}

			@Override
			public boolean isEnabled() {
				int ___index = getIndexOfRootEmailbox(_selectedEmailboxSet);
				return ___index > 0 && _rootEmailboxs.size() > 1;
			}
		};
		__previousSet.setId(PREVIOUS_SET_ACTION);
		__previousSet.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/backward.gif"));//$NON-NLS-1$
		__previousSet.setDisabledImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/dtool16/backward.gif"));//$NON-NLS-1$
		this._toolBarManager.add(__previousSet);

		IAction __nextSet = new Action(Messages.FileNavigatorSearchBar_NEXT_SET) {
			@Override
			public void run() {
				int ___index = getIndexOfRootEmailbox(_selectedEmailboxSet);
				changeSet(getRootEmailboxAt(___index + 1));
			}

			@Override
			public boolean isEnabled() {
				int ___index = getIndexOfRootEmailbox(_selectedEmailboxSet);
				return ___index < (_rootEmailboxs.size() - 1) && _rootEmailboxs.size() > 1;
			}
		};
		__nextSet.setId(NEXT_SET_ACTION);
		__nextSet.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/forward.gif"));//$NON-NLS-1$
		__nextSet.setDisabledImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/dtool16/forward.gif"));//$NON-NLS-1$
		this._toolBarManager.add(__nextSet);

	}

	private void hookViewMenu() {
		IMenuManager __menuManager = this._viewSite.getActionBars().getMenuManager();
		__menuManager.setRemoveAllWhenShown(true);

		__menuManager.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager $manager) {

				/* Manage Mail Sets */
				IAction ___manageSets = new Action(Messages.FileNavigatorSearchBar_MANAGE_SETS) {
					@Override
					public void run() {
						ManageSetsDialog ____instance = ManageSetsDialog.getVisibleInstance();
						if (____instance == null) {
							ManageSetsDialog _____dialog = new ManageSetsDialog(_viewSite.getShell(), _selectedEmailboxSet);
							_____dialog.open();
						} else {
							____instance.getShell().forceActive();
						}
					}
				};
				$manager.add(___manageSets);

				/* Available Emailbox Sets */
				$manager.add(new Separator());
				for (final IEmailbox ___emailbox : _rootEmailboxs) {
					IAction ____selectEmailboxSet = new Action(___emailbox.getName() + "@", IAction.AS_RADIO_BUTTON) {//$NON-NLS-1$
						@Override
						public void run() {
							if (!_selectedEmailboxSet.equals(___emailbox) && this.isChecked()) {
								changeSet(___emailbox);
							}
						}
					};
					____selectEmailboxSet.setImageDescriptor(CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/obj16/bkmrk_set.gif"));//$NON-NLS-1$

					if (_selectedEmailboxSet.equals(___emailbox)) {
						____selectEmailboxSet.setChecked(true);
					}
					$manager.add(____selectEmailboxSet);
				}

				/* Search Bar */
				$manager.add(new Separator());
				MenuManager ___searchMenu = new MenuManager(Messages.FileNavigatorView_FIND);
				$manager.add(___searchMenu);

				/* Search Bar - Always Show Bar */
				IAction ___alwaysShow = new Action(Messages.FileNavigatorView_ALWAYS_SHOW, IAction.AS_CHECK_BOX) {
					@Override
					public void run() {
						/* Only Update if the Filter is not Active */
					}
				};
				___alwaysShow.setChecked(true);
				___searchMenu.add(___alwaysShow);

				/* Search Bar - Begin Search when Typing */
				IAction ___beginWhenTyping = new Action(Messages.FileNavigatorView_BEGIN_WHEN_TYPING, IAction.AS_CHECK_BOX) {
					@Override
					public void run() {

					}
				};
				___beginWhenTyping.setChecked(true);
				___searchMenu.add(___beginWhenTyping);

				$manager.add(new Separator());
				/* Misc Settings */
				IAction ___showFavicons = new Action(Messages.FileNavigatorView_SHOW_FAVICONS, IAction.AS_CHECK_BOX) {
					@Override
					public void run() {

					}
				};
				___showFavicons.setChecked(true);
				$manager.add(___showFavicons);

				/* Allow Contributions */
				$manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		});

		/* Dummy Entry to show Menu in View */
		__menuManager.add(new Action("") {}); //$NON-NLS-1$

	}

	public void saveState() {
		saveExpandedElements();

		/* Misc. Settings */
		if (!_blockSaveState) {
			_globalPreferences.putInteger(DefaultPreferences.FN_FILTER_TYPE, _fileNavigatorFilter.getType().ordinal());
			_globalPreferences.putInteger(DefaultPreferences.FN_MAILBOX_INDEX, getIndexOfRootEmailbox(_selectedEmailboxSet));
		}

	}

	private void doFilter(FileNavigatorFilter.Type $type) {

		/* Remember Selection */
		if ($type != FileNavigatorFilter.Type.SHOW_ALL)
			_lastFilterType = $type;
		else if (this._fileNavigatorFilter.getType() != FileNavigatorFilter.Type.SHOW_ALL)
			_lastFilterType = this._fileNavigatorFilter.getType();

		/* Change Filter Type */
		this._fileNavigatorFilter.setType($type);
		this._treeViewer.refresh(false);

		/* Restore Expanded Elements */
		restoreExpandedElements();

		/* Update Image */
		this._toolBarManager.find(FILTER_ACTION).update(IAction.IMAGE);
	}

	void restoreExpandedElements() {

	}

	private IEmailbox getRootEmailboxAt(int $index) {
		int __i = 0;
		for (IEmailbox __rootEmailbox : this._rootEmailboxs) {
			if (__i == $index) {
				return __rootEmailbox;
			}
			__i++;
		}
		return null;
	}

	private int getIndexOfRootEmailbox(IEmailbox $emailbox) {
		int __i = 0;
		for (IEmailbox ___emailbox : this._rootEmailboxs) {
			if (___emailbox.equals($emailbox))
				return __i;
			__i++;
		}
		return -1;
	}

	private void changeSet(IEmailbox $emailbox) {
		/* Save Expanded Elements */
		this.saveExpandedElements();

		/* Set new Input */
		this._selectedEmailboxSet = $emailbox;
		this._treeViewer.setInput(this._selectedEmailboxSet);

		/* Restore Expanded Elements */
		this._expandedNodes.clear();
		this.loadExpandedElements();
		this._treeViewer.getControl().setRedraw(false);
		try {
			this.restoreExpandedElements();
		} finally {
			this._treeViewer.getControl().setRedraw(true);
		}

		/* Update Set Actions */
		this._viewSite.getActionBars().getToolBarManager().find(PREVIOUS_SET_ACTION).update(IAction.ENABLED);
		this._viewSite.getActionBars().getToolBarManager().find(NEXT_SET_ACTION).update(IAction.ENABLED);

	}

	private void saveExpandedElements() {

	}

	private void onTreeEvent(Object $element, boolean $expanded) {
		/* Element expanded - add to List of expanded Nodes */
		if ($expanded) {
			if ($element instanceof IEntity) {
				this._expandedNodes.add(((IEntity) $element).getUuid());
			}
		}
		/* Element collapsed - remove from List of expanded Nodes */
		else {
			if ($element instanceof IEntity) {
				this._expandedNodes.remove(((IEntity) $element).getUuid());
			}
		}
	}

	private void loadExpandedElements() {

	}

	private void editorActivated(IEditorPart $part) {
		if (!_linkingEnabled || $part == null)
			return;

		/* Try to select and reveal editor input in the Explorer */
		IEditorInput __editorInput = $part.getEditorInput();
		if (__editorInput instanceof EmailViewInput)
			reveal(((EmailViewInput) __editorInput).getEmail(), false);
	}

	public void reveal(IChild $element, boolean $expand) {
		/* Return early if hidden */
		if (!_viewSite.getPage().isPartVisible(this))
			return;

		/* Change Set if required */
		IChild __child = $element;
		while (__child.getParent() != null) {
			__child = __child.getParent();
			if (__child instanceof IEmailbox) {
				break;
			}
		}

		if (!_selectedEmailboxSet.equals(__child))
			changeSet((IEmailbox) __child);

		/* Set Selection */
		_treeViewer.setSelection(new StructuredSelection($element), true);

		/* Expand if Set */
		if ($expand) {
			_treeViewer.setExpandedState($element, true);
			_expandedNodes.add($element.getUuid());
		}
	}

	private IEmail getEmail(IStructuredSelection $selection) {
		List<?> __list = $selection.toList();
		for (Object ___object : __list) {
			if (___object instanceof IEmail)
				return (IEmail) ___object;
		}
		return null;
	}

	public void saveStateOnDispose(boolean $saveStateOnDispose) {
		_blockSaveState = !$saveStateOnDispose;
	}

	/**
	 * The user performed the "Find" action.
	 */
	public void find() {
		setSearchBarVisible(((GridData) _separator.getLayoutData()).exclude);
		_searchBar.getControl().setFocus();
	}

	void setSearchBarVisible(boolean $visible) {

		/* Return if no State Change */
		if ($visible != ((GridData) _separator.getLayoutData()).exclude)
			return;

		/* Update LayoutData and layout Parent */
		((GridData) _separator.getLayoutData()).exclude = !$visible;
		((GridData) _searchBarContainer.getLayoutData()).exclude = !$visible;
		_searchBarContainer.getParent().layout();
	}
}
