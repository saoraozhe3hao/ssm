package com.hogen.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        super.setCredentialsMatcher(matcher);
    }
    @Override // 认证
    // UsernamePasswordToken 是 AuthenticationToken的子类，这里的入参就是 subject.login()传进来的token
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();  // 获取首要信息，即UsernamePasswordToken里的username
        String password = getPasswordByUsername(username);
        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "MyRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("salt_1"));
        return authenticationInfo;
    }

    @Override    // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUsername(username);
        Set<String> permissions = getPermissionsByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    private String getPasswordByUsername(String username) {
        return "eafb8cb896588a487ed9679994d25284";
    }

    private Set<String> getRolesByUsername(String userName) {
        Set roleSet = new HashSet<String>();
        roleSet.add("role_1");
        roleSet.add("role_2");
        return roleSet;
    }

    private Set<String> getPermissionsByUsername(String username) {
        Set permissionSet = new HashSet<String>();
        permissionSet.add("user:delete");
        permissionSet.add("user:update");
        return permissionSet;
    }
}
