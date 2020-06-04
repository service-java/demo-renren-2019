/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.sys.controller;

import com.xyz.modules.sys.model.entity.SysUserEntity;
import com.xyz.modules.sys.service.SysUserRoleService;
import com.xyz.modules.sys.service.SysUserService;
import com.xyz.common.annotation.SysLog;
import com.xyz.common.constant.Constants;
import com.xyz.common.base.PageDataVO;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.validator.Assert;
import com.xyz.common.validator.ValidatorUtils;
import com.xyz.common.validator.group.AddGroup;
import com.xyz.common.validator.group.UpdateGroup;
import com.xyz.modules.sys.model.query.PasswordFormQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags = "系统用户")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


	@ApiOperation("所有用户列表")
    @GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ResponseVO list(@RequestParam Map<String, Object> params){
		// @TODO 只有超级管理员，才能查看所有管理员列表, 普通管理员只能查看自己创建的管理员
		if (getUserId() != Constants.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}

		PageDataVO page = sysUserService.pageUser(params);
		return ResponseVO.ok().put("page", page);
	}


	@ApiOperation("获取登录的用户信息")
	@GetMapping("/info")
	public ResponseVO info(){
		return ResponseVO.ok().put("user", getUser());
	}


	@ApiOperation("修改密码")
	@SysLog("修改密码")
	@PostMapping("/password")
	public ResponseVO password(@RequestBody PasswordFormQuery form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");

		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return ResponseVO.error("原密码不正确");
		}

		return ResponseVO.ok();
	}


	@ApiOperation("用户信息")
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public ResponseVO info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		return ResponseVO.ok().put("user", user);
	}


	@ApiOperation("保存用户")
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public ResponseVO save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateParams(user, AddGroup.class);

		// 更新创建管理员ID
		user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);

		return ResponseVO.ok();
	}


	@ApiOperation("修改用户")
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public ResponseVO update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateParams(user, UpdateGroup.class);

		// 更新修改者
		user.setCreateUserId(getUserId());
		sysUserService.update(user);

		return ResponseVO.ok();
	}

	@ApiOperation("删除用户")
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public ResponseVO delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return ResponseVO.error("系统管理员不能删除");
		}

		if(ArrayUtils.contains(userIds, getUserId())){
			return ResponseVO.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		return ResponseVO.ok();
	}
}
