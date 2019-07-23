package io.renren.modules.sys.model.dto;

import com.alibaba.excel.metadata.BaseRowModel;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.util.Date;

@Data
@Excel("系统用户")
public class ExcelKitUserDTO extends BaseRowModel {

    @ExcelField(value = "编号")
    private Long userId;

    @ExcelField(
            value = "用户名",
//        required = true,
            maxLength = 20,
            comment = "提示：必填，长度不能超过20个字符")
    private String username;

    @ExcelField(value = "手机号")
    private String mobile;

    @ExcelField(value = "邮箱")
    private String email;

    @ExcelField(value = "创建时间", dateFormat = "yyyy/MM/dd")
    private Date createTime;

}
