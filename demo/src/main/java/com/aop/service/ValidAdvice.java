package com.aop.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Component
@Aspect
public class ValidAdvice {

    @Value("${aes.key}")
    private String aesKey;

    // 定義切點，這裡的切點表示式指定攔截 MyService 介面中的所有方法
    @Pointcut("execution(* com.controller.EmpController.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 獲取當前的HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 獲取Session資料
        byte[] sessionData = (byte[]) request.getSession().getAttribute("encryptedData");

        System.out.println("Before method execution...");

        byte[] keyBytes = Base64.getDecoder().decode(aesKey);
        SecretKey customSecretKey = new SecretKeySpec(keyBytes, "AES");
        // 解密操作
        Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, customSecretKey);
        byte[] decryptedData = decryptCipher.doFinal(sessionData);

        // 输出解密后的结果
        System.out.println("解密後結果=>" + new String(decryptedData));


        System.out.println(getMethodName(joinPoint));
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
    }

//    @After("pointcut()")
//    public void myAfterAdvice(JoinPoint joinPoint) {
//        System.out.println("After method execution...");
//    }

    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getName();
    }

//    @AfterThrowing(pointcut="execution(* com.controller.EmpController.*(..))", throwing= "error")
//    public void afterThrowingAdvice(JoinPoint jp, Throwable error){
//        System.out.println("[afterThrowingAdvice] Method Signature: "  + jp.getSignature());
//        System.out.println("[afterThrowingAdvice] Exception: "+error);
//    }

}