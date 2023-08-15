package com.service;

import com.bean.CheckFormBean;

import java.util.List;

public interface CheckService {
    public String checkIn(CheckFormBean reqVo);
    public String checkOut(CheckFormBean reqVo);
    List<CheckFormBean> queryEmpDate(String startDate, String endDate, String empNo);

}
