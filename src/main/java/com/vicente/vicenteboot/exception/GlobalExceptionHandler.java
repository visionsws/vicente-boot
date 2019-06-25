package com.vicente.vicenteboot.exception;

import com.vicente.vicenteboot.common.result.Result;
import com.vicente.vicenteboot.common.result.ResultBuilder;
import com.vicente.vicenteboot.common.result.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author shiweisen
 * @since 2018-11-29
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  /*  @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ReturnT methodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException  ex)  {
        ReturnT result = ReturnT.FAIL;
        String message = "";
        ServletContext servletContext = req.getServletContext();
        String exceptionCode = "";
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        if (errors.size() > 0) {
            exceptionCode = errors.get(0).getDefaultMessage();
        }
        message = message + messageSource.getMessage(exceptionCode, (Object[])null, ex.getMessage(), req.getLocale());
        log.error("---MethodArgumentNotValidException Handler--- ERROR: {}", message);
        result.setMsg(message);
        return result;
    }*/

   /* @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ReturnT methodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException  ex)  {
        ReturnT result = ReturnT.FAIL;
        List<ObjectError> errors =ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg=new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        log.error("---MethodArgumentNotValidException Handler--- ERROR: {}", errorMsg.toString());
        result.setMsg(errorMsg.toString());
        return result;
    }*/
}

