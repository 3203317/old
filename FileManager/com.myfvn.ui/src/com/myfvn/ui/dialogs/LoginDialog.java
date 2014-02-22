package com.myfvn.ui.dialogs;

import java.util.List;

import net.foreworld.utils.URLUtil;
import net.foreworld.utils.rcp.core.persist.IUser;
import net.foreworld.utils.rcp.core.persist.IWebSite;
import net.foreworld.utils.rcp.core.persist.events.UserAdapter;
import net.foreworld.utils.rcp.core.persist.events.UserEvent;
import net.foreworld.utils.rcp.dialogs.CustomTitleAreaDialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.myfvn.core.internal.ApplicationServer;
import com.myfvn.core.internal.persist.User;
import com.myfvn.core.persist.event.UserEventManager;
import com.myfvn.core.persist.reference.WebSiteReference;
import com.myfvn.ui.ApplicationContext;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class LoginDialog extends CustomTitleAreaDialog {

	private UserAdapter _userAdapter = null;

	public LoginDialog(Shell $parentShell) {
		super($parentShell);

		this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);

		this.setTitleAreaColor(new RGB(255, 255, 255));

		this.registerListeners();
	}

	protected Control createContents(Composite $parent) {
		Control __control = super.createContents($parent);

		Button __button = null;

		__button = this.getButton(IDialogConstants.CANCEL_ID);
		if (__button != null)
			__button.setText(Messages.LoginDialog_CANCEL_TEXT);
		__button = this.getButton(IDialogConstants.OK_ID);
		if (__button != null)
			__button.setText(Messages.LoginDialog_OK_TEXT);

		return __control;
	}

	public void create() {
		super.create();
		// 设置壳大小
		this.getShell().setSize(370, 250);
		// 设置壳居中
		Rectangle __clientArea = this.getShell().getDisplay().getClientArea();
		// 壳范围
		Rectangle __bounds = this.getShell().getBounds();
		//
		this.getShell().setLocation((__clientArea.width - __bounds.width) / 2, (__clientArea.height - __bounds.height) / 2);
		this.getShell().setText(Messages.LoginDialog_WELCOME);
		this.setMessage(Messages.LoginDialog_INPUT);
		this.setTitle(Messages.LoginDialog_TITLE);
	}

	private Combo _combo_username;
	private Text _txt_password;
	private Button _btn_remember_password;
	private Button _btn_auto_login;

	/* Datas */
	private List<IUser> _users = null;

	protected Control createDialogArea(Composite $parent) {
		Composite composite = new Composite($parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);

		// 创建grid
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		gridLayout.marginWidth = 15;
		gridLayout.marginHeight = 10;
		gridLayout.makeColumnsEqualWidth = false;
		composite.setLayout(gridLayout);
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.LoginDialog_USERNAME);
		label.setLayoutData(new GridData());

		this._combo_username = new Combo(composite, SWT.NONE);
		this._combo_username.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		this._combo_username.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent $event) {
				updateDisplayWidget();
			}
		});

		// 注册帐号
		final Link eclipseorgLink = new Link(composite, SWT.NONE);
		eclipseorgLink.setToolTipText(Messages.LoginDialog_REGISTER);
		eclipseorgLink.setText("<a href=\"http://www.foreworld.net\">\u6ce8\u518c\u5e10\u53f7</a>");//$NON-NLS-1$
		eclipseorgLink.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent $evt) {
				URLUtil.openUrl("http://www.foreworld.net/register.asp");//$NON-NLS-1$
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText(Messages.LoginDialog_PASSWORD);
		label.setLayoutData(new GridData());

		this._txt_password = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		this._txt_password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		// 找回密码
		final Link eclipseorgLink_1 = new Link(composite, SWT.NONE);
		eclipseorgLink_1.setText("<a href=\"http://www.foreworld.net\">\u627e\u56de\u5bc6\u7801</a>");//$NON-NLS-1$
		new Label(composite, SWT.NONE);

		_btn_remember_password = new Button(composite, SWT.CHECK);
		_btn_remember_password.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		_btn_remember_password.setText(Messages.LoginDialog_REMEMBER_PASSWORD);

		_btn_auto_login = new Button(composite, SWT.CHECK);
		_btn_auto_login.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		_btn_auto_login.setText(Messages.LoginDialog_AUTO_LOGIN);
		new Label(composite, SWT.NONE);

		fillData();

		return $parent;
	}

	private void super_okPressed() {
		super.okPressed();
	}

	protected void okPressed() {
		final IUser __user = new User();
		__user.setName(this._combo_username.getText().trim());
		__user.setIsautologin(this._btn_auto_login.getSelection() ? 1 : 0);
		__user.setIsrememberpassword(this._btn_remember_password.getSelection() ? 1 : 0);
		__user.setPassword(this._txt_password.getText().trim());
		__user.setLPassword(this._txt_password.getText().trim());

		BusyIndicator.showWhile(this.getShell().getDisplay(), new Runnable() {
			public void run() {
				__user.login();
			}
		});
	}

	private void registerListeners() {
		this._userAdapter = new UserAdapter() {

			public void loginFail_Network_Error(UserEvent $event) {
				boolean __result = MessageDialog.openConfirm(getShell(), Messages.LoginDialog_FAILURE, $event.getMessage());

				if (__result) {
					ApplicationServer.getDefault().setLoginMode(ApplicationServer.LoginMode.LOCAL);
				}
			}

			public void loginFail_Network(UserEvent $event) {
				MessageDialog.openError(getShell(), Messages.LoginDialog_FAILURE, $event.getMessage());
				URLUtil.openUrl("http://www.foreworld.net/register.asp");
			}

			public void loginFail(UserEvent $event) {
				MessageDialog.openError(getShell(), Messages.LoginDialog_FAILURE, $event.getMessage());
			}

			public void loginSuccess(UserEvent $event) {
				ApplicationContext.getDefault().setUser_uuid($event.getUser_uuid());
				super_okPressed();
			}
		};
		UserEventManager.getDefault().addUserEventListener(this._userAdapter);
	}

	private void unregisterListeners() {
		UserEventManager.getDefault().removeUserEventListener(this._userAdapter);
	}

	public void finalize() {
		this.unregisterListeners();
	}

	private void updateDisplayWidget() {
		String __key = this._combo_username.getText();
		IUser __user = (IUser) this._combo_username.getData(__key);

		this._combo_username.setText(__user.getName());
		this._btn_auto_login.setSelection(__user.getIsautologin() == 1);
		if (__user.getIsrememberpassword() == 1) {
			this._txt_password.setText(__user.getLPassword());
			this._btn_remember_password.setSelection(true);
		} else {
			this._txt_password.setText("");
			this._btn_remember_password.setSelection(false);
		}
	}

	private void fillData() {
		IWebSite __webSite = new WebSiteReference().resolve();
		this._users = __webSite.getUsers();

		for (IUser ___user : this._users) {
			this._combo_username.add(___user.getName());
			this._combo_username.setData(___user.getName(), ___user);
		}

		IUser __user = __webSite.getLastLoginUser();

		if (__user != null) {
			this._combo_username.setText(__user.getName());
			this._btn_auto_login.setSelection(__user.getIsautologin() == 1);
			if (__user.getIsrememberpassword() == 1) {
				this._txt_password.setText(__user.getLPassword());
				this._btn_remember_password.setSelection(true);
			}
		}
	}

}
