package io.renren.modules.biz.service;

import io.renren.common.base.Query;
import io.renren.common.util.PageUtils;
import io.renren.modules.biz.dao.BizAreaDao;
import io.renren.modules.biz.entity.BizAreaEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



import io.renren.modules.biz.service.BizAreaService;


@Service("bizAreaService")
public class BizAreaService extends ServiceImpl<BizAreaDao, BizAreaEntity> {


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizAreaEntity> page = this.page(
                new Query<BizAreaEntity>().getPage(params),
                new QueryWrapper<BizAreaEntity>()
        );

        return new PageUtils(page);
    }

}
