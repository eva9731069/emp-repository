package com.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@EnableAutoConfiguration
public class DecodeUtil {

    public String aesDecode(String aesKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 獲取當前的HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 獲取Session資料
        byte[] sessionData = (byte[]) request.getSession().getAttribute("encryptedData");

        byte[] keyBytes = Base64.getDecoder().decode(aesKey);
        SecretKey customSecretKey = new SecretKeySpec(keyBytes, "AES");
        // 解密操作
        Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, customSecretKey);
        byte[] decryptedData = decryptCipher.doFinal(sessionData);

        // 輸出解密後的結果
        log.info("解密後結果=>" + new String(decryptedData));


        return new String(decryptedData);
    }


}
