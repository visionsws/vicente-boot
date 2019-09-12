package com.vicente.vicenteboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class ShiroDemoTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("admin","admin123","admin","user");
    }


    @Test
    public void testAuthentication(){
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //获取主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //主体提交认证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin123");
        subject.login(usernamePasswordToken);

        System.out.println("subject.isAuthenticated: "+subject.isAuthenticated());
        subject.logout();
        System.out.println("subject.isAuthenticated: "+subject.isAuthenticated());


    }

    @Test
    public void testAuthorizer(){
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //获取主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //主体提交认证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin123");
        subject.login(usernamePasswordToken);

        System.out.println("subject.isAuthenticated: "+subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkRoles("admin2","user");

    }


}
