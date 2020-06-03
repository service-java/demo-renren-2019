package com.xyz.modules.biz.controller;

import java.util.Arrays;
import java.util.Map;

import com.xyz.modules.biz.entity.BizComplaintEntity;
import com.xyz.modules.biz.service.BizComplaintService;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.base.PageDataVO;
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
@RequestMapping("biz/complaint")
public class BizComplaintController {
    @Autowired
    private BizComplaintService bizComplaintService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("biz:complaint:list")
    public ResponseVO list(@RequestParam Map<String, Object> params){
        PageDataVO page = bizComplaintService.queryPage(params);

        return ResponseVO.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{complaintId}")
    @RequiresPermissions("biz:complaint:info")
    public ResponseVO info(@PathVariable("complaintId") Long complaintId){
		BizComplaintEntity bizComplaint = bizComplaintService.getById(complaintId);

        return ResponseVO.ok().put("bizComplaint", bizComplaint);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("biz:complaint:save")
    public ResponseVO save(@RequestBody BizComplaintEntity bizComplaint){
		bizComplaintService.save(bizComplaint);

        return ResponseVO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("biz:complaint:update")
    public ResponseVO update(@RequestBody BizComplaintEntity bizComplaint){
		bizComplaintService.updateById(bizComplaint);

        return ResponseVO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("biz:complaint:delete")
    public ResponseVO delete(@RequestBody Long[] complaintIds){
		bizComplaintService.removeByIds(Arrays.asList(complaintIds));

        return ResponseVO.ok();
    }

}
