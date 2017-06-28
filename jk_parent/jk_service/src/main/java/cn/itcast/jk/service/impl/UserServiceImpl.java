package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class UserServiceImpl implements UserService {
	// 注入dao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	private MailMessage mailMessage;
	private MailSender mailSender;

	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public List<User> find(String hql, Class<User> entityClass, Object[] params) {

		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {

		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(User entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			String uuid = UUID.randomUUID().toString();
			entity.setId(uuid);
			entity.getUserInfo().setId(uuid);
			mailMessage.setFrom("15222166972@163.com");
			mailMessage.setTo(entity.getUserInfo().getEmail());
			mailMessage.setSubject("入职通知!!");
			mailMessage.setText("您已经被我们公司所录取，您的用户名为" + entity.getUserName() + "初始密码为:1234");
			// 发送邮件开启线程
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					mailSender.send((SimpleMailMessage) mailMessage);

				}
			});
			thread.start();

		}
		baseDao.saveOrUpdate(entity);

	}

	public void Update(User entity) {

	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Class<User> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);

	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		for (int i = 0; i < ids.length; i++) {
			deleteById(entityClass, ids[i]);
		}

	}

	@Override
	public void deleteDept(User d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMail(String feedback, User user) throws Exception {
		// TODO Auto-generated method stub
		// 此处反馈与意见反馈不同，只需要系统邮箱，不需要用户邮箱，所以，此处固定邮箱

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", true);
		Session session = Session.getInstance(props);
		MimeMessage message = new MimeMessage(session);
		Address fromAddr = new InternetAddress("itheima14@163.com", "用户反馈");
		message.setFrom(fromAddr);
		Address toAddr = new InternetAddress("itheima14@163.com");
		message.setRecipient(RecipientType.TO, toAddr);
		message.setSubject("来自" + user.getUserName() + "的反馈");
		message.setText(feedback);
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", "itheima14", "iamsorry123");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

		// String nick = "";
		// try {
		// nick = javax.mail.internet.MimeUtility.encodeText("我的昵称");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// Thread th = new Thread(new Runnable() {
		//
		// public void run() {
		// // 发送邮件
		// mailSender.send((SimpleMailMessage) mailMessage);
		// }
		// });
		// th.start();
	}

}
