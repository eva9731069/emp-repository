package com.bean;

import com.vo.CheckVo;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class CheckFormBean {
    String empNo;
    String chName;
    Timestamp checkInTime;
    Timestamp checkOutTime;
    String startDate;
    String endDate;
    boolean status;
    Date missWorkDate;
    String missWorkStatus;
    String holidayType;
    String holidayHour;
    String leaveEarly;
    CheckVo checkVo;

}
