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

        return "Hello World!";
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    String test(@PathVariable String id) {

        return mybatisDao.queryOne(id).getLimit_amt();
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    List<MybatisTestVo> queryAll() {
        List<MybatisTestVo> list = mybatisDao.queryAll();

        for(MybatisTestVo  data:list){
            log.info("data=>{}",data);
        }
        return list;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    String insert(@RequestBody MybatisTestVo vo) {

        mybatisDao.insert(vo);
        return "Hello World!";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    String update(@RequestBody MybatisTestVo vo) {

        mybatisDao.update(vo);
        return "Hello World!";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    String delete(@PathVariable String id) {

        mybatisDao.delete(id);
        return "Hello World!";
    }

}
