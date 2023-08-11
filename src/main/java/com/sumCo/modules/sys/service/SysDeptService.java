package com.sumCo.modules.sys.service;

import com.sumCo.modules.sys.entity.SysDept;
import java.util.List;
import java.util.Map;

/**
 * @author Honda
 * @Description: TODO(部門管理)
 * @date 2023-08-08 11:01:45
 */
public interface SysDeptService {
	
	SysDept queryObject(Long id);
	
	List<SysDept> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysDept sysDept);
	
	void update(SysDept sysDept);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

}
