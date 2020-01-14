package com.vicente.vicenteboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class ReportUser extends Model<ReportUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String realName;

    private String userName;

    private String password;

    private Integer age;

    private Integer sex;

    private String phone;

    private String email;

    private String address;

    /**
     * 民族
     */
    private String nation;

    /**
     * 职业
     */
    private String job;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
