package com.controller;

import com.config.KafkaProducerConfig;
import com.emailService.MailService;
import com.mapper.EmpDao;
import com.service.LoginService;
import com.util.EncodeUtil;
import com.vo.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;
import java.util.Random;


@RestController
@RequestMapping(value = "/home")
@EnableAutoConfiguration
@Slf4j
public class LoginController {

    @Autowired
    private EmpDao empDao;

    @Autowired
    @Qualifier("LoginServiceImpl")
    private LoginService loginService;

    @Value("${aes.key}")
    private String aesKey;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MailService sendMailservice;

    @Autowired
    private KafkaProducerConfig kafkaTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    EmployeeVo login(@RequestBody EmployeeVo reqVo, HttpSession session) {
//        kafkaTemplate.send("my-topic", "hello...test");
//
        kafkaTemplate.kafkaTemplate().send("my-topic", "hello...testddd");
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
