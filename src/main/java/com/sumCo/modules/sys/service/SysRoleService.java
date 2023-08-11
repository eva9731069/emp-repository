package com.sumCo.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.sumCo.modules.sys.entity.SysRole;

public interface SysRoleService {
	
	SysRole queryObject(Long id);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRole role);
	
	void update(SysRole role);
	
	void deleteBatch(Long[] ids);

}
