package com.mapper;

import com.vo.EmpFunctionVo;

import java.sql.Timestamp;

public interface EmpFunctionDao {
    EmpFunctionVo getEmpFunction(String auth);
}
