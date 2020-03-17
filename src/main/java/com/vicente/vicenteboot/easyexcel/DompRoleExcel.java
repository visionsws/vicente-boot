package com.vicente.vicenteboot.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author weisen
 * @since 2020-03-17
 */
@Data
public class DompRoleExcel  {

    @ExcelProperty(index = 0)
    private Long roleId;

    @ExcelProperty(index = 1)
    private Long rid;

    @ExcelProperty(index = 2)
    private String roleName;

    @ExcelProperty(index = 3)
    private String status;

    @ExcelProperty(index = 4)
    private String roleDes;

    @ExcelProperty(index = 5)
    private String createDate;

    @ExcelProperty(index = 6)
    private String updateDate;

    @ExcelProperty(index = 7)
    private String modifyUser;


}
