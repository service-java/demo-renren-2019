package com.xyz.modules.demo.controller;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xyz.common.base.Query;
import com.xyz.common.base.R;
import com.xyz.common.base.exception.RRException;
import com.xyz.common.util.MapUtils;
import com.xyz.common.util.PageUtils;
import com.xyz.common.util.file.FileUploadUtils;
import com.xyz.common.util.file.FileUtils;
import com.xyz.config.properties.LocalStorageProperties;
import com.xyz.modules.app.service.UserService;
import com.xyz.modules.biz.dao.BizAreaDao;
import com.xyz.modules.biz.entity.BizAreaEntity;
import com.xyz.modules.biz.entity.BizViolationEntity;
import com.xyz.modules.biz.service.BizAreaService;
import com.xyz.modules.oss.entity.SysOssEntity;
import com.xyz.modules.oss.service.SysOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 示例请求处理
 *
 * @author ruoyi
 */
@Api(tags = "示例接口")
@Controller
@RequestMapping("/demo")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);


    @Autowired
    private SysOssService sysOssService;

    @Autowired
    private BizAreaService bizAreaService;

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


    @ApiOperation("测试一番")
    @PostMapping("/test")
    @ResponseBody
    public R getAllArea() {
        BizAreaDao baseMapper = bizAreaService.getBaseMapper();

//        List<BizAreaEntity> area = bizAreaService.listArea();
//        Console.log(area);

//        area = baseMapper.selectList(new QueryWrapper<BizAreaEntity>().eq("is_delete", 0));
//        Console.log(area);

          // 删除演示
//        baseMapper.delete(new QueryWrapper());
//        baseMapper.deleteByMap(new MapUtils().put("id", 0));
//        baseMapper.deleteById(0);

          // 批量删除
//        ArrayList idLists = new ArrayList<>();
//        idLists.add(1);
//        idLists.add(2);
//        // idLists.add("asd");
//        baseMapper.deleteBatchIds(idLists);


        // 插入测试
//        BizAreaEntity bizAreaEntity = new BizAreaEntity();
//        bizAreaEntity.setGmtCreate(new Date()).setGmtModified(new Date());
//        bizAreaEntity.setName("hhhhhhh");
//        int insert = baseMapper.insert(bizAreaEntity);
//        Console.log(insert);
//        // --> 1

        // 查询测试
//        List<BizAreaEntity> list = baseMapper.selectList(null);
//        Console.log(list);

        // lambda查询
        MapUtils query = new MapUtils().put("page", 1).put("limit", 10);
        IPage<BizAreaEntity> page = bizAreaService.page(
            new Query<BizAreaEntity>().getPage(query),
            new QueryWrapper<BizAreaEntity>().lambda()
                    .eq(BizAreaEntity::getIsDelete, 0)
                    .or().eq(BizAreaEntity::getCode, 0)
                    .and(wrapper -> wrapper.like(BizAreaEntity::getName, ""))
        );

        return R.ok().put("data", new PageUtils(page));
    }

}
