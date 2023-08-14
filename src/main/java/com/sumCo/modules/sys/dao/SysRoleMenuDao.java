package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.entity.SysRoleMenu;

import java.util.List;

@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {
	
	/**
	 * 根據角色ID，獲取菜單ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
