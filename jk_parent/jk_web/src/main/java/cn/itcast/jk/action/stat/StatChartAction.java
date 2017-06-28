package cn.itcast.jk.action.stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.service.SqlService;
import cn.itcast.jk.utils.FastJsonUtils;
import cn.itcast.jk.utils.UtilFuns;
import cn.itcast.jk.utils.file.FileUtil;

public class StatChartAction extends BaseAction {
	// 注入sqlService
	private SqlService sqlService;

	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	public String factorysale1() throws Exception {
		// sql语句
		String sql = "select Factory_name,sum(amount) amount from CONTRACT_PRODUCT_C t group by Factory_name order by amount";
		// 执行语句
		List list = sqlService.executeSQL(sql);
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<pie>");
		// <slice title="Once a day">258</slice>
		for (int i = 0; i < list.size(); i++) {
			sb.append("<slice title=\"" + list.get(i++) + "\">" + list.get(i) + "</slice>");
		}
		sb.append("</pie>");
		// 写入xml
		FileUtil fileUtil = new FileUtil();
		String sPath = ServletActionContext.getServletContext().getRealPath("/stat/chart/factorysale");
		fileUtil.createTxt(sPath, "data.xml", sb.toString(), "utf-8");
		return "factorysale";
	}

	// productsale
	public String productsale() throws Exception {
		String sql = "select * from (select product_no p,sum(amount) a from CONTRACT_PRODUCT_C group by product_no order by a desc) where rownum<10";
		// 执行语句
		List list = sqlService.executeSQL(sql);
		// 拼接xml
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<chart>");
		sb.append("<series>");
		// 定义一个变量存储id
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			sb.append("<value xid=\"" + (j++) + "\">" + list.get(i++) + "</value>");
		}
		sb.append("</series>");
		sb.append("<graphs>");
		sb.append("<graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
		j = 0;
		for (int i = 1; i < list.size(); i++) {
			sb.append("<value xid=\"" + (j++) + "\">" + list.get(i++) + "</value>");
		}
		sb.append("</graph>");
		sb.append("</graphs>");
		sb.append("<labels>");
		sb.append("<label lid=\"0\">");
		sb.append(
				"<x>0</x> <y>20</y> <rotate></rotate> <width></width><align>center</align><text_color></text_color> ");
		sb.append(" <text_size></text_size><text> <![CDATA[<b>屈永旺</b>]]> </text>");
		sb.append(" </label> </labels></chart>");
		// 写入文件
		FileUtil file = new FileUtil();
		String sPath = ServletActionContext.getServletContext().getRealPath("/stat/chart/productsale");
		file.createTxt(sPath, "data.xml", sb.toString(), "utf-8");
		return "productsale";
	}

	public String onlineinfo() throws Exception {
		String sql = "select s1.a1,nvl(s2.he,0) from ONLINE_INFO_T s1 left join (select to_char(login_time,'HH24') c,count(1) he from LOGIN_LOG_P  group by to_char(login_time,'HH24')) s2 on s1.a1=s2.c order by s1.a1";
		// 执行sql
		List list = sqlService.executeSQL(sql);
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<chart><series>");
		// 定义中间变量
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			sb.append("<value xid=\"" + (j++) + "\">" + list.get(i++) + "</value>");
		}
		sb.append("</series>");
		sb.append("<graphs><graph color=\"#00CC00\" title=\"登录人数\">");
		j = 0;
		for (int i = 1; i < list.size(); i++) {
			sb.append("<value xid=\"" + (j++) + "\">" + list.get(i++) + "</value>");
		}
		sb.append("</graph></graphs></chart>");
		FileUtil fileUtil = new FileUtil();
		String sPath = ServletActionContext.getServletContext().getRealPath("/stat/chart/onlineinfo");
		fileUtil.createTxt(sPath, "data.xml", sb.toString(), "utf-8");
		return "onlineinfo";
	}

	public String getData() throws Exception {
		// sql语句
		String sql = "select Factory_name,sum(amount) amount from CONTRACT_PRODUCT_C t group by Factory_name order by amount";
		/*
		 * var chartData = [ { "country": "Lithuania", "value": 260 },
		 */
		List list = sqlService.executeSQL(sql);
		List<Map<String, Object>> listMap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("FactoryName", list.get(i++));
			map.put("amount", list.get(i));
			listMap.add(map);
		}
		FastJsonUtils.write_json(ServletActionContext.getResponse(), listMap);
		return NONE;

	}

	public String factorysale() throws Exception {
		return "factorysale";
	}
	/*public String onlineinfo() throws Exception {
		return "onlineinfo";
	}*/

}
