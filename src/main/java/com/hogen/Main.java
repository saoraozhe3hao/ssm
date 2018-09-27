package com.hogen;


import com.hogen.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class Main {
    public static void main(String[] args) throws Exception {


        MyRealm realm = new MyRealm();
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("username", "password");
        subject.login(token);  // token错误，则抛出异常
        System.out.println(subject.isAuthenticated());
        subject.checkRoles("role_1" , "role_2");  // 角色检查失败，则抛出异常
        subject.checkPermissions("user:delete", "user:update");  // 检查权限，检查失败则抛出异常
        subject.logout();
        System.out.println(subject.isAuthenticated());
    }
}


