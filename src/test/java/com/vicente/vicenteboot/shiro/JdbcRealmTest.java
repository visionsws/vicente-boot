package com.vicente.vicenteboot.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import javax.sql.DataSource;

public class JdbcRealmTest {
    DruidDataSource datasource = new DruidDataSource();
    {
        datasource.setUrl("jdbc:mysql://192.168.243.20:9097/user_role");
        datasource.setUsername("root");
        datasource.setPassword("b#12345678");
    }



    @Test
    public void testAuthentication(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(datasource);

        String authSql = "select phone from aur_member where member_name = ?";
        jdbcRealm.setAuthenticationQuery(authSql);
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //获取主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //主体提交认证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tim1", "13751836453");
        subject.login(usernamePasswordToken);

        System.out.println("subject.isAuthenticated: "+subject.isAuthenticated());
        subject.logout();
        System.out.println("subject.isAuthenticated: "+subject.isAuthenticated());


    }



}
