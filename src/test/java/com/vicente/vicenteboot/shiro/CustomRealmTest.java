package com.vicente.vicenteboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {

    @Test
    public void testAuthentication(){
        CustomRealm customRealm = new CustomRealm();
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //加密的算法
        matcher.setHashAlgorithmName("md5");
        //加密的次数
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        //获取主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //主体提交认证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin123");
        subject.login(usernamePasswordToken);

        System.out.println("subject.isAuthenticated: "+subject.isAuthenticated());

      /*  subject.checkRole("admin");
        subject.checkPermissions("user:delete","user:select");*/




    }


}
