package com.vicente.vicenteboot.service;

import com.vicente.vicenteboot.entity.ReportUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weisen
 * @since 2020-01-14
 */
public interface ReportUserService extends IService<ReportUser> {

    String getPasswordByUserName(String userName);
}
