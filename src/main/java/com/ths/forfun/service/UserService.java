package com.ths.forfun.service;

import com.ths.forfun.pojo.entity.User;

/**
 * com.ths.forfun.service
 *
 * @author: wangqd
 * @since: 2018/7/26
 */
public interface UserService {

    User get(Long id);

    User getByIdentity(String identity);
}
