package com.xyz.modules.biz.service;

import com.xyz.common.base.Query;
import com.xyz.common.util.PageUtils;
import com.xyz.modules.biz.dao.BizAreaDao;
import com.xyz.modules.biz.entity.BizAreaEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



import com.xyz.modules.biz.service.BizAreaService;


@Service("bizAreaService")
public class BizAreaService extends ServiceImpl<BizAreaDao, BizAreaEntity> {


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizAreaEntity> page = this.page(
                new Query<BizAreaEntity>().getPage(params),
                new QueryWrapper<BizAreaEntity>()
        );

        return new PageUtils(page);
    }

    public List<BizAreaEntity> listArea() {
        return baseMapper.selectArea();
    }
}
