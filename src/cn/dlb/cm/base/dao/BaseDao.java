package cn.dlb.cm.base.dao;

import java.util.List;

public interface  BaseDao<T> {
	
	public void delete(Integer id);
	
	public void add(T t);
	
	public void update(T t);
	
	public T getById(Integer id);
	
	public T getById(String id);
	
	List<T> getByIds(Integer[] ids);
	List<T> getByIds(String[] ids);
	List<T> findAll();
	
}
