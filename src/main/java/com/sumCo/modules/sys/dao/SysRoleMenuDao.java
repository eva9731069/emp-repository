package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.entity.SysRoleMenu;

import java.util.List;

@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
