package com.zero.file.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 上传文件组
 * @Author:xuyp
 * @Date:2018/8/6 22:17
 */
@Table(name = "zero_file_group")
@Entity
public class ZeroFileGroup extends BaseModel {

    /**
     * 文件组关联的文件
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "file_group_id")
    private List<ZeroFileInfo> fileInfo;

    @Column(name = "group_key", unique = true, columnDefinition = "char(36) comment '分组标识'")
    private String groupKey;

    public ZeroFileGroup() {
        this.setCreateTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    public ZeroFileGroup(String groupKey) {
        this.groupKey = groupKey;
        this.setCreateTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    public List<ZeroFileInfo> getFileInfo() {
        return this.fileInfo;
    }

    public void setFileInfo(List<ZeroFileInfo> fileInfo) {
        this.fileInfo = fileInfo;
    }
}
