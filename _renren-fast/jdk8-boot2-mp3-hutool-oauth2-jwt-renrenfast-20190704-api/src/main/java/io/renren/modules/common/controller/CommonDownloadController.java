package io.renren.modules.common.controller;

import io.renren.common.util.file.FileUtils;
import io.renren.config.properties.LocalStorageProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Api(tags = "通用接口")
@Controller
@RequestMapping("/common")
public class CommonDownloadController {

    private static final Logger log = LoggerFactory.getLogger(CommonDownloadController.class);

    @ApiOperation(value = "公共下载", httpMethod = "GET")
    @RequestMapping("/download")
    public void download(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {

//            if (!FileUtils.isValidFilename(fileName)) {
//                throw new Exception(StrUtil.format("文件名称({})非法，不允许下载 ", fileName));
//            }

            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = LocalStorageProperties.getProfile() + fileName;


            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());

//            if (delete) {
//                FileUtils.deleteFile(filePath);
//            }

        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }


}
