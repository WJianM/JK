package cn.itcast.jk.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.domain.Message;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.domain.UserInfo;
import cn.itcast.jk.service.MessageService;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.FastJsonUtils;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.SysConstant;

public class TomainAction extends BaseAction implements ModelDriven<Message> {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Message model = new Message();

	public Message getModel() {
		return model;
	}

	private MessageService messageService;

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	// 注入page
	private Page page = new Page<>();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// 跳转到富文本编辑器页面
	public String toUEditor() throws Exception {
		List<User> userList = userService.find("from User", User.class, null);
		ServletActionContext.getRequest().setAttribute("userList", userList);
		// 查询留言列表
		// 获取当前session用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		// 查询该用户所有留言
		page = messageService.findPage("from Message where fromuserid = ?", page, Message.class,
				new Object[] { user.getId() });
		page.setUrl("tomainAction_toUEditor");
		// 压
		super.push(page);
		return "toUEditor";
	}

	// 提交留言
	public String submit() throws Exception {
		model.setMessagetime(new Date());
		if (model.getTouserid().equals("-1")) {
			// 查询所有用户
			List<User> list = userService.find("from User", User.class, null);
			for (User user : list) {
				model.setTousername(user.getUserName());
				model.setTouserid(user.getId());
				messageService.saveOrUpdate(new Message(model.getFromusername(), model.getFromuserid(), model.getTouserid(), model.getTousername(), model.getContent(), model.getMessagetime()));
				
			}
		} else {
			User user = userService.get(User.class, model.getTouserid());
			model.setTousername(user.getUserName());
			messageService.saveOrUpdate(model);

		}

		return "submit";
	}

	// 删除列表
	public String delete() throws Exception {
		String[] ids = model.getId().split(", ");
		messageService.delete(Message.class, ids);
		return "submit";
	}

	public String delete1() throws Exception {
		String[] ids = model.getId().split(", ");
		messageService.delete(Message.class, ids);
		return "submit1";
	}

	// 跳转到富文本编辑器页面
	public String toUEditor2() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		// 查询该用户所有留言
		page = messageService.findPage("from Message where touserid = ?", page, Message.class,
				new Object[] { user.getId() });
		page.setUrl("tomainAction_toUEditor2");
		// 压
		super.push(page);
		return "toUEditor2";
	}

}
