package com.vicente.vicenteboot.service;

import com.vicente.vicenteboot.easyexcel.DompRoleUserExcel;
import com.vicente.vicenteboot.entity.DompRoleUserRef;
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
public interface DompRoleUserRefService extends IService<DompRoleUserRef> {

    boolean saveRoleUserBatch(List<DompRoleUserExcel> list);
}
