package com.vicente.vicenteboot.service.impl;

import com.vicente.vicenteboot.easyexcel.DompRoleExcel;
import com.vicente.vicenteboot.easyexcel.DompRoleUserExcel;
import com.vicente.vicenteboot.entity.DompRole;
import com.vicente.vicenteboot.entity.DompRoleUserRef;
import com.vicente.vicenteboot.mapper.DompRoleUserRefMapper;
import com.vicente.vicenteboot.service.DompRoleService;
import com.vicente.vicenteboot.service.DompRoleUserRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weisen
 * @since 2020-03-17
 */
@Service
public class DompRoleUserRefServiceImpl extends ServiceImpl<DompRoleUserRefMapper, DompRoleUserRef> implements DompRoleUserRefService {
    @Autowired
    private DompRoleService dompRoleService;

    @Override
    public boolean saveRoleUserBatch(List<DompRoleUserExcel> list) {
        List<DompRoleUserRef> resList = transferDompRole(list);
        return this.saveBatch(resList);
    }

    private Map changeRoleIdMap(){
        Map hashMap = new HashMap();
        List<DompRole> list = dompRoleService.list();
        for (DompRole role: list){
            hashMap.put(role.getRid(),role.getRoleId());
        }
        return hashMap;
    }



    private List<DompRoleUserRef> transferDompRole(List<DompRoleUserExcel> list){
        Map hashMap = changeRoleIdMap();
        List<DompRoleUserRef> resList = new ArrayList<>();
        for (DompRoleUserExcel easyExcel : list) {
            DompRoleUserRef ref = new DompRoleUserRef();
            ref.setRoleId((Long) hashMap.get(easyExcel.getRoleId()));
            ref.setUserId(easyExcel.getUserId());
            resList.add(ref);
        }
        return resList;
    }
}
