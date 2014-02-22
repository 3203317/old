package net.foreworld.mail.core;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMail {

	private String mailTo = null;

	private String mailFrom = null;

	private String smtpHost = null;

	private boolean debug = false;

	private String messageBasePath = null;

	private String subject;

	private String msgContent;

	private Vector attachedFileList;

	private String mailAccount = null;

	private String mailPass = null;

	private String messageContentMimeType = "text/plain; charset=utf-8";

	private String mailbccTo = null;

	private String mailccTo = null;
	
	public SendMail() {

		super();

	}

	private void fillMail(Session session, MimeMessage msg) throws IOException,
			MessagingException {
		String fileName = null;
		Multipart mPart = new MimeMultipart();
		if (mailFrom != null) {
			msg.setFrom(new InternetAddress(mailFrom));
		} else {
			return;
		}
		if (mailTo != null) {
			InternetAddress[] address = InternetAddress.parse(mailTo);
			msg.setRecipients(Message.RecipientType.TO, address);
		} else {
			return;
		}
		if (mailccTo != null) {
			InternetAddress[] ccaddress = InternetAddress.parse(mailccTo);
			msg.setRecipients(Message.RecipientType.CC, ccaddress);
		}
		if (mailbccTo != null) {
			InternetAddress[] bccaddress = InternetAddress.parse(mailbccTo);
			msg.setRecipients(Message.RecipientType.BCC, bccaddress);
		}
		msg.setSubject(subject);
		InternetAddress[] replyAddress = { new InternetAddress(mailFrom) };
		msg.setReplyTo(replyAddress);
		MimeBodyPart mBodyContent = new MimeBodyPart();
		if (msgContent != null)
			mBodyContent.setContent(msgContent, messageContentMimeType);
		else
			mBodyContent.setContent("", messageContentMimeType);
		mPart.addBodyPart(mBodyContent);
		if (attachedFileList != null) {
			for (Enumeration fileList = attachedFileList.elements(); fileList
					.hasMoreElements();) {
				fileName = (String) fileList.nextElement();
				MimeBodyPart mBodyPart = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(fileName);
				mBodyPart.setDataHandler(new DataHandler(fds));
				mBodyPart.setFileName(fileName);
				
//				BASE64Encoder enc = new BASE64Encoder();
//				mBodyPart.setFileName("=?UTF-8?B?"+ enc.encode(fileName.getBytes()) +"?=");
				
				mBodyPart.setFileName(MimeUtility.encodeText(fileName));
				
				mPart.addBodyPart(mBodyPart);
			}
		}
		msg.setContent(mPart);
		msg.setSentDate(new Date());
	}

	public void init() {
	}

	public int sendMail() throws IOException, MessagingException {
		int loopCount;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
		MailAuthenticator auth = new MailAuthenticator();
		Session session = Session.getInstance(props, auth);
		session.setDebug(debug);
		MimeMessage msg = new MimeMessage(session);
		Transport trans = null;
		try {
			fillMail(session, msg);
			trans = session.getTransport("smtp");
			try {
				trans.connect(smtpHost, MailAuthenticator.MAIL_USER,
						MailAuthenticator.MAIL_PASSWORD);// ,
			} catch (AuthenticationFailedException e) {
				e.printStackTrace();
				return 3;
			} catch (MessagingException e) {
				return 3;
			}
			trans.send(msg);
			trans.close();
		} catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
			return 3;
		} finally {
			try {
				if (trans != null && trans.isConnected())
					trans.close();
			} catch (Exception e) {
			}
		}
		return 0;
	}

	public void setAttachedFileList(java.util.Vector filelist) {
		attachedFileList = filelist;
	}

	public void setDebug(boolean debugFlag) {
		debug = debugFlag;
	}
	public void setMailAccount(String strAccount) {
		mailAccount = strAccount;
	}
	public void setMailbccTo(String bccto) {
		mailbccTo = bccto;
	}
	public void setMailccTo(String ccto) {
		mailccTo = ccto;
	}
	public void setMailFrom(String from){
		mailFrom = from;
	}

	public void setMailPass(String strMailPass) {
		mailPass = strMailPass;
	}
	public void setMailTo(String to){
		mailTo = to;
	}

	public void setMessageBasePath(String basePath){
		messageBasePath = basePath;
	}
	public void setMessageContentMimeType(String mimeType){
		messageContentMimeType = mimeType;
	}
	public void setMsgContent(String content){
		msgContent = content;
	}
	public void setSMTPHost(String host){
		smtpHost = host;
	}
	public void setSubject(String sub){
		subject = sub;
	}
	public boolean sendMailToServer(String content,String file){
		try{
//			MailAuthenticator.MAIL_USER = SysConfig.user_name;
//			MailAuthenticator.MAIL_PASSWORD = SysConfig.password;
			
			MailAuthenticator.MAIL_USER = "3203317@qq.com";
			MailAuthenticator.MAIL_PASSWORD = "82314178hxi";
			
			SendMail sm = new SendMail();
//			sm.setSMTPHost(SysConfig.smtp_host);
//			sm.setMailFrom(SysConfig.mail_from);
//			sm.setMailTo(SysConfig.mail_to);
			
			sm.setSMTPHost("smtp.qq.com");
			sm.setMailFrom("3203317@qq.com");
			sm.setMailTo("3203317@qq.com");
			
			sm.setMsgContent(content);
			
			Vector v = new Vector();
			
			String[] files = file.split(";");
			
			for(int i=0,j=files.length; i<j; i++){
				v.add(files[i].toString());
			}
			
//			v.add("e:\\huangxin@foreworld.net.xml");
//			
//			v.add("f:\\msvci70.dll");
//			
//			v.add("d:\\zmtybj.jpg");
			
			if(!"".equals(file)){
				sm.setAttachedFileList(v);
			}
			
			sm.setSubject(content);
//			sm.setSubject(new Date().toString());
			sm.sendMail();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] argv) throws Exception{
		MailAuthenticator.MAIL_USER ="youmail@service.com.cn";
		MailAuthenticator.MAIL_PASSWORD="yourpassword";
		SendMail sm = new SendMail();
		sm.setSMTPHost("pop.163.com");
		sm.setMailFrom("yourmail@service.com");
		sm.setMailTo("njtuslx@163.com");
		sm.setMsgContent("content");
		sm.setSubject("title");
		sm.sendMail();
	}
	
}

class MailAuthenticator extends Authenticator{
	public static String MAIL_USER = "";
	public static String MAIL_PASSWORD = "";
	public MailAuthenticator(){
	}
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(MAIL_USER,
				MAIL_PASSWORD);
	}
}
