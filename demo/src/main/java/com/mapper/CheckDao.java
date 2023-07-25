package com.mapper;

import com.vo.CheckVo;

import java.sql.Timestamp;

public interface CheckDao {
    void empCheckIn(String empNo, String chName, Timestamp checkInTime);
    void empCheckOut( String empNo,Timestamp checkOutTime);


    CheckVo empIsCheckIn(String empNo, Timestamp checkInTime);
    CheckVo empIsCheckOut(String empNo, Timestamp checkOutTime);



}
