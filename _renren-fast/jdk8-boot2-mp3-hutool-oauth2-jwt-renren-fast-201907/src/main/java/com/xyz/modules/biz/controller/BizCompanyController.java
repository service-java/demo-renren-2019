package com.xyz.modules.biz.controller;

import java.util.Arrays;
import java.util.Map;

import com.xyz.common.base.ResponseVO;
import com.xyz.common.base.PageDataVO;
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
    public ResponseVO list(@RequestParam Map<String, Object> params){
        PageDataVO page = bizCompanyService.queryPage(params);

        return ResponseVO.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{companyId}")
    @RequiresPermissions("biz:company:info")
    public ResponseVO info(@PathVariable("companyId") Long companyId){
		BizCompanyEntity bizCompany = bizCompanyService.getById(companyId);

        return ResponseVO.ok().put("bizCompany", bizCompany);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("biz:company:save")
    public ResponseVO save(@RequestBody BizCompanyEntity bizCompany){
		bizCompanyService.save(bizCompany);

        return ResponseVO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("biz:company:update")
    public ResponseVO update(@RequestBody BizCompanyEntity bizCompany){
		bizCompanyService.updateById(bizCompany);

        return ResponseVO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("biz:company:delete")
    public ResponseVO delete(@RequestBody Long[] companyIds){
		bizCompanyService.removeByIds(Arrays.asList(companyIds));

        return ResponseVO.ok();
    }

}
