/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.sys.controller;

import com.xyz.modules.sys.service.SysRoleMenuService;
import com.xyz.modules.sys.service.SysRoleService;
import com.xyz.common.annotation.SysLog;
import com.xyz.common.constant.Constants;
import com.xyz.common.base.PageDataVO;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.validator.ValidatorUtils;
import com.xyz.modules.sys.model.entity.SysRoleEntity;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:role:list")
	public ResponseVO list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
		if(getUserId() != Constants.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}

		PageDataVO page = sysRoleService.queryPage(params);

		return ResponseVO.ok().put("page", page);
	}

	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:role:select")
	public ResponseVO select(){
		Map<String, Object> map = new HashMap<>();

		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != Constants.SUPER_ADMIN){
			map.put("create_user_id", getUserId());
		}
		List<SysRoleEntity> list = (List<SysRoleEntity>) sysRoleService.listByMap(map);

		return ResponseVO.ok().put("list", list);
	}

	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public ResponseVO info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.getById(roleId);

		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		return ResponseVO.ok().put("role", role);
	}

	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	@RequiresPermissions("sys:role:save")
	public ResponseVO save(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateParams(role);

		role.setCreateUserId(getUserId());
		sysRoleService.saveRole(role);

		return ResponseVO.ok();
	}

	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	@RequiresPermissions("sys:role:update")
	public ResponseVO update(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateParams(role);

		role.setCreateUserId(getUserId());
		sysRoleService.update(role);

		return ResponseVO.ok();
	}

	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public ResponseVO delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);

		return ResponseVO.ok();
	}
}
