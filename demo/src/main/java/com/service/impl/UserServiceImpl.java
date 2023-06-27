package com.service.impl;

import com.mapper.MybatisTestService;
import com.service.UserService;
import com.util.SaltUtil;
import com.vo.MybatisTestVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserServiceImpl implements UserService {

    @Autowired
    private MybatisTestService mybatisDao;

    @Override
    public void CreateUser(MybatisTestVo user) {
        //先檢查是否有重複名稱
        String userId = user.getEstimateId();
        //如果查詢結果不為null，代表有重複名稱
        if (mybatisDao.queryOne(userId) != null) {
            throw new IllegalArgumentException("用戶名稱重複");
        }

//        Date now = new Date();
//
//        //密碼加密
//        String salt = SaltUtil.uuid();
//        user.setSalt(salt);
//        String md5Password = MD5util.md5(user.getPassword(), salt);
//        user.setPassword(md5Password);
//        //開始設置其他後台參數，時間，使用者等等
//        user.setIsDelete(0);
//        user.setCreated_user(username);
//        user.setCreated_time(now);
//        user.setModified_user(username);
//        user.setModified_time(now);
//
//        return userDao.CreateUser(user);
    }
}
