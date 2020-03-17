package com.vicente.vicenteboot.service.impl;

import com.vicente.vicenteboot.easyexcel.DemoEasyExcel;
import com.vicente.vicenteboot.easyexcel.DompRoleExcel;
import com.vicente.vicenteboot.entity.DemoExcel;
import com.vicente.vicenteboot.entity.DompRole;
import com.vicente.vicenteboot.mapper.DompRoleMapper;
import com.vicente.vicenteboot.service.DompRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weisen
 * @since 2020-03-17
 */
@Service
public class DompRoleServiceImpl extends ServiceImpl<DompRoleMapper, DompRole> implements DompRoleService {

    @Override
    public boolean saveRoleBatch(List<DompRoleExcel> list) {
        List<DompRole> resList = transferDompRole(list);
        return this.saveBatch(resList);
    }

    private List<DompRole> transferDompRole(List<DompRoleExcel> list){
        List<DompRole> resList = new ArrayList<>();
        for (DompRoleExcel easyExcel : list) {
            DompRole role = new DompRole();
            role.setRoleId(easyExcel.getRoleId());
            role.setRid(easyExcel.getRid());
            role.setRoleName(easyExcel.getRoleName());
            role.setRoleDes(easyExcel.getRoleDes());
            //role.setStatus(Integer.parseInt(easyExcel.getStatus()));
            role.setModifyUser(easyExcel.getModifyUser());
           /* LocalDateTime createTime = LocalDateTime.parse(easyExcel.getCreateDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            role.setCreateDate(createTime);
            LocalDateTime updateTime = LocalDateTime.parse(easyExcel.getUpdateDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            role.setUpdateDate(updateTime);*/
            resList.add(role);
        }
        return resList;
    }
}
