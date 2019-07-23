package io.renren.modules.biz.service.impl;

import io.renren.common.base.Query;
import io.renren.common.util.PageUtils;
import io.renren.modules.biz.dao.BizCompanyDao;
import io.renren.modules.biz.entity.BizCompanyEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;




import io.renren.modules.biz.service.BizCompanyService;


@Service("bizCompanyService")
public class BizCompanyServiceImpl extends ServiceImpl<BizCompanyDao, BizCompanyEntity> implements BizCompanyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizCompanyEntity> page = this.page(
                new Query<BizCompanyEntity>().getPage(params),
                new QueryWrapper<BizCompanyEntity>()
        );

        return new PageUtils(page);
    }

}
