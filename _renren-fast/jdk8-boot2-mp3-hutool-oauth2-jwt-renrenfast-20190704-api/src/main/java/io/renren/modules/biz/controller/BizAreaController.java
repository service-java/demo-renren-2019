package io.renren.modules.biz.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.base.R;
import io.renren.common.util.PageUtils;
import io.renren.modules.biz.entity.BizAreaEntity;
import io.renren.modules.biz.service.BizAreaService;
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
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizAreaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{areaId}")
    @RequiresPermissions("biz:area:info")
    public R info(@PathVariable("areaId") Long areaId){
		BizAreaEntity bizArea = bizAreaService.getById(areaId);

        return R.ok().put("bizArea", bizArea);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("biz:area:save")
    public R save(@RequestBody BizAreaEntity bizArea){
		bizAreaService.save(bizArea);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("biz:area:update")
    public R update(@RequestBody BizAreaEntity bizArea){
		bizAreaService.updateById(bizArea);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("biz:area:delete")
    public R delete(@RequestBody Long[] areaIds){
		bizAreaService.removeByIds(Arrays.asList(areaIds));

        return R.ok();
    }

}
