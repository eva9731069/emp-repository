package com.mapper;

import com.vo.EmployeeVo;

import java.util.List;

public interface EmpDao {

    EmployeeVo queryEmp(String empAccount, String empPassword);

    EmployeeVo queryEmpByEmpNo(String empNo);

    List<EmployeeVo> queryAll();

    List<EmployeeVo> getEmpList(String empAccount, String empPassword);

//     List<EmployeeVo> queryEmpDate();
     EmployeeVo queryEmpDate();
    void insert(EmployeeVo vo);

    void delete(EmployeeVo vo);

    void edit(EmployeeVo vo);




}
