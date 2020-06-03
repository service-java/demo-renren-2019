/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.app.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.common.base.exception.BaseException;
import com.xyz.common.validator.Assert;
import com.xyz.modules.app.dao.UserDao;
import com.xyz.modules.app.model.entity.UserEntity;
import com.xyz.modules.app.model.query.LoginFormQuery;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService extends ServiceImpl<UserDao, UserEntity> {

	public UserEntity queryByMobile(String mobile) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
	}

	public long login(LoginFormQuery form) {
		UserEntity user = queryByMobile(form.getMobile());
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new BaseException("手机号或密码错误");
		}

		return user.getUserId();
	}
}
