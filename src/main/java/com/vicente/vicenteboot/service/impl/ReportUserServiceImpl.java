package com.vicente.vicenteboot.service.impl;

import com.vicente.vicenteboot.entity.ReportUser;
import com.vicente.vicenteboot.mapper.ReportUserMapper;
import com.vicente.vicenteboot.service.ReportUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weisen
 * @since 2020-01-14
 */
@Service
public class ReportUserServiceImpl extends ServiceImpl<ReportUserMapper, ReportUser> implements ReportUserService {

    @Override
    public String getPasswordByUserName(String userName) {
        return null;
    }
}
