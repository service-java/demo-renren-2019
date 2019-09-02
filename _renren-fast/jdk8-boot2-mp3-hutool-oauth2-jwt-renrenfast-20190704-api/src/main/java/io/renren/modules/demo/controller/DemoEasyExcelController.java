package io.renren.modules.demo.controller;

import cn.hutool.core.lang.Console;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import io.renren.common.base.R;
import io.renren.common.base.exception.RRException;
import io.renren.common.util.BeanUtils;
import io.renren.common.util.excel.EasyExcelListener;
import io.renren.common.util.excel.EasyExcelUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.model.dto.EasyExcelUserDTO;
import io.renren.modules.sys.service.SysUserService;
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
 * 通用请求处理
 *
 * @author ruoyi
 */
@Api(tags = "通用接口")
@Controller
@RequestMapping("/common/easyexcel")
public class DemoEasyExcelController {
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "easyexcel-导入用户", httpMethod = "POST")
    @PostMapping("/import/user")
    @ResponseBody
    public R importEasyExcelUser(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
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

        return R.ok().put("list", data);
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
