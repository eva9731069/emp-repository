package com.sumCo.modules.sys.service.impl;

import com.sumCo.modules.sys.dao.CheckInDao;
import com.sumCo.modules.sys.entity.CheckInVo;
import com.sumCo.modules.sys.formBean.CheckInFormBean;
import com.sumCo.modules.sys.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service("CheckInServiceImpl")
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private CheckInDao checkInDao;

    @Override
    public String checkIn(Long userId, String userName) {
        String msg = "打卡完成";

        // 檢查是否有打卡紀錄
        CheckInVo checkInVo = checkInDao.queryObject(userName);

        if (null != checkInVo) {
            msg = "已有打卡紀錄";
        } else {
            CheckInFormBean checkInFormBean = new CheckInFormBean();
            checkInFormBean.setUserName(userName);
            checkInFormBean.setUserId(userId);

            checkInDao.empCheckIn(checkInFormBean);
        }

        return msg;
    }

    @Override
    public String checkOut(Long userId, String userName, String checkOutConfirm) {
        String msg = "打卡完成";

        // 檢查是否有打卡紀錄
        CheckInVo checkInVo = checkInDao.queryObject(userName);

        // 防止未打上班卡,直接打下班卡
        if (checkInVo == null) {
            return msg = "尚未上班打卡";
        }

        CheckInVo checkVo = checkInDao.empIsCheckOut(userName);

        if (checkVo != null) {
            return msg = "已有打卡紀錄";
        }

        // 未滿9小時，經確認後確定要打卡
        if ("true".equals(checkOutConfirm)) {
            CheckInFormBean checkInFormBean = new CheckInFormBean();
            checkInFormBean.setUserName(userName);
            checkInFormBean.setUserId(userId);

            checkInDao.empCheckOut(checkInFormBean);
            return msg = "打卡完成";
        }

        // 檢查上班時間是否滿9小時
        long minutes = this.countWorkTime(checkInVo.getCheckInTime());

        if (minutes < 540) {
            msg = "尚未超過9小時";
        } else {
            CheckInFormBean checkInFormBean = new CheckInFormBean();
            checkInFormBean.setUserName(userName);
            checkInFormBean.setUserId(userId);

            checkInDao.empCheckOut(checkInFormBean);
            msg = "打卡完成";
        }


        return msg;
    }

    @Override
    public List<CheckInVo> queryList(Map<String, Object> paramMap) {
        List<CheckInVo> checkVoList = checkInDao.queryList(paramMap);

        return checkVoList;
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
