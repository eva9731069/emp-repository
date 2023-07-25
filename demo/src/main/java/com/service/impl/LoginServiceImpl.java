package com.service.impl;

import com.mapper.EmpDao;
import com.mapper.EmpFunctionDao;
import com.service.LoginService;
import com.vo.EmpFunctionVo;
import com.vo.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("LoginServiceImpl")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmpDao empDao;

    @Autowired
    private EmpFunctionDao empFunctionDaoDao;
    @Override
    public EmployeeVo loginVerify(EmployeeVo reqVo) {
        EmployeeVo empVo = empDao.queryEmp(reqVo.getEmpAccount(), reqVo.getEmpPassword());
        return empVo;
    }

    @Override
    public List<EmployeeVo> queryEmpOne(EmployeeVo reqVo) {
        List<EmployeeVo> empList = empDao.getEmpList(reqVo.getEmpAccount(), reqVo.getEmpPassword());

        return empList;
    }

    @Override
    public List<EmployeeVo> queryEmpAll() {
        List<EmployeeVo> empList = empDao.queryAll();
        return empList;
    }

    @Override
    public void addEmp(EmployeeVo reqVo) {

        empDao.insert(reqVo);
    }

    @Override
    public void deleteEmp(EmployeeVo reqVo) {
        empDao.delete(reqVo);
    }

    @Override
    public void editEmp(EmployeeVo reqVo) {
        empDao.edit(reqVo);
    }

    @Override
    public List<String> getUserFunction(EmployeeVo reqVo) {

        EmployeeVo empVo = empDao.queryEmpByEmpNo(reqVo.getEmpNo());

        EmpFunctionVo empFunctionVo =  empFunctionDaoDao.getEmpFunction(empVo.getAuth());

        List<String> empFuncList = new ArrayList<>();

        String functionStr = empFunctionVo.getEmpFunction();

        for (String str: functionStr.split(",")){
            empFuncList.add(str);
        }

        return empFuncList;
    }}
