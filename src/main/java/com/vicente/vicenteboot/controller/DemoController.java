package com.vicente.vicenteboot.controller;

import com.vicente.vicenteboot.common.ResultBean;
import com.vicente.vicenteboot.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

/**
 * @author shiweisen
 * @since 2018-11-28
 */
@RestController
public class DemoController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("getMessageByKey")
    public ResultBean getMessageByKey(@Valid @RequestBody DemoDto dto){
        String key = dto.getKey();
        String [] param = {"2019-8-8", "2019-9-9"};
        return new ResultBean(messageSource.getMessage(key, param, Locale.CHINA));
    }

    @RequestMapping("test")
    public ResultBean test(@Valid @RequestBody DemoDto dto){
        System.out.println("test....................");
        return new ResultBean("test.........................");
    }


}
