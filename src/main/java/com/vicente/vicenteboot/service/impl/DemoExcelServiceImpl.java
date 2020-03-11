package com.vicente.vicenteboot.service.impl;

import com.vicente.vicenteboot.easyexcel.DemoEasyExcel;
import com.vicente.vicenteboot.entity.DemoExcel;
import com.vicente.vicenteboot.mapper.DemoExcelMapper;
import com.vicente.vicenteboot.service.DemoExcelService;
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
 * @since 2020-02-27
 */
@Service
public class DemoExcelServiceImpl extends ServiceImpl<DemoExcelMapper, DemoExcel> implements DemoExcelService {

    @Override
    public void saveList(List<DemoExcel> list) {
        this.baseMapper.batchInsert(list);
    }

    @Override
    public void saveExcelList(List<DemoEasyExcel> list) {
        List<DemoExcel> demoList = new ArrayList<>();
        for (DemoEasyExcel easyExcel : list) {
            DemoExcel demo = new DemoExcel();
            demo.setTitle(easyExcel.getTitle());
            demo.setContent(easyExcel.getContent());
            demo.setPageNum(Integer.parseInt(easyExcel.getPageNum()));
            LocalDateTime time = LocalDateTime.parse(easyExcel.getCreateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            demo.setCreateTime(time);
            demoList.add(demo);
        }
        this.saveList(demoList);
    }
}
