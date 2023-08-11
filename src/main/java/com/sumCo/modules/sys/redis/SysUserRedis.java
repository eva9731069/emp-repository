package com.sumCo.modules.sys.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumCo.common.cache.RedisUtils;
import com.sumCo.modules.sys.entity.SysUser;

/**
 * @author oplus
 * @Description: TODO()
 * @date 2017-7-27 16:11
 */
@Component
public class SysUserRedis {

    private static final String NAME="SysUser:";

    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysUser user) {
        if(user==null){
            return ;
        }

        String id=NAME+user.getId();
        redisUtils.set(id, user);

        String username=NAME+user.getUsername();
        redisUtils.set(username, user);
    }

    public void delete(SysUser user) {
        if(user==null){
            return ;
        }

        redisUtils.delete(NAME+user.getId());
        redisUtils.delete(NAME+user.getUsername());
    }

    public SysUser get(Object key){
        return redisUtils.get(NAME+key, SysUser.class);
    }

}
