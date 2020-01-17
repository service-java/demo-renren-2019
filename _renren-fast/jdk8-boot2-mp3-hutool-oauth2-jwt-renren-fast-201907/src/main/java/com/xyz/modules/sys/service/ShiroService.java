/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xyz.modules.sys.service;

import com.xyz.common.constant.Constants;
import com.xyz.modules.sys.dao.SysMenuDao;
import com.xyz.modules.sys.dao.SysUserDao;
import com.xyz.modules.sys.dao.SysUserTokenDao;
import com.xyz.modules.sys.entity.SysMenuEntity;
import com.xyz.modules.sys.entity.SysUserEntity;
import com.xyz.modules.sys.entity.SysUserTokenEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroService  {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;


    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == Constants.SUPER_ADMIN) {
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }

        // 用户权限列表(去重)
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList){
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }


    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }


    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
