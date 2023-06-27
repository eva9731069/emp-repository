package com.controller;

import com.mapper.MybatisTestService;
import com.vo.MybatisTestVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
@EnableAutoConfiguration
@Slf4j
public class testController {

    @Autowired
    private MybatisTestService mybatisDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String home() {
        log.info("22224");

        log.info("queryById=>{}",mybatisDao.queryOne("xxxxxxxx").getLimit_amt());
        return "Hello World!";
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    String test(@PathVariable String id) {
        log.info("555");
        log.info("queryById=>{}",mybatisDao.queryById("xxxxxxxx"));

        log.info("queryById=>{}",mybatisDao.queryOne(id).getLimit_amt());
        return mybatisDao.queryOne(id).getLimit_amt();
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    List<MybatisTestVo> queryAll() {
        log.info("00000");

        List<MybatisTestVo> list = mybatisDao.queryAll();

        for(MybatisTestVo  data:list){
            log.info("data=>{}",data);
        }
        return list;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    String insert(@RequestBody MybatisTestVo vo) {
        log.info("7777");
        mybatisDao.insert(vo);
        return "Hello World!";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    String update(@RequestBody MybatisTestVo vo) {
        log.info("666");
        mybatisDao.update(vo);
        return "Hello World!";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    String delete(@PathVariable String id) {
        log.info("hhhhhhhhh");
        mybatisDao.delete(id);
        return "Hello World!";
    }

}
