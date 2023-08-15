package com.mapper;

import com.vo.SalaryVo;

import java.sql.Timestamp;
import java.util.List;

public interface SalaryDao {
    List<SalaryVo> querySalary();
    List<SalaryVo> queryEmpSalary(String empNo);
   SalaryVo  queryEmpDate(String empNo, Timestamp salaryTimeStart, Timestamp salaryTimeEnd);
   List<SalaryVo> queryEmpAllDate();
   SalaryVo empSalary(String empNo);
   List<SalaryVo> queryOne(String empNo, Timestamp salaryTimeStart, Timestamp salaryTimeEnd);

    void addSalary();
}
