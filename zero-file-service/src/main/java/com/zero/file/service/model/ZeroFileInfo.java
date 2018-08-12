package com.zero.file.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 上传文件信息表
 * @Author:xuyp
 * @Date:2018/8/6 22:05
 */
@Entity
@Table(name = "zero_file_info")
public class ZeroFileInfo extends BaseModel {

    /**
     * 原始文件名
     */
    @Column(name = "original_file_name", columnDefinition = "varchar(128) comment '原始文件名'")
    private String originalFileName;

    /**
     * 文件保存路径，本地硬盘路径/共享文件路径
     */
    @Column(name = "path", columnDefinition = "varchar(512) comment '文件保存路径'")
    private String path;

    /**
     * 保存文件方式，保存到本地或者保存到共享文件夹，etc.
     */
    @Column(name = "save_file_way", columnDefinition = "char(16) comment '保存文件的方式'")
    private String saveFileWay;

    /**
     * 关联文件组
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ZeroFileGroup fileGroup;

    public ZeroFileInfo() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    public String getOriginalFileName() {
        return this.originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSaveFileWay() {
        return this.saveFileWay;
    }

    public void setSaveFileWay(String saveFileWay) {
        this.saveFileWay = saveFileWay;
    }

    public ZeroFileGroup getFileGroup() {
        return this.fileGroup;
    }

    public void setFileGroup(ZeroFileGroup fileGroup) {
        this.fileGroup = fileGroup;
    }
}
