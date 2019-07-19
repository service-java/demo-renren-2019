/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.base.Query;
import io.renren.common.base.exception.RRException;
import io.renren.common.constant.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserService extends ServiceImpl<SysUserDao, SysUserEntity>  {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;


	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		Long createUserId = (Long)params.get("createUserId");

		IPage<SysUserEntity> page = this.page(
			new Query<SysUserEntity>().getPage(params),
			new QueryWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.eq(createUserId != null,"create_user_id", createUserId)
		);

		return new PageUtils(page);
	}


	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}


    /**
     * 当前用户角色roley拥有的菜单
     * @param userId
     * @return
     */
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}


	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}


	@Transactional
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex()).setSalt(salt);
		this.save(user);

		//检查角色是否越权
		checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

    // 支持事务

	@Transactional
	public void update(SysUserEntity user) {
	    // 判断是否更新密码 salt不修改
		if (StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		} else {
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);

		//检查角色是否越权
		checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}


	public void deleteBatch(Long[] userId) {
	    // 两种方法皆可
	    // 1. baseMapper删除
        // baseMapper.deleteBatchIds(Arrays.asList(userId));

        // 2. service删除
        this.removeByIds(Arrays.asList(userId));
	}

	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);

		return this.update(
            userEntity,
            new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password)
        );
	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){

		if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
			return;
		}
        // if (CollUtil.isEmpty(user.getRoleIdList())) {
        //	   return;
        // }

		// 如果不是超级管理员，则需要判断用户的角色是否自己创建
		if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
			return ;
		}

		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if (!roleIdList.containsAll(user.getRoleIdList())) {
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}
}
