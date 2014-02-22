package net.foreworld.rss2.dialogs;

import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.dialogs.wizards.AddChannelWizard;
import net.foreworld.rss2.dialogs.wizards.AddChannelWizardPage1;
import net.foreworld.rss2.dialogs.wizards.AddChannelWizardPage2;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.utils.StringUtils;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 11, 2008 4:20:35 PM
 */
public class AddChannelWizardDialog extends WizardDialog {
	
	private AddChannelWizardPage1 page1;
	private AddChannelWizardPage2 page2;
	private Job nextJob;
	
	public AddChannelWizardDialog(Shell parentShell) {
		super(parentShell,new AddChannelWizard());
		this.setHelpAvailable(true);
	}
	/**
	 * 创建dialog
	 */
	public void create(){
		super.create();
		//设置壳大小
		this.getShell().setSize(480, 320);
		//设置壳居中
		Rectangle screenSize = this.getShell().getDisplay().getClientArea();
		Rectangle frameSize = this.getShell().getBounds();
		this.getShell().setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		this.setTitle("添加频道");
        this.setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);
        this.setTitleAreaColor(new RGB(255, 255, 255));
	}
	/**
	 * 按钮汉化
	 */
	protected Control createContents(Composite parent) {
		Control c = super.createContents(parent);
		this.getButton(IDialogConstants.CANCEL_ID).setText("取消(&C)");
		this.getButton(IDialogConstants.FINISH_ID).setText("完成(&F)");
		this.getButton(IDialogConstants.NEXT_ID).setText("下一步(&N) >");
		this.getButton(IDialogConstants.BACK_ID).setText("< 上一步(&B)");
		return c;
	}
	/**
	 * 后退按键
	 */
	public void backPressed(){
		IWizardPage page = super.getCurrentPage();
		if(page instanceof AddChannelWizardPage2){
			super.backPressed();
		}
	}
	/**
	 * 前进按键
	 */
	public void nextPressed(){
		IWizardPage page = this.getCurrentPage();
		if(page instanceof AddChannelWizardPage1){
			this.page1 = (AddChannelWizardPage1)page;
			//判断
			if(StringUtils.getURLString(this.page1.getXmlUrl_Text().getText().trim()).equals("")){
				this.page1.setErrorMessage("不是有效的URL地址！");
				return;
			}
			else{
				this.page1.setErrorMessage(null);
			}
//			if(ChannelNavigator.checkXmlUrlIsExist(this.page1.getXmlUrl_Text().getText().trim())){
//			this.page1.setErrorMessage("此地址已经存在，请重新输入！");
//			return;	
//		}
			ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
			if(RssXml2.checkElementIsExist(channelNavigatorView.getDoc(), "//outline[@xmlUrl='"+ this.page1.getXmlUrl_Text().getText().trim() +"']")){
				this.page1.setErrorMessage("此地址已经存在，请重新输入！");
				return;					
			}
			//线程
			this.nextJob = new Job(""){
				protected IStatus run(IProgressMonitor arg0) {
					page1.getShell().getDisplay().asyncExec(new Runnable(){
						public void run() {
							page1.getProgressBar().setVisible(true);
							page1.getProgressBar().setToolTipText("正在下载...");
							page1.getMsg_Label().setText(page1.getProgressBar().getToolTipText());
							//获取新的频道
//							page1.setNewChannel(XmlUtil.download(page1.getXmlUrl_Text().getText().trim()));
							page1.setNewChannel(RssXml2.getDocumentByURL(page1.getXmlUrl_Text().getText().trim()));
							if(page1.getNewChannel() == null){
								nextJob = null;
								page1.getMsg_Label().setText("下载失败！请重试。");
								page1.getProgressBar().setVisible(false);
								return;
							}
							page1.getProgressBar().setSelection(1);
							
							if(!page1.getNewChannel().getRootElement().getName().trim().equals("rss")){
								nextJob = null;
								page1.getMsg_Label().setText("不是标准的Rss2.0文档！");
								page1.getProgressBar().setVisible(false);
								return;
							}
							if(page1.getNewChannel().getRootElement().attribute("version") == null){
								nextJob = null;
								page1.getMsg_Label().setText("不是标准的Rss2.0文档！");
								page1.getProgressBar().setVisible(false);
								return;
							}
							if(!page1.getNewChannel().getRootElement().attributeValue("version").trim().equals("2.0")){
								nextJob = null;
								page1.getMsg_Label().setText("不是标准的Rss2.0文档！");
								page1.getProgressBar().setVisible(false);
								return;
							}
							page1.getProgressBar().setSelection(page1.getProgressBar().getSelection() + 1);
							
							page1.getMsg_Label().setText("");
							page1.getProgressBar().setVisible(false);
							//进入下一步
							superNextPressed();
							nextJob = null;
							
//							nextJob.schedule(10000);
						}
					});
					return Status.OK_STATUS;
				}
			};
			this.nextJob.setPriority(Job.SHORT);
			this.nextJob.schedule();
		}
//		super.nextPressed();
	}
	
	private void superNextPressed(){
		super.nextPressed();
		this.page2 = (AddChannelWizardPage2)this.getCurrentPage();
		this.page2.getChannelName_Text().setText(this.page1.getNewChannel().getRootElement().element("channel").element("title").getText().trim());
	}
}
