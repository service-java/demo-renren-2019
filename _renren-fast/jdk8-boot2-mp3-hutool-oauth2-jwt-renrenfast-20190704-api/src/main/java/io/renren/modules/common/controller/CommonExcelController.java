package io.renren.modules.common.controller;

import cn.hutool.core.lang.Console;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import io.renren.common.base.R;
import io.renren.common.base.exception.RRException;
import io.renren.common.util.BeanUtils;
import io.renren.common.util.excel.EasyExcelListener;
import io.renren.common.util.excel.EasyExcelUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.model.dto.EasyExcelUserDTO;
import io.renren.modules.sys.model.dto.ExcelKitUserDTO;
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
import java.util.Date;
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
public class CommonExcelController {
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "excelkit-模板下载", httpMethod = "GET")
    @RequestMapping("/excelkit/export/template")
    public void excelTemplate(HttpServletResponse response) {

        // 构建数据
        List<SysUserEntity> list = new ArrayList<>();
        IntStream.range(0, 20).forEach(i -> {
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setUsername("张三").setEmail("1095847440@qq.com").setCreateTime(new Date());
            list.add(sysUserEntity);
        });

        // 构建模板
        ExcelKit.$Export(SysUserEntity.class, response).downXlsx(list, true);
    }

    @Deprecated
    @ApiOperation(value = "excelkit-导入用户", httpMethod = "POST")
    @PostMapping("/excelkit/import/user")
    @ResponseBody
    public R importKitUser(@RequestParam MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();

        ArrayList<ExcelKitUserDTO> userList = new ArrayList<>();
        ExcelKit.$Import(ExcelKitUserDTO.class)
                .readXlsx(inputStream, new ExcelReadHandler<ExcelKitUserDTO>() {
                    @Override
                    public void onSuccess(int sheetIndex, int rowIndex, ExcelKitUserDTO entity) {
                        Console.log("表{} 行{} : {}", sheetIndex, rowIndex, entity);
                        userList.add(entity);
                    }

                    @Override
                    public void onError(int sheetIndex, int rowIndex, List<ExcelErrorField> errorFields) {
                        Console.log(errorFields);
                    }
                });

        return R.ok().put("list", userList);
    }


    @ApiOperation(value = "excelkit-导出用户", httpMethod = "GET")
    @RequestMapping("/excelkit/export/user")
    public void exportUser(HttpServletResponse response) {
        try {
            List<SysUserEntity> list = sysUserService.list();

            List<ExcelKitUserDTO> excelUserList = new ArrayList<>();
            for (SysUserEntity s : list) {
                ExcelKitUserDTO eu = new ExcelKitUserDTO();
                BeanUtils.copyBeanProp(eu, s);
                excelUserList.add(eu);
            }

            ExcelKit.$Export(EasyExcelUserDTO.class, response).downXlsx(excelUserList, false);
        } catch (Exception e) {
            throw new RRException("文件导出失败");
        }
    }


    @ApiOperation(value = "easyexcel-导入用户", httpMethod = "POST")
    @PostMapping("/easyexcel/import/user")
    @ResponseBody
    public R importUser(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        // List<Object> data = ExcelUtils.readExcel(file, new ExcelUserDTO());

        InputStream inputStream = file.getInputStream();
        InputStream bufferedInputStream = new BufferedInputStream(inputStream);
        EasyExcelListener excelListener = new EasyExcelListener();
        List<Object> data = null;

        // 小于1000行数据
//        data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
//        Console.log(data);

//        data = EasyExcelFactory.read(bufferedInputStream, new Sheet(2, 1, ExcelUserDTO.class));
//        Console.log(data);

        // 大于1000行数据
//        EasyExcelFactory.readBySax(bufferedInputStream, new Sheet(1, 0), excelListener);
//        data = excelListener.getData();
//        Console.log(data);

        EasyExcelFactory.readBySax(bufferedInputStream, new Sheet(2, 1, EasyExcelUserDTO.class), excelListener);
        data = excelListener.getData();
        Console.log(data);

        return R.ok().put("list", data);
    }


    @ApiOperation(value = "easyexcel-导出用户", httpMethod = "GET")
    @GetMapping("/easyexcel/export/user")
    public void importUser(HttpServletResponse response, HttpServletRequest request) throws Exception {

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
