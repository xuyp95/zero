package com.zero.file.service.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author:xuyp
 * @Date:2018/8/6 21:56
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -7243826361621111600L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(strategy = "uuid", name = "uuidGenerator")
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "datetime comment '创建时间'")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", columnDefinition = "datetime comment '更新时间'")
    private LocalDateTime updateTime;

    /**
     * 创建者
     */
    @Column(name = "creator", columnDefinition = "varchar(128) comment '创建者'")
    private String creator;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
