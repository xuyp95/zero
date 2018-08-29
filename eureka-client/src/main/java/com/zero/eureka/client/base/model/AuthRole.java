package com.zero.eureka.client.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色实体类
 * @Author:xuyp
 * @Date:2018/6/29 22:42
 */
@Getter
@Setter
@Entity
@Table(name = "auth_role")
public class AuthRole implements Serializable {

    private static final long serialVersionUID = 1847837105156375667L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
    private String id;

    /**
     * 角色名称
     */
    @Column(name = "name", length = 64)
    private String name;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time", updatable = false, columnDefinition = "datetime comment '创建时间'")
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    @Column(name = "update_time", columnDefinition = "datetime comment '更新时间'")
    private LocalDateTime updateTime;

    /**
     * 所属用户
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleList")
    private List<AuthUser> userList;

    /**
     * 所拥有权限
     */
    @ManyToMany
    @JoinTable(name = "auth_role_permission", joinColumns = {@JoinColumn(name = "role_id")},
        inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<AuthPermission> permissionList;
}
