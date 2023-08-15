package com.sumCo.modules.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * @author oplus
 * @Description: TODO(還需在XML文件裡，有對應的SQL語句)
 * @date 2017-6-23 15:07
 */
public interface BaseDao<T> {
	
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	
	int update(T t);
	
	int update(Map<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] id);
	int deleteBatchTwo(Object[]id);
	int deleteBatchThree(Object[]id);

	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
