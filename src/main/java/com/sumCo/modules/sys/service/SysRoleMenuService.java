package com.sumCo.modules.sys.service;

import java.util.List;

public interface SysRoleMenuService {
	
	void saveOrUpdate(Long roleId, List<Long> menuIdList);
	
	/**
	 * 根據角色ID，獲取菜單ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);

	void deleteBatch(Long[] roleIds);
	
}
