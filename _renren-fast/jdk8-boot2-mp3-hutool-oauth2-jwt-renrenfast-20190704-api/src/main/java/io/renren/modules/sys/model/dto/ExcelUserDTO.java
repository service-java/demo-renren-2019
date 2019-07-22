package io.renren.modules.sys.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
public class ExcelUserDTO extends BaseRowModel {

    @ExcelProperty(value = "用户名", index = 0)
    private String username;

    @ExcelProperty(value = "手机号", index = 1)
    private String mobile;

    @ExcelProperty(value = "邮件", index = 2)
    private String email;

    @ExcelProperty(value="创建时间", index = 3, format = "yyyy/MM/dd")
    private Date createTime;

}
