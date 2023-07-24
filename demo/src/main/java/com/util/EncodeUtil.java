package com.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.util.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@EnableAutoConfiguration
public class EncodeUtil {

    public static String md5(String src, String salt) {
        //這邊使用了springframework的加密方式
        //md5DigestAsHex參數是Bytes，所以透過java String類將字串轉為Bytes
        String result = src + salt;
        return DigestUtils.md5DigestAsHex(result.getBytes());
    }

    public byte[] aesEncode(String doEncodeStr, String aesKey) {
        byte[] keyBytes = Base64.getDecoder().decode(aesKey);

        try {
            // 使用提供的密钥创建 SecretKey 对象
            SecretKey customSecretKey = new SecretKeySpec(keyBytes, "AES");

            // 加密操作
            Cipher encryptCipher = Cipher.getInstance("AES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, customSecretKey);
            byte[] encryptedData = encryptCipher.doFinal(doEncodeStr.getBytes());

            return encryptedData;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }


}
