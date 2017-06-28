package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.ShippingOrder;
import cn.itcast.jk.service.ShippingOrderService;
import cn.itcast.jk.utils.Page;




/**
 * @Description:	ShippingOrderService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-9 20:37:58
 */

public class ShippingOrderServiceImpl implements ShippingOrderService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ShippingOrder> find(String hql, Class<ShippingOrder> entityClass, Object[] params) {
		return baseDao.find(hql, ShippingOrder.class, params);
	}

	public ShippingOrder get(Class<ShippingOrder> entityClass, Serializable id) {
		return baseDao.get(ShippingOrder.class, id);
	}

	public Page<ShippingOrder> findPage(String hql, Page<ShippingOrder> page, Class<ShippingOrder> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, ShippingOrder.class, params);
	}

	public void saveOrUpdate(ShippingOrder entity) {
		if(entity.getId()==null){								//代表新增
			
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<ShippingOrder> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<ShippingOrder> entityClass, Serializable id) {
		baseDao.deleteById(ShippingOrder.class, id);
	}

	public void delete(Class<ShippingOrder> entityClass, Serializable[] ids) {
		baseDao.delete(ShippingOrder.class, ids);
	}

}

