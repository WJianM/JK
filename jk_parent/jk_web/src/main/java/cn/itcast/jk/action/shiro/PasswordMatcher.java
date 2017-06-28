package cn.itcast.jk.action.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import cn.itcast.jk.utils.Encrypt;

public class PasswordMatcher extends SimpleCredentialsMatcher {
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// 获得传过来的对象
		UsernamePasswordToken user = (UsernamePasswordToken) token;
		// 获得密码
		String password = new String(user.getPassword());
		// 对密码进行加密
		String newPassword = Encrypt.md5(password, user.getUsername());
		//从数据库获得密码
		 Object object = info.getCredentials();
		return super.equals(newPassword, object.toString());
	}
}
