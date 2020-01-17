package com.xyz.modules.biz.dao;

import com.xyz.modules.biz.entity.BizAreaEntity;
import com.xyz.modules.biz.entity.BizAreaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-23 20:00:17
 */
@Mapper
public interface BizAreaDao extends BaseMapper<BizAreaEntity> {
    List<BizAreaEntity> selectArea();
}
