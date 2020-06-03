package com.xyz.modules.demo.controller;

import cn.hutool.core.lang.Console;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.xyz.modules.sys.model.entity.SysUserEntity;
import com.xyz.modules.sys.service.SysUserService;
import com.xyz.common.base.ResponseVO;
import com.xyz.common.base.exception.BaseException;
import com.xyz.common.util.BeanUtils;
import com.xyz.modules.demo.util.EasyExcelListener;
import com.xyz.modules.demo.util.EasyExcelUtils;
import com.xyz.modules.demo.dto.DemoUserExcelDTO;
import com.xyz.modules.demo.listener.DemoUserExcelListener;
import com.xyz.modules.sys.model.dto.EasyExcelUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 示例请求处理
 *
 * @author ruoyi
 */
@Api(tags = "示例接口")
@Controller
@RequestMapping("/demo/easyexcel")
public class DemoEasyExcelController {
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "easyexcel-导入用户", httpMethod = "POST")
    @PostMapping("/import/user")
    @ResponseBody
    public ResponseVO importEasyExcelUser(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BaseException("上传文件不能为空");
        }

        // List<Object> data = ExcelUtils.readExcel(file, new ExcelUserDTO());

        InputStream inputStream = file.getInputStream();
        InputStream bufferedInputStream = new BufferedInputStream(inputStream);
        EasyExcelListener excelListener = new EasyExcelListener();
        List<Object> data = null;

        // 小于1000行数据
//        data = EasyExcelFactory.read(bufferedInputStream, new Sheet(1, 0));
//        Console.log(data);

        data = EasyExcelFactory.read(bufferedInputStream, new Sheet(2, 1, EasyExcelUserDTO.class));
        Console.log(data);

        // 大于1000行数据
//        EasyExcelFactory.readBySax(bufferedInputStream, new Sheet(1, 0), excelListener);
//        data = excelListener.getData();
//        Console.log(data);

//        EasyExcelFactory.readBySax(bufferedInputStream, new Sheet(2, 1, EasyExcelUserDTO.class), excelListener);
//        data = excelListener.getData();
//        Console.log(data);

        return ResponseVO.ok().put("list", data);
    }


    @ApiOperation(value = "easyexcel-导入用户-v2", httpMethod = "POST")
    @PostMapping("/import/v2/user")
    @ResponseBody
    public ResponseVO importV2EasyExcelUser(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BaseException("上传文件不能为空");
        }

        DemoUserExcelListener excelListener = new DemoUserExcelListener();
        EasyExcel.read(file.getInputStream(), DemoUserExcelDTO.class, excelListener).sheet(0).doRead();
        List<DemoUserExcelDTO> list = excelListener.getList();

        return ResponseVO.ok().put("data", list);
    }



    @ApiOperation(value = "easyexcel-导出用户", httpMethod = "GET")
    @GetMapping("/export/user")
    public void exportEasyExcelUser(HttpServletResponse response, HttpServletRequest request) throws Exception {

        List<SysUserEntity> list = sysUserService.list();

        List<EasyExcelUserDTO> excelUserList = new ArrayList<>();
        for (SysUserEntity s : list) {
            EasyExcelUserDTO eu = new EasyExcelUserDTO();
            BeanUtils.copyBeanProp(eu, s);
            excelUserList.add(eu);
        }

        String fileName = "用户信息excel";
        String sheetName = "第一个sheet";

        EasyExcelUtils.writeExcel(response, excelUserList, fileName, sheetName, new EasyExcelUserDTO());
    }


    @Test
    public void readEasyExcelLocalXlsx() throws IOException {
        //        ClassPathResource resource = new ClassPathResource("excelTemplate" + File.separator + "2007.xlsx");
//        InputStream inputStream = resource.getInputStream();

//        InputStream inputStream = ExcelUtils.getResourcesFileInputStream("excelTemplate" + File.separator + "2007.xlsx");
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

//        EasyExcelListener excelListener = new EasyExcelListener();

        FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/excelTemplate/user.xlsx"));
        InputStream inputStream = new BufferedInputStream(fileInputStream);
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        Console.log(data);
    }

}
