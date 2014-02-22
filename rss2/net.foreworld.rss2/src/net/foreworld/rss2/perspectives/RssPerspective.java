package net.foreworld.rss2.perspectives;

import net.foreworld.rss2.views.ChannelNavigatorView;
import net.foreworld.rss2.views.DetailListView;
import net.foreworld.rss2.views.FavoritesView;
import net.foreworld.rss2.views.IEView;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 6, 2008 10:20:14 AM
 */
public class RssPerspective implements IPerspectiveFactory {
	public static final String ID = RssPerspective.class.getName();
	
	public void createInitialLayout(IPageLayout arg0) {
		String editorArea = arg0.getEditorArea();
		arg0.setEditorAreaVisible(false);
		arg0.setFixed(false);

		//左边视图
		IFolderLayout layout = arg0.createFolder("left", IPageLayout.LEFT, .25f, editorArea);
		layout.addView(ChannelNavigatorView.ID);
		layout.addPlaceholder(ChannelNavigatorView.ID);
		layout.addView(FavoritesView.ID);
		layout.addPlaceholder(FavoritesView.ID);
		
		//左下视图
		layout = arg0.createFolder("leftbottom", IPageLayout.BOTTOM, .7f, "left");
		layout.addView(IPageLayout.ID_PROP_SHEET);
		layout.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		layout.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
		layout.addView(IPageLayout.ID_PROGRESS_VIEW);
		layout.addPlaceholder(IPageLayout.ID_PROGRESS_VIEW);
		
		
		//右上视图
		layout = arg0.createFolder("righttop", IPageLayout.TOP, .5f, editorArea);
		layout.addView(DetailListView.ID);
		layout.addPlaceholder(DetailListView.ID);
		
		//剩下的视图
		layout = arg0.createFolder("rightbottom", IPageLayout.BOTTOM, .5f, editorArea);
		layout.addView(IEView.ID);
		layout.addPlaceholder(IEView.ID);
		
		
		// 在透视图的打开按钮上增加透视图的快捷方式
		arg0.addPerspectiveShortcut("net.foreworld.rss2.perspective");
		//这一项是加在菜单里的透视图菜单上
		arg0.addNewWizardShortcut("net.foreworld.rss2.perspective");
		
    	arg0.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
    	arg0.addShowViewShortcut(IPageLayout.ID_EDITOR_AREA);
    	arg0.addShowViewShortcut(IPageLayout.ID_PROGRESS_VIEW);
    	arg0.addShowViewShortcut("net.foreworld.rss2.view");
    	arg0.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
    	arg0.addShowViewShortcut(IPageLayout.ID_OUTLINE);
    	arg0.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
    	
    	arg0.addShowInPart(IPageLayout.ID_EDITOR_AREA);
    	arg0.addShowInPart(IPageLayout.ID_PROP_SHEET);
    	arg0.addShowInPart(IPageLayout.ID_BOOKMARKS);
    	arg0.addShowInPart(IPageLayout.ID_OUTLINE);
	}

}
