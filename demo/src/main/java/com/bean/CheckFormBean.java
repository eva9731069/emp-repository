package com.bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CheckFormBean {
    String empNo;
    String chName;
    Timestamp checkInTime;
    Timestamp checkOutTime;
    boolean status;
}
