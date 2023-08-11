package com.sumCo.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.sumCo.modules.sys.entity.Generals;

/**
 * @author oplus
 * @Description: TODO(兵器)
 * @date 2017-12-19 13:59:34
 */
public interface GeneralsService {
	
	Generals queryObject(Integer id);
	
	List<Generals> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Generals generals);
	
	void update(Generals generals);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

}
