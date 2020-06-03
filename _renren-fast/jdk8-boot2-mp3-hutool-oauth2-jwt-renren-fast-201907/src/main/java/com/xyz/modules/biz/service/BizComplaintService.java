package com.xyz.modules.biz.service;

import com.xyz.common.base.PageQuery;
import com.xyz.common.base.PageUtils;
import com.xyz.modules.biz.dao.BizComplaintDao;
import com.xyz.modules.biz.entity.BizComplaintEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("bizComplaintService")
public class BizComplaintService extends ServiceImpl<BizComplaintDao, BizComplaintEntity>  {


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizComplaintEntity> page = this.page(
                new PageQuery<BizComplaintEntity>().getPage(params),
                new QueryWrapper<BizComplaintEntity>()
        );

        return new PageUtils(page);
    }

}
