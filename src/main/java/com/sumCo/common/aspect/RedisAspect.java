package com.sumCo.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.sumCo.common.exception.AppException;

/**
 * @author oplus
 * @Description: TODO(Redis切面處理類)
 * @date 2017-6-23 15:07
 */
@Aspect
@Configuration
public class RedisAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //是否開啟redis緩存  true開啟   false關閉
    @Value("${spring.redis.open: #{false}}")
    private boolean open;

    @Around("execution(* com.sumCo.common.cache.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new AppException("Redis服務異常");
            }
        }
        return result;
    }
}
