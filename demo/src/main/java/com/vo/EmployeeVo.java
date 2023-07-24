package com.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeVo {

    String empNo;
    String empAccount;
    String empPassword;
    String chName;
    String enName;
//    String emp_account;
//    String emp_password;
//    String ch_name;
//    String en_name;
    String email;
    String personId;
    String gender;
    String birth;
    String age;
    String bloodType;
    String personType;
    String soldierType;
    String marriedType;
    String homeAddr;
    String contactAddr;
    String homePhone;
    String cellPhone;
    String pressingPerson;
    String pressingRelation;
    String pressingPhone;
    String registerDate;
    String resignDate;
    byte[] empPhoto;
    byte[] resignPhoto;
    String empStatus;
    String auth;
    

}
