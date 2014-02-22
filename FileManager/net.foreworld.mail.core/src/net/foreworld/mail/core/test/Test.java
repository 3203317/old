package net.foreworld.mail.core.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import net.foreworld.java.mail.mail_mail.Mail_mail;
import net.foreworld.java.mail.mail_mailaccount.Mail_mailaccount;
import net.foreworld.java.mail.mail_mailfile.Mail_mailfile;
import net.foreworld.mail.core.MailerImpl;
import net.foreworld.mail.core.SendMail;
import sun.misc.BASE64Encoder;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println("email test");
//		
		Test t = new Test();
		
		t.test3();
		
		
//		long __l = new Date().getTime();
//		
//		System.out.println(__l);
//		
//		Mail_mail __mail_mail = new Mail_mail();
//		
//		System.out.println(__mail_mail);
		
	}
	
	public void test5(){

		
		Mail_mailaccount m1 = new Mail_mailaccount();
		
		m1.setMail_user("huangxin@foreworld.net");
		m1.setMail_pass("82314178hxi");
		m1.setMail_smtp_host("smtp.qq.com");
		m1.setMail_smtp_auth(Integer.valueOf(1));
		
		
		MailerImpl ms = new MailerImpl();
		
		ms.setMail_mailaccount(m1);
		
		
		ms.readMail();
		
//		System.out.println(ms.sendMail());
		
	
	}
	
	public void test4() throws IOException{
		
		Mail_mailaccount m1 = new Mail_mailaccount();
		
		m1.setMail_user("3203317@qq.com");
		m1.setMail_pass("82314178hxi");
		m1.setMail_smtp_host("smtp.qq.com");
		m1.setMail_smtp_auth(Integer.valueOf(1));
		
		FileOutputStream fos = new FileOutputStream("foo.ser");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(m1);
		
		System.out.println(fos.getFD());
		
		oos.close();
		
	}
	
	public void test3(){
		
		Mail_mailaccount m1 = new Mail_mailaccount();
		
		m1.setMail_user("huangxin@foreworld.net");
		m1.setMail_pass("82314178hxi");
		m1.setMail_smtp_host("smtp.qq.com");
		m1.setMail_smtp_auth(Integer.valueOf(1));
		
		Mail_mail m2 = new Mail_mail();
		
		m2.setFrom("huangxin@foreworld.net");
		m2.setTo("3203317@qq.com");
		m2.setCc("7357877@qq.com");
		m2.setBcc("7106319@qq.com");
		
		m2.setSubject("haha");
		m2.setContent("gaga");
		
		MailerImpl ms = new MailerImpl();
		
		ms.setMail_mailaccount(m1);
		
		ms.setMail_mail(m2);
		
		Mail_mailfile m3 = new Mail_mailfile();
		
		m3.setFile_name("E:\\game\\Plants vs. Zombies\\particles\\Doom.png");
		

		List m4 = new ArrayList();
		
		m4.add(m3);
		
		
		ms.setMail_mailfiles(m4);
		
		System.out.println(ms.sendMail());
		
	}
	
	public void test(String $msg, String $file){
		SendMail sendMail = new SendMail();
		boolean result = sendMail.sendMailToServer($msg, $file);
		
	}	
	
	public void test(){
		System.out.println("email test");
		
		this.sendEmail();
	}
	
	/**
	 * http://www.iteye.com/topic/348564
	 */
	private void sendEmail() {
		try {
			
			String title = "三等奖连发三道街dsdsd";
			String server163 = "smtp.qq.com";
			String serverSohu = "smtp.qq.com";
			title = new String(title.getBytes("utf-8"));
			Properties props = new Properties();
			props.put("mail.smtp.host", serverSohu);// 指定SMTP服务器
			props.put("mail.smtp.auth", "true");// 指定是否需要SMTP验证

			Session mailSession = Session.getDefaultInstance(props);

			mailSession.setDebug(true);// 是否在控制台显示debug信息
			Message message = new MimeMessage(mailSession);
			
			message.setFrom(new InternetAddress("3203317@qq.com"));// 发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"3203317@qq.com"));// 收件人
			
			BASE64Encoder enc = new BASE64Encoder();
//			message.setSubject("=?UTF-8?B?"+ enc.encode(title.getBytes()) +"?=");// 邮件主题
			
			
			message.setSubject(MimeUtility.encodeText(new String(title.getBytes(),"GB2312"),"GB2312","B"));
			
			String __cph = "";
			try {
				__cph = new String("JLK克里斯蒂解放螺丝钉解放lasjdfl".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
//			message.setDataHandler(new DataHandler(title, "text/html;charset=utf-8"));
			
//			message.setContent(__cph, "text/plain");
//			message.setText("=?UTF-8?B?"+ enc.encode(title.getBytes()) +"?=");// 邮件内容

			message.setContent(title, "text/html;charset=utf-8");
			
			message.saveChanges();
			
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(serverSohu, "3203317", "82314178hxi"); // 这个邮箱可随便使用
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
