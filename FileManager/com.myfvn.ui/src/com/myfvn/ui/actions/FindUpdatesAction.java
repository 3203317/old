package com.myfvn.ui.actions;

import java.net.MalformedURLException;
import java.net.URL;

import net.foreworld.utils.StringUtil;
import net.foreworld.utils.rcp.JobRunner;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.update.operations.IInstallFeatureOperation;
import org.eclipse.update.search.UpdateSearchScope;
import org.eclipse.update.ui.UpdateJob;

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class FindUpdatesAction extends Action implements
		IWorkbenchWindowActionDelegate {

	final static String UPDATE_SITE = "http://www.myfvn.com/updates";//$NON-NLS-1$

	private Shell _shell;

	private final boolean _userInitiated;

	public FindUpdatesAction() {
		this(true);
	}

	public FindUpdatesAction(boolean $userInitiated) {
		this._userInitiated = $userInitiated;
	}

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction $action) {

		try {
			// Scope to myfvn Updates Only
			UpdateSearchScope __scope = new UpdateSearchScope();
			__scope.addSearchSite("www.myfvn.com", new URL(UPDATE_SITE), null);//$NON-NLS-1$
			__scope.setFeatureProvidedSitesEnabled(false);
			
			
			// Run in Update Job
			final UpdateJob __ujob = new UpdateJob(Messages.FindUpdatesAction_UPDATE_SEARCH, true, false);
			__ujob.getSearchRequest().setScope(__scope);
			__ujob.addJobChangeListener(new JobChangeAdapter(){
				
				public void done(IJobChangeEvent $evt){
					JobRunner.runInUIThread(_shell, new Runnable(){

						public void run() {
							if(_shell != null && _shell.isDisposed()){
								return;
							}
							
							if(__ujob.getState() == 1){
								handleUpdates(__ujob.getUpdates());
							}
							else{
								handleError(__ujob.getStatus());
							}
						}
					});
				}
			});
			
			if(this._userInitiated){
				__ujob.setUser(true);
				__ujob.setPriority(Job.INTERACTIVE);
			}
			else{
				__ujob.setUser(false);
				__ujob.setSystem(true);
			}
			
			// Schedule
			__ujob.schedule();
			
			
		} catch (MalformedURLException $ex) {
			$ex.printStackTrace();
		}
	}
	
	public void handleUpdates(final IInstallFeatureOperation[] $updates){
		
	}
	
	private void handleError(IStatus $status){
		if(this._userInitiated){
			String __msg = Messages.FindUpdatesAction_WARNING_SEARCH_FAILED;
			
			if(StringUtil.isSet($status.getMessage())){
				__msg += "\n\n"+ NLS.bind(Messages.FindUpdatesAction_REASON, $status.getMessage());//$NON-NLS-1$
			}
			
			MessageDialog.openWarning(this._shell, Messages.FindUpdatesAction_CHECK_UPDATES, __msg);
		}
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow $window) {
		this._shell = $window.getShell();
	}
}