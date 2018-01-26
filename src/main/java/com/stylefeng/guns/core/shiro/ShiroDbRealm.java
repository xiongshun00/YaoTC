package com.stylefeng.guns.core.shiro;

import com.stylefeng.guns.core.shiro.factory.IShiro;
import com.stylefeng.guns.core.shiro.factory.ShiroFactroy;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.common.persistence.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 登录认证
     * 通过用户名查找数据库用户进行和用户输入的信息对比
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        IShiro shiroFactory = ShiroFactroy.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = shiroFactory.user(token.getUsername());
        ShiroUser shiroUser = shiroFactory.shiroUser(user);
        SimpleAuthenticationInfo info = shiroFactory.info(shiroUser, user, super.getName());
        return info;
    }

    /**
     * 权限认证
     * 登陆成功后告诉shiro该用户有哪些资源可以访问
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        IShiro shiroFactory = ShiroFactroy.me();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Integer> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        for (Integer roleId : roleList) {
            List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
            if (permissions != null) {
                for (String permission : permissions) {
                    if (ToolUtil.isNotEmpty(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
            String roleName = shiroFactory.findRoleNameByRoleId(roleId);
            roleNameSet.add(roleName);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
