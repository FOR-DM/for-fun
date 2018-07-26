package com.ths.forfun.service.impl;

import com.ths.forfun.pojo.User;
import com.ths.forfun.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * com.ths.forfun.service.impl
 *
 * @author: wangqd
 * @since: 2018/7/20
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public void login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getIdentity(),user.getPassword());
        subject.login(token);
    }

    @Override
    public Set<String> listPrivs(Long id) {
        return null;
    }

}
