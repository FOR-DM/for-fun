package com.ths.forfun.service;

import com.ths.forfun.pojo.User;

import java.util.Set;

/**
 * 权限控制相关
 *
 * @author: wangqd
 * @since: 2018/7/20
 */
public interface AuthService {

    /**
     * 权限集合
     *
     * @param id
     * @return
     */
    Set<String> listPrivs(Long id);

    /**
     * 登录
     *
     * @param user
     */
    void login(User user);
}
