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

    @ExcelProperty(value = {"角色", "角色ID"})
    private Long roleId;

    @ExcelProperty(value = {"角色", "角色编号"})
    private Long rid;

    @ExcelProperty(value = {"角色", "角色名称"})
    private String roleName;

    @ExcelProperty(value = {"角色", "角色状态"})
    private String status;

    @ExcelProperty(value = {"角色", "角色描述"})
    private String roleDes;

    @ExcelProperty(value = "创建时间")
    private String createDate;

    @ExcelProperty(value = "修改时间")
    private String updateDate;

    @ExcelProperty(value = "修改人")
    private String modifyUser;


}
