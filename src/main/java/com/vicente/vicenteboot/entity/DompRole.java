package com.vicente.vicenteboot.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DompRole extends Model<DompRole> {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long rid;

    private String roleName;

    private Integer status;

    private String roleDes;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String modifyUser;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
