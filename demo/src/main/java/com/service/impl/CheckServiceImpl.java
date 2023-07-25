package com.service.impl;

import com.bean.CheckFormBean;
import com.mapper.CheckDao;
import com.service.CheckService;
import com.vo.CheckVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@Service("CheckServiceImpl")
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckDao checkDao;

    //上班打卡
    @Override
    public String checkIn(CheckFormBean reqVo) {
        String msg = "打卡完成";
        Timestamp nowTime = new Timestamp(new Date().getTime());

        CheckVo checkVo = checkDao.empIsCheckIn(reqVo.getEmpNo(), nowTime);

        // 代表已有打卡紀錄
        if (null != checkVo) {
            msg = "已有打卡紀錄";
        } else {
            // 開始打卡
            checkDao.empCheckIn(reqVo.getEmpNo(), reqVo.getChName(), nowTime);
        }
        return msg;
    }

    @Override
    public String checkOut(CheckFormBean reqVo) {

        boolean formStatus = reqVo.isStatus();
        String msg = "";

        Timestamp nowTime = new Timestamp(new Date().getTime());
        CheckVo checkInVo = checkDao.empIsCheckIn(reqVo.getEmpNo(), nowTime);

        // 防止未打上班卡,直接打下班卡
        if (checkInVo == null) {
            return msg = "尚未上班打卡";
        }

        CheckVo checkVo = checkDao.empIsCheckOut(reqVo.getEmpNo(), nowTime);

        if (checkVo != null) {
            return msg = "已有打卡紀錄";
        }

        // 未滿9小時，經確認後確定要打卡
        if (formStatus) {
            checkDao.empCheckOut(reqVo.getEmpNo(), nowTime);
            return msg = "打卡完成";
        }

        // 檢查上班時間是否滿9小時
        long minutes = this.countWorkTime(checkInVo.getCheckInTime());

         if (minutes < 540) {
            msg = "尚未超過9小時";
        } else {
            checkDao.empCheckOut(reqVo.getEmpNo(), nowTime);
            msg = "打卡完成";
        }

        return msg;
    }

    private long countWorkTime(Timestamp checkInTime) {
        // 取得現在時間
        Timestamp now = new Timestamp(System.currentTimeMillis());

        long nowMillis = now.getTime();

        // 取得上班時間
        long checkInTimeMillis = checkInTime.getTime();

        // 計算上班時數是否有9小時
        long timeDifferenceMillis = nowMillis - checkInTimeMillis;

        // 計算時間差，例如轉換為分鐘
        long minutes = timeDifferenceMillis / (60 * 1000);

        return minutes;
    }
}
