package com.mapper;

import com.vo.CheckVo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CheckDao {
    void empCheckIn(String empNo, String chName, Timestamp checkInTime);
    void empCheckOut( String empNo,Timestamp checkOutTime);


    CheckVo empIsCheckIn(String empNo, Timestamp checkInTime);
    CheckVo empIsCheckOut(String empNo, Timestamp checkOutTime);
     List<CheckVo> queryEmpDate(Map<String,String>paramMap);
    List<CheckVo> queryEmpMonth(Map<String,String>paramMap);




}
