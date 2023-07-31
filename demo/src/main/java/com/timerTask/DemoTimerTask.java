package com.timerTask;


import com.emailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class DemoTimerTask  {

    @Autowired
    private MailService mailService;
    // 每隔5秒執行一次
    @Scheduled(fixedRate = 5000)
    public void runTask() {

//        mailService.prepareAndSend("053792@fju.edu.tw","Sample mail subject");
        System.out.println("定時任務 - 執行中");
    }

    // 每天凌晨1點執行
    @Scheduled(cron = "0 0 1 * * ?")
    public void runDailyTask() {
        System.out.println("每天凌晨1點的定時任務 - 執行中");
    }

}
