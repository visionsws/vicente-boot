package com.vicente.vicenteboot.config;

import com.vicente.vicenteboot.service.ReportUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ReportUserService reportUserService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:add");
        sets.add("user:select");
        return sets;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("system");
        return sets;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1、从主体传过来的认证信息中，获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //2、通过用户名到数据库中获取凭证
        String password = reportUserService.getPasswordByUserName(userName);
        if (password == null){
            return null;
        }
        /**
         * 四个参数
         * principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
         * credentials：数据库中的密码（经过加密的密码）
         * credentialsSalt：盐值（使用用户名）
         * realmName：当前realm对象的name，调用父类的getName()方法即可
         */
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName,
                password,
                credentialsSalt,
                getName()
        );
        return authenticationInfo;
    }

}