package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.entity.SysRole;

@Mapper
public interface SysRoleDao extends BaseDao<SysRole> {



    SysRole queryByRoleName(String roleName);

}
