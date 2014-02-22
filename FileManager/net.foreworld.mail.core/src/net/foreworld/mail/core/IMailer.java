package net.foreworld.mail.core;

import net.foreworld.java.mail.mail_mail.Mail_mail;
import net.foreworld.java.mail.mail_mailaccount.Mail_mailaccount;


public interface IMailer {
	
	void setMail_mailaccount(Mail_mailaccount $mail_mailaccount);
	
	void setMail_mail(Mail_mail $mail_mail);
	
	int sendMail();
	
	Mail_mail readMail(Mail_mail $mail_mail);
	
}
