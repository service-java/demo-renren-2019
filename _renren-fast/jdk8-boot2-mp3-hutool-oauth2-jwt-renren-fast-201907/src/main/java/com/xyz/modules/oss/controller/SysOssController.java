/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.oss.controller;

import com.google.gson.Gson;
import com.xyz.common.base.exception.BaseException;
import com.xyz.common.constant.ConfigConstants;
import com.xyz.common.util.file.FileUploadUtils;
import com.xyz.common.validator.group.AliyunGroup;
import com.xyz.common.validator.group.QcloudGroup;
import com.xyz.common.validator.group.QiniuGroup;
import com.xyz.modules.oss.entity.SysOssEntity;
import com.xyz.modules.oss.service.SysOssService;
import com.xyz.modules.sys.service.SysConfigService;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.constant.Constants;
import com.xyz.common.base.PageDataVO;
import com.xyz.common.validator.ValidatorUtils;
import com.xyz.config.property.LocalStorageProperties;
import com.xyz.modules.oss.cloud.CloudStorageConfig;
import com.xyz.modules.oss.cloud.OSSFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags="OSS服务")
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstants.CLOUD_STORAGE_CONFIG_KEY;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public ResponseVO list(@RequestParam Map<String, Object> params){
		PageDataVO page = sysOssService.queryPage(params);

		return ResponseVO.ok().put("page", page);
	}


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public ResponseVO config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return ResponseVO.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@PostMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public ResponseVO saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constants.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constants.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constants.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

		return ResponseVO.ok();
	}


	/**
	 * 上传文件
	 */
	@PostMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public ResponseVO upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new BaseException("上传文件不能为空");
		}

		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);

		return ResponseVO.ok().put("url", url);
	}


    @ApiOperation("本地上传")
    @PostMapping("/upload/local")
    @ResponseBody
    @RequiresPermissions("sys:oss:all")
    public ResponseVO localUpload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BaseException("上传文件不能为空");
        }

        // 上传文件路径(真实存储路径)
        String filePath = LocalStorageProperties.getUploadPath();

        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);

        // 保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(fileName).setCreateDate(new Date());
        sysOssService.save(ossEntity);

        return ResponseVO.ok().put("data", fileName);
    }



    /**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public ResponseVO delete(@RequestBody Long[] ids){
		sysOssService.removeByIds(Arrays.asList(ids));

		return ResponseVO.ok();
	}

}
