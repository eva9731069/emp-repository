package com.mapper;

import com.vo.AttendanceRecVo;
import com.vo.EmployeeVo;

import java.sql.Timestamp;
import java.util.List;

public interface AttendanceDao {
    void empCheckIn(String emp_no, String ch_name, Timestamp check_in_time);

}
