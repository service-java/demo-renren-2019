/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.oss.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.common.base.PageDataVO;
import com.xyz.common.base.PageQueryUtils;
import com.xyz.modules.oss.dao.SysOssDao;
import com.xyz.modules.oss.entity.SysOssEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysOssService")
public class SysOssService extends ServiceImpl<SysOssDao, SysOssEntity> {

	public PageDataVO queryPage(Map<String, Object> params) {
		IPage<SysOssEntity> page = this.page(
			new PageQueryUtils<SysOssEntity>().getPage(params)
		);

		return new PageDataVO(page);
	}

}
