package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Memo;
import cn.itcast.jk.domain.MemoIcon;

public interface MemoIconService {

	// 查询所有，带条件查询
	public List<MemoIcon> find(String hql, Class<MemoIcon> entityClass, Object[] params);

	// 获取一条记录
	public MemoIcon get(Class<MemoIcon> entityClass, Serializable id);
}
