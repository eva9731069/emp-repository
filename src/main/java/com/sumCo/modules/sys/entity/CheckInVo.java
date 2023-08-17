package com.sumCo.modules.sys.entity;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;



@Data
public class CheckInVo implements Serializable {

    private Long empNo;

    private String chName;

    private Timestamp checkInTime;

    private Timestamp checkOutTime;

    private String missWorkDate;

    /**
     * 曠職狀態
     */
    private String missWorkStatus;

    /**
     * 打卡上班或打卡下班的狀態
     */
	private String status;


}
