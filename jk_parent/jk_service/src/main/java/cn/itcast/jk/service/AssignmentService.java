package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.domain.Assignment;
import cn.itcast.jk.utils.Page;

/**
 * @Description:	AssignmentService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-10 19:17:06
 */

public interface AssignmentService {

	public List<Assignment> find(String hql, Class<Assignment> entityClass, Object[] params);
	public Assignment get(Class<Assignment> entityClass, Serializable id);
	public Page<Assignment> findPage(String hql, Page<Assignment> page, Class<Assignment> entityClass, Object[] params);
	
	public void saveOrUpdate(Assignment entity);
	public void saveOrUpdateAll(Collection<Assignment> entitys);
	
	public void deleteById(Class<Assignment> entityClass, Serializable id);
	public void delete(Class<Assignment> entityClass, Serializable[] ids);
}
