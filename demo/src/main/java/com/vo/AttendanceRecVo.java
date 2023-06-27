package com.vo;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class AttendanceRecVo {
    String emp_no;
    String ch_name;
    Timestamp check_in_time;
    Timestamp check_out_time;
}
