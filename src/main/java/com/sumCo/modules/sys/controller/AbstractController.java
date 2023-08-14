package com.sumCo.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumCo.modules.sys.entity.SysUser;

/**
 * @author oplus
 * @Description: TODO(Controller公共組件)
 * @date 2017-6-23 15:07
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getId();
    }


}
