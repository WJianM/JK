package cn.itcast.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.itcast.jk.domain.User;

public class json {
	@Test
	public void run() {
		User user = new User();
		user.setId("aaa");
		user.setUserName("bbb");
		// 把user对象转换成Json
		String jsonString = JSON.toJSONString(user);
		System.out.println(jsonString);
	}

	@Test
	public void run1() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("aaa", "美美");
		map.put("bbb", "小凤");
		// 把user对象转换成Json
		String jsonString = JSON.toJSONString(map);
		System.out.println(jsonString);
	}

	@Test
	//[{"id":"aaa","roles":[],"userName":"bbb"},{"id":"aaa","roles":[],"userName":"bbb"}]
	public void run2() {
		List<User> list = new ArrayList<User>();
		// 使用FastJson工具类
		User user = new User();
		user.setId("aaa");
		user.setUserName("bbb");
		list.add(user);

		// 使用FastJson工具类
		User user2 = new User();
		user2.setId("aaa");
		user2.setUserName("bbb");
		list.add(user2);

		// 把user对象转换成Json
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
	}
	@Test
	//[{"id":"aaa","roles":[],"userName":"bbb"},{"id":"aaa","roles":[],"userName":"bbb"}]
	public void run3() {
		Set<User> list = new HashSet<>();
		// 使用FastJson工具类
		User user = new User();
		user.setId("aaa");
		user.setUserName("bbb");
		list.add(user);

		// 使用FastJson工具类
		User user2 = new User();
		user2.setId("aaa");
		user2.setUserName("bbb");
		list.add(user2);

		// 把user对象转换成Json
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
	}
	@Test
	public void run4() {
		List<String> list = new ArrayList<String>();
		list.add("qyw");
		list.add("qqq");
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
	}

}
