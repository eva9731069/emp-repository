package com.mapper;

import com.vo.AttendanceRecVo;
import com.vo.EmployeeVo;

import java.sql.Timestamp;

public interface AttendanceDao {
    AttendanceRecVo empCheckIn(String empNo, Timestamp emp_password);
}
