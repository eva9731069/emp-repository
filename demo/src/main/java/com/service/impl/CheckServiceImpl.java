package com.service.impl;

import com.bean.CheckFormBean;
import com.mapper.CheckDao;
import com.service.CheckService;
import com.vo.CheckVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service("CheckServiceImpl")
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckDao checkDao;

    //上班打卡
    @Override
    public String checkIn(CheckFormBean reqBean) {
        String msg = "";
        Timestamp nowTime = new Timestamp(new Date().getTime());

        CheckVo checkVo = checkDao.empIsCheckIn(reqBean.getEmpNo(), nowTime);

        if (null != checkVo) {
            //代表已有打卡紀錄
            msg = "已有打卡紀錄";
        } else {
            checkDao.empCheckIn(reqBean.getEmpNo(), reqBean.getChName(), nowTime);

            msg = "打卡完成";
        }
        return msg;
    }

    //下班打卡
    @Override
    public String checkOut(CheckFormBean reqBean) {

        boolean formStatus = reqBean.isStatus();
        String msg = "";

        Timestamp nowTime = new Timestamp(new Date().getTime());
        CheckVo checkInVo = checkDao.empIsCheckIn(reqBean.getEmpNo(), nowTime);
        //防止未打上班卡,直接打下班卡
        if (checkInVo == null) {
            return msg = "尚未上班打卡";
        }
        CheckVo checkVo = checkDao.empIsCheckOut(reqBean.getEmpNo(), nowTime);
//
        long minutes = this.countIs9hour(checkInVo.getCheckInTime());

        if (formStatus) {
            checkDao.empCheckOut(reqBean.getEmpNo(), nowTime);
            return msg = "打卡完成";
        }

        if (checkVo != null) {
            msg = "已有打卡紀錄";
        } else if (minutes < 540) {
            msg = "尚未超過9小時";

        } else {
            checkDao.empCheckOut(reqBean.getEmpNo(), nowTime);
            msg = "打卡完成";
        }

        return msg;
    }
    @Override
    public List<CheckFormBean> queryEmpDate(String startDate, String endDate,String empNo) {

        if (startDate.equals("    -  -  ") && endDate.equals("    -  -  ")) {


            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("startDate", startDate);
            paramMap.put("endDate", endDate);
            paramMap.put("empNo", empNo);

            CheckFormBean checkFormBean = new CheckFormBean();
            checkFormBean.setStartDate(checkFormBean.getStartDate());
            checkFormBean.setEndDate(checkFormBean.getEndDate());
//        String checkDate = forQueryTime(checkFormBean);

            List<CheckFormBean> beanList = new ArrayList<>();
            // 呼叫 DAO 層進行查詢
            List<CheckVo> checkVoList = checkDao.queryEmpDate(paramMap);
            for (CheckVo checkList : checkVoList) {
                // 請假類別
                String typeMsg = checkHolidayType(checkList);
                // 是否曠職
                String absenceMsg = absence(checkList.getMissWorkDate());
                // 是否早退
                String leaveMsg = leaveEarly(checkList);


                log.info("typeMsg==" + typeMsg);
                log.info("absenceMsg==" + absenceMsg);
                log.info("leaveMsg==" + leaveMsg);

                String newEmpNo = checkList.getEmpNo();
                String newName = checkList.getChName();
                Timestamp newStart = checkList.getCheckInTime();
                Timestamp newEnd = checkList.getCheckOutTime();
                String holidayHour = checkList.getHolidayHour();
                String typeHour = typeMsg + holidayHour;


                CheckFormBean formBean = new CheckFormBean();

                formBean.setHolidayType(typeHour);
                formBean.setMissWorkStatus(absenceMsg);
                formBean.setLeaveEarly(leaveMsg);
                formBean.setEmpNo(newEmpNo);
                formBean.setChName(newName);
                formBean.setCheckInTime(newStart);
                formBean.setCheckOutTime(newEnd);


                beanList.add(formBean);

            }

            for (CheckFormBean bra : beanList) {
                log.info("getEmpNo=>{}", bra.getEmpNo());
                log.info("getMissWorkStatus=>{}", bra.getMissWorkStatus());
                log.info("getHolidayType=>{}", bra.getHolidayType());
            }

            return beanList;
        }else {
            return null;
        }


        }


    private long countIs9hour(Timestamp checkInTime) {

        //取得現在時間
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long nowMillis = now.getTime();
        //取得上班時間

//    Timestamp checkInTime = checkInVo.getCheckInTime();
//        long checkInTimeMillis = checkInTime.getTime();

        long checkInTimeMillis = checkInTime.getTime();
        //計算上班時數是否有9小時
        long timeDifferenceMillis = nowMillis - checkInTimeMillis;

        // 計算時間差，例如轉換為分鐘
        long minutes = timeDifferenceMillis / (60 * 1000);

        return minutes;
    }
   private String checkHolidayType(CheckVo holidayType){


       if ("0".equals(holidayType.getHolidayType())) {
           return "婚嫁";
       } else if ("1".equals(holidayType.getHolidayType())) {
           return "喪假";
       } else if ("2".equals(holidayType.getHolidayType())) {
           return "病假";
       } else if ("3".equals(holidayType.getHolidayType())) {
           return "事假";
       } else if ("4".equals(holidayType.getHolidayType())) {
           return "公假";
       } else if ("5".equals(holidayType.getHolidayType())) {
           return "特休";
       } else {
           return "";
       }
    }

    private String leaveEarly(CheckVo checkVo){
        Timestamp checkInTime = checkVo.getCheckInTime();
        Timestamp checkOutTime = checkVo.getCheckOutTime();
        long inTime =  checkInTime.getTime();
        long outTime = checkOutTime.getTime();
long time = outTime - inTime;
long mins = time / (60 * 1000);

log.info("checkInTime=>"+checkInTime);
log.info("checkOutTime=>"+checkOutTime);
        log.info("mins=>"+mins);

        if(mins < 540){
            return  "早退";
        }else {
            return "";
        }
    }

    private String absence(Date missWorkDate){

        if(missWorkDate != null){
          return  "曠職";
        }else {
            return "";
        }
    }

//    private String forQueryTime(CheckFormBean checkFormBean){
//        String start = checkFormBean.getStartDate();
//        String end = checkFormBean.getEndDate();
//        if(start != "    -  " && end != "    -  "){
//           return "請輸入年月日";
//        }
//           return "";
//    }
private  String forQueryTime(String startDate,String endDate) {
    if (startDate.equals("    -  ") && endDate.equals("    -  ")) {

    }
   return "請輸入年月日";
}
}
