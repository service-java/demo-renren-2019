package io.renren.modules.biz.service;

import io.renren.common.base.Query;
import io.renren.common.util.PageUtils;
import io.renren.modules.biz.dao.BizComplaintDao;
import io.renren.modules.biz.entity.BizComplaintEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;




import io.renren.modules.biz.service.BizComplaintService;


@Service("bizComplaintService")
public class BizComplaintService extends ServiceImpl<BizComplaintDao, BizComplaintEntity>  {


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BizComplaintEntity> page = this.page(
                new Query<BizComplaintEntity>().getPage(params),
                new QueryWrapper<BizComplaintEntity>()
        );

        return new PageUtils(page);
    }

}
