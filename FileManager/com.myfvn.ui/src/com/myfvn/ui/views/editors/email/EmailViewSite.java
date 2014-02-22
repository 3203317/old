package com.myfvn.ui.views.editors.email;

import org.eclipse.ui.IEditorSite;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class EmailViewSite implements IEmailViewSite {

	private IEditorSite _site;
	private EmailView _emailView;

	public EmailViewSite(EmailView $emailView, IEditorSite $site) {
		this._emailView = $emailView;
		this._site = $site;
	}

	public IEditorSite getEditorSite() {
		return this._site;
	}

}
