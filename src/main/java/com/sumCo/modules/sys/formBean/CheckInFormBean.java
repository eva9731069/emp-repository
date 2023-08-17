package com.sumCo.modules.sys.formBean;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class CheckInFormBean implements Serializable {

	private String status;

	private Long userId;

	private String userName;

	private String workStatus;

	private String isCheckOutConfirm;

	private String startDate;

	private String endDate;

}
