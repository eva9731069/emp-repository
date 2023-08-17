package com.sumCo.modules.sys.dao;

import com.sumCo.modules.sys.entity.CheckInVo;
import com.sumCo.modules.sys.formBean.CheckInFormBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckInDao extends BaseDao<CheckInVo> {

    void empCheckIn(CheckInFormBean checkInFormBean);

    void empCheckOut(CheckInFormBean checkInFormBean);

    CheckInVo empIsCheckOut(String userName);

	
}
