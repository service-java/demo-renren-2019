package io.renren.modules.biz.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.base.R;
import io.renren.common.util.PageUtils;
import io.renren.modules.biz.entity.BizViolationEntity;
import io.renren.modules.biz.service.BizViolationService;
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
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizViolationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{violationId}")
    @RequiresPermissions("biz:violation:info")
    public R info(@PathVariable("violationId") Long violationId){
		BizViolationEntity bizViolation = bizViolationService.getById(violationId);

        return R.ok().put("bizViolation", bizViolation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("biz:violation:save")
    public R save(@RequestBody BizViolationEntity bizViolation){
		bizViolationService.save(bizViolation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("biz:violation:update")
    public R update(@RequestBody BizViolationEntity bizViolation){
		bizViolationService.updateById(bizViolation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("biz:violation:delete")
    public R delete(@RequestBody Long[] violationIds){
		bizViolationService.removeByIds(Arrays.asList(violationIds));

        return R.ok();
    }

}
