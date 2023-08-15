package com.sumCo.modules.sys.entity;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



@Data
public class CheckInVo implements Serializable {

    private Long empNo;

    private String chName;

    private Timestamp checkInTime;

    private Timestamp checkOutTime;

    private String missWorkDate;

    private String missWorkStatus;

	private String status;


}
