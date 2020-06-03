package com.xyz.modules.biz.controller;

import java.util.Arrays;
import java.util.Map;

import com.xyz.modules.biz.entity.BizViolationEntity;
import com.xyz.modules.biz.service.BizViolationService;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.base.PageUtils;
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
@RequestMapping("biz/violation")
public class BizViolationController {
    @Autowired
    private BizViolationService bizViolationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("biz:violation:list")
    public ResponseVO list(@RequestParam Map<String, Object> params){
        PageUtils page = bizViolationService.queryPage(params);

        return ResponseVO.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{violationId}")
    @RequiresPermissions("biz:violation:info")
    public ResponseVO info(@PathVariable("violationId") Long violationId){
		BizViolationEntity bizViolation = bizViolationService.getById(violationId);

        return ResponseVO.ok().put("bizViolation", bizViolation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("biz:violation:save")
    public ResponseVO save(@RequestBody BizViolationEntity bizViolation){
		bizViolationService.save(bizViolation);

        return ResponseVO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("biz:violation:update")
    public ResponseVO update(@RequestBody BizViolationEntity bizViolation){
		bizViolationService.updateById(bizViolation);

        return ResponseVO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("biz:violation:delete")
    public ResponseVO delete(@RequestBody Long[] violationIds){
		bizViolationService.removeByIds(Arrays.asList(violationIds));

        return ResponseVO.ok();
    }

}
