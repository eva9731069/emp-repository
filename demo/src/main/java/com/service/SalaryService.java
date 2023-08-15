package com.service;

import com.bean.SalaryFormBean;
import com.vo.SalaryVo;

import java.util.List;

public interface SalaryService {
   public List<SalaryVo> salaryQuery();

   public  List<SalaryVo>queryEmpSalary(SalaryFormBean salaryFormBean);
   public  List<SalaryVo>salaryEmpAllDate();
   List<SalaryVo> salaryOne(SalaryFormBean salaryFormBean);

   public  SalaryVo salaryEmpDate(SalaryFormBean salaryFormBean);
   public  void addSalary(SalaryFormBean salaryFormBean);
}
