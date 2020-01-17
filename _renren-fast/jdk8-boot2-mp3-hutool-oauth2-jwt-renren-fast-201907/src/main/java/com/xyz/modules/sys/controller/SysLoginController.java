/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.sys.controller;

import com.xyz.modules.sys.entity.SysUserEntity;
import com.xyz.modules.sys.service.SysCaptchaService;
import com.xyz.modules.sys.service.SysUserService;
import com.xyz.common.base.R;
import com.xyz.modules.sys.entity.SysUserEntity;
import com.xyz.modules.sys.model.query.SysLoginFormQuery;
import com.xyz.modules.sys.service.SysCaptchaService;
import com.xyz.modules.sys.service.SysUserService;
import com.xyz.modules.sys.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags = "系统登录")
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
    @ApiOperation("获取验证码")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

    /**
     * 验证码
     */
    @GetMapping("captcha")
    @ApiOperation("获取验证码")
    @ResponseBody
    public R captchaBase64(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 写入输出流
        ImageIO.write(image, "jpg", outputStream);
        // 转成base64
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(outputStream.toByteArray());
        String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");

        // 及时关闭
        if (outputStream != null) {
            outputStream.close();
        }
//		IOUtils.closeQuietly(outputStream);

        return R.ok().put("data", captchaBase64);
    }


	/**
	 * 登录
	 */
	@ApiOperation("用户登录")
	@PostMapping("/sys/login")
	public R login(@RequestBody SysLoginFormQuery form)throws IOException {
		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
		if(!captcha){
			return R.error("验证码不正确");
		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}

}
