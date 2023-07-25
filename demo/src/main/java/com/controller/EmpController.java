package com.controller;

import com.mapper.CheckDao;
import com.mapper.EmpDao;
import com.service.LoginService;
import com.vo.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@EnableAutoConfiguration
@Slf4j
public class EmpController {

    @Autowired
    private EmpDao empDao;

    @Autowired
    private CheckDao checkDao;

    @Autowired
    @Qualifier("LoginServiceImpl")
    private LoginService loginService;





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


    @RequestMapping(value = "/getUserFunction", method = RequestMethod.POST)
    List<String> getUserFunction(@RequestBody EmployeeVo vo) {
        List<String> empFunctionList = loginService.getUserFunction(vo);

        return empFunctionList;

    }


}
