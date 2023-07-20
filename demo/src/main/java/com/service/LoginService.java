package com.service;

import com.vo.AttendanceRecVo;
import com.vo.EmpFunctionVo;
import com.vo.EmployeeVo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LoginService {

    public EmployeeVo loginVerify(EmployeeVo reqVo);

    public void checkIn(AttendanceRecVo reqVo);

    public List<EmployeeVo> queryEmpOne(EmployeeVo reqVo);

    public List<EmployeeVo> queryEmpAll();

    public void addEmp(EmployeeVo reqVo);

    public void deleteEmp(EmployeeVo reqVo);

    public void editEmp(EmployeeVo reqVo);

    public List<String> getUserFunction(EmployeeVo reqVo);


}
