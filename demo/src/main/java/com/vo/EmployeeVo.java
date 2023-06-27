package com.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeVo {

    String emp_no;
    String emp_account;
    String emp_password;
    String ch_name;
    String en_name;
    String email;
    String person_id;
    String gender;
    String birth;
    String age;
    String blood_type;
    String person_type;
    String soldier_type;
    String married_type;
    String home_addr;
    String contact_addr;
    String home_phone;
    String cell_phone;
    String pressing_person;
    String pressing_relation;
    String pressing_phone;
    String register_date;
    String resign_date;
    byte[] emp_photo;
    byte[] resign_photo;
    String emp_status;
    String auth;

}
