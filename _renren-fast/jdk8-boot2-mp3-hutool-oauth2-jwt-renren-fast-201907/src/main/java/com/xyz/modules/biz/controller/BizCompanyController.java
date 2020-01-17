package com.xyz.modules.biz.controller;

import java.util.Arrays;
import java.util.Map;

import com.xyz.common.base.R;
import com.xyz.common.util.PageUtils;
import com.xyz.modules.biz.entity.BizCompanyEntity;
import com.xyz.modules.biz.service.BizCompanyService;
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
@RequestMapping("biz/company")
public class BizCompanyController {
    @Autowired
    private BizCompanyService bizCompanyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("biz:company:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizCompanyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{companyId}")
    @RequiresPermissions("biz:company:info")
    public R info(@PathVariable("companyId") Long companyId){
		BizCompanyEntity bizCompany = bizCompanyService.getById(companyId);

        return R.ok().put("bizCompany", bizCompany);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("biz:company:save")
    public R save(@RequestBody BizCompanyEntity bizCompany){
		bizCompanyService.save(bizCompany);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("biz:company:update")
    public R update(@RequestBody BizCompanyEntity bizCompany){
		bizCompanyService.updateById(bizCompany);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("biz:company:delete")
    public R delete(@RequestBody Long[] companyIds){
		bizCompanyService.removeByIds(Arrays.asList(companyIds));

        return R.ok();
    }

}
