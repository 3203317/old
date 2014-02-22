package com.myfvn.ui;

import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

import net.foreworld.utils.StringUtil;
import net.foreworld.utils.rcp.CacheImage;
import net.foreworld.utils.rcp.core.persist.IEntity;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.DeviceResourceException;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.myfvn.core.persist.IEmail;
import com.myfvn.ui.utils.EditorUtils;
import com.myfvn.ui.views.FileNavigatorView;
import com.myfvn.ui.views.HistoryView;
import com.myfvn.ui.views.editors.email.EmailView;
import com.myfvn.ui.views.editors.email.EmailViewInput;
import com.myfvn.ui.views.editors.email.PerformAfterInputSet;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class FvnUI {
	static final Logger _logger = Logger.getLogger(FvnUI.class.getName());

	/** Dialog Font Id */
	public static final String DIALOG_FONT_ID = "org.eclipse.jface.dialogfont"; //$NON-NLS-1$

	public static final String FILE_NAVIGATOR_FONT_ID = "com.myfvn.ui.FileNavigatorFont";//$NON-NLS-1$

	/** Default */
	public static final ImageDescriptor UNKNOWN = CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/obj16/default.gif"); //$NON-NLS-1$
	/** Folder with new News */
	public static final ImageDescriptor FOLDER_NEW = CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/obj16/folder_new.gif"); //$NON-NLS-1$
	/** Folder */
	public static final ImageDescriptor FOLDER = CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/obj16/folder.gif"); //$NON-NLS-1$
	/** Attachment */
	public static final ImageDescriptor ATTACHMENT = CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/attachment16.png");//$NON-NLS-1$
	/** Filter */
	public static final ImageDescriptor FILTER = CacheImage.getInstance().getImageDescriptor(Activator.PLUGIN_ID, "icons/etool16/filter.gif"); //$NON-NLS-1$
	/** Minimum width of Dialogs in Dialog Units */
	public static final int MIN_DIALOG_WIDTH_DLU = 320;

	/** An enumeration of Open Modes when opening something in the All View */
	public enum ViewOpenMode {
		/** Force to Activate the Email */
		FORCE_ACTIVATE,

		/** Ignore Email if already opened */
		IGNORE_ALREADY_OPENED,

		/** Ignore Tab reuse for Emails */
		IGNORE_REUSE;
	}

	/** Supported Feedview Layouts */
	public enum Layout {
		CLASSIC(Messages.FvnUI_CLASSIC_LAYOUT), VERTICAL(Messages.FvnUI_VERTICAL_LAYOUT);

		private final String _name;

		private Layout(String $name) {
			_name = $name;
		}

		/**
		 * @return the name of this layout option.
		 */
		public String getName() {
			return _name;
		}
	}

	/**
	 * 
	 * @param $key
	 * @param $style
	 * @return
	 */
	public static Font getThemeFont(String $key, int $style) {
		FontRegistry __fontRegistry = PlatformUI.getWorkbench().getThemeManager().getCurrentTheme().getFontRegistry();
		if (__fontRegistry != null) {
			if ($style == SWT.NORMAL)
				return __fontRegistry.get($key);
			else if (($style & SWT.BOLD) != 0)
				return __fontRegistry.getBold($key);
			else if (($style & SWT.ITALIC) != 0)
				return __fontRegistry.getItalic($key);
		}

		return getFont($key);
	}

	/**
	 * 
	 * @param $key
	 * @return
	 */
	public static Font getFont(String $key) {
		return JFaceResources.getFontRegistry().get($key);
	}

	/**
	 * @param $manager
	 * @param $descriptor
	 * @return Image
	 */
	public static Image getImage(ResourceManager $manager, ImageDescriptor $descriptor) {
		try {
			return $manager.createImage($descriptor);
		} catch (DeviceResourceException $ex) {
			return getDefaultImage($manager);
		} catch (SWTException $ex) {
			return getDefaultImage($manager);
		}
	}

	/* Returns the default Image or NULL if unable to create */
	private static Image getDefaultImage(ResourceManager $manager) {
		try {
			return $manager.createImage(UNKNOWN);
		} catch (DeviceResourceException e1) {
			return null; // Should not happen
		}
	}

	/**
	 * Safely disposes the provided {@link Menu}.
	 * 
	 * @param $menu
	 *            the {@link Menu} to dispose.
	 */
	public static void safeDispose(Menu $menu) {
		try {
			if ($menu != null) {
				$menu.dispose();
			}
		} catch (NegativeArraySizeException $ex) {
			/* Bug in SWT that we can safely ignore */
		}
	}

	/**
	 * @return the Size of the {@link Monitor} if only a single monitor is used
	 *         or <code>null</code> if none.
	 */
	public static Point getFirstMonitorSize() {
		Display __display = Display.getDefault();
		if (__display != null) {
			Monitor[] ___monitors = __display.getMonitors();
			if (___monitors.length == 1) {
				Rectangle ____clientArea = ___monitors[0].getClientArea();
				return new Point(____clientArea.width, ____clientArea.height);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param $action
	 * @param $manager
	 */
	public static void positionDropDownMenu(Action $action, ToolBarManager $manager) {
		Menu _menu_2 = $action.getMenuCreator().getMenu($manager.getControl());
		if (_menu_2 != null) {
			/* Adjust Location */
			IContributionItem _contributionItem_3 = $manager.find($action.getId());
			if (_contributionItem_3 != null && _contributionItem_3 instanceof ActionContributionItem) {
				Widget _widget_4 = ((ActionContributionItem) _contributionItem_3).getWidget();
				if (_widget_4 != null && _widget_4 instanceof ToolItem) {
					ToolItem _item_5 = (ToolItem) _widget_4;
					Rectangle _rect_5 = _item_5.getBounds();
					Point _pt_5 = new Point(_rect_5.x, _rect_5.y + _rect_5.height);
					_pt_5 = $manager.getControl().toDisplay(_pt_5);
					if (Application.IS_MAC)
						_pt_5.y += 5;
					_menu_2.setLocation(_pt_5.x, _pt_5.y);
				}
			}
			/* Set Visible */
			_menu_2.setVisible(true);
		}
	}

	/**
	 * 
	 * @param $page
	 * @param $selection
	 */
	public static void openInEmailView(IWorkbenchPage $page, IStructuredSelection $selection) {
		openInEmailView($page, $selection, false);
	}

	/**
	 * 
	 * @param $page
	 * @param $selection
	 * @param $forceActivate
	 */
	public static void openInEmailView(IWorkbenchPage $page, IStructuredSelection $selection, boolean $forceActivate) {
		openInEmailView($page, $selection, $forceActivate, false);
	}

	/**
	 * 
	 * @param $page
	 * @param $selection
	 * @param $forceActivate
	 * @param $ignoreAlreadyOpened
	 */
	public static void openInEmailView(IWorkbenchPage $page, IStructuredSelection $selection, boolean $forceActivate, boolean $ignoreAlreadyOpened) {
		openInEmailView($page, $selection, $forceActivate, $ignoreAlreadyOpened, null);
	}

	/**
	 * 
	 * @param $page
	 * @param $selection
	 * @param $forceActivate
	 * @param $ignoreAlreadyOpened
	 * @param $perform
	 */
	public static void openInEmailView(IWorkbenchPage $page, IStructuredSelection $selection, boolean $forceActivate, boolean $ignoreAlreadyOpened, PerformAfterInputSet $perform) {
		try {
			internalOpenInEmailView($page, $selection, $forceActivate, $ignoreAlreadyOpened, false, $perform);
		} finally {

		}
	}

	/**
	 * 
	 * @return
	 */
	public static IWorkbenchWindow getWindow() {
		/* First try active Window */
		IWorkbenchWindow __activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (__activeWorkbenchWindow != null)
			return __activeWorkbenchWindow;

		/* Finally try any Window */
		IWorkbenchWindow __windows[] = PlatformUI.getWorkbench().getWorkbenchWindows();
		if (__windows.length > 0)
			return __windows[0];

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static IWorkbenchPage getPage() {
		IWorkbenchWindow __window = getWindow();
		return getPage(__window);
	}

	/**
	 * 
	 * @param $window
	 * @return
	 */
	public static IWorkbenchPage getPage(IWorkbenchWindow $window) {
		if ($window != null) {
			/* First try active Page */
			if ($window.getActivePage() != null)
				return $window.getActivePage();

			/* Finally try any Page */
			IWorkbenchPage[] ___pages = $window.getPages();
			if (___pages.length > 0)
				return ___pages[0];
		}
		return null;
	}

	public static EmailView getFirstActiveEmailView() {
		IWorkbenchPage __page = getPage();
		if (__page != null) {

			/* First try current active editor */
			IEditorPart ___activeEditor = __page.getActiveEditor();
			if (___activeEditor instanceof EmailView)
				return (EmailView) ___activeEditor;

			/* Then navigate through all from first to last */
			IEditorReference[] ___editorReferences = __page.getEditorReferences();
			for (IEditorReference ____editorReference : ___editorReferences) {
				if (EmailView.ID.equals(____editorReference.getId()))
					return (EmailView) ____editorReference.getEditor(true);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param $page
	 * @param $selection
	 * @param $forceActivate
	 * @param $ignoreAlreadyOpened
	 * @param $ignoreReuse
	 * @param $perform
	 */
	private static void internalOpenInEmailView(IWorkbenchPage $page, IStructuredSelection $selection, boolean $forceActivate, boolean $ignoreAlreadyOpened, boolean $ignoreReuse, PerformAfterInputSet $perform) {
		List<?> __list = $selection.toList();
		boolean __activateEditor = $forceActivate || OpenStrategy.activateOnOpen();
		int __openedEditors = 0;
		int __maxOpenEditors = EditorUtils.getOpenEditorLimit();
		boolean __reuseEmailView = !$ignoreReuse;

		/* Open Editors for the given Selection */
		for (int ___i = 0; ___i < __list.size() && __openedEditors < __maxOpenEditors; ___i++) {
			Object ____object = __list.get(___i);

			if (____object instanceof IEmail) {
				IEmail _____email = (IEmail) ____object;
				EmailViewInput ____input = new EmailViewInput(_____email, $perform);

				if (__reuseEmailView) {

					/* Email could be already open in editor (avoid duplicates) */
					IEditorPart _____existingEditor = $page.findEditor(____input);
					if (_____existingEditor != null) {
						if (__activateEditor) {
							$page.activate(_____existingEditor);
						} else {
							$page.bringToTop(_____existingEditor);
						}

						if ($perform != null && _____existingEditor instanceof EmailView) {
						}

						break;
					}

					/* Otherwise replace the input in the first active feed view */
					/*
					 * EmailView _____activeFeedView =
					 * FvnUI.getFirstActiveEmailView(); if (_____activeFeedView
					 * != null) { _____activeFeedView.setInput(____input); if
					 * (__activateEditor) $page.activate(_____activeFeedView);
					 * else $page.bringToTop(_____activeFeedView); break; }
					 */
				}

				/* Otherwise simply open */
				try {
					boolean _____explicitPerform = false;
					IEditorPart _____existingEditor = null;
					if ($perform != null) {
						_____existingEditor = $page.findEditor(____input);
						_____explicitPerform = (_____existingEditor != null);
					}

					if (!$ignoreAlreadyOpened || $page.findEditor(____input) == null) {
						$page.openEditor(____input, EmailView.ID, __activateEditor);
					}
					__openedEditors++;

					/* Pass in Perform Code */
					if (_____explicitPerform && _____existingEditor instanceof EmailView) {

					}

					/*
					 * Break loop if we reuse feed views (thus can only display
					 * a single feed)
					 */
					if (__reuseEmailView)
						break;
				} catch (PartInitException $ex) {
					_logger.warning($ex.getMessage());
				}
			}
		}
	}

	/**
	 * 
	 * @param $page
	 * @param $selection
	 * @param $openModes
	 */
	public static void openInEmailView(IWorkbenchPage $page, IStructuredSelection $selection, EnumSet<ViewOpenMode> $openModes) {
		boolean __forceActivate = $openModes.contains(ViewOpenMode.FORCE_ACTIVATE);
		boolean __ignoreAlreadyOpened = $openModes.contains(ViewOpenMode.IGNORE_ALREADY_OPENED);
		boolean __ignoreReuse = $openModes.contains(ViewOpenMode.IGNORE_REUSE);

		try {
			internalOpenInEmailView($page, $selection, __forceActivate, __ignoreAlreadyOpened, __ignoreReuse, null);
		} finally {
		}
	}

	/**
	 * 
	 * @param $input
	 */
	public static void updateWindowTitle(IEntity $input) {
		if ($input != null)
			updateWindowTitle($input.getName());
	}

	/**
	 * 
	 * @param $title
	 */
	public static void updateWindowTitle(String $title) {
		IWorkbenchWindow __window = getWindow();
		if (__window != null) {
			String ___appTitle = "FileManager"; //$NON-NLS-1$
			if (StringUtil.isSet($title))
				$title = NLS.bind(Messages.FvnUI_TITLE, ___appTitle, $title);
			else
				$title = ___appTitle;

			String ___shellText = __window.getShell().getText();
			if (___shellText == null || !___shellText.equals($title))
				__window.getShell().setText($title);
		}
	}

	/**
	 * 
	 * @param $manager
	 * @param $rgb
	 * @return
	 */
	public static Color getColor(ResourceManager $manager, RGB $rgb) {
		try {
			return $manager.createColor($rgb);
		} catch (DeviceResourceException $ex) {
			return $manager.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}
	}

	/**
	 * 
	 * @param $event
	 * @param $scrollable
	 * @param $gc
	 * @param $area
	 */
	public static void codExpandRegion(Event $event, Scrollable $scrollable, GC $gc, Rectangle $area) {
		int __columnCount;
		if ($scrollable instanceof Table)
			__columnCount = ((Table) $scrollable).getColumnCount();
		else
			__columnCount = ((Tree) $scrollable).getColumnCount();

		if ($event.index == __columnCount - 1 || __columnCount == 0) {
			int ___width = $area.x + $area.width - $event.x;
			if (___width > 0) {
				Region ____region = new Region();
				$gc.getClipping(____region);
				____region.add($event.x, $event.y, ___width, $event.height);
				$gc.setClipping(____region);
				____region.dispose();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isHighContrast() {
		return false;
	}

	public static boolean needsCancelControl() {
		if (Application.IS_WINDOWS)
			return true; // Windows does not support a native cancel button
		// in text fields

		if (Application.IS_MAC)
			return false; // Mac supports native cancel button in text fields

		return SWT.getVersion() < 3700; // Some Linux distros support it with
		// recent SWT version
	}

	public static void toggleMails() {
		IWorkbenchPage __page = FvnUI.getPage();
		if (__page != null) {
			IViewPart ___explorerView = __page.findView(FileNavigatorView.ID);

			/* Hide Mails */
			if (___explorerView != null)
				__page.hideView(___explorerView);

			/* Show Mails */
			else {
				try {
					__page.showView(FileNavigatorView.ID);
				} catch (PartInitException $ex) {
					_logger.warning($ex.getMessage());
				}
			}
		}
	}

	public static void showHistoryView() {
		IWorkbenchPage __page = FvnUI.getPage();
		if (__page != null) {
			IViewPart __view_3 = __page.findView(HistoryView.ID);

			if (__view_3 == null) {
				try {
					__page.showView(HistoryView.ID);
				} catch (PartInitException $ex) {
					_logger.warning($ex.getMessage());
				}
			}
		}
	}

	public static Shell getActiveShell() {
		IWorkbenchWindow __window = getWindow();
		if (__window != null)
			return __window.getShell();
		return null;
	}

	/**
	 * Switch between full-screen and normal screen.
	 */
	public static void toggleFullScreen() {
		Shell __shell = FvnUI.getActiveShell();
		if (__shell != null) {
			__shell.setFullScreen(!__shell.getFullScreen());

			/* Shell got restored */
			if (!__shell.getFullScreen()) {
				__shell.layout();// Need to layout to avoid screen cheese
			}
			/* Shell got fullscreen */
			else {
				ApplicationWorkbenchWindowAdvisor ___configurer = ApplicationWorkbenchAdvisor._primaryApplicationWorkbenchWindowAdvisor;
				___configurer.setStatusVisible(false, true);
			}
		}
	}
}
