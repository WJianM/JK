package cn.itcast.jk.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * FastJson的工具类
 * @author Administrator
 */
public class FastJsonUtils {
	
	/**
	 * 将对象转成json串
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object){
		return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 响应字符串
	 * @param response
	 * @param jsonString
	 */
	public static void write_json(HttpServletResponse response,String jsonString){
		response.setContentType("application/json;utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			// 获取到输出流
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 响应字符串
	 * @param response
	 * @param jsonString
	 */
	public static void write_json(HttpServletResponse response,Object obj){
		// 先把obj转换成json，响应
		String jsonString = toJSONString(obj);
		response.setContentType("application/json;utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			// 获取到输出流
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
