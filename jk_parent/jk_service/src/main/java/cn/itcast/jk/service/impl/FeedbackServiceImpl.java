package cn.itcast.jk.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.struts2.ServletActionContext;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.FeedbackService;
import cn.itcast.jk.utils.SysConstant;

public class FeedbackServiceImpl implements FeedbackService {

	private MailMessage mailMessage;
	private MailSender mailSender;

	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void send(String subject, String text) throws Exception {
		User user = (User) ServletActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", true);
		Session session = Session.getInstance(props);
		MimeMessage message = new MimeMessage(session);
		Address fromAddr = new InternetAddress("itheima14@163.com", "意见反馈");
		message.setFrom(fromAddr);

		// 将意见反馈给领导
		User manager = user.getUserInfo().getManager();
		String email = manager.getUserInfo().getEmail();

		Address toAddr = new InternetAddress(email);
		message.setRecipient(RecipientType.TO, toAddr);
		message.setSubject("来自" + user.getUserName() + "的反馈：" + subject);
		message.setText(text);
		Transport transport = session.getTransport("smtp");
		transport.connect("itheima14@163.com", "iamsorry123");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

	}

}
