package com.vicente.vicenteboot.controller;

import com.vicente.vicenteboot.common.ResultBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("doLogin")
    public ResultBean doLogin(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            return new ResultBean("登录失败，用户名或密码错误！");
        }
        return new ResultBean("登录成功");
    }

}
