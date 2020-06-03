package com.xyz.modules.biz.service;

import com.xyz.common.base.PageQuery;
import com.xyz.common.base.PageUtils;
import com.xyz.modules.biz.dao.BizAreaDao;
import com.xyz.modules.biz.entity.BizAreaEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("bizAreaService")
public class BizAreaService extends ServiceImpl<BizAreaDao, BizAreaEntity> {


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizAreaEntity> page = this.page(
                new PageQuery<BizAreaEntity>().getPage(params),
                new QueryWrapper<BizAreaEntity>()
        );

        return new PageUtils(page);
    }

    public List<BizAreaEntity> listArea() {
        return baseMapper.selectArea();
    }
}
