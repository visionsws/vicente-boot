package com.vicente.vicenteboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author weisen
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReportSale extends Model<ReportSale> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String areaName;

    private String saleName;

    private String productType;

    private String productName;

    private String saleNum;

    private String englishName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
