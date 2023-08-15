package com.service.impl;

import com.bean.SalaryFormBean;
import com.mapper.EmpDao;
import com.mapper.SalaryDao;
import com.service.SalaryService;
import com.vo.EmployeeVo;
import com.vo.SalaryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service("SalaryServiceImpl")

public  class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryDao salaryDao;
    @Autowired
    private EmpDao empDao;
    // 查詢全部
    public List<SalaryVo> salaryQuery(){
        List<SalaryVo> salaryList = salaryDao.querySalary();
        return salaryList;
    }
    // 單一查詢
    public List<SalaryVo> queryEmpSalary(SalaryFormBean salaryFormBean){
        List<SalaryVo> queryEmpSalaryList = salaryDao.queryEmpSalary(salaryFormBean.getEmpNo());
        return queryEmpSalaryList;
    }
    // 薪資紀錄 條件查詢
    public SalaryVo salaryEmpDate(SalaryFormBean salaryFormBean){


           SalaryVo  salaryList = salaryDao.queryEmpDate(salaryFormBean.getEmpNo(), salaryFormBean.getSalaryTimeStart(),salaryFormBean.getSalaryTimeEnd());
//           String forTime = String.valueOf(salaryFormBean.getSalaryTime());

            SalaryVo salaryVo =  salaryDao.queryEmpDate(salaryFormBean.getEmpNo(),salaryFormBean.getSalaryTimeStart(),salaryFormBean.getSalaryTimeEnd());
            EmployeeVo employeeVo = empDao.queryEmpByEmpNo(salaryList.getEmpNo());

            String jobSafe = countJobSafe(employeeVo);
            String healthSafe = countHealthSafe(employeeVo);
            String projectBonus = bonus(salaryVo);
            String salary = countSalaryS(salaryVo);

            salaryList.setBasicSalary(salaryVo.getBasicSalary());
            salaryList.setSalary(salary);
            salaryList.setEmpNo(salaryVo.getEmpNo());
            salaryList.setChName(salaryVo.getChName());
            salaryList.setJobSafeMoney(jobSafe);
            salaryList.setHealthSafeMoney(healthSafe);
            salaryList.setProjectBonus(projectBonus);
            salaryList.setSalaryTime(salaryVo.getSalaryTime());

log.info("salaryVo.getBasicSalary()=>"+salaryVo.getBasicSalary());
        log.info("jobSafe=>"+jobSafe);
        log.info("healthSafe=>"+healthSafe);
        log.info("projectBonus=>"+projectBonus);
        log.info("salary=>"+salary);


        return salaryList;
    }

    // 薪資紀錄 查詢全部
    public List<SalaryVo> salaryEmpAllDate(){


//        List<EmployeeVo> employeeVoList = empDao.queryEmpDate();
//
//
//        for(EmployeeVo  empList : employeeVoList){
//
//            String jobSafe = countJobSafe(empList);
//            String healthSafe = countHealthSafe(empList);
////            String salary = countSalary(empList);
////            Timestamp salaryTime = employeeVoList.getSalaryTime();
//            String basicSalary = empList.getBasicSalary();
//            String salary = basicSalary + jobSafe + healthSafe;
//
//            empList.setJobSafe(jobSafe);
//            empList.setHealthSafe(healthSafe);
//            empList.setBasicSalary(basicSalary);
//            empList.setSa

//            String countJobSafetest = countJobSafetest(data.getJobSafe(), data.getBasicSalary());
//        }

List<SalaryVo> salaryList = salaryDao.queryEmpAllDate();
        for(SalaryVo salaryAllList : salaryList) {
            EmployeeVo employeeVo = empDao.queryEmpByEmpNo(salaryAllList.getEmpNo());


            String jobSafe = String.valueOf(Double.parseDouble(employeeVo.getBasicSalary()) * Double.parseDouble(employeeVo.getJobSafe()));
            String healthSafe = String.valueOf(Double.parseDouble(employeeVo.getBasicSalary()) * Double.parseDouble(employeeVo.getHealthSafe()));
            String projectBonus = bonus(salaryAllList);
            String salary = countSalaryS(salaryAllList);
            Timestamp salaryTime = salaryAllList.getSalaryTime();
            String basicSalary = salaryAllList.getBasicSalary();

            salaryAllList.setSalary(salary);
            salaryAllList.setSalaryTime(salaryTime);
            salaryAllList.setBasicSalary(basicSalary);
            salaryAllList.setJobSafeMoney(jobSafe);
            salaryAllList.setHealthSafeMoney(healthSafe);
            salaryAllList.setProjectBonus(projectBonus);


            log.info("employeeVo=>" +employeeVo);


            log.info("jobSafe=>" + jobSafe);
            log.info("healthSafe=>" + healthSafe);
            log.info("projectBonus=>" + projectBonus);
            log.info("salary=>" + salary);

        }
        return salaryList;
    }

    private String countJobSafetest(String jobSafe, String basicSalary) {
        String countJobSafe = "";

       int a = Integer.valueOf(jobSafe) *  Integer.valueOf(basicSalary);

        return String.valueOf(a);
    }
    // 薪資紀錄 單一查詢
    public List<SalaryVo> salaryOne(SalaryFormBean salaryFormBean){

        List<SalaryVo>  salaryList = salaryDao.queryOne(salaryFormBean.getEmpNo(), salaryFormBean.getSalaryTimeStart(),salaryFormBean.getSalaryTimeEnd());
        for(SalaryVo salaryOneList : salaryList) {
//            EmployeeVo employeeVo = empDao.queryEmpDate();
            EmployeeVo employeeVo = empDao.queryEmpByEmpNo(salaryOneList.getEmpNo());

            String jobSafe = countJobSafe(employeeVo);
            String healthSafe = countHealthSafe(employeeVo);
            String basicSalary = employeeVo.getBasicSalary();
            String salary = countSalaryS(salaryOneList);


            salaryOneList.setBasicSalary(basicSalary);
            salaryOneList.setSalary(salary);
            salaryOneList.setEmpNo(salaryOneList.getEmpNo());
            salaryOneList.setChName(salaryOneList.getChName());
            salaryOneList.setJobSafeMoney(jobSafe);
            salaryOneList.setHealthSafeMoney(healthSafe);
            salaryOneList.setSalaryTime(salaryOneList.getSalaryTime());


        }

        return salaryList;
    }





    // 勞保
    private String countJobSafe(EmployeeVo employeeVo){
//        EmployeeVo employeeVo1 = empDao.queryEmpByEmpNo(employeeVo.getJobSafe());
//        EmployeeVo employeeVo2 = empDao.queryEmpByEmpNo((employeeVo.getBasicSalary()));
        double jobSafe = Double.parseDouble(employeeVo.getJobSafe()) ;
        double basicSalary = Double.parseDouble(employeeVo.getBasicSalary());
        double jobSafeMoney =  basicSalary  * jobSafe;
//        double jobSafeMoney = Double.parseDouble(String.valueOf(employeeVo1)) * Double.parseDouble(String.valueOf(employeeVo2)) ;
        String jobSafeMoneyStr = String.valueOf(jobSafeMoney);
        log.info("jobSafeMoneyStr=>"+jobSafeMoneyStr);
        return jobSafeMoneyStr;
    }
    // 健保
    private String countHealthSafe(EmployeeVo employeeVo){
//        EmployeeVo employeeVo1 = empDao.queryEmpByEmpNo(employeeVo.getHealthSafe());
//        EmployeeVo employeeVo2 = empDao.queryEmpByEmpNo((employeeVo.getBasicSalary()));
        double healthSafe = Double.parseDouble(employeeVo.getHealthSafe()) ;
        double basicSalary = Double.parseDouble(employeeVo.getBasicSalary());
//        double healthSafeMoney =  Double.parseDouble(String.valueOf(employeeVo1)) * Double.parseDouble(String.valueOf(employeeVo2)) ;
        double healthSafeMoney = basicSalary * healthSafe;
        String healthSafeMoneyStr = String.valueOf(healthSafeMoney);
        return healthSafeMoneyStr;
    }
    // 獎金
    private String bonus(SalaryVo salaryVo){
        return salaryVo.getProjectBonus();
    }
    // 薪水
