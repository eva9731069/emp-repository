package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.entity.SysUserToken;

@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserToken> {
    
    SysUserToken queryByUserId(Long userId);

    SysUserToken queryByToken(String token);
	
}
