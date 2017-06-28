package cn.itcast.jk.action;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.domain.Memo;
import cn.itcast.jk.domain.MemoBg;
import cn.itcast.jk.domain.MemoIcon;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.MemoBgService;
import cn.itcast.jk.service.MemoIconService;
import cn.itcast.jk.service.MemoService;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.SysConstant;


/**
 * 主页
 * @author Administrator
 */
public class HomeAction extends BaseAction implements ModelDriven<Memo> {
	
	private static final long serialVersionUID = -1084129033785013035L;
	
	private Memo model = new Memo();
	public Memo getModel(){
		return model;
	}
	
	private String moduleName;		//动态指定跳转的模块，在struts.xml中配置动态的result
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	private MemoService memoService;
	public void setMemoService(MemoService memoService) {
		this.memoService = memoService;
	}
	
	private MemoBgService memoBgService;
	public void setMemoBgService(MemoBgService memoBgService) {
		this.memoBgService = memoBgService;
	}
	
	private MemoIconService memoIconService;
	public void setMemoIconService(MemoIconService memoIconService) {
		this.memoIconService = memoIconService;
	}
	
	private Page<Memo> page = new Page<>();
	
	public Page<Memo> getPage() {
		return page;
	}
	public void setPage(Page<Memo> page) {
		this.page = page;
	}
	
	public String fmain(){
		return "fmain";
	}
	
	public String title(){
		//获取session
		//User curUser = (User)session.get(SysConstant.CURRENT_USER_INFO);
		//ActionContext.getContext().getValueStack().push(curUser);
		
		return "title";
	}
	
	//转向moduleName指向的模块
	public String tomain(){
		//获取request
		String moduleName = (String)request.get("moduleName");
		
		this.setModuleName(moduleName);
		return "tomain";
	}
	
	public String toleft(){
		//获取request
		String moduleName = (String)request.get("moduleName");
		
		this.setModuleName(moduleName);
		return "toleft";
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String modifyPersonalInfo() {
		System.out.println("PersonalInfo");
		// User user = (User)
		// ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
		// User user = (User)
		// ActionContext.getContext().getSession().get("UserSettings");
		// User userInfo = (User)
		User userInfo = (User) ServletActionContext.getRequest().getSession().getAttribute("UserSettings");
		System.out.println(userInfo);
		ServletActionContext.getRequest().setAttribute("user", userInfo);
		return "PersonalInfo";
	}

	public String sendSystemFeedback() {
		System.out.println("SystemFeedback");
		return "SystemFeedback";
	}
	
	User user = (User) ServletActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
	
	/**
	 * 查询备忘录
	 * @return
	 * @throws Exception
	 */
	public String memolist() throws Exception {
		
		List<Memo> memolist = memoService.find("from Memo where user.id = ? and state = 0", Memo.class, new Object[]{user.getId()});
		this.put("memolist", memolist);
		System.out.println(memolist);
		return "memolist";
	}
	
	/**
	 * 跳转到新建备忘录页面
	 * @return
	 * @throws Exception
	 */
	public String tocreatememo() throws Exception {
		
		List<MemoBg> memoBgList = memoBgService.find("from MemoBg", MemoBg.class, null);
		List<MemoIcon> memoIconList = memoIconService.find("from MemoIcon", MemoIcon.class, null);
		
		this.put("memoBgList", memoBgList);
		this.put("memoIconList", memoIconList);
		
		return "tocreatememo";
	}
	
	/**
	 * 新建备忘录
	 * @return
	 * @throws Exception
	 */
	public String insertmemo() throws Exception {
		
		MemoBg memoBg = memoBgService.get(MemoBg.class, model.getMemoBg().getId());
		MemoIcon memoIcon = memoIconService.get(MemoIcon.class, model.getMemoIcon().getId());
		
		model.setUser(user);
		model.setUpdatetime(new Date());
		
		memoService.saveOrUpdate(model);
		
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改备忘录页面
	 * @return
	 * @throws Exception
	 */
	public String toupdatememo() throws Exception {
		
		Memo memo = memoService.get(Memo.class, model.getId());
		List<MemoBg> memoBgList = memoBgService.find("from MemoBg", MemoBg.class, null);
		List<MemoIcon> memoIconList = memoIconService.find("from MemoIcon", MemoIcon.class, null);
		
		this.push(memo);
		this.put("memoBgList", memoBgList);
		this.put("memoIconList", memoIconList);
		
		return "toupdatememo";
	}
	
	/**
	 * 修改备忘录
	 * @return
	 * @throws Exception
	 */
	public String updatememo() throws Exception {
		
		Memo memo = memoService.get(Memo.class, model.getId());
		MemoBg memoBg = memoBgService.get(MemoBg.class, model.getMemoBg().getId());
		MemoIcon memoIcon = memoIconService.get(MemoIcon.class, model.getMemoIcon().getId());
		
		memo.setUpdatetime(new Date());
		memo.setMemoBg(memoBg);
		memo.setMemoIcon(memoIcon);
		memo.setRemark(model.getRemark());
		
		memoService.saveOrUpdate(memo);
		
		return SUCCESS;
	}
	
	/**
	 * 从主页删除备忘录
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		Memo memo = memoService.get(Memo.class, model.getId());
		memo.setState(1);
		memoService.saveOrUpdate(memo);
		
		return SUCCESS;
	}
	
	/**
	 * 分页查询所有备忘录
	 * @return
	 * @throws Exception
	 */
	public String showmemo() throws Exception {
		
		page = memoService.findPage("from Memo where user.id = ?", page, Memo.class, new Object[]{user.getId()});
		page.setUrl("homeAction_showmemo");
		
		return "showmemo";
	}
	
	/**
	 * 删除备忘录
	 * @return
	 * @throws Exception
	 */
	public String deletememo() throws Exception {
		
		String[] ids = model.getId().split(", ");
		//System.out.println(Arrays.toString(ids));
		memoService.delete(Memo.class, ids);
		
		return "deletesuccess";
	}
	
	/**
	 * 还原备忘录到主页
	 * @return
	 * @throws Exception
	 */
	public String restorememo() throws Exception {
		
		Memo memo = memoService.get(Memo.class, model.getId());
		memo.setState(0);
		memoService.saveOrUpdate(memo);
		
		return "deletesuccess";
	}

	public void sendMail() throws Exception {
		String feedback = ServletActionContext.getRequest().getParameter("feedback");
		System.out.println(feedback);
		User user = (User) ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
		userService.sendMail(feedback, user);
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().write("<h2 align='center' style='margin-top:100px;color:blue'>谢谢您的反馈</h2>");
		// return "success";
	}

	public void savePersonalInfo() throws Exception {
		Map<String, String[]> pMap = ServletActionContext.getRequest().getParameterMap();
		System.out.println(pMap);
		User user = (User) ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
		// 这部分可以用反射做的，不过属性太少，直接写
		user.getUserInfo().setRemark(pMap.get("remark")[0]);
		user.getUserInfo().setBirthday(DateFormat.getDateInstance().parse(pMap.get("birthday")[0]));
		user.getUserInfo().setTelephone(pMap.get("telephone")[0]);
		user.getUserInfo().setEmail(pMap.get("email")[0]);
		userService.saveOrUpdate(user);
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().write("<h2 align='center' style='margin-top:100px;color:blue'>保存成功</h2>");
	}

}
