package cn.itcast.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailDemo {
	@Test
	public void run() throws Exception {
		// 创建properties文件
		Properties pro = new Properties();
		// 主机地址是163
		pro.put("mail.smtp.host", "smtp.163.com");
		// 设置是否需要验证
		pro.put("mail.smtp.auth", true);
		// 获取连接对象
		Session session = Session.getInstance(pro);
		// 创建邮件对象
		MimeMessage mimeMessage = new MimeMessage(session);
		// 设置发件人地址
		Address fromAddress = new InternetAddress("15222166972@163.com");
		mimeMessage.setFrom(fromAddress);
		// 设置收件人
		Address toAddress1 = new InternetAddress("919867774@qq.com");
		Address toAddress2 = new InternetAddress("2307779825@qq.com");
		Address[] toAddress = { toAddress1, toAddress2 };
		RecipientType type;
		mimeMessage.setRecipients(RecipientType.TO, toAddress);
		// 设置内容
		mimeMessage.setSubject("入职通知!!");
		mimeMessage.setText("您被录取了!");
		mimeMessage.saveChanges();
		// 获取发送邮件的对象
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", "15222166972@163.com", "qyw29183435");
		transport.sendMessage(mimeMessage, toAddress);
		transport.close();
	}
     @Test
	public void run1() throws Exception {
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-email.xml");
		// 获取发送对象
		JavaMailSender mailSender = (JavaMailSender) cp.getBean("mailSender");
		//获取邮件对象
		MimeMessage message = mailSender.createMimeMessage();
		//创建邮件的帮助类
	    MimeMessageHelper helper = new MimeMessageHelper(message,true);
	    //设置内容    
	    helper.setSubject("好听!!!");
	    helper.setFrom("15222166972@163.com");
	    helper.setTo("919867774@qq.com");
	    helper.setText("<html><head></head><body><h1>hello!!spring image html mail</h1><a href=http://www.baidu.com>baidu</a><img src='cid:image' /></body></html>",true);;
		//添加图片
	    helper.addInline("image", new File("C:\\Users\\qyw2\\Desktop\\timg.jpg"));
	    helper.addInline("image", new File("C:\\Users\\qyw2\\Desktop\\qyw.mp3"));
	    
	    //设置附件
		helper.addAttachment("附件", new File("C:\\Users\\qyw2\\Desktop\\a.PNG"));
        //发送
		mailSender.send(message);
	}
}
