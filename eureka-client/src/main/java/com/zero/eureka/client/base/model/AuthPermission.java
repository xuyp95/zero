package com.zero.eureka.client.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统权限
 * @Author:xuyp
 * @Date:2018/7/1 23:21
 */
@Getter
@Setter
@Entity
@Table(name = "auth_permission")
public class AuthPermission implements Serializable{

    private static final long serialVersionUID = -2368720725055321098L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
    private String id;

    /**
     * 权限名称
     */
    @Column(name = "name", length = 128)
    private String name;

    /**
     * 资源类型  columnDefinition = "emu('menu','button')"
     */
    @Column(name = "resource_type")
    private String reourceType;

    /**
     * 请求路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 所属父类
     */
    @Column(name = "parent_id", length = 64)
    private String parentId;

    /**
     * 状态
     */
    @Column(name = "available")
    private Boolean available = Boolean.FALSE;

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
     * 所属角色
     */
    @ManyToMany(mappedBy = "permissionList")
    private List<AuthRole> roleList;
}
