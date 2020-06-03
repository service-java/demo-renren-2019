/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.modules.sys.model.entity.SysUserTokenEntity;
import com.xyz.modules.sys.oauth2.TokenGenerator;
import com.xyz.common.base.ResponseVO;
import com.xyz.modules.sys.dao.SysUserTokenDao;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenService extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;



	public ResponseVO createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		// 判断是否生成过token
		SysUserTokenEntity tokenEntity = this.getById(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId).setToken(token).setUpdateTime(now).setExpireTime(expireTime);

			//保存token
			this.save(tokenEntity);

		} else {
			tokenEntity.setToken(token).setUpdateTime(now).setExpireTime(expireTime);

			//更新token
			this.updateById(tokenEntity);
		}

		ResponseVO r = ResponseVO.ok().put("token", token).put("expire", EXPIRE);

		return r;
	}


	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId).setToken(token);
		this.updateById(tokenEntity);
	}
}
