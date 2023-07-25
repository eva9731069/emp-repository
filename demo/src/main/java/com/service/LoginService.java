package com.service;

import com.vo.EmployeeVo;

import java.util.List;


public interface LoginService {

    public EmployeeVo loginVerify(EmployeeVo reqVo);
    public List<EmployeeVo> queryEmpOne(EmployeeVo reqVo);

    public List<EmployeeVo> queryEmpAll();

    public void addEmp(EmployeeVo reqVo);

    public void deleteEmp(EmployeeVo reqVo);

    public void editEmp(EmployeeVo reqVo);

    public List<String> getUserFunction(EmployeeVo reqVo);

}
