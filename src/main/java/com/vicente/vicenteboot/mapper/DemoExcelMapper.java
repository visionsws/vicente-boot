package com.vicente.vicenteboot.mapper;

import com.vicente.vicenteboot.entity.DemoExcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weisen
 * @since 2020-02-27
 */
public interface DemoExcelMapper extends BaseMapper<DemoExcel> {

    /**
     * 批量插入
     * @param users
     */
    void batchInsert(List<DemoExcel> users);

}
