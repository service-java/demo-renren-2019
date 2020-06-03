/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.sys.controller;


import com.xyz.modules.sys.service.SysConfigService;
import com.xyz.common.aop.annotation.SysLog;
import com.xyz.common.util.PageUtils;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.validator.ValidatorUtils;
import com.xyz.modules.sys.model.entity.SysConfigEntity;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags = "系统配置信息")
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 所有配置列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:config:list")
	public ResponseVO list(@RequestParam Map<String, Object> params){
		PageUtils page = sysConfigService.queryPage(params);

		return ResponseVO.ok().put("page", page);
	}


	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public ResponseVO info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigService.getById(id);

		return ResponseVO.ok().put("config", config);
	}

	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@PostMapping("/save")
	@RequiresPermissions("sys:config:save")
	public ResponseVO save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.saveConfig(config);

		return ResponseVO.ok();
	}

	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@PostMapping("/update")
	@RequiresPermissions("sys:config:update")
	public ResponseVO update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.update(config);

		return ResponseVO.ok();
	}

	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@PostMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public ResponseVO delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);

		return ResponseVO.ok();
	}

}
