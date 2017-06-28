package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.domain.Fastmenu;
import cn.itcast.jk.utils.Page;

/**
 * @Description:	FastmenuService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-11 20:53:17
 */

public interface FastmenuService {

	public List<Fastmenu> find(String hql, Class<Fastmenu> entityClass, Object[] params);
	public Fastmenu get(Class<Fastmenu> entityClass, Serializable id);
	public Page<Fastmenu> findPage(String hql, Page<Fastmenu> page, Class<Fastmenu> entityClass, Object[] params);
	
	public void saveOrUpdate(Fastmenu entity);
	public void saveOrUpdateAll(Collection<Fastmenu> entitys);
	
	public void deleteById(Class<Fastmenu> entityClass, Serializable id);
	public void delete(Class<Fastmenu> entityClass, Serializable[] ids);
}
