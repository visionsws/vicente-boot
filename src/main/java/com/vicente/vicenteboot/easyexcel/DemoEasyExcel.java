package com.vicente.vicenteboot.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoEasyExcel  {

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("内容")
    private String content;

    @ExcelProperty("页码")
    private String pageNum;

    @ExcelProperty("创建时间")
    private String createTime;

}
