package com.xyz.modules.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2019-09-30 9:15
 */

// lombok支持似乎有点问题 可能和chain的设置有关
//@Data
public class DemoUserExcelDTO {
    @ExcelProperty("签约代表")
    private String name;

    @ExcelProperty("签约日期")
    @DateTimeFormat("yyyy年MM月dd日")
    private Date date;
//    private String date;

//    @ExcelProperty("金额")
//    private Double doubleData;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
