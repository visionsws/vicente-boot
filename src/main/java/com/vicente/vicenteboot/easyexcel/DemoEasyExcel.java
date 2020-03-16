package com.vicente.vicenteboot.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.time.LocalDateTime;

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
