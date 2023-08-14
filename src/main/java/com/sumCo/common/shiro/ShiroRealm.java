package com.sumCo.common.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumCo.common.Constant;
import com.sumCo.modules.sys.entity.SysUser;
import com.sumCo.modules.sys.entity.SysUserToken;
import com.sumCo.modules.sys.service.SysUserService;
import com.sumCo.modules.sys.service.SysUserTokenService;

import java.util.Set;

/**
 * @author oplus
 * @Description: TODO(認證)
 * @date 2017-6-23 15:07
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        Long userId = user.getId();


        Set<String> permsSet = sysUserService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();


        SysUserToken tokenEntity = sysUserTokenService.queryByToken(accessToken);

        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，請重新登入");
        }


        SysUser user = sysUserService.queryObject(tokenEntity.getUserId());
        if(Constant.UserStatus.DISABLE.getValue()==user.getStatus()){
            throw new LockedAccountException("帳號已被鎖定,請聯繫管理員");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
