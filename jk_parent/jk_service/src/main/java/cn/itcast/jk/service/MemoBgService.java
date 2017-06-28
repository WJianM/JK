package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Memo;
import cn.itcast.jk.domain.MemoBg;

public interface MemoBgService {

	// 查询所有，带条件查询
	public List<MemoBg> find(String hql, Class<MemoBg> entityClass, Object[] params);

	// 获取一条记录
	public MemoBg get(Class<MemoBg> entityClass, Serializable id);
}
