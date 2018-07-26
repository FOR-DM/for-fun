package com.ths.forfun.service.impl;

import com.ths.forfun.pojo.User;
import com.ths.forfun.repository.UserRepository;
import com.ths.forfun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.ths.forfun.service.impl
 *
 * @author: wangqd
 * @since: 2018/7/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(Long id) {

        return null;
    }

    @Override
    public User getByIdentity(String identity) {
        return userRepository.findByIdentity(identity);
    }
}
