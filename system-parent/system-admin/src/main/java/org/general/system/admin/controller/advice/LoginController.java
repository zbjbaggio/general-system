package org.general.system.admin.controller.advice;

import com.auth0.jwt.JWT;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.general.system.admin.data.vo.CatpchaVO;
import org.general.system.common.data.dto.SystemUserDTO;
import org.general.system.common.data.vo.SystemUserVO;
import org.general.system.common.service.system.SystemUserService;
import org.general.system.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private Producer kaptchaProducer;

    @GetMapping(value = "/captcha")
    public CatpchaVO captcha() throws IOException {
        // 生成验证码
        CatpchaVO catpchaVO = new CatpchaVO();
        catpchaVO.setToken(JwtUtil.sign("", UUID.randomUUID().toString()));
        String text = kaptchaProducer.createText();
        // TODO redis 缓存
        BufferedImage image = kaptchaProducer.createImage(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            catpchaVO.setCaptcha("data:image/jpeg;base64," + png_base64);
        } catch (IOException e) {
            log.error("响应验证码失败:" + e.getMessage());
        }
        finally {
            baos.close();
        }
        return catpchaVO;
    }

    /**
     * 登录接口
     *
     * @param systemUserDTO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public SystemUserVO login(@RequestBody @Validated(SystemUserDTO.LoginGroup.class) SystemUserDTO systemUserDTO, HttpServletRequest request, BindingResult bindingResult) {
        return systemUserService.login(systemUserDTO);
    }


}
