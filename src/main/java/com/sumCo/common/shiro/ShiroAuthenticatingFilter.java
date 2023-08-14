package com.sumCo.common.shiro;

import com.google.gson.Gson;
import com.sumCo.common.Constant;
import com.sumCo.common.utils.Result;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author oplus
 * @Description: TODO(shiro 認證過濾器)
 * @date 2017-6-23 15:07
 */
public class ShiroAuthenticatingFilter extends AuthenticatingFilter {

    //創建shiro認證的token
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //獲取請求token
        String token = getRequestToken((HttpServletRequest) request);

        if(StringUtils.isBlank(token)){
            return null;
        }

        return new ShiroToken(token);
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String json = new Gson().toJson(Result.error(HttpStatus.SC_UNAUTHORIZED, "token失效"));
            httpResponse.getWriter().print(json);

            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        try {

            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result r = Result.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            String json = new Gson().toJson(r);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }

        return false;
    }


    private String getRequestToken(HttpServletRequest httpRequest){

        String token = httpRequest.getHeader(Constant.X_TOKEN);


        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter(Constant.X_TOKEN);
        }

        return token;
    }

}
