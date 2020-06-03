/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.job.controller;

import com.xyz.common.aop.annotation.SysLog;
import com.xyz.modules.job.service.ScheduleJobService;
import com.xyz.common.base.PageUtils;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.validator.ValidatorUtils;
import com.xyz.modules.job.entity.ScheduleJobEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:list")
	public ResponseVO list(@RequestParam Map<String, Object> params){
		PageUtils page = scheduleJobService.queryPage(params);

		return ResponseVO.ok().put("page", page);
	}

	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("sys:schedule:info")
	public ResponseVO info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.getById(jobId);

		return ResponseVO.ok().put("schedule", schedule);
	}

	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@RequestMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	public ResponseVO save(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);

		scheduleJobService.saveJob(scheduleJob);

		return ResponseVO.ok();
	}

	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@RequestMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	public ResponseVO update(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);

		scheduleJobService.update(scheduleJob);

		return ResponseVO.ok();
	}

	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	public ResponseVO delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);

		return ResponseVO.ok();
	}

	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	public ResponseVO run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);

		return ResponseVO.ok();
	}

	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	public ResponseVO pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);

		return ResponseVO.ok();
	}

	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	public ResponseVO resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);

		return ResponseVO.ok();
	}

}
