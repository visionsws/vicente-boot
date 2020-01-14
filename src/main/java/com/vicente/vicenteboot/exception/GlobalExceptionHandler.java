package com.vicente.vicenteboot.exception;

import com.vicente.vicenteboot.common.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBean methodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException  ex)  {
        ResultBean result = ResultBean.FAIL;
        List<ObjectError> errors =ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg=new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        log.error("---MethodArgumentNotValidException Handler--- ERROR: {}", errorMsg.toString());
        result.setMsg(errorMsg.toString());
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    public ResultBean methodArgumentNotValid(HttpServletRequest req, Exception  ex)  {
        ResultBean result = ResultBean.FAIL;
        StringBuffer errorMsg=new StringBuffer();
        errorMsg.append(ex.getMessage());
        log.error("---Exception Handler--- ERROR: {}", errorMsg.toString());

        return result;
    }
}
