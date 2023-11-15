package com.sumCo.common.aspect;

import com.google.gson.Gson;
import com.sumCo.common.utils.HttpContextUtils;
import com.sumCo.common.utils.IPUtils;
import com.sumCo.modules.sys.entity.SysLog;
import com.sumCo.modules.sys.entity.SysUser;
import com.sumCo.modules.sys.service.SysLogService;
import com.sumCo.modules.sys.service.NoSqlService;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.bson.Document;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author oplus
 * @Description: TODO(系統日誌 ， 切面處理類)
 * @date 2017-6-23 15:07
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

	@Autowired
	private NoSqlService noSqlService;

	protected Logger logger = LoggerFactory.getLogger(getClass());


    @Pointcut("@annotation(com.sumCo.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //執行方法
        Object result = point.proceed();
        //執行時長(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        this.saveNoSqlDb(point, time);

        //保存日誌
        saveSysLog(point, time);

        return result;
    }

    private void saveNoSqlDb(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        com.sumCo.common.annotation.SysLog log = method.getAnnotation(com.sumCo.common.annotation.SysLog.class);
        if (log != null) {
            //注解上的描述
            sysLog.setOperation(log.value());
        }

        //請求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //請求的參數
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {
			logger.error("", e);
        }

        //獲取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //設置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //用戶名
        String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
        sysLog.setUsername(username);

        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());

		Document document = new Document("userName", sysLog.getUsername()).
				append("operation", sysLog.getOperation()).
				append("method", sysLog.getMethod()).
				append("params", sysLog.getParams()).
				append("ip", sysLog.getIp()).
				append("time", sysLog.getTime()).
				append("createTime", sysLog.getCreateTime());

		noSqlService.insert(sysLog, "sysLog", document);
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        com.sumCo.common.annotation.SysLog log = method.getAnnotation(com.sumCo.common.annotation.SysLog.class);
        if (log != null) {
            //注解上的描述
            sysLog.setOperation(log.value());
        }

        //請求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //請求的參數
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //獲取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //設置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //用戶名
        String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
        sysLog.setUsername(username);

        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        //保存系統日誌
        sysLogService.save(sysLog);
    }

}
