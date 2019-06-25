package com.vicente.vicenteboot.controller;

import com.vicente.vicenteboot.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
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
    public String getMessageByKey(@Valid @RequestBody DemoDto dto){
        String key = dto.getKey();
        String [] param = {"11111111", "222222222222"};
        return messageSource.getMessage(key, param, Locale.CHINA);
    }

    @RequestMapping("test")
    public String test(){
        System.out.println("test....................");
        return "zuul test.........................";
    }
}
