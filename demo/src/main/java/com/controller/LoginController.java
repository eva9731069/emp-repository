package com.controller;

import com.emailService.MailService;
import com.mapper.AttendanceDao;
import com.mapper.EmpDao;
import com.service.LoginService;
import com.timerTask.DemoTimerTask;
import com.util.EncodeUtil;
import com.vo.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Timer;


@RestController
@RequestMapping(value = "/home")
@EnableAutoConfiguration
@Slf4j
public class LoginController {

    @Autowired
    private EmpDao empDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    @Qualifier("LoginServiceImpl")
    private LoginService loginService;

    @Value("${aes.key}")
    private String aesKey;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MailService sendMailservice;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    EmployeeVo login(@RequestBody EmployeeVo reqVo, HttpSession session) {
        EmployeeVo empVo = loginService.loginVerify(reqVo);

        //登入成功
        if (empVo != null) {
            EncodeUtil encodeUtil = new EncodeUtil();
            //取得加密資訊
            byte[] encryptedData = encodeUtil.aesEncode(empVo.getEmpNo() + "-" + empVo.getEmpPassword(), aesKey);
            log.info("encryptedData" + encryptedData);
//            empVo.setEncryptedData(encryptedData);
            request.getSession().setAttribute("encryptedData", encryptedData);
            request.getSession().setAttribute("empNo", empVo.getEmpNo());

        }
//        sendMailservice.prepareAndSend("eva9731069@gmail.com","subject","body");
        return empVo;
    }


}
