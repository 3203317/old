package net.foreworld.mail.core;

import net.foreworld.java.mail.mail_mail.Mail_mail;
import net.foreworld.java.mail.mail_mailaccount.Mail_mailaccount;

public class QQMailerImpl implements IMailer {
	
	private Mail_mail _mail_mail;

	public void setMail_mail(Mail_mail $mail_mail) {
		this._mail_mail = $mail_mail;
	}
	
	private Mail_mailaccount _mail_mailaccount;

	public void setMail_mailaccount(Mail_mailaccount $mail_mailaccount) {
		this._mail_mailaccount = $mail_mailaccount;
	}


	public int sendMail() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Mail_mail readMail(Mail_mail $mail_mail) {
		// TODO Auto-generated method stub
		return null;
	}


}
