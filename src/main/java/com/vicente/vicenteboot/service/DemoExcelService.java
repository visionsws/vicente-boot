package com.vicente.vicenteboot.service;

import com.vicente.vicenteboot.easyexcel.DemoEasyExcel;
import com.vicente.vicenteboot.entity.DemoExcel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weisen
 * @since 2020-02-27
 */
public interface DemoExcelService extends IService<DemoExcel> {

    void saveList(List<DemoExcel> list);

    void saveExcelList(List<DemoEasyExcel> list);
}
