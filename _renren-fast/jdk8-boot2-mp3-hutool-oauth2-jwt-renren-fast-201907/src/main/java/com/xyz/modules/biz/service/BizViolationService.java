package com.xyz.modules.biz.service;

import com.xyz.common.base.Query;
import com.xyz.common.util.PageUtils;
import com.xyz.modules.biz.dao.BizViolationDao;
import com.xyz.modules.biz.entity.BizViolationEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;




import com.xyz.modules.biz.service.BizViolationService;


@Service("bizViolationService")
public class BizViolationService extends ServiceImpl<BizViolationDao, BizViolationEntity>  {


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizViolationEntity> page = this.page(
                new Query<BizViolationEntity>().getPage(params),
                new QueryWrapper<BizViolationEntity>()
        );

        return new PageUtils(page);
    }

}
