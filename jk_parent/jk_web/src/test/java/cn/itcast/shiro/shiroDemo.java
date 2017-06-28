package cn.itcast.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class shiroDemo {
	@Test
	public void demo() {
		// 创建认证管理器工厂
		Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 得到安全管理器
		SecurityManager securityManager = securityManagerFactory.getInstance();
		// 获取到subject对象，但是需要先注册工具
		SecurityUtils.setSecurityManager(securityManager);
		// 得到subject对象
		Subject subject = SecurityUtils.getSubject();
		//
		AuthenticationToken token = new UsernamePasswordToken("qyw", "123");
		// 认证
		subject.login(token);
		// 认证成功不会报异常
		System.out.println("成功了");
	}
}
