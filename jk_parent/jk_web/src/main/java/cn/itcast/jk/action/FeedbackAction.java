package cn.itcast.jk.action;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.domain.UserInfo;
import cn.itcast.jk.service.FeedbackService;

/**
 * 意见反馈
 * @author DT
 *
 */
public class FeedbackAction extends BaseAction implements ModelDriven<UserInfo> {

	private static final long serialVersionUID = -2627348027707795872L;
	
	private UserInfo model = new UserInfo();
	public UserInfo getModel(){
		return model;
	}
	
	private FeedbackService feedbackService;
	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	/**
	 * 跳转到意见反馈页面
	 * @return
	 * @throws Exception
	 */
	public String tocreate() throws Exception {
		return "tocreate";
	}
	
	private String subject;
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	private String text;
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * 发送意见反馈
	 * @return
	 * @throws Exception
	 */
	public String send() throws Exception {
		
		feedbackService.send(subject, text);
		
		return SUCCESS;
	}
	
}
