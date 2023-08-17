package com.sumCo.modules.sys.service;

import com.sumCo.modules.sys.entity.CheckInVo;
import com.sumCo.modules.sys.entity.SysDept;

import java.util.List;
import java.util.Map;


public interface CheckInService {
	String checkIn(Long userId, String username);

	String checkOut(Long userId, String username, String checkOutConfirm);

	List<CheckInVo> queryList(Map<String, Object> map);
	

}