//    private String countSalary(EmployeeVo employeeVo){
//       SalaryVo salaryVo = salaryDao.empSalary(employeeVo.getEmpNo());
//
//        String countJob  = countJobSafe(employeeVo);
//        String countHealth = countHealthSafe(employeeVo);
//
//        salaryVo.setJobSafeMoney(countJob);
//        salaryVo.setHealthSafeMoney(countHealth);
//
//        double forJob = Double.parseDouble(salaryVo.getJobSafeMoney());
//        double forHealth = Double.parseDouble(salaryVo.getHealthSafeMoney());
//
//
//        double forBonus = Double.parseDouble(bonus(salaryVo));
//        double forBasic = Double.parseDouble(salaryVo.getBasicSalary());
//        int forSalary =(int) ((forBasic - forJob - forHealth) + forBonus);
//        String salary = String.valueOf(forSalary);
//        return salary;
//    }

    private String countSalaryS(SalaryVo salaryVo){
        EmployeeVo employeeVo = empDao.queryEmpByEmpNo(salaryVo.getEmpNo());
        String countJob  = countJobSafe(employeeVo);
        String countHealth = countHealthSafe(employeeVo);

        salaryVo.setJobSafeMoney(countJob);
        salaryVo.setHealthSafeMoney(countHealth);

        double forJob = Double.parseDouble(salaryVo.getJobSafeMoney());
        double forHealth = Double.parseDouble(salaryVo.getHealthSafeMoney());


//        double forBonus = Double.parseDouble(bonus(salaryVo));
        double forBasic = Double.parseDouble(salaryVo.getBasicSalary());
        int forSalary =(int) ((forBasic - forJob - forHealth) );
        String salary = String.valueOf(forSalary);
        return salary;
    }
    // 新增筆數
    public void addSalary(SalaryFormBean salaryFormBean){
        salaryDao.addSalary();
    }

}
