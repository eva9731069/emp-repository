package com.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.AttendanceDao;
import com.mapper.EmpDao;

import com.mapper.MybatisTestService;
import com.service.LoginService;
import com.util.SaltUtil;
import com.vo.AttendanceRecVo;
import com.vo.EmployeeVo;
import com.vo.HolidayVo;
import com.vo.MybatisTestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class EmpController {

    @Autowired
    private EmpDao empDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    @Qualifier("LoginServiceImpl")
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    EmployeeVo login(@RequestBody EmployeeVo reqVo, HttpSession session) {

        EmployeeVo empVo = loginService.loginVerify(reqVo);

        //登入成功
        if (empVo != null) {
            session.setAttribute("uid", SaltUtil.uuid());
        }

        return empVo;
    }

    @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
    void checkIn(@RequestBody AttendanceRecVo vo, HttpSession session) {
        loginService.checkIn(vo);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    List<EmployeeVo> query(@RequestBody EmployeeVo vo) {

        List<EmployeeVo> empList = loginService.queryEmpOne(vo);

        return empList;
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    List<EmployeeVo> queryAll() {

        List<EmployeeVo> empList = loginService.queryEmpAll();


        return empList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void add(@RequestParam("empNo") String empNo,
             @RequestParam("empAccount") String empAccount,
             @RequestParam("empPassword") String empPassword,
             @RequestParam("chName") String chName) {

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmpNo(empNo);
        employeeVo.setEmpAccount(empAccount);
        employeeVo.setEmpPassword(empPassword);
        employeeVo.setChName(chName);

        loginService.addEmp(employeeVo);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    void delete(@RequestBody EmployeeVo vo) {
        loginService.deleteEmp(vo);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    void edit(@RequestBody EmployeeVo vo) {

        loginService.editEmp(vo);
    }

    @RequestMapping(value = "/jqueryQuery", method = RequestMethod.POST)
    List<EmployeeVo> jqueryQuery(
            @RequestParam("emp_account") String empAccount,
            @RequestParam("emp_password") String empPassword) {

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmpAccount(empAccount);
        employeeVo.setEmpPassword(empPassword);


        List<EmployeeVo> empList = loginService.queryEmpOne(employeeVo);

        return empList;

    }

    @RequestMapping(value = "/editJquery", method = RequestMethod.POST)
    void editJquery(@RequestParam("emp_no") String empNo,
                    @RequestParam("ch_name") String chName) {

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmpNo(empNo);
        employeeVo.setChName(chName);

        loginService.editEmp(employeeVo);

    }


}
