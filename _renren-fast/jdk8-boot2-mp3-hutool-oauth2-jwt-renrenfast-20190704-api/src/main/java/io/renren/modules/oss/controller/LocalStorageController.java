package io.renren.modules.oss.controller;

import io.renren.common.base.R;
import io.renren.common.base.exception.RRException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 模块功能
 * User: luo0412
 * Date: 2019-07-19
 * Time: 17:24
 */
@RestController
@RequestMapping("sys/local")
public class LocalStorageController {

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        return R.ok();
    }


}
