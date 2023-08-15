package com.sumCo.modules.sys.service.impl;

import com.sumCo.modules.sys.dao.CheckInDao;
import com.sumCo.modules.sys.entity.CheckInVo;
import com.sumCo.modules.sys.entity.SysDept;
import com.sumCo.modules.sys.service.CheckInService;
import com.sumCo.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("CheckInServiceImpl")
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private CheckInDao checkInDao;

    @Override
    public void checkIn(CheckInVo checkInVo) {
        String msg = "打卡完成";
//        Timestamp nowTime = new Timestamp(new Date().getTime());
//
//        CheckVo checkVo = checkDao.empIsCheckIn(reqVo.getEmpNo(), nowTime);
//
//        // 代表已有打卡紀錄
//        if (null != checkVo) {
//            msg = "已有打卡紀錄";
//        } else {
//            // 開始打卡
//            checkDao.empCheckIn(reqVo.getEmpNo(), reqVo.getChName(), nowTime);
//        }
//        return msg;
    }
}
