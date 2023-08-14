package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sumCo.modules.sys.entity.SysConfig;

@Mapper
public interface SysConfigDao extends BaseDao<SysConfig> {
	
	/**
	 * 根據key，查詢SysConfig
	 */
	SysConfig queryObjectByKey(String paramKey);
	
	/**
	 * 根據key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
