package com.vicente.vicenteboot.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author shiweisen
 * @since 2018-11-29
 */
@Data
public class DemoDto {

    public interface Default {
    }

    public interface Update {
    }

    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotEmpty(message = "{demo.key.null}")
    @Length(min = 5, max = 25, message = "key的长度为5-25" )
    private String key;

    @Pattern(regexp = "[012]", message = "无效的状态标志",groups = {Default.class,Update.class} )
    private String state;

}
