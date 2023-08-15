package com.sumCo.modules.sys.dao;

import com.sumCo.modules.sys.entity.CheckInVo;
import com.sumCo.modules.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckInDao extends BaseDao<CheckInVo> {
	
}
