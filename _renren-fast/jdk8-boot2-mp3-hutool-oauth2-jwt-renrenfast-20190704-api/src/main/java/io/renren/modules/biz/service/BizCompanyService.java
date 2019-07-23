package io.renren.modules.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.util.PageUtils;
import io.renren.modules.biz.entity.BizCompanyEntity;


import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-23 20:00:17
 */
public interface BizCompanyService extends IService<BizCompanyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

