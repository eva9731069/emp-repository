package com.sumCo.modules.api2.intercept;

import com.sumCo.common.exception.AppException;
import com.sumCo.modules.api2.annotation.Login;
import io.jsonwebtoken.Claims;
import com.sumCo.modules.api2.utils.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author oplus
 * @Description: TODO(api interceptor)
 * @date 2017-9-27 14:41
 */
@Component
public class ApiInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }


        String token = request.getHeader(jwtUtils.getHeader());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(jwtUtils.getHeader());
        }


        if (StringUtils.isBlank(token)) {
            throw new AppException(jwtUtils.getHeader() + "不能為空", HttpStatus.UNAUTHORIZED.value());
        }
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            throw new AppException(jwtUtils.getHeader() + "已經失效", HttpStatus.UNAUTHORIZED.value());
        }

        return true;
    }

}
