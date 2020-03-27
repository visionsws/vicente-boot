package com.vicente.vicenteboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vicente.vicenteboot.easyexcel.DemoEasyExcel;
import com.vicente.vicenteboot.easyexcel.DompRoleExcel;
import com.vicente.vicenteboot.entity.DemoExcel;
import com.vicente.vicenteboot.entity.DompRole;
import com.vicente.vicenteboot.mapper.DompRoleMapper;
import com.vicente.vicenteboot.service.DompRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
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
            role.setModifyUser(easyExcel.getModifyUser());
            resList.add(role);
        }
        return resList;
    }

    private List<DompRoleExcel> transferDompRoleExcel(List<DompRole> list){
        List<DompRoleExcel> resList = new ArrayList<>();
        for (DompRole easyExcel : list) {
            DompRoleExcel role = new DompRoleExcel();
            role.setRoleId(easyExcel.getRoleId());
            role.setRid(easyExcel.getRid());
            role.setRoleName(easyExcel.getRoleName());
            role.setRoleDes(easyExcel.getRoleDes());
            role.setModifyUser(easyExcel.getModifyUser());
            resList.add(role);
        }
        return resList;
    }

    @Override
    public List<DompRoleExcel> selectRoleForExcel() {
        Wrapper<DompRole> queryWrapper = new QueryWrapper<DompRole>().lt("rid", "140382957");
        List<DompRole> roleList = this.baseMapper.selectList(queryWrapper);
        List<DompRoleExcel> resList = transferDompRoleExcel(roleList);
        return resList;
    }

}
