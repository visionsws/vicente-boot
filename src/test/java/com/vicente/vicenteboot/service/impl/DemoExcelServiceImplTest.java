package com.vicente.vicenteboot.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.vicente.vicenteboot.easyexcel.*;
import com.vicente.vicenteboot.entity.DemoExcel;
import com.vicente.vicenteboot.entity.DompRole;
import com.vicente.vicenteboot.service.DemoExcelService;
import com.vicente.vicenteboot.service.DompRoleService;
import com.vicente.vicenteboot.service.DompRoleUserRefService;
import com.vicente.vicenteboot.util.TestFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoExcelServiceImplTest {

    @Autowired
    private DemoExcelService demoExcelService;

    @Autowired
    private DompRoleService dompRoleService;

    @Autowired
    private DompRoleUserRefService dompRoleUserRefService;

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
        //EasyExcel.read(fileName, DemoEasyExcel.class, new DemoExcelListener(demoExcelService)).sheet().doRead();

        ExcelReader excelReader = EasyExcel.read(fileName).build();
        // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
        ReadSheet readSheet1 =
                EasyExcel.readSheet(0).head(DemoEasyExcel.class).registerReadListener(new DemoExcelListener(demoExcelService)).build();

        // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
        excelReader.read(readSheet1);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    @Test
    public void converterRead() {
        String fileName ="D://demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, ConverterData.class, new ConverterDataListener())
                // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
                // 如果就想单个字段使用请使用@ExcelProperty 指定converter
                // .registerConverter(new CustomStringStringConverter())
                // 读取sheet
                .sheet().doRead();
    }


    @Test
    public void testReadRoleExcel() {
        String fileName = "D://role.xlsx";
        EasyExcel.read(fileName, DompRoleExcel.class, new DompRoleExcelListener(dompRoleService)).sheet().doRead();
    }

    @Test
    public void testReadRoleRefExcel() {
        String fileName = "D://role_user.xlsx";
        EasyExcel.read(fileName, DompRoleUserExcel.class, new DompRoleUserExcelListener(dompRoleUserRefService)).sheet().doRead();
    }


    @Test
    public void testWriteRoleExcel() {
        List<DompRoleExcel> list = dompRoleService.selectRoleForExcel();
        System.out.println(list.size());
        // 写法1
        String fileName =  "D://simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DompRoleExcel.class).sheet("模板").doWrite(list);

        // 写法2
        fileName = "D://simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DompRoleExcel.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(list, writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 最简单的填充
     *
     * @since 2.1.1
     */
    @Test
    public void testSimpleFill() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "D://simple.xlsx";
        // 方案2 根据Map填充
        String fileName = "D://simpleFill" + System.currentTimeMillis() + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        List<Map<String, Object>> list = new ArrayList();
        for (int i=0;i<10;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("name", "张三"+i);
            map.put("number", 5.2+i);
            list.add(map);
        }
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(list);
    }

}