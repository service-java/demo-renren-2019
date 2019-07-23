package io.renren.modules.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.util.PageUtils;
import io.renren.modules.biz.entity.BizViolationEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-23 20:00:17
 */
public interface BizViolationService extends IService<BizViolationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

