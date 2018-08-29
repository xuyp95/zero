package com.zero.eureka.client.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户实体
 * @Author:xuyp
 * @Date:2018/8/27 23:15
 */
@Getter
@Setter
@Entity
@Table(name = "auth_user")
public class AuthUser implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
    private String id;

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "varchar(50) comment '姓名'", unique = true)
    private String name;

    /**
     * 账户名
     */
    @Column(name = "userName", nullable = false, columnDefinition = "varchar(64) comment '账户名'")
    private String userName;

    /**
     * 密码  md5/BCrypt
     */
    @Column(name = "password", nullable = false, columnDefinition = "varchar(64) comment '密码'")
    private String password;

    /**
     * 盐
     */
    @Column(name = "salt", columnDefinition = "varchar(64) comment '盐'")
    private String salt;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "varchar(64) comment '邮箱'")
    private String email;

    /**
     * 用户状态
     */
    @Column(name = "state")
    private Byte state;////用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    /**
     * 创建时间
     */
    @Column(name = "create_time", updatable = false, columnDefinition = "datetime comment '创建时间'")
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    @Column(name = "update_time", updatable = false, columnDefinition = "datetime comment '更新时间'")
    private LocalDateTime updateTime;

    @ManyToMany(fetch = FetchType.LAZY)// 懒加载， EAGER：立即加载
    @JoinTable(name = "auth_user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<AuthRole> roleList;
}
