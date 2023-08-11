package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.entity.SysUserRole;

import java.util.List;

@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRole> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
