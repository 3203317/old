package com.myfvn.ui;

import java.util.Arrays;
import java.util.List;

import net.foreworld.utils.rcp.core.persist.IChild;
import net.foreworld.utils.rcp.core.persist.IParent;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.myfvn.core.Fvn;
import com.myfvn.core.internal.persist.pref.DefaultPreferences;
import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.IEmailbox;
import com.myfvn.core.persist.IUser;
import com.myfvn.core.persist.pref.IPreferenceScope;
import com.myfvn.core.persist.reference.UserReference;
import com.myfvn.ui.actions.FVNActionFactory;
import com.myfvn.ui.actions.OpenInNewTabAction;
import com.myfvn.ui.dialogs.CustomizeToolbarDialog;
import com.myfvn.ui.views.FileNavigatorFilter;
import com.myfvn.ui.views.FileNavigatorView;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 * 
 * @author huangxin (huangxin@foreworld.net)
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	protected IWorkbenchWindow _window;

	public static final String VIEW_START = "viewStart";//$NON-NLS-1$

	public static final String VIEW_END = "viewEnd";//$NON-NLS-1$

	public static final String M_TOOLS = "tools";//$NON-NLS-1$

	public static final String M_GO = "go";//$NON-NLS-1$
	public static final String M_MAILS = "mails";//$NON-NLS-1$

	public static final String TOOLS_START = "toolsStart";//$NON-NLS-1$

	public static final String TOOLS_END = "toolsEnd";//$NON-NLS-1$

	private IWorkbenchAction newWindowAction;
	private OpenViewAction openViewAction;
	private Action messagePopupAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(final IWorkbenchWindow $window) {
		this._window = $window;

		// menu : file
		this.register(FVNActionFactory.CLOSE.create($window));
		this.register(FVNActionFactory.CLOSE_ALL.create($window));
		this.register(FVNActionFactory.SAVE.create($window));
		this.register(FVNActionFactory.SAVE_AS.create($window));
		this.register(FVNActionFactory.SAVE_ALL.create($window));
		this.register(FVNActionFactory.REVERT.create($window));
		this.register(FVNActionFactory.MOVE.create($window));
		this.register(FVNActionFactory.RENAME.create($window));
		this.register(FVNActionFactory.REFRESH.create($window));
		this.register(FVNActionFactory.PRINT.create($window));
		this.register(FVNActionFactory.PROPERTIES.create($window));
		this.register(FVNActionFactory.QUIT.create($window));

		// menu : edit
		this.register(FVNActionFactory.UNDO.create($window));
		this.register(FVNActionFactory.REDO.create($window));
		this.register(FVNActionFactory.CUT.create($window));
		this.register(FVNActionFactory.COPY.create($window));
		this.register(FVNActionFactory.PASTE.create($window));
		this.register(FVNActionFactory.DELETE.create($window));
		this.register(FVNActionFactory.SELECT_ALL.create($window));
		this.register(FVNActionFactory.SEARCH.create($window));
		this.register(FVNActionFactory.FIND.create($window));

		// menu : view
		this.register(FVNActionFactory.MINIMIZE_SHELL.create($window));

		newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create($window);
		register(newWindowAction);

		openViewAction = new OpenViewAction($window, "Open Another Message View", View.ID);
		register(openViewAction);

		messagePopupAction = new MessagePopupAction("Open Message", $window);
		register(messagePopupAction);

		// menu : window
		this.register(FVNActionFactory.OPEN_NEW_WINDOW.create($window));
		this.register(FVNActionFactory.PREFERENCES.create($window));

		// menu : help
		this.register(FVNActionFactory.OPEN_HOMEPAGE.create($window));
		this.register(FVNActionFactory.HELP_CONTENTS.create($window));
		this.register(FVNActionFactory.HELP_SEARCH.create($window));
		this.register(FVNActionFactory.DYNAMIC_HELP.create($window));
		this.register(FVNActionFactory.SHOW_KEY_ASSIST.create($window));
		this.register(FVNActionFactory.ABOUT.create($window));

		this.register(FVNActionFactory.LOCK_TOOL_BAR.create($window));
	}

	protected void fillMenuBar(IMenuManager $menuBar) {

		this.createFileMenu($menuBar);

		this.createEditMenu($menuBar);

		this.createViewMenu($menuBar);

		this.createGoMenu($menuBar);

		this.createMailsMenu($menuBar);

		this.createToolsMenu($menuBar);

		this.createWindowMenu($menuBar);

		this.createHelpMenu($menuBar);
	}

	private void createFileMenu(IMenuManager $menuBar) {

		MenuManager __fileMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_FILE, IWorkbenchActionConstants.M_FILE);
		$menuBar.add(__fileMenu);

		__fileMenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
		__fileMenu.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
		__fileMenu.add(new Separator());

		__fileMenu.add(this.getAction(FVNActionFactory.CLOSE.getId()));
		__fileMenu.add(this.getAction(FVNActionFactory.CLOSE_ALL.getId()));
		__fileMenu.add(new GroupMarker(IWorkbenchActionConstants.CLOSE_EXT));
		__fileMenu.add(new Separator());

		__fileMenu.add(this.getAction(FVNActionFactory.SAVE.getId()));
		__fileMenu.add(this.getAction(FVNActionFactory.SAVE_AS.getId()));
		__fileMenu.add(this.getAction(FVNActionFactory.SAVE_ALL.getId()));
		__fileMenu.add(new Separator());

		__fileMenu.add(this.getAction(FVNActionFactory.MOVE.getId()));
		__fileMenu.add(this.getAction(FVNActionFactory.RENAME.getId()));
		__fileMenu.add(this.getAction(FVNActionFactory.REFRESH.getId()));

		if (!Application.IS_MAC) {
			__fileMenu.add(new Separator());
			__fileMenu.add(this.getAction(FVNActionFactory.PRINT.getId()));
		}

		__fileMenu.add(new Separator());
		__fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		__fileMenu.add(new Separator());

		__fileMenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
		__fileMenu.add(new Separator());

		__fileMenu.add(this.getAction(FVNActionFactory.PROPERTIES.getId()));
		__fileMenu.add(new Separator());

		__fileMenu.add(this.getAction(FVNActionFactory.QUIT.getId()));
	}

	private void createEditMenu(IMenuManager $menuBar) {

		MenuManager __editMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_EDIT, IWorkbenchActionConstants.M_EDIT);
		__editMenu.add(this.getAction(FVNActionFactory.UNDO.getId()));
		$menuBar.add(__editMenu);

		__editMenu.setRemoveAllWhenShown(true);
		__editMenu.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager $editMenu) {
				$editMenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));
				$editMenu.add(new Separator());

				$editMenu.add(getAction(FVNActionFactory.UNDO.getId()));
				$editMenu.add(getAction(FVNActionFactory.REDO.getId()));
				$editMenu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
				$editMenu.add(new Separator());

				$editMenu.add(getAction(FVNActionFactory.CUT.getId()));
				$editMenu.add(getAction(FVNActionFactory.COPY.getId()));
				$editMenu.add(getAction(FVNActionFactory.PASTE.getId()));
				$editMenu.add(new Separator());

				$editMenu.add(getAction(FVNActionFactory.DELETE.getId()));
				$editMenu.add(getAction(FVNActionFactory.SELECT_ALL.getId()));
				$editMenu.add(new Separator());

				$editMenu.add(getAction(FVNActionFactory.SEARCH.getId()));
				$editMenu.add(getAction(FVNActionFactory.FIND.getId()));

				$editMenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
			}
		});
	}

	private void createViewMenu(IMenuManager $menuBar) {
		MenuManager __viewMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_VIEW, IWorkbenchActionConstants.M_VIEW);
		__viewMenu.setRemoveAllWhenShown(true);
		$menuBar.add(__viewMenu);

		__viewMenu.add(new Action("") {
		});

		__viewMenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager $viewMenu) {

				$viewMenu.add(new GroupMarker(VIEW_START));

				/* Layout */
				MenuManager ___layoutMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_LAYOUT);
				___layoutMenu.add(new Action(Messages.ApplicationActionBarAdvisor_CLASSIC_LAYOUT, IAction.AS_RADIO_BUTTON) {
					@Override
					public void run() {
						if (super.isChecked()) {
						}
					}

					@Override
					public boolean isChecked() {
						return true;
					}
				});

				___layoutMenu.add(new Action(Messages.ApplicationActionBarAdvisor_VERTICAL_LAYOUT, IAction.AS_RADIO_BUTTON) {
					@Override
					public void run() {
						if (super.isChecked()) {
						}
					}

					@Override
					public boolean isChecked() {
						return false;
					}
				});

				$viewMenu.add(___layoutMenu);

				/* Zoom (In, Out, Reset) */
				final boolean ___isZoomingEnabled = true;
				$viewMenu.add(new Separator());
				MenuManager ___zoomMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_ZOOM);

				/* Zoom In */
				___zoomMenu.add(new Action(Messages.ApplicationActionBarAdvisor_ZOOM_IN) {
					@Override
					public void run() {
					}

					@Override
					public String getId() {
						return "com.myfvn.ui.ZoomInCommand"; //$NON-NLS-1$
					}

					@Override
					public String getActionDefinitionId() {
						return "com.myfvn.ui.ZoomInCommand"; //$NON-NLS-1$
					}

					@Override
					public boolean isEnabled() {
						return ___isZoomingEnabled;
					}
				});

				/* Zoom Out */
				___zoomMenu.add(new Action(Messages.ApplicationActionBarAdvisor_ZOOM_OUT) {
					@Override
					public void run() {
					}

					@Override
					public String getId() {
						return "com.myfvn.ui.ZoomOutCommand"; //$NON-NLS-1$
					}

					@Override
					public String getActionDefinitionId() {
						return "com.myfvn.ui.ZoomOutCommand"; //$NON-NLS-1$
					}

					@Override
					public boolean isEnabled() {
						return ___isZoomingEnabled;
					}
				});

				/* Reset */
				___zoomMenu.add(new Separator());
				___zoomMenu.add(new Action(Messages.ApplicationActionBarAdvisor_RESET) {
					@Override
					public void run() {
					}

					@Override
					public String getId() {
						return "com.myfvn.ui.ZoomResetCommand"; //$NON-NLS-1$
					}

					@Override
					public String getActionDefinitionId() {
						return "com.myfvn.ui.ZoomResetCommand"; //$NON-NLS-1$
					}

					@Override
					public boolean isEnabled() {
						return ___isZoomingEnabled;
					}
				});
				$viewMenu.add(___zoomMenu);

				/* Toolbars */
				$viewMenu.add(new Separator());
				final MenuManager ___toolbarsMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_TOOLBARS);
				___toolbarsMenu.add(new Action("") {
				});
				___toolbarsMenu.setRemoveAllWhenShown(true);
				___toolbarsMenu.addMenuListener(new IMenuListener() {
					public void menuAboutToShow(IMenuManager $manager) {
						boolean __useExternalBrowser = false;

						/* Lock Toolbar */
						IAction __lockToolbarAction = getAction(ActionFactory.LOCK_TOOL_BAR.getId());
						__lockToolbarAction.setText(Messages.ApplicationActionBarAdvisor_LOCK_TOOLBAR);
						___toolbarsMenu.add(__lockToolbarAction);

						/* Main Toolbar */
						___toolbarsMenu.add(new Action(Messages.ApplicationActionBarAdvisor_TOOLBAR, IAction.AS_CHECK_BOX) {
							@Override
							public void run() {
								ApplicationWorkbenchWindowAdvisor __configurer = ApplicationWorkbenchAdvisor._primaryApplicationWorkbenchWindowAdvisor;
								__configurer.setToolBarVisible(true, true);
							}

							@Override
							public boolean isChecked() {
								return true;
							}
						});
					}
				});
				$viewMenu.add(___toolbarsMenu);

				/* Toggle State of Status Bar Visibility */
				$viewMenu.add(new Action(Messages.ApplicationActionBarAdvisor_STATUS, IAction.AS_CHECK_BOX) {
					@Override
					public void run() {
						ApplicationWorkbenchWindowAdvisor __configurer = ApplicationWorkbenchAdvisor._primaryApplicationWorkbenchWindowAdvisor;
						__configurer.setStatusVisible(true, true);
					}

					@Override
					public boolean isChecked() {
						return true;
					}
				});

				/* Toggle State of Mails Visibility */
				$viewMenu.add(new Action(Messages.ApplicationActionBarAdvisor_MAILS, IAction.AS_CHECK_BOX) {
					@Override
					public void run() {
						FvnUI.toggleMails();
					}

					@Override
					public String getActionDefinitionId() {
						return "com.myfvn.ui.ToggleMailsCommand"; //$NON-NLS-1$
					}

					@Override
					public String getId() {
						return "com.myfvn.ui.ToggleMailsCommand"; //$NON-NLS-1$
					}

					@Override
					public boolean isChecked() {
						IWorkbenchPage __page = FvnUI.getPage();
						if (__page != null)
							return __page.findView(FileNavigatorView.ID) != null;
						return false;
					}
				});

				/* Customize Toolbar */
				$viewMenu.add(new Separator());
				$viewMenu.add(new Action(Messages.ApplicationActionBarAdvisor_CUSTOMIZE_TOOLBAR) {
					@Override
					public void run() {
						/* Open Dialog to Customize Toolbar */
						CustomizeToolbarDialog dialog = new CustomizeToolbarDialog(getActionBarConfigurer().getWindowConfigurer().getWindow().getShell());
						if (dialog.open() == IDialogConstants.OK_ID) {

						}
					}
				});

				$viewMenu.add(new Separator());
				/* Fullscreen Mode */
				$viewMenu.add(new Action(Messages.ApplicationActionBarAdvisor_FULL_SCREEN, IAction.AS_CHECK_BOX) {
					@Override
					public void run() {
						FvnUI.toggleFullScreen();
					}

					@Override
					public String getActionDefinitionId() {
						return "com.myfvn.ui.FullScreenCommand"; //$NON-NLS-1$
					}

					@Override
					public String getId() {
						return "com.myfvn.ui.FullScreenCommand"; //$NON-NLS-1$
					}

					@Override
					public boolean isChecked() {
						return super.isChecked();
					}
				});

				$viewMenu.add(getAction(FVNActionFactory.MINIMIZE_SHELL.getId()));

				$viewMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
				$viewMenu.add(new GroupMarker(VIEW_END));
			}
		});
	}

	private void createGoMenu(IMenuManager $menuBar) {
		MenuManager __goMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_GO, M_GO);
		__goMenu.setRemoveAllWhenShown(true);
		__goMenu.add(new Action("") {});// Dummy Action //$NON-NLS-1$
		__goMenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager $manager) {

				/* Configurate */
				$manager.add(new Separator());
				$manager.add(new Action(Messages.ApplicationActionBarAdvisor_CONFIGURE) {
				});
			}
		});
		$menuBar.add(__goMenu);
	}

	private void createMailsMenu(IMenuManager $menuBar) {
		MenuManager __mailsMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_MAILS, M_MAILS);
		__mailsMenu.setRemoveAllWhenShown(true);
		__mailsMenu.add(new Action("") {});// Dummy Action //$NON-NLS-1$
		__mailsMenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager $manager) {
				fillMailsMenu($manager, getActionBarConfigurer().getWindowConfigurer().getWindow());
				$manager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		});

		$menuBar.add(__mailsMenu);

	}

	private void createToolsMenu(IMenuManager $menuBar) {

		MenuManager __toolsMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_TOOLS, M_TOOLS);
		$menuBar.add(__toolsMenu);

		__toolsMenu.add(new GroupMarker(TOOLS_START));

		__toolsMenu.add(new GroupMarker("cleanup"));//$NON-NLS-1$
		__toolsMenu.add(new GroupMarker("filter"));//$NON-NLS-1$
		__toolsMenu.add(new Separator());

		__toolsMenu.add(new GroupMarker("addons"));//$NON-NLS-1$

		__toolsMenu.add(new GroupMarker(TOOLS_END));

	}

	private void createWindowMenu(IMenuManager $menuBar) {

		MenuManager __windowMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_WINDOW, IWorkbenchActionConstants.M_WINDOW);
		$menuBar.add(__windowMenu);

		__windowMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		__windowMenu.add(this.getAction(FVNActionFactory.PREFERENCES.getId()));
	}

	private void createHelpMenu(IMenuManager $menuBar) {

		MenuManager __helpMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_HELP, IWorkbenchActionConstants.M_HELP);
		$menuBar.add(__helpMenu);

		__helpMenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));

		__helpMenu.add(this.getAction(FVNActionFactory.OPEN_HOMEPAGE.getId()));
		__helpMenu.add(new Separator());

		__helpMenu.add(this.getAction(FVNActionFactory.HELP_CONTENTS.getId()));
		__helpMenu.add(this.getAction(FVNActionFactory.HELP_SEARCH.getId()));
		__helpMenu.add(this.getAction(FVNActionFactory.DYNAMIC_HELP.getId()));
		__helpMenu.add(new Separator());

		__helpMenu.add(this.getAction(FVNActionFactory.SHOW_KEY_ASSIST.getId()));

		__helpMenu.add(new Separator());
		__helpMenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));
		__helpMenu.add(new Separator());

		__helpMenu.add(this.getAction(FVNActionFactory.ABOUT.getId()));
		if (Application.IS_MAC) {
			IContributionItem ___item = __helpMenu.find(FVNActionFactory.ABOUT.getId());

			if (___item != null)
				___item.setVisible(false);
		}
	}

	protected void fillCoolBar(ICoolBarManager $coolBar) {

		/* CoolBar Context Menu */
		MenuManager __coolBarContextMenuManager = new MenuManager(null, "com.myfvn.ui.CoolBarContextMenu"); //$NON-NLS-1$
		$coolBar.setContextMenuManager(__coolBarContextMenuManager);

		/* Customize Coolbar */
		__coolBarContextMenuManager.add(new Action(Messages.ApplicationActionBarAdvisor_CUSTOMIZE_TOOLBAR) {
			@Override
			public void run() {

				/* Open Dialog to Customize Toolbar */
				CustomizeToolbarDialog dialog = new CustomizeToolbarDialog(_window.getShell());
				if (dialog.open() == IDialogConstants.OK_ID) {

				}
			}
		});

		/* Lock Coolbar */
		__coolBarContextMenuManager.add(new Separator());
		IAction __lockToolbarAction = getAction(FVNActionFactory.LOCK_TOOL_BAR.getId());
		__lockToolbarAction.setText(Messages.ApplicationActionBarAdvisor_LOCK_TOOLBAR);
		__coolBarContextMenuManager.add(__lockToolbarAction);

		/* Toggle State of Toolbar Visibility */
		__coolBarContextMenuManager.add(new Action(Messages.ApplicationActionBarAdvisor_HIDE_TOOLBAR) {
			@Override
			public void run() {
			}
		});

		/* Support for more Contributions */
		__coolBarContextMenuManager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		$coolBar.add(new ToolBarContributionItem(toolbar, "main"));
		toolbar.add(openViewAction);
		toolbar.add(messagePopupAction);
	}

	/**
	 * 
	 * @param $menu
	 * @param $window
	 */
	public static void fillMailsMenu(IMenuManager $menu, IWorkbenchWindow $window) {
		IUser __user = new UserReference(ApplicationContext.getDefault().getUser_uuid()).resolve();
		List<IEmailbox> __roots = __user.getEmailboxs();

		/* Filter Options */
		final IPreferenceScope __globalPreferences = Fvn.getPreferenceService().getGlobalScope();
		FileNavigatorFilter.Type[] __allFilters = FileNavigatorFilter.Type.values();
		FileNavigatorFilter.Type __selectedFilter = __allFilters[__globalPreferences.getInteger(DefaultPreferences.MENU_MAILS_FILTER)];
		List<FileNavigatorFilter.Type> __displayedFilters = Arrays.asList(new FileNavigatorFilter.Type[] { FileNavigatorFilter.Type.SHOW_ALL, FileNavigatorFilter.Type.SHOW_MAIL, FileNavigatorFilter.Type.SHOW_ATTACHMENT });

		MenuManager __optionsMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_FILTER_ELEMENTS, FvnUI.FILTER, "filter"); //$NON-NLS-1$
		for (final FileNavigatorFilter.Type ___filter : __displayedFilters) {
			String ____name = Messages.ApplicationActionBarAdvisor_SHOW_ALL;
			switch (___filter) {
			case SHOW_MAIL:
				____name = Messages.ApplicationActionBarAdvisor_SHOW_MAIL;
				break;
			case SHOW_ATTACHMENT:
				____name = Messages.ApplicationActionBarAdvisor_SHOW_ATTACHMENT;
				break;
			}

			Action ___action = new Action(____name, IAction.AS_RADIO_BUTTON) {
				@Override
				public void run() {
					if (isChecked()) {
						__globalPreferences.putInteger(DefaultPreferences.MENU_MAILS_FILTER, ___filter.ordinal());
					}
				}
			};
			___action.setChecked(___filter == __selectedFilter);
			__optionsMenu.add(___action);
			if (___filter == FileNavigatorFilter.Type.SHOW_ALL) {
				__optionsMenu.add(new Separator());
			}
		}
		$menu.add(__optionsMenu);
		$menu.add(new Separator());

		for (IParent ___root : __roots) {
			if (shouldShow(___root, __selectedFilter)) {
				MenuManager ___rootItem = new MenuManager(___root.getName(), ___root.getUuid());
				$menu.add(___rootItem);

				fillEmailboxsMenu($window, __globalPreferences, ___rootItem, ___root.getChildren(), __selectedFilter);
			}
		}
	}

	private static void fillEmailboxsMenu(final IWorkbenchWindow $window, final IPreferenceScope $preferences, IMenuManager $parent, List<IChild> $childs, final FileNavigatorFilter.Type $filter) {
		for (final IChild ___child : $childs) {
			/* Check if a Filter applies */
			if (!shouldShow((IParent) ___child, $filter)) {
				continue;
			}

			if (___child instanceof IEmail) {
				final MenuManager ____emailMenu = new MenuManager(___child.getName());
				$parent.add(____emailMenu);
				____emailMenu.add(new Action("") {});//$NON-NLS-1$
				____emailMenu.setRemoveAllWhenShown(true);
				____emailMenu.addMenuListener(new IMenuListener() {
					public void menuAboutToShow(IMenuManager $manager) {

						/* Open in New Tab */
						____emailMenu.add(new Action(Messages.ApplicationActionBarAdvisor_OPEN_IN_NEW_TAB) {
							public void run() {
								BusyIndicator.showWhile(PlatformUI.getWorkbench().getDisplay(), new Runnable() {
									public void run() {
										FvnUI.openInEmailView($window.getActivePage(), new StructuredSelection(___child));
									}
								});
							}
						});
						/* Open All in New Tabs */
						____emailMenu.add(new OpenInNewTabAction(FvnUI.getPage($window), (IParent) ___child));

						____emailMenu.add(new Separator());

						/* Show other entries */
						fillEmailboxsMenu($window, $preferences, ____emailMenu, ((IParent) ___child).getChildren(), $filter);
					}
				});
			} else {
				$parent.add(new Action(___child.getName()) {
				});
			}
		}
	}

	private static boolean shouldShow(IParent $child, FileNavigatorFilter.Type $filter) {
		switch ($filter) {
		case SHOW_ALL:
			return true;
		case SHOW_MAIL:
			return hasMailsWithState($child);
		case SHOW_ATTACHMENT:
			return hasAttachmentsWithState($child);
		default:
			return true;
		}
	}

	private static boolean hasMailsWithState(IParent $child) {
		return ($child instanceof IEmailbox || $child instanceof IEmail);
	}

	private static boolean hasAttachmentsWithState(IParent $child) {
		if (!($child instanceof IAttachment)) {

		}
		return true;
	}

}
