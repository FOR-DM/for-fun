package com.ths.forfun.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * shiro工具类
 *
 * @author richer
 * @since 2018/1/18
 */
@Component
public final class ShiroUtil {

    private ShiroUtil() {

    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    public static Long getCurrentUserId() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        Long userId = (Long) session.getAttribute("user_id");
        return userId != null ? userId : -1L;
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String getCurrentUserName() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        String userName = (String) session.getAttribute("user_name");
        return userName != null ? userName : "SYSTEM";
    }
}
