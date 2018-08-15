package com.zero.file.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文件模块中有关的配置参数表
 * @Author:xuyp
 * @Date:2018/8/12 23:09
 */
@Entity
@Table(name = "file_config")
public class FileConfig extends BaseModel {

    /**
     * 配置参数的键
     */
    @Column(name = "config_key", unique = true, columnDefinition = "varchar(128) comment '配置参数的键'")
    private String configKey;

    /**
     * 配置参数的值
     */
    @Column(name = "config_value", columnDefinition = "varchar(255) comment '配置参数的值'")
    private String configValue;

    public FileConfig() {
    }

    public FileConfig(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }

    public String getConfigKey() {
        return this.configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return this.configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
