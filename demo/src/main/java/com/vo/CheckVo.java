package com.vo;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class CheckVo {
    String empNo;
    String chName;
    Timestamp checkInTime;
    Timestamp checkOutTime;

}
