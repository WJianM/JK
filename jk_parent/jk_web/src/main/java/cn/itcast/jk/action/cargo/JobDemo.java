package cn.itcast.jk.action.cargo;

import java.util.List;

import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.service.ContractService;

public class JobDemo {
	// 注入邮箱发送器对象
	private MailSender mailSender;
    private MailMessage mailMessage;
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
    
	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	private ContractService contractService;
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	// zhuru
	public void method() {
		System.out.println("调用了!!!!");
	}

	// 发送邮件
	public void sendMail() {
		System.out.println("吊桶了");
       //发件人邮箱
		mailMessage.setFrom("15222166972@163.com");
		//设置收件人
		mailMessage.setTo("919867774@qq.com");
		//设置主题
		mailMessage.setSubject("早点签订合同");
		//查询合同
		String hql="from Contract order by deliveryPeriod desc";
		List<Contract> list = contractService.find(hql, Contract.class, null);
		//设置正文
		mailMessage.setText(list.get(0).getInputBy()+"赶紧去签合同吧!!");
		mailSender.send((SimpleMailMessage) mailMessage);
	}
}
