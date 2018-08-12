package com.zero.file.service.vo;

/**
 * 上传文件信息
 * @Author:xuyp
 * @Date:2018/8/12 16:14
 */
public class ZeroFileInfoVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 原始文件名
     */
    private String originalFileName;

    /**
     * 保存文件路径
     */
    private String path;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建者
     */
    private String creator;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
