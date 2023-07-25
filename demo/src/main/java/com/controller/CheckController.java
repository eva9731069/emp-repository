package com.controller;

import com.bean.CheckFormBean;
import com.mapper.CheckDao;
import com.service.CheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
@RestController
@RequestMapping(value = "/user")
@EnableAutoConfiguration
@Slf4j
public class CheckController {

        @Autowired
        private CheckDao checkDao;

        @Autowired
        @Qualifier("CheckServiceImpl")
        private CheckService checkService;

        @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
        String checkIn(@RequestBody CheckFormBean reqVo, HttpSession session) {
            return   checkService.checkIn(reqVo);
        }
        @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
        String checkOut(@RequestBody CheckFormBean reqVo, HttpSession session) {
                return   checkService.checkOut(reqVo);
        }

}
