package com.timerTask;


import com.emailService.MailService;
import com.mapper.CheckDao;
import com.vo.CheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class DemoTimerTask {

    @Autowired
    private CheckDao checkDao;
    // 每隔5秒執行一次
//    @Scheduled(fixedRate = 5000)
//    public void runTask() {
//
//
//
////        mailService.prepareAndSend("053792@fju.edu.tw","Sample mail subject");
//        System.out.println("定時任務 - 執行中");
//    }


    // 每天凌晨00:05執行，同步曠班的名單
    @Scheduled(cron = "0 5 0 * * ?")
    public void runDailyTask() {
        List<CheckVo> missWorkList = checkDao.empIsCheckInMiss();

        for (CheckVo emp : missWorkList) {
            checkDao.insertMissWork(emp.getEmpNo(), emp.getChName());
        }

        System.out.println("每天凌晨00:05點的定時任務 - 共執行" + missWorkList.size() + "筆數");
    }

}
