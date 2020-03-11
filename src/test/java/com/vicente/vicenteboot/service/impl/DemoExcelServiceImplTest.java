package com.vicente.vicenteboot.service.impl;

import com.alibaba.excel.EasyExcel;
import com.vicente.vicenteboot.easyexcel.DemoEasyExcel;
import com.vicente.vicenteboot.easyexcel.DemoExcelListener;
import com.vicente.vicenteboot.entity.DemoExcel;
import com.vicente.vicenteboot.service.DemoExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoExcelServiceImplTest {

    @Autowired
    private DemoExcelService demoExcelService;

    @Test
    public void testSaveList() {
        DemoExcel excel = new DemoExcel();
        excel.setTitle("标题1");
        excel.setContent("内容11111");
        excel.setPageNum(3);
        excel.setCreateTime(LocalDateTime.now());
        List<DemoExcel> list = new ArrayList<>();
        list.add(excel);
        DemoExcel excel2 = new DemoExcel();
        excel2.setTitle("标题2");
        excel2.setContent("内容2222");
        excel2.setPageNum(5);
        excel2.setCreateTime(LocalDateTime.of(2019, 12, 12, 9, 21, 32));
        list.add(excel2);
        demoExcelService.saveList(list);
    }

    @Test
    public void testReadExcel() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "D://test_excel.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoEasyExcel.class, new DemoExcelListener(demoExcelService)).sheet().doRead();

    }

}