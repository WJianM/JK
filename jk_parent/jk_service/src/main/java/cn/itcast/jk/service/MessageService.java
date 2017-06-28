package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.domain.Message;
import cn.itcast.jk.utils.Page;

/**
 * @Description:	MessageService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-10 16:51:28
 */

public interface MessageService {

	public List<Message> find(String hql, Class<Message> entityClass, Object[] params);
	public Message get(Class<Message> entityClass, Serializable id);
	public Page<Message> findPage(String hql, Page<Message> page, Class<Message> entityClass, Object[] params);
	
	public void saveOrUpdate(Message entity);
	public void saveOrUpdateAll(Collection<Message> entitys);
	
	public void deleteById(Class<Message> entityClass, Serializable id);
	public void delete(Class<Message> entityClass, Serializable[] ids);
}
