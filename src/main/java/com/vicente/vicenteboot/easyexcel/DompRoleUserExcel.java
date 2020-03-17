package com.vicente.vicenteboot.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
public class DompRoleUserExcel {


    @ExcelProperty(index = 0)
    private Long id;

    @ExcelProperty(index = 1)
    private Long roleId;

    @ExcelProperty(index = 2)
    private String userId;


}
