/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.app.controller;


import com.xyz.modules.app.annotation.LoginUser;
import com.xyz.common.base.ResponseVO;
import com.xyz.modules.app.annotation.Login;
import com.xyz.modules.app.model.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
//@Api("APP测试接口")
public class AppTestController {

    @Login
    @GetMapping("userInfo")
    // @ApiOperation("获取用户信息")
    public ResponseVO userInfo(@LoginUser UserEntity user){
        return ResponseVO.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    // @ApiOperation("获取用户ID")
    public ResponseVO userInfo(@RequestAttribute("userId") Integer userId){
        return ResponseVO.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    // @ApiOperation("忽略Token验证测试")
    public ResponseVO notToken(){
        return ResponseVO.ok().put("msg", "无需token也能访问。。。");
    }

}
