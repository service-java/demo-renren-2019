package com.xyz.modules.biz.service;

import com.xyz.common.base.Query;
import com.xyz.common.util.PageUtils;
import com.xyz.modules.biz.dao.BizCompanyDao;
import com.xyz.modules.biz.entity.BizCompanyEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;




import com.xyz.modules.biz.service.BizCompanyService;


@Service("bizCompanyService")
public class BizCompanyService extends ServiceImpl<BizCompanyDao, BizCompanyEntity>  {


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizCompanyEntity> page = this.page(
                new Query<BizCompanyEntity>().getPage(params),
                new QueryWrapper<BizCompanyEntity>()
        );

        return new PageUtils(page);
    }

}
