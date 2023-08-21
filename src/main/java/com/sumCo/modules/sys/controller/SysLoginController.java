package com.sumCo.modules.sys.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.sumCo.common.Constant;
import com.sumCo.common.exception.AppException;
import com.sumCo.common.utils.Result;
import com.sumCo.common.utils.ShiroUtils;
import com.sumCo.common.utils.SpringContextUtils;
import com.sumCo.config.KaptchaConfig;
import com.sumCo.modules.sys.entity.SysUser;
import com.sumCo.modules.sys.service.SysUserService;
import com.sumCo.modules.sys.service.SysUserTokenService;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author oplus
 * @Description: TODO(登入相關)
 * @date 2017-6-23 15:07
 */
@RestController
public class SysLoginController extends AbstractController {

    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字驗證碼
        String text = producer.createText();
        //生成圖片驗證碼
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 驗證碼開關
     */
    @RequestMapping("/sys/doGetKaptchaOnOff")
    public Result doGetKaptchaOnOff() {
        boolean kaptchaOnOff = SpringContextUtils.getBean(KaptchaConfig.class).getKaptchaOpen();
        return Result.ok().put("kaptchaOnOff", kaptchaOnOff);
    }

    /**
     * 登入
     */
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public Result login(String username, String password, String captcha) throws IOException {
        //驗證碼
        if (SpringContextUtils.getBean(KaptchaConfig.class).getKaptchaOpen()) {
            String kaptcha = getKaptcha(Constants.KAPTCHA_SESSION_KEY);
            if (!captcha.equalsIgnoreCase(kaptcha)) {
                return Result.error("驗證碼不正確");
            }
        }

        //用戶信息
        SysUser user = sysUserService.queryByUserName(username);

        //帳號不存在
        if (user == null) {
            return Result.error("帳號不存在");
        }


        //密碼驗證
        if (!new Sha256Hash(user.getPassword(), user.getSalt()).toHex().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return Result.error("密碼不正確");
        }


        //帳號鎖定
        if (Constant.UserStatus.DISABLE.getValue() == user.getStatus()) {
            return Result.error("帳號已被鎖定,請聯繫管理員");
        }

        //生成token，並保存到數據庫
        Map<String, Object> result = sysUserTokenService.createToken(user.getId(), user.getUsername(), user.getRole());
        Result r = Result.ok().put(result);
        return r;
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/sys/logout", method = RequestMethod.POST)
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.ok();
    }

    /**
     * 从session中獲取記錄的驗證碼
     */
    private String getKaptcha(String key) {
        Object kaptcha = ShiroUtils.getSessionAttribute(key);
        if (kaptcha == null) {
            throw new AppException("驗證碼已失效");
        }
        ShiroUtils.getSession().removeAttribute(key);
        return kaptcha.toString();
    }

}
