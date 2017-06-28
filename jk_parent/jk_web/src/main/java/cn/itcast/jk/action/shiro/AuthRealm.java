package cn.itcast.jk.action.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.itcast.jk.domain.Module;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.Encrypt;
import cn.itcast.jk.utils.UtilFuns;

public class AuthRealm extends AuthorizingRealm {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 查询出菜单 从参数中获取对象
		Collection realm = principals.fromRealm("qyw");
		User user = (User) realm.iterator().next();
		// 获取当前用户所对应的角色
		Set<Role> roles = user.getRoles();
		// 定义一个空set
		Set<String> set = new HashSet<>();
		// 遍历集合 获取每个角色对应的菜单
		for (Role role : roles) {
			Set<Module> modules = role.getModules();
			// 遍历菜单
			for (Module module : modules) {
				set.add(module.getName());
			}

		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(set);

		return info;

	}

	// 认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg) throws AuthenticationException {
		
		// 接收action传来的数据
		UsernamePasswordToken token = (UsernamePasswordToken) arg;
		// 获得用户名
		String username = token.getUsername();
		// dedao密码
		/*String passwod = new String(token.getPassword());*/
		// 查询数据库
		List<User> list = userService.find("from User where userName=?", User.class, new Object[] { username });
		if (list != null && list.size() > 0) {
			User user = list.get(0);
			// 创建SimpleAuthenticationInfo对象
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), "qyw");
			return authenticationInfo;
		}

		return null;
	}

}
