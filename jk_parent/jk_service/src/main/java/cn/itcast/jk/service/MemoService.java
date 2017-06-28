package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.domain.Memo;
import cn.itcast.jk.utils.Page;

public interface MemoService {

	// 查询所有，带条件查询
	public List<Memo> find(String hql, Class<Memo> entityClass, Object[] params);

	// 获取一条记录
	public Memo get(Class<Memo> entityClass, Serializable id);

	// 分页查询，将数据封装到一个page分页工具类对象
	public Page<Memo> findPage(String hql, Page<Memo> page, Class<Memo> entityClass, Object[] params);

	// 新增和修改保存
	public void saveOrUpdate(Memo entity);

	// 批量新增和修改保存
	public void saveOrUpdateAll(Collection<Memo> entitys);

	// 单条删除，按id
	public void deleteById(Class<Memo> entityClass, Serializable id);

	// 批量删除
	public void delete(Class<Memo> entityClass, Serializable[] ids);

	// 递归删除
	public void deleteDept(Memo d);

	public void Update(Memo entity);

}
