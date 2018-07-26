package com.ths.forfun.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * com.ths.forfun.pojo
 *
 * @author: wangqd
 * @since: 2018/7/20
 */
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String identity;

    private String name;

    private String salt;
}
