package com.xyz.modules.biz.controller;

import java.util.Arrays;
import java.util.Map;

import com.xyz.common.base.ResponseVO;
import com.xyz.common.base.PageUtils;
import com.xyz.modules.biz.entity.BizAreaEntity;
import com.xyz.modules.biz.service.BizAreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-23 20:00:17
 */
@RestController
@RequestMapping("biz/area")
public class BizAreaController {
    @Autowired
    private BizAreaService bizAreaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("biz:area:list")
    public ResponseVO list(@RequestParam Map<String, Object> params){
        PageUtils page = bizAreaService.queryPage(params);

        return ResponseVO.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{areaId}")
    @RequiresPermissions("biz:area:info")
    public ResponseVO info(@PathVariable("areaId") Long areaId){
		BizAreaEntity bizArea = bizAreaService.getById(areaId);

        return ResponseVO.ok().put("bizArea", bizArea);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("biz:area:save")
    public ResponseVO save(@RequestBody BizAreaEntity bizArea){
		bizAreaService.save(bizArea);

        return ResponseVO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("biz:area:update")
    public ResponseVO update(@RequestBody BizAreaEntity bizArea){
		bizAreaService.updateById(bizArea);

        return ResponseVO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("biz:area:delete")
    public ResponseVO delete(@RequestBody Long[] areaIds){
		bizAreaService.removeByIds(Arrays.asList(areaIds));

        return ResponseVO.ok();
    }

}
