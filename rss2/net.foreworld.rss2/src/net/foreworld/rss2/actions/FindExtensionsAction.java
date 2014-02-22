package net.foreworld.rss2.actions;

import java.net.MalformedURLException;
import java.net.URL;

import net.foreworld.rss2.Messages;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.update.search.BackLevelFilter;
import org.eclipse.update.search.EnvironmentFilter;
import org.eclipse.update.search.UpdateSearchRequest;
import org.eclipse.update.search.UpdateSearchScope;
import org.eclipse.update.ui.UpdateJob;
import org.eclipse.update.ui.UpdateManagerUI;

public class FindExtensionsAction extends BaseAction {
	
	private static final String RSS2_CATEGORY = "RSS2 Application";

	public static final String ID = FindExtensionsAction.class.getName();
	private IWorkbenchWindow window;
	
	public FindExtensionsAction(IWorkbenchWindow window) {
		super(window);
		this.window = window;
	}
	
	public void run(){
		BusyIndicator.showWhile(this.window.getShell().getDisplay(), new Runnable(){
			public void run() {
				UpdateJob job = new UpdateJob(getToolTipText(),getSearchRequest());
		        job.setUser(true);
		        job.setPriority(Job.INTERACTIVE);
				UpdateManagerUI.openInstaller(window.getShell(), job);
				//如果加上这一段会有两个进度框
//				PlatformUI.getWorkbench().getProgressService().showInDialog(window.getShell(),job);
			}
		});
	}

	UpdateSearchRequest getSearchRequest(){
		UpdateSearchScope scope = new UpdateSearchScope();
		try {
			URL url = new URL(Messages.FOREWORLD_UPDATE_SITE);
			scope.addSearchSite("foreworld.net", url, new String[]{RSS2_CATEGORY});
		} 
		catch (MalformedURLException e) {
		}
	
	    UpdateSearchRequest result = new UpdateSearchRequest(UpdateSearchRequest.createDefaultSiteSearchCategory(), scope);
	    result.addFilter(new BackLevelFilter());
	    result.addFilter(new EnvironmentFilter());
	    return result;
	}

}
