package io.renren.modules.common.controller;

import cn.hutool.core.lang.Console;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.wuwenze.poi.ExcelKit;
import io.renren.common.base.R;
import io.renren.common.base.exception.RRException;
import io.renren.common.util.BeanUtils;
import io.renren.common.util.excel.ExcelUtils;
import io.renren.common.util.file.FileUtils;
import io.renren.config.properties.LocalStorageProperties;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.model.dto.ExcelUserDTO;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Api(tags = "通用接口")
@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private SysUserService sysUserService;

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

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

    /**
     * 生成 Excel导入模板
     */
    @ApiOperation(value = "Excel模板下载", httpMethod = "GET")
    @RequestMapping("/excelTemplate")
    public void excelTemplate(HttpServletResponse response) {
        // 构建数据
        List<SysUserEntity> list = new ArrayList<>();
        IntStream.range(0, 20).forEach(i -> {
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setUsername("张三");
            list.add(sysUserEntity);
        });

        // 构建模板
        ExcelKit.$Export(SysUserEntity.class, response).downXlsx(list, true);
    }



    /**
     * 导出 Excel
     */
    @ApiOperation(value = "导出用户EXCEL", httpMethod = "GET")
    @RequestMapping("/exportXls/user")
    public void exportUser(HttpServletResponse response) {
        try {
            List<SysUserEntity> list = sysUserService.list();
            ExcelKit.$Export(SysUserEntity.class, response).downXlsx(list, false);
        } catch (Exception e) {
            throw new RRException("文件导出失败");
        }
    }


    @Test
    public void readLocalXLs () throws IOException {
        //        ClassPathResource resource = new ClassPathResource("excelTemplate" + File.separator + "2007.xlsx");
//        InputStream inputStream = resource.getInputStream();

//        InputStream inputStream = ExcelUtils.getResourcesFileInputStream("excelTemplate" + File.separator + "2007.xlsx");
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

//        EasyExcelListener excelListener = new EasyExcelListener();

        InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("src/main/resources/excelTemplate/user.xlsx")));
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        Console.log(data);
    }



    @ApiOperation(value = "导入用户", httpMethod = "POST")
    @PostMapping("/importXls/user")
    public R importUser(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        List<Object> data = ExcelUtils.readExcel(file, new BaseRowModel());
        Console.log(data);

        return R.ok();
    }


    @ApiOperation(value = "下载", httpMethod = "GET")
    @GetMapping("/exportXls/local")
    public void importUser(HttpServletResponse response, HttpServletRequest request) throws Exception {

        List<SysUserEntity> list = sysUserService.list();
        List<ExcelUserDTO> excelUserList = new ArrayList<>();
        for (SysUserEntity s: list) {
            ExcelUserDTO eu = new ExcelUserDTO();
            BeanUtils.copyBeanProp(eu, s);
            excelUserList.add(eu);
        }

        String fileName = "用户信息excel";
        String sheetName = "第一个sheet";

        ExcelUtils.writeExcel(response, excelUserList, fileName, sheetName, new ExcelUserDTO());
    }

}
