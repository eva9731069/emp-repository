package com.mapper;

import com.vo.EmployeeVo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface EmpDao {

    EmployeeVo queryEmp(String emp_account, String emp_password);

    List<EmployeeVo> queryAll();

    List<EmployeeVo> getEmpList(String emp_account, String emp_password);

    void insert(EmployeeVo vo);

    void delete(EmployeeVo vo);

    void edit(EmployeeVo vo);

}
