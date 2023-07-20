package com.mapper;

import com.vo.EmployeeVo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface EmpDao {

    EmployeeVo queryEmp(String empAccount, String empPassword);

    EmployeeVo queryEmpByEmpNo(String empNo);

    List<EmployeeVo> queryAll();

    List<EmployeeVo> getEmpList(String empAccount, String empPassword);

    void insert(EmployeeVo vo);

    void delete(EmployeeVo vo);

    void edit(EmployeeVo vo);

}
