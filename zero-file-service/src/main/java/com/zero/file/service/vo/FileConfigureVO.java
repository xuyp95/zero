package com.zero.file.service.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 参数VO
 * @Author:xuyp
 * @Date:2018/8/16 22:40
 */
@Data
public class FileConfigureVO implements Serializable{
    private static final long serialVersionUID = -4486047012993678380L;

    /**
     * 文件上传方式
     */
    private String uploadWay;

    /**
     * 本地上传地址
     */
    private String localfilePath;

    /**
     * smb共享文件地址
     */
    private String smbFilePath;

    @Override
    public String toString() {
        return "FileConfigureVO{" +
                "uploadWay='" + uploadWay + '\'' +
                ", localfilePath='" + localfilePath + '\'' +
                ", smbFilePath='" + smbFilePath + '\'' +
                '}';
    }
}
