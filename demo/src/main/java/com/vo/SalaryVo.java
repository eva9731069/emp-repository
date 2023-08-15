package com.vo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class SalaryVo {
    String empNo;
    String chName;
    String salary;
    Timestamp salaryTime;
    String basicSalary;
    String jobSafeMoney;
    String healthSafeMoney;
    String projectBonus;
}
