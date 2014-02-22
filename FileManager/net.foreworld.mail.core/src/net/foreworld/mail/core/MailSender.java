package net.foreworld.mail.core;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import net.foreworld.java.mail.mail_mail.Mail_mail;
import net.foreworld.java.mail.mail_mailaccount.Mail_mailaccount;
import net.foreworld.java.mail.mail_mailfile.Mail_mailfile;

public abstract class MailSender {
	
	private static String _messageContentMimeType = "text/plain; charset=utf-8";
	
	////////////////////////////////////////////////////
	
	private MimeBodyPart _mimeBodyPart;
	
	private Multipart _multipart;
	
	////////////////////////////////////////////////////
	
	private InternetAddress _from_address;
	
	////////////////////////////////////////////////////
	
	private MimeMessage _mimeMessage;
	
	private Session _session;
	
	////////////////////////////////////////////////////
	
	private class MailAuthenticator extends Authenticator {
		
		public MailAuthenticator() {}
		
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(_mail_mailaccount.getMail_user(), _mail_mailaccount.getMail_pass());
		}
	}

	private MailAuthenticator _mailAuthenticator;
	
	////////////////////////////////////////////////////////////////////////

	private Mail_mailaccount _mail_mailaccount;
	
	private Mail_mail _mail_mail;
	
	private List _mail_mailfiles;
	
	/////////////////////////////////////////////////////////////////////////
	
	private Properties _properties;
	
	/////////////////////////////////////////////////////////////////////////
	
	public Mail_mailaccount getMail_mailaccount() {
		return _mail_mailaccount;
	}

	public void setMail_mailaccount(Mail_mailaccount $mail_mailaccount) {
		this._mail_mailaccount = $mail_mailaccount;
		
		/////////////////////////////
		
		if(this._properties == null){
			this._properties = System.getProperties();
		}

		this._properties.put("mail.smtp.host", this._mail_mailaccount.getMail_smtp_host());
		this._properties.put("mail.smtp.auth", String.valueOf("1".equals(this._mail_mailaccount.getMail_smtp_auth().toString())));
		
		/////////////////////////////
		
		this._mailAuthenticator = new MailAuthenticator();
		
		/////////////////////////////
		
		this._session = Session.getInstance(this._properties, this._mailAuthenticator);
		
		this._session.setDebug(true);

		/////////////////////////////
		
		this._mimeMessage = new MimeMessage(this._session);

		/////////////////////////////
		
		
	}

	public Mail_mail getMail_mail() {
		return _mail_mail;
	}

	public void setMail_mail(Mail_mail $mail_mail) {
		this._mail_mail = $mail_mail;
		
		this._from_address.setAddress(this._mail_mail.getFrom());
		
		try{
			this._mimeMessage.setFrom(this._from_address);
	
			if(this._mail_mail.getTo() != null) this._mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this._mail_mail.getTo()));
			
			if(this._mail_mail.getCc() != null) this._mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(this._mail_mail.getCc()));
			
			if(this._mail_mail.getBcc() != null) this._mimeMessage.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(this._mail_mail.getBcc()));
			
			this._mimeMessage.setSubject(this._mail_mail.getSubject());
			
			InternetAddress[] __replyTo_address = {this._from_address}; 
			
			this._mimeMessage.setReplyTo(__replyTo_address);
			
			
			this._mimeBodyPart.setContent(this._mail_mail.getContent() == null ? "" : this._mail_mail.getContent(), _messageContentMimeType);
			
			/////////////////////////////////////
			
			this._multipart.addBodyPart(this._mimeBodyPart);
	
			/////////////////////////////////////
			
			this._mimeMessage.setContent(this._multipart);
			
			this._mimeMessage.setSentDate(new Date());
		}
		catch(MessagingException $ex){
			$ex.printStackTrace();
		}
	}

	public List getMail_mailfiles() {
		return _mail_mailfiles;
	}

	public void setMail_mailfiles(List _mail_mailfiles) {
		this._mail_mailfiles = _mail_mailfiles;
		
		try{
			for(int __i=0,__j=(this._mail_mailfiles == null ? 0 : this._mail_mailfiles.size()); __i<__j; __i++){
	
				Mail_mailfile ___mail_mailfile = (Mail_mailfile)this._mail_mailfiles.get(__i);
				
				String ___file_name = ___mail_mailfile.getFile_name();
				
				FileDataSource ___fileDataSource = new FileDataSource(___file_name);
				
				MimeBodyPart ___mimeBodyPart = new MimeBodyPart();
				
				___mimeBodyPart.setDataHandler(new DataHandler(___fileDataSource));
				
				___mimeBodyPart.setFileName(MimeUtility.encodeText(___file_name));
				
				this._multipart.addBodyPart(___mimeBodyPart);
			}
		}
		catch(MessagingException $ex){
			$ex.printStackTrace();
		}
		catch(UnsupportedEncodingException $ex){
			$ex.printStackTrace();
		}
	}
	
	public MailSender(){
		
		this._mimeBodyPart = new MimeBodyPart();
		
		this._multipart = new MimeMultipart();
		
		this._from_address = new InternetAddress();
	}
	
	public int sendMail(){
		
		Transport __transport = null;
		
		try {
			
			__transport = this._session.getTransport("smtp");
			
			try {
				__transport.connect(this._mail_mailaccount.getMail_smtp_host(), 
										this._mail_mailaccount.getMail_user(), 
										this._mail_mailaccount.getMail_pass());
			} 
			catch(AuthenticationFailedException $ex){
				$ex.printStackTrace();
				return 3;
			}
			
			Transport.send(this._mimeMessage);
			__transport.close();
		} 
		catch (NoSuchProviderException e) {
			e.printStackTrace();
			return 2;
		}
		catch(MessagingException $ex){
			$ex.printStackTrace();
			return 4;
		}
		finally{
			if(__transport != null && __transport.isConnected()){
				try {
					__transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
	
	
	
}
