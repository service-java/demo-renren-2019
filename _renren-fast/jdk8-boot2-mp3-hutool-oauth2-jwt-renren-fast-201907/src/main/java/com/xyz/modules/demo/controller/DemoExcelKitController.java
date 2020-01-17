package com.xyz.modules.demo.controller;

import cn.hutool.core.lang.Console;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import com.xyz.modules.sys.entity.SysUserEntity;
import com.xyz.modules.sys.service.SysUserService;
import com.xyz.common.base.R;
import com.xyz.common.base.exception.RRException;
import com.xyz.modules.sys.entity.SysUserEntity;
import com.xyz.modules.sys.model.dto.ExcelKitUserDTO;
import com.xyz.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 示例请求处理
 *
 * @author ruoyi
 */
@Api(tags = "示例接口")
@Controller
@RequestMapping("/demo/excelkit")
public class DemoExcelKitController {
    @Autowired
    private SysUserService sysUserService;


    @Deprecated
    @ApiOperation(value = "excelkit-模板下载", httpMethod = "GET")
    @RequestMapping("/export/template")
    public void exportTemplate(HttpServletResponse response) {

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
    @PostMapping("/import/user")
    @ResponseBody
    public R importUser(@RequestParam MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();

        ArrayList<ExcelKitUserDTO> userList = new ArrayList<>();
        ExcelKit.$Import(ExcelKitUserDTO.class)
                .readXlsx(inputStream, new ExcelReadHandler<ExcelKitUserDTO>() {
                    @Override
                    public void onSuccess(int sheetIndex, int rowIndex, ExcelKitUserDTO entity) {
                        Console.log("表{} 行{}: {}", sheetIndex, rowIndex, entity);
                        userList.add(entity);
                    }

                    @Override
                    public void onError(int sheetIndex, int rowIndex, List<ExcelErrorField> errorFields) {
                        Console.log(errorFields);
                    }
                });

        return R.ok().put("list", userList);
    }

    @Deprecated
    @ApiOperation(value = "excelkit-导出用户", httpMethod = "GET")
    @GetMapping("/export/user")
    public void exportUser(HttpServletResponse response) {
        try {
            List<SysUserEntity> list = sysUserService.list();

//            List<ExcelKitUserDTO> excelUserList = new ArrayList<>();
//            for (SysUserEntity s : list) {
//                ExcelKitUserDTO eu = new ExcelKitUserDTO();
//                BeanUtils.copyBeanProp(eu, s);
//                excelUserList.add(eu);
//            }

            ExcelKit.$Export(SysUserEntity.class, response).downXlsx(list, false);

        } catch (Exception e) {
            throw new RRException("文件导出失败");
        }
    }


}
