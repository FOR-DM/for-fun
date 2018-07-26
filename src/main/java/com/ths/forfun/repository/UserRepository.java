package com.ths.forfun.repository;

import com.ths.forfun.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * com.ths.forfun.repository
 *
 * @author: wangqd
 * @since: 2018/7/26
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByIdentity(String identity);
}
