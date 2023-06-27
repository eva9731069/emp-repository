package com.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.AttendanceDao;
import com.mapper.EmpDao;

import com.mapper.MybatisTestService;
import com.util.SaltUtil;
import com.vo.AttendanceRecVo;
import com.vo.EmployeeVo;
import com.vo.HolidayVo;
import com.vo.MybatisTestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@EnableAutoConfiguration
@Slf4j
public class LoginController {

    @Autowired
    private EmpDao empDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    EmployeeVo login(@RequestBody EmployeeVo vo, HttpSession session) {

        EmployeeVo empVo = empDao.queryEmp(vo.getEmp_account(), vo.getEmp_password());

        //登入成功
        if (empVo != null) {
            session.setAttribute("uid", SaltUtil.uuid());
        }

        return empVo;
    }

    @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
    void checkIn(@RequestBody EmployeeVo vo, HttpSession session) {
        Timestamp checkInTime = new Timestamp(new Date().getTime());

        AttendanceRecVo attendanceVo = attendanceDao.empCheckIn(vo.getEmp_no(), checkInTime);

    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    List<EmployeeVo> query(@RequestBody EmployeeVo vo) {
        List<EmployeeVo> empList = empDao.getEmpList(vo.getEmp_account(), vo.getEmp_password());
        return empList;
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    List<EmployeeVo> queryAll() {
        List<EmployeeVo> empList = empDao.queryAll();
        return empList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void add(@RequestParam("emp_no") String empNo,
             @RequestParam("emp_account") String empAccount,
             @RequestParam("emp_password") String empPassword,
             @RequestParam("ch_name") String chName) {

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmp_no(empNo);
        employeeVo.setEmp_account(empAccount);
        employeeVo.setEmp_password(empPassword);
        employeeVo.setCh_name(chName);

        empDao.insert(employeeVo);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    void delete(@RequestBody EmployeeVo vo) {
        empDao.delete(vo);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    void edit(@RequestBody EmployeeVo vo) {
        empDao.edit(vo);
    }


}
