package com.ths.forfun.shiro;


import com.ths.forfun.pojo.User;
import com.ths.forfun.service.AuthService;
import com.ths.forfun.service.UserService;
import org.apache.shiro.SecurityUtils;
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
import org.springframework.context.annotation.Lazy;

/**
 * 授权策略
 *
 * @author richer
 * @since 2018/1/11
 */
public class AuthRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private AuthService authService;

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String identity = (String) authenticationToken.getPrincipal();

        User user = userService.getByIdentity(identity);
        SecurityUtils.getSubject().getSession().setAttribute("user_id", user.getId());
        SecurityUtils.getSubject().getSession().setAttribute("user_name", user.getName());
        return new SimpleAuthenticationInfo(user.getIdentity(), user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), this.getName());
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long id = ShiroUtil.getCurrentUserId();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(authService.listPrivs(id));
        return authorizationInfo;
    }

}
