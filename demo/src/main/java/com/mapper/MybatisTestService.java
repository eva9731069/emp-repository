package com.mapper;

import com.vo.MybatisTestVo;

import java.util.List;

public interface MybatisTestService {

    String queryById(String id);

    MybatisTestVo queryOne(String id);

    List<MybatisTestVo> queryAll();

    void update(MybatisTestVo vo);

    void insert(MybatisTestVo vo);

    void delete(String id);

    String queryHoliday(String empNo);
}
