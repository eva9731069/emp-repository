package com.bean;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class SalaryFormBean {
    String empNo;
    String chName;
    String salary;
    Timestamp salaryTime;
    String basicSalary;
    String jobSafeMoney;
    String healthSafeMoney;
    String projectBonus;
    Timestamp salaryTimeStart;
    Timestamp salaryTimeEnd;
}
