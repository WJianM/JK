package cn.itcast.jk.action.baseinfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Log;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.LogService;
import cn.itcast.jk.utils.SysConstant;

@Aspect
public class logInfo extends BaseAction implements ModelDriven<Log>{
	
	Log log=new Log();
	
	@Override
	public Log getModel() {
		// TODO Auto-generated method stub
		return log;
	}
	
	User user=new User();
	public void setUser(User user) {
		this.user = user;
	}

	
	
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	

	@Before(value="execution(* cn.itcast.jk.action.HomeAction.title(..))")
	public void log(){
		System.out.println("aaaaaaaaaaaaaaaa");
		
		//获取用户id和姓名
		User user =(User)ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
		System.out.println(user.getUserName());
		//获取登录时间
		Date d=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		java.sql.Date d1 = new java.sql.Date(d.getTime()); //转换为sql.Date对象
//		String date = sdf.format(d1);
		
//		log.setId(user.getId());
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		log.setName(user.getUserName());
		
		HttpServletRequest request = ServletActionContext.getRequest();     
	    String ip = request.getRemoteAddr();     
		log.setAddress(ip);
		
		
		log.setId(uuid);
		log.setTime(d1);
		
		/*log.setId("1231asdasdaasddasad");
		log.setName("adsadadsadsaads");
		log.setAddress("adasdasdadadaasddsd");*/

		
		
		try {
			logService.saveOrUpdate(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/*public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
		}
		return ip;
	}*/
	


}
