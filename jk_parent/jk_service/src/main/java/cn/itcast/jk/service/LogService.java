package cn.itcast.jk.service;

import java.io.Serializable;

import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.domain.Log;
import cn.itcast.jk.utils.Page;

public interface LogService {
	
	
	//分页查询，将数据封装到一个page分页工具类对象
	public Page<Log> findPage(String hql, Page<Log> page, Class<Log> entityClass, Object[] params);


	void save(Log entity);

	

	public void saveOrUpdate(Log entity);

}
