package io.renren.modules.demo.controller;

import io.renren.common.base.R;
import io.renren.common.base.exception.RRException;
import io.renren.common.util.file.FileUploadUtils;
import io.renren.common.util.file.FileUtils;
import io.renren.config.properties.LocalStorageProperties;
import io.renren.modules.oss.entity.SysOssEntity;
import io.renren.modules.oss.service.SysOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Api(tags = "通用接口")
@Controller
@RequestMapping("/common")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);


    @Autowired
    private SysOssService sysOssService;

    @ApiOperation(value = "公共下载", httpMethod = "GET")
    @RequestMapping("/download")
    public void download(String fileName, HttpServletResponse response, HttpServletRequest request) {
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

    @ApiOperation("本地上传")
    @PostMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        // 上传文件路径(真实存储路径)
        String filePath = LocalStorageProperties.getUploadPath();

        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(fileName).setCreateDate(new Date());
        sysOssService.save(ossEntity);
        return R.ok().put("data", fileName);
    }


    /**
     * Tika判断文件类型（可正确判断） https://blog.csdn.net/bingguang1993/article/details/86692332
     * 常用文件的mime和mimetype的对应关系 https://blog.csdn.net/bingguang1993/article/details/86687748
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ApiOperation("判断文件类型")
    @PostMapping("/detectFileType")
    @ResponseBody
    public R getFileType(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        Tika tika = new Tika();
        String fileType = tika.detect(file.getInputStream());

        return R.ok().put("data", fileType);
    }

}
