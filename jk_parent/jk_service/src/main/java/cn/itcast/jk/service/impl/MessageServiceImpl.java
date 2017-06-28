package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Message;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.service.MessageService;
import cn.itcast.jk.utils.UtilFuns;

/**
 * @Description:	MessageService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-10 16:51:28
 */

public class MessageServiceImpl implements MessageService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Message> find(String hql, Class<Message> entityClass, Object[] params) {
		return baseDao.find(hql, Message.class, params);
	}

	public Message get(Class<Message> entityClass, Serializable id) {
		return baseDao.get(Message.class, id);
	}

	public Page<Message> findPage(String hql, Page<Message> page, Class<Message> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Message.class, params);
	}

	public void saveOrUpdate(Message entity) {
		/*if(entity.getId()==null){								//代表新增
			
		}*/
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Message> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Message> entityClass, Serializable id) {
		baseDao.deleteById(Message.class, id);
	}

	public void delete(Class<Message> entityClass, Serializable[] ids) {
		baseDao.delete(Message.class, ids);
	}

}

