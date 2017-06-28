package cn.itcast.jk.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.jk.domain.User;
import cn.itcast.jk.utils.SysConstant;
import cn.itcast.jk.utils.UtilFuns;

/**
 * 登录和退出类
 */

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	// SSH传统登录方式
	public String login() throws Exception {

		// if(true){
		// String msg = "登录错误，请重新填写用户名密码!";
		// this.addActionError(msg);
		// throw new Exception(msg);
		// }
		// User user = new User(username, password);
		// User login = userService.login(user);
		// if (login != null) {
		// ActionContext.getContext().getValueStack().push(user);
		// session.put(SysConstant.CURRENT_USER_INFO, login); //记录session
		// return SUCCESS;
		// }
		// return "login";
		// 创建subObject对象
		if (UtilFuns.isEmpty(username)) {
			return LOGIN;
		}
		try {
			Subject subject = SecurityUtils.getSubject();
			// 创建对象
			AuthenticationToken token = new UsernamePasswordToken(username, password);
			// 进行认证
			subject.login(token);
			// 代表 用户登录成功 存入session
			/*
			 * User user = new User(); user.setUserName(username);
			 * user.setPassword(password);
			 */
			User user = (User) subject.getPrincipal();
			ActionContext.getContext().getSession().put(SysConstant.CURRENT_USER_INFO, user);
			// ActionContext.getContext().getSession().put("UserSettings", user);
			ServletActionContext.getRequest().getSession().setAttribute("UserSettings", user.clone());
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "login";

		}

	}

	// 退出
	public String logout() {
		session.remove(SysConstant.CURRENT_USER_INFO); // 删除session
		// shiro 有自己的方法
		SecurityUtils.getSubject().logout();
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
