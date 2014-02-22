package com.myfvn.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.myfvn.ui.perspectives.Perspective;

/**
 * This workbench advisor creates the window advisor, and specifies the
 * perspective id for the initial window.
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = Perspective.class.getName();

	/** Keep a static reference to the primary Workbench Window Advisor */
	public static ApplicationWorkbenchWindowAdvisor _primaryApplicationWorkbenchWindowAdvisor;

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer $configurer) {
		ApplicationWorkbenchWindowAdvisor __advisor = new ApplicationWorkbenchWindowAdvisor($configurer);

		if (_primaryApplicationWorkbenchWindowAdvisor == null) {
			_primaryApplicationWorkbenchWindowAdvisor = __advisor;
		}

		return _primaryApplicationWorkbenchWindowAdvisor;
	}

	/* Provide access to the primary WorkbenchWindowAdvisor */
	ApplicationWorkbenchWindowAdvisor getPrimaryWorkbenchWindowAdvisor() {
		return _primaryApplicationWorkbenchWindowAdvisor;
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	public void initialize(IWorkbenchConfigurer $configurer) {
		// Saving Window Location and Size 选择true将保存
		super.initialize($configurer);
		$configurer.setSaveAndRestore(true);
		//
		IPreferenceStore __preferenceStore = PlatformUI.getPreferenceStore();
		//
		__preferenceStore.setDefault(IWorkbenchPreferenceConstants.SHOW_MEMORY_MONITOR, true);
		//
		__preferenceStore.setDefault(IWorkbenchPreferenceConstants.SHOW_SYSTEM_JOBS, true);
		//
		__preferenceStore.putValue(IWorkbenchPreferenceConstants.INITIAL_FAST_VIEW_BAR_LOCATION, IWorkbenchPreferenceConstants.BOTTOM);

		// 取首选项的值
		// System.out.println(Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_FWQDZ));
	}

}
