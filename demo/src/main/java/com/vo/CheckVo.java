package com.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;


@Data
public class CheckVo {
    String empNo;
    String chName;
    Timestamp checkInTime;
    Timestamp checkOutTime;
    Date missWorkDate;
    String missWorkStatus;
    String holidayType;
    String holidayHour;

}
