package com.service;

import com.bean.CheckFormBean;

public interface CheckService {
    public String checkIn(CheckFormBean reqVo);
    public String checkOut(CheckFormBean reqVo);

}
