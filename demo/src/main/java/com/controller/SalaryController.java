package com.controller;

import com.bean.SalaryFormBean;
import com.mapper.EmpDao;
import com.mapper.SalaryDao;
import com.service.SalaryService;
import com.vo.SalaryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/salary")
@EnableAutoConfiguration
@Slf4j
public class SalaryController {
    @Autowired
    private EmpDao empDao;
    @Autowired
    private SalaryDao salaryDao;
    @Autowired
    @Qualifier("SalaryServiceImpl")
    private SalaryService salaryService;

    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    List<SalaryVo> queryAll() {

        List<SalaryVo> salaryList = salaryService.salaryQuery();

        log.info("salaryList=>"+salaryList);
        return salaryList;
    }

//    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
//    List<SalaryVo> queryOne(@RequestBody SalaryFormBean salaryFormBean){
//        List<SalaryVo> queryEmpList = salaryService.queryEmpSalary(salaryFormBean);
//        return queryEmpList;
//    }

    @RequestMapping(value = "/queryAllDate", method = RequestMethod.POST)
    List<SalaryVo> queryAllDate(){
        List<SalaryVo> salaryAllList = salaryService.salaryEmpAllDate();
    return salaryAllList;
    }
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    List<SalaryVo> queryOne(@RequestBody SalaryFormBean salaryFormBean, HttpSession session){

        List<SalaryVo> queryOne = salaryService.salaryOne(salaryFormBean);

        return queryOne;
    }

    @RequestMapping(value = "/queryDate", method = RequestMethod.POST)
    SalaryVo queryDate(@RequestBody SalaryFormBean salaryFormBean, HttpSession session){

        SalaryVo queryDate = salaryService.salaryEmpDate(salaryFormBean);
        log.info("queryDateList=>"+queryDate);
        return queryDate;
    }

    @RequestMapping(value = "/addSalary" , method = RequestMethod.POST)
    void addSalary(@RequestParam("empNo") String empNo,
                   @RequestParam("chName")String chName,
                   @RequestParam("salary")String salary,
                   @RequestParam("salaryTime")Timestamp salaryTime,
                   @RequestParam("basicSalary")String basicSalary,
                   @RequestParam("jobSafeMoney")String jobSafeMoney,
                   @RequestParam("healthSafeMoney")String healthSafeMoney,
                   @RequestParam("projectBonus")String projectBonus){
        SalaryVo salaryVo = new SalaryVo();
        salaryVo.setEmpNo(empNo);
        salaryVo.setChName(chName);
        salaryVo.setSalary(salary);
        salaryVo.setSalaryTime(salaryTime);
        salaryVo.setBasicSalary(basicSalary);
        salaryVo.setJobSafeMoney(jobSafeMoney);
        salaryVo.setHealthSafeMoney(healthSafeMoney);
        salaryVo.setProjectBonus(projectBonus);
    }



}
