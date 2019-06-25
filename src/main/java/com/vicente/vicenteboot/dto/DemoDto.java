package com.vicente.vicenteboot.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author shiweisen
 * @since 2018-11-29
 */
@Data
public class DemoDto {

    @NotEmpty(message = "{demo.key.null}")
    @Length(min = 5, max = 25, message = "{demo.key.length}")
    private String key;
}
