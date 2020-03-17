package com.vicente.vicenteboot.service;

import com.vicente.vicenteboot.easyexcel.DompRoleExcel;
import com.vicente.vicenteboot.entity.DompRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weisen
 * @since 2020-03-17
 */
public interface DompRoleService extends IService<DompRole> {

    boolean saveRoleBatch(List<DompRoleExcel> list);
}
